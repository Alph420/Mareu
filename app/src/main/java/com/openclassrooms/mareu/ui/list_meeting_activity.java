package com.openclassrooms.mareu.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.events.DeleteMeetingEvent;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.service.MeetingApiService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class list_meeting_activity extends AppCompatActivity {

    FloatingActionButton mFloatingActionButton;
    RecyclerView mRecyclerView;
    MeetingApiService mApiService;
    private List<Meeting> mMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mApiService = DI.getMeetingApiService();

        mRecyclerView = findViewById(R.id.meeting_list);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new MeetingListRecyclerViewAdapter(mMeeting));

        initList();

        mFloatingActionButton = findViewById(R.id.add_meeting);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), add_meeting_activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.filterDate:

                break;

            case R.id.filterLocation:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initList() {
        mMeeting = mApiService.getMeeting();
        mRecyclerView.setAdapter(new MeetingListRecyclerViewAdapter(mMeeting));
    }

    //region RegionOverride
    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Resume");
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("Start");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("Stop");
        EventBus.getDefault().unregister(this);
    }
    //endregion

    @Subscribe
    public void onDeleteMeetingEvent(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.meeting);
        initList();
    }

}

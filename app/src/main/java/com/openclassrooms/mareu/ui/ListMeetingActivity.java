package com.openclassrooms.mareu.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.events.DeleteMeetingEvent;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.Room;
import com.openclassrooms.mareu.service.MeetingApiService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ListMeetingActivity extends AppCompatActivity {

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
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_filter_list_white_24dp));
       mApiService = DI.getMeetingApiService();

        mRecyclerView = findViewById(R.id.meeting_list);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new MeetingListRecyclerViewAdapter(mMeeting));

        initList();

        mFloatingActionButton = findViewById(R.id.add_meeting);
        mFloatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddMeetingActivity.class);
            startActivity(intent);
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
        List<String> salleList = Room.getSalle();
        switch (item.getItemId()) {

            case R.id.filterDate:

                break;

            case R.id.filterLocation:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
                mBuilder.setTitle("Salle");


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

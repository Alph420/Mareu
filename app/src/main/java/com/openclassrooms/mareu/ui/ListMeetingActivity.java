package com.openclassrooms.mareu.ui;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.DatePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_filter_list_white_24dp));
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
        switch (item.getItemId()) {

            case R.id.filterDate:
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                DatePicker picker = new DatePicker(this);
                picker.setCalendarViewShown(false);
                builder1.setView(picker);


                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = picker.getYear();
                        int mon = picker.getMonth();
                        int day = picker.getDayOfMonth();
                        Date date = new GregorianCalendar(year,mon,day).getTime();
                        initList(date);
                    }
                });
                builder1.setNegativeButton("Reset", (dialog, whichButton) -> initList());
                builder1.show();


                break;

            case R.id.filterLocation:
                List<String> salleList = Room.getSalle();
                String[] salleArray = new String[salleList.size()];
                salleArray = salleList.toArray(salleArray);
                final String[] salle = new String[1];
                final AlertDialog.Builder builderRoom = new AlertDialog.Builder(this);
                builderRoom.setTitle("Choisissez une Salle");

                String[] finalSalleArray = salleArray;

                builderRoom.setSingleChoiceItems(salleArray,-1,
                        (dialog, which) -> salle[0] = finalSalleArray[which]);

                builderRoom.setPositiveButton("OK", (dialogInterface, i) -> initList(salle[0]));

                builderRoom.setNegativeButton("Reset", (dialog, whichButton) -> initList());

                AlertDialog dialogRoom = builderRoom.create();

                dialogRoom.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initList(Date date){
        mMeeting = mApiService.getMeeting();
        List<Meeting> mMeetingFiltered = new ArrayList<>();
        for (Meeting meeting : mMeeting) {
            if (meeting.getDateStart().getDay()==date.getDay() && meeting.getDateStart().getMonth()==date.getMonth() && meeting.getDateStart().getYear()==date.getYear()) mMeetingFiltered.add(meeting);
        }
        mRecyclerView.setAdapter(new MeetingListRecyclerViewAdapter(mMeetingFiltered));
    }

    private void initList(String s) {
        mMeeting = mApiService.getMeeting();
        List<Meeting> mMeetingFiltered = new ArrayList<>();
        for (Meeting meeting : mMeeting) {
            if (meeting.getRoom().equals(s)) mMeetingFiltered.add(meeting);
        }
        mRecyclerView.setAdapter(new MeetingListRecyclerViewAdapter(mMeetingFiltered));
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

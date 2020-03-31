package com.openclassrooms.mareu.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.service.DummyMeetingApiService;
import com.openclassrooms.mareu.service.DummyMeetingGenerator;
import com.openclassrooms.mareu.service.MeetingApiService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class list_meeting_activity extends AppCompatActivity {

    FloatingActionButton mFloatingActionButton;


    MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mApiService = DI.getNeighbourApiService();


        ArrayAdapter<Meeting> adapter = new ArrayAdapter<Meeting>(this,
                android.R.layout.simple_dropdown_item_1line, mApiService.getMeeting());

        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.meeting_list);

        textView.setAdapter(adapter);

        mFloatingActionButton = findViewById(R.id.add_meeting);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), add_meeting_activity.class);
                startActivity(intent);
            }
        });
    }

}

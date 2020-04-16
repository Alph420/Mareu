package com.openclassrooms.mareu.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.Users;
import com.openclassrooms.mareu.model.Salle;
import com.openclassrooms.mareu.service.DummyMeetingGenerator;
import com.openclassrooms.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {

    Button mButtonBack;
    ImageView mImageView;
    DatePicker mDateMeeting;
    TimePicker mDateMeetingStart;
    TimePicker mDateMeetingEnd;
    Spinner mLocationMeeting;
    EditText mSujet_meeting;
    MultiAutoCompleteTextView mParticipant;
    Button mButtonSave;


    MeetingApiService mApiService;

    final int color = DummyMeetingGenerator.generateColor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting_activity);

        //region RegionInstance
        mApiService = DI.getMeetingApiService();
        Calendar calendar = Calendar.getInstance();


        mButtonBack = findViewById(R.id.buttonBack);

        mImageView = findViewById(R.id.color_meeting);
        mImageView.setBackgroundColor(color);

        mDateMeeting=findViewById(R.id.date_meeting);

        mDateMeetingStart = findViewById(R.id.date_meeting_start);
        mDateMeetingStart.setIs24HourView(true);

        mDateMeetingEnd = findViewById(R.id.date_meeting_end);
        mDateMeetingEnd.setIs24HourView(true);

        mLocationMeeting = findViewById(R.id.location_meeting);
        mSujet_meeting = findViewById(R.id.sujet_meeting);
        mParticipant = findViewById(R.id.participant);
        //endregion

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMeetingActivity.this, ListMeetingActivity.class);
                startActivity(intent);
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.setBackgroundColor(DummyMeetingGenerator.generateColor());
            }
        });


        //region RegionDatePicker
        int jour = mDateMeeting.getDayOfMonth();
        int month = mDateMeeting.getMonth();
        int year = mDateMeeting.getYear();

        final String date = String.valueOf(jour) + String.valueOf(month) + String.valueOf(year);
        //endregion

        //region RegionTimerPicker
        final int getHourStart;
        final int getMinuteStart;

        if (Build.VERSION.SDK_INT < 23) {
            getHourStart = mDateMeetingStart.getCurrentHour();
            getMinuteStart = mDateMeetingStart.getCurrentMinute();

            calendar.set(Calendar.HOUR_OF_DAY, mDateMeetingStart.getCurrentHour());
            calendar.set(Calendar.MINUTE, mDateMeetingStart.getCurrentMinute());
        } else {
            getHourStart = mDateMeetingStart.getHour();
            getMinuteStart = mDateMeetingStart.getMinute();

            calendar.set(Calendar.HOUR_OF_DAY, mDateMeetingStart.getHour());
            calendar.set(Calendar.MINUTE, mDateMeetingStart.getMinute());
        }

        final int getHourEnd;
        final int getMinuteEnd;

        if (Build.VERSION.SDK_INT < 23) {
            getHourEnd = mDateMeetingEnd.getCurrentHour();
            getMinuteEnd = mDateMeetingEnd.getCurrentMinute();

            calendar.set(Calendar.HOUR_OF_DAY, mDateMeetingEnd.getCurrentHour());
            calendar.set(Calendar.MINUTE, mDateMeetingEnd.getCurrentMinute());
        } else {
            getHourEnd = mDateMeetingEnd.getHour();
            getMinuteEnd = mDateMeetingEnd.getMinute();

            calendar.set(Calendar.HOUR_OF_DAY, mDateMeetingEnd.getHour());
            calendar.set(Calendar.MINUTE, mDateMeetingEnd.getMinute());
        }
        //endregion

        //region RegionSpinner
        List<String> area = Salle.getSalle();
        ArrayAdapter<String> salleArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, area);
        mLocationMeeting.setDropDownHorizontalOffset(android.R.layout.simple_dropdown_item_1line);
        mLocationMeeting.setAdapter(salleArrayAdapter);
        //endregion

        //region RegionAutoCompleteView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Users.listParticipants);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Users.listPrenom);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Users.listNom);



        MultiAutoCompleteTextView textView = findViewById(R.id.participant);
        textView.setAdapter(adapter);


        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //endregion

        //region RegionButtonSaveClick
        mButtonSave = findViewById(R.id.save_meeting);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String meetingTimeStart = String.valueOf(getHourStart) +'h'+ String.valueOf(getMinuteStart);
                String meetingTimeEnd = String.valueOf(getHourEnd) +'h'+ String.valueOf(getMinuteEnd);

                String particpants = mParticipant.getText().toString();

                String[] participantsList = particpants.split("\n");
                List<String> participantListMeeting = new ArrayList<String>();

                for (String participant : participantsList) {
                    System.out.println(participant);
                    participantListMeeting.add(participant);
                }

                Meeting meeting = new Meeting(DummyMeetingGenerator.getActualColor(), meetingTimeStart, meetingTimeEnd, mLocationMeeting.getSelectedItem().toString(), mSujet_meeting.getText().toString(), participantListMeeting);
                meeting.setDate(date);
                mApiService.createMeeting(meeting);
                finish();
            }
        });
        //endregion
    }
}

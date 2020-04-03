package com.openclassrooms.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.ParticipantsGenerator;
import com.openclassrooms.mareu.model.Salle;
import com.openclassrooms.mareu.service.DummyMeetingGenerator;
import com.openclassrooms.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class add_meeting_activity extends AppCompatActivity {

    ImageView mImageView;
    TimePicker mDateMeeting;
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

        mImageView = findViewById(R.id.color_meeting);
        mImageView.setBackgroundColor(color);

        mDateMeeting = findViewById(R.id.date_meeting);
        mDateMeeting.setIs24HourView(true);
        Calendar calendar = Calendar.getInstance();

        mLocationMeeting = findViewById(R.id.location_meeting);
        mSujet_meeting = findViewById(R.id.sujet_meeting);
        mParticipant = findViewById(R.id.participant);
        //endregion

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.setBackgroundColor(DummyMeetingGenerator.generateColor());
            }
        });

        //region RegionTimerPicker
        final int getHour;
        final int getMinute;

        if(Build.VERSION.SDK_INT < 23){
             getHour = mDateMeeting.getCurrentHour();
             getMinute = mDateMeeting.getCurrentMinute();

            calendar.set(Calendar.HOUR_OF_DAY, mDateMeeting.getCurrentHour());
            calendar.set(Calendar.MINUTE, mDateMeeting.getCurrentMinute());
        } else{
             getHour = mDateMeeting.getHour();
             getMinute = mDateMeeting.getMinute();

            calendar.set(Calendar.HOUR_OF_DAY, mDateMeeting.getHour());
            calendar.set(Calendar.MINUTE, mDateMeeting.getMinute());
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
                android.R.layout.simple_dropdown_item_1line, ParticipantsGenerator.listParticipants);
        MultiAutoCompleteTextView textView = findViewById(R.id.participant);
        textView.setAdapter(adapter);
        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //endregion

        //region RegionButtonSaveClick
        mButtonSave = findViewById(R.id.save_meeting);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int meetingTime = getHour + getMinute;

                String particpants = mParticipant.getText().toString();

                String[] participantsList = particpants.split("\n");
                List<String> participantListMeeting = new ArrayList<String>();

                for (String participant : participantsList) {
                    System.out.println(participant);
                    participantListMeeting.add(participant);
                }

                Meeting meeting = new Meeting(DummyMeetingGenerator.getActualColor(), meetingTime, mLocationMeeting.getSelectedItem().toString(), mSujet_meeting.getText().toString(), participantListMeeting);
                mApiService.createMeeting(meeting);
                finish();
            }
        });
        //endregion
    }
}

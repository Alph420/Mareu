package com.openclassrooms.mareu.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.User;
import com.openclassrooms.mareu.model.Room;
import com.openclassrooms.mareu.service.DummyMeetingGenerator;
import com.openclassrooms.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

    MeetingApiService mApiService= DI.getMeetingApiService();

    final int color = DummyMeetingGenerator.generateColor();
    Calendar calendarStart = Calendar.getInstance();
    Calendar calendarEnd = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        //region RegionInstance
        mButtonBack = findViewById(R.id.buttonBack);

        mImageView = findViewById(R.id.color_meeting);
        mImageView.setBackgroundColor(color);

        mDateMeeting = findViewById(R.id.date_meeting);

        mDateMeetingStart = findViewById(R.id.date_meeting_start);
        mDateMeetingStart.setIs24HourView(true);

        mDateMeetingEnd = findViewById(R.id.date_meeting_end);
        mDateMeetingEnd.setIs24HourView(true);

        mLocationMeeting = findViewById(R.id.location_meeting);
        mSujet_meeting = findViewById(R.id.sujet_meeting);
        mParticipant = findViewById(R.id.participant);
        //endregion

        mButtonBack.setOnClickListener(v -> startActivity(new Intent(AddMeetingActivity.this, ListMeetingActivity.class)));
        mImageView.setOnClickListener(v -> mImageView.setBackgroundColor(DummyMeetingGenerator.generateColor()));


        //region RegionSpinner
        List<String> area = Room.getSalle();
        ArrayAdapter<String> salleArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, area);
        mLocationMeeting.setDropDownHorizontalOffset(android.R.layout.simple_dropdown_item_1line);
        mLocationMeeting.setAdapter(salleArrayAdapter);
        //endregion

        //region RegionVerifyContentSujet
        mSujet_meeting.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 0) {
                    mSujet_meeting.setBackgroundColor(Color.RED);
                    mSujet_meeting.setAlpha((float) 0.5);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 4 && !s.toString().equals("         ")) {
                    mSujet_meeting.setBackgroundColor(Color.WHITE);
                    mSujet_meeting.setAlpha((float) 1);
                    mButtonSave.setEnabled(true);
                    mButtonSave.setBackgroundColor(Color.CYAN);
                }
                if (s.length() < 4) {
                    mSujet_meeting.setBackgroundColor(Color.RED);
                    mSujet_meeting.setAlpha((float) 0.5);
                    mButtonSave.setEnabled(false);
                    mButtonSave.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //endregion

        //region RegionAutoCompleteView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, User.listParticipants);

        mParticipant.setAdapter(adapter);


        mParticipant.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //endregion

        //region RegionButtonSaveClick
        mButtonSave = findViewById(R.id.save_meeting);
        mButtonSave.setEnabled(false);
        mButtonSave.setBackgroundColor(Color.RED);
        mButtonSave.setTextColor(Color.WHITE);
        mButtonSave.setOnClickListener(v -> {
            TextView mErrorTextView = findViewById(R.id.text_error);

            //region RegionDatePicker

            int jour = mDateMeeting.getDayOfMonth();
            int month = mDateMeeting.getMonth();
            int year = mDateMeeting.getYear();
            calendarStart.set(year, month, jour);
            //endregion

            //region RegionTimerPicker
            Date dateStart = calendarStart.getTime();
            Date dateEnd = calendarStart.getTime();

            final int getHourStart;
            final int getMinuteStart;
            if (Build.VERSION.SDK_INT < 23) {
                getHourStart = mDateMeetingStart.getCurrentHour();
                getMinuteStart = mDateMeetingStart.getCurrentMinute();

                dateStart.setHours(getHourStart);
                dateStart.setMinutes(getMinuteStart);
                calendarStart.setTime(dateStart);
            } else {
                getHourStart = mDateMeetingStart.getHour();
                getMinuteStart = mDateMeetingStart.getMinute();

                dateStart.setHours(getHourStart);
                dateStart.setMinutes(getMinuteStart);
                calendarStart.setTime(dateStart);
            }

            final int getHourEnd;
            final int getMinuteEnd;
            if (Build.VERSION.SDK_INT < 23) {
                getHourEnd = mDateMeetingEnd.getCurrentHour();
                getMinuteEnd = mDateMeetingEnd.getCurrentMinute();

                dateEnd.setHours(getHourEnd);
                dateEnd.setMinutes(getMinuteEnd);
                calendarEnd.setTime(dateEnd);

            } else {
                getHourEnd = mDateMeetingEnd.getHour();
                getMinuteEnd = mDateMeetingEnd.getMinute();

                dateEnd.setHours(getHourEnd);
                dateEnd.setMinutes(getMinuteEnd);
                calendarEnd.setTime(dateEnd);
            }
                //endregion

            String[] participantsList = mParticipant.getText().toString().split("\n");

            List<String> participantListMeeting = new ArrayList<>(Arrays.asList(participantsList));


            Meeting meeting = new Meeting(DummyMeetingGenerator.getActualColor(), mLocationMeeting.getSelectedItem().toString(), calendarStart.getTime(), calendarEnd.getTime(), mSujet_meeting.getText().toString(), participantListMeeting);
            if (mApiService.checkingMetting(meeting)) {
                mApiService.createMeeting(meeting);
                finish();
            } else {
                mErrorTextView.setText(getString(R.string.errorPart1) + " (" + mLocationMeeting.getSelectedItem().toString() + ") " + getString(R.string.errorPart2));
                mErrorTextView.setVisibility(View.VISIBLE);
            }
        });
        //endregion
    }
}

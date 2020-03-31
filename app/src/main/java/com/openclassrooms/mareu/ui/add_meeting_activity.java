package com.openclassrooms.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.service.MeetingApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.graphics.Color.rgb;

public class add_meeting_activity extends AppCompatActivity {

ImageView mImageView;
EditText mTitleMeeting;
EditText mDateMeeting;
EditText mLocationMeeting;
EditText mSujet_meeting;
EditText mParticipant;
Button mButtonSave;

MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting_activity);

       final int color = rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));

        mImageView = findViewById(R.id.color_meeting);
        mImageView.setBackgroundColor(color);


         int mBackground = mImageView.getDrawingCacheBackgroundColor();
        mDateMeeting = findViewById(R.id.date_meeting);
        mLocationMeeting = findViewById(R.id.location_meeting);
        mSujet_meeting = findViewById(R.id.sujet_meeting);
        mParticipant = findViewById(R.id.participant);


        mButtonSave = findViewById(R.id.save_meeting);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("kk");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateInString = mDateMeeting.getText().toString();
                Date date = null;

                try {
                    date = formatter.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String particpants = mParticipant.getText().toString();

                String [] participantsList = particpants.split("\n");
                List<String> participantListMeeting = new ArrayList<String>();

                for (String participant: participantsList) {
                    System.out.println(participant);
                    participantListMeeting.add(participant);
                }

                Meeting meeting = new Meeting(color,date,mLocationMeeting.getText().toString(),mSujet_meeting.getText().toString(),participantListMeeting);
                mApiService.createMeeting(meeting);
            }
        });
    }
}

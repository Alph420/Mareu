package com.openclassrooms.mareu;

import org.junit.Before;
import org.junit.Test;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Meeting;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import com.openclassrooms.mareu.service.DummyMeetingGenerator;
import com.openclassrooms.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.junit.Assert.*;
import static android.graphics.Color.rgb;


public class MeetingServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getMeetingApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meeting = service.getMeeting();
        List<Meeting> expectedMeeting = DummyMeetingGenerator.DUMMY_MEETING;
        assertThat(meeting, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedMeeting.toArray())));
    }


    @Test
    public void createNeighbourWithSuccess(){
        List<String> list = new ArrayList<String>(Arrays.asList("1@1.1", "2@2.2"));
        Meeting test = new Meeting(rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)), 14, "Test", "test",
                list);
        service.createMeeting(test);
        assertTrue(service.getMeeting().contains(test));
    }


    @Test
    public void deleteNeighbourWithSuccess() {
        Meeting meetingToDelete = service.getMeeting().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeeting().contains(meetingToDelete));
    }
}
package com.openclassrooms.mareu;

import android.view.View;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareu.ui.ListMeetingActivity;
import com.openclassrooms.mareu.utils.DeleteViewAction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListTest {
    // This is fixed
    private static int ITEMS_COUNT = 4;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule(ListMeetingActivity.class);


    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.meeting_list))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myAddingMeetingButton_workingSucess(){
        onView(ViewMatchers.withId(R.id.add_meeting))
                .perform(click());

        onView(ViewMatchers.withId(R.id.color_meeting))
                .check(matches(isDisplayed()));
    }


    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        onView(ViewMatchers.withId(R.id.meeting_list)).check(matches(hasChildCount(ITEMS_COUNT)));

        onView(ViewMatchers.withId(R.id.meeting_list))
                .perform(actionOnItemAtPosition(1, new DeleteViewAction()));

        onView(ViewMatchers.withId(R.id.meeting_list)).check(matches(hasChildCount(ITEMS_COUNT-1)));
    }
}

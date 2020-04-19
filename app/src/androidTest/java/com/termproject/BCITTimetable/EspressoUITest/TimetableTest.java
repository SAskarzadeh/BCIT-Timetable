package com.termproject.BCITTimetable.EspressoUITest;

import android.os.SystemClock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.termproject.BCITTimetable.Main.MainActivity;
import com.termproject.BCITTimetable.R;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasEntry;

@RunWith(AndroidJUnit4.class)
public class TimetableTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestFilter() {


        //School of Energy - Instructor
        SystemClock.sleep(1000);
        onView(ViewMatchers.withId(R.id.btnEnergy)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnInstructor)).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.searchFilter)).perform(typeText("Romalo"));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        SystemClock.sleep(3000);
        onView(withId(R.id.webViewer)).perform(swipeUp());
        SystemClock.sleep(500);
        onView(withId(R.id.webViewer)).perform(swipeDown());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnQR)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.Return_btn)).perform(click());
        SystemClock.sleep(1000);

        //School of Computing & Academic - Instructor
        onView(withId(R.id.btnComputingAndAcademic)).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.btnInstructor)).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.searchFilter)).perform(typeText("Tejinder"));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        SystemClock.sleep(3000);
        onView(withId(R.id.webViewer)).perform(swipeUp());
        SystemClock.sleep(500);
        onView(withId(R.id.webViewer)).perform(swipeDown());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnQR)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.Return_btn)).perform(click());
        SystemClock.sleep(1000);

        //School of Energy - Programs
        onView(withId(R.id.btnEnergy)).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.btnPrograms)).perform(click());
        SystemClock.sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.listView_2)).atPosition(2).perform(click());
        SystemClock.sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.listView_2)).atPosition(5).perform(click());
        SystemClock.sleep(3000);
        onView(withId(R.id.webViewer)).perform(swipeUp());
        SystemClock.sleep(500);
        onView(withId(R.id.webViewer)).perform(swipeDown());
        SystemClock.sleep(500);
        onView(withId(R.id.btnQR)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.Return_btn)).perform(click());
        SystemClock.sleep(1000);

        //School of Computing & Academic- Programs
        onView(withId(R.id.btnComputingAndAcademic)).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.btnPrograms)).perform(click());
        SystemClock.sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.listView_2)).atPosition(1).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.listView_2)).perform(swipeUp());
        SystemClock.sleep(1000);
        onView(withId(R.id.listView_2)).perform(swipeDown());
        SystemClock.sleep(1000);
        onView(withId(R.id.searchFilter)).perform(typeText("COMP 1 E"));
        onData(anything()).inAdapterView(withId(R.id.listView_2)).atPosition(0).perform(click());
        SystemClock.sleep(3000);
        onView(withId(R.id.webViewer)).perform(swipeUp());
        SystemClock.sleep(500);
        onView(withId(R.id.webViewer)).perform(swipeDown());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnQR)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.Share_btn)).perform(click());
        SystemClock.sleep(100);
        //end of UNIT Test
    }


}

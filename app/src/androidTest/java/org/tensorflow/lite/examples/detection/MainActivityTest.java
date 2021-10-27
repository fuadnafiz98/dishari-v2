package org.tensorflow.lite.examples.detection;

import android.os.SystemClock;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class  MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testActivity() {
        View view = mainActivity.findViewById(R.id.startBtn);
        assertNotNull(view);
        Espresso.onView(withId(R.id.startBtn)).perform(click());
        Intents.init();

        // go to help page
        Espresso.onView(withId(R.id.help_card)).perform(click());

//        Espresso.onView(withId(R.id.floatingObjDetect)).perform(click());
//        SystemClock.sleep(2000);
//        Espresso.onView(withId(R.id.floatingObjDetect)).perform(click());
//
//        Espresso.onView(withId(R.id.distMeasure)).perform(click());
//        SystemClock.sleep(2000);
//        Espresso.onView(withId(R.id.distMeasure)).perform(click());

        Espresso.pressBack();

        // go to object detection page
        Espresso.onView(withId(R.id.objectDetection_card)).perform(click());
        SystemClock.sleep(2000);
        Espresso.pressBack();

        // go to notify obstacle page
        Espresso.onView(withId(R.id.obstacle_card)).perform(click());
        SystemClock.sleep(2000);
        Espresso.pressBack();

        // go to distance measurement page
        Espresso.onView(withId(R.id.distance_card)).perform(click());
        SystemClock.sleep(2000);

        Espresso.onView(withId(R.id.getData)).perform(click());
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.getData)).perform(click());
        SystemClock.sleep(2000);

        Espresso.pressBack();

        SystemClock.sleep(1000);
        Espresso.pressBack();
        SystemClock.sleep(1000);

        Intents.release();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}

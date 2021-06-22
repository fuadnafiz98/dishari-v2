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

public class HelpActivityTest {
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

        Espresso.onView(withId(R.id.obj_dict_media_play)).perform(click());
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.obj_dict_media_play)).perform(click());

        Espresso.onView(withId(R.id.dist_measure_media_play)).perform(click());
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.dist_measure_media_play)).perform(click());

        Espresso.onView(withId(R.id.notify_obs_media_play)).perform(click());
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.notify_obs_media_play)).perform(click());

        Espresso.pressBack();

        Intents.release();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}

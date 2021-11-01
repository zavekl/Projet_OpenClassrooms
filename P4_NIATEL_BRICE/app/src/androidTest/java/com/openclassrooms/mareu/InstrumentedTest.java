package com.openclassrooms.mareu;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TimePicker;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareu.utils.RecyclerViewMatcher;
import com.openclassrooms.ui.AddMeetingActivity;
import com.openclassrooms.ui.ListMeetingActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingActivity.class);

    @Test
    public void useButtonCreateMeeting() {
        Intents.init();
        //Click on FAB
        ViewInteraction floatingActionButton = onView(allOf(withId(R.id.fab), childAtPosition(allOf(withId(R.id.main_content2), childAtPosition(withId(R.id.main_content), 0)),
                1), isDisplayed()));
        floatingActionButton.perform(click());

        intended(hasComponent(AddMeetingActivity.class.getName()));
    }

    @Test
    public void createMeetingWithFail() {
        //Click on FAB
        ViewInteraction floatingActionButton = onView(allOf(withId(R.id.fab), childAtPosition(allOf(withId(R.id.main_content2), childAtPosition(withId(R.id.main_content), 0)),
                1), isDisplayed()));
        floatingActionButton.perform(click());

        //Click on create meeting
        ViewInteraction materialButton = onView(allOf(withId(R.id.buttonAdd), withText("CREATE"), childAtPosition(childAtPosition(withClassName(is("androidx.cardview.widget.CardView")),
                0), 6)));
        materialButton.perform(scrollTo(), click());

        //Click on back button
        ViewInteraction materialButton2 = onView(allOf(withId(R.id.backButton), withText("BACK"), childAtPosition(childAtPosition(withClassName(is("androidx.cardview.widget.CardView")),
                0), 7)));
        materialButton2.perform(scrollTo(), click());
    }

    @Test
    public void createThreeMeetingWithSuccess() {
        //Create meetings
        createThreeMeetings();

        //Test
        onView(withId(R.id.list_meeting)).check(withItemCount(3));

        onView(withRecyclerView(R.id.list_meeting).atPositionOnView(0, R.id.item_list_meeting1)).check(matches(withText("Meeting room 12 - 18:20 - Prepare project 6")));
        onView(withRecyclerView(R.id.list_meeting).atPosition(1)).check(matches(hasDescendant(withText("Meeting room 10 - 09:40 - Prepare project 48"))));
        onView(withRecyclerView(R.id.list_meeting).atPosition(2)).check(matches(hasDescendant(withText("Meeting room 22 - 15:10 - Prepare project 7"))));

        onView(withRecyclerView(R.id.list_meeting).atPosition(0)).check(matches(hasDescendant(withText("azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com"))));
        onView(withRecyclerView(R.id.list_meeting).atPosition(1)).check(matches(hasDescendant(withText("azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com"))));
        onView(withRecyclerView(R.id.list_meeting).atPosition(2)).check(matches(hasDescendant(withText("azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com"))));
    }

    @Test
    public void sortMeetingByHourWithSuccess() {
        //Create meetings
        createThreeMeetings();

        //Sort by hour
        ViewInteraction overflowMenuButton = onView(allOf(withContentDescription("More options"), childAtPosition(childAtPosition(withId(R.id.toolbar), 2), 0),
                isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView5 = onView(allOf(withId(R.id.title), withText("Sort by hour"), childAtPosition(childAtPosition(withId(R.id.content), 0), 0),
                isDisplayed()));
        materialTextView5.perform(click());

        //Test
        onView(withRecyclerView(R.id.list_meeting).atPositionOnView(0, R.id.item_list_meeting1)).check(matches(withText("Meeting room 10 - 09:40 - Prepare project 48")));
        onView(withRecyclerView(R.id.list_meeting).atPosition(1)).check(matches(hasDescendant(withText("Meeting room 22 - 15:10 - Prepare project 7"))));
        onView(withRecyclerView(R.id.list_meeting).atPosition(2)).check(matches(hasDescendant(withText("Meeting room 12 - 18:20 - Prepare project 6"))));
    }

    @Test
    public void sortMeetingByLocationWithSuccess() {
        //Create meetings
        createThreeMeetings();

        //Sort by hour
        ViewInteraction overflowMenuButton = onView(allOf(withContentDescription("More options"), childAtPosition(childAtPosition(withId(R.id.toolbar), 2), 0),
                isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView5 = onView(allOf(withId(R.id.title), withText("Sort by hour"), childAtPosition(childAtPosition(withId(R.id.content), 0), 0),
                isDisplayed()));
        materialTextView5.perform(click());

        //Test
        onView(withRecyclerView(R.id.list_meeting).atPositionOnView(0, R.id.item_list_meeting1)).check(matches(withText("Meeting room 10 - 09:40 - Prepare project 48")));
        onView(withRecyclerView(R.id.list_meeting).atPosition(1)).check(matches(hasDescendant(withText("Meeting room 22 - 15:10 - Prepare project 7"))));
        onView(withRecyclerView(R.id.list_meeting).atPosition(2)).check(matches(hasDescendant(withText("Meeting room 12 - 18:20 - Prepare project 6"))));
    }

    @Test
    public void createThreeMeetings() {
        //Create 3 meetings
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), childAtPosition(allOf(withId(R.id.main_content2), childAtPosition(withId(R.id.main_content), 0)), 1), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction materialTextView = onView(allOf(withId(R.id.textinput2), childAtPosition(childAtPosition(withClassName(is("androidx.cardview.widget.CardView")), 0),
                2)));
        materialTextView.perform(scrollTo(), click());

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(18, 20));

        ViewInteraction materialButton = onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")),
                0), 3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction mLocation = onView(withId(R.id.filled_exposed_dropdown)).perform(click());
        mLocation.perform(replaceText("Meeting room 12"), closeSoftKeyboard());

        ViewInteraction mSubject = onView(withId(R.id.textinput3)).perform(click());
        mSubject.perform(replaceText("Prepare project 6"), closeSoftKeyboard());

        ViewInteraction mPeople = onView(withId(R.id.textinput4)).perform(click());
        mPeople.perform(replaceText("azerty1@gmail.com"), closeSoftKeyboard());
        ViewInteraction mAdd = onView(withId(R.id.addPeopleToAList)).perform(click());

        mPeople.perform(click());
        mPeople.perform(replaceText("azerty2@gmail.com"), closeSoftKeyboard());
        mAdd.perform(click());

        mPeople.perform(click());
        mPeople.perform(replaceText("azerty3@gmail.com"), closeSoftKeyboard());
        mAdd.perform(click());

        onView(withId(R.id.buttonAdd)).perform(click());

        onView(withId(R.id.list_meeting)).check(matches(hasMinimumChildCount(1)));


        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.fab), childAtPosition(allOf(withId(R.id.main_content2), childAtPosition(withId(R.id.main_content), 0)), 1), isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction materialTextView2 = onView(allOf(withId(R.id.textinput2), childAtPosition(childAtPosition(withClassName(is("androidx.cardview.widget.CardView")), 0),
                2)));
        materialTextView.perform(scrollTo(), click());

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(9, 40));

        ViewInteraction materialButton2 = onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")),
                0), 3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction mLocation2 = onView(withId(R.id.filled_exposed_dropdown)).perform(click());
        mLocation2.perform(replaceText("Meeting room 10"), closeSoftKeyboard());

        ViewInteraction mSubject2 = onView(withId(R.id.textinput3)).perform(click());
        mSubject2.perform(replaceText("Prepare project 48"), closeSoftKeyboard());

        ViewInteraction mPeople2 = onView(withId(R.id.textinput4)).perform(click());
        mPeople2.perform(replaceText("azerty1@gmail.com"), closeSoftKeyboard());
        ViewInteraction mAdd2 = onView(withId(R.id.addPeopleToAList)).perform(click());

        mPeople2.perform(click());
        mPeople2.perform(replaceText("azerty2@gmail.com"), closeSoftKeyboard());
        mAdd2.perform(click());

        mPeople2.perform(click());
        mPeople2.perform(replaceText("azerty3@gmail.com"), closeSoftKeyboard());
        mAdd2.perform(click());

        onView(withId(R.id.buttonAdd)).perform(click());

        onView(withId(R.id.list_meeting))
                .check(matches(hasMinimumChildCount(1)));


        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.fab), childAtPosition(allOf(withId(R.id.main_content2), childAtPosition(withId(R.id.main_content), 0)), 1), isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction materialTextView3 = onView(allOf(withId(R.id.textinput2), childAtPosition(childAtPosition(withClassName(is("androidx.cardview.widget.CardView")), 0),
                2)));
        materialTextView3.perform(scrollTo(), click());

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15, 10));

        ViewInteraction materialButton3 = onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")),
                0), 3)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction mLocation3 = onView(withId(R.id.filled_exposed_dropdown)).perform(click());
        mLocation3.perform(replaceText("Meeting room 22"), closeSoftKeyboard());

        ViewInteraction mSubject3 = onView(withId(R.id.textinput3)).perform(click());
        mSubject3.perform(replaceText("Prepare project 7"), closeSoftKeyboard());

        ViewInteraction mPeople3 = onView(withId(R.id.textinput4)).perform(click());
        mPeople3.perform(replaceText("azerty1@gmail.com"), closeSoftKeyboard());
        ViewInteraction mAdd3 = onView(withId(R.id.addPeopleToAList)).perform(click());

        mPeople3.perform(click());
        mPeople3.perform(replaceText("azerty2@gmail.com"), closeSoftKeyboard());
        mAdd3.perform(click());

        mPeople3.perform(click());
        mPeople3.perform(replaceText("azerty3@gmail.com"), closeSoftKeyboard());
        mAdd3.perform(click());

        onView(withId(R.id.buttonAdd)).perform(click());
    }

    /**
     * Matchers
     */
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }


}

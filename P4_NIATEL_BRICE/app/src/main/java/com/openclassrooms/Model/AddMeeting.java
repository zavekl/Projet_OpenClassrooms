package com.openclassrooms.Model;

import android.content.Context;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.mareu.R;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AddMeeting {

    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private List<String> listOfPeopleToAddMeeting;

    private Context context;

    private AutoCompleteTextView mAutoCompleteTextView1;
    private TextView mTextInputEdit2;
    private TextInputEditText mTextInputEdit3;
    private TextInputEditText mTextInputEdit4;


    public AddMeeting(AutoCompleteTextView mAutoCompleteTextView1, TextView mTextInputEdit2, TextInputEditText mTextInputEdit3,
                      TextInputEditText mTextInputEdit4, Context context) {
        this.mAutoCompleteTextView1 = mAutoCompleteTextView1;
        this.mTextInputEdit2 = mTextInputEdit2;
        this.mTextInputEdit3 = mTextInputEdit3;
        this.mTextInputEdit4 = mTextInputEdit4;
        this.context = context;
        listOfPeopleToAddMeeting = new ArrayList<>();

    }

    public boolean validateAllParameters() {
        return !validateLocation() | !validateHour() | !validateSubject() | !validateEmail();
    }


    private boolean validateLocation() {
        text1 = Objects.requireNonNull(mAutoCompleteTextView1.getText()).toString().trim();
        if (text1.isEmpty()) {
            mAutoCompleteTextView1.setError(context.getString(R.string.field_empty));
            return false;
        } else {
            mAutoCompleteTextView1.setError(null);
            return true;
        }
    }

    private boolean validateHour() {
        text2 = Objects.requireNonNull(mTextInputEdit2.getText()).toString().trim();
        if (text2.isEmpty()) {
            mTextInputEdit2.setError(context.getString(R.string.field_empty));
            return false;
        } else {
            mTextInputEdit2.setError(null);
            return true;
        }
    }

    private boolean validateSubject() {
        text3 = Objects.requireNonNull(mTextInputEdit3.getText()).toString();

        if (text3.isEmpty()) {
            mTextInputEdit3.setError(context.getString(R.string.field_empty));
            return false;
        } else if (text1.length() > 40) {
            mTextInputEdit3.setError(context.getString(R.string.subject_long));
            return false;
        } else {
            mTextInputEdit3.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        if (listOfPeopleToAddMeeting.size() == 0) {
            mTextInputEdit4.setError(context.getString(R.string.field_empty));
            return false;
        } else {
            mTextInputEdit4.setError(null);
            return true;
        }
    }


    public void addPeopleToTheMeeting() {
        String onePerson = Objects.requireNonNull(mTextInputEdit4.getText()).toString();
        listOfPeopleToAddMeeting.add(Objects.requireNonNull(onePerson));
        text4 = StringUtils.join(listOfPeopleToAddMeeting, "; ");
    }

    public String getLocation() {
        return text1;
    }

    public String getHour() {
        return text2;
    }

    public String getSubject() {
        return text3;
    }

    public String getPeople() {
        return text4;
    }

    public List getItemList() {
        return Arrays.asList("Meeting room 01", "Meeting room 02", "Meeting room 03", "Meeting room 04", "Meeting room 05", "Meeting room 06",
                "Meeting room 07", "Meeting room 08", "Meeting room 09", "Meeting room 10", "Meeting room 11", "Meeting room 12", "Meeting room 13",
                "Meeting room 14", "Meeting room 15", "Meeting room 16", "Meeting room 17", "Meeting room 18", "Meeting room 19", "Meeting room 20",
                "Meeting room 21", "Meeting room 22", "Meeting room 23", "Meeting room 24", "Meeting room 25", "Meeting room 26", "Meeting room 27",
                "Meeting room 28", "Meeting room 29", "Meeting room 30");
    }
}

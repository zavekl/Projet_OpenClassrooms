package com.openclassrooms.ui;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.Model.AddMeeting;
import com.openclassrooms.Model.Meeting;
import com.openclassrooms.Model.ToastCustomization;
import com.openclassrooms.Service.MeetingApiService;
import com.openclassrooms.di.DI;
import com.openclassrooms.events.ListenerAddMeeting;
import com.openclassrooms.mareu.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <NIATEL BRICE> on <04/11/2019>.
 */
public class AddMeetingActivity extends AppCompatActivity implements ListenerAddMeeting {

    private MeetingApiService mApiService;
    private AddMeeting addMeetingService;
    private Context context = this;


    // UI Components
    @BindView(R.id.filled_exposed_dropdown)
    AutoCompleteTextView mAutoCompleteTextView;
    @BindView(R.id.textinput2)
    TextView mTextInputEdit2;
    @BindView(R.id.textinput3)
    TextInputEditText mTextInputEdit3;
    @BindView(R.id.textinput4)
    TextInputEditText mTextInputEdit4;
    @BindView(R.id.buttonAdd)
    Button mCreateMeetingButton;
    @BindView(R.id.backButton)
    Button mBackButton;
    @BindView(R.id.addPeopleToAList)
    Button mAddPeopleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting);
        mApiService = DI.getMeetingApiService();
        ButterKnife.bind(this);
        addMeetingService = new AddMeeting(mAutoCompleteTextView, mTextInputEdit2, mTextInputEdit3, mTextInputEdit4, context);

        onClickButtonAdd();
        onClickButtonBack();
        onClickEditTextHour();
        onClickButtonAddPeople();

        setAdapterAutoCompleteViewLocation();
    }

    @Override
    public void onClickButtonAdd() {
        mCreateMeetingButton.setOnClickListener(view -> {
            if (!addMeetingService.validateAllParameters()) {
                Log.i("textpeople", "onClickButtonAdd: ");
                getNewMeeting();
                onBackPressed();
            }
        });
    }

    @Override
    public void onClickEditTextHour() {
        mTextInputEdit2.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (timePicker, hourOfDay, minutes) ->
                    mTextInputEdit2.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minutes)), 0, 0, true);
            timePickerDialog.show();
        });
    }

    @Override
    public void onClickButtonBack() {
        mBackButton.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onClickButtonAddPeople() {

        mAddPeopleButton.setOnClickListener(view -> {
            String peopleToAdd = Objects.requireNonNull(mTextInputEdit4.getText()).toString();

            if (peopleToAdd.isEmpty()) {
                ToastCustomization toast = new ToastCustomization(this);
                toast.toastEmptyEditText();
            } else {
                addMeetingService.addPeopleToTheMeeting();
                mTextInputEdit4.setText("");
            }
        });
    }

    private void getNewMeeting() {
        Meeting meeting = mApiService.createNewMeeting(addMeetingService.getLocation(), addMeetingService.getHour(), addMeetingService.getSubject(), addMeetingService.getPeople());
        Intent intent = getIntent();
        intent.putExtra("key", meeting);
        setResult(RESULT_OK, intent);
    }

    public void setAdapterAutoCompleteViewLocation() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, addMeetingService.getItemList());
        mAutoCompleteTextView.setAdapter(adapter);
    }
}
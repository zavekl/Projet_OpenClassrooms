package com.openclassrooms.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.Model.Meeting;
import com.openclassrooms.Model.ToastCustomization;
import com.openclassrooms.Service.MeetingApiService;
import com.openclassrooms.di.DI;
import com.openclassrooms.events.ListenerMeeting;
import com.openclassrooms.mareu.R;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingActivity extends AppCompatActivity implements ListenerMeeting {

    //Api service
    private MeetingApiService mApiService;

    // Request code for onActivityResult
    public static final int REQUEST_CODE = 1;

    // UI Components
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFAB;
    @BindView(R.id.list_meeting)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNewInstanceApiService();
        setContentView(R.layout.activity_list_meeting);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(mToolbar);

        onFabClickListener();
        onScrollRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        List<Meeting> mMeetings = mApiService.getListMeetings();
        MyMeetingRecyclerViewAdapter adapter = new MyMeetingRecyclerViewAdapter(mMeetings, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        changeIcon();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.sort_by_hour) {
            ToastCustomization toast = new ToastCustomization(this);
            toast.toastHour();
            if (!mApiService.sortMeetingByHour()) {
                toast.toastEmptyList();
            }
            initList();
            return true;
        }
        if (id == R.id.sort_by_location) {
            ToastCustomization toast = new ToastCustomization(this);
            toast.toastLocation();
            if (!mApiService.sortMeetingByLocation()) {
                toast.toastEmptyList();
            }
            initList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(Meeting meeting) {
        ToastCustomization toast = new ToastCustomization(this, meeting.getSubject());
        toast.toastItem();
    }

    @Override
    public void onDeleteClickListener(Meeting meeting) {
        mApiService.deleteMeeting(meeting);
        initList();
    }

    @Override
    public void onFabClickListener() {
        Intent intent = new Intent(this, AddMeetingActivity.class);
        mFAB.setOnClickListener(view -> startActivityForResult(intent, REQUEST_CODE));
    }

    @Override
    public void onScrollRecyclerView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0)
                    mFAB.hide();
                else if (dy < 0)
                    mFAB.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Meeting meeting = (Meeting) Objects.requireNonNull(Objects.requireNonNull(data).getExtras()).getSerializable("key");
            mApiService.addMeeting(meeting);
        }
    }

    private void changeIcon() {
        //Change the icon of menu
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_filter_list_black_24dp);
        DrawableCompat.setTint(Objects.requireNonNull(drawable), ContextCompat.getColor(this, R.color.colorIconographyTypographyWhite));
        mToolbar.setOverflowIcon(drawable);
    }
}
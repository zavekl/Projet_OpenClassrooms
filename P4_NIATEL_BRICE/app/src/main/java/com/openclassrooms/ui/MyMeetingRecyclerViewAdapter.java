package com.openclassrooms.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.Model.Meeting;
import com.openclassrooms.events.ListenerMeeting;
import com.openclassrooms.mareu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mNeighbours;
    private final ListenerMeeting onItemListner;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items, ListenerMeeting onItemListner) {
        mNeighbours = items;
        this.onItemListner = onItemListner;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting meeting = mNeighbours.get(position);

        String text1 = meeting.getLocation() + " - " + meeting.getHour() + " - " + meeting.getSubject();
        String text2 = meeting.getListPeople();

        holder.mMeetingText1.setText(text1);
        holder.mMeetingText2.setText(text2);

        holder.mDeleteButton.setOnClickListener(v -> onItemListner.onDeleteClickListener(meeting));

        holder.mItemList.setOnClickListener(v -> onItemListner.onItemClickListener(meeting));
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_meeting1)
        public TextView mMeetingText1;
        @BindView(R.id.item_list_meeting2)
        public TextView mMeetingText2;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        @BindView(R.id.item_list)
        public View mItemList;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
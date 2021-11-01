package com.openclassrooms.events;

import com.openclassrooms.Model.Meeting;

/**
 * Created by <BRICE> on <09/10/2019>.
 */
public interface ListenerMeeting {

    void onItemClickListener(Meeting meeting);

    void onDeleteClickListener(Meeting meeting);

    void onFabClickListener();

    void onScrollRecyclerView();
}

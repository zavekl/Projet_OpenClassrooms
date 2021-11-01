package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.ListenerFragment;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;


public class NeighbourFavoriteFragment extends Fragment implements ListenerFragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private final Boolean mFavoriteFragment = true;
    private boolean isVisible;
    private boolean isStarted;


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFavoriteFragment}
     */

    public static NeighbourFavoriteFragment newInstance() {
        NeighbourFavoriteFragment fragment = new NeighbourFavoriteFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }


    /**
     * For the attribute isStarted
     */

    @Override
    public void onStart() {
        super.onStart();
        isStarted = true;
        if (isVisible)
            initList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mApiService.loadFavoriteNeighbours();
        initList();
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {

        mNeighbours = mApiService.getFavoriteNeighbours();
        Log.i("Favoris", "initList: " + mApiService.getFavoriteNeighbours().size());
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, this, mFavoriteFragment));
    }

    @Override
    public void OnItemClickListener(Neighbour item) {
        Intent descriptionActivity = new Intent(getActivity(), DescriptionNeighbourActivity.class);
        descriptionActivity.putExtra("id", item.getId());
        startActivity(descriptionActivity);
    }

    @Override
    public void OnDeleteClickListener(Neighbour neighbour) {

    }

    @Override
    public void OnDeleteFavoriteClickListener(Neighbour neighbour) {
        neighbour.setFavorite(false);
        mApiService.deleteFavoriteNeighbours(neighbour);
        initList();
    }

    /**
     * To load information for the view
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisible && isStarted) {
            initList();
        }
    }
}

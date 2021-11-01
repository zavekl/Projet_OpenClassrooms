package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.model.ToastCustomization;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescriptionNeighbourActivity extends AppCompatActivity {

    /**
     * UI Component
     */

    @BindView(R.id.name1TextView)
    TextView name1TextView;
    @BindView(R.id.name2TextView)
    TextView name2TextView;
    @BindView(R.id.avatarUrlImageView)
    ImageView avatarUrlimageView;
    @BindView(R.id.locationTextView)
    TextView locationTextView;
    @BindView(R.id.phoneTextView)
    TextView phoneTextView;
    @BindView(R.id.fbTextView)
    TextView fbTextView;
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.backImageView)
    Button backImageView;
    @BindView(R.id.favoriteImageView)
    FloatingActionButton favoriteImageView;

    private NeighbourApiService mApiService;
    private Neighbour neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        loadElementLayout();
        backButtonListener();
        favoriteButtonListener();

    }

    /**
     * Charge all UI component with their text / image
     */

    private void loadElementLayout() {
        Intent intent = getIntent();

        if (intent != null) {

            if (intent.hasExtra("id")) {
                //Log.i("loadElementLayout", "Id intent: " + intent.getStringExtra("id"));
                int id = intent.getIntExtra("id", -1);

                if (id != -1) {
                    neighbour = mApiService.getNeighbourWithId(id);

                    // Name
                    Log.i("loadElementLayout", "Id list:" + neighbour.getName());
                    name1TextView.setText(neighbour.getName());
                    name2TextView.setText(neighbour.getName());

                    //Image
                    Log.i("loadElementLayout", "loadElementLayout: " + neighbour.getAvatarUrl());
                    Picasso.get().load(neighbour.getAvatarUrl()).into(avatarUrlimageView);

                    //Location
                    Log.i("loadElementLayout", "loadElementLayout: " + neighbour.getAddress());
                    locationTextView.setText(neighbour.getAddress());

                    //Phone
                    Log.i("loadElementLayout", "loadElementLayout: " + neighbour.getPhone());
                    phoneTextView.setText(neighbour.getPhone());

                    //fb URL
                    Log.i("loadElementLayout", "loadElementLayout: " + neighbour.getFacebookProfile());
                    fbTextView.setText(neighbour.getFacebookProfile());

                    //Description
                    Log.i("loadElementLayout", "loadElementLayout: " + neighbour.getDescription());
                    descriptionTextView.setText(neighbour.getDescription());
                }
            }
        }
    }

    /**
     * To back to the list of neighbour
     */
    private void backButtonListener() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void favoriteButtonListener() {
        favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mApiService.getFavoriteNeighbours().size() == 0) {
                    mApiService.addFavoriteNeighbour(neighbour);
                    ToastCustomization toast = new ToastCustomization(DescriptionNeighbourActivity.this, neighbour);
                    toast.toastCustom2();
                } else {
                    for (Neighbour itemNeighbour : new ArrayList<>(mApiService.getFavoriteNeighbours())) { //Créer une liste copy pour éviter l'erreur de modification de la liste lu.

                        if (neighbour.getId().equals(itemNeighbour.getId())) {
                            ToastCustomization toast = new ToastCustomization(DescriptionNeighbourActivity.this, neighbour);
                            toast.toastCustom1();
                            break;
                        } else {

                            if (!neighbour.getFavorite()) {
                                ToastCustomization toast = new ToastCustomization(DescriptionNeighbourActivity.this, neighbour);
                                toast.toastCustom2();
                                mApiService.addFavoriteNeighbour(neighbour);
                            }
                        }
                    }
                }
            }
        });
    }


}
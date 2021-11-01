package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by <BRICE> on <09/10/2019>.
 */
public interface ListenerFragment {
    void OnItemClickListener(Neighbour neighbour);

    void OnDeleteClickListener(Neighbour neighbour);

    void OnDeleteFavoriteClickListener(Neighbour neighbour);
}

package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {
    List<Neighbour> getListNeighbours();

    Neighbour getNeighbourWithId(int id);

    ArrayList<Neighbour> getFavoriteNeighbours();

    void deleteNeighbour(Neighbour neighbour);

    void addFavoriteNeighbour(Neighbour neighbour);

    void deleteFavoriteNeighbours(Neighbour neighbour);

    void loadFavoriteNeighbours();
}

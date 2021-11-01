package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.generateNeighbours;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> listNeighbours = generateNeighbours();
    private ArrayList<Neighbour> ListFavoriteNeighbours = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getListNeighbours() {
        return listNeighbours;
    }

    @Override
    public ArrayList<Neighbour> getFavoriteNeighbours() {
        return ListFavoriteNeighbours;
    }

    @Override
    public Neighbour getNeighbourWithId(int id) {
        for (Neighbour neighbour : listNeighbours) {
            if (neighbour.getId() == id) {
                return neighbour;
            }
        }
        return null;
    }

    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        listNeighbours.remove(neighbour);
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        ListFavoriteNeighbours.add(neighbour);
        neighbour.setFavorite(true);

    }

    @Override
    public void loadFavoriteNeighbours() {
        if (ListFavoriteNeighbours.size() == 0) {
            for (Neighbour neighbour : listNeighbours) {
                if (neighbour.getFavorite()) {
                    ListFavoriteNeighbours.add(neighbour);
                }
            }
        }
    }

    @Override
    public void deleteFavoriteNeighbours(Neighbour neighbour) {
        ListFavoriteNeighbours.remove(neighbour);
    }

}

package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getListNeighbours();
        List<Neighbour> expectedNeighbours = service.getListNeighbours();
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedNeighbours.toArray())));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getListNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getListNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoriteNeighbourWithSuccess() {

        service.loadFavoriteNeighbours();
        int favoriteList = service.getFavoriteNeighbours().size();
        Assert.assertEquals(4, favoriteList);

    }

    @Test
    public void loadNeighbourWithSuccess() {
        service.loadFavoriteNeighbours();
        int favoriteList = service.getFavoriteNeighbours().size();
        Assert.assertEquals(4, favoriteList);

    }

    @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        service.loadFavoriteNeighbours();
        Neighbour neighbourToDelete = service.getFavoriteNeighbours().get(0);
        service.deleteFavoriteNeighbours(neighbourToDelete);
        assertFalse(service.getFavoriteNeighbours().contains(neighbourToDelete));

    }

    @Test
    public void getNeighbourWithIdWithSuccess() {
        Neighbour neighbourToGet = service.getListNeighbours().get(0);
        service.getNeighbourWithId(1);
        assertEquals(neighbourToGet, service.getNeighbourWithId(1));

    }

    @Test
    public void setANeighbourFavoriteWithSuccess() {
        Neighbour neighbourToSetFavorite = service.getListNeighbours().get(11);
        service.addFavoriteNeighbour(neighbourToSetFavorite);
        assertTrue(service.getFavoriteNeighbours().contains(neighbourToSetFavorite));
        neighbourToSetFavorite.setFavorite(false);
    }
}

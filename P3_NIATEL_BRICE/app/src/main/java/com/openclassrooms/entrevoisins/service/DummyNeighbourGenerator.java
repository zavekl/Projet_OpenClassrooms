package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    private static List DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "adr1", "01", "Caroline FB",
                    "J'aime les fleurs aaaaaaa aaaaaaaaaa aaaaaaaaaaa aaaaaaaaaa aaaaaaaaa aaaaaaaaa aaaaaaaaa aaaaaaaaaaaa aaaaaaaaaaa", true),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "adr2", "02", "Jack FB",
                    "J'aime la peinture", true),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "adr3", "03", "Chloé FB",
                    "J'aime les jeux vidéos", true),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "adr4", "04", "Vincent FB",
                    "J'aime le sport", false),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", "adr5", "05", "Elodie FB",
                    "J'aime la nature", true),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "adr6", "06", "Sylvain FB",
                    "J'aime le dessin", false),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", "adr7", "07", "Laetitia FB",
                    "J'aime le cinéma", false),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "adr8", "08", "Dan FB",
                    "J'aime l'espace", false),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "adr9", "09", "Joseph FB",
                    "J'aime le shopping", false),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "adr10", "10", "Emma FB",
                    "J'aime la cuisine", false),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "adr11", "11", "Patrick FB",
                    "J'aime les couchés de soleil", false),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "adr12", "12", "Ludovic FB",
                    "J'aime la lecture", false)
    );

    public static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}

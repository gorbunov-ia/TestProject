package ru.gorbunov.test.algorithms.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.gorbunov.test.algorithms.other.TrackShuffler.Track;

class TrackShufflerTest {

    private List<Track> trackList;
    private TrackShuffler shuffler;

    @BeforeEach
    void setUp() {
        trackList = Arrays.asList(new Track("#1"), new Track("#2"), new Track("#3"), new Track("#4"));
        shuffler = new TrackShuffler(trackList);
    }

    @Test
    void iterator() {
        final Set<Track> trackSet = new HashSet<>(trackList);

        final Iterator<Track> iterator = shuffler.iterator();

        List<Track> result = new ArrayList<>(trackList.size());
        while (iterator.hasNext()) {
            final Track next = iterator.next();
            result.add(next);
            Assertions.assertTrue(trackSet.remove(next));
        }
        Assertions.assertTrue(trackSet.isEmpty());
        Assertions.assertNotEquals(result, trackList);
    }
}
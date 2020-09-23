package ru.gorbunov.test.algorithms.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

class TrackShuffler implements Iterable<TrackShuffler.Track> {

    private final List<Track> tracks;

    TrackShuffler(Collection<? extends Track> tracks) {
        this.tracks = new ArrayList<>(tracks);
    }

    @Override
    public Iterator<Track> iterator() {
        return new TrackIterator(tracks);
    }


    static class TrackIterator implements Iterator<Track> {

        private final List<Track> tracks;
        private int currentIndex = 0;

        TrackIterator(List<Track> tracks) {
            this.tracks = tracks;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < tracks.size();
        }

        @Override
        public Track next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final int index = nextIndex();
            Track track = tracks.get(index);

            Collections.swap(tracks, currentIndex, index);
            currentIndex++;
            return track;
        }

        private int nextIndex() {
//            return currentIndex + (int)(Math.random() * (tracks.size() - currentIndex));
            return ThreadLocalRandom.current().nextInt(currentIndex, tracks.size());
        }
    }

    static class Track {
        private final String title;

        Track(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Track{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }
}

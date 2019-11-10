package ru.gorbunov.test.algorithms.other;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Дан список со страницами, которые
 * просматривали за день. Вывести топ N таких страниц.
 */
public class TopViewPage {

    public static void main(String[] args) {
        new TopViewPage().process();
    }

    private void process() {
        final List<Page> pages = Arrays.asList(new Page(1), new Page(2), new Page(3), new Page(2));
        System.out.println(getTopPages(pages, 2));
    }

    private List<Page> getTopPages(Collection<Page> pages, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (pages.isEmpty()) {
            return Collections.emptyList();
        }
        final Map<Page, Long> countMap = pages.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return countMap.entrySet().stream()
                .sorted(createComparator())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Comparator<Map.Entry<Page, Long>> createComparator() {
        return Comparator.comparing((Function<Map.Entry<Page, Long>, Long>) Map.Entry::getValue).reversed();
    }

    private static class Page {
        private long id;

        private Page(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Page page = (Page) o;
            return id == page.id;
        }

        @Override
        public int hashCode() {

            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Page{" +
                    "id=" + id +
                    '}';
        }
    }



}


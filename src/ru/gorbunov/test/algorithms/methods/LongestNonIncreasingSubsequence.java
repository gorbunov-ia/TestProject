package ru.gorbunov.test.algorithms.methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Дано целое число 1≤n≤10^5 и массив A[1…n], содержащий неотрицательные целые числа, не превосходящие 10^9.
 * Найдите наибольшую невозрастающую подпоследовательность в A. В первой строке выведите её длину k,
 * во второй — её индексы 1≤i_1<i_2<…<i_k≤n (таким образом, A[i_1]≥A[i_2]≥…≥A[i_n]
 */
public class LongestNonIncreasingSubsequence {
    public static void main(String[] args) throws IOException {
        int[] source = getSource();
//        printResult(n2(source));
        printResult(nLogN(source));
    }

    private static void printResult(List<Integer> indexes) {
        System.out.println(indexes.size());
        final StringBuilder builder = new StringBuilder(indexes.size());
        builder.append(indexes.get(indexes.size() - 1));
        for (int i = indexes.size() - 2; i >= 0; i--) {
            builder.append(" ").append(indexes.get(i));
        }
        System.out.println(builder.toString());
    }

    private static int[] getSource() throws IOException {
//        return new int[]{5, 3, 4, 4, 2};
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        return Stream.of(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static List<Integer> n2(int[] source) {
        int[] sequences = new int[source.length];

        for (int i = 0; i < sequences.length; i++) {
            sequences[i] = 1;
            for (int j = 0; j < i; j++) {
                // Non increasing
                if (source[j] >= source[i] && sequences[j] + 1 > sequences[i]) {
                    sequences[i] = sequences[j] + 1;
                }
            }
        }

        int maxSequence = 0;
        int index = 0;
        for (int i = 0; i < sequences.length; i++) {
            if (sequences[i] > maxSequence) {
                index = i;
                maxSequence = sequences[i];
            }
        }

        List<Integer> indexes = new ArrayList<>(maxSequence);
        indexes.add(index + 1);

        int prevValue = maxSequence;
        int prevIndex = index;
        for (int i = index - 1; i >= 0; i--) {
            if (sequences[i] == prevValue - 1 && source[i] >= source[prevIndex]) {
                prevValue--;
                prevIndex = i;
//                builder.append(" ").append(i + 1);
                indexes.add(i + 1);
            }
        }
        return indexes;
    }

    // Binary search
    private static int getCeilIndex(int arr[], int T[], int l, int r, int key) {

        while (r - l > 1) {

            int m = l + (r - l) / 2;
            if (arr[T[m]] < key)
                r = m;
            else
                l = m;
        }

        return r;
    }

    private static List<Integer> nLogN(int arr[]) {

        // Add boundary case, when array n is zero
        // Depend on smart pointers
        final int n = arr.length;
        int tailIndices[] = new int[n];

        // Initialized with 0
        Arrays.fill(tailIndices, 0);

        int prevIndices[] = new int[n];

        // initialized with -1
        Arrays.fill(prevIndices, -1);

        // it will always point to empty
        // location
        int len = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[tailIndices[0]])

                // new smallest value
                tailIndices[0] = i;

            else if (arr[i] <= arr[tailIndices[len - 1]]) {

                // arr[i] wants to extend subsequence
                prevIndices[i] = tailIndices[len - 1];
                tailIndices[len++] = i;
            } else {

                // arr[i] wants to be a potential
                // condidate of future subsequence
                // It will replace ceil value in
                // tailIndices
                int pos = getCeilIndex(arr, tailIndices, 0, len, arr[i]);

                prevIndices[i] = tailIndices[pos - 1];
                tailIndices[pos] = i;
            }
        }

        final ArrayList<Integer> indexes = new ArrayList<>(len);
        for (int i = tailIndices[len - 1]; i >= 0; i = prevIndices[i]) {
            indexes.add(i + 1);
        }
        return indexes;
    }

}

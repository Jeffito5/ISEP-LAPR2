package app.domain.model.algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class InsertionSort {

    /**
     * Sorts an unsorted List with the insertion sort algorithm,
     * according to a given comparator.
     *
     * @param unsortedList Unsorted list.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    public <T> void insertionSort(List<T> unsortedList, Comparator<T> comparator)
    {
        int n = unsortedList.size();

        for (int i = 1; i < n; i++)
        {
            T key = unsortedList.get(i);
            int j = i - 1;

            /* Moves the elements of the unsorted list prior to the iteration
            index, whose attribute in analysis is greater than the one in 'key'
            to one position ahead of their current position.
            */
            while (j >= 0 && comparator.compare(key, unsortedList.get(j)) < 0)
            {
                unsortedList.set(j + 1, unsortedList.get(j));
                j--;
            }
            unsortedList.set(j + 1, key);
        }
    }
}

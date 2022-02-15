package app.domain.model.algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class QuickSort {

    /**
     * Sorts an unsorted List with the Quick Sort algorithm,
     * according to a given comparator.
     *
     * @param unsortedList Unsorted list.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    public <T> void sort(List<T> unsortedList, Comparator<T> comparator)
    {
        if (unsortedList.size() < 2)
        {
            return;
        }
        sort(unsortedList, 0, unsortedList.size() - 1, comparator);
    }

    /**
     * Quick Sort algorithm. First, the pivotIndex is obtained.
     * Then, the sub arrays to the left and right of the pivot are recursively sorted.
     *
     * @param unsortedList Unsorted list.
     * @param start beginning index of sub array.
     * @param end ending index of sub array.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    private <T> void sort(List<T> unsortedList, int start, int end, Comparator<T> comparator)
    {
        if (start < end)
        {
            // Pivot element to partition the array
            int pivotIndex = partition(unsortedList, start, end, comparator);

            // Recursive calls of the Quick Sort Algorithm for the left and right sub arrays.
            sort(unsortedList, start, pivotIndex - 1, comparator);
            sort(unsortedList, pivotIndex + 1, end, comparator);
        }
    }

    /**
     * Methos that picks a pivot, and finds its right location.
     *
     * @param unsortedList Unsorted list.
     * @param start beginning index of sub array.
     * @param end ending index of sub array.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    private <T> int partition(List<T> unsortedList, int start, int end, Comparator<T> comparator)
    {
        // Pivot is set to the last element of the list
        T pivot = unsortedList.get(end);

        // Index of the smaller element
        int i = start;

        for (int j = start; j < end; j++)
        {
            if (comparator.compare(unsortedList.get(j), pivot) <= 0)
            {
                // Swapping elements
                T swapAux = unsortedList.get(i);
                unsortedList.set(i, unsortedList.get(j));
                unsortedList.set(j, swapAux);

                i++;
            }
        }

        // Placing the pivot in the correct position
        T swapAux = unsortedList.get(i);
        unsortedList.set(i, unsortedList.get(end));
        unsortedList.set(end, swapAux);

        return i;
    }
}

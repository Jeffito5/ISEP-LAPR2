package app.domain.model.algorithms.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class MergeSort {

    /**
     * Sorts an unsorted List with the Merge Sort algorithm,
     * according to a given comparator.
     *
     * @param unsortedList Unsorted list.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    public <T> void sort(List<T> unsortedList, Comparator<T> comparator)
    {
        // List size
        int n = unsortedList.size();

        // Base condition
        if (n < 2)
        {
            return;
        }

        // Finding the middle point
        int middle = n / 2;

        // Temporary Arrays
        ArrayList<T> left = new ArrayList<>(middle);
        ArrayList<T> right = new ArrayList<>(n - middle);

        // Filling temporary arrays
        for(int i = 0; i < middle; i++)
        {
            left.add(unsortedList.get(i));
        }

        for(int i = middle; i < n; i++)
        {
            right.add(unsortedList.get(i));
        }

        // Sorting halves
        sort(left, comparator);
        sort(right, comparator);

        // Merging the sorted halves
        merge(unsortedList, left, right, comparator);
    }

    /**
     * Merges the left and right sub arrays.
     *
     * @param mergeList list to be merged.
     * @param left left sub array.
     * @param right right sub array.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    private <T> void merge(List<T> mergeList, List<T> left, List<T> right, Comparator<T> comparator)
    {
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedListIndex = 0;

        // Compares halves to merge sub arrays into array
        while(leftIndex < left.size() && rightIndex < right.size())
        {
            if(comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0)
            {
                mergeList.set(mergedListIndex, left.get(leftIndex));
                leftIndex++;
            }
            else
            {
                mergeList.set(mergedListIndex, right.get(rightIndex));
                rightIndex++;
            }
            mergedListIndex++;
        }

        // Fills array with remaining elements of left half, if they exist
        while(leftIndex < left.size())
        {
            mergeList.set(mergedListIndex++, left.get(leftIndex++));
        }

        // Fills array with remaining elements of right half, if they exist
        while(rightIndex < right.size())
        {
            mergeList.set(mergedListIndex++, right.get(rightIndex++));
        }
    }
}

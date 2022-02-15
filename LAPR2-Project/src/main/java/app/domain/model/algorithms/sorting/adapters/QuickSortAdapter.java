package app.domain.model.algorithms.sorting.adapters;

import app.domain.model.algorithms.sorting.QuickSort;
import app.domain.model.algorithms.sorting.Sort;

import java.util.Comparator;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class QuickSortAdapter implements Sort {
    /**
     * Quick Sort algorithm.
     */
    private final QuickSort quickSort;

    /**
     * Constructor that instantiates the algorithm.
     */
    public QuickSortAdapter(){
        quickSort = new QuickSort();
    }

    /**
     * Sorts an unsorted List with the quick sort algorithm,
     * according to a given comparator.
     *
     * @param unsortedList Unsorted list.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    @Override
    public <T> void sort(List<T> unsortedList, Comparator<T> comparator) {
        quickSort.sort(unsortedList, comparator);
    }
}

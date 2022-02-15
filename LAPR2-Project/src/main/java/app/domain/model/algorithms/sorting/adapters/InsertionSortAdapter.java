package app.domain.model.algorithms.sorting.adapters;

import app.domain.model.algorithms.sorting.InsertionSort;
import app.domain.model.algorithms.sorting.Sort;

import java.util.Comparator;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class InsertionSortAdapter implements Sort {
    /**
     * Insertion Sort algorithm.
     */
    private final InsertionSort insertionSort;

    /**
     * Constructor that instantiates the algorithm.
     */
    public InsertionSortAdapter(){
        insertionSort = new InsertionSort();
    }

    /**
     * Sorts an unsorted List with the insertion sort algorithm,
     * according to a given comparator.
     *
     * @param unsortedList Unsorted list.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    @Override
    public <T> void sort(List<T> unsortedList, Comparator<T> comparator) {
        insertionSort.insertionSort(unsortedList, comparator);
    }
}

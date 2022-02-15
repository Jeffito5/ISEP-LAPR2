package app.domain.model.algorithms.sorting.adapters;

import app.domain.model.algorithms.sorting.MergeSort;
import app.domain.model.algorithms.sorting.Sort;

import java.util.Comparator;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class MergeSortAdapter implements Sort {
    /**
     * Merge Sort algorithm.
     */
    private final MergeSort mergeSort;

    /**
     * Constructor that instantiates the algorithm.
     */
    public MergeSortAdapter (){
        mergeSort = new MergeSort();
    }

    /**
     * Sorts an unsorted List with the merge sort algorithm,
     * according to a given comparator.
     *
     * @param unsortedList Unsorted list.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    @Override
    public <T> void sort(List<T> unsortedList, Comparator<T> comparator) {
        mergeSort.sort(unsortedList, comparator);
    }
}

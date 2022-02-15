package app.domain.model.algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public interface Sort {
    /**
     * Sorts an unsorted List, according to a given comparator.
     *
     * @param unsortedList Unsorted list.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    <T> void sort(List<T> unsortedList, Comparator<T> comparator);
}

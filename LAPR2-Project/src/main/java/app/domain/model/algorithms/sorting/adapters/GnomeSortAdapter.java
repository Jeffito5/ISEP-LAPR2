package app.domain.model.algorithms.sorting.adapters;

import app.domain.model.algorithms.sorting.GnomeSort;
import app.domain.model.algorithms.sorting.Sort;

import java.util.Comparator;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class GnomeSortAdapter implements Sort {
    /**
     * Gnome Sort algorithm.
     */
    private final GnomeSort gnomeSort;

    /**
     * Constructor that instantiates the algorithm.
     */
    public GnomeSortAdapter()
    {
        gnomeSort = new GnomeSort();
    }

    /**
     * Sorts an unsorted List with the gnome sort algorithm,
     * according to a given comparator.
     *
     * @param unsortedList Unsorted list.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    @Override
    public <T> void sort(List<T> unsortedList, Comparator<T> comparator) {
        gnomeSort.gnomeSort(unsortedList, comparator);
    }
}

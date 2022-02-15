package app.domain.model.algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class GnomeSort {

    /**
     * Sorts an unsorted List with the gnome sort algorithm,
     * according to a given comparator.
     *
     * @param unsortedList Unsorted list.
     * @param comparator Comparator.
     * @param <T> Generic type.
     */
    public <T> void gnomeSort(List<T> unsortedList, Comparator<T> comparator)
    {
        int index = 0;
        int n = unsortedList.size();

        while (index < n)
        {
            if (index == 0)
            {
                index ++;
            }

            // Checks if the object at index - 1 is smaller or equals to the object at index
            if (comparator.compare(unsortedList.get( index - 1), unsortedList.get(index)) <= 0)
            {
                index++;
            }
            else
            {
                T temp = unsortedList.get(index);
                unsortedList.set(index, unsortedList.get(index - 1));
                unsortedList.set(index - 1, temp);
                index--;
            }
        }
    }
}

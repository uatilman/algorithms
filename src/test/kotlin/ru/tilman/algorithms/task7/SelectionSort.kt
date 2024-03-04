package ru.tilman.algorithms.task7

import ru.tilman.algorithms.task6.AbstractSort

class SelectionSort<T : Comparable<T>>(override val array: Array<T>) : AbstractSort<T>() {
    override fun sort() {
        for (j in array.size - 1 downTo 1 step 1)
            swap(findMax(0, j), j)
    }

    @Suppress("SameParameterValue")
    private fun findMax(fromIndex: Int, toIndex: Int): Int {
        var max = fromIndex
        for (i in fromIndex + 1..toIndex step 1)
            if (more(array[i], array[max])) max = i
        return max
    }

}
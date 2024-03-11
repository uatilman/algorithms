package ru.tilman.algorithms.task8

import ru.tilman.algorithms.task6.AbstractSort

class QSort<T : Comparable<T>>(override val array: Array<T>) : AbstractSort<T>() {
    private val n = array.size
    override fun sort() {
        qsort(0, n - 1)
    }

    private fun qsort(left: Int, right: Int) {
        if ((left >= right)) return;
        val m = split(left, right)
        qsort(left, m - 1)
        qsort(m + 1, right)
    }

    private fun split(left: Int, right: Int): Int {
        val pivot = array[right]
        var m = left - 1
        for (j in left..right) {
            if (moreEq(pivot, array[j])) { //если элемент меньше опорного, двигаем границу m и меняем элемент с первым (до сдвига) элементом части с большими элементами
                swap(++m, j)
            }
        }
        return m
    }


}
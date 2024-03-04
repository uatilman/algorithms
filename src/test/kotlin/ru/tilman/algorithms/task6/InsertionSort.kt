package ru.tilman.algorithms.task6

class InsertionSort<T>(override val array: Array<T>) : AbstractSort<T>()  where T : Comparable<T> {

    override fun sort() {
        for (j in 1 until array.size) {
            var i = j - 1
            while (i >= 0 && more(array[i], array[i + 1])) {
                swap(i, i + 1)
                i--
            }
        }
    }

}
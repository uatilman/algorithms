package ru.tilman.algorithms.task6

class InsertionShiftSort<T>(override val array: Array<T>) : AbstractSort<T>()  where T : Comparable<T> {

    override fun sort() {
        var i: Int
        for (j in 1 until array.size) {
            val current = array[j]
            assignment++

            i = j - 1
            while (i >= 0 && more(array[i], current)) {
                array[i + 1] = array[i]
                assignment++
                i--
            }
            array[i + 1] = current
            assignment++
        }
    }

}
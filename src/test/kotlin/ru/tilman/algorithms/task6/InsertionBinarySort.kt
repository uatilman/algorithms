package ru.tilman.algorithms.task6

class InsertionBinarySort<T>(override val array: Array<T>) : AbstractSort<T>()  where T : Comparable<T> {

    override fun sort() {
        var i: Int
        for (j in 1 until array.size) {
            val current = array[j]
            assignment++

            i = j - 1
            val insertionIndex = array.binarySearchRecursion(current, 0, j - 1, compare)
            while (i >= insertionIndex) {
                array[i + 1] = array[i]
                assignment++
                i--
            }
            array[i + 1] = current
            assignment++
        }
    }


}

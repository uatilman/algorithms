package ru.tilman.algorithms.task6

class ShellSort<T>(override val array: Array<T>) : AbstractSort<T>() where T : Comparable<T> {

    override fun sort() {

        var gap = array.size / 2
        while (gap > 0) {

            var j = gap
            while (j < array.size) {

                var i = j
                while (i >= gap && more(array[i - gap], array[i])) {
                    swap(i - gap, i)
                    i -= gap
                }
                j++

            }

            gap /= 2
        }

    }

}
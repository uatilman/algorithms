package ru.tilman.algorithms.task8

import ru.tilman.algorithms.task6.AbstractSort

class MSort<T : Comparable<T>>(override val array: Array<T>) : AbstractSort<T>() {
    private val n = array.size
    private val tmpArray = Array<Any>(n) {}
    override fun sort() {
        mSort(0, n - 1)
    }

    private fun mSort(left: Int, right: Int) {
        if (left >= right) return
        val m = (left + right) / 2
        mSort(left, m)
        mSort(m + 1, right)
        merge(left, m, right)
    }

    @Suppress("UNCHECKED_CAST")
    private fun merge(left: Int, m: Int, right: Int) {
//        val tmpArray = Array<Any>(right - left + 1) {}
        var a = left // указатель "первого" массива
        var b = m + 1 // указатель "второго" массива
        var t = 0 //указатель в tmpArray

        // пока не уткнулись в одну из границ если элемент больше, берем меньший
        while (a <= m && b <= right) tmpArray[t++] = if (more(array[a], array[b])) array[b++] else array[a++]

        //если в "первом" массиве остались элементы
        while (a <= m) tmpArray[t++] = array[a++]

        //если во "втором" массиве остались элементы
        while (b <= right) tmpArray[t++] = array[b++]

        //перезаписываем в исходный массив
        for (i in left..right) array[i] = tmpArray[i - left] as T


    }


}
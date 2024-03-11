package ru.tilman.algorithms.task6


interface Sort<T> where T : Comparable<T> {
    val array: Array<T>
    var assignment: Long
    val compare: CompareCounter
    var executeTime: Long

    fun sort()
    fun execute(): Sort<T>
    fun lastSortResult(): Sort<T>
    fun assert(expected: Array<T>): Boolean

}


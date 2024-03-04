package ru.tilman.algorithms.task6


import java.text.DecimalFormat
import kotlin.system.measureTimeMillis

abstract class AbstractSort<T> : Sort<T> where T : Comparable<T>{
    override var assignment: Long = 0
    override var compare = CompareCounter()
    override var executeTime: Long = 0
    private val decimalFormat = DecimalFormat("###,###")

    protected fun swap(firstIndex: Int, secondIndex: Int) {
        validateSwapCompareIndex(firstIndex, secondIndex)

        val temp = array[firstIndex]
        array[firstIndex] = array[secondIndex]
        array[secondIndex] = temp
        assignment += 3
    }

    protected fun more(first: T, second: T): Boolean {
        compare++
        return first > second
    }

    protected abstract fun sort()

    override fun execute(): AbstractSort<T> {
        executeTime = measureTimeMillis {
            sort()
        }
        return this
    }

    override fun lastSortResult(): Sort<T> {
        println("Размер: '${decimalFormat.format(array.size)}', сравнений: '${decimalFormat.format(compare)}', присвоений: '${decimalFormat.format(assignment)}', время исполнения: '${decimalFormat.format(executeTime)} мс'.")

        assignment = 0
        compare.reset()

        return this
    }

    override fun assert(expected: Array<T>): Boolean {
        return expected.contentEquals(array)
    }

    private fun validateSwapCompareIndex(firstIndex: Int, secondIndex: Int) {
        if (array.size < 2)
            throw IllegalArgumentException("Array must be grater or then '2', but size is '${array.size}' ")
        if (firstIndex < 0 || firstIndex > array.size - 1 || secondIndex < 0 || secondIndex > array.size - 1)
            throw IllegalArgumentException("Illegal index values: array size: '${array.size}', firstIndex: '$firstIndex', secondIndex: '$secondIndex'")
    }
}
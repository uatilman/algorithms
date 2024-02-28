package ru.tilman.algorithms.task3

import java.math.BigInteger

class Matrix(private val values: Array<Array<BigInteger>>) {

    operator fun get(i: Int, j: Int): BigInteger {
        return values[i][j]
    }

    operator fun times(other: Matrix): Matrix {
        val row1 = values.size
        val col1 = values[0].size
        val col2 = other.values[0].size
        val result: Array<Array<BigInteger>> = Array(row1) { Array(col2) { BigInteger.ZERO } }

        for (i in 0 until row1) {
            for (j in 0 until col2) {
                for (k in 0 until col1) {
                    result[i][j] += values[i][k] * other.values[k][j]
                }
            }
        }
        return Matrix(result)
    }

    fun printLong() {
        println("=".repeat(values.size * 2))
        for (i in values.indices) {
            println(values[i].joinToString(" "))
        }
        println("=".repeat(values.size * 2))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (!values.contentDeepEquals(other.values)) return false

        return true
    }

    override fun hashCode(): Int {
        return values.contentDeepHashCode()
    }


}
// Тестирование если матрица целых чисел
//class MatrixTest {
//    @Test
//    fun timesMatrix() {
//        val matrix1 = Matrix(arrayOf(arrayOf(1, 2), arrayOf(3, 4)))
//        val matrix2 = Matrix(arrayOf(arrayOf(5, 6), arrayOf(7, 8)))
//        val expected = Matrix(arrayOf(arrayOf(19, 22), arrayOf(43, 50)))
//        val actual = matrix1 * matrix2
//        assertEquals(expected, actual)
//    }
//}
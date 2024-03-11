package ru.tilman.algorithms.task6

import kotlinx.coroutines.debug.junit5.CoroutinesTimeout
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import ru.tilman.algorithms.task7.HeapSort
import ru.tilman.algorithms.task7.SelectionSort
import ru.tilman.algorithms.task8.MSort
import ru.tilman.algorithms.task8.QSort
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.exists

class SortTest {

    private val randomPath = "src/test/resources/6.SORT/0.random/"
    private val digitsPath = "src/test/resources/6.SORT/1.digits/"
    private val sortedPath = "src/test/resources/6.SORT/2.sorted/"
    private val reversePath = "src/test/resources/6.SORT/3.revers/"

    /**
     * На первой строчке указан размер массива, на второй строчке через пробел перечислены элементы массива.
     * В файл результата записать числа из отсортированного массива в одну строчку через пробел.
     * */
    @Test
    @CoroutinesTimeout(testTimeoutMs = 60_000 * 10)
    fun bubleSortTestAuto() {

        var counter = 0

        while (true) {
            readData("${randomPath}test.${counter}")
                ?.let { executeTest(BubleSort(it.second), it.first, "RANDOM") } ?: return

            readData("${digitsPath}test.${counter}")
                ?.let { executeTest(BubleSort(it.second), it.first, "DIGITS") } ?: return

            readData("${sortedPath}test.${counter}")
                ?.let { executeTest(BubleSort(it.second), it.first, "SORTED") } ?: return

            readData("${reversePath}test.${counter}")
                ?.let { executeTest(BubleSort(it.second), it.first, "REVERSED") } ?: return

            println()
            counter++
        }

    }


    @Test
    @CoroutinesTimeout(testTimeoutMs = 60_000 * 10)
    fun insertionSortTestAuto() {
        var counter = 0

        while (true) {
            readData("${randomPath}test.${counter}")
                ?.let { executeTest(InsertionSort(it.second), it.first, "RANDOM") } ?: return

            readData("${digitsPath}test.${counter}")
                ?.let { executeTest(InsertionSort(it.second), it.first, "DIGITS") } ?: return

            readData("${sortedPath}test.${counter}")
                ?.let { executeTest(InsertionSort(it.second), it.first, "SORTED") } ?: return

            readData("${reversePath}test.${counter}")
                ?.let { executeTest(InsertionSort(it.second), it.first, "REVERSED") } ?: return

            println()
            counter++
        }
    }



    @Test
    @CoroutinesTimeout(testTimeoutMs = 60_000 * 10)
    fun insertionShiftSortTestAuto() {
        var counter = 0

        while (true) {
            readData("${randomPath}test.${counter}")
                ?.let { executeTest(InsertionShiftSort(it.second), it.first, "RANDOM") } ?: return

            readData("${digitsPath}test.${counter}")
                ?.let { executeTest(InsertionShiftSort(it.second), it.first, "DIGITS") } ?: return

            readData("${sortedPath}test.${counter}")
                ?.let { executeTest(InsertionShiftSort(it.second), it.first, "SORTED") } ?: return

            readData("${reversePath}test.${counter}")
                ?.let { executeTest(InsertionShiftSort(it.second), it.first, "REVERSED") } ?: return

            println()
            counter++
        }
    }

    @Test
    @CoroutinesTimeout(testTimeoutMs = 60_000 * 10)
    fun insertionBinarySortTestAuto() {
        var counter = 0

        while (true) {
            readData("${randomPath}test.${counter}")
                ?.let { executeTest(InsertionBinarySort(it.second), it.first, "RANDOM") } ?: return

            readData("${digitsPath}test.${counter}")
                ?.let { executeTest(InsertionBinarySort(it.second), it.first, "DIGITS") } ?: return

            readData("${sortedPath}test.${counter}")
                ?.let { executeTest(InsertionBinarySort(it.second), it.first, "SORTED") } ?: return

            readData("${reversePath}test.${counter}")
                ?.let { executeTest(InsertionBinarySort(it.second), it.first, "REVERSED") } ?: return

            println()
            counter++
        }
    }


    @Test
    @CoroutinesTimeout(testTimeoutMs = 60_000 * 10)
    fun shellSortTestAuto() {
        var counter = 0

        while (true) {
            readData("${randomPath}test.${counter}")
                ?.let { executeTest(ShellSort(it.second), it.first, "RANDOM") } ?: return

            readData("${digitsPath}test.${counter}")
                ?.let { executeTest(ShellSort(it.second), it.first, "DIGITS") } ?: return

            readData("${sortedPath}test.${counter}")
                ?.let { executeTest(ShellSort(it.second), it.first, "SORTED") } ?: return

            readData("${reversePath}test.${counter}")
                ?.let { executeTest(ShellSort(it.second), it.first, "REVERSED") } ?: return

            println()
            counter++
        }
    }

    @Test
    @CoroutinesTimeout(testTimeoutMs = 60_000 * 10)
    fun selectionSortTestAuto() {
        var counter = 0

        while (true) {
            readData("${randomPath}test.${counter}")
                ?.let { executeTest(SelectionSort(it.second), it.first, "RANDOM") } ?: return

            readData("${digitsPath}test.${counter}")
                ?.let { executeTest(SelectionSort(it.second), it.first, "DIGITS") } ?: return

            readData("${sortedPath}test.${counter}")
                ?.let { executeTest(SelectionSort(it.second), it.first, "SORTED") } ?: return

            readData("${reversePath}test.${counter}")
                ?.let { executeTest(SelectionSort(it.second), it.first, "REVERSED") } ?: return

            println()
            counter++
        }
    }

    @Test
    @CoroutinesTimeout(testTimeoutMs = 60_000 * 10)
    fun heapSortTestAuto() {
        var counter = 0

        while (true) {
            readData("${randomPath}test.${counter}")
                ?.let { executeTest(HeapSort(it.second), it.first, "RANDOM") } ?: return

            readData("${digitsPath}test.${counter}")
                ?.let { executeTest(HeapSort(it.second), it.first, "DIGITS") } ?: return

            readData("${sortedPath}test.${counter}")
                ?.let { executeTest(HeapSort(it.second), it.first, "SORTED") } ?: return

            readData("${reversePath}test.${counter}")
                ?.let { executeTest(HeapSort(it.second), it.first, "REVERSED") } ?: return

            println()
            counter++
        }
    }

    @Test
    @CoroutinesTimeout(testTimeoutMs = 60_000 * 10)
    fun mergeSortTestAuto() {
        var counter = 0

        while (true) {
            readData("${randomPath}test.${counter}")
                ?.let { executeTest(MSort(it.second), it.first, "RANDOM") } ?: return

            readData("${digitsPath}test.${counter}")
                ?.let { executeTest(MSort(it.second), it.first, "DIGITS") } ?: return

            readData("${sortedPath}test.${counter}")
                ?.let { executeTest(MSort(it.second), it.first, "SORTED") } ?: return

            readData("${reversePath}test.${counter}")
                ?.let { executeTest(MSort(it.second), it.first, "REVERSED") } ?: return

            println()
            counter++
        }
    }

    @Test
    @CoroutinesTimeout(testTimeoutMs = 60_000 * 10)
    fun qSortTestAuto() {
        var counter = 0

        while (true) {
            readData("${randomPath}test.${counter}")
                ?.let { executeTest(QSort(it.second), it.first, "RANDOM") } ?: return

            readData("${digitsPath}test.${counter}")
                ?.let { executeTest(QSort(it.second), it.first, "DIGITS") } ?: return

            readData("${sortedPath}test.${counter}")
                ?.let { executeTest(QSort(it.second), it.first, "SORTED") } ?: return

            readData("${reversePath}test.${counter}")
                ?.let { executeTest(QSort(it.second), it.first, "REVERSED") } ?: return

            println()
            counter++
        }
    }

    private fun readData(path: String): Pair<Array<Int>, Array<Int>>? {
        val inFile = Path("${path}.in")
        val outFile = Path("${path}.out")

        if (!inFile.exists() || !outFile.exists()) return null

        val rawArray = Files.readAllLines(inFile)[1].split(" ").map { it.toInt() }.toTypedArray()
        val expected = Files.readAllLines(outFile).first().split(" ").map { it.toInt() }.toTypedArray()

        return expected to rawArray
    }

    private fun executeTest(sort: Sort<Int>, expectSort: Array<Int>, type: String) {
        sort.execute()
        assertArrayEquals(expectSort, sort.array)
        print("$type. ")
        sort.lastSortResult()

    }
}
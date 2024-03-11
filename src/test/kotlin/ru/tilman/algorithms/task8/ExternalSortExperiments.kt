package ru.tilman.algorithms.task8

import org.junit.jupiter.api.Test
import ru.tilman.algorithms.task6.setRandom
import ru.tilman.algorithms.task7.HeapSort
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.bufferedReader
import kotlin.io.path.writeLines
import kotlin.io.path.writeText


class ExternalSortExperiments(
    private val path: String,
    private val n: Int,
    private val bound: Int,
    private val t: Int,
) {
    private lateinit var pathList: MutableList<Path>

    init {
        val dir = Path(path)
        if (Files.exists(dir)) {
            Files.list(dir).forEach {
                Files.delete(it)
            }
        }
        Files.createDirectories(Path(path))
        repeat(t) { count ->
            val file = Path("${path}/${count}_size_${n}_interval_${t}_${System.currentTimeMillis()}.in")
            Files.createFile(file)
            file.writeLines(setRandom(n, bound + 1).asSequence().map { it.toString() })
        }
        updatePathList()
    }


    fun sort() {
        pathList.forEach {
            val array = loadArrayFromFile(it)
            val heapSort = HeapSort(array)
            heapSort.sort()
            rewriteArrayFile(it, heapSort.array.toIntArray())
        }


        while (pathList.size > 2) {
            val pathListTmp = pathList.toTypedArray()
            for (i in 0..pathListTmp.size - 3 step 2) {
                merge(pathListTmp[i], pathListTmp[i + 1])
            }
        }

        merge(pathList[0], pathList[1])

    }

    private fun updatePathList(): MutableList<Path> {
        pathList = Files.list(Path(path)).toList()
        return pathList
    }

    private fun merge(pathA: Path, pathB: Path) {
        val outPath = Path("${path}/${System.currentTimeMillis()}.out")
        val bufferWriter = Files.newBufferedWriter(outPath)


        pathA.bufferedReader().use { aBufferReader ->
            val aIterator = aBufferReader.lineSequence().iterator()
            pathB.bufferedReader().use { bBufferReader ->
                val bIterator = bBufferReader.lineSequence().iterator()
                var isAIterate = true
                var isBIterate = true
                var a = aIterator.next().toInt()
                var b = bIterator.next().toInt()
                while (aIterator.hasNext() && bIterator.hasNext()) {
                    if (a <= b) {
                        bufferWriter.write(a.toString() + "\n")
                        isAIterate = true
                        isBIterate = false
                    } else {
                        bufferWriter.write(b.toString() + "\n")
                        isAIterate = false
                        isBIterate = true
                    }

                    if (isAIterate) {
                        if (aIterator.hasNext())
                            aIterator.next().parseIntOrNull()?.let { a = it }
                    }

                    if (isBIterate) {
                        while (bIterator.hasNext())
                            bIterator.next().parseIntOrNull()?.let { b = it }
                    }

                }

                while (aIterator.hasNext()) {
                    aIterator.next().parseIntOrNull()?.let { bufferWriter.write(it.toString() + "\n") }
                }
                while (bIterator.hasNext()) {
                    bIterator.next().parseIntOrNull()?.let { bufferWriter.write(it.toString() + "\n") }
                }
            }

        }
        bufferWriter.flush()
        bufferWriter.close()
        pathList = pathList.filter { it != pathA && it != pathB }.toMutableList()
        pathList.add(outPath)
        Files.delete(pathA)
        Files.delete(pathB)
    }

    private fun rewriteArrayFile(path: Path, array: IntArray) {
        path.writeText("")
        path.writeLines(array.asSequence().map { it.toString() })
    }

    private fun loadArrayFromFile(path: Path): Array<Int> {
        println("load array from $path")
        return Files.readAllLines(path).filter { it.isNotBlank() }.map { it.toInt() }.toTypedArray()
    }

}

fun String.parseIntOrNull() = runCatching { this.toInt() }.getOrNull()

class ExternalSortExperimentsTest {
    @Test
    fun sort() {
        val path = "src/test/resources/task8/fileGenerator"
        val es1 = ExternalSortExperiments(path, 30, 100, 18)
        es1.sort()
    }
}
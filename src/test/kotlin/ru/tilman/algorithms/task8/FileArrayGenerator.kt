package ru.tilman.algorithms.task8

import org.junit.jupiter.api.Test
import ru.tilman.algorithms.task6.setRandom
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.writeLines

class FileArrayGenerator(
    private val rootPath: String,
) {

    init {
        Files.createDirectories(Path(rootPath))
    }

    /**
     * +1 байт. Написать функцию (N, T) для генерации текстового файла из N строчек, на каждой строке записано случайное число от 1 до T.
     * @param n количество строк в файле
     * @param t диапазон чисел
     * */
    fun generate(n: Int, t: Int): Path {
        val file = Path("${rootPath}/size_${n}_interval_${t}_${System.currentTimeMillis()}.in")
        Files.createFile(file)
        file.writeLines(setRandom(n, t + 1).asSequence().map { it.toString() })
        return file
    }
}

class FileArrayGeneratorTest {
    @Test
    fun testGenerate() {
        val generator = FileArrayGenerator("src/test/resources/task8/fileGenerator")
        generator.generate(20, 10)
    }
}
package ru.tilman.algorithms.task4

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import ru.tilman.algorithms.task4.model.ArrayListWrapped
import ru.tilman.algorithms.task4.model.FactorArray
import ru.tilman.algorithms.task4.model.SingleArray
import java.util.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class ArraysTest {

    private val infoList = listOf(
        Info(SingleArray::class.java),
        Info(FactorArray::class.java),
        Info(ArrayListWrapped::class.java)
    )


    @AfterAll
    internal fun printInfo() {
        println(infoList.joinToString("\n") { it.toString() })
    }

    @Test
    fun add() {
        val singleArray: IArray<Date> = SingleArray()
        val factorArray: IArray<Date> =
            FactorArray()
        val arrayListWrapped: IArray<Date> = ArrayListWrapped()

        val longTime = 100_000

        countExecuteTime { repeat(longTime) { singleArray.add(Date()) } }
            .run { infoList[0].addTime = this }
        countExecuteTime { repeat(longTime) { factorArray.add(Date()) } }
            .run { infoList[1].addTime = this }
        countExecuteTime { repeat(longTime) { arrayListWrapped.add(Date()) } }
            .run { infoList[2].addTime = this }

    }

    @Test
    fun addIndex() {
        val singleArray: IArray<Date> = SingleArray()
        val factorArray: IArray<Date> =
            FactorArray()
        val arrayListWrapped: IArray<Date> = ArrayListWrapped()


        val longTime = 100_000

        countExecuteTime { repeat(longTime) { singleArray.add(Date(), it / 2) } }
            .run { infoList[0].addIndexTime = this }
        countExecuteTime { repeat(longTime) { factorArray.add(Date(), it / 2) } }
            .run { infoList[1].addIndexTime = this }
        countExecuteTime { repeat(longTime) { arrayListWrapped.add(Date(), it / 2) } }
            .run { infoList[2].addIndexTime = this }
    }

    @Test
    fun remove() {
        val singleArray: IArray<Date> = SingleArray()
        val factorArray: IArray<Date> =
            FactorArray()
        val arrayListWrapped: IArray<Date> = ArrayListWrapped()

        val longTime = 100_000

        repeat(longTime) { singleArray.add(Date()) }
        repeat(longTime) { factorArray.add(Date()) }
        repeat(longTime) { arrayListWrapped.add(Date()) }

        countExecuteTime { repeat(longTime) { singleArray.remove((longTime - it) / 2) } }
            .run { infoList[0].removeTime = this }
        countExecuteTime { repeat(longTime) { factorArray.remove((longTime - it) / 2) } }
            .run { infoList[1].removeTime = this }
        countExecuteTime { repeat(longTime) { arrayListWrapped.remove((longTime - it) / 2) } }
            .run { infoList[2].removeTime = this }
    }


    private fun countExecuteTime(task: () -> Unit): Long {
        val start = System.currentTimeMillis()
        task.invoke()
        return System.currentTimeMillis() - start
    }


}


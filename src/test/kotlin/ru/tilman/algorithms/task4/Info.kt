package ru.tilman.algorithms.task4

class Info(
    private val clazz: Class<*>,
    var addTime: Long = -1L,
    var addIndexTime: Long = -1L,
    var removeTime: Long = -1L,
) {

    override fun toString(): String {
        return "clazz=${clazz.simpleName}, addTime=$addTime, addIndexTime=$addIndexTime, removeTime=$removeTime"
    }
}
package ru.tilman.algorithms.task12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class CustomHashTableTest {
    @Test
    fun run() {
        val hashTable = CustomHashTable<String, String>(1 shl 2, 0.75f)
        hashTable["oneKey"] = "one"
        hashTable["twoKey"] = "two"
        hashTable["threeKey"] = "three"
        hashTable["fourKey"] = "four"
        hashTable["fiveKey"] = "five"
        hashTable["fiveKey"] = "fiveDuplicate"
        hashTable["sixKey"] = "six"
        hashTable["sevenKey"] = "seven"
        hashTable["eightKey"] = "eight"
        hashTable["nineKey"] = "nine"
        hashTable["10Key"] = "10"
        hashTable["11Key"] = "11"
        hashTable["12Key"] = "12"

        println(hashTable.toString())

        assertEquals("one", hashTable["oneKey"])
        assertEquals("two", hashTable["twoKey"])
        assertEquals("fiveDuplicate", hashTable["fiveKey"])
        assertEquals("six", hashTable["sixKey"])
        assertEquals("nine", hashTable["nineKey"])

        println("*******************TEST REMOVE*******************")
        var res = hashTable.remove("sixKey")
        println("after remove 'sixKey' removed value $res \n$hashTable")

        res = hashTable.remove("sixKey")
        assertNull(hashTable["sixKey"])
        println("after remove 'sixKey' removed value $res \n$hashTable")

        res = hashTable.remove("10Key")
        assertNull(hashTable["10Key"])
        println("after remove '10Key' removed value $res \n$hashTable")

        res = hashTable.remove("twoKey")
        assertNull(hashTable["twoKey"])
        println("after remove 'twoKey' removed value $res \n$hashTable")

        res = hashTable.remove("some key which not exist")
        println("after remove 'some key which not exist' removed value $res \n$hashTable")

        res = hashTable.remove("fiveKey")
        assertNull(hashTable["twoKey"])
        println("after remove 'fiveKey' removed value $res \n$hashTable")

    }
}
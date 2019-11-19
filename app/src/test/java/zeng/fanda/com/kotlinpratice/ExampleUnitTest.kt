package zeng.fanda.com.kotlinpratice

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TemporaryFolder

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

const val TEST_TIMEOUT = 100L

class ExampleUnitTest {

    @Rule
    val folder = TemporaryFolder()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testTrue() {
        Assert.assertTrue(true)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testMethod() {
        val createFile = folder.newFile("myfile.txt")
    }

}


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListsTest {

    @Test
    fun `last element of a list`() {
        assertEquals( "d", list.last( listOf( "a", "b", "c", "d")))
        assertEquals( null, list.last( emptyList<String>()))
    }

    @Test
    fun `last and penultimate elements of a list`() {
        assertEquals( "c" to "d", list.lastTwo ( listOf( "a", "b", "c", "d")))
        assertEquals( null, list.lastTwo ( listOf( "a")))
    }

    @Test
    fun `k'th element of a list`() {
        assertEquals( "c", list.at( 3, ( listOf( "a", "b", "c", "d", "e"))))
        assertEquals( null, list.at( 3, ( listOf( "a"))))
    }

    @Test
    fun `number of elements of a list`() {
        assertEquals( 3, list.length ( ( listOf( "a", "b", "c"))))
        assertEquals( 0, list.length( emptyList<String>()))
    }

    @Test
    fun `reverse a list`() {
        assertEquals( listOf( "c", "b", "a"), list.rev ( ( listOf( "a", "b", "c"))))
    }

    @Test
    fun `whether a list is a palindrome`() {
        assertTrue( list.isPalindrome ( ( listOf( "x", "a", "m", "a", "x" ))))
    }

    @Test
    fun `flatten a nested list structure`() {
        assertEquals(
            listOf("a", "b", "c", "d", "e"),
            list.flatten(
                listOf(
                    list.One("a"),
                    list.Many(
                        listOf(
                            list.One("b"),
                            list.Many( listOf( list.One("c"), list.One("d")) ),
                            list.One("e")
                        )
                    )
                )
            )
        )
    }

    @Test
    fun `eliminate consecutive duplicates`() {
        assertEquals(
            listOf("a", "b", "c", "a", "d", "e"),
            list.compress( listOf("a","a","a","a","b","c","c","a","a","d","e","e","e","e"))
        )
    }

    @Test
    fun `pack consecutive duplicates of list elements into sublists`() {
        assertEquals(
            listOf(
                listOf("a","a","a","a"),
                listOf("b"),
                listOf("c","c"),
                listOf("a","a"),
                listOf("d"),
                listOf("e","e","e","e")
            ),
            list.pack( listOf("a","a","a","a","b","c","c","a","a","d","e","e","e","e"))
        )
    }

    @Test
    fun `run-length encoding of a list`() {
        assertEquals(
                listOf(4 to "a", 1 to "b", 2 to "c", 2 to "a", 1 to "d", 4 to "e"),
                list.encode(listOf("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e"))
        )
    }
}
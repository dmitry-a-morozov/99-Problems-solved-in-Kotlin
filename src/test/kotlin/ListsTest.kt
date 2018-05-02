
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListsTest {

    @Test
    fun simpleTestCase() {
        assertEquals( "d",tryLast( listOf( "a", "b", "c", "d")))
        assertEquals( null, tryLast( emptyList<String>()))
    }
}
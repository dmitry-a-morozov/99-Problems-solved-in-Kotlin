package list

fun<T> last(xs: List<T>): T? =
    when (xs.size) {
        0 -> null
        1 -> xs[0]
        else -> last(xs.subList(1, xs.size))
    }

fun<T> lastTwo(xs: List<T>): Pair<T, T>? =
    when (xs.size){
        0, 1 -> null
        2 -> Pair(xs[0], xs[1])
        else -> lastTwo( xs.drop(1))
    }

fun<T> at(pos: Int, xs: List<T>): T? =
    when {
        xs.isEmpty() -> null
        pos == 1 -> xs[0]
        else -> at(pos - 1, xs.drop(1))
    }

fun<T> length(xs: List<T>) =
    xs.fold(0, { acc, _ -> acc + 1 })

fun<T> rev(xs: List<T>) =
    xs.foldRight(emptyList<T>(), { x, acc -> acc.plusElement(x)})

fun<T> isPalindrome(xs: List<T>) =
    xs == rev(xs)

sealed class Node<T> {
    data class One<T>(val x: T) : Node<T>()
    data class Many<T>(val xs: List<Node<T>>) : Node<T>()
}

fun<T> flatten(xs: List<Node<T>>): List<T> =
    xs.flatMap {
        when(it) {
            is Node.One -> listOf(it.x)
            is Node.Many -> flatten(it.xs)
        }
    }

fun<T> compress(xs: List<T>): List<T> =
    if (xs.isEmpty())
        xs
    else
        listOf(xs[0]) + list.compress( xs.drop(1).dropWhile { it == xs[0] })

fun<T> pack(xs: List<T>): List<List<T>> =
    if (xs.isEmpty())
        emptyList()
    else
        xs
        .drop(1)
        .fold(
            mutableListOf( mutableListOf(xs[0])),
            { acc, x ->
                if (acc.last().last() == x) {
                    acc.last().add(x)
                } else {
                    acc.add(mutableListOf(x))
                }
        acc
            }
        )

fun<T> encode(xs: List<T>): List<Pair<Int, T>> =
    pack( xs).map { it.size to it[0] }

sealed class Rle<T> {
    data class One<T>(val value: T) : Rle<T>()
    data class Many<T>(val count: Int, val value: T) : Rle<T>()
}

fun<T> modifiedEncode(xs: List<T>): List<Rle<T>> =
    pack( xs).map { if (it.size == 1) Rle.One(it[0]) else Rle.Many(it.size, it[0]) }

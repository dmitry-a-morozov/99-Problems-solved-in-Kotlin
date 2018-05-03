package list

fun<T> last(xs: List<T>): T? =
    when (xs.size) {
        0 -> null
        1 -> xs[0]
        else -> last(xs.subList(1, xs.size))
    }

fun<T> lastTwo(xs: List<T>): Pair<T, T>? =
    if (xs.size == 2)
        Pair(xs[0], xs[1])
    else if (xs.size > 2)
        lastTwo( xs.drop(1))
    else
        null


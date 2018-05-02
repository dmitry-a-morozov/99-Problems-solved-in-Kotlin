
fun<T> tryLast(xs: List<T>): T? =
    when (xs.size) {
        0 -> null
        1 -> xs[0]
        else -> tryLast(xs.subList(1, xs.size))
    }

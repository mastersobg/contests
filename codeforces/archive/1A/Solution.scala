object Solution {

    def main(args: Array[String]) {
        val Array(n, m, a) = readLine.split(" ").map(_.toInt)
        def f(x: Long) : Long = x / a + (if(x % a != 0) 1 else 0)
        println(f(n) * f(m))
    }
}

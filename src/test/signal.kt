package test.signal

import signal.*

fun run() {
    val a = A()
    val b = B()
    val c = C()
    connect(a, { a.onXChanged(0)}, b, {b.slot(0)})
//    connect(a, onXChanged, c, slot)
    a.x = 10
}


class A() {
    var x: Int = 0
        set(value) {
            if (value != x) {
                $x = value
                onXChanged(x)
            }
        }
    fun onXChanged(x: Int) {}
}

class B() {
    fun slot(x: Int) { println("class B, x = $x") }
}

class C() {
    fun slot(x: Int) { println("class C, x = $x") }
}
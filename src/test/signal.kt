package test.signal

import signal.*

fun run() {
    val a = A()
    val b = B()
    val c = C()
    SigSlot.connect(a.onXChanged, b.slot)
    SigSlot.connect(a.onXChanged, c.slot)
    a.x = 10
    SigSlot.disconnect(a.onXChanged, c.slot)
    a.x = 56
    SigSlot.disconnect(a.onXChanged, b.slot)
    a.x = 12
}

class A() {
    var x: Int = 0
        set(value) {
            if (value != x) {
                $x = value
                onXChanged(x)
            }
        }
    val onXChanged: (Any) -> Unit = { x -> SigSlot.action(onXChanged, x) }
}

class B() {
    val slot: (Any) -> Unit = { x -> println("slot class B, x = $x") }
}

class C() {
    val slot: (Any) -> Unit = { x -> println("slot class C, x = $x") }
}

package signal

import java.util.HashMap

object SigSlot {
    val signalMap = HashMap<(Any) -> Unit, MutableList<(Any) -> Unit>>()

    fun connect(signal: (Any) -> Unit, slot: (Any) -> Unit) {
        if (!signalMap.containsKey(signal)) {
            signalMap.put(signal, listOf(slot).toArrayList())
        } else {
            signalMap.get(signal).add(slot)
        }
    }

    fun disconnect(signal: (Any) -> Unit, slot: (Any) -> Unit) {
        val slots = signalMap.get(signal)
        if (slots != null) {
            slots.filter { it == slot } map { slots.remove(it) }
            if (slots.count() == 0) {
                signalMap.remove(signal)
            }
        }
    }

    fun action(signal: Any, value: Any) {
        val slots = signalMap.get(signal)
        if (slots != null) {
            for(slot in slots) {
                slot(value)
            }
        }
    }
}

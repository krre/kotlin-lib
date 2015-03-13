package signal

import java.util.HashMap

object SigSlot {
    val signalMap = HashMap<(Any) -> Unit, MutableList<(Any) -> Unit>>()

    fun connect(signal: (Any) -> Unit, slot: (Any) -> Unit) {
        if (!signalMap.containsKey(signal)) {
            val list = listOf(slot).toArrayList()
            signalMap.put(signal, list)
        } else {
            signalMap.get(signal).add(slot)
        }
    }

    fun action(signal: Any, value: Any) {
        for ((sig, slots) in signalMap) {
            if (sig == signal) {
                for(slot in slots) {
                    slot(value)
                }
            }
        }
    }
}

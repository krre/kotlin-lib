package signal

import java.util.HashMap

object SigSlot {
    val hash = HashMap<(Any) -> Unit, (Any) -> Unit>()

    fun connect(signal: (Any) -> Unit, slot: (Any) -> Unit) {
        hash.put(slot, signal)
    }

    fun action(signal: Any, value: Any) {
        for (item in hash) {
            if (item.getValue() == signal) {
                val slot = item.getKey()
                slot(value)
            }
        }
    }
}

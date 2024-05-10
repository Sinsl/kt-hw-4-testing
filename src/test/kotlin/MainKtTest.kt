import org.junit.Test

import org.junit.Assert.*
import ru.netology.calcTransferFee

class MainKtTest {

    @Test
    fun calcTransferFeeMCLessLimit() {
        val typeCard = "MasterCard"
        val sumTransferMonth = 10_000
        val currentTransfer = 10_000

        val result = calcTransferFee(typeCard, sumTransferMonth, currentTransfer)

        assertEquals(0, result)
    }

    @Test
    fun calcTransferFeeMCMoreLimit() {
        val typeCard = "MasterCard"
        val sumTransferMonth = 80_000
        val currentTransfer = 10_000

        val result = calcTransferFee(typeCard, sumTransferMonth, currentTransfer)

        assertEquals(80, result)
    }

    @Test
    fun calcTransferFeeVisa() {
        val typeCard = "Visa"
        val sumTransferMonth = 80_000
        val currentTransfer = 10_000

        val result = calcTransferFee(typeCard, sumTransferMonth, currentTransfer)

        assertEquals(75, result)
    }

    @Test
    fun calcTransferFeeMir() {
        val typeCard = "Mir"
        val sumTransferMonth = 80_000
        val currentTransfer = 10_000

        val result = calcTransferFee(typeCard, sumTransferMonth, currentTransfer)

        assertEquals(0, result)
    }

    @Test
    fun calcTransferFeeSumDefailt() {
        val typeCard = "MasterCard"
        val currentTransfer = 10_000

        val result = calcTransferFee(typeCard = typeCard, currentTransfer = currentTransfer)

        assertEquals(0, result)
    }
}
package ru.netology

fun main() {
    var sumTransferMonthMir = 0
    var sumTransferMonthVisa = 0
    var sumTransferMonthMC = 0

//    sumTransferMonthMir += checkTransfer(sumTransferMonth = sumTransferMonthMir, currentTransfer = 60_000)
//    sumTransferMonthMir += checkTransfer(sumTransferMonth = sumTransferMonthMir, currentTransfer = 150_000)
//    sumTransferMonthMir += checkTransfer(sumTransferMonth = sumTransferMonthMir, currentTransfer = 150_000)
//    sumTransferMonthMir += checkTransfer(sumTransferMonth = sumTransferMonthMir, currentTransfer = 150_000)
//    printBalans(sumTransferMonthMir, sumTransferMonthVisa, sumTransferMonthMC)
//    sumTransferMonthMir += checkTransfer(sumTransferMonth = sumTransferMonthMir, currentTransfer = 100_000)
//    printBalans(sumTransferMonthMir, sumTransferMonthVisa, sumTransferMonthMC)

    sumTransferMonthVisa += checkTransfer("Visa", sumTransferMonth = sumTransferMonthVisa, currentTransfer = 20_000)
    sumTransferMonthVisa += checkTransfer("Visa", sumTransferMonth = sumTransferMonthVisa, currentTransfer = 45_000)
    sumTransferMonthVisa += checkTransfer("Visa", sumTransferMonth = sumTransferMonthVisa, currentTransfer = 11_000)
    sumTransferMonthVisa += checkTransfer("Visa", sumTransferMonth = sumTransferMonthVisa, currentTransfer = 20_000)
    printBalans(sumTransferMonthMir, sumTransferMonthVisa, sumTransferMonthMC)
    sumTransferMonthVisa += checkTransfer("Visa", sumTransferMonth = sumTransferMonthVisa, currentTransfer = 170_000)
    printBalans(sumTransferMonthMir, sumTransferMonthVisa, sumTransferMonthMC)

    sumTransferMonthMC += checkTransfer("MasterCard", sumTransferMonth = sumTransferMonthMC, currentTransfer = 20_000)
    sumTransferMonthMC += checkTransfer("MasterCard", sumTransferMonth = sumTransferMonthMC, currentTransfer = 45_000)
    sumTransferMonthMC += checkTransfer("MasterCard", sumTransferMonth = sumTransferMonthMC, currentTransfer = 11_000)
    sumTransferMonthMC += checkTransfer("MasterCard", sumTransferMonth = sumTransferMonthMC, currentTransfer = 20_000)
    printBalans(sumTransferMonthMir, sumTransferMonthVisa, sumTransferMonthMC)
    sumTransferMonthMC += checkTransfer("MasterCard", sumTransferMonth = sumTransferMonthMC, currentTransfer = 170_000)

}

fun printBalans(mir: Int, visa: Int, mc: Int) {
    println("Суммы переводов за месяц по картам: мир - $mir, visa - $visa, mastercard - $mc")
}

fun checkTransfer(typeCard: String = "Mir", sumTransferMonth: Int = 0, currentTransfer: Int): Int {
    if (isBlockedTransfer(sumTransferMonth, currentTransfer)) {
        println("Операция на сумму $currentTransfer невозможна из-за превышения лимитов.")
        return 0
    }
    val commission = calcTransferFee(typeCard, sumTransferMonth = sumTransferMonth, currentTransfer = currentTransfer)
    println("Перевод на сумму $currentTransfer выполнен успешно. Комиссия: $commission руб")
    return currentTransfer
}

fun isBlockedTransfer(sumTransferMonth: Int, currentTransfer: Int): Boolean {
    val limitDay = 150_000
    val limitMonth = 600_000
    return (currentTransfer > limitDay || sumTransferMonth + currentTransfer > limitMonth)
}

fun calcTransferFee(typeCard: String, sumTransferMonth: Int = 0, currentTransfer: Int): Int {
    val freeLimit = 75_000
    val taxMC = 0.006
    val taxVisa = 0.0075

    return when (typeCard) {
        "MasterCard" -> {
            if (sumTransferMonth + currentTransfer < freeLimit) {
                return 0
            }
            val sumForCommission = currentTransfer - 0.coerceAtLeast(freeLimit - sumTransferMonth)
            return (sumForCommission * taxMC).toInt() + 20
        }

        "Visa" -> 35.coerceAtLeast((currentTransfer * taxVisa).toInt())
        else -> 0
    }
}
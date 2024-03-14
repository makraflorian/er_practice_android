package com.ersummonrange.calculator.utils

class Constants() {
    companion object {
        val weaponRegularRangeTable = mapOf(
            0 to intArrayOf(0, 3, 0, 1),
            1 to intArrayOf(0, 4, 0, 1),
            2 to intArrayOf(0, 5, 0, 2),
            3 to intArrayOf(0, 6, 0, 2),
            4 to intArrayOf(1, 7, 1, 3),
            5 to intArrayOf(2, 8, 1, 3),
            6 to intArrayOf(3, 10, 2, 4),
            7 to intArrayOf(4, 11, 2, 4),
            8 to intArrayOf(5, 12, 2, 5),
            9 to intArrayOf(6, 13, 3, 5),
            10 to intArrayOf(6, 14, 3, 5),
            11 to intArrayOf(7, 15, 3, 6),
            12 to intArrayOf(8, 17, 4, 7),
            13 to intArrayOf(9, 18, 4, 7),
            14 to intArrayOf(10, 19, 4, 7),
            15 to intArrayOf(11, 20, 5, 8),
            16 to intArrayOf(12, 21, 5, 8),
            17 to intArrayOf(12, 22, 5, 9),
            18 to intArrayOf(13, 24, 6, 9),
            19 to intArrayOf(14, 25, 6, 10),
            20 to intArrayOf(15, 25, 6, 10),
            21 to intArrayOf(16, 25, 7, 10),
            22 to intArrayOf(17, 25, 7, 10),
            23 to intArrayOf(18, 25, 8, 10),
            24 to intArrayOf(18, 25, 8, 10),
            25 to intArrayOf(19, 25, 8, 10)
        )

        val weaponSomberRangeTable = mapOf(
            0 to intArrayOf(0, 3, 0, 1),
            1 to intArrayOf(0, 5, 0, 2),
            2 to intArrayOf(2, 8, 1, 3),
            3 to intArrayOf(4, 11, 2, 4),
            4 to intArrayOf(6, 14, 3, 5),
            5 to intArrayOf(8, 17, 4, 7),
            6 to intArrayOf(11, 20, 5, 8),
            7 to intArrayOf(12, 22, 5, 9),
            8 to intArrayOf(15, 25, 6, 10),
            9 to intArrayOf(17, 25, 7, 10),
            10 to intArrayOf(19, 25, 8, 10)
        )
    }
}

data class SummonRangeCoefficients(
    val a: Double,
    val b: Double,
    val reverseA: Double,
    val reverseB: Double,
) {
    companion object {
        val summonMin: SummonRangeCoefficients =
            SummonRangeCoefficients(a = 0.9, b = -10.0, reverseA = 1.1, reverseB = -10.0)
        val summonMax: SummonRangeCoefficients =
            SummonRangeCoefficients(a = 1.1, b = 10.0, reverseA = 0.9, reverseB = 10.0)
        val invadeMin: SummonRangeCoefficients =
            SummonRangeCoefficients(a = 0.9, b = 0.0, reverseA = 1.1, reverseB = -20.0)
        val invadeMax: SummonRangeCoefficients =
            SummonRangeCoefficients(a = 1.1, b = 20.0, reverseA = 0.9, reverseB = 0.0)
    }
}

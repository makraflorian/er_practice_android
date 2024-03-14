package com.ersummonrange.calculator.summonrange

import com.ersummonrange.calculator.model.MultiplayerRanges

data class SummonRangeState(
    val inputRuneLevel: String,
    val inputWeaponLevel: String,
    val runeLevel: Double,
    val weaponLevel: Int,
    val isSomber: Boolean,
    val presentSheet: Boolean,
    val rangesByType: MutableList<MultiplayerRanges>
)
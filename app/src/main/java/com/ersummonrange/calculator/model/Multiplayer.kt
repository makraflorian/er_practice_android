package com.ersummonrange.calculator.model

data class MultiplayerRanges(
    //val id: String,
    val name: String,
    val ranges: MutableList<LevelRange>
)

data class LevelRange(
    val id: Int,
    val name: String,
    val minLevel: Int,
    val maxLevel: Int
)

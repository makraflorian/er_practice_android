package com.ersummonrange.calculator.summonrange

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ersummonrange.calculator.R
import com.ersummonrange.calculator.model.LevelRange
import com.ersummonrange.calculator.model.MultiplayerRanges
import com.ersummonrange.calculator.utils.Constants
import com.ersummonrange.calculator.utils.SummonRangeCoefficients
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.ceil
import kotlin.math.floor

class SummonRangeViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(
        SummonRangeState(
            inputRuneLevel = "",
            inputWeaponLevel = "",
            runeLevel = 0.0,
            weaponLevel = 0,
            isSomber = false,
            presentSheet = false,
            rangesByType = mutableListOf()
        )
    )
    val uiState: StateFlow<SummonRangeState> = _uiState.asStateFlow()

    var inputText by mutableStateOf("")
        private  set

    //region Update Helpers
    fun updateInputRuneLevel(level: String) {
        _uiState.update { currentState ->
            currentState.copy(
                inputRuneLevel = level,
                runeLevel = level.toDouble()
            )
        }
    }

    fun updateInputWeaponLevel(level: String) {
        _uiState.update { currentState ->
            currentState.copy(
                inputWeaponLevel = level,
                weaponLevel = level.toInt()
            )
        }
    }

    fun resetRanges(range: MutableList<MultiplayerRanges>) {
        _uiState.update { currentState ->
            currentState.copy(
                rangesByType = range
            )
        }
    }



    fun updateText(text: String) {
        inputText = text
    }
    //endregion

    fun getRanges() {

        resetRanges(mutableListOf())

        if (uiState.value.inputRuneLevel.isNotEmpty()) {
            val summonMin = getLevelMinMax(coefficients = SummonRangeCoefficients.summonMin)
            val summonMax = getLevelMinMax(coefficients = SummonRangeCoefficients.summonMax)
            val canSummon = LevelRange(id = R.drawable.corn_ket, name = "canSummon", minLevel = summonMin.first, maxLevel = summonMax.first)
            val canBeSummonedBy = LevelRange(id = R.drawable.corn_ket, name = "canBeSummonedBy", minLevel = summonMin.second, maxLevel = summonMax.second)

            val invadeMin = getLevelMinMax(coefficients = SummonRangeCoefficients.invadeMin)
            val invadeMax = getLevelMinMax(coefficients = SummonRangeCoefficients.invadeMax)
            val canInvade = LevelRange(id = R.drawable.corn_ket, name = "canInvade", minLevel = invadeMin.first, maxLevel = invadeMax.first)
            val canBeInvadedBy = LevelRange(id = R.drawable.corn_ket, name = "canBeInvadedBy", minLevel = invadeMin.second, maxLevel = invadeMax.second)

            val coopRanges = MultiplayerRanges(name = "coopRanges", ranges = mutableListOf(canSummon, canBeSummonedBy))
            val pvpRanges = MultiplayerRanges(name = "pvpRanges", ranges = mutableListOf(canInvade, canBeInvadedBy))

            var tempRanges = uiState.value.rangesByType
            tempRanges.add(coopRanges)
            tempRanges.add(pvpRanges)
            resetRanges(tempRanges)
        }

        if (uiState.value.inputWeaponLevel.isNotEmpty()) {
            val weaponLevelData =  getWeaponMinMax()

            if (weaponLevelData.isNotEmpty()) {
                val regularWeapon = LevelRange(id = R.drawable.corn_ket, name = "regularWeapon", minLevel = weaponLevelData[0], maxLevel = weaponLevelData[1])
                val somberWeapon = LevelRange(id = R.drawable.corn_ket, name = "somberWeapon", minLevel = weaponLevelData[2], maxLevel = weaponLevelData[3])

                val weaponRanges = MultiplayerRanges(name = "weaponRanges", ranges = mutableListOf(regularWeapon, somberWeapon))
                var tempRanges = uiState.value.rangesByType
                tempRanges.add(weaponRanges)
                resetRanges(tempRanges)
            }
        }
    }

    fun getLevelMinMax(coefficients: SummonRangeCoefficients): Pair<Int, Int> {
        val result = floor(uiState.value.runeLevel * coefficients.a + coefficients.b).toInt()
        val resultReverse = ceil((uiState.value.runeLevel + coefficients.reverseB) / coefficients.reverseA).toInt()
        return Pair(if(result > 0) result else 1, if(resultReverse > 0) resultReverse else 1)
    }

    fun getWeaponMinMax(): IntArray {
        return if (!uiState.value.isSomber) {
            (Constants.weaponRegularRangeTable[uiState.value.weaponLevel]) ?: intArrayOf()
        } else {
            (Constants.weaponSomberRangeTable[uiState.value.weaponLevel]) ?: intArrayOf()
        }
    }

}
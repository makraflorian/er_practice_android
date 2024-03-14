package com.ersummonrange.calculator.summonrange

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ersummonrange.calculator.R
import com.ersummonrange.calculator.summonrange.detail.SummonRangeDetailView
import com.ersummonrange.calculator.ui.theme.ERSummonRangeCalculatorTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummonRangeView(viewModel: SummonRangeViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var inputRuneLevel by remember { mutableStateOf("") }
    var inputWeaponLevel by remember { mutableStateOf("") }
    var chatOutputText by remember { mutableStateOf("asd") }

    Column {
        Text(text = context.getString(R.string.ranges_title))

        Text(text = uiState.inputRuneLevel)

        Text(text = chatOutputText)

        OutlinedTextField(
            value = uiState.inputRuneLevel,
            label = { Text(text = context.getString(R.string.rune_level)) },
            onValueChange = {
                //viewModel.updateText(it)
                viewModel.updateInputRuneLevel(it)
            },
        )
        OutlinedTextField(
            value = uiState.inputWeaponLevel,
            label = { Text(text = context.getString(R.string.weapon_level)) },
            onValueChange = {
                viewModel.updateInputWeaponLevel(it)
            },
        )
        Button(onClick = {
            viewModel.getRanges()
            scope.launch { sheetState.show() }.invokeOnCompletion {
                if (sheetState.isVisible) {
                    showBottomSheet = true
                }
            }
        }) {
            Text(text = "send")
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                // Sheet content
                SummonRangeDetailView(list = uiState.rangesByType)

                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text("Hide bottom sheet")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SummonRangeViewPreview() {
    ERSummonRangeCalculatorTheme {
        SummonRangeView()
    }
}
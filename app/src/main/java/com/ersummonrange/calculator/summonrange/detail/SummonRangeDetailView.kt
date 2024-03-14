package com.ersummonrange.calculator.summonrange.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ersummonrange.calculator.R
import com.ersummonrange.calculator.model.LevelRange
import com.ersummonrange.calculator.model.MultiplayerRanges
import com.ersummonrange.calculator.summonrange.SummonRangeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SummonRangeDetailView(list: MutableList<MultiplayerRanges>) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = { list.size })
    
    Column {
        HorizontalPager(state = pagerState) { page ->
            RangesView(title = list[page].name, levelRanges = list[page].ranges)
        }
    }
}

@Composable
fun RangesView(title: String, levelRanges: MutableList<LevelRange>) {
    Column {
        Text(text = title)
        levelRanges.forEach { range ->
            RangeCardView(range = range)
        }
    }
}

@Composable
fun RangeCardView(range: LevelRange) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(id = range.id),
            contentDescription = "its a ket",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(75.dp)
        )
        Column {
            Text(text = range.name)
            Row {
                Text(text = range.minLevel.toString())
                Text(text = range.maxLevel.toString())
            }
        }
    }
}
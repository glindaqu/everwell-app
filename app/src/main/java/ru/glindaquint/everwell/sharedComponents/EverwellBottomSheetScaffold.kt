package ru.glindaquint.everwell.sharedComponents

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun EverwellBottomSheetScaffold(
    containerColor: Color,
    scaffoldState: BottomSheetScaffoldState,
    contentPadding: PaddingValues = PaddingValues(top = 10.dp),
    contentSpacing: Arrangement.Vertical = Arrangement.Top,
    contentAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    topBar: @Composable () -> Unit,
    sheetContent: @Composable () -> Unit,
    floatingActionButton: (@Composable () -> Unit)? = null,
    navigationBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = { sheetContent() },
        modifier = Modifier.fillMaxSize(),
        topBar = { topBar() },
    ) { paddingValues ->
        Scaffold(
            floatingActionButton = { floatingActionButton?.invoke() },
            bottomBar = { navigationBar?.invoke() },
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(containerColor)
                        .padding(bottom = 1.dp)
                        .navigationBarsPadding()
                        .verticalScroll(rememberScrollState())
                        .padding(contentPadding),
                verticalArrangement = contentSpacing,
                horizontalAlignment = contentAlignment,
            ) {
                content()
            }
        }
    }
}

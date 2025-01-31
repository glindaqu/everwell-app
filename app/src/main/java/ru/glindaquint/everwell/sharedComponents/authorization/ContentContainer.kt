package ru.glindaquint.everwell.sharedComponents.authorization

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.utils.dropShadow
import ru.glindaquint.everwell.utils.pxToDp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("ktlint:standard:function-naming")
@Composable
fun ContentContainer(
    topBarTitle: String,
    content: @Composable () -> Unit,
) {
    Scaffold(topBar = {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = MainPrimary,
                        shape = RoundedCornerShape(bottomEnd = 18.dp, bottomStart = 18.dp),
                    ).statusBarsPadding(),
        ) {
            Text(
                modifier = Modifier.padding(vertical = 25.dp, horizontal = 16.dp),
                text = topBarTitle,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                color = MainSecondary,
            )
        }
    }, containerColor = MainBackground, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.pxToDp())
                        .dropShadow(
                            color = Color(0xff326779).copy(0.8f),
                            offsetX = (3).dp,
                            offsetY = (5).dp,
                            blurRadius = 4.dp,
                            roundX = 40f,
                            roundY = 40f,
                        ),
                colors = CardDefaults.cardColors(containerColor = MainOnBackground),
                shape = RoundedCornerShape(18.dp),
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 62.dp, horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    content()
                }
            }
        }
    }
}

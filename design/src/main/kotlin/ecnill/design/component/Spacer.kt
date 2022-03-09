package ecnill.design.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(size: Dp = 8.dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun HorizontalSpacer(size: Dp = 8.dp) {
    Spacer(modifier = Modifier.width(size))
}
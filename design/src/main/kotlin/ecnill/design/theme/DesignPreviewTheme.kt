package ecnill.design.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DesignPreviewTheme(
    modifier: Modifier = Modifier,
    backgroundProvider: @Composable () -> Color = { DesignTheme.colors.background },
    content: @Composable ColumnScope.() -> Unit,
) = DesignTheme {
    Column(
        modifier = Modifier.fillMaxWidth().background(backgroundProvider()).then(modifier),
        content = content,
    )
}
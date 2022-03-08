package ecnill.design.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DesignTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = {
        Box(modifier = Modifier.fillMaxWidth()) {
            content()
        }
    })
}
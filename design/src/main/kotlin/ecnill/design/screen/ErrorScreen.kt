package ecnill.design.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ecnill.design.R
import ecnill.design.component.VerticalSpacer
import ecnill.design.theme.DesignTheme

@Composable
fun ErrorScreen(message: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            tint = DesignTheme.colors.error,
            painter = painterResource(id = R.drawable.ic_report),
            contentDescription = stringResource(id = R.string.design_error_icon_content_description)
        )
        VerticalSpacer(16.dp)
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = DesignTheme.colors.primaryText,
        )
    }
}
package ecnill.country.feature.launch.scene

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ecnill.country.feature.launch.R
import ecnill.design.theme.DesignTheme
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen(navigateToNext: () -> Unit) = DesignTheme {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = LaunchConfig.animationDelay,
                easing = { OvershootInterpolator(4f).getInterpolation(it) }
            )
        )

        delay(LaunchConfig.launchDelay)
        navigateToNext()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(160.dp).scale(scale.value),
            painter = painterResource(R.drawable.ic_globe),
            contentDescription = stringResource(R.string.launch_icon_content_description)
        )

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.launch_title),
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
        )
    }
}
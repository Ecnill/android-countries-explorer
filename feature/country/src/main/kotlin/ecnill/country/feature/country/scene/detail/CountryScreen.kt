package ecnill.country.feature.country.scene.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ecnill.country.feature.country.R
import ecnill.country.feature.country.scene.ui.CountryInformationRow
import ecnill.design.component.Header
import ecnill.design.component.ProgressIndicator
import ecnill.design.component.RemoteImage
import ecnill.design.screen.ErrorScreen
import ecnill.design.theme.DesignTheme
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CountryScreen(country: String, navigateBack: () -> Unit) = DesignTheme {
    val viewModel: CountryViewModel = getViewModel { parametersOf(country) }
    val state = viewModel.states.collectAsState().value

    Scaffold(
        topBar = {
            Header(
                navigationButton = Header.NavigationButton(
                    action = navigateBack,
                    type = Header.NavigationButton.Type.Close
                )
            )
        },
        content = {
            when {
                state.loading -> ProgressIndicator()
                state.error -> ErrorScreen(stringResource(R.string.country_detail_error_message))
                else -> CountryDetail(state)
            }
        }
    )
}

@Composable
private fun CountryDetail(state: CountryState) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = state.officialName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = DesignTheme.colors.primaryText,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        RemoteImage(
            imageUrl = state.flagPng.orEmpty(),
            modifier = Modifier
                .size(width = 400.dp, height = 300.dp)
                .clip(RectangleShape)
                .align(Alignment.CenterHorizontally)
        )

        state.properties.forEach {
            CountryInformationRow(
                title = stringResource(id = it.titleResId),
                value = it.value,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        val uriHandler = LocalUriHandler.current
        if (state.mapUrl != null) {
            Button(
                onClick = { uriHandler.openUri(state.mapUrl) },
                modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.CenterHorizontally)
            ) {
                Text(text = stringResource(id = R.string.country_detail_button_map), modifier = Modifier.padding(8.dp))
            }
        }
    }
}
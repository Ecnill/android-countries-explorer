package ecnill.country.feature.country.scene.list

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import ecnill.country.feature.country.R
import ecnill.country.feature.country.model.CountryListItem
import ecnill.country.feature.country.scene.ui.CountryInformationRow
import ecnill.design.component.Header
import ecnill.design.component.HorizontalSpacer
import ecnill.design.component.ProgressIndicator
import ecnill.design.component.RemoteImage
import ecnill.design.component.VerticalSpacer
import ecnill.design.screen.ErrorScreen
import ecnill.design.theme.DesignTheme
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CountriesScreen(
    region: String,
    @StringRes regionResId: Int?,
    navigateToNext: (country: String) -> Unit,
    navigateBack: () -> Unit,
) = DesignTheme {
    val viewModel: CountriesViewModel = getViewModel { parametersOf(region) }
    val state = viewModel.states.collectAsState().value

    Scaffold(
        topBar = {
            Header(
                title = stringResource(
                    R.string.country_list_header_title,
                    if (regionResId != null) stringResource(id = regionResId) else region
                ),
                navigationButton = Header.NavigationButton(
                    action = navigateBack,
                    type = Header.NavigationButton.Type.Back
                )
            )
        },
        content = {
            when {
                state.loading -> ProgressIndicator()
                !state.loading && state.countries.isEmpty() -> {
                    ErrorScreen(message = stringResource(R.string.country_list_error_message))
                }
                else -> {
                    SwipeRefresh(
                        state = SwipeRefreshState(state.swiping),
                        onRefresh = { viewModel.dispatch(CountriesAction.Refreshing) },
                        indicator = { state, trigger ->
                            SwipeRefreshIndicator(
                                state = state,
                                refreshTriggerDistance = trigger,
                                scale = true,
                                backgroundColor = DesignTheme.colors.primary,
                            )
                        }
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            itemsIndexed(state.countries) { index, country ->
                                CountryItem(country = country, onItemClicked = navigateToNext)
                                if (index != state.countries.lastIndex) {
                                    Divider(color = DesignTheme.colors.onBackground, thickness = 1.dp)
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun CountryItem(country: CountryListItem, onItemClicked: (String) -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp).clickable { onItemClicked(country.officialName) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RemoteImage(
            imageUrl = country.flagPng.orEmpty(),
            modifier = Modifier
                .size(width = 96.dp, height = 60.dp)
                .clip(RectangleShape)
                .border(width = 1.dp, color = DesignTheme.colors.divider)
        )
        HorizontalSpacer(16.dp)

        Column(modifier = Modifier.padding(start = 4.dp)) {
            Text(text = country.officialName, fontSize = 20.sp, color = DesignTheme.colors.primaryText)
            VerticalSpacer(8.dp)

            if (country.officialName != country.commonName) {
                CountryInformationRow(
                    title = stringResource(id = R.string.country_list_info_unofficial_name),
                    value = country.commonName
                )
                VerticalSpacer(4.dp)
            }
            CountryInformationRow(
                title = stringResource(id = R.string.country_list_info_capital),
                value = country.capital
            )
        }
    }
}
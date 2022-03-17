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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
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
import ecnill.design.theme.CountryTheme
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CountriesScreen(
    region: String,
    @StringRes regionResId: Int?,
    navigateToNext: (country: String) -> Unit,
    navigateBack: () -> Unit,
) = CountryTheme {
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
                    type = if (state.error) Header.NavigationButton.Type.Close else Header.NavigationButton.Type.Back
                )
            )
        },
        content = {
            when {
                state.loading -> ProgressIndicator()
                state.error -> ErrorScreen(message = stringResource(R.string.country_list_error_message))
                else -> {
                    SwipeRefresh(
                        state = SwipeRefreshState(state.swiping),
                        onRefresh = { viewModel.dispatch(CountriesAction.Refreshing) },
                        indicator = { state, trigger ->
                            SwipeRefreshIndicator(
                                state = state,
                                refreshTriggerDistance = trigger,
                                scale = true,
                                backgroundColor = CountryTheme.colors.primary,
                            )
                        }
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(state.countries) { country ->
                                CountryItem(country = country, onItemClicked = navigateToNext)
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
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onItemClicked(country.officialName) },
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RemoteImage(
                imageUrl = country.flagPng.orEmpty(),
                modifier = Modifier
                    .size(width = 80.dp, height = 56.dp)
                    .clip(RectangleShape)
                    .border(width = 1.dp, color = CountryTheme.colors.divider)
            )
            HorizontalSpacer(8.dp)

            Column(modifier = Modifier.padding(start = 4.dp)) {
                Text(text = country.officialName, fontSize = 20.sp, color = CountryTheme.colors.primaryText)
                Divider(
                    modifier = Modifier.padding(top = 4.dp, end = 32.dp),
                    color = CountryTheme.colors.divider,
                )
                if (country.officialName != country.commonName) {
                    CountryInformationRow(
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                        title = stringResource(id = R.string.country_list_info_common_name),
                        value = country.commonName
                    )
                } else {
                    VerticalSpacer(16.dp)
                }
                CountryInformationRow(
                    title = stringResource(id = R.string.country_list_info_capital),
                    value = country.capital
                )
                VerticalSpacer(4.dp)
            }
        }
    }
}
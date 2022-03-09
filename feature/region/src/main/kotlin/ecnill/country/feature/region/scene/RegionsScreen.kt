package ecnill.country.feature.region.scene

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ecnill.country.feature.region.R
import ecnill.country.feature.region.data.Region
import ecnill.design.component.Header
import ecnill.design.theme.DesignTheme
import java.util.Locale
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegionsScreen(navigateToNext: (region: String, regionResId: Int) -> Unit) = DesignTheme {
    val viewModel: RegionsViewModel = getViewModel()
    Scaffold(
        topBar = { Header(title = stringResource(id = R.string.region_header_title)) },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 4.dp
                ),
                content = {
                    items(viewModel.regions) {
                        RegionItem(region = it, onItemClicked = navigateToNext)
                    }
                }
            )
        }
    )
}

@Composable
private fun RegionItem(region: Region, onItemClicked: (String, Int) -> Unit) {
    Card(
        backgroundColor = DesignTheme.colors.secondary,
        contentColor = DesignTheme.colors.onSecondary,
        modifier = Modifier.padding(16.dp).fillMaxWidth().clickable { onItemClicked(region.key, region.resId) },
        elevation = 4.dp,
    ) {
        Text(
            text = stringResource(id = region.resId).uppercase(Locale.US),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
        )
    }
}
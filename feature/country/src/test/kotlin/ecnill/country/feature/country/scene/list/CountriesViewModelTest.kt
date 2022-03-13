package ecnill.country.feature.country.scene.list

import ecnill.arch.test.TestDispatcherRule
import ecnill.arch.test.lastValue
import ecnill.country.feature.country.model.CountryListItem
import ecnill.country.feature.country.model.RegionRequest
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CountriesViewModelTest {

    @get:Rule
    val coroutinesRule = TestDispatcherRule()

    @Test
    fun `should emit initial state`() {
        val region = "region"
        val reducer = reducer()
        val viewModel = CountriesViewModel(region, reducer)

        val state = viewModel.states.lastValue()
        state shouldNotBe null
        state?.let {
            it.loading shouldBe false
            it.region shouldBe region
            it.countries shouldBe countries()
        }
    }

    @Test
    fun `should dispatch actions in correct order during refreshing`() {
        val reducer = reducer()
        val viewModel = CountriesViewModel("region", reducer)

        viewModel.dispatch(CountriesAction.Refreshing)

        coVerifySequence {
            reducer.reduce(any(), CountriesAction.Fetching("region"))
            reducer.reduce(any(), CountriesAction.Fetched(countries()))
            reducer.reduce(any(), CountriesAction.Refreshing)
            reducer.reduce(any(), CountriesAction.Fetched(countries()))
        }
    }

    private fun reducer(): CountriesReducer = spyk(
        CountriesReducer(
            fetchCountries = mockk {
                every { create(RegionRequest(any()), any()) } returns mockk {
                    coEvery { this@mockk.invoke() } returns CountriesAction.Fetched(countries())
                }
            }
        )
    )

    private fun countries() = listOf(
        CountryListItem(
            region = "region",
            officialName = "Country 1",
            commonName = "",
            flagPng = null,
            capital = "",
        ),
    )
}
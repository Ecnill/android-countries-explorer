package ecnill.country.feature.region.data

import androidx.annotation.StringRes
import ecnill.country.feature.region.R

internal enum class Region(
    val key: String,
    @StringRes val resId: Int,
) {
    Africa("Africa", R.string.region_name_africa),
    Americas("Americas", R.string.region_name_americas),
    Europe("Europe", R.string.region_name_europe),
    Asia("Asia", R.string.region_name_asia),
    Oceania("Oceania", R.string.region_name_oceania)
}
package ppm.b.kelompok4.tokoelektronik.screens

import androidx.annotation.StringRes
import ppm.b.kelompok4.tokoelektronik.R
enum class Menu(
    @StringRes val title: Int,
    val icon: Int,
    val route: String
) {
    PENGELOLAAN_KOMPUTER(R.string.pengelolaan_komputer, R.drawable.baseline_computer_24, "pengelolaan-komputer"),
    PENGELOLAAN_SMARTPHONE(R.string.pengelolaan_smartphone, R.drawable.baseline_smartphone_24, "pengelolaan-smartphone"),
    PENGELOLAAN_PERIFERAL(R.string.pengelolaan_periferal, R.drawable.baseline_keyboard_24, "pengelolaan-periferal"),
    ABOUT(R.string.about, R.drawable.baseline_info_24, "about");
    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu
        {
            return when (resource) {
                R.string.pengelolaan_komputer -> PENGELOLAAN_KOMPUTER
                R.string.pengelolaan_periferal -> PENGELOLAAN_PERIFERAL
                R.string.about -> PENGELOLAAN_SMARTPHONE
                else -> ABOUT
            }
        }
    }
}
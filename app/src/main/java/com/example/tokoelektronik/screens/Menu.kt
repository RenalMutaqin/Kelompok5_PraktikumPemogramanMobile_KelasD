package com.example.TokoElektronik.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.TokoElektronik.R

enum class Menu (
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
){
    HOME(R.string.home, Icons.Default.Home, "home"),
    KOMPUTER("Komputer".komputer, Icons.Default.Person, "komputer"),
    PERIFERAL("Periferal".periferal, Icons.Default.AccountBox, "periferal"),
    SMARTPHONE("Smartphone".smartphone, Icons.Default.Email, "smartphoone");

    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu
        {
            return when (resource) {
                R.string.home -> HOME
                "Komputer" -> KOMPUTER
                "Periferal" -> PERIFERAL
                else -> SMARTPHONE
            }
        }
    }
}
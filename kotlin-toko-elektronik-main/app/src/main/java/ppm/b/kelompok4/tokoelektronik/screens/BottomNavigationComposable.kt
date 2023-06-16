package ppm.b.kelompok4.tokoelektronik.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
@Composable
fun BottomNavigationComposable(title : String, onClick: (Menu) -> Unit)
{
    val tabs = Menu.values()
    BottomNavigation(
        elevation = 15.dp
    ) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                selected = tab.name == title,
                onClick = {
                    onClick(tab)
                },
                icon = {
                    Icon(
                        painterResource(id = tab.icon),
                        modifier =
                        Modifier
                            .height(30.dp)
                            .width(30.dp),
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                selectedContentColor =
                LocalContentColor.current,
                unselectedContentColor =
                LocalContentColor.current,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}
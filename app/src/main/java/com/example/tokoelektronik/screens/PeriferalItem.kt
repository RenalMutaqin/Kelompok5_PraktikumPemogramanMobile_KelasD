package com.example.tokoelektronik.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.message
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import com.example.tokoelektronik.model.Periferal

@Composable
fun PeriferalItem(item: Periferal, navController: NavHostController, onDelete: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val subMenus = listOf("Edit", "Delete")
    val confirmationDialogState = rememberMaterialDialogState()
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()) {
            Column(modifier = Modifier.weight(3f)) {
                Text(text = "NAMA", fontSize = 14.sp)
                Text(item.nama, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Column(modifier = Modifier.weight(3f)) {
                Text(text = "HARGA", fontSize = 14.sp)
                Text(text = item.harga, fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)
            }

            Column(modifier = Modifier.weight(3f)) {
                Text(text = "Deskripsi", fontSize = 14.sp)
                Text(item.deskripsi, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Column(modifier = Modifier.weight(3f)) {
                Text(text = "Jenis", fontSize = 14.sp)
                Text(item.jenis, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Icon(
                Icons.Default.MoreVert,
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .padding(0.dp)
                    .weight(1f, true)
                    .clickable {
                        expanded = true
                    },
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = (-66).dp, y = (-10).dp)
        ) {
            subMenus.forEachIndexed { _, s ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    when (s) {
                        "Edit" -> {
                            navController.navigate("edit-periferal/${item.id}")
                        }
                        "Delete" -> {
                            confirmationDialogState.show()
                        }
                    }
                }) {
                    Text(text = s)
                }
            }
        }
    }
    Divider(modifier = Modifier.fillMaxWidth())
    MaterialDialog(dialogState = confirmationDialogState,
        buttons = {
            positiveButton("Ya", onClick = {
                onDelete(item.id)
            })
            negativeButton("Tidak")
        }) {
        title(text = "Konfirmasi")
        message(text = "Apakah anda yakin ingin menghapus data?")
    }
}
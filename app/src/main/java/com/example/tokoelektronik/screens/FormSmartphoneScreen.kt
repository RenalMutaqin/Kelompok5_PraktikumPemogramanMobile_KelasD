package com.example.tokoelektronik.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tokoelektronik.ui.theme.Purple700
import com.example.tokoelektronik.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormSmartphoneScreen(navController : NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<SmartphoneViewModel>()
    val model = remember { mutableStateOf(TextFieldValue("")) }
    val warna = remember { mutableStateOf(TextFieldValue("")) }
    val storage = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal_rilis = remember { mutableStateOf(TextFieldValue("")) }
    val sistem_operasi = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Kode") },
            value = model.value,
            onValueChange = {
                model.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "IF231") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = warna.value,
            onValueChange = {
                warna.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "SKS") },
            value = storage.value,
            onValueChange = {
                storage.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "2") }
        )

        OutlinedTextField(
            label = { Text(text = "Praktikum") },
            value = tanggal_rilis.value,
            onValueChange = {
                tanggal_rilis.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "IF231") }
        )

        OutlinedTextField(
            label = { Text(text = "Deskrpsi") },
            value = sistem_operasi.value,
            onValueChange = {
                sistem_operasi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "IF231") }
        )

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (id == null) {
                    scope.launch {
                        viewModel.insert(
                            model.value.text,
                            warna.value.text,
                            storage.value.text.toInt(),
                            tanggal_rilis.value.text.toInt(),
                            sistem_operasi.value.text
                        )
                    }
                } else {
                    scope.launch {
                        viewModel.update(
                            id,
                            model.value.text,
                            warna.value.text,
                            storage.value.text.toInt(),
                            tanggal_rilis.value.text.toInt(),
                            sistem_operasi.value.text
                        )
                    }
                }
                navController.navigate("matkul")
            }, colors = loginButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                model.value = TextFieldValue("")
                warna.value = TextFieldValue("")
                storage.value = TextFieldValue("")
                tanggal_rilis.value = TextFieldValue("")
                sistem_operasi.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }

    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { smartphone ->
                smartphone?.let {
                    model.value = TextFieldValue(smartphone.model)
                    warna.value = TextFieldValue(smartphone.warna)
                    storage.value = TextFieldValue(smartphone.storage.toString())
                    tanggal_rilis.value = TextFieldValue(smartphone.tanggal_rilis.toString())
                    sistem_operasi.value = TextFieldValue(smartphone.sistem_operasi)
                }
            }
        }
    }
}
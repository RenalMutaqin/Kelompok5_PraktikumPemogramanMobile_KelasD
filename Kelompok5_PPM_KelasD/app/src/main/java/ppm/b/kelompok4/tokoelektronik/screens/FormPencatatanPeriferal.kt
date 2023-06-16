package ppm.b.kelompok4.tokoelektronik.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ppm.b.kelompok4.tokoelektronik.ui.theme.Purple700
import ppm.b.kelompok4.tokoelektronik.ui.theme.Teal200
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


@Composable
fun FormPencatatanPeriferal(navController : NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val jenisOptions = listOf("Jenis", "Mouse", "Keyboard")
    var expandDropdown by remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else
        "Simpan"
    val viewModel = hiltViewModel<PengelolaanPeriferalViewModel>()
    val scope = rememberCoroutineScope()
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }
    val (jenis, setJenis) = remember { mutableStateOf(jenisOptions[0]) }

    val icon = if (expandDropdown)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Nama Barang") }
        )
        OutlinedTextField(
            label = { Text(text = "Harga") },
            value = harga.value,
            onValueChange = {
                harga.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "Rp. 0") }
        )
        OutlinedTextField(
            label = { Text(text = "Deskripsi") },
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Deskripsi") }
        )

        Box(
            modifier = Modifier.padding(top = 8.dp)
        ){
            OutlinedTextField(
                onValueChange = {},
                enabled = false,
                value = jenis,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable { expandDropdown = !expandDropdown },
                trailingIcon = {
                    Icon(icon, "dropdown icon")
                },
                textStyle = TextStyle(color = Color.Black)
            )

            DropdownMenu(
                expanded = expandDropdown,
                onDismissRequest = { expandDropdown = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                jenisOptions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            setJenis(label)
                            expandDropdown = false
                        },
                        enabled = label != jenisOptions[0])
                    {
                        Text(text = label)
                    }
                }
            }
        }

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp),
                onClick = {
                if (nama.value.text.isNotBlank() && harga.value.text.isNotBlank() && deskripsi.value.text.isNotBlank() && jenis != jenisOptions[0]) {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(nama.value.text, Integer.parseInt(harga.value.text),
                                deskripsi.value.text, jenis)
                        }
                    } else {
                        scope.launch {
                            viewModel.update(id, nama.value.text, Integer.parseInt(harga.value.text),
                                deskripsi.value.text, jenis)
                        }
                    }
                    if (!isLoading.value) {
                        navController.navigate("pengelolaan-periferal")
                    }
                }
            }, colors = loginButtonColors) {
                Text(
                    text =  buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp),
                onClick = {
                nama.value = TextFieldValue("")
                harga.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
                setJenis(jenisOptions[0])
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
            viewModel.loadItem(id) { Periferal ->
                Periferal?.let {
                    nama.value = TextFieldValue(Periferal.nama)
                    harga.value = TextFieldValue(Periferal.harga.toString())
                    deskripsi.value = TextFieldValue(Periferal.deskripsi)
                    setJenis(Periferal.jenis)
                }
            }
        }
    }
}
package ppm.b.kelompok4.tokoelektronik.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.Role
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp


@Composable
fun FormPencatatanKomputer(navController : NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val upgradeOptions = listOf(0, 1);
    val jenisOptions = listOf("Jenis" ,"Laptop", "Desktop", "AIO")
    var expandDropdown by remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else
        "Simpan"

    val viewModel = hiltViewModel<PengelolaanKomputerViewModel>()
    val scope = rememberCoroutineScope()

    val merk = remember { mutableStateOf(TextFieldValue("")) }
    val (jenis, setJenis) = remember { mutableStateOf(jenisOptions[0]) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val (dapatDiupgrade, setDapatDiupgrade) = remember { mutableStateOf(upgradeOptions[0]) }
    val spesifikasi = remember { mutableStateOf(TextFieldValue("")) }

    val icon = if (expandDropdown)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier = modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Merk") },
            value = merk.value,
            onValueChange = {
                merk.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Merk") }
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

        Row(
            Modifier
                .selectableGroup()
                .padding(top = 8.dp)
        ) {
            upgradeOptions.forEach { text ->
                Row(
                    Modifier
                        .selectable(
                            selected = (text == dapatDiupgrade),
                            onClick = { setDapatDiupgrade(text) },
                            role = Role.RadioButton
                        )
                        .padding(end = 20.dp)
                ) {
                    RadioButton(
                        selected = (text == dapatDiupgrade),
                        onClick = { setDapatDiupgrade(text) })

                    val dapatDiUpgradeText = when (text) {
                        0 -> "Tidak"
                        else -> "Ya"
                    }
                    Text(
                        text = dapatDiUpgradeText,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
        }

        OutlinedTextField(
            label = { Text(text = "Spesifikasi") },
            value = spesifikasi.value,
            onValueChange = {
                spesifikasi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Spesifikasi") }
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
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .fillMaxWidth()
        ) {
            Button(modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp),
                onClick = {
                if (merk.value.text.isNotBlank() && jenis != jenisOptions[0] && harga.value.text.isNotBlank() && spesifikasi.value.text.isNotBlank()) {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(merk.value.text, jenis, Integer.parseInt(harga.value.text), dapatDiupgrade, spesifikasi.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.update(id, merk.value.text, jenis, Integer.parseInt(harga.value.text), dapatDiupgrade, spesifikasi.value.text)
                        }
                    }
                    if (!isLoading.value) {
                        navController.navigate("pengelolaan-komputer")
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
                merk.value = TextFieldValue("")
                setJenis(jenisOptions[0])
                harga.value = TextFieldValue("")
                spesifikasi.value = TextFieldValue("")
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
            viewModel.loadItem(id) { Komputer ->
                Komputer?.let {
                    merk.value = TextFieldValue(Komputer.merk)
                    setJenis(Komputer.jenis)
                    harga.value = TextFieldValue(Komputer.harga.toString())
                    setDapatDiupgrade(Komputer.dapat_diupgrade)
                    spesifikasi.value = TextFieldValue(Komputer.spesifikasi)
                }
            }
        }
    }
}
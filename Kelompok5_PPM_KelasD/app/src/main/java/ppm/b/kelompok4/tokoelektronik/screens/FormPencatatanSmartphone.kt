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
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter


@Composable
fun FormPencatatanSmartphone(navController : NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val sistemOperasiOptions = listOf("Sistem Operasi" ,"Android", "iOS")
    var expandDropdown by remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else
        "Simpan"
    val tanggalDialogState = rememberMaterialDialogState()
    val viewModel = hiltViewModel<PengelolaanSmartphoneViewModel>()
    val scope = rememberCoroutineScope()
    val model = remember { mutableStateOf(TextFieldValue("")) }
    val warna = remember { mutableStateOf(TextFieldValue("")) }
    val storage = remember { mutableStateOf(TextFieldValue("")) }
    val tanggalRilis = remember { mutableStateOf(TextFieldValue("")) }
    val (sistemOperasi, setSistemOperasi) = remember { mutableStateOf(sistemOperasiOptions[0]) }

    val icon = if (expandDropdown)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier = modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Model") },
            value = model.value,
            onValueChange = {
                model.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Model") }
        )
        OutlinedTextField(
            label = { Text(text = "Warna") },
            value = warna.value,
            onValueChange = {
                warna.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Warna") }
        )
        OutlinedTextField(
            label = { Text(text = "Storage") },
            value = storage.value,
            onValueChange = {
                storage.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "128") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Rilis") },
            value = tanggalRilis.value,
            enabled = false,
            onValueChange = {
                tanggalRilis.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    tanggalDialogState.show()
                },
            textStyle = TextStyle(color = Color.Black)
        )
        Box(
            modifier = Modifier.padding(top = 8.dp)
        ){
            OutlinedTextField(
                onValueChange = {},
                enabled = false,
                value = sistemOperasi,
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
                sistemOperasiOptions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            setSistemOperasi(label)
                            expandDropdown = false
                        },
                        enabled = label != sistemOperasiOptions[0])
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
                if (model.value.text.isNotBlank() && warna.value.text.isNotBlank() && storage.value.text.isNotBlank() && tanggalRilis.value.text.isNotBlank() && sistemOperasi != sistemOperasiOptions[0]) {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(model.value.text, warna.value.text, Integer.parseInt(storage.value.text),
                                tanggalRilis.value.text, sistemOperasi)
                        }
                    } else {
                        scope.launch {
                            viewModel.update(id, model.value.text, warna.value.text, Integer.parseInt(storage.value.text),
                                tanggalRilis.value.text, sistemOperasi)
                        }
                    }
                    if (!isLoading.value) {
                        navController.navigate("pengelolaan-smartphone")
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
                model.value = TextFieldValue("")
                warna.value = TextFieldValue("")
                storage.value = TextFieldValue("")
                tanggalRilis.value = TextFieldValue("")
                setSistemOperasi(sistemOperasiOptions[0])
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
            viewModel.loadItem(id) { Smartphone ->
                Smartphone?.let {
                    model.value = TextFieldValue(Smartphone.model)
                    warna.value = TextFieldValue(Smartphone.warna)
                    storage.value = TextFieldValue(Smartphone.storage.toString())
                    tanggalRilis.value = TextFieldValue(Smartphone.tanggal_rilis)
                    setSistemOperasi(Smartphone.sistem_operasi)
                }
            }
        }
    }

    MaterialDialog(dialogState = tanggalDialogState, buttons = {
        positiveButton("OK")
        negativeButton("Batal")
    }) {
        datepicker { date ->
            tanggalRilis.value =
                TextFieldValue(date.format(DateTimeFormatter.ISO_LOCAL_DATE))
        }
    }
}
package ar.android.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.android.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                    UnitConverter()
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputText by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters")}
    val iExpended = remember { mutableStateOf(false) }
    val oExpended = remember { mutableStateOf(false) }
    var conversionFactor by remember { mutableStateOf(1.0) }
    var oconversionFactor by remember { mutableStateOf(1.0) }
    fun convertUnits(){
        val inputDouble=inputText.toDoubleOrNull() ?: 0.0
        val result = (inputDouble*conversionFactor /oconversionFactor *100).roundToInt()/100.0
        outputText=result.toString()
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            Text("Unit Converter")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            label = { Text(text = "Enter a value") },
            value = inputText,
            onValueChange = {
                inputText=it
                convertUnits()
            })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box(){
                Button(onClick = { iExpended.value=true }) {
                    Text(inputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpended.value, onDismissRequest = { iExpended.value=false }) {
                    DropdownMenuItem(text = {Text("Centimeters")}, onClick = {
                        iExpended.value=false
                        inputUnit="Centimeters"
                        conversionFactor=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("Meters")}, onClick = {
                        iExpended.value=false
                        inputUnit="Meters"
                        conversionFactor=1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("Feets")}, onClick = {
                        iExpended.value=false
                        inputUnit="Feet"
                        conversionFactor=0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("Milimeters")}, onClick = {
                        iExpended.value=false
                        inputUnit="Milimeters"
                        conversionFactor=0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(){
                Button(onClick = { oExpended.value=true }) {
                    Text(outputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpended.value, onDismissRequest = { oExpended.value=false }) {
                    DropdownMenuItem(text = {Text("Centimeters")}, onClick = {
                        oExpended.value=false
                        outputUnit="Centimeters"
                        oconversionFactor=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("Meters")}, onClick = {
                        oExpended.value=false
                        outputUnit="Meters"
                        oconversionFactor=1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("Feets")}, onClick = {
                        oExpended.value=false
                        outputUnit="Feet"
                        oconversionFactor=0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("Milimeters")}, onClick = {
                        oExpended.value=false
                        outputUnit="Milimeters"
                        oconversionFactor=0.001
                        convertUnits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result : ${outputText} ${outputUnit}")
    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}
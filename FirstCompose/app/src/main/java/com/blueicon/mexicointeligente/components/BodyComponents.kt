package com.blueicon.mexicointeligente.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.enums.TypeKeyboard
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@Composable
fun TitleView(title: String, subtitle: String) {
    Text(
        text = title,
        fontSize = 32.sp,
        fontFamily = openSansFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Left,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp)
    )

    Text(
        text = subtitle,
        fontSize = 32.sp,
        fontFamily = openSansFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Left,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, bottom = 50.dp)
    )
}

@Composable
fun labelView(name: String) {
    Text(
        text = name,
        fontSize = 18.sp,
        fontFamily = openSansFamily,
        fontWeight = FontWeight.SemiBold,
        color = Color(red = 164, green = 0, blue = 41, alpha = 255),
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@ExperimentalMaterial3Api
@Composable
fun borderEditText() {
    var textoIngresado by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val colors = TextFieldDefaults.textFieldColors()

    TextField(
        value = textoIngresado, // Valor actual del campo
        onValueChange = { nuevoTexto -> // Callback cuando el texto cambia
            textoIngresado = nuevoTexto
        },
        label = { Text("Escribe algo aquí") }, // Texto de sugerencia (placeholder)
        // Otros parámetros: modifier, keyboardOptions, visualTransformation, etc.
        modifier = Modifier
            .border(1.dp, Color.LightGray, CircleShape)
            .indicatorLine(
                enabled = true,
                isError = false,
                colors = colors,
                interactionSource = interactionSource,
                focusedIndicatorLineThickness = 0.dp,
                unfocusedIndicatorLineThickness = 0.dp
            )
    )
}

@ExperimentalMaterial3Api
@Composable
fun editText(isPassword: Boolean, type: TypeKeyboard, title: String, value: String, onValueChange: (String) -> Unit) {

    var typeToAsign = KeyboardType.Text
    var capitalizationToAsign = KeyboardCapitalization.None

    when (type) {
        TypeKeyboard.NUMERIC -> {
            typeToAsign = KeyboardType.Number
        }
        TypeKeyboard.EMAIL -> {
            typeToAsign = KeyboardType.Email
        }
        TypeKeyboard.PASSWORD -> {
            typeToAsign = KeyboardType.Password
        }
        else -> { // Opción default
            capitalizationToAsign = KeyboardCapitalization.Words
        }
    }

    Column {

        labelView(name = title)

        BasicTextField(
            value = value,
            onValueChange = onValueChange, // This lambda updates the state in the parent
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            singleLine = true, // Optional: Restrict to a single line,
            cursorBrush = SolidColor(Color.Black),
            keyboardOptions = KeyboardOptions(
                keyboardType = typeToAsign,
                capitalization = capitalizationToAsign
            ),
            visualTransformation = if (!isPassword) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = TextStyle(fontSize = 16.sp, fontFamily = openSansFamily, fontWeight = FontWeight.Normal)
        )

        Divider(
            color = Color.Gray, // Your desired color
            thickness = 1.dp
        )
    }
}

@Composable
fun Space() {
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun btnGradient(name: String, backColor: Color, colorText: Color, onClick:() -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = colorText,
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 32.dp, end = 32.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(red = 164, green = 0, blue = 41, alpha = 255),
                        Color(red = 41, 18, 44, alpha = 255),
                    )
                ), shape = ButtonDefaults.shape
            )
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            color = colorText,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun btnNormal(name: String, backColor: Color, colorText: Color, onClick:() -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = colorText,
            containerColor = backColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(start = 32.dp, end = 32.dp)

    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            color = colorText,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
fun btnOutline(name: String, outlineColor: Color, colorText: Color, onClick:() -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colorText
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 32.dp, end = 32.dp),
        shape = RoundedCornerShape(35.dp), // Custom shape
        border = BorderStroke(1.dp, outlineColor), // Custom border stroke
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            color = colorText,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun imageGral() {
    Box (
        modifier = Modifier.fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 48.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.deporte), // Reference your image resource
            contentDescription = "A description of the image", // Mandatory for accessibility
            modifier = Modifier.size(128.dp)
                .fillMaxWidth()
            // Optional parameters: contentScale, colorFilter, alpha, etc.
        )
    }
}

@Composable
fun CustomMarkerImage(
    imageId: Int, // ID del recurso drawable (ej. R.drawable.ic_marker)
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        modifier = modifier
            .size(48.dp) // Ajusta el tamaño según necesites
            .clip(CircleShape), // Ejemplo: para un ícono circular
        contentScale = ContentScale.Fit // Cómo se ajusta la imagen
    )
}

@Composable
fun backBtn(image: Int, onClick:() -> Unit) {
    IconButton(
        onClick = onClick,
        enabled = true,
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp)
    ) {
        Icon(
            painter = painterResource(id = image),
            contentDescription = "Icono",
            modifier = Modifier
                .size(35.dp)
        )
    }
}

@Composable
fun backBtnToolbar(image: Int, onClick:() -> Unit) {
    IconButton(
        onClick = onClick,
        enabled = true,
        modifier = Modifier
            .padding(top = 4.dp, start = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = image),
            contentDescription = "Icono",
            modifier = Modifier
                .size(35.dp)
        )
    }
}

@Composable
fun inputField(label: String, value: String, type: TypeKeyboard, onValueChange: (String) -> Unit) {

    var typeToAsign = KeyboardType.Text
    var capitalizationToAsign = KeyboardCapitalization.None

    when (type) {
        TypeKeyboard.NUMERIC -> {
            typeToAsign = KeyboardType.Number
        }
        TypeKeyboard.EMAIL -> {
            typeToAsign = KeyboardType.Email
        }
        TypeKeyboard.PASSWORD -> {
            typeToAsign = KeyboardType.Password
        }
        else -> { // Opción default
            capitalizationToAsign = KeyboardCapitalization.Words
        }
    }

    Column (
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 14.dp)
    )
    {

        Text(
            text = label,
            fontSize = 14.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            //cursorBrush = SolidColor(Color.Black),
            keyboardOptions = KeyboardOptions(
                keyboardType = typeToAsign,
                capitalization = capitalizationToAsign
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.Gray
                //containerColor = Color.White
            ),
            textStyle = TextStyle(
                fontSize = 14.sp, fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun genericMenu(opciones: List<String>, onOptionSelected: (String) -> Unit)
{
    val opciones = opciones

    var expandido by remember { mutableStateOf(false) }
    var textoSeleccionado by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expandido,
        onExpandedChange = { expandido = !expandido },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
    ) {
        OutlinedTextField(
            value = textoSeleccionado,
            onValueChange = {},
            shape = RoundedCornerShape(12.dp),
            readOnly = true,
            label = {
                Text(
                    "Selecciona una opción",
                    fontSize = 14.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Normal,
                )},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido) },
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.Gray
            ),
        )

        ExposedDropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false },
            modifier = Modifier.background(Color.White)
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = {
                        Text(
                            opcion,
                            fontSize = 12.sp,
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ) },
                    onClick = {
                        textoSeleccionado = opcion // Actualiza la UI local
                        expandido = false
                        onOptionSelected(opcion) // <--- Regresa el valor al padre
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun genericCenterAlignedTopAppBar(title: String, navController: NavController)
{
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface, // Color del texto
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface// Iconos
        ),
        navigationIcon = {
            backBtnToolbar(R.drawable.atras) {
                navController.popBackStack()
            }
        }
    )
}
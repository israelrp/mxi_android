package com.blueicon.mexicointeligente.business.myearnings

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyearningsView(navController: NavController, myearningsViewModel: MyearningsViewModel) {
    Scaffold(

    ) {
        ContentMyearningsView(navController, myearningsViewModel)
    }
}

@Composable
fun ContentMyearningsView(navController: NavController, myearningsViewModel: MyearningsViewModel) {

    var typeSelected by remember { mutableStateOf("Activas") }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val allItems by myearningsViewModel.itemsOperations.collectAsState()
    val filtered = allItems.filter { it.typeOpe.name.contains(typeSelected) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
            .background(Color.White)
    )
    {

        Column(
            modifier = Modifier
                .fillMaxWidth()
            //.verticalScroll(scrollState)
        )
        {

            val titles = listOf("Activas", "Cerradas", "Renovaciones")

            //Tab
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                    .fillMaxWidth()
            )
            {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = Color.White, // Fondo claro como en la imagen
                    contentColor = Color.Black, // Color del texto
                    indicator = { tabPositions ->
                        // Línea roja personalizada debajo de la pestaña seleccionada
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                            height = 4.dp,
                            color = colorResource(id = R.color.redTitles)
                        )
                    },
                    divider = {} // Eliminamos la línea divisoria gris por defecto para un look más limpio
                ) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                Text(
                                    text = title,
                                    fontSize = 13.sp,
                                    fontFamily = openSansFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = if (selectedTabIndex == index) Color.Black else Color.Black
                                )
                            }
                        )
                    }
                }

                // Contenido que cambia según la pestaña
                when (selectedTabIndex) {
                    0 -> typeSelected = "Activas"
                    1 -> typeSelected = "Cerradas"
                    2 -> typeSelected = "Renovaciones"
                }
            }

            searchBar(typeSelected)

            Text(
                text = "$typeSelected",
                fontSize = 20.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 32.dp, bottom = 8.dp)
                    .fillMaxWidth()
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                items(filtered, key = { it.id }) { item ->
                    operationRowView(item, navController)
                }
            }

        }//End Column

    }//End Box

}

@Composable
fun searchBar(moveToSearch: String) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(16.dp)
            .padding(start = 24.dp, end = 24.dp, top = 12.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(12.dp))
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.search), // Reference your image resource
                        contentDescription = null, // Mandatory for accessibility
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    if (text.isEmpty()) {
                        Text(
                            text = "Buscar en ${moveToSearch}",
                            fontSize = 14.sp,
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.graySearch),
                        )
                    }

                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun operationRowView(item: OperationsItem, navController: NavController) {

    var statusOpeBackcolor = colorResource(id = R.color.black)

    Box(
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Card(
            shape = RoundedCornerShape(15.dp), // Rounded corners
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Shadow
            //border = BorderStroke(2.dp, Color.Blue), // Border
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 16.dp)
                .fillMaxWidth() // Ajusta el ancho al máximo disponible
                .wrapContentHeight(), // EL AJUSTE DINÁMICO
        )
        {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )
            {
                Row(
                    modifier = Modifier
                        .padding(bottom = 8.dp, top = 8.dp, start = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                )
                {

                    statusOpeView(R.drawable.proteccion, "Protección", colorResource(id = R.color.blueProteccion))

                    when (item.operationalStatus) {
                        "Registrada" -> {
                            statusOpeView(
                                R.drawable.registrada,
                                item.operationalStatus,
                                colorResource(id = R.color.grayRegistrada)
                            )
                        }
                        "Habitacional" -> {
                            statusOpeView(
                                R.drawable.propietario,
                                item.operationalStatus,
                                colorResource(id = R.color.yellowHab)
                            )
                        }
                        "LocalComercial" -> {
                            statusOpeView(R.drawable.local, "Local comercial", colorResource(id = R.color.cardExp))
                        }
                        "Vigente" -> {
                            statusOpeView(R.drawable.comprobado, item.operationalStatus, colorResource(id = R.color.greenVigente))
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = {

                        },
                        enabled = true
                    )
                    {
                        Icon(
                            painter = painterResource(R.drawable.editar),
                            contentDescription = "Icono",
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                }//End Row

                /*stepsView(item.step)

                progressBarView((item.step.toFloat() / 7.toFloat()))*/

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Column(
                        modifier = Modifier.weight(1f)
                    )
                    {

                        if (item.typeOpe.name == "Activas") {
                            infoView(R.drawable.identificador, "ID", item.operationsId)
                        } else {
                            infoView(R.drawable.identificador, "ID / Contrato", "${item.operationsId} / ${item.contract}")
                        }

                        infoView(R.drawable.home, "Dirección", item.address)

                        if (item.typeOpe.name == "Cerradas") {
                            infoView(R.drawable.propietario, "Propietario", item.landlord)
                        }

                        infoView(R.drawable.inquilino, "Inquilino", item.tenant)

                        if (item.status == "Disponible") {
                            infoView(R.drawable.comprobado, "Contratos", "Disponible para revisión")
                        } else {
                            infoView(R.drawable.alerta, "Perfil de inquilino", item.status)
                        }

                        if (item.typeOpe.name == "Cerradas") {
                            infoView(R.drawable.recargar, "Fecha de renovación", item.dateRenovation)
                        }

                    }//End Column

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    )
                    {
                        Text(
                            text = "Pasos completados",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.black),
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        stepProgressIndicator(item.step, 7)
                    }

                }//End Row

                Button(
                    onClick = {
                        Log.e("OnboardingView", "Llendo a configuracion")
                        /*CoroutineScope(Dispatchers.IO).launch {
                            dataStores.saveStatusOnboarding(true)
                        }*/
                        navController.navigate("EarningDetail/${item.step}")
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = colorResource(id = R.color.white),
                        containerColor = colorResource(id = R.color.redTitles)
                    ),
                    modifier = Modifier
                        .padding(bottom = 24.dp, top = 24.dp)
                        .align(Alignment.CenterHorizontally)
                        .width(180.dp)
                        .height(40.dp)
                )
                {
                    Text(
                        text = "Ver expediente",
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.white),
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                /*ClickableText (
                    text = AnnotatedString("Ver expediente"),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.redTitles),
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                    ),
                    onClick = {
                        Log.e("OnboardingView", "Llendo a configuracion")
                        /*CoroutineScope(Dispatchers.IO).launch {
                            dataStores.saveStatusOnboarding(true)
                        }*/
                        navController.navigate("EarningDetail/${item.step}")
                    },
                    modifier = Modifier
                        .padding(bottom = 24.dp, top = 8.dp)
                        .fillMaxWidth()
                )*/

            }//End Column

        }//Card

    }//End Column
}

@Composable
fun statusOpeView(image: Int, title: String, backGroundColor: Color) {

    Row(
        modifier = Modifier
            .background(backGroundColor, shape = RoundedCornerShape(20.dp))
            .padding(top = 2.dp, bottom = 2.dp, start = 24.dp, end = 24.dp)
    )
    {

        /*Image(
            painter = painterResource(image), // Reference your image resource
            contentDescription = null, // Mandatory for accessibility
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp)
                .size(16.dp)
        )*/

        Text(
            text = title,
            fontSize = 12.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp, start = 4.dp)
        )
    }

}

@Composable
fun stepsView(setps: Int) {

    Row(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
            .fillMaxWidth()
    )
    {
        Text(
            text = "$setps / 7",
            fontSize = 12.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.redTitles),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .wrapContentSize()
        )

        Text(
            text = "Pasos completados",
            fontSize = 12.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp, start = 8.dp)
                .wrapContentSize()
        )

        Spacer(modifier = Modifier.weight(1f))

        ClickableText (
            text = AnnotatedString("Editar"),
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.redTitles),
                textAlign = TextAlign.Center
            ),
            onClick = {
                Log.e("OnboardingView", "Llendo a configuracion")
                /*CoroutineScope(Dispatchers.IO).launch {
                    dataStores.saveStatusOnboarding(true)
                }*/

                //navController.navigate("PhoneNumber")
            },
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .wrapContentSize()
                .weight(0.4f)
        )
    }
}

@Composable
fun progressBarView(progress: Float) {
    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
            .fillMaxWidth()
            .height(10.dp)
            .clip(CircleShape),
        color = colorResource(id = R.color.redTitles),
        trackColor = colorResource(id = R.color.grayProgress),
    )
}

@Composable
fun infoView(image: Int, title: String, value: String) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 16.dp, end = 8.dp, bottom = 16.dp)
            .fillMaxWidth()
    )
    {

        Image(
            painter = painterResource(image), // Reference your image resource
            contentDescription = null, // Mandatory for accessibility
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(colorResource(R.color.redTitles)),
            modifier = Modifier
                .size(20.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .wrapContentSize()
        )
        {

            Text(
                text = title,
                fontSize = 13.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .fillMaxWidth()
            )

            Text(
                text = value,
                fontSize = 13.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.grayText),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .fillMaxWidth()
            )

        }

    }

}

@Composable
fun stepProgressIndicator(pasoActual: Int, totalPasos: Int) {
    val progreso = pasoActual.toFloat() / totalPasos

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(90.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 10.dp.toPx()

            // 1. Dibujar el círculo de fondo (Gris)
            drawCircle(
                color = Color.LightGray,
                style = Stroke(width = strokeWidth),
                radius = (size.minDimension / 1.8 - strokeWidth / 1.8).toFloat()
            )

            // 2. Dibujar el arco de progreso (Rojo)
            drawArc(
                color = Color(0xFFB01C21), // Color rojo de la imagen
                startAngle = -90f, // Empieza arriba
                sweepAngle = 360f * progreso,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        // 3. Texto central
        Text(
            text = "$pasoActual/$totalPasos",
            fontSize = 22.sp,
            color = colorResource(id = R.color.black),
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
        )
    }
}
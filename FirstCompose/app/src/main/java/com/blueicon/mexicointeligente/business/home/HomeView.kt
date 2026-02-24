package com.blueicon.mexicointeligente.business.home

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, viewModel: HomeViewModel) {
    Scaffold(

    ) {
        ContentHomeView(navController, viewModel)
    }
}

@Composable
fun ContentHomeView(navController: NavController, viewModel: HomeViewModel) {

    var isLoading by remember { mutableStateOf(false) }
    var pageTitle by remember { mutableStateOf("Cargando...") }
    val scrollState = rememberScrollState()
    val listOperations by viewModel.itemsOperations.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
            // Apply the verticalScroll modifier
            //.verticalScroll(scrollState)
        ) {
            Text(
                text = "¡Bienvenido!",
                fontSize = 22.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 20.dp)
                    .fillMaxWidth()
            )

            //Primeras cards
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    menuCard(
                        value = listOperations[0],
                        modifier = Modifier.weight(1f)
                    )

                    menuCard(
                        value = listOperations[1],
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                menuCard(
                    value = listOperations[2],
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Text(
                text = "Resumen de operaciones",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 24.dp)
                    .fillMaxWidth()
            )

            //Segundas cards
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            )
            {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                )
                {
                    // Columna Izquierda: Activas (más grande)
                    operacionCardBig(
                        title = "Activas",
                        valor = "8",
                        color = colorResource(id = R.color.greenStatus), // Verde
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )

                    // Columna Derecha: Cerradas y Renovaciones
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        operacionCard(
                            title = "Cerradas",
                            valor = "51",
                            color = colorResource(id = R.color.blueProteccion), // Gris azulado
                            modifier = Modifier.weight(1f)
                        )

                        operacionCard(
                            title = "Renovaciones",
                            valor = "0",
                            color = colorResource(id = R.color.redTitles), // Rojo
                            modifier = Modifier.weight(1f)
                        )
                    }//End Column

                }//End Row
            }

            // Add content dynamically or statically
            /*LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                items(listOperations, key = { it.id }) { item ->
                    actionsContent(item)
                }
            }

            operationsResumeContent("8", "51", "0")*/
        }

        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun actionsContent(value: OperationsHome) {

    var cardColor = Color.White
    var cardTextColor = Color.White

    when (value.id) {
        1 -> {
            cardColor = colorResource(id = R.color.cardExp)
            cardTextColor = colorResource(id = R.color.cardTextExp)
        }

        2 -> {
            cardColor = colorResource(id = R.color.cardInv)
            cardTextColor = colorResource(id = R.color.cardTextInv)
        }

        else -> {
            cardColor = colorResource(id = R.color.cardPolizas)
            cardTextColor = colorResource(id = R.color.cardTextPolizas)
        }
    }

    Card(
        shape = RoundedCornerShape(10.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Shadow
        //border = BorderStroke(2.dp, Color.Blue), // Border
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .padding(top = 8.dp, bottom = 8.dp)
            .height(85.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(cardColor)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Spacer(modifier = Modifier.weight(0.3f))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(60.dp) // Set a size (width and height must be equal for a perfect circle)
                        .clip(CircleShape) // Clip the composable to a circle shape
                        .background(Color.White) // Apply the background color
                ) {
                    Image(
                        painter = painterResource(id = value.imagen), // Reference your image resource
                        contentDescription = "A description of the image", // Mandatory for accessibility
                        contentScale = ContentScale.FillHeight,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(width = 30.dp, height = 30.dp)
                    )
                }

                Text(
                    text = value.title,
                    fontSize = 13.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = cardTextColor,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .wrapContentWidth()
                )

                Spacer(modifier = Modifier.weight(3f))
            }
        }
    }
}

@Composable
fun operationsResumeContent(countActive: String, countClose: String, countRenovation: String) {

    Column {

        Text(
            text = "Resumen de operaciones",
            fontSize = 16.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 16.dp, top = 24.dp)
                .fillMaxWidth()
        )

        Card(
            shape = RoundedCornerShape(15.dp), // Rounded corners
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Shadow
            //border = BorderStroke(2.dp, Color.Blue), // Border
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxWidth() // Ajusta el ancho al máximo disponible
                .wrapContentHeight(), // EL AJUSTE DINÁMICO
        )
        {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color.White)
                    .height(125.dp)
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    operationResumeItem(
                        countActive,
                        "Activas",
                        R.drawable.check,
                        Modifier.weight(1f)
                    )

                    VerticalDivider(
                        modifier = Modifier.height(110.dp),
                        1.dp,
                        color = Color.LightGray
                    )

                    operationResumeItem(
                        countClose,
                        "Cerradas",
                        R.drawable.folder,
                        Modifier.weight(1f)
                    )

                    VerticalDivider(
                        modifier = Modifier.height(110.dp),
                        1.dp,
                        color = Color.LightGray
                    )

                    operationResumeItem(
                        countRenovation,
                        "Renovaciones",
                        R.drawable.recargar,
                        Modifier.weight(1f)
                    )

                }
            }
        }

    }
}

@Composable
fun operationResumeItem(value: String, title: String, icon: Int, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Image(
            painter = painterResource(id = icon), // Reference your image resource
            contentDescription = "A description of the image", // Mandatory for accessibility
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(width = 23.dp, height = 23.dp)
        )

        Text(
            text = value,
            fontSize = 20.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 8.dp)
        )

        Text(
            text = title,
            fontSize = 12.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Start,
            modifier = Modifier
            //.padding(start = 24.dp, end = 24.dp, bottom = 16.dp, top = 24.dp)
        )
    }

}

@Composable
fun menuCard(value: OperationsHome, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(15.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Shadow
        modifier = modifier.height(90.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = value.imagen), // Reference your image resource
                contentDescription = "A description of the image", // Mandatory for accessibility
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(colorResource(R.color.red)),
                modifier = Modifier
                    .size(width = 30.dp, height = 30.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = value.title,
                fontSize = 13.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.redTitles),
            )
        }
    }
}

@Composable
fun operacionCard(title: String, valor: String, color: Color, modifier: Modifier = Modifier)
{
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Shadow
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = valor,
                fontSize = 24.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Bold,
                color = color,
            )

            Text(
                text = title,
                fontSize = 14.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = color,
            )
        }
    }
}

@Composable
fun operacionCardBig(title: String, valor: String, color: Color, modifier: Modifier = Modifier)
{
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Shadow
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = valor,
                fontSize = 58.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Bold,
                color = color,
            )

            Text(
                text = title,
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = color,
            )
        }
    }
}
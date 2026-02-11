package com.blueicon.mexicointeligente.business.enrolment.onboarding

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.blueicon.mexicointeligente.components.btnNormal
import com.blueicon.mexicointeligente.ui.theme.openSansFamily
import com.blueicon.mexicointeligente.utils.StorePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingView(navController: NavController) {

    Scaffold {
        ContentOnboardingView(navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun ContentOnboardingView(navController: NavController) {

    var items = ArrayList<DataPage>()
    val context = LocalContext.current
    val dataStore = StorePreferences(context)

    items.add(DataPage(R.drawable.cell, "Agiliza y asegura el cierre de tus rentas", "Accede a productos que se adecuen a los requerimientos de tus operaciones"))
    items.add(DataPage(R.drawable.cell, "Centraliza y optimiza tus operaciones", "Visibiliza el proceso de tus operaciones y aumenta tus ingreso"))
    items.add(DataPage(R.drawable.cell, "Lleva tu carrera inmobiliaria al \nsiguiente nivel", "Encuentra materiales exclusivos y cierra la renta perfecta"))

    val pagerState = rememberPagerState(
        pageCount = { 3 },
        initialPage = 0
    )

    OnBoardingPager(item = items, pagerState = pagerState, modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
        navController = navController,
        dataStore
    )
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun OnBoardingPager(
    item: List<DataPage>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    navController: NavController,
    dataStores: StorePreferences
) {
    Box(modifier = modifier) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            ClickableText (
                text = AnnotatedString("Omitir"),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.redTitles),
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                    Log.e("OnboardingView", "Omitiendo onboarding")
                    CoroutineScope(Dispatchers.IO).launch {
                        dataStores.saveStatusOnboarding(true)
                    }

                    navController.navigate("PhoneNumber") {
                        popUpTo(0)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 24.dp, bottom = 30.dp, top = 16.dp)
            )

            HorizontalPager(state = pagerState) { page ->

                Column(
                    modifier = Modifier
                        //.background(Color.Green)
                        .padding(start = 16.dp, end = 16.dp)
                        //.fillMaxSize()
                ) {
                    //Imagen del cel y su sombra
                    Box(modifier = modifier
                        .padding(top = 40.dp), contentAlignment = Alignment.Center) {

                        Box(modifier = Modifier
                                .offset(x = 4.dp, y = 4.dp)
                                .shadow(elevation = 10.dp, shape = RoundedCornerShape(15.dp), clip = false, spotColor = Color.Black)
                                .size(190.dp, height = 360.dp))

                        Image(
                            painter = painterResource(id = item[page].image), // Reference your image resource
                            contentDescription = "A description of the image", // Mandatory for accessibility
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                //.offset(x = 100.dp, y = 5.dp)
                                //.shadow(elevation = 10.dp, shape = RoundedCornerShape(15.dp), clip = false)
                                .size(260.dp, height = 360.dp)
                                //.shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp), ambientColor = Color.Red, spotColor = Color.Yellow)
                                //.align(alignment = Alignment.CenterHorizontally)
                            // Optional parameters: contentScale, colorFilter, alpha, etc.
                        )
                    }

                    Text(
                        modifier = Modifier
                            //.background(Color.Red)
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, top = 40.dp),
                        text = item[page].title,
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = item[page].description,
                        fontSize = 15.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp)
                    )

                }

            }//End HorizontalPager

            IndicatorPage(currentPage = pagerState.currentPage)

            if (pagerState.currentPage == 2) {
                Box(
                    modifier = Modifier.padding(top = 32.dp, start = 32.dp, end = 32.dp)
                ) {
                    btnNormal(
                        name = "Ir al login",
                        backColor = colorResource(id = R.color.red),
                        colorText = Color.White
                    ) {

                        CoroutineScope(Dispatchers.IO).launch {
                            dataStores.saveStatusOnboarding(true)
                        }

                        navController.navigate("PhoneNumber") {
                            popUpTo(0)
                        }
                    }
                }
            }

        }//End Column

    }//End Box
}

@ExperimentalMaterial3Api
@Composable
fun IndicatorPage(currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            //.background(Color.Blue)
            .padding(top = 40.dp),
    ) {
        repeat(3) {
            Indicator(isSelect = it == currentPage)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun Indicator(isSelect: Boolean) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(15.dp)
            .width(15.dp)
            .clip(CircleShape)
            .background(if (isSelect) Color.Red else Color.Gray)
    )
}
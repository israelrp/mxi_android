package com.blueicon.mexicointeligente.views

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeWebView() {
    Scaffold(
        /*floatingActionButton = {
            ActionButton()
        }*/
    ) {
        ContentHomeWebView()
    }
}

@Composable
fun ContentHomeWebView() {

    var isLoading by remember { mutableStateOf(false) }
    var pageTitle by remember { mutableStateOf("Cargando...") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp, top = 65.dp)
    ) {

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.textZoom = 110

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                            //isLoading = true
                            //pageTitle = "Cargando..."

                            Log.e("onPageStarted", "WebView URL: $url")

                            if (url != null) {
                                if (url.contains("http://welcome/?")) {

                                } else if (url.contains("https://grp.volkswagenag.com/pkmslogout")) {

                                }
                            }
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            //isLoading = false
                            //pageTitle = view?.title ?: "Contenido Web"
                        }

                        // Controla si se intercepta la URL para cargarla dentro del WebView
                        // o abrirla en un navegador externo.
                        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                            url?.let { view?.loadUrl(it) }
                            return true // Devuelve true para que la navegación ocurra dentro del WebView
                        }

                        // Puedes sobrescribir onReceivedError para manejar errores de carga.
                    }
                    loadUrl("https://grp.volkswagenag.com/isam/sps/authsvc/policy/grpuidpw?Target=https://grp.volkswagenag.com/isam/sps/auth")
                }
            },
            update = { webView ->
                // Lógica de actualización si es necesario
            }
        )

        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxSize())
        }
    }
}
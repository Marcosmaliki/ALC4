package com.maliki.alc4phase1

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*
import spencerstudios.com.bungeelib.Bungee
import android.widget.Toast
import android.webkit.WebView
import android.webkit.WebViewClient




class About : AppCompatActivity() {
   private val aboutPageLink:String = "https://andela.com/alc/"
   private val mindPageLink:String = "https://mindorks.com/"
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        toolbar.setNavigationOnClickListener {
            finish()
            Bungee.zoom(this)
        }
//        webViewSuite.startLoading(aboutPageLink)
//        webview.webViewClient = WebViewSslTolerant()
        webview.loadUrl(aboutPageLink)
        webview.settings.javaScriptEnabled = true

        webview.measure(100, 100)
        webview.settings.useWideViewPort = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.domStorageEnabled = true

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                progressBar.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Toast.makeText(this@About, "Error:$description", Toast.LENGTH_SHORT).show()

            }
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed() // Ignore SSL certificate errors
            }
        }
    }
//    override fun onBackPressed() {
//        if (!webViewSuite.goBackIfPossible()) super.onBackPressed()
//    }


}

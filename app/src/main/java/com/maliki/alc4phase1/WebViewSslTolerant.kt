package com.maliki.alc4phase1

import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient

 class WebViewSslTolerant : WebViewClient() {

    override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
        handler.proceed() // Ignore SSL certificate errors
    }

}
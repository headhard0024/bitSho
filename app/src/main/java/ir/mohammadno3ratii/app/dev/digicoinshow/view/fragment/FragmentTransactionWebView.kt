package ir.mohammadno3ratii.app.dev.digicoinshow.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import ir.mohammadno3ratii.app.dev.digicoinshow.databinding.FragmentTransactionWebViewBinding


class FragmentTransactionWebView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentTransactionWebViewBinding.inflate(inflater, container, false)
        val symbol = arguments!!.getString("symbol")
        val coinName = arguments!!.getString("name")

        view.txtTitle.text = "نمودار معاملات $coinName"

        val webSettings = view.webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        view.webView.webViewClient = WebViewClient()
        view.webView.loadUrl("https://www.tradingview.com/chart/?symbol=BINANCE%3A${symbol}USDT")

        view.imgBack.setOnClickListener {
            activity!!.onBackPressed()
        }

        return view.root
    }

}
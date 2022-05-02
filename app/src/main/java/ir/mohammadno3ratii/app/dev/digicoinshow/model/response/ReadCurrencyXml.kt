package ir.mohammadno3ratii.app.dev.digicoinshow.model.response

import android.app.Activity
import ir.mohammadno3ratii.app.dev.digicoinshow.base.XmlParser
import java.util.concurrent.Executors


abstract class ReadCurrencyXml {
    fun read(activity: Activity, url: String) {
        Executors.newSingleThreadExecutor().submit(Runnable {
            val reader =
                XmlParser(url)
            activity.runOnUiThread {
                val rssItems = reader.items
                onSuccess(rssItems)
            }
        })
    }

    abstract fun onSuccess(itemList: ArrayList<XmlParser.Item>?)
}
package ir.mohammadno3ratii.app.dev.digicoinshow.view.activity

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageInfo
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatDrawableManager
import ir.mohammadno3ratii.app.dev.digicoinshow.base.ApiConnection
import ir.mohammadno3ratii.app.dev.digicoinshow.base.G
import ir.mohammadno3ratii.app.dev.digicoinshow.base.G.context
import ir.mohammadno3ratii.app.dev.digicoinshow.base.XmlParser
import ir.mohammadno3ratii.app.dev.digicoinshow.databinding.ActivitySplashBinding
import ir.mohammadno3ratii.app.dev.digicoinshow.di.AppComponent
import ir.mohammadno3ratii.app.dev.digicoinshow.di.DaggerAppComponent
import ir.mohammadno3ratii.app.dev.digicoinshow.model.calzz.CoinExchange
import ir.mohammadno3ratii.app.dev.digicoinshow.model.response.ReadCurrencyXml
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class ActivitySplazh : AppCompatActivity() {

    private var packageInfo: PackageInfo? = null
    lateinit var component: AppComponent

    @Inject
    lateinit var connection: ApiConnection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component = DaggerAppComponent.factory().create(this)
        component.ACTIVITY_SPLASH(this)

        binding.txtDate.text = "${G.currentPersianTime.dayName()} ${G.currentPersianTime.shDay.toString()} ${G.currentPersianTime.monthName()} ${G.currentPersianTime.shYear.toString()}"

        checkInternetStatus(binding)

        /** in api lover that level 21 can use image drawable refrence in xml */
        @SuppressLint("RestrictedApi")
        val iconReloadId =
            AppCompatDrawableManager.get().getDrawable(context, ir.mohammadno3ratii.app.dev.digicoinshow.R.drawable.ic_reload)
        binding.btnInternetCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(iconReloadId, null, null, null)

        binding.btnInternetCheck.setOnClickListener {
            checkInternetStatus(binding)
        }

        packageInfo = packageManager.getPackageInfo(packageName, 0)
        binding.txtVersion.text = "نسخه ${packageInfo!!.versionName}"

    }

    private fun checkInternetStatus(binding: ActivitySplashBinding) {
        /** first check internet status and set layout fail or start read apis */
        if (internetIsAvailable()) {
            connectionVisibleLayoutStatus(binding)
            readApis(binding)
        } else {
            connectionFailLayoutStatus(binding)
        }
    }

    private fun internetIsAvailable(): Boolean {
        /** return internet status  */
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val connectByWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val connectByData = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        return connectByWifi != null && connectByData != null && connectByWifi.isConnected or connectByData.isConnected
    }


    private fun readApis(binding: ActivitySplashBinding) {
        object : ReadCurrencyXml() {
            override fun onSuccess(itemList: ArrayList<XmlParser.Item>?) {
                if (itemList != null) {
                    itemList.forEach { item ->
                        var toman = item.price.replace(",", "").toInt()
                        toman /= 10
                        item.price = toman.toString()
                    }
                    G.exchangeList = itemList
                    readCoinApiResponse(binding)
                } else {
                    connectionFailLayoutStatus(binding)
                    return
                }
            }
        }.read(this, "https://parsijoo.ir/api?serviceType=price-API&query=Currency")
    }

    private fun readCoinApiResponse(binding: ActivitySplashBinding) {
        connection.coinExchangeResponse().enqueue(object :
            retrofit2.Callback<CoinExchange> {
            override fun onResponse(
                call: Call<CoinExchange>,
                response: Response<CoinExchange>
            ) {
                G.coinList = response.body()
                startActivity(Intent(this@ActivitySplazh, ActivityMain::class.java))
                finish()
            }

            override fun onFailure(call: Call<CoinExchange>, t: Throwable) {
                connectionFailLayoutStatus(binding)
                return
            }
        })
    }

    private fun connectionVisibleLayoutStatus(binding: ActivitySplashBinding) {
        /** if internet connection is available show this  */
        binding.layConnectionFail.visibility = View.GONE
        binding.layConnectionIsTrue.visibility = View.VISIBLE
    }

    private fun connectionFailLayoutStatus(binding: ActivitySplashBinding) {
        /** if internet connection is not available show this  */
        binding.layConnectionFail.visibility = View.VISIBLE
        binding.layConnectionIsTrue.visibility = View.GONE
    }

//    private fun changeicon() {
//        // disable old icon
//        val manager = packageManager
//        manager.setComponentEnabledSetting(
//            ComponentName(this@ActivitySplash,"ir.mohammadno3ratii.app.dev.digicoinshow.view.activity.ActivitySplash"),
//            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//            PackageManager.DONT_KILL_APP
//        )
//
//        // enable new icon
//        manager.setComponentEnabledSetting(
//            ComponentName(this@ActivitySplash, "ir.mohammadno3ratii.app.dev.digicoinshow.Alias"),
//            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//            PackageManager.DONT_KILL_APP
//        )
//        Toast.makeText(this@ActivitySplash, "Enable Old Icon", Toast.LENGTH_LONG).show()
//    }

}
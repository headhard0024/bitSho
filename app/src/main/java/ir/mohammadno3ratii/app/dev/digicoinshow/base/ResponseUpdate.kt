package ir.mohammadno3ratii.app.dev.digicoinshow.base

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * use rxJava for auto update items in seconds time That set form viewmodel
 */
abstract class ResponseUpdate {
    @SuppressLint("CheckResult")
    fun upDate(secondsInterval:Long) {
        val fetchWeatherInterval: Observable<Long>? = Observable
                .interval(secondsInterval, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        fetchWeatherInterval?.subscribe {
            upDate()
        }
    }

    abstract fun upDate()
}
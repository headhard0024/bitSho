package ir.mohammadno3ratii.app.dev.digicoinshow.base

import ir.mohammadno3ratii.app.dev.digicoinshow.model.calzz.CoinExchange
import retrofit2.Call
import retrofit2.http.GET

interface ApiConnection {

    @GET("tickers")
    fun coinExchangeResponse(): Call<CoinExchange>

}
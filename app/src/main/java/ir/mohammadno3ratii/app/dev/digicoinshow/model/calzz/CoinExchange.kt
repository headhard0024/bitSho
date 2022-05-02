package ir.mohammadno3ratii.app.dev.digicoinshow.model.calzz

import com.google.gson.annotations.SerializedName

data class CoinExchange(val data: ArrayList<Coin>, val info: Info) {

    inner class Info(val coins_num: Int, val time: Int)

    inner class Coin(
        val id: Int,
        val symbol: String,
        val name: String,
        val rank: Int,
        @SerializedName("price_usd")
        val price: Float,
        @SerializedName("percent_change_1h")
        val percentChangeOneHour: String,
        @SerializedName("percent_change_24h")
        val percentChangeOneDay: String,
        @SerializedName("percent_change_7d")
        val percentChangeSevenDay: String,
    )
}


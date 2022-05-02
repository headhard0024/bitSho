package ir.mohammadno3ratii.app.dev.digicoinshow.view.adapter

import android.content.Context
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mohammadno3ratii.app.dev.digicoinshow.R
import ir.mohammadno3ratii.app.dev.digicoinshow.base.G
import ir.mohammadno3ratii.app.dev.digicoinshow.base.GeneralHelper
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstant
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstantNumber
import ir.mohammadno3ratii.app.dev.digicoinshow.model.calzz.CoinExchange
import javax.inject.Inject


@Suppress("DEPRECATION")
class CoinAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    private var exchangeList = emptyList<CoinExchange.Coin>()
    private val coinExchangeBase = PreferenceManager.getDefaultSharedPreferences(context).getInt(PublicConstant.COIN_EXCHANGE_BASE.value, PublicConstantNumber.TOMAN_ID.value)

    fun setCoinList(list: ArrayList<CoinExchange.Coin>) {
        this.exchangeList = list
    }

    class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layRoot: LinearLayout = itemView.findViewById(R.id.layRoot)
        var txtName: TextView = itemView.findViewById(R.id.txtName)
        var txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        var txtSymbol: TextView = itemView.findViewById(R.id.txtSymbol)
        var txtPercentChange: TextView = itemView.findViewById(R.id.txtPercentChange)
        var txtPriceName: TextView = itemView.findViewById(R.id.txtPriceName)
        var imgLogo: ImageView = itemView.findViewById(R.id.imgLogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val myHolder =
            LayoutInflater.from(context).inflate(R.layout.coin_list_item, parent, false)
        return CoinViewHolder(myHolder)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = exchangeList[position]

        /**check exchange response is valid and receive run other codes**/
        if (G.exchangeList[0].price.toInt() == 0) {
            return
        }

        holder.txtName.text = item.name
        holder.txtSymbol.text = item.symbol

        val percentChange = item.percentChangeOneHour
        holder.txtPercentChange.text = percentChange

        /** set text color by price percent change positive or negative */
        if (percentChange.contains("-")) {
            holder.txtPercentChange.setTextColor(context.resources.getColor(R.color.redRate))
        } else {
            holder.txtPercentChange.setTextColor(context.resources.getColor(R.color.greenRate))
        }



        /** use this glouble variable for send ready value to FragmentCoinDetails */
        var exchangePrice = ""
        var priceName = ""
        var coinIconResourceId = R.drawable.ic_bottom_sheet_cryptocurrency
        /***/
        /** use excange price is checked */
        if (coinExchangeBase == PublicConstantNumber.TOMAN_ID.value) {
            /**if output cate to int = 0 return value cast to float for show details**/
            holder.txtPriceName.text = PublicConstant.TOMAN.value
            priceName = PublicConstant.TOMAN.value
            var price: Any = 0
            price = item.price.toInt() * G.exchangeList[0].price.toInt()

            if (price == 0) {
                //cate to float
                price = item.price * G.exchangeList[0].price.toInt()
                val priceBySimicalman = GeneralHelper.setNumberBySimicalman(price.toString())
                exchangePrice = priceBySimicalman
                holder.txtPrice.text = priceBySimicalman
            } else {
                // show int cast output
                val priceBySimicalman = GeneralHelper.setNumberBySimicalman(price.toString())
                exchangePrice = priceBySimicalman
                holder.txtPrice.text = priceBySimicalman
            }
        } else {
            holder.txtPriceName.text = PublicConstant.DOLLAR.value
            priceName = PublicConstant.DOLLAR.value
            val priceBySimicalman = GeneralHelper.setNumberBySimicalman(item.price.toString())
            exchangePrice = priceBySimicalman
            holder.txtPrice.text = priceBySimicalman
        }

        coinIconResourceId = setCoinLogo(item.symbol, holder.imgLogo)

        holder.layRoot.setOnClickListener {
            coinClickListener.onClicked(item,coinIconResourceId,exchangePrice,priceName)
        }

    }

    private fun setCoinLogo(symbol: String, imgLogo: ImageView):Int {
        var coinIconResourceId = R.drawable.ic_bottom_sheet_cryptocurrency
        when (symbol) {
            "BTC" -> {imgLogo.setImageResource(R.drawable.ic_btc)
                        coinIconResourceId = R.drawable.ic_btc}
            "ETH" -> {imgLogo.setImageResource(R.drawable.ic_eth)
                        coinIconResourceId = R.drawable.ic_eth}
            "ADA" -> {imgLogo.setImageResource(R.drawable.ic_ada)
                        coinIconResourceId = R.drawable.ic_ada}
            "AVAX" -> {imgLogo.setImageResource(R.drawable.ic_avax)
                        coinIconResourceId = R.drawable.ic_avax}
            "BNB" -> {imgLogo.setImageResource(R.drawable.ic_bnb)
                        coinIconResourceId = R.drawable.ic_bnb}
            "BUSD" -> {imgLogo.setImageResource(R.drawable.ic_busd)
                        coinIconResourceId = R.drawable.ic_busd}
            "DOGE" -> {imgLogo.setImageResource(R.drawable.ic_doge)
                        coinIconResourceId = R.drawable.ic_doge}
            "DOT" -> {imgLogo.setImageResource(R.drawable.ic_dot)
                        coinIconResourceId = R.drawable.ic_dot}
            "LUNA" -> {imgLogo.setImageResource(R.drawable.ic_luna)
                        coinIconResourceId = R.drawable.ic_luna}
            "SHIB" -> {imgLogo.setImageResource(R.drawable.ic_shib)
                        coinIconResourceId = R.drawable.ic_shib}
            "SOL" -> {imgLogo.setImageResource(R.drawable.ic_sol)
                        coinIconResourceId = R.drawable.ic_sol}
            "USDC" -> {imgLogo.setImageResource(R.drawable.ic_usdc)
                        coinIconResourceId = R.drawable.ic_usdc}
            "USDT" -> {imgLogo.setImageResource(R.drawable.ic_usdt)
                        coinIconResourceId = R.drawable.ic_usdt}
            "WBTC" -> {imgLogo.setImageResource(R.drawable.ic_wbtc)
                        coinIconResourceId = R.drawable.ic_wbtc}
            "XRP" -> {imgLogo.setImageResource(R.drawable.ic_xrp)
                        coinIconResourceId = R.drawable.ic_xrp}
            else -> {imgLogo.setImageResource(R.drawable.ic_coin)
                        coinIconResourceId = R.drawable.ic_coin}
        }
        return coinIconResourceId
    }

    override fun getItemCount(): Int {
        return exchangeList.size
    }

    interface ICoinItemClickedListener{
        fun onClicked(item: CoinExchange.Coin , iconResourceId:Int , exchangePrice:String , priceName:String)
    }

    companion object {
        private lateinit var coinClickListener:ICoinItemClickedListener
        fun getCoinOnClick(listener:ICoinItemClickedListener){
            this.coinClickListener = listener
        }
    }
}

package ir.mohammadno3ratii.app.dev.digicoinshow.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mohammadno3ratii.app.dev.digicoinshow.R
import ir.mohammadno3ratii.app.dev.digicoinshow.base.GeneralHelper
import ir.mohammadno3ratii.app.dev.digicoinshow.base.XmlParser
import javax.inject.Inject

class ExchangeAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>() {

    private var exchangeList = emptyList<XmlParser.Item>()

    fun setExchangeList(list: List<XmlParser.Item>) {
        this.exchangeList = list
    }

    class ExchangeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt: TextView = itemView.findViewById(R.id.txt)
        var txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        var txtChange: TextView = itemView.findViewById(R.id.txtChange)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder {
        val myHolder =
            LayoutInflater.from(context).inflate(R.layout.exchange_list_item, parent, false)
        return ExchangeViewHolder(myHolder)
    }

    override fun onBindViewHolder(holder: ExchangeViewHolder, position: Int) {
        val item = exchangeList[position]

        val price = GeneralHelper.setNumberBySimicalman(item.price)
        holder.txtPrice.text = price

        holder.txt.text = item.name
        if (item.percent.equals("۰")) {
            holder.txtChange.text = "0%"
            holder.txtChange.background = context.resources.getDrawable(R.drawable.rate_positiv)
        } else {
            holder.txtChange.text = "${item.percent}%${item.change}"
            //change background color
            if (item.change.contains("+")) {
                holder.txtChange.background = context.resources.getDrawable(R.drawable.rate_positiv)
            } else {
                holder.txtChange.background = context.resources.getDrawable(R.drawable.rate_negative)
            }
        }

    }

    override fun getItemCount(): Int {
        return exchangeList.size
    }

}

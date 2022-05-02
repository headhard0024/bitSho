package ir.mohammadno3ratii.app.dev.digicoinshow.view.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ir.mohammadno3ratii.app.dev.digicoinshow.R
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstant
import ir.mohammadno3ratii.app.dev.digicoinshow.databinding.FragmentCoinDetailsBinding


class FragmentCoinDetails : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentCoinDetailsBinding.inflate(inflater, container, false)

        val id = arguments!!.getInt("id", 1)
        val name = arguments!!.getString("name").orEmpty()
        val symbol = arguments!!.getString("symbol").orEmpty()
        val price = arguments!!.getString("price").orEmpty()
        val priceName = arguments!!.getString("priceName").orEmpty()
        val rank = arguments!!.getInt("rank", 1)
        val percentChangeOneHour = arguments!!.getString("percentChangeOneHour").orEmpty()
        val percentChangeOneDay = arguments!!.getString("percentChangeOneDay").orEmpty()
        val percentChangeSevenDay = arguments!!.getString("percentChangeSevenDay").orEmpty()
        val iconResourceId = arguments!!.getInt("iconResourceId",1)


        view.txtName.text = name
        view.txtSymbol.text = symbol
        view.txtPrice.text = price
        view.txtPriceName.text = priceName
        view.txtRank.text = rank.toString()
        view.imgLogo.setImageResource(iconResourceId)

        /** set text color by price percent change positive or negative */
        if (percentChangeOneHour!!.contains("-")) {
            view.txtChangeOneHour.setTextColor(resources.getColor(R.color.redRate))
        } else {
            view.txtChangeOneHour.setTextColor(resources.getColor(R.color.greenRate))
        }

        if (percentChangeOneDay!!.contains("-")) {
            view.txtChangeOneDay.setTextColor(resources.getColor(R.color.redRate))
        } else {
            view.txtChangeOneDay.setTextColor(resources.getColor(R.color.greenRate))
        }

        if (percentChangeSevenDay!!.contains("-")) {
            view.txtChangeSevenDay.setTextColor(resources.getColor(R.color.redRate))
        } else {
            view.txtChangeSevenDay.setTextColor(resources.getColor(R.color.greenRate))
        }
        /***/

        view.txtChangeOneHour.text = percentChangeOneHour
        view.txtChangeOneDay.text = percentChangeOneDay
        view.txtChangeSevenDay.text = percentChangeSevenDay

        view.txtTransaction.setOnClickListener {
            if (internetIsAvailable()){
            val bundle = Bundle()
                bundle.putString("symbol", symbol)
                bundle.putString("name", name)
                findNavController().navigate(
                    R.id.action_fragmentCoinDetails_to_fragmentTransactionWebView,
                    bundle
                )
            }else{
                Snackbar.make(it, PublicConstant.internetIsNotAvailable.value, Snackbar.LENGTH_LONG).show()
            }
        }

        view.imgBack.setOnClickListener {
            activity!!.onBackPressed()
        }

        return view.root
    }

    private fun internetIsAvailable(): Boolean {
        /** return internet status  */
        val cm = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connectByWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val connectByData = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        return connectByWifi != null && connectByData != null && connectByWifi.isConnected or connectByData.isConnected
    }
}

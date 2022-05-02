package ir.mohammadno3ratii.app.dev.digicoinshow.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatDrawableManager
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ir.mohammadno3ratii.app.dev.digicoinshow.R
import ir.mohammadno3ratii.app.dev.digicoinshow.base.G
import ir.mohammadno3ratii.app.dev.digicoinshow.databinding.FragmentHomeBinding
import ir.mohammadno3ratii.app.dev.digicoinshow.di.AppComponent
import ir.mohammadno3ratii.app.dev.digicoinshow.di.DaggerAppComponent
import ir.mohammadno3ratii.app.dev.digicoinshow.model.calzz.CoinExchange
import ir.mohammadno3ratii.app.dev.digicoinshow.view.adapter.CoinAdapter
import ir.mohammadno3ratii.app.dev.digicoinshow.viewModel.fragment.FragmentHomeViewModel
import javax.inject.Inject

class FragmentHome : Fragment() {

    lateinit var component: AppComponent

    @Inject
    lateinit var viewModel: FragmentHomeViewModel

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var adapter: CoinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        component = DaggerAppComponent.factory().create(context)
        component.HOME_FRAGMENT(this)

        adapter.setCoinList(G.coinList.data)
        adapter.notifyDataSetChanged()

        startGetApi(binding)
        binding.recycler.layoutManager = gridLayoutManager
        binding.recycler.adapter = adapter

        binding.btnInternetCheck.setOnClickListener {
            startGetApi(binding)
        }

        /** in api lover that level 21 can use image drawable refrence in xml */
        @SuppressLint("RestrictedApi")
        val iconReloadId =
            AppCompatDrawableManager.get().getDrawable(context!!, ir.mohammadno3ratii.app.dev.digicoinshow.R.drawable.ic_reload)
        binding.btnInternetCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(iconReloadId, null, null, null)

        /** call item clicked listener for send to details fragment */
        CoinAdapter.getCoinOnClick(object : CoinAdapter.ICoinItemClickedListener {
            override fun onClicked(
                item: CoinExchange.Coin,
                iconResourceId: Int,
                exchangePrice: String,
                priceName: String
            ) {
                val bundle = Bundle()
                bundle.putInt("id", item.id)
                bundle.putString("name", item.name)
                bundle.putString("symbol", item.symbol)
                bundle.putString("price", exchangePrice)
                bundle.putString("priceName", priceName)
                bundle.putInt("rank", item.rank)
                bundle.putString("percentChangeOneHour", item.percentChangeOneHour)
                bundle.putString("percentChangeOneDay", item.percentChangeOneDay)
                bundle.putString("percentChangeSevenDay", item.percentChangeSevenDay)
                bundle.putInt("iconResourceId", iconResourceId)
                findNavController().navigate(
                    R.id.action_fragmentHome_to_fragmentCoinDetails, bundle
                )
            }
        })

        FragmentHomeViewModel.getFailureConnection(object :
            FragmentHomeViewModel.IConnectionFailureListener {
            override fun onFailure() {
                binding.recycler.visibility = View.GONE
                binding.layConnectionFail.visibility = View.VISIBLE
            }
        })

        return binding.root
    }

    private fun startGetApi(binding: FragmentHomeBinding) {
        if (binding.layConnectionFail.isVisible){
            binding.recycler.visibility = View.VISIBLE
            binding.layConnectionFail.visibility = View.GONE
        }
        viewModel.callCoinConnection()
        viewModel.coinResponse.observeForever {
            adapter.setCoinList(it.data)
            adapter.notifyDataSetChanged()
            G.coinList = it
        }
    }
}

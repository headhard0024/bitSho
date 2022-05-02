package ir.mohammadno3ratii.app.dev.digicoinshow.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import ir.mohammadno3ratii.app.dev.digicoinshow.base.G
import ir.mohammadno3ratii.app.dev.digicoinshow.databinding.FragmentExchangeRateBinding
import ir.mohammadno3ratii.app.dev.digicoinshow.di.AppComponent
import ir.mohammadno3ratii.app.dev.digicoinshow.di.DaggerAppComponent
import ir.mohammadno3ratii.app.dev.digicoinshow.view.adapter.ExchangeAdapter

import javax.inject.Inject

class FragmentExchangeRate : Fragment() {

    lateinit var component: AppComponent

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var adapter: ExchangeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentExchangeRateBinding.inflate(inflater, container, false)

        component = DaggerAppComponent.factory().create(context)
        component.EXCHANGE_FRAGMENT(this)

        adapter.setExchangeList(G.exchangeList)
        adapter.notifyDataSetChanged()

        view.recycler.layoutManager = gridLayoutManager
        view.recycler.adapter = adapter

        return view.root
    }
}
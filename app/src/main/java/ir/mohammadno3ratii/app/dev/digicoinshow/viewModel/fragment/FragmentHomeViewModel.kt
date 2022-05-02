package ir.mohammadno3ratii.app.dev.digicoinshow.viewModel.fragment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.mohammadno3ratii.app.dev.digicoinshow.base.ApiConnection
import ir.mohammadno3ratii.app.dev.digicoinshow.base.G
import ir.mohammadno3ratii.app.dev.digicoinshow.base.ResponseUpdate
import ir.mohammadno3ratii.app.dev.digicoinshow.model.calzz.CoinExchange
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class FragmentHomeViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var connection: ApiConnection

    var coinResponse = MutableLiveData<CoinExchange>()

    fun callCoinConnection() {
        object : ResponseUpdate() {
            override fun upDate() {
                connectionForResponse()
            }
        }.upDate(120)
    }

    private fun connectionForResponse() {
        connection.coinExchangeResponse().enqueue(object :
            retrofit2.Callback<CoinExchange> {
            override fun onResponse(
                call: Call<CoinExchange>,
                response: Response<CoinExchange>
            ) {
                coinResponse.value = response.body()
            }

            override fun onFailure(call: Call<CoinExchange>, t: Throwable) {
                Log.i("MONO", "onFailure: " + t.toString())
                onFailureListener.onFailure()
            }
        })
    }

    interface IConnectionFailureListener{
        fun onFailure()
    }

    companion object {
        private lateinit var onFailureListener:IConnectionFailureListener
        fun getFailureConnection(listener:IConnectionFailureListener){
            this.onFailureListener = listener
        }
    }

}
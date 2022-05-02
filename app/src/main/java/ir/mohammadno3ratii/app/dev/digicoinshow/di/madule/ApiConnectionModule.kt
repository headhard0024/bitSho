package ir.mohammadno3ratii.app.dev.digicoinshow.di.madule

import dagger.Module
import dagger.Provides
import ir.mohammadno3ratii.app.dev.digicoinshow.base.ApiConnection
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiConnectionModule {

    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun apiConnection(): ApiConnection {
        return Retrofit.Builder()
            .baseUrl(PublicConstant.BASE_URL.value)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(ApiConnection::class.java)
    }
}
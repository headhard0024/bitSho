package ir.mohammadno3ratii.app.dev.digicoinshow.di;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

import dagger.BindsInstance;
import dagger.Component;
import ir.mohammadno3ratii.app.dev.digicoinshow.base.ApiConnection;
import ir.mohammadno3ratii.app.dev.digicoinshow.di.madule.ApiConnectionModule;
import ir.mohammadno3ratii.app.dev.digicoinshow.di.madule.ExchangeAdapterModule;
import ir.mohammadno3ratii.app.dev.digicoinshow.view.activity.ActivityMain;
import ir.mohammadno3ratii.app.dev.digicoinshow.view.activity.ActivitySplazh;
import ir.mohammadno3ratii.app.dev.digicoinshow.view.adapter.CoinAdapter;
import ir.mohammadno3ratii.app.dev.digicoinshow.view.adapter.ExchangeAdapter;
import ir.mohammadno3ratii.app.dev.digicoinshow.view.fragment.FragmentExchangeRate;
import ir.mohammadno3ratii.app.dev.digicoinshow.view.fragment.FragmentHome;
import ir.mohammadno3ratii.app.dev.digicoinshow.viewModel.fragment.FragmentHomeViewModel;

@Component(modules = {ApiConnectionModule.class , ExchangeAdapterModule.class})
public interface AppComponent {

    GridLayoutManager GRID_LAYOUT_MANAGER();
    ExchangeAdapter EXCHANGE_ADAPTER();
    CoinAdapter COIN_ADAPTER();
    ApiConnection API_CONNECTION();

    FragmentHomeViewModel FRAGMENT_HOME_VIEW_MODEL();

    void ACTIVITY_MAIN (ActivityMain activityMain);
    void ACTIVITY_SPLASH (ActivitySplazh activitySplazh);
    void EXCHANGE_FRAGMENT (FragmentExchangeRate exchangeRateFragment);
    void HOME_FRAGMENT (FragmentHome homeFragment);

    @Component.Factory
    interface Factory{
        AppComponent create(
                @BindsInstance Context context);
    }
}

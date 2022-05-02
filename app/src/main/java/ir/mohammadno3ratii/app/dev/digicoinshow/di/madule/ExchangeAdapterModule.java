package ir.mohammadno3ratii.app.dev.digicoinshow.di.madule;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;

@Module
public class ExchangeAdapterModule {

    @Provides
    GridLayoutManager gridLayoutManager(Context context){
        return new GridLayoutManager(context , 1, RecyclerView.VERTICAL,false);
    }

}

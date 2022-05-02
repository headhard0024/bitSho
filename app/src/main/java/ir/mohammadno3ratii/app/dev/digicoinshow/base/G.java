package ir.mohammadno3ratii.app.dev.digicoinshow.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.Date;

import ir.mohammadno3ratii.app.dev.digicoinshow.model.calzz.CoinExchange;
import saman.zamani.persiandate.PersianDate;

public class G extends Application {

    public static ArrayList<XmlParser.Item> exchangeList;
    public static CoinExchange coinList;
    public static Context context;
    public static PersianDate currentPersianTime;
    public static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();
        /** set app theme read old set value and set for default started theme */
        int appTheme = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt(PublicConstant.APP_THEME.getValue(), PublicConstantNumber.APP_THEME_DEVISE_DEFAULT.getValue());
        if (appTheme == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else if(appTheme == 2){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        context = getApplicationContext();
        currentPersianTime = nowCurrentTime();
        resources = getResources();
    }

    private PersianDate nowCurrentTime() {
        Date resultdate = new Date(System.currentTimeMillis());
        Date myDate = new Date(String.valueOf(resultdate));
        return new PersianDate(myDate);
    }
}

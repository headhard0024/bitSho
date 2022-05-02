package ir.mohammadno3ratii.app.dev.digicoinshow.view.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kardari.kardariapp.Helper.CirculeRevealHelper;

import ir.mohammadno3ratii.app.dev.digicoinshow.R;
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstant;
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstantNumber;

public class BottomSheetCoinExchangePrice extends BottomSheetDialog {

    public BottomSheetCoinExchangePrice(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_coin_exchange_price);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        int currentItemCheckedId = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt(PublicConstant.COIN_EXCHANGE_BASE.getValue(), PublicConstantNumber.TOMAN_ID.getValue());

        ImageView imgCheckToman = findViewById(R.id.imgCheckToman);
        ImageView imgCheckDollar = findViewById(R.id.imgCheckDollar);
        LinearLayout layCheckToman = findViewById(R.id.layCheckToman);
        LinearLayout layCheckDollar = findViewById(R.id.layCheckDollar);

        if (currentItemCheckedId == PublicConstantNumber.TOMAN_ID.getValue()) {
            imgCheckToman.setVisibility(View.VISIBLE);
        } else {
            imgCheckDollar.setVisibility(View.VISIBLE);
        }

        layCheckToman.setOnClickListener(view -> {
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(PublicConstant.COIN_EXCHANGE_BASE.getValue(), PublicConstantNumber.TOMAN_ID.getValue()).apply();
            dismiss();
        });

        layCheckDollar.setOnClickListener(view -> {
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(PublicConstant.COIN_EXCHANGE_BASE.getValue(), PublicConstantNumber.DOLAR_ID.getValue()).apply();
            dismiss();
        });

        layCheckToman.setOnTouchListener((view, motionEvent) -> {
           setCirculeReveal(view,motionEvent.getX() , motionEvent.getY());
            return false;
        });

        layCheckDollar.setOnTouchListener((view, motionEvent) -> {
           setCirculeReveal(view,motionEvent.getX() , motionEvent.getY());
            return false;
        });

    }

    private void setCirculeReveal(View view, float x, float y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            new CirculeRevealHelper(view, R.color.E8, R.color.white).position((int) x, (int) y).init();
        }
    }
}

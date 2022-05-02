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

public class BottomSheetCoinUpdateSpeed extends BottomSheetDialog {

    public BottomSheetCoinUpdateSpeed(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_coin_update_speed);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        int updateSpeed = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt(PublicConstant.COIN_UPDATE_SPEED.getValue(), PublicConstantNumber.COIN_UPDATE_SPEED_TEN_MINUTRS.getValue());

        ImageView imgCheckThirtySeconds = findViewById(R.id.imgCheckThirtySeconds);
        ImageView imgCheckOneMinutrs = findViewById(R.id.imgCheckOneMinutrs);
        ImageView imgCheckFiveMinutrs = findViewById(R.id.imgCheckFiveMinutrs);
        ImageView imgCheckTenMinutrs = findViewById(R.id.imgCheckTenMinutrs);

        LinearLayout layCheckThirtySeconds = findViewById(R.id.layCheckThirtySeconds);
        LinearLayout layCheckOneMinutrs = findViewById(R.id.layCheckOneMinutrs);
        LinearLayout layCheckFiveMinutrs = findViewById(R.id.layCheckFiveMinutrs);
        LinearLayout layCheckTenMinutrs = findViewById(R.id.layCheckTenMinutrs);


         if (updateSpeed == PublicConstantNumber.COIN_UPDATE_SPEED_THIRTY_SECONDS.getValue()){
             imgCheckThirtySeconds.setVisibility(View.VISIBLE);
         }else if(updateSpeed == PublicConstantNumber.COIN_UPDATE_SPEED_ONE_MINUTRS.getValue()){
             imgCheckOneMinutrs.setVisibility(View.VISIBLE);
         }else if(updateSpeed == PublicConstantNumber.COIN_UPDATE_SPEED_FIVE_MINUTRS.getValue()){
             imgCheckFiveMinutrs.setVisibility(View.VISIBLE);
         }else if(updateSpeed == PublicConstantNumber.COIN_UPDATE_SPEED_TEN_MINUTRS.getValue()){
             imgCheckTenMinutrs.setVisibility(View.VISIBLE);
         }

        layCheckThirtySeconds.setOnClickListener(view -> {
            setCirculeReveal(view);
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(PublicConstant.COIN_UPDATE_SPEED.getValue(), PublicConstantNumber.COIN_UPDATE_SPEED_THIRTY_SECONDS.getValue()).apply();
            dismiss();
        });

        layCheckOneMinutrs.setOnClickListener(view -> {
            setCirculeReveal(view);
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(PublicConstant.COIN_UPDATE_SPEED.getValue(), PublicConstantNumber.COIN_UPDATE_SPEED_ONE_MINUTRS.getValue()).apply();
            dismiss();
        });

        layCheckFiveMinutrs.setOnClickListener(view -> {
            setCirculeReveal(view);
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(PublicConstant.COIN_UPDATE_SPEED.getValue(), PublicConstantNumber.COIN_UPDATE_SPEED_FIVE_MINUTRS.getValue()).apply();
            dismiss();
        });

        layCheckTenMinutrs.setOnClickListener(view -> {
            setCirculeReveal(view);
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(PublicConstant.COIN_UPDATE_SPEED.getValue(), PublicConstantNumber.COIN_UPDATE_SPEED_TEN_MINUTRS.getValue()).apply();
            dismiss();
        });
    }

    private void setCirculeReveal(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            new CirculeRevealHelper(view, R.color.E8, R.color.white).init();
        }
    }
}

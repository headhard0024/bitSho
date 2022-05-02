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

public class BottomSheetAppTheme extends BottomSheetDialog {

    public BottomSheetAppTheme(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_app_theme);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        int currentItemCheckedId = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt(PublicConstant.APP_THEME.getValue(), PublicConstantNumber.APP_THEME_DEVISE_DEFAULT.getValue());

        ImageView imgCheckLight = findViewById(R.id.imgCheckLight);
        ImageView imgCheckDark = findViewById(R.id.imgCheckDark);
        ImageView imgCheckDefault = findViewById(R.id.imgCheckDefault);
        LinearLayout layCheckDefault = findViewById(R.id.layCheckDefault);
        LinearLayout layCheckLight = findViewById(R.id.layCheckLight);
        LinearLayout layCheckDark = findViewById(R.id.layCheckDark);

        if (currentItemCheckedId == PublicConstantNumber.APP_THEME_LIGHT.getValue()) {
            imgCheckLight.setVisibility(View.VISIBLE);
        } else if (currentItemCheckedId == PublicConstantNumber.APP_THEME_DARK.getValue()) {
            imgCheckDark.setVisibility(View.VISIBLE);
        } else {
            imgCheckDefault.setVisibility(View.VISIBLE);
        }

        layCheckLight.setOnClickListener(view -> {
            setCirculeReveal(view);
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(PublicConstant.APP_THEME.getValue(), PublicConstantNumber.APP_THEME_LIGHT.getValue()).apply();
            coinClickListener.lightModeClicked();
            dismiss();
        });

        layCheckDark.setOnClickListener(view -> {
            setCirculeReveal(view);
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(PublicConstant.APP_THEME.getValue(), PublicConstantNumber.APP_THEME_DARK.getValue()).apply();
            coinClickListener.darkModeClicked();
            dismiss();
        });

        layCheckDefault.setOnClickListener(view -> {
            setCirculeReveal(view);
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(PublicConstant.APP_THEME.getValue(), PublicConstantNumber.APP_THEME_DEVISE_DEFAULT.getValue()).apply();
            coinClickListener.systemDefaultClicked();
            dismiss();
        });

    }

    public interface IAppThemeClickedListener {
        void darkModeClicked();
        void lightModeClicked();
        void systemDefaultClicked();
    }

    public static IAppThemeClickedListener coinClickListener;
    public static void getThemeOnClick(IAppThemeClickedListener _coinClickListener) {
        coinClickListener = _coinClickListener;
    }

    private void setCirculeReveal(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            new CirculeRevealHelper(view, R.color.E8, R.color.white).init();
        }
    }
}

package ir.mohammadno3ratii.app.dev.digicoinshow.view.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatDrawableManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kardari.kardariapp.Helper.CirculeRevealHelper;

import ir.mohammadno3ratii.app.dev.digicoinshow.R;
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstant;
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstantNumber;

public class BottomSheetAppSupport extends BottomSheetDialog {

    public BottomSheetAppSupport(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_app_support);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        Button btnEmail = findViewById(R.id.btnEmail);
        Button btnTelegram = findViewById(R.id.btnTelegram);

        /** in api lover that level 21 can use image drawable refrence in xml */
        @SuppressLint("RestrictedApi")
        Drawable iconEmailId =
                AppCompatDrawableManager.get().getDrawable(getContext(), R.drawable.ic_email);
        btnEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(iconEmailId, null, null, null);

        @SuppressLint("RestrictedApi")
        Drawable iconTelegramId =
                AppCompatDrawableManager.get().getDrawable(getContext(), R.drawable.ic_telegram);
        btnTelegram.setCompoundDrawablesRelativeWithIntrinsicBounds(iconTelegramId, null, null, null);

        btnEmail.setOnClickListener(view -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "info.bitSho@gmial.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "مشکل در اجرای برنامه");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");
            getContext().startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
        });

        btnTelegram.setOnClickListener(view -> {
            try {
                Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/info_bitsho"));
                telegram.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                telegram.setPackage("org.telegram.messenger");
                getContext().startActivity(telegram);
            } catch (Exception e) {
                Toast.makeText(getContext(), "برنامه تلگرام روی دستگاه شما شناسایی نشد!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

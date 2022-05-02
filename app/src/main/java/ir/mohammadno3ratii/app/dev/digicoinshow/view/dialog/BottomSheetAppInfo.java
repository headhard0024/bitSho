package ir.mohammadno3ratii.app.dev.digicoinshow.view.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatDrawableManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import ir.mohammadno3ratii.app.dev.digicoinshow.R;

public class BottomSheetAppInfo extends BottomSheetDialog {

    public BottomSheetAppInfo(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_app_info);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        Button btnSupport = findViewById(R.id.btnSupport);
        Button btnSource = findViewById(R.id.btnSource);

        /** in api lover that level 21 can use image drawable refrence in xml */
        @SuppressLint("RestrictedApi")
        Drawable iconLikeId =
                AppCompatDrawableManager.get().getDrawable(getContext(), R.drawable.ic_like);
        btnSupport.setCompoundDrawablesRelativeWithIntrinsicBounds(iconLikeId, null, null, null);

        @SuppressLint("RestrictedApi")
        Drawable iconSourceId =
                AppCompatDrawableManager.get().getDrawable(getContext(), R.drawable.ic_open_source);
        btnSource.setCompoundDrawablesRelativeWithIntrinsicBounds(iconSourceId, null, null, null);


        btnSupport.setOnClickListener(view -> {
            startIntent("http://mohammadno3ratii.ir/support.html");
        });

        btnSource.setOnClickListener(view -> {
            startIntent("https://github.com/mohammadno3rati/bitsho");
        });

    }

    private void startIntent(String url) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            getContext().startActivity(i);
        } catch (Exception e) {
            Toast.makeText(getContext(), "برنامه روی دستگاه شما شناسایی نشد!", Toast.LENGTH_SHORT).show();
        }
    }

}

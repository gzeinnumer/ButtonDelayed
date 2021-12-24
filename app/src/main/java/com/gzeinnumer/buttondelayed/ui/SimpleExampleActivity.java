package com.gzeinnumer.buttondelayed.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.flod.drawabletextview.DrawableTextView;
import com.flod.loadingbutton.LoadingButton;
import com.gzeinnumer.buttondelayed.Glide4Engine;
import com.gzeinnumer.buttondelayed.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.Arrays;
import java.util.List;

public class SimpleExampleActivity extends AppCompatActivity implements View.OnClickListener {

    private LoadingButton loadingBtn;
    private Button btCancel;
    private Button btFail;
    private Button btComplete;

    private String loadingText = "Loading";
    private String completeText = "Success";
    private String failText = "Fail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_example);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Reset")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        initView();
                        Toast.makeText(getApplicationContext(), "Reset", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);

    }

    private void initView() {
        loadingBtn = findViewById(R.id.loadingBtn);

        initLoadingButton();

        loadingBtn.setEnableShrink(false);
        loadingBtn.setDisableClickOnLoading(true);

        loadingBtn.setRadius(10);
        loadingBtn.setLoadingEndDrawableSize(60);
        loadingBtn.setEndDrawableKeepDuration(900);

        btCancel = findViewById(R.id.btCancel);
        btFail = findViewById(R.id.btFail);
        btComplete = findViewById(R.id.btComplete);

        btCancel.setOnClickListener(this);
        btFail.setOnClickListener(this);
        btComplete.setOnClickListener(this);

    }

    private void initLoadingButton() {

        loadingBtn.setOnClickListener(this);
        loadingBtn.cancel();
        loadingBtn.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        loadingBtn.getLoadingDrawable().setStrokeWidth(loadingBtn.getTextSize() * 0.14f);
        loadingBtn.getLoadingDrawable().setColorSchemeColors(loadingBtn.getTextColors().getDefaultColor());
        loadingBtn.setEnableShrink(true)
                .setDisableClickOnLoading(true)
                .setShrinkDuration(450)
                .setLoadingPosition(DrawableTextView.POSITION.START)
                .setSuccessDrawable(R.drawable.ic_successful)
                .setFailDrawable(R.drawable.ic_fail)
                .setEndDrawableKeepDuration(900)
                .setEnableRestore(true)
                .setLoadingEndDrawableSize((int) (loadingBtn.getTextSize() * 2))
                .setOnStatusChangedListener(new LoadingButton.OnStatusChangedListener() {

                    @Override
                    public void onShrinking() {
                        Log.d("LoadingButton", "onShrinking");
                    }

                    @Override
                    public void onLoadingStart() {
                        Log.d("LoadingButton", "onLoadingStart");
                        loadingBtn.setText(loadingText);
                    }

                    @Override
                    public void onLoadingStop() {
                        Log.d("LoadingButton", "onLoadingStop");
                    }

                    @Override
                    public void onEndDrawableAppear(boolean isSuccess, LoadingButton.EndDrawable endDrawable) {
                        Log.d("LoadingButton", "onEndDrawableAppear");
                        if (isSuccess) {
                            loadingBtn.setText(completeText);
                        } else {
                            loadingBtn.setText(failText);
                        }
                    }


                    @Override
                    public void onCompleted(boolean isSuccess) {
                        Log.d("LoadingButton", "onCompleted isSuccess: " + isSuccess);
                        Toast.makeText(getApplicationContext(), isSuccess ? "Success" : "Fail", Toast.LENGTH_SHORT).show();

                    }


                    @Override
                    public void onRestored() {
                        Log.d("LoadingButton", "onRestored");

                    }

                    @Override
                    public void onCanceled() {
                        Log.d("LoadingButton", "onCanceled");
                        Toast.makeText(getApplicationContext(), "onCanceled", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(final View v) {
        int id = v.getId();
        switch (id) {
            case R.id.loadingBtn:
                loadingBtn.start();
                Toast.makeText(getApplicationContext(), "tekan", Toast.LENGTH_SHORT).show();
                return;
            case R.id.btCancel: {
                loadingBtn.cancel();
                return;
            }
            case R.id.btFail: {
                loadingBtn.complete(false);
                return;
            }
            case R.id.btComplete: {
                loadingBtn.complete(true);
                return;
            }
        }


        loadingBtn.cancel();
    }
}
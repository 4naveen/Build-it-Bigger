package com.naveen.androidlib;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class JokeActivity extends AppCompatActivity {

    public static void startActivity(Context context, String result){

      /*  Intent iJokeActivity = new Intent(context, JokeActivity.class);
        iJokeActivity.putExtra("joke",result);
        context.startActivity(iJokeActivity);*/

        new MaterialDialog.Builder(context)
                .content("I am just kidding!")
                .positiveText("Ok")
                .content(result)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                     dialog.dismiss();

                    }
                })

                .show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView tvJoke = (TextView) findViewById(R.id.tvJoke);

        tvJoke.setText(""+getIntent().getExtras().getString("joke"));

    }
}

package com.naveen.build_it_bigger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.Joke;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.naveen.androidlib.JokeActivity;

public class MainActivity extends AppCompatActivity {
    Button button;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button2);
        if (BuildConfig.FLAVOR == "free") {
            // add some ads or restrict functionallity
            mInterstitialAd = new InterstitialAd(MainActivity.this);
            mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }


                }
            });
            MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().addTestDevice("62214F3343BD8A700EAB1A326B37AFEF").build();
            mAdView.loadAd(adRequest);
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    new GCEPointsAsynTask(MainActivity.this).execute();

                    // this will show joke without GCE points

                 /*   Joke joke = new Joke();
                    String txt_joke = joke.getJoke();
                    JokeActivity.startActivity(MainActivity.this,txt_joke);*/
                }
            });
        }
        else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GCEPointsAsynTask(MainActivity.this).execute();

                    // this will show joke without GCE points
                  /*  Joke joke = new Joke();
                    String txt_joke = joke.getJoke();
                    JokeActivity.startActivity(MainActivity.this,txt_joke);*/

                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.FLAVOR == "free") {

            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
    }
    public void start(String result) {

        JokeActivity.startActivity(this, result);

       /* if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
        }*/
    }
}

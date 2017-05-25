package com.naveen.build_it_bigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.naveen.build_it_bigger.backend.myApi.MyApi;
import com.naveen.build_it_bigger.backend.myApi.model.MyBean;
import java.io.IOException;

/**
 * Created by User on 5/25/2017.
 */

public class GCEPointsAsynTask  extends AsyncTask<Void, Void, String> {

    private MyApi myApiService = null;
    Context context;
    ProgressDialog dialog;
    //for testing
/*    private JsonGetTaskListener mListener = null;
    private Exception mError = null;*/

    public GCEPointsAsynTask(Context context) {

        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading, please wait");
        dialog.setTitle("Connecting server");
        dialog.show();
        dialog.setCancelable(false);
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-99a27.appspot.com/_ah/api/");

            //https://maximal-coast-145518.appspot.com/_ah/api/
            myApiService = builder.build();
        }


        MyBean bean = new MyBean();

        try {
            return myApiService.sayJoke(bean).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        ((MainActivity) context).start(result);
          dialog.dismiss();
        /*if (this.mListener != null) {
            this.mListener.onComplete(result, mError);
        }else {
        }*/

    }
}

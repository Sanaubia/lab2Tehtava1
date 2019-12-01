package com.example.lab2tehtava1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import jdk.nashorn.internal.ir.RuntimeNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HttpAsync.HttpProgressUpdate {


    HttpAsync task = new HttpAsync ( );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        Final TextView tuloste = (TextView) findViewById(R.id.tuloste);



        findViewById (R.id.button).setOnClickListener (this);



    }

    /*

    private void lataa() {

        try {



            String oikeaOsoite = osoite.getText ().toString ();

            if(oikeaOsoite.contains ("https://") || oikeaOsoite.contains ("HTTPS://")){
                Log.d ("OSOITE", oikeaOsoite);
            }else{
                oikeaOsoite = "https://" + oikeaOsoite;

                Log.d ("OSOITE lisätty", oikeaOsoite);
            }


            URL url;
            url = new URL (oikeaOsoite);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection ( );


            String result = fromStream (urlConnection.getInputStream ());

            tuloste.setText (result);

        } catch (MalformedURLException e) {
            e.printStackTrace ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }
    */


    //from https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java

    @Override
    public void onClick(View var1) {

        if (var1.getId ( ) == R.id.button) {
            RequestQueue queue = Volley.newRequestQueue (this );
            EditText osoite = findViewById (R.id.editText);
            String url = osoite.getText ( ).toString ( );

            StringRequest stringRequest = new StringRequest (Request.Method.GET, url,
                    new Response.Listener<String> ( ) {
                        @Override
                        public void onResponse(String response) {
                            tuloste.setText ("Response is: " + response.substring (0, 500));
                        }
                    }, new Response.ErrorListener ( ) {
                @Override
                public void onErrorResponse(VolleyError error) {
                    tuloste.setText ("Error in request");
                }
            });

            queue.add(stringRequest);

            //lataa ();
        }


    }

    /*
    @Override
    public String searchDone() {


        tuloste.setText (task.result);

    return "";
    }

    @Override
    public void onUpdate(Integer percentageDone) {

    }
    */


}

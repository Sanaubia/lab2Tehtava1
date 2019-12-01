package com.example.lab2tehtava1;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpAsync extends AsyncTask<String, Integer, Boolean>  {




        public static String result;
        interface HttpProgressUpdate{

            String searchDone();
            void onUpdate(Integer precentageDone);
        }

        private HttpProgressUpdate callback = null;

        public void  setCallback(HttpProgressUpdate callback){
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(String... strings){

            try{

                //String oikeaOsoite = strings.toString ();


                URL url;
                url = new URL (strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection ( );
                result = fromStream (urlConnection.getInputStream ());
                Log.d ("Vastus", result);



            } catch (MalformedURLException e) {
                e.printStackTrace ( );
            } catch (IOException e) {
                e.printStackTrace ( );
            }



                catch (Exception e){
                e.printStackTrace ();
            }

            return new Boolean (true);

        }



    public static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader (in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }

    @Override
        protected void onPostExecute(Boolean aBoolean){
            callback.searchDone ();

        }
}

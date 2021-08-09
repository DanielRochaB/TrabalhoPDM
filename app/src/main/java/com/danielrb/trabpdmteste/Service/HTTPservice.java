package com.danielrb.trabpdmteste.Service;

import android.os.AsyncTask;
import android.widget.TextView;

import com.danielrb.trabpdmteste.Model.ConsumirJson;
import com.danielrb.trabpdmteste.Model.Players;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTTPservice extends AsyncTask<String, Void, String>{

    private List<Players> jsonDados = new ArrayList<>();

    //processo de conexão
    @Override
    protected String doInBackground(String... params) {

        StringBuilder resposta= new StringBuilder();
        String result = "";

        String token= "";

        try {

            URL url = new URL(params[0]);
            HttpURLConnection URLc = (HttpURLConnection) url.openConnection();


            URLc.setRequestMethod("GET");
            URLc.setRequestProperty("authorization","Bearer " + token);
            URLc.setRequestProperty("Accept","application/json");
            URLc.connect();


            //mostra o numero do erro
            int status= URLc.getResponseCode();

            if (status == 200){
                InputStream is = URLc.getInputStream();
                if(is != null){
                    result = convertInputStreamToString(is);


                }else
                    result = "Não funcionou!";

            }else
            System.out.println("Não entrou no status=200");
            //fim de status


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
        String line = "";
        String result = "";
        while((line = br.readLine()) != null )
            result += line;

        inputStream.close();
        return result;

    }

}
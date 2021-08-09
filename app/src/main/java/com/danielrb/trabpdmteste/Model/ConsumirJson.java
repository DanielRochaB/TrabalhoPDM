package com.danielrb.trabpdmteste.Model;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsumirJson {

    //Converte a String que pegamos da API em objeto da classe Players
    public Players GSON(String conteudo){
        Gson gson = new Gson();
        Players res = gson.fromJson(conteudo, Players.class);
        return res;
    }

}

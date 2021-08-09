package com.danielrb.trabpdmteste.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.danielrb.trabpdmteste.R;
import com.danielrb.trabpdmteste.Service.HTTPservice;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvNome, tvNivel, tvVitorias, tvDerrotas, tvTrophies, tvArena, tvCards;
    private EditText etPesquisar;
    List<Players> lst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNome= findViewById(R.id.tvNome);
        tvNivel= findViewById(R.id.tvNivel);
        tvVitorias= findViewById(R.id.tvVitorias);
        tvDerrotas= findViewById(R.id.tvDerrotas);
        tvTrophies= findViewById(R.id.tvTrophies);
        tvArena= findViewById(R.id.tvArena);
        tvCards= findViewById(R.id.tvCards);

        etPesquisar = findViewById(R.id.etPesquisar);

        getSupportActionBar().setTitle("Clash Royale API");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.clash);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1E90FF")));

    }

    public void clkBuscar(View view) {

        try {
            HTTPservice ht= new HTTPservice();
            ht.execute("https://api.clashroyale.com/v1/players/%23" + etPesquisar.getText());
            String retorno = ht.get();

            ConsumirJson c = new ConsumirJson();

            lst.add(c.GSON(retorno));

            tvNome.setText(lst.get(0).getName());
            tvNivel.setText(lst.get(0).getExpLevel());
            tvVitorias.setText(lst.get(0).getWins());
            tvDerrotas.setText(lst.get(0).getLosses());
            //troféus
            tvTrophies.setText(lst.get(0).getTrophies());
            //Arena
            tvArena.setText("Nome: " + lst.get(0).getArena().getName()  + " ID: " + lst.get(0).getArena().getId());
            //Cards imprime o nome e o nível dos cards
            List<Cards> lst2= lst.get(0).getCards();
            StringBuilder bd= new StringBuilder();

            for (int i=0; i <lst2.size(); i++){
                bd.append("Nome: " + lst2.get(i).getName() + " Nível: "+  lst2.get(i).getLevel() +"\n");
            }

            tvCards.setText(""+bd);


        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
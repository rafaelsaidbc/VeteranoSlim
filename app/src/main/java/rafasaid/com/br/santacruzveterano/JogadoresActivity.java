package rafasaid.com.br.santacruzveterano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class JogadoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores);

    }


    public void startRafaelDados(View view) {
        Intent startRafaelDados = new Intent(this, RafaelActivity.class);
        startActivity(startRafaelDados);
    }

    public void startDouglasDados(View view) {
        Intent startDouglasDados = new Intent(this, DouglasActivity.class);
        startActivity(startDouglasDados);
    }

    public void startErickDados(View view) {
        Intent startErickDados = new Intent(this, ErickActivity.class);
        startActivity(startErickDados);
    }

    public void startErliDados(View view) {
        Intent startErliDados = new Intent(this, ErliActivity.class);
        startActivity(startErliDados);
    }

    public void startRomarioDados(View view) {
        Intent startRomarioDados = new Intent(this, RomarioActivity.class);
        startActivity(startRomarioDados);
    }

    public void startPelotaDados(View view) {
        Intent startPelotaDados = new Intent(this, PelotaActivity.class);
        startActivity(startPelotaDados);
    }

    public void startPaulinhoDados(View view) {
        Intent startPaulinhoDados = new Intent(this, PaulinhoActivity.class);
        startActivity(startPaulinhoDados);
    }

    public void startBaianoDados(View view) {
        Intent startBaianoDados = new Intent(this, BaianoActivity.class);
        startActivity(startBaianoDados);
    }

    public void startAlexDados(View view) {
        Intent startAlexDados = new Intent(this, AlexActivity.class);
        startActivity(startAlexDados);
    }

    public void startAlissonDados(View view) {
        Intent startAlissonDados = new Intent(this, AlissonActivity.class);
        startActivity(startAlissonDados);
    }

    public void startBilinDados(View view) {
        Intent startBilinDados = new Intent(this, BilinActivity.class);
        startActivity(startBilinDados);
    }

    public void startBoizinhoDados(View view) {
        Intent startBoizinhoDados = new Intent(this, BoizinhoActivity.class);
        startActivity(startBoizinhoDados);
    }

    public void startBrunoDados(View view) {
        Intent startBrunoDados = new Intent(this, BrunoActivity.class);
        startActivity(startBrunoDados);
    }

    public void startEdmundoDados(View view) {
        Intent startEdmundoDados = new Intent(this, EdmundoActivity.class);
        startActivity(startEdmundoDados);
    }

    public void startJosielDados(View view) {
        Intent startJosielDados = new Intent(this, JosielActivity.class);
        startActivity(startJosielDados);
    }

    public void startLuizEduardoDados(View view) {
        Intent startLuizEduardoDados = new Intent(this, LuizEduardoActivity.class);
        startActivity(startLuizEduardoDados);
    }

    public void startRicardoDados(View view) {
        Intent startRicardoDados = new Intent(this, RicardoActivity.class);
        startActivity(startRicardoDados);
    }

    public void startRobertoDados(View view) {
        Intent startRobertoDados = new Intent(this, RobertoActivity.class);
        startActivity(startRobertoDados);
    }

    public void startRyanDados(View view) {
        Intent startRyanDados = new Intent(this, RyanActivity.class);
        startActivity(startRyanDados);
    }

    public void startZeGatoDados(View view) {
        Intent startZeGatoDados = new Intent(this, ZeGatoActivity.class);
        startActivity(startZeGatoDados);
    }

    public void startGabrielDados(View view) {
        Intent startGabrielDados = new Intent(this, GabrielActivity.class);
        startActivity(startGabrielDados);
    }

    public void startVanorDados(View view) {
        Intent startVanorDados = new Intent(this, VanorActivity.class);
        startActivity(startVanorDados);
    }

    public void startMaurinhoDados(View view) {
        Intent startMaurinhoDados = new Intent(this, MaurinhoActivity.class);
        startActivity(startMaurinhoDados);
    }
}

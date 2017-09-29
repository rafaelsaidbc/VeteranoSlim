package rafasaid.com.br.santacruzveterano.jogadores.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rafasaid.com.br.santacruzveterano.R;
import rafasaid.com.br.santacruzveterano.colaboradores.Daniel;
import rafasaid.com.br.santacruzveterano.colaboradores.Delinha;
import rafasaid.com.br.santacruzveterano.colaboradores.MaurinhoActivity;
import rafasaid.com.br.santacruzveterano.colaboradores.Toninho;
import rafasaid.com.br.santacruzveterano.colaboradores.VanorActivity;
import rafasaid.com.br.santacruzveterano.jogadores.AlexActivity;
import rafasaid.com.br.santacruzveterano.jogadores.AlissonActivity;
import rafasaid.com.br.santacruzveterano.jogadores.BaianoActivity;
import rafasaid.com.br.santacruzveterano.jogadores.BilinActivity;
import rafasaid.com.br.santacruzveterano.jogadores.BoizinhoActivity;
import rafasaid.com.br.santacruzveterano.jogadores.BrunoActivity;
import rafasaid.com.br.santacruzveterano.jogadores.DouglasActivity;
import rafasaid.com.br.santacruzveterano.jogadores.EdmundoActivity;
import rafasaid.com.br.santacruzveterano.jogadores.ErickActivity;
import rafasaid.com.br.santacruzveterano.jogadores.ErliActivity;
import rafasaid.com.br.santacruzveterano.jogadores.FlavioActivity;
import rafasaid.com.br.santacruzveterano.jogadores.GabrielActivity;
import rafasaid.com.br.santacruzveterano.jogadores.JosielActivity;
import rafasaid.com.br.santacruzveterano.jogadores.LuizEduardoActivity;
import rafasaid.com.br.santacruzveterano.jogadores.PaulinhoActivity;
import rafasaid.com.br.santacruzveterano.jogadores.PelotaActivity;
import rafasaid.com.br.santacruzveterano.jogadores.RafaelActivity;
import rafasaid.com.br.santacruzveterano.jogadores.RicardoActivity;
import rafasaid.com.br.santacruzveterano.jogadores.RobertoActivity;
import rafasaid.com.br.santacruzveterano.jogadores.RomarioActivity;
import rafasaid.com.br.santacruzveterano.jogadores.RyanActivity;
import rafasaid.com.br.santacruzveterano.jogadores.ZeGatoActivity;

public class JogadoresActivity extends AppCompatActivity {

    private static final String TAG = "JogadoresActivity";

    private ImageView mRomarioRostoImageView;
    private ImageView mDouglasRostoImageView;
    private ImageView mPaulinhoRostoImageView;
    private ImageView mBoizinhoRostoImageView;
    private ImageView mFlavioRostoImageView;
    private ImageView mPelotaRostoImageView;
    private ImageView mBaianoRostoImageView;
    private ImageView mZeGatoRostoImageView;
    private ImageView mRafaelRostoImageView;
    private ImageView mAlissonRostoImageView;
    private ImageView mEdmundoRostoImageView;
    private ImageView mLuizEduardoRostoImageView;
    private ImageView mRicardoRostoImageView;
    private ImageView mRobertoRostoImageView;
    private ImageView mGabrielRostoImageView;
    private ImageView mErickRostoImageView;
    private ImageView mErliRostoImageView;
    private ImageView mAlexRostoImageView;
    private ImageView mBilinRostoImageView;
    private ImageView mBrunoRostoImageView;
    private ImageView mJosielRostoImageView;
    private ImageView mRyanRostoImageView;
    private ImageView mDelinhaRostoImageView;
    private ImageView mToninhoRostoImageView;
    private ImageView mDanielRostoImageView;
    private ImageView mVanorRostoImageView;
    private ImageView mMaurinhoRostoImageView;

    private JogadoresRostoFirebaseAdapter mJogadoresRostoAdapter;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;//ponto de acesso do app ao Database
    private DatabaseReference mJogadoresRostoDatabaseReference;//classe que faz referência a uma parte específica da Database;
    //para cada referência que for utilizar a database, deve ter
    //uma classe

    //leitura e exibição dos dados da database na ListView
    private ChildEventListener mChildEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores);


        // obtém a referêcia dos resultados
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fotosRosto = database.getReference("fotosRosto");

        fotosRosto.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                JogadoresRostoFirebase jogadoresRostoFirebase = dataSnapshot.getValue(JogadoresRostoFirebase.class);

                String rafaelFotoRosto = jogadoresRostoFirebase.getRafaelRosto();
                mRafaelRostoImageView = (ImageView) findViewById(R.id.rafael_rosto);
                Glide.with(getApplicationContext()).load(rafaelFotoRosto).into(mRafaelRostoImageView);

                String alexFotoRosto = jogadoresRostoFirebase.getAlexRosto();
                mAlexRostoImageView = (ImageView) findViewById(R.id.alex_rosto);
                Glide.with(getApplicationContext()).load(alexFotoRosto).into(mAlexRostoImageView);

                String alissonFotoRosto = jogadoresRostoFirebase.getAlissonRosto();
                mAlissonRostoImageView = (ImageView) findViewById(R.id.alisson_rosto);
                Glide.with(getApplicationContext()).load(alissonFotoRosto).into(mAlissonRostoImageView);

                String baianoFotoRosto = jogadoresRostoFirebase.getBaianoRosto();
                mBaianoRostoImageView = (ImageView) findViewById(R.id.baiano_rosto);
                Glide.with(getApplicationContext()).load(baianoFotoRosto).into(mBaianoRostoImageView);

                String bilinFotoRosto = jogadoresRostoFirebase.getBilinRosto();
                mBilinRostoImageView = (ImageView) findViewById(R.id.bilin_rosto);
                Glide.with(getApplicationContext()).load(bilinFotoRosto).into(mBilinRostoImageView);

                // Inicializa as referências das Views
                String romarioRostoFoto = jogadoresRostoFirebase.getRomarioRosto();
                ;
                mRomarioRostoImageView = (ImageView) findViewById(R.id.romario_rosto);
                Glide.with(getApplicationContext()).load(romarioRostoFoto).into(mRomarioRostoImageView);


                String douglasRostoFoto = jogadoresRostoFirebase.getDouglasRosto();
                mDouglasRostoImageView = (ImageView) findViewById(R.id.douglas_rosto);
                Glide.with(getApplicationContext()).load(douglasRostoFoto).into(mDouglasRostoImageView);

                String paulinhoRostoFoto = jogadoresRostoFirebase.getPaulinhoRosto();
                mPaulinhoRostoImageView = (ImageView) findViewById(R.id.paulinho_rosto);
                Glide.with(getApplicationContext()).load(paulinhoRostoFoto).into(mPaulinhoRostoImageView);

                String boizinhoRostoFoto = jogadoresRostoFirebase.getBoizinhoRosto();
                mBoizinhoRostoImageView = (ImageView) findViewById(R.id.boizinho_rosto);
                Glide.with(getApplicationContext()).load(boizinhoRostoFoto).into(mBoizinhoRostoImageView);

                String flavioRostoFoto = jogadoresRostoFirebase.getFlavioRosto();
                mFlavioRostoImageView = (ImageView) findViewById(R.id.flavio_rosto);
                Glide.with(getApplicationContext()).load(flavioRostoFoto).into(mFlavioRostoImageView);

                String pelotaRostoFoto = jogadoresRostoFirebase.getPelotaRosto();
                mPelotaRostoImageView = (ImageView) findViewById(R.id.pelota_rosto);
                Glide.with(getApplicationContext()).load(pelotaRostoFoto).into(mPelotaRostoImageView);

                String zeGatoRostoFoto = jogadoresRostoFirebase.getZeGatoRosto();
                mZeGatoRostoImageView = (ImageView) findViewById(R.id.zeGato_rosto);
                Glide.with(getApplicationContext()).load(zeGatoRostoFoto).into(mZeGatoRostoImageView);

                String edmundoRostoFoto = jogadoresRostoFirebase.getEdmundoRosto();
                mEdmundoRostoImageView = (ImageView) findViewById(R.id.edmundo_rosto);
                Glide.with(getApplicationContext()).load(edmundoRostoFoto).into(mEdmundoRostoImageView);

                String luizEduardoRostoFoto = jogadoresRostoFirebase.getLuizEduardoRosto();
                mLuizEduardoRostoImageView = (ImageView) findViewById(R.id.luiz_eduardo_rosto);
                Glide.with(getApplicationContext()).load(luizEduardoRostoFoto).into(mLuizEduardoRostoImageView);

                String ricardoRostoFoto = jogadoresRostoFirebase.getRicardoRosto();
                mRicardoRostoImageView = (ImageView) findViewById(R.id.ricardo_rosto);
                Glide.with(getApplicationContext()).load(ricardoRostoFoto).into(mRicardoRostoImageView);

                String robertoRostoFoto = jogadoresRostoFirebase.getRobertoRosto();
                mRobertoRostoImageView = (ImageView) findViewById(R.id.roberto_rosto);
                Glide.with(getApplicationContext()).load(robertoRostoFoto).into(mRobertoRostoImageView);

                String gabrielRostoFoto = jogadoresRostoFirebase.getGabrielRosto();
                mGabrielRostoImageView = (ImageView) findViewById(R.id.gabriel_rosto);
                Glide.with(getApplicationContext()).load(gabrielRostoFoto).into(mGabrielRostoImageView);

                String erickRostoFoto = jogadoresRostoFirebase.getErickRosto();
                mErickRostoImageView = (ImageView) findViewById(R.id.erick_rosto);
                Glide.with(getApplicationContext()).load(erickRostoFoto).into(mErickRostoImageView);

                String erliRostoFoto = jogadoresRostoFirebase.getErliRosto();
                mErliRostoImageView = (ImageView) findViewById(R.id.erli_rosto);
                Glide.with(getApplicationContext()).load(erliRostoFoto).into(mErliRostoImageView);

                String brunoRostoFoto = jogadoresRostoFirebase.getBrunoRosto();
                mBrunoRostoImageView = (ImageView) findViewById(R.id.bruno_rosto);
                Glide.with(getApplicationContext()).load(brunoRostoFoto).into(mBrunoRostoImageView);

                String josielRostoFoto = jogadoresRostoFirebase.getJosielRosto();
                mJosielRostoImageView = (ImageView) findViewById(R.id.josiel_rosto);
                Glide.with(getApplicationContext()).load(josielRostoFoto).into(mJosielRostoImageView);

                String ryanRostoFoto = jogadoresRostoFirebase.getRyanRosto();
                mRyanRostoImageView = (ImageView) findViewById(R.id.ryan_rosto);
                Glide.with(getApplicationContext()).load(ryanRostoFoto).into(mRyanRostoImageView);

                String delinhaRostoFoto = jogadoresRostoFirebase.getDelinhaRosto();
                mDelinhaRostoImageView = (ImageView) findViewById(R.id.delinha_rosto);
                Glide.with(getApplicationContext()).load(delinhaRostoFoto).into(mDelinhaRostoImageView);

                String toninhoRostoFoto = jogadoresRostoFirebase.getToninhoRosto();
                mToninhoRostoImageView = (ImageView) findViewById(R.id.toninho_rosto);
                Glide.with(getApplicationContext()).load(toninhoRostoFoto).into(mToninhoRostoImageView);

                String danielRostoFoto = jogadoresRostoFirebase.getDanielRosto();
                mDanielRostoImageView = (ImageView) findViewById(R.id.daniel_rosto);
                Glide.with(getApplicationContext()).load(danielRostoFoto).into(mDanielRostoImageView);

                String vanorRostoFoto = jogadoresRostoFirebase.getVanorRosto();
                mVanorRostoImageView = (ImageView) findViewById(R.id.vanor_rosto);
                Glide.with(getApplicationContext()).load(vanorRostoFoto).into(mVanorRostoImageView);

                String maurinhoRostoFoto = jogadoresRostoFirebase.getMaurinhoRosto();
                mMaurinhoRostoImageView = (ImageView) findViewById(R.id.maurinho_rosto);
                Glide.with(getApplicationContext()).load(maurinhoRostoFoto).into(mMaurinhoRostoImageView);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void startRafaelDados(View view) {
        Intent startRafaelDados = new Intent(this, RafaelActivity.class);
        startActivity(startRafaelDados);
    }

    public void startDouglasDados(View view) {
        Intent startDouglasDados = new Intent(this, DouglasActivity.class);
        startActivity(startDouglasDados);
    }

    public void startFlavioDados(View view) {
        Intent startFlavioDados = new Intent(this, FlavioActivity.class);
        startActivity(startFlavioDados);
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

    public void startDelinhaDados(View view) {
        Intent startDelinhaDados = new Intent(this, Delinha.class);
        startActivity(startDelinhaDados);
    }

    public void startToninhoDados(View view) {
        Intent startToninhoDados = new Intent(this, Toninho.class);
        startActivity(startToninhoDados);
    }

    public void startDanielDados(View view) {
        Intent startDanielDados = new Intent(this, Daniel.class);
        startActivity(startDanielDados);
    }
}

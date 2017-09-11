package rafasaid.com.br.santacruzveterano;

import android.support.v7.app.AppCompatActivity;

public class EstatisticasActivity extends AppCompatActivity {


    public int estatisticasResultado(int mGolsSantaCruz, int mGolsAdversario) {


        int vitoriasStaCruz = 0;

        int derrotasStaCruz = 0;

        int empatesStaCruz = 0;

        if (mGolsSantaCruz > mGolsAdversario) {
            return vitoriasStaCruz++;

        } else if (mGolsSantaCruz == mGolsAdversario) {
            return empatesStaCruz++;

        } else return derrotasStaCruz++;
    }
}
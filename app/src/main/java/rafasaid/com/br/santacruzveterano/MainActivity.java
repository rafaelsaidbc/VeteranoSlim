/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rafasaid.com.br.santacruzveterano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(rafasaid.com.br.santacruzveterano.R.layout.activity_main);

        // Find the View that shows the jogadores category
        TextView jogadores = (TextView) findViewById(R.id.jogadores);

        // Set a click listener on that View
        jogadores.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the jogadores category is clicked on.
            @Override
            public void onClick(View view) {

                // Create a new intent to open the {@link JogadoresActivity}
                Intent jogadoresIntent = new Intent(MainActivity.this, rafasaid.com.br.santacruzveterano.JogadoresActivity.class);

                // Start the new activity (show jogadores activity)
                startActivity(jogadoresIntent);

            }
        });

        TextView resultados = (TextView) findViewById(R.id.resultados);

        resultados.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the resultados category is clicked on.
            @Override
            public void onClick(View view) {
                Intent resultadosIntent = new Intent(MainActivity.this, rafasaid.com.br.santacruzveterano.ResultadosActivity.class);

                startActivity(resultadosIntent);
            }
        });

        TextView calendario = (TextView) findViewById(R.id.calendario);

        calendario.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                Intent calendarioIntent = new Intent(MainActivity.this, rafasaid.com.br.santacruzveterano.CalendarioActivity.class);

                startActivity(calendarioIntent);
            }
        });

        TextView estatisticas = (TextView) findViewById(R.id.estatisticas);

        estatisticas.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                Intent estatisticasIntent = new Intent(MainActivity.this, EstatisticasActivity.class);

                startActivity(estatisticasIntent);
            }
        });

        TextView contato = (TextView) findViewById(R.id.contato);

        contato.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the contato category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link PhrasesActivity}
                Intent contatoIntent = new Intent(MainActivity.this, rafasaid.com.br.santacruzveterano.ContatoActivity.class);

                // Start the new activity
                startActivity(contatoIntent);
            }
        });

        TextView mensagens = (TextView) findViewById(R.id.mensagens);

        // Set a click listener on that View
        mensagens.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the contato category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link PhrasesActivity}
                Intent mensagensIntent = new Intent(MainActivity.this, rafasaid.com.br.santacruzveterano.MensagensActivity.class);

                // Start the new activity
                startActivity(mensagensIntent);
            }
        });


        TextView fotos = (TextView) findViewById(R.id.fotos);

        // Set a click listener on that View
        fotos.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the contato category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link PhrasesActivity}
                Intent fotosIntent = new Intent(MainActivity.this, FotosActivity.class);

                // Start the new activity
                startActivity(fotosIntent);
            }
        });
    }
}

package rafasaid.com.br.santacruzveterano;

/**
 * Created by Rafael Said on 14/08/2017.
 */

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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link JogadorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Jogador} objects.
 */
public class JogadorAdapter extends ArrayAdapter<rafasaid.com.br.santacruzveterano.Jogador> {

    /**
     * Create a new {@link JogadorAdapter} object.
     *
     * @param context   is the current context (i.e. Activity) that the adapter is being created in.
     * @param jogadores is the list of {@link Jogador}s to be displayed.
     */
    public JogadorAdapter(Context context, ArrayList<rafasaid.com.br.santacruzveterano.Jogador> jogadores) {
        super(context, 0, jogadores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.jogador_info, parent, false);
        }
        // Get the {@link Jogador} object located at this position in the list
        rafasaid.com.br.santacruzveterano.Jogador currentJogador = getItem(position);

        ImageView jogadorView = (ImageView) listItemView.findViewById(R.id.jogador_corpo);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        jogadorView.setImageResource(currentJogador.getJogadorImageView());

        // Find the TextView in the layout_info_jogadores.xmlogadores.xml layout with the ID miwok_text_view.
        TextView nomeTextView = (TextView) listItemView.findViewById(R.id.nome_text_view);
        // Get the Miwok translation from the currentJogador object and set this text on
        // the Miwok TextView.
        nomeTextView.setText(currentJogador.getNomeJogador());

        // Find the TextView in the layout_info_jogadores.xmlogadores.xml layout with the ID default_text_view.
        TextView apelidoTextView = (TextView) listItemView.findViewById(R.id.apelido_text_view);
        // Get the default translation from the currentJogador object and set this text on
        // the default TextView.
        apelidoTextView.setText(currentJogador.getApelidoJogador());

        // Find the TextView in the layout_info_jogadores.xmlogadores.xml layout with the ID default_text_view.
        TextView nascimentoTextView = (TextView) listItemView.findViewById(R.id.nascimento_text_view);
        // Get the default translation from the currentJogador object and set this text on
        // the default TextView.
        nascimentoTextView.setText(currentJogador.getNascimentoData());


        TextView posicaoTextView = (TextView) listItemView.findViewById(R.id.posicao_text_view);
        posicaoTextView.setText(currentJogador.getPosicaoJogador());

        // Find the TextView in the layout_info_jogadores.xmlogadores.xml layout with the ID default_text_view.
        TextView numeroTextView = (TextView) listItemView.findViewById(R.id.numero_camisa_view);
        // Get the default translation from the currentJogador object and set this text on
        // the default TextView.
        numeroTextView.setText(currentJogador.getNumeroCamisa());

        // Find the TextView in the layout_info_jogadoresfo_jogadores.xml layout with the ID default_text_view.
        TextView ingressoTextView = (TextView) listItemView.findViewById(R.id.ano_ingresso_view);
        // Get the default translation from the currentJogador object and set this text on
        // the default TextView.
        ingressoTextView.setText(currentJogador.getAnoIngresso());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}

package rafasaid.com.br.santacruzveterano.jogadores.firebase;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import rafasaid.com.br.santacruzveterano.R;

public class JogadoresFirebaseAdapter extends ArrayAdapter<JogadoresFirebase> {
    public JogadoresFirebaseAdapter(Context context, int resource, List<JogadoresFirebase> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_jogadores, parent, false);
        }

        TextView anoIngressoTextView = (TextView) convertView.findViewById(R.id.anoIngresso);
        TextView apelidoJogadorTextView = (TextView) convertView.findViewById(R.id.apelidoJogador);
        TextView dataNascimentoTextView = (TextView) convertView.findViewById(R.id.dataNascimentoJogador);
        ImageView fotoJogadorImageView = (ImageView) convertView.findViewById(R.id.fotoJogadorCorpo);
        TextView nomeJogadorTextView = (TextView) convertView.findViewById(R.id.nomeJogador);
        TextView numeroCamisaTextView = (TextView) convertView.findViewById(R.id.numeroCamisa);
        TextView posicaoJogadorTextView = (TextView) convertView.findViewById(R.id.posicaoJogador);

        JogadoresFirebase jogadoresInfo = getItem(position);

        anoIngressoTextView.setText(jogadoresInfo.getanoIngresso());
        apelidoJogadorTextView.setText(jogadoresInfo.getapelidoJogador());
        dataNascimentoTextView.setText(jogadoresInfo.getdataNascimento());

        fotoJogadorImageView.setVisibility(View.VISIBLE);
        Glide.with(fotoJogadorImageView.getContext())
                .load(jogadoresInfo.getfotoJogador())
                .into(fotoJogadorImageView);


        nomeJogadorTextView.setText(jogadoresInfo.getnomeJogador());
        numeroCamisaTextView.setText(jogadoresInfo.getnumeroCamisa());
        posicaoJogadorTextView.setText(jogadoresInfo.getposicaoJogador());

        return convertView;
    }
}



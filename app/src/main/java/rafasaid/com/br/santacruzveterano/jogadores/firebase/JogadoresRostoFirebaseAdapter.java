package rafasaid.com.br.santacruzveterano.jogadores.firebase;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import rafasaid.com.br.santacruzveterano.R;

public class JogadoresRostoFirebaseAdapter extends ArrayAdapter<JogadoresRostoFirebase> {
    public JogadoresRostoFirebaseAdapter(Context context, int resource, List<JogadoresRostoFirebase> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_jogadores_rosto, parent, false);
        }

        ImageView fotoJogadorImageView = (ImageView) convertView.findViewById(R.id.fotoJogadorRosto);

        JogadoresRostoFirebase jogadoresRosto = getItem(position);

        fotoJogadorImageView.setVisibility(View.VISIBLE);
        Glide.with(fotoJogadorImageView.getContext())
                .load(jogadoresRosto.getfotosRosto())
                .into(fotoJogadorImageView);

        return convertView;
    }
}



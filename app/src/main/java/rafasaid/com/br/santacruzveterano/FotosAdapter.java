package rafasaid.com.br.santacruzveterano;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FotosAdapter extends ArrayAdapter<FotosFirebase> {
    public FotosAdapter(Context context, int resource, List<FotosFirebase> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_fotos, parent, false);
        }

        ImageView fotosActivityImageView = (ImageView) convertView.findViewById(R.id.fotosActivityImageView);
        TextView usuariosFotosActivityTextView = (TextView) convertView.findViewById(R.id.usuarioFotosActivityTextView);

        final FotosFirebase fotosActivty = getItem(position);

        //faz com que o ImageButton seja clicável
        ImageButton mBtnFotosFullscreen = (ImageButton) convertView.findViewById(R.id.btnFotosFullscreen);
        mBtnFotosFullscreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //estabelece o que acontece quando o ImageButton é clicado, neste caso cria um
                //intent que abrirá uma nova class [ResultadoShare.class].
                Intent openFotosFullscreen = new Intent(getContext(), FotosFullscreen.class);
                //usa-se getContext() no lugar de this porque é um adapter, só aceitar getContext()
                //para dar certo
                getContext().startActivity(openFotosFullscreen);
                //cria um intent [shareResultado] que armazenará as variáveis para serem recuperadas
                //em outras activity, neste caso na activity ResultadoShare.class
                Intent fotosFullscreenIntent = new Intent(getContext(), FotosFullscreen.class);
                //armazena a "dataResultadoIntent", pegando os dados de resultado.getDataAddResultado
                //buscando assim os dados da data que estão no Firebase
                fotosFullscreenIntent.putExtra("fotosFullscreen", fotosActivty.getFotosActivityUrl());
                //inicia a atividade com os dados armazenados em shareResultado que serão recuperados
                //em ResultadoShare.class
                getContext().startActivity(fotosFullscreenIntent);
            }
        });

        boolean isPhoto = fotosActivty.getFotosActivityUrl() != null;
        if (isPhoto) {
            fotosActivityImageView.setVisibility(View.VISIBLE);
            Glide.with(fotosActivityImageView.getContext())
                    .load(fotosActivty.getFotosActivityUrl())
                    .into(fotosActivityImageView);
        } else {
            fotosActivityImageView.setVisibility(View.GONE);
        }
        usuariosFotosActivityTextView.setText(fotosActivty.getUsuarioFotosActivity());

        return convertView;
    }
}

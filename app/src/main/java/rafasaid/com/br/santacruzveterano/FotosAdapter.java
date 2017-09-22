package rafasaid.com.br.santacruzveterano;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        FotosFirebase fotosActivty = getItem(position);

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

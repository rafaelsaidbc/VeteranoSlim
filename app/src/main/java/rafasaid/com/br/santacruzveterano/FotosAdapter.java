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

public class FotosAdapter extends ArrayAdapter<FotosMessage> {
    public FotosAdapter(Context context, int resource, List<FotosMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.fotos_message, parent, false);
        }

        ImageView photoFotosImageView = (ImageView) convertView.findViewById(R.id.fotosImageView);
        TextView messageFotosTextView = (TextView) convertView.findViewById(R.id.fotos_messageTextView);
        TextView authorFotosTextView = (TextView) convertView.findViewById(R.id.fotos_nameTextView);

        FotosMessage messageFotos = getItem(position);

        boolean isPhoto = messageFotos.getPhotoUrlFotos() != null;
        if (isPhoto) {
            messageFotosTextView.setVisibility(View.GONE);
            photoFotosImageView.setVisibility(View.VISIBLE);
            Glide.with(photoFotosImageView.getContext())
                    .load(messageFotos.getPhotoUrlFotos())
                    .into(photoFotosImageView);
        } else {
            messageFotosTextView.setVisibility(View.VISIBLE);
            photoFotosImageView.setVisibility(View.GONE);
            messageFotosTextView.setText(messageFotos.getTextFotos());
        }
        authorFotosTextView.setText(messageFotos.getNameFotos());

        return convertView;
    }
}

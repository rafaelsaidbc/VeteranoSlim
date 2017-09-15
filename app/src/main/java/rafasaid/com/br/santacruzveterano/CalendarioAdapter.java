package rafasaid.com.br.santacruzveterano;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CalendarioAdapter extends ArrayAdapter<CalendarioFirebase> {
    public CalendarioAdapter(Context context, int resource, List<CalendarioFirebase> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_calendario, parent, false);
        }

        TextView dataAddCalendarioTextView = (TextView) convertView.findViewById(R.id.data_calendario);
        TextView horaAddCalendarioTextView = (TextView) convertView.findViewById(R.id.hora_calendario);
        TextView adversarioAddCalendarioTextView = (TextView) convertView.findViewById(R.id.adversario_calendario);
        TextView localCalendarioTextView = (TextView) convertView.findViewById(R.id.local_calendario);

        CalendarioFirebase calendario = getItem(position);

        dataAddCalendarioTextView.setText(calendario.getDataAddCalendario());
        horaAddCalendarioTextView.setText(calendario.getHoraAddCalendario());
        adversarioAddCalendarioTextView.setText(calendario.getAdversarioAddCalendario());
        localCalendarioTextView.setText(calendario.getLocalAddCalendario());

        return convertView;
    }
}



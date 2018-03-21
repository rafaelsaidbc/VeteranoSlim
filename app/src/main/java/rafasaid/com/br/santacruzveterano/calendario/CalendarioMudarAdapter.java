package rafasaid.com.br.santacruzveterano.calendario;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rafasaid.com.br.santacruzveterano.R;

//O Adapter define como as informações serão mostradas no layout
public class CalendarioMudarAdapter extends ArrayAdapter<CalendarioFirebase> {
    public CalendarioMudarAdapter(Context context, int resource, List<CalendarioFirebase> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_calendario_mudar, parent, false);
        }

        TextView idAddCalendarioTextView = (TextView) convertView.findViewById(R.id.idJogoCalendarioMudar);
        TextView dataAddCalendarioTextView = (TextView) convertView.findViewById(R.id.data_calendarioMudar);
        TextView horaAddCalendarioTextView = (TextView) convertView.findViewById(R.id.hora_calendarioMudar);
        TextView adversarioAddCalendarioTextView = (TextView) convertView.findViewById(R.id.adversario_calendarioMudar);
        TextView localCalendarioTextView = (TextView) convertView.findViewById(R.id.local_calendarioMudar);

        final CalendarioFirebase calendario = getItem(position);

        idAddCalendarioTextView.setText(calendario.getIdAddCalendario());
        dataAddCalendarioTextView.setText(calendario.getDataAddCalendario());
        horaAddCalendarioTextView.setText(calendario.getHoraAddCalendario());
        adversarioAddCalendarioTextView.setText(calendario.getAdversarioAddCalendario());
        localCalendarioTextView.setText(calendario.getLocalAddCalendario());

        return convertView;
    }
}



package rafasaid.com.br.santacruzveterano;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

//O Adapter define como as informações serão mostradas no layout
public class CalendarioAdapter extends ArrayAdapter<CalendarioFirebase> {
    public CalendarioAdapter(Context context, int resource, List<CalendarioFirebase> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_calendario, parent, false);
            //ToDo 4: adicionar compartilhar calendário com facebook e whatsapp

        }

        TextView idAddCalendarioTextView = (TextView) convertView.findViewById(R.id.idJogoCalendario);
        TextView dataAddCalendarioTextView = (TextView) convertView.findViewById(R.id.data_calendario);
        TextView horaAddCalendarioTextView = (TextView) convertView.findViewById(R.id.hora_calendario);
        TextView adversarioAddCalendarioTextView = (TextView) convertView.findViewById(R.id.adversario_calendario);
        TextView localCalendarioTextView = (TextView) convertView.findViewById(R.id.local_calendario);

        final CalendarioFirebase calendario = getItem(position);

        //faz com que o Image Button seja clicável
        ImageButton mBtnShareItemCalendario = (ImageButton) convertView.findViewById(R.id.imageBtnShareCalendario);
        mBtnShareItemCalendario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //estabelece o que acontece quando o ImageButton é clicado, neste caso cria um
                //intent que abrirá uma nova class [CalendarioShare.class].
                Intent shareItemCalendario = new Intent(getContext(), CalendarioShare.class);
                //usa-se getContext() no lugar de this porque é um adapter, só aceitar getContext()
                //para dar certo
                getContext().startActivity(shareItemCalendario);

                //cria um intent [shareCalendario] que armazenará as variáveis para serem recuperadas
                //em outras activity, neste caso na activity CalendarioShare.class
                Intent shareCalendario = new Intent(getContext(), CalendarioShare.class);
                //armazena a "dataCalendarioIntent", pegando os dados de calandario.getDataAddCalendario
                //buscando assim os dados da data que estão no Firebase
                shareCalendario.putExtra("dataCalendarioIntent", calendario.getDataAddCalendario());
                shareCalendario.putExtra("horaCalendarioIntent", calendario.getHoraAddCalendario());
                shareCalendario.putExtra("adversarioCalendarioIntent", calendario.getAdversarioAddCalendario());
                shareCalendario.putExtra("localCalendarioIntent", calendario.getLocalAddCalendario());
                //inicia a atividade com os dados armazenados em shareCalendario que serão recuperados
                //em CalendarioShare.class
                getContext().startActivity(shareCalendario);
            }
        });

        idAddCalendarioTextView.setText(calendario.getIdAddCalendario());
        dataAddCalendarioTextView.setText(calendario.getDataAddCalendario());
        horaAddCalendarioTextView.setText(calendario.getHoraAddCalendario());
        adversarioAddCalendarioTextView.setText(calendario.getAdversarioAddCalendario());
        localCalendarioTextView.setText(calendario.getLocalAddCalendario());

        return convertView;
    }
}



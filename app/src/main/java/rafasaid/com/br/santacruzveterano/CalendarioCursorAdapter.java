package rafasaid.com.br.santacruzveterano;

/**
 * Created by Rafael Said on 25/08/2017.
 */


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import rafasaid.com.br.santacruzveterano.data.CalendarioContract.CalendarioEntry;

public class CalendarioCursorAdapter extends CursorAdapter {

    /**
     * Constrói um novo {@link CalendarioCursorAdapter}.
     *
     * @param context O contexto
     * @param c       O cursor a partir do qual se obtém os dados.
     */
    public CalendarioCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_calendario, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //Procura as views individuais que nós queremos modificar na lista de item layout
        TextView dataListaCalendarioTextView = view.findViewById(R.id.data_lista_calendario);
        TextView adversarioListaCalendarioTextView = view.findViewById(R.id.adversario_lista_calendario);
        TextView localListaCalendarioTextView = view.findViewById(R.id.local_lista_calendario);

        //Procura as colunas de atributos do calendario que nos estamos interessados
        int dataListaCalendarioColumnIndex = cursor.getColumnIndex(CalendarioEntry.COLUMN_CALENDARIO_DATA);
        int adversarioListaCalendarioColumnIndex = cursor.getColumnIndex(CalendarioEntry.COLUMN_CALENDARIO_ADVERSARIO);
        int localListaCalendarioColumnIndex = cursor.getColumnIndex(CalendarioEntry.COLUMN_CALENDARIO_LOCAL);

        //Lê os atributos do calendario do Cursor para o calendario (partida marcada) atual
        String calendarioData = cursor.getString(dataListaCalendarioColumnIndex);
        String calendarioAdversario = cursor.getString(adversarioListaCalendarioColumnIndex);
        String calendarioLocal = cursor.getString(localListaCalendarioColumnIndex);

        //Atualiza as TextViews com os atributos do calendario atual (partida marcada atual)
        dataListaCalendarioTextView.setText(calendarioData);
        adversarioListaCalendarioTextView.setText(calendarioAdversario);
        localListaCalendarioTextView.setText(calendarioLocal);
    }
}

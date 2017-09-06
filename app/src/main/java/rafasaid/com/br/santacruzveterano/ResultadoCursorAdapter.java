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

import rafasaid.com.br.santacruzveterano.data.ResultadoContract.ResultadoEntry;

/**
 * {@link ResultadoCursorAdapter} é um adapter para list ou grid view que usa o {@link Cursor}
 * dos dados resultado como fonte de dados. Este adapter sabe como criar uma lista de itens
 * para cada linha de dados do resultado no {@link Cursor}.
 */
public class ResultadoCursorAdapter extends CursorAdapter {

    /**
     * Constrói um novo {@link ResultadoCursorAdapter}.
     *
     * @param context O contexto
     * @param c       O cursor a partir do qual se obtém os dados.
     */
    public ResultadoCursorAdapter(Context context, Cursor c) {
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
        return LayoutInflater.from(context).inflate(R.layout.list_view_resultado, parent, false);
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
        // Find individual views that we want to modify in the list item layout
        TextView dataListaResultadoTextView = view.findViewById(R.id.data_lista_resultado);
        TextView golsStaListaResultadoTextView = view.findViewById(R.id.gols_sta_lista_resultado);
        TextView golsAdversarioListaResultadoTextView = view.findViewById(R.id.gols_adversario_lista_resultado);
        TextView adversarioListaResultadoTextView = view.findViewById(R.id.adversario_lista_resultado);
        TextView golsListaResultadoTextView = view.findViewById(R.id.gols_lista_resultado);

        // Find the columns of pet attributes that we're interested in
        int dataListaResultadoColumnIndex = cursor.getColumnIndex(ResultadoEntry.COLUMN_RESULTADO_DATA);
        int golsStaListaResultadoColumnIndex = cursor.getColumnIndex(ResultadoEntry.COLUMN_GOLS_STA_RESUTALDO);
        int golsAdversarioListaResultadoColumnIndex = cursor.getColumnIndex(ResultadoEntry.COLUMN_GOLS_ADVERSARIO_RESUTALDO);
        int adversarioListaResultadoColumnIndex = cursor.getColumnIndex(ResultadoEntry.COLUMN_RESULTADO_ADVERSARIO);
        int golsListaResultadoColumnIndex = cursor.getColumnIndex(ResultadoEntry.COLUMN_RESULTADO_GOLS);

        // Read the pet attributes from the Cursor for the current pet
        String resultadoData = cursor.getString(dataListaResultadoColumnIndex);
        int resultadoGolsSta = cursor.getInt(golsStaListaResultadoColumnIndex);
        int resultadoGolsAdversario = cursor.getInt(golsAdversarioListaResultadoColumnIndex);
        String resultadoAdversario = cursor.getString(adversarioListaResultadoColumnIndex);
        String resultadoGols = cursor.getString(golsListaResultadoColumnIndex);

        // Update the TextViews with the attributes for the current pet
        dataListaResultadoTextView.setText(resultadoData);
        golsStaListaResultadoTextView.append(String.valueOf(resultadoGolsSta));
        golsAdversarioListaResultadoTextView.append(String.valueOf(resultadoGolsAdversario));
        adversarioListaResultadoTextView.setText(resultadoAdversario);
        golsListaResultadoTextView.setText(resultadoGols);
    }
}

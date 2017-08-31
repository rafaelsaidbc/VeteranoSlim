package rafasaid.com.br.santacruzveterano;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import rafasaid.com.br.santacruzveterano.data.CalendarioContract.CalendarioEntry;
import rafasaid.com.br.santacruzveterano.data.DbHelper;

public class CalendarioActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final int CALENDARIO_LOADER = 1;

    CalendarioCursorAdapter mCursorAdapterCalendario;

    private DbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        FloatingActionButton fabCalendario = (FloatingActionButton) findViewById(R.id.btn_add_calendario);
        fabCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCalendario = new Intent(CalendarioActivity.this, AdicionarCalendario.class);
                startActivity(intentCalendario);
            }
        });


        ListView calendarioListView = (ListView) findViewById(R.id.list_calendario);

        mCursorAdapterCalendario = new CalendarioCursorAdapter(this, null);

        calendarioListView.setAdapter(mCursorAdapterCalendario);

        calendarioListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(CalendarioActivity.this, AdicionarCalendario.class);

                Uri currentCalendarioUri = ContentUris.withAppendedId(CalendarioEntry.CONTENT_URI_CALENDARIO, id);

                intent.setData(currentCalendarioUri);

                startActivity(intent);
            }
        });

        getSupportLoaderManager().initLoader(CALENDARIO_LOADER, null, this);

        mDbHelper = new DbHelper(this);
    }

    private void insertCalendario() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        long newRowId = db.insert(CalendarioEntry.TABLE_NAME_CALENDARIO, null, values);

        Log.v("AdicionarCalendario", "Calendario adicionado" + newRowId);

    }

    private void deleteAllCalendario() {
        int rowsDeleted = getContentResolver().delete(CalendarioEntry.CONTENT_URI_CALENDARIO, null, null);
        Log.v("CalendarioActivity", rowsDeleted + " rows deleted from calendario database");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_calendario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_all_entries_calendario:
                //deleteAllCalendario();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                CalendarioEntry._ID,
                CalendarioEntry.COLUMN_CALENDARIO_DATA,
                CalendarioEntry.COLUMN_CALENDARIO_ADVERSARIO,
                CalendarioEntry.COLUMN_CALENDARIO_LOCAL};

        return new CursorLoader(this,       //parent activity context
                CalendarioEntry.CONTENT_URI_CALENDARIO, //provider contet URI to query
                projection,                 //colunas que incluirão o resultado cursor
                null,                       //sem clausula de seleção
                null,                       //sem seleção de argumentos
                null);                      //ordem de classificação padrão

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapterCalendario.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapterCalendario.swapCursor(null);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
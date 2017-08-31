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

import rafasaid.com.br.santacruzveterano.data.DbHelper;
import rafasaid.com.br.santacruzveterano.data.ResultadoContract.ResultadoEntry;

public class ResultadosActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    //variável global para os métodos loader, pode ser usado qualquer inteiro no final [= 0];
    private static final int RESULTADO_LOADER = 0;

    //cria um objeto mCursorAdapterCalendario do tipo ResultadoCursorAdapter, que será o adaptador para a ListView
    ResultadoCursorAdapter mCursorAdapter;

    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_add_resultado);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultadosActivity.this, AdicionarResultado.class);
                startActivity(intent);
            }
        });

        // Encontre o ListView que será preenchido com os dados do pet
        ListView resultadoListView = (ListView) findViewById(R.id.list_resultados);

        // Configura um Adapter para criar uma lista de itens para cada linha de dados de resultado
        //no Cursor. Aqui ainda não tem dados de resultado (até o loader terminar)), então
        //passa nulo [null] para o Cursor
        mCursorAdapter = new ResultadoCursorAdapter(this, null);
        resultadoListView.setAdapter(mCursorAdapter);

        resultadoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            // [int position (default vem com i)] = número inteiro que indica a posição do item na
            // ListView. [long id] indica o ID do item
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Create new intent to go to {@link AdicionarResultado}
                Intent intent = new Intent(ResultadosActivity.this, AdicionarResultado.class);

                // Form the content URI that represents the specific resultado that was clicked on,
                // by appending the "id" (passed as input to this method) onto the
                // {@link ResultadoEntry#CONTENT_URI_CALENDARIO}.
                // For example, the URI would be "content://rafasaid.com.br.santacruzveterano/resultados2017/2"
                // if the resultado with ID 2 was clicked on.
                Uri currentResultadoUri = ContentUris.withAppendedId(ResultadoEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentResultadoUri);

                // Launch the {@link AdicionarResultado} to display the data for the current resultado.
                startActivity(intent);
            }
        });

        // inicia o loader
        getSupportLoaderManager().initLoader(RESULTADO_LOADER, null, this);

        //faz a instância mDbHelper ocorrer nesta activity (context) [this]
        mDbHelper = new DbHelper(this);
    }

    //método para inserir resultados, fica em branco para adicionar os dados que for informado
    //pelo usuário
    private void insertResultado() {

        //inicia a conexão com a database de nome [db] e permite que seja escrito nela com o
        //método getWritableDatabase();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //cria um objeto de valores de conteúdo
        ContentValues values = new ContentValues();

        //insere as informações no banco de dados (database) usando o método insert();
        //objeto do banco de dados criado [db].executando o método de inserção [insert();]
        //passando os parâmetros, TABLE_CALENDARIO (nome da tabela), seguido de null (nulo), e os
        //valores de conteúdo [values]
        //long newRowId captura o método de inserção de dados em uma variável [newRowId] do tipo
        //long. Isto permite que seja repassado ao usuário uma mensagem após finalizar a
        //adição de informações
        long newRowId = db.insert(ResultadoEntry.TABLE_NAME, null, values);

        Log.v("AdicionarResultado", "Resultado adicionado" + newRowId);

    }

    /**
     * Helper method to delete all pets in the database.
     */
    private void deleteAllResultados() {
        int rowsDeleted = getContentResolver().delete(ResultadoEntry.CONTENT_URI, null, null);
        Log.v("ResultadosActivity", rowsDeleted + " rows deleted from resultado database");
    }


    //este método cria o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Infla (chama, coloca) o menu de opções, deixados na pasta /res/menu, neste caso o arquivo
        //menu_activity_resultados.xmlsultados.xml. Este menu adiciona itens à barra do app, parte superior da tela.
        getMenuInflater().inflate(R.menu.menu_activity_resultados, menu);
        return true;
    }

    //este método será chamado pelo sistema quando um item do menu for clicado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Usuário clicou em uma opção do menu na barra do aplicativo; os vários itens [case] do
        //menu são registrados aqui
        switch (item.getItemId()) {
            //responde ao click no "Delete all entries" do menu de opções
            case R.id.action_delete_all_entries:
                deleteAllResultados();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //define a projeção que especifica a coluna da tabela que nos preocupa
        String[] projection = {
                ResultadoEntry._ID,
                ResultadoEntry.COLUMN_RESULTADO_DATA,
                ResultadoEntry.COLUMN_RESULTADO_TIMES,
                ResultadoEntry.COLUMN_RESULTADO_GOLS};
        //este loade executará o ContentProvider query method em uma thread de fundo
        return new CursorLoader(this,       //parent activity context
                ResultadoEntry.CONTENT_URI, //provider contet URI to query
                projection,                 //colunas que incluirão o resultado cursor
                null,                       //sem clausula de seleção
                null,                       //sem seleção de argumentos
                null);                      //ordem de classificação padrão
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //atualiza {@link ResultadoCursorAdapter} com este novo cursor content os dados atualizados
        //do resultado
        mCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //Callback chamado quando os dados precisam ser deletados
        mCursorAdapter.swapCursor(null);

    }
}

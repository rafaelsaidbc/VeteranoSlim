package rafasaid.com.br.santacruzveterano.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import rafasaid.com.br.santacruzveterano.data.CalendarioContract.CalendarioEntry;
import rafasaid.com.br.santacruzveterano.data.ResultadoContract.ResultadoEntry;

/**
 * Created by Rafael on 24/08/2017.
 */

public class DbProvider extends ContentProvider {

    public static final String LOG_TAG = DbProvider.class.getSimpleName();
    //URI matcher code para o conteúdo URI para a tabela de resultados2017
    private static final int RESULTADOS = 100;
    //URI matcher code para o conteúdo URI para um único resultado na tabela de resultados2017
    private static final int RESULTADOS_ID = 101;

    private static final int CALENDARIO = 200;

    private static final int CALENDARIO_ID = 201;
    /*
    Objeto UriMatcher para encontrar um conteúdo URI no código correspondente. A entrada passada
     dentro do construtor representa o código de retorno do URI root. É comum usar NO_MATCH como
     entrada para este caso
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //Inicializados static. Isso é rodado pela primeira vez quando qualquer é chamado pela classe
    static {
        /*
        A chamada para addURI() é aqui, para todos conteúdo URI padrões que o provider deveria
        reconhecer. Todos caminho adicionar ao UriMatcher tem que corresponde ao código de retorno
        quando a correspondência é encontrada.
        TODO: adicionar 2 conteúdos URI no URI matcher
         */

        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI of the form "content://com.example.android.pets/pets" will map to the
        // integer code {@link #PETS}. This URI is used to provide access to MULTIPLE rows
        // of the pets table.
        sUriMatcher.addURI(ResultadoContract.CONTENT_AUTHORITY, ResultadoContract.PATH_RESULTADOS, RESULTADOS);
        sUriMatcher.addURI(CalendarioContract.CONTENT_AUTHORITY_CALENDARIO, CalendarioContract.PATH_CALENDARIO, CALENDARIO);


        // The content URI of the form "content://com.example.android.pets/pets/#" will map to the
        // integer code {@link #PET_ID}. This URI is used to provide access to ONE single row
        // of the pets table.
        //
        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
        // For example, "content://com.example.android.pets/pets/3" matches, but
        // "content://com.example.android.pets/pets" (without a number at the end) doesn't match.
        sUriMatcher.addURI(ResultadoContract.CONTENT_AUTHORITY, ResultadoContract.PATH_RESULTADOS + "/#", RESULTADOS_ID);
        sUriMatcher.addURI(CalendarioContract.CONTENT_AUTHORITY_CALENDARIO, CalendarioContract.PATH_CALENDARIO + "/#", CALENDARIO_ID);

    }

    //Objeto helper da database
    private DbHelper mDbHelper;

    //inicializar o provider e o objeto database helper [DbHelper.class]
    @Override
    public boolean onCreate() {
        //TODO: Criar e inicializar o objeto DbHelper para obter acesso à resultado database
        //Tenha certeza de que a variável é global, então a variável poderá ser utilizada por
        //outros métodos do ContentProvider
        mDbHelper = new DbHelper(getContext());
        return true;
    }

    //executa a consulta pelo URI dado. Usa a projeção [projection] dada, selection, selection
    //arguments, e ordenar.
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        //Obtem a database para leitura
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        //Este cursor regurará o resultado da questão [query]
        Cursor cursor;

        //Descobre se o URImatcher pode combinar o URI com um código específico
        int match = sUriMatcher.match(uri);
        switch (match) {
            case RESULTADOS:
                //Para o código TABELAS2017, consulta a tabela de resultados diretamente com os
                //dados selection, selectionArgs e sortOrder [ordem de classificação]. O cursor
                //pode conter várias linhas da tabela de resultados.
                //TODO: execute a consulta de banco de dados na tabela de resultados.
                cursor = database.query(ResultadoEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case CALENDARIO:
                cursor = database.query(CalendarioEntry.TABLE_NAME_CALENDARIO, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;

            case RESULTADOS_ID:
                //Para o código RESULTADOS_ID, extraia o ID do URI. Para um exemplo de URI
                //como "content:// android.example.com.resultado/resultados2017/3", a seleção
                //será um String array contendo a ID real de 3, nesse caso. Para cada "?" na
                //seleção, precisamos ter um elemento na selectionArgs que preencherão o "?".
                //Uma vez que temos 1 ponto de interrogação na selection,, temos 1 String nos
                //selection arguments String array
                selection = ResultadoContract.ResultadoEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                //Isso executará uma consulta na tabela de resultados2017 onde o _ID é igual a 3
                //para retornar um Cursor contendo essa linha da tabela
                cursor = database.query(ResultadoContract.ResultadoEntry.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;

            case CALENDARIO_ID:
                selection = CalendarioEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(CalendarioEntry.TABLE_NAME_CALENDARIO, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);

        }

        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // retorna o cursor
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case RESULTADOS:
                return insertResultado(uri, contentValues);

            case CALENDARIO:
                return insertCalendario(uri, contentValues);

            default:
                throw new IllegalArgumentException("Inserção não suportada pelo " + uri);
        }
    }

    //inserir novos dados no provider com os ContentValues informados
    private Uri insertResultado(Uri uri, ContentValues values) {

        //Checar se o nome é válido ou não, se a data é uma String
        String data = values.getAsString(ResultadoEntry.COLUMN_RESULTADO_DATA);
        if (data == null) {
            throw new IllegalArgumentException("Data inválida, tem que ser do tipo texto");
        }

        Integer golsStaResultado = values.getAsInteger(ResultadoEntry.COLUMN_GOLS_STA_RESUTALDO);
        if (golsStaResultado != null && golsStaResultado < 0) {
            throw new IllegalArgumentException("Resultado requer valor válido");
        }

        Integer golsAdversarioResultado = values.getAsInteger(ResultadoEntry.COLUMN_GOLS_ADVERSARIO_RESUTALDO);
        if (golsAdversarioResultado != null && golsAdversarioResultado < 0) {
            throw new IllegalArgumentException("Resultado requer valor válido");
        }

        //checar se o resultado é válido, requer uma String
        String adversario = values.getAsString(ResultadoEntry.COLUMN_RESULTADO_ADVERSARIO);
        if (adversario == null) {
            throw new IllegalArgumentException("Inválido. Digite no formato TimeA 1 x 1 TimeB");
        }

        String gols = values.getAsString(ResultadoEntry.COLUMN_RESULTADO_GOLS);
        if (gols == null) {
            throw new IllegalArgumentException("Ninguém marcou gols...");
        }

        //Obtem modo escrever na database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        //insere um novo resultado com os valores dados
        long id = database.insert(ResultadoEntry.TABLE_NAME, null, values);

        //se o ID for = -1 então a inserção falhou. Log e erro retornam nulo
        if (id == -1) {
            Log.e(LOG_TAG, "Falha ao inserir linha para " + uri);
            return null;
        }
        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);


        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertCalendario(Uri uri, ContentValues values) {

        //Checar se o nome é válido ou não, se a data é uma String
        String dataCalendario = values.getAsString(CalendarioEntry.COLUMN_CALENDARIO_DATA);
        if (dataCalendario == null) {
            throw new IllegalArgumentException("Data inválida, tem que ser do tipo texto");
        }

        //checar se o adversário é válido, requer uma String
        String adversario = values.getAsString(CalendarioEntry.COLUMN_CALENDARIO_ADVERSARIO);
        if (adversario == null) {
            throw new IllegalArgumentException("Inválido. Digite no formato Adversário");
        }

        String local = values.getAsString(CalendarioEntry.COLUMN_CALENDARIO_LOCAL);
        if (local == null) {
            throw new IllegalArgumentException("Tem que ter um local para o jogo...");
        }

        //Obtem modo escrever na database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        //insere um novo calendario com os valores dados
        long id = database.insert(CalendarioEntry.TABLE_NAME_CALENDARIO, null, values);

        //se o ID for = -1 então a inserção falhou. Log e erro retornam nulo
        if (id == -1) {
            Log.e(LOG_TAG, "Falha ao inserir linha para " + uri);
            return null;
        }
        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);


        return ContentUris.withAppendedId(uri, id);
    }

    //Atualizar os dados informados pela selection e pela selection arguments, com os novos
    //ContentValues
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case RESULTADOS:
                return updateResultado(uri, contentValues, selection, selectionArgs);

            case CALENDARIO:
                return updateCalendario(uri, contentValues, selection, selectionArgs);

            case RESULTADOS_ID:
                // Para o código PET_ID, extraia o ID do URI,
                // para que saibamos qual registro atualizar. Selection será "_id=?" and selection
                // args será um String array contendo o atual ID.
                selection = ResultadoEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateResultado(uri, contentValues, selection, selectionArgs);

            case CALENDARIO_ID:
                selection = CalendarioEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateCalendario(uri, contentValues, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Atualização não é suportada pelo " + uri);

        }
    }

    /**
     * Atualize pets no banco de dados com os content values dados. Aplique as mudanças aos registros
     * especificados no selection e selection args (que podem ser 0 ou 1 ou mais pets).
     * Retorne o número de registros que foram atualizados com sucesso.
     */
    private int updateResultado(Uri uri, ContentValues values, String selection, String[] selectionArgs) {


        // If the {@link PetEntry#COLUMN_PET_GENDER} key is present,
        // check that the gender value is valid.
        if (values.containsKey(ResultadoEntry.COLUMN_RESULTADO_DATA)) {
            String data = values.getAsString(ResultadoEntry.COLUMN_RESULTADO_DATA);
            if (data == null) {
                throw new IllegalArgumentException("Resultado precisa de uma data");
            }
        }

        if (values.containsKey(ResultadoEntry.COLUMN_GOLS_STA_RESUTALDO)) {
            Integer golsStaResultado = values.getAsInteger(ResultadoEntry.COLUMN_GOLS_STA_RESUTALDO);
            if (golsStaResultado != null & golsStaResultado < 0) {
                throw new IllegalArgumentException("Requer valor válido");
            }
        }

        if (values.containsKey(ResultadoEntry.COLUMN_GOLS_ADVERSARIO_RESUTALDO)) {
            Integer golsAdversarioResultado = values.getAsInteger(ResultadoEntry.COLUMN_GOLS_ADVERSARIO_RESUTALDO);
            if (golsAdversarioResultado != null & golsAdversarioResultado < 0) {
                throw new IllegalArgumentException("Requer valor válido");
            }
        }


        // If the {@link PetEntry#COLUMN_PET_WEIGHT} key is present,
        // check that the weight value is valid.
        if (values.containsKey(ResultadoEntry.COLUMN_RESULTADO_ADVERSARIO)) {
            String adversario = values.getAsString(ResultadoEntry.COLUMN_RESULTADO_ADVERSARIO);

            if (adversario == null) {
                throw new IllegalArgumentException("Resultado requer times");
            }
        }

        if (values.containsKey(ResultadoEntry.COLUMN_RESULTADO_GOLS)) {
            String gols = values.getAsString(ResultadoEntry.COLUMN_RESULTADO_GOLS);
            if (gols == null) {
                throw new IllegalArgumentException("Ninguém marcou gol");
            }
        }

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }
        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(ResultadoEntry.TABLE_NAME, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }

    private int updateCalendario(Uri uri, ContentValues values, String selection, String[] selectionArgs) {


        // If the {@link PetEntry#COLUMN_PET_GENDER} key is present,
        // check that the gender value is valid.
        if (values.containsKey(CalendarioEntry.COLUMN_CALENDARIO_DATA)) {
            String dataCalendario = values.getAsString(CalendarioEntry.COLUMN_CALENDARIO_DATA);
            if (dataCalendario == null) {
                throw new IllegalArgumentException("Calendario precisa de uma data");
            }
        }

        // If the {@link PetEntry#COLUMN_PET_WEIGHT} key is present,
        // check that the weight value is valid.
        if (values.containsKey(CalendarioEntry.COLUMN_CALENDARIO_ADVERSARIO)) {
            String adversario = values.getAsString(CalendarioEntry.COLUMN_CALENDARIO_ADVERSARIO);

            if (adversario == null) {
                throw new IllegalArgumentException("Calendario requer adversário");
            }
        }

        if (values.containsKey(CalendarioEntry.COLUMN_CALENDARIO_LOCAL)) {
            String local = values.getAsString(CalendarioEntry.COLUMN_CALENDARIO_LOCAL);
            if (local == null) {
                throw new IllegalArgumentException("Não há local para o jogo");
            }
        }

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }
        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(CalendarioEntry.TABLE_NAME_CALENDARIO, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }

    //Deleta todos os dados informados pela selection e pela selection arguments
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Obtém banco de dados com permissão de escrita
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case RESULTADOS:

                // Deleta todos os registros que correspondem ao selection e selection args
                rowsDeleted = database.delete(ResultadoEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case CALENDARIO:
                rowsDeleted = database.delete(CalendarioEntry.TABLE_NAME_CALENDARIO, selection, selectionArgs);
                break;

            case RESULTADOS_ID:
                // Deleta um único registro dado pelo ID na URI
                selection = ResultadoEntry._ID + "=?";

                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                rowsDeleted = database.delete(ResultadoEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case CALENDARIO_ID:
                selection = CalendarioEntry._ID + "=?";

                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                rowsDeleted = database.delete(CalendarioEntry.TABLE_NAME_CALENDARIO,
                        selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;
    }

    //retorna o tipo de dado MIME do conteúdo URI
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {

            case RESULTADOS:
                return ResultadoEntry.CONTENT_LIST_TYPE;

            case CALENDARIO:
                return CalendarioEntry.CONTENT_LIST_TYPE_CALENDARIO;

            case RESULTADOS_ID:
                return ResultadoEntry.CONTENT_ITEM_TYPE;

            case CALENDARIO_ID:
                return CalendarioEntry.CONTENT_LIST_TYPE_CALENDARIO;

            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

}
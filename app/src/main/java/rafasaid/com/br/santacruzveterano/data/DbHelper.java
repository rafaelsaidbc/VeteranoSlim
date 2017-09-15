package rafasaid.com.br.santacruzveterano.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rafael Said on 23/08/2017.
 */

//classe que liga a um banco de dados retornando o objeto [SQLiteDatabase] do banco de dados; cria o banco de dados
//se ele ainda não existir
    /*
    C = create
    R = read
    U = update
    D = delete
     */

public class DbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "santacruz.db";

    private static final int DATABASE_VERSION = 2017;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
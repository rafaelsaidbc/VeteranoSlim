package rafasaid.com.br.santacruzveterano.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by Rafael Said on 23/08/2017.
 */

public final class CalendarioContract {

    /*
    O conteúdo de autoridade [Content authority] é um nome para todos content provider, similar
      ao relacionamento entre um domain name e o website. Um String conveniente para usar para
      o content authority é o pacote [package] do nome do app, que é uma garantia de ser único
      no dispositivo
     */
    //o problema era apenas o CONTENT_AUTHORITY que estava errado...
    public static final String CONTENT_AUTHORITY_CALENDARIO = "rafasaid.com.br.santacruzveterano";
    /*
    Usar CONTENT_AUTHORITY_CALENDARIO para criar a base de todos URI's que os apps irão usar para contactar
    o content provider
     */
    public static final Uri BASE_CONTENT_URI_CALENDARIO = Uri.parse("content://" + CONTENT_AUTHORITY_CALENDARIO);
    /*
    Possível pasta (anexada como base URI da possível URI's). Para instanciar
    content://com.example.entomologia.calendarioresultado/calendario/ é uma pasta válida para olhar os
    dados dos calendarios.
     */
    public static final String PATH_CALENDARIO = "calendario";

    private CalendarioContract() {
    }

    public static final class CalendarioEntry implements BaseColumns {

        //O conteúdo URI para acessar os dados de calendario no provider
        public static final Uri CONTENT_URI_CALENDARIO = Uri.withAppendedPath(BASE_CONTENT_URI_CALENDARIO, PATH_CALENDARIO);

        /**
         * O tipo MIME do {@link #CONTENT_URI_CALENDARIO} para uma lista de calendarios.
         */
        public static final String CONTENT_LIST_TYPE_CALENDARIO =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY_CALENDARIO + "/" + PATH_CALENDARIO;

        /**
         * O tipo MIME do {@link #CONTENT_URI_CALENDARIO} para um único calendario.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY_CALENDARIO + "/" + PATH_CALENDARIO;

        public final static String TABLE_NAME_CALENDARIO = "calendario";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CALENDARIO_DATA = "dataCalendario";
        //aqui no lugar de "times" deveria ser "adversário", não consigo consertar o erro
        public final static String COLUMN_CALENDARIO_ADVERSARIO = "adversarioCalendario";
        //aqui no lugar de "gols" deveria ser "local", não consigo consertar o erro
        public final static String COLUMN_CALENDARIO_LOCAL = "localCalendario";
    }
}

package rafasaid.com.br.santacruzveterano.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Rafael Said on 23/08/2017.
 */

public final class ResultadoContract {

    /*
    O conteúdo de autoridade [Content authority] é um nome para todos content provider, similar
      ao relacionamento entre um domain name e o website. Um String conveniente para usar para
      o content authority é o pacote [package] do noma do app, que é uma garantia de ser único
      no dispositivo
     */
    public static final String CONTENT_AUTHORITY = "rafasaid.com.br.santacruzveterano";
    /*
    Usar CONTENT_AUTHORITY para criar a base de todos URI's que os apps irão usar para contactar
    o content provider
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    /*
    Possível pasta (anexada como base URI da possível URI's). Para instanciar
    content://rafasaid.com.br.santacruzveterano/resultados2017/ é uma pasta válida para olhar os
    dados dos resultados.
     */
    public static final String PATH_RESULTADOS = "resultados2017";

    private ResultadoContract() {
    }

    public static final class ResultadoEntry implements BaseColumns {

        //O conteúdo URI para acessar os dados de resultado no provider
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_RESULTADOS);

        /**
         * O tipo MIME do {@link #CONTENT_URI} para uma lista de resultados.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESULTADOS;

        /**
         * O tipo MIME do {@link #CONTENT_URI} para um único resultado.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESULTADOS;

        public final static String TABLE_NAME = "resultados2017";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_RESULTADO_DATA = "data";
        public final static String COLUMN_GOLS_STA_RESUTALDO = "golsSantaCruzResultado";
        public final static String COLUMN_GOLS_ADVERSARIO_RESUTALDO = "golsAdversarioResultado";
        public final static String COLUMN_RESULTADO_ADVERSARIO = "adversario";
        public final static String COLUMN_RESULTADO_GOLS = "gols";
    }
}
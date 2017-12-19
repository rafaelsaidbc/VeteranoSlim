package rafasaid.com.br.santacruzveterano.estatisticas;

/**
 * Created by Entomologia on 02/10/2017.
 */

//O Adapter define como as informações serão mostradas no layout
public class Estatisticas {
    public String nomeMarcador;
    int golsMarcador;

    public Estatisticas(String nomeMarcador, int golsMarcador) {
        this.nomeMarcador = nomeMarcador;
        this.golsMarcador = golsMarcador;
    }
}

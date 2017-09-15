package rafasaid.com.br.santacruzveterano;

/**
 * Created by Entomologia on 30/08/2017.
 */

public class ResultadoFirebase {

    //classes utilizadas para implementar a database
    private String dataAddResultado;
    private String golsStaCruzAddResultado;
    private String golsAdversarioAddResultado;
    private String adversarioAddResultado;
    private String golsMarcadoresAddResultado;

    public ResultadoFirebase() {
    }

    public ResultadoFirebase(String dataAddResultado, String golsStaCruzAddResultado, String golsAdversarioAddResultado,
                             String adversarioAddResultado, String golsMarcadoresAddResultado) {
        this.dataAddResultado = dataAddResultado;
        this.golsStaCruzAddResultado = golsStaCruzAddResultado;
        this.golsAdversarioAddResultado = golsAdversarioAddResultado;
        this.adversarioAddResultado = adversarioAddResultado;
        this.golsMarcadoresAddResultado = golsMarcadoresAddResultado;
    }

    public String getDataAddResultado() {
        return dataAddResultado;
    }

    public void setDataAddResultado(String dataAddResultado) {
        this.dataAddResultado = dataAddResultado;
    }

    public String getGolsStaCruzAddResultado() {
        return golsStaCruzAddResultado;
    }

    public void setGolsStaCruzAddResultado(String golsStaCruzAddResultado) {
        this.golsStaCruzAddResultado = golsStaCruzAddResultado;
    }

    public String getGolsAdversarioAddResultado() {
        return golsAdversarioAddResultado;
    }

    public String getAdversarioAddResultado() {
        return adversarioAddResultado;
    }

    public void setAdversarioAddResultado(String adversarioAddResultado) {
        this.adversarioAddResultado = adversarioAddResultado;
    }

    public void setAdversarioAddResultado() {
        this.adversarioAddResultado = adversarioAddResultado;
    }

    public String getGolsMarcadoresAddResultado() {
        return golsMarcadoresAddResultado;
    }

    public void setGolsMarcadoresAddResultado() {
        this.golsMarcadoresAddResultado = golsMarcadoresAddResultado;
    }

}

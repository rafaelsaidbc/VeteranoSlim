package rafasaid.com.br.santacruzveterano;

/**
 * Created by Entomologia on 20/09/2017.
 */

public class ResultadoFirebase2016 {

    //classes utilizadas para implementar a database
    private String anoAddResultado;
    private String idAddResultado;
    private String dataAddResultado;
    private String golsStaCruzAddResultado;
    private String golsAdversarioAddResultado;
    private String adversarioAddResultado;
    private String golsMarcadoresAddResultado;

    public ResultadoFirebase2016() {
    }

    public ResultadoFirebase2016(String anoAddResultado2016, String idAddResultado2016, String dataAddResultado2016,
                                 String golsStaCruzAddResultado2016, String golsAdversarioAddResultado2016,
                                 String adversarioAddResultado2016, String golsMarcadoresAddResultado2016) {
        this.anoAddResultado = anoAddResultado2016;
        this.idAddResultado = idAddResultado2016;
        this.dataAddResultado = dataAddResultado2016;
        this.golsStaCruzAddResultado = golsStaCruzAddResultado2016;
        this.golsAdversarioAddResultado = golsAdversarioAddResultado2016;
        this.adversarioAddResultado = adversarioAddResultado2016;
        this.golsMarcadoresAddResultado = golsMarcadoresAddResultado2016;
    }

    public String getAnoAddResultado() {
        return anoAddResultado;
    }

    public void setAnoAddResultado(String anoAddResultado2016) {
        this.anoAddResultado = anoAddResultado2016;
    }

    public String getIdAddResultado() {
        return idAddResultado;
    }

    public void setIdAddResultado(String idAddResultado) {
        this.idAddResultado = idAddResultado;
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



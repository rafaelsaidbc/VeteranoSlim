package rafasaid.com.br.santacruzveterano;

/**
 * Created by Entomologia on 30/08/2017.
 */

public class CalendarioFirebase {

    //classes utilizadas para implementar a database
    private String dataAddCalendario;
    private String horaAddCalendario;
    private String adversarioAddCalendario;
    private String localAddCalendario;

    public CalendarioFirebase() {
    }

    public CalendarioFirebase(String dataAddCalendario, String horaAddCalendario, String adversarioAddCalendario,
                              String localAddCalendario) {
        this.dataAddCalendario = dataAddCalendario;
        this.horaAddCalendario = horaAddCalendario;
        this.adversarioAddCalendario = adversarioAddCalendario;
        this.localAddCalendario = localAddCalendario;
    }

    public String getDataAddCalendario() {
        return dataAddCalendario;
    }

    public void setDataAddCalendario(String dataAddCalendario) {
        this.dataAddCalendario = dataAddCalendario;
    }

    public String getHoraAddCalendario() {
        return horaAddCalendario;
    }

    public void setHoraAddCalendario(String horaAddCalendario) {
        this.horaAddCalendario = horaAddCalendario;
    }

    public String getAdversarioAddCalendario() {
        return adversarioAddCalendario;
    }

    public void setAdversarioAddCalendario(String adversarioAddCalendario) {
        this.adversarioAddCalendario = adversarioAddCalendario;
    }

    public String getLocalAddCalendario() {
        return localAddCalendario;
    }

    public void setLocalAddCalendario() {
        this.localAddCalendario = localAddCalendario;
    }
}

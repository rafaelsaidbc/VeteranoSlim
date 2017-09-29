package rafasaid.com.br.santacruzveterano.jogadores.firebase;

/**
 * Created by Entomologia on 30/08/2017.
 */

public class JogadoresFirebase {

    //classes utilizadas para implementar a database
    private String anoIngresso;
    private String apelidoJogador;
    private String dataNascimento;
    private String fotoJogador;
    private String nomeJogador;
    private String numeroCamisa;
    private String posicaoJogador;

    public JogadoresFirebase() {
    }

    public JogadoresFirebase(String anoIngresso, String apelidoJogador, String dataNascimento, String fotoJogador, String nomeJogador,
                             String numeroCamisa, String posicaoJogador) {
        this.anoIngresso = anoIngresso;
        this.apelidoJogador = apelidoJogador;
        this.dataNascimento = dataNascimento;
        this.fotoJogador = fotoJogador;
        this.nomeJogador = nomeJogador;
        this.numeroCamisa = numeroCamisa;
        this.posicaoJogador = posicaoJogador;
    }

    public String getanoIngresso() {
        return anoIngresso;
    }

    public void setanoIngresso(String anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    public String getapelidoJogador() {
        return apelidoJogador;
    }

    public void setapelidoJogador(String apelidoJogador) {
        this.apelidoJogador = apelidoJogador;
    }

    public String getdataNascimento() {
        return dataNascimento;
    }

    public void setdataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getfotoJogador() {
        return fotoJogador;
    }

    public void setfotoJogador(String fotoJogador) {
        this.fotoJogador = fotoJogador;
    }

    public String getnomeJogador() {
        return nomeJogador;
    }

    public String getnumeroCamisa() {
        return numeroCamisa;
    }

    public void setnumeroCamisa(String numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public void setnumeroCamisa() {
        this.numeroCamisa = numeroCamisa;
    }

    public String getposicaoJogador() {
        return posicaoJogador;
    }

    public void setposicaoJogador() {
        this.posicaoJogador = posicaoJogador;
    }

}

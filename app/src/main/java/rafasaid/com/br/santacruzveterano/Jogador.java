package rafasaid.com.br.santacruzveterano;

/**
 * Created by Rafael Said on 14/08/17.
 * <p>
 * representa um vocabulário de palavras que o usuário quer aprender.
 * Contém a palavra do idioma padrão (nativo), neste caso o inglês, e a palavra traduzida para o idioma
 * que se quer aprender, neste caso o Miwok
 */


public class Jogador {

    private int mJogadorImageView;

    //Define o nome do jogador
    private String mNomeJogador;

    //define o apelido do jogador
    private String mApelidoJogador;

    //Define a data de nascimento do jogador
    private String mNascimentoData;

    //Define a posição do jogador
    private String mPosicaoJogador;

    //Define o número da camisa do jogador
    private String mNumeroCamisa;

    //define o ano que o jogador entrou no time
    private String mAnoIngresso;

    public Jogador(int jogadorImageView, String nomeJogador, String apelidoJogador, String nascimentoData, String posicaoJogador, String numeroCamisa, String anoIngresso) {
        mJogadorImageView = jogadorImageView;
        mNomeJogador = nomeJogador;
        mApelidoJogador = apelidoJogador;
        mNascimentoData = nascimentoData;
        mPosicaoJogador = posicaoJogador;
        mNumeroCamisa = numeroCamisa;
        mAnoIngresso = anoIngresso;
    }

    //get the default translation word.
    /*public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    */

    public int getJogadorImageView() {
        return mJogadorImageView;
    }

    //retorna o nome do jogador
    public String getNomeJogador() {
        return mNomeJogador;
    }

    //retorna o apelido do jogador
    public String getApelidoJogador() {
        return mApelidoJogador;
    }

    //retorna a data de nascimento do jogador
    public String getNascimentoData() {
        return mNascimentoData;
    }

    //retorna a posição do jogador
    public String getPosicaoJogador() {
        return mPosicaoJogador;
    }

    //retorna número da camisa do jogador
    public String getNumeroCamisa() {
        return mNumeroCamisa;
    }

    //retorna o ano que o jogador entrou no time
    public String getAnoIngresso() {
        return mAnoIngresso;
    }

}

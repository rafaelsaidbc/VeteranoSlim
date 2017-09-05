package rafasaid.com.br.santacruzveterano;

/**
 * Created by Entomologia on 05/09/2017.
 */

public class FotosMessage {

    private String textFotos;
    private String nameFotos;
    private String photoUrlFotos;

    public FotosMessage() {
    }

    public FotosMessage(String textFotos, String nameFotos, String photoUrlFotos) {
        this.textFotos = textFotos;
        this.nameFotos = nameFotos;
        this.photoUrlFotos = photoUrlFotos;
    }

    public String getTextFotos() {
        return textFotos;
    }

    public void setTextFotos(String textFotos) {
        this.textFotos = textFotos;
    }

    public String getNameFotos() {
        return nameFotos;
    }

    public void setNameFotos(String nameFotos) {
        this.nameFotos = nameFotos;
    }

    public String getPhotoUrlFotos() {
        return photoUrlFotos;
    }

    public void setPhotoUrlFotos(String photoUrlFotos) {
        this.photoUrlFotos = photoUrlFotos;
    }
}

package rafasaid.com.br.santacruzveterano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FotosFullscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fotos_fullscreen);

        //recupera os dados que foram armazenados na activity FotosAdapter, usa-se final para
        //dar certo
        final Intent intent = getIntent();

        //cria uma String com nome fotosFullscreen, intent recupera os dados armazenados em outra
        // activity utilizando getStringExtra [nome com o qual a variável foi armazenada na outra
        //activity, neste caso fotosFullscreen]
        final String fotosFullscreen = intent.getStringExtra("fotosFullscreen");

        //cria uma variável fotosFullScreenImageView do tipo ImageView e coloca para ser mostrada no
        //local findViewById(R.id.fotoFullScreen)
        ImageView fotosFullScreenImageView = (ImageView) findViewById(R.id.fotoFullScreen);

        //pega a referência à URL da imagem no Firebase e carrega a imagem
        Glide.with(fotosFullScreenImageView.getContext()).load(fotosFullscreen)
                .into(fotosFullScreenImageView);
        fotosFullScreenImageView.setVisibility(View.VISIBLE);

    }
}

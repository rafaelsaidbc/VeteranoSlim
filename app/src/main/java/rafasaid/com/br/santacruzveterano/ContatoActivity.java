package rafasaid.com.br.santacruzveterano;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ContatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        Button btnAgendarJogos = (Button) findViewById(R.id.btnAgendarJogos);
        btnAgendarJogos.setOnClickListener(new View.OnClickListener() {
            Intent telefoneIntent = new Intent(Intent.ACTION_DIAL);

            @Override
            public void onClick(View view) {
                String telefone = "+3138853057";
                telefoneIntent.setData(Uri.parse("tel:" + telefone));
                startActivity(telefoneIntent);
            }
        });

    }
}

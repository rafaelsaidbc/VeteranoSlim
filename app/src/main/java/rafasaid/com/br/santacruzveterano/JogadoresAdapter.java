package rafasaid.com.br.santacruzveterano;

/**
 * Created by Rafael Said on 14/08/2017.
 */


import android.widget.ArrayAdapter;

/**
 * {@link JogadoresAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Jogadores} objects.
 */
public class JogadoresAdapter {

    /*

    public JogadoresAdapter(Context context, ArrayList<rafasaid.com.br.santacruzveterano.Jogadores> jogadoreses) {
        super(context, 0, jogadoreses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_jogadores, parent, false);
        }
        // Get the {@link Jogador} object located at this position in the list
        rafasaid.com.br.santacruzveterano.Jogadores currentJogadores = getItem(position);

        ImageView goleiroView = (ImageView) listItemView.findViewById(R.id.goleiro_rosto);
        goleiroView.setImageResource(currentJogadores.getGoleiroImageView());

        ImageView zagueiroView = (ImageView) listItemView.findViewById(R.id.zagueiro1_rosto);
        goleiroView.setImageResource(currentJogadores.getGoleiroImageView());

        ImageView meioCampoView = (ImageView) listItemView.findViewById(R.id.meio_campo1_rosto);
        goleiroView.setImageResource(currentJogadores.getGoleiroImageView());

        ImageView atacanteView = (ImageView) listItemView.findViewById(R.id.atacante1_rosto);
        goleiroView.setImageResource(currentJogadores.getGoleiroImageView());

        ImageView arbitroView = (ImageView) listItemView.findViewById(R.id.arbitro_rosto);
        goleiroView.setImageResource(currentJogadores.getGoleiroImageView());

        ImageView gandulaView = (ImageView) listItemView.findViewById(R.id.gandula_rosto);
        goleiroView.setImageResource(currentJogadores.getGoleiroImageView());

        ImageView torcedorView = (ImageView) listItemView.findViewById(R.id.torcedor1_rosto);
        goleiroView.setImageResource(currentJogadores.getGoleiroImageView());



        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

    */
}

package cc.slideworks.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import cc.slideworks.pokedex.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tela_detalhes extends AppCompatActivity {

    private static final String TAG = "acionar";
    private String type = "";
    private String img;

    /*@BindView(R.id.nome_poke)
    TextView nome_poke;

    @BindView(R.id.species_poke)
    TextView species;

    @BindView(R.id.types_poke)
    TextView types_poke;

    @BindView(R.id.id_poke)
    TextView id_poke;

    @BindView(R.id.foto_poke)
    ImageView foto;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhes);

        //ButterKnife.bind(this);

        Intent intent = getIntent();
        Pokemon pokemon = (Pokemon) intent.getSerializableExtra("pokemon");

        Call<PokemonDetalhes> requestPokemons = new RetrofitInicializador().getPokedexService().pokemon(pokemon.getResource_uri());

        requestPokemons.enqueue(new Callback<PokemonDetalhes>() {

            @Override
            public void onResponse(Call<PokemonDetalhes> call, Response<PokemonDetalhes> response) {

                PokemonDetalhes pokemon = response.body();

                TextView id_poke = (TextView) findViewById(R.id.id_poke);
                id_poke.setText("#"+pokemon.getPkdx_id());

                String imgCompleta = "http://pokeapi.co/"+pokemon.getPicture();
                ImageView foto = (ImageView) findViewById(R.id.foto_poke);
                Picasso.with(tela_detalhes.this).load(imgCompleta).into(foto);

                TextView nome_poke = (TextView) findViewById(R.id.nome_poke);
                nome_poke.setText(pokemon.getName());

                TextView species = (TextView) findViewById(R.id.species_poke);

                if(pokemon.getSpecies() != "") {
                    species.setText(pokemon.getSpecies());
                }else{
                    species.setText("Not found");
                }

                List<Types> types = pokemon.getTypes();

                for (Types t : types){
                    type = type + t.getName() + " | ";
                }

                TextView types_poke = (TextView) findViewById(R.id.types_poke);
                types_poke.setText(type);

            }

            @Override
            public void onFailure(Call<PokemonDetalhes> call, Throwable t) {
                Log.e(TAG, "Erro de Conexão: " + t.getMessage());
            }

        });

    }
}

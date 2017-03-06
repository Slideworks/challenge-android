package cc.slideworks.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import android.content.Intent;
import android.widget.SearchView;
import cc.slideworks.pokedex.adapter.PokemonsAdapter;
import cc.slideworks.pokedex.model.PokemonDAO;
import cc.slideworks.pokedex.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tela_principal extends AppCompatActivity {

    private static final String TAG = "acionar";
    private ListView ListaPokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);


        Call<Pokemons> requestPokemons = new RetrofitInicializador().getPokedexService().lista();

        requestPokemons.enqueue(new Callback<Pokemons>() {

            @Override
            public void onResponse(Call<Pokemons> call, Response<Pokemons> response) {

                if(!response.isSuccessful()){
                    Log.i(TAG, "Erro de Resposta: " + response.code());
                }else{
                    Pokemons pokemons = response.body();

                    for (Pokemon p : pokemons.pokemon){

                        PokemonDAO dao = new PokemonDAO(tela_principal.this);
                        dao.insere(p.getName(), p.getResource_uri());
                        dao.close();

                    }

                }

            }

            @Override
            public void onFailure(Call<Pokemons> call, Throwable t) {
                Log.e(TAG, "Erro de Conexão: " + t.getMessage());
            }

        });


        ListaPokemons = (ListView) findViewById(R.id.lista_pokemons);

        ListaPokemons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon pokemon = (Pokemon) ListaPokemons.getItemAtPosition(position);
                Intent detalhesIntent = new Intent(tela_principal.this, tela_detalhes.class);
                detalhesIntent.putExtra("pokemon", pokemon);
                startActivity(detalhesIntent);
            }
        });

        listaPokemons();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                buscaPokemons(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void listaPokemons() {
        PokemonDAO dao = new PokemonDAO(this);
        List<Pokemon> pokemons = dao.buscaPokemons();
        dao.close();

        PokemonsAdapter adapter = new PokemonsAdapter(this, pokemons);
        ListaPokemons.setAdapter(adapter);
    }

    private void buscaPokemons(String busca) {
        PokemonDAO dao = new PokemonDAO(this);
        List<Pokemon> pokemons = dao.buscaPokemonsQuery(busca);
        dao.close();

        PokemonsAdapter adapter = new PokemonsAdapter(this, pokemons);
        ListaPokemons.setAdapter(adapter);
    }


}

package cc.slideworks.pokedex.services;

import cc.slideworks.pokedex.PokemonDetalhes;
import cc.slideworks.pokedex.Pokemons;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokedexService {

    public static final String URL = "http://pokeapi.co/";

    @GET("api/v1/pokedex/1/")
    Call<Pokemons> lista();

    @GET("{resource_uri}")
    Call<PokemonDetalhes> pokemon(@Path("resource_uri") String resource_uri);

}

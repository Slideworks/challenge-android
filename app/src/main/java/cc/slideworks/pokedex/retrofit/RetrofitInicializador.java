package cc.slideworks.pokedex.retrofit;

import cc.slideworks.pokedex.services.PokedexService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador(){

        retrofit = new Retrofit.Builder()
                .baseUrl(PokedexService.URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }

    public PokedexService getPokedexService(){
        return retrofit.create(PokedexService.class);
    }

}

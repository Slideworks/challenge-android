package cc.slideworks.pokedex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Sony on 01/03/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemons {

    public List<Pokemon> pokemon;

}

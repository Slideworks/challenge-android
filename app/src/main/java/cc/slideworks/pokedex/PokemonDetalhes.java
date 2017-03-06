package cc.slideworks.pokedex;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDetalhes implements Serializable {

    public String name;
    public String pkdx_id;
    public String picture;
    public List<Types> types;
    public String species;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }


    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkdx_id() {
        return pkdx_id;
    }

    public void setPkdx_id(String pkdx_id) {
        this.pkdx_id = pkdx_id;
    }

    public String getPicture() {
        return "media/img/"+pkdx_id+".png";
    }

    public void setPicture(String picture) {
        this.picture = "media/img/"+picture+".png";
    }

}

package cc.slideworks.pokedex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Types implements Serializable {

    public String name;

    public String getName() {
        return name.toString() ;
    }

    public void setName(String name) {
        this.name = name;
    }

}

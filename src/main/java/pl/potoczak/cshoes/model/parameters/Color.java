package pl.potoczak.cshoes.model.parameters;

import javax.persistence.Entity;

@Entity
public class Color extends Parameter{

    public Color(String name) {
        this.setName(name);
    }
}

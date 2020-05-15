package pl.potoczak.cshoes.model.parameters;

import javax.persistence.Entity;

@Entity
public class Picture extends Parameter {
    public Picture() {
    }

    public Picture(String name) {
        this.setName(name);
    }
}

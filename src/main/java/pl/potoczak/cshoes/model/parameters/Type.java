package pl.potoczak.cshoes.model.parameters;

import pl.potoczak.cshoes.model.parameters_match.TypeMatch;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Type extends Parameter {
    public Type() {
    }

    @OneToMany(mappedBy = "type")
    private List<TypeMatch> typeList;
}

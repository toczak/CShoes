package pl.potoczak.cshoes.model.parameters;

import pl.potoczak.cshoes.model.parameters_match.GenderGroupMatch;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
public class GenderGroup extends Parameter {
    public GenderGroup() {
    }

    @OneToMany(mappedBy = "genderGroup")
    private List<GenderGroupMatch> genderGroupMatchList;
}

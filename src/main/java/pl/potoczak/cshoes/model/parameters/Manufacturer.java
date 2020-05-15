package pl.potoczak.cshoes.model.parameters;

import pl.potoczak.cshoes.model.parameters_match.ColorMatch;
import pl.potoczak.cshoes.model.parameters_match.ManufacturerMatch;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Manufacturer extends Parameter {
    public Manufacturer() {
    }

    @OneToMany(mappedBy = "manufacturer")
    private List<ManufacturerMatch> manufacturerMatchList;
}

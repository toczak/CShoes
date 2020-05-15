package pl.potoczak.cshoes.model.parameters;

import pl.potoczak.cshoes.model.parameters_match.ColorMatch;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Color extends Parameter{

    @OneToMany(mappedBy = "color")
    private List<ColorMatch> colorMatchList;

    public Color() {
    }

    public Color(String name) {
        this.setName(name);
    }

    public List<ColorMatch> getColorMatchList() {
        return colorMatchList;
    }

    public void setColorMatchList(List<ColorMatch> colorMatchList) {
        this.colorMatchList = colorMatchList;
    }
}

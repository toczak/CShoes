package pl.potoczak.cshoes.DTO;

import pl.potoczak.cshoes.model.parameters.GenderGroup;
import pl.potoczak.cshoes.model.parameters.Size;
import pl.potoczak.cshoes.model.parameters.Type;

import java.math.BigDecimal;

public interface ShoesListDTO {
    Long getId();
    String getName();
    BigDecimal getPrice();
    Type getType();
    GenderGroup getGenderGroup();
    Size getSize();
}

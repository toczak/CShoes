package pl.potoczak.cshoes.dto;

import pl.potoczak.cshoes.model.parameters.GenderGroup;
import pl.potoczak.cshoes.model.parameters.Picture;
import pl.potoczak.cshoes.model.parameters.Type;

import java.math.BigDecimal;

public interface ShoesListDTO {
    Long getId();
    String getName();
    BigDecimal getPrice();
    Type getType();
    GenderGroup getGenderGroup();
    Picture getPicture();
}

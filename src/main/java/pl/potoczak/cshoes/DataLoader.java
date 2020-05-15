package pl.potoczak.cshoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.*;
import pl.potoczak.cshoes.model.parameters_match.ColorMatch;
import pl.potoczak.cshoes.model.parameters_match.GenderGroupMatch;
import pl.potoczak.cshoes.model.parameters_match.ManufacturerMatch;
import pl.potoczak.cshoes.model.parameters_match.TypeMatch;
import pl.potoczak.cshoes.repository.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private ShoesRepository shoesRepository;
    private ColorRepository colorRepository;
    private ManufacturerRepository manufacturerRepository;
    private TypeRepository typeRepository;
    private GenderGroupRepository genderGroupRepository;
    private PictureRepository pictureRepository;

    @Autowired
    public DataLoader(ShoesRepository shoesRepository, ColorRepository colorRepository, ManufacturerRepository manufacturerRepository, TypeRepository typeRepository, GenderGroupRepository genderGroupRepository, PictureRepository pictureRepository) {
        this.shoesRepository = shoesRepository;
        this.colorRepository = colorRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.typeRepository = typeRepository;
        this.genderGroupRepository = genderGroupRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Color> colors = (List<Color>) colorRepository.findAll();
        List<Manufacturer> manufacturers = (List<Manufacturer>) manufacturerRepository.findAll();
        List<Type> types = (List<Type>) typeRepository.findAll();
        List<GenderGroup> genderGroups = (List<GenderGroup>) genderGroupRepository.findAll();

        Shoes shoes = new Shoes("Czonwersy BLK1", new BigDecimal(239), pictureRepository.findById(1L).get());
        shoes.setSizesShoes(new SizesShoes(shoes, 35,45));
        List<GenderGroupMatch> genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes,3));
            add(new GenderGroupMatch(genderGroups.get(1),shoes,3));
            add(new GenderGroupMatch(genderGroups.get(2),shoes,2));
        }};
        shoes.setGenderGroupMatchList(genderGroupMatches);
        List<ManufacturerMatch> manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(2),shoes,2));
            add(new ManufacturerMatch(manufacturers.get(3),shoes,3));
        }};
        shoes.setManufacturerMatchList(manufacturerMatches);
        List<TypeMatch> typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes,2));
            add(new TypeMatch(types.get(1),shoes,1));
            add(new TypeMatch(types.get(2),shoes,1));
            add(new TypeMatch(types.get(3),shoes,3));
        }};
        shoes.setTypeMatchList(typeMatches);
        List<ColorMatch> colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes,3));
            add(new ColorMatch(colors.get(5),shoes,2));
        }};
        shoes.setColorMatchList(colorMatches);
        shoesRepository.save(shoes);


        Shoes shoes1 = new Shoes("Czonwersy GRN1", new BigDecimal(204), pictureRepository.findById(2L).get());
        shoes1.setSizesShoes(new SizesShoes(shoes1, 33,43));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes1,3));
            add(new GenderGroupMatch(genderGroups.get(1),shoes1,3));
            add(new GenderGroupMatch(genderGroups.get(2),shoes1,2));
        }};
        shoes1.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(2),shoes1,2));
            add(new ManufacturerMatch(manufacturers.get(3),shoes1,3));
        }};
        shoes1.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes1,2));
            add(new TypeMatch(types.get(1),shoes1,1));
            add(new TypeMatch(types.get(2),shoes1,1));
            add(new TypeMatch(types.get(3),shoes1,3));
        }};
        shoes1.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes1,2));
            add(new ColorMatch(colors.get(2),shoes1,3));
            add(new ColorMatch(colors.get(5),shoes1,2));
        }};
        shoes1.setColorMatchList(colorMatches);
        shoesRepository.save(shoes1);


        
        Shoes shoes2 = new Shoes("Liuto RK3", new BigDecimal(204), pictureRepository.findById(2L).get());
        shoes2.setSizesShoes(new SizesShoes(shoes2, 29,39));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes2,0));
            add(new GenderGroupMatch(genderGroups.get(1),shoes2,3));
            add(new GenderGroupMatch(genderGroups.get(2),shoes2,0));
        }};
        shoes2.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(0),shoes2,1));
            add(new ManufacturerMatch(manufacturers.get(1),shoes2,3));
        }};
        shoes2.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(1),shoes2,1));
            add(new TypeMatch(types.get(2),shoes2,3));
            add(new TypeMatch(types.get(4),shoes2,1));
        }};
        shoes2.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes2,3));
            add(new ColorMatch(colors.get(5),shoes2,3));
        }};
        shoes2.setColorMatchList(colorMatches);
        shoesRepository.save(shoes2);
    }

}

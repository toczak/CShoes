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


        Shoes shoes1 = new Shoes("Czonwersy GRN1", new BigDecimal(199), pictureRepository.findById(2L).get());
        shoes1.setSizesShoes(new SizesShoes(shoes1, 32,43));
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


        
        Shoes shoes2 = new Shoes("Liuto RK3", new BigDecimal(99), pictureRepository.findById(3L).get());
        shoes2.setSizesShoes(new SizesShoes(shoes2, 37,45));
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


        Shoes shoes3 = new Shoes("Aigrek KRMK3", new BigDecimal(179), pictureRepository.findById(4L).get());
        shoes3.setSizesShoes(new SizesShoes(shoes3, 33,45));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes3,1));
            add(new GenderGroupMatch(genderGroups.get(1),shoes3,3));
            add(new GenderGroupMatch(genderGroups.get(2),shoes3,1));
        }};
        shoes3.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(2),shoes3,3));
            add(new ManufacturerMatch(manufacturers.get(3),shoes3,2));
        }};
        shoes3.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes3,3));
            add(new TypeMatch(types.get(2),shoes3,2));
            add(new TypeMatch(types.get(3),shoes3,2));
        }};
        shoes3.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(1),shoes3,2));
            add(new ColorMatch(colors.get(3),shoes3,2));
            add(new ColorMatch(colors.get(4),shoes3,3));
        }};
        shoes3.setColorMatchList(colorMatches);
        shoesRepository.save(shoes3);

        Shoes shoes4 = new Shoes("Mondelum M765", new BigDecimal(239), pictureRepository.findById(5L).get());
        shoes4.setSizesShoes(new SizesShoes(shoes4, 29,37));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes4,3));
            add(new GenderGroupMatch(genderGroups.get(1),shoes4,1));
            add(new GenderGroupMatch(genderGroups.get(2),shoes4,2));
        }};
        shoes4.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(1),shoes4,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes4,1));
        }};
        shoes4.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes4,2));
            add(new TypeMatch(types.get(2),shoes4,3));
            add(new TypeMatch(types.get(3),shoes4,2));
        }};
        shoes4.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(1),shoes4,2));
            add(new ColorMatch(colors.get(2),shoes4,3));
            add(new ColorMatch(colors.get(5),shoes4,2));
        }};
        shoes4.setColorMatchList(colorMatches);
        shoesRepository.save(shoes4);

        Shoes shoes5 = new Shoes("Tryki GB65", new BigDecimal(349), pictureRepository.findById(6L).get());
        shoes5.setSizesShoes(new SizesShoes(shoes5, 38,45));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(1),shoes5,3));
        }};
        shoes5.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(0),shoes5,3));
            add(new ManufacturerMatch(manufacturers.get(1),shoes5,2));
        }};
        shoes5.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(1),shoes5,3));
            add(new TypeMatch(types.get(2),shoes5,2));
            add(new TypeMatch(types.get(3),shoes5,1));
            add(new TypeMatch(types.get(4),shoes5,1));
        }};
        shoes5.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes5,2));
            add(new ColorMatch(colors.get(2),shoes5,1));
            add(new ColorMatch(colors.get(5),shoes5,3));
        }};
        shoes5.setColorMatchList(colorMatches);
        shoesRepository.save(shoes5);

        
        Shoes shoes6 = new Shoes("Sportswear K5", new BigDecimal(123), pictureRepository.findById(7L).get());
        shoes6.setSizesShoes(new SizesShoes(shoes6, 33,38));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes6,3));
        }};
        shoes6.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(1),shoes6,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes6,1));
        }};
        shoes6.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes6,3));
            add(new TypeMatch(types.get(2),shoes6,1));
            add(new TypeMatch(types.get(3),shoes6,2));
        }};
        shoes6.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes6,1));
            add(new ColorMatch(colors.get(1),shoes6,2));
            add(new ColorMatch(colors.get(2),shoes6,1));
            add(new ColorMatch(colors.get(3),shoes6,3));
            add(new ColorMatch(colors.get(4),shoes6,2));
            add(new ColorMatch(colors.get(5),shoes6,1));
        }};
        shoes6.setColorMatchList(colorMatches);
        shoesRepository.save(shoes6);


        Shoes shoes7 = new Shoes("Wygner OR1", new BigDecimal(79), pictureRepository.findById(8L).get());
        shoes7.setSizesShoes(new SizesShoes(shoes7, 39,45));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(1),shoes7,3));
        }};
        shoes7.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(1),shoes7,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes7,1));
        }};
        shoes7.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes7,3));
            add(new TypeMatch(types.get(2),shoes7,1));
            add(new TypeMatch(types.get(3),shoes7,2));
        }};
        shoes7.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes7,1));
            add(new ColorMatch(colors.get(1),shoes7,1));
            add(new ColorMatch(colors.get(3),shoes7,1));
            add(new ColorMatch(colors.get(4),shoes7,3));
        }};
        shoes7.setColorMatchList(colorMatches);
        shoesRepository.save(shoes7);


        Shoes shoes8 = new Shoes("S4KIDS 390", new BigDecimal(149), pictureRepository.findById(9L).get());
        shoes8.setSizesShoes(new SizesShoes(shoes8, 25,35));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes8,1));
            add(new GenderGroupMatch(genderGroups.get(2),shoes8,3));
        }};
        shoes8.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(2),shoes8,3));
            add(new ManufacturerMatch(manufacturers.get(3),shoes8,2));
        }};
        shoes8.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes8,2));
            add(new TypeMatch(types.get(1),shoes8,1));
            add(new TypeMatch(types.get(2),shoes8,3));
            add(new TypeMatch(types.get(3),shoes8,2));
        }};
        shoes8.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes8,3));
            add(new ColorMatch(colors.get(5),shoes8,2));
        }};
        shoes8.setColorMatchList(colorMatches);
        shoesRepository.save(shoes8);

        
        Shoes shoes9 = new Shoes("O4KIDS KD1", new BigDecimal(49), pictureRepository.findById(10L).get());
        shoes9.setSizesShoes(new SizesShoes(shoes9, 25,33));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes9,1));
            add(new GenderGroupMatch(genderGroups.get(2),shoes9,3));
        }};
        shoes9.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(1),shoes9,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes9,1));
        }};
        shoes9.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes9,2));
            add(new TypeMatch(types.get(2),shoes9,3));
            add(new TypeMatch(types.get(3),shoes9,3));
        }};
        shoes9.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(1),shoes9,2));
            add(new ColorMatch(colors.get(3),shoes9,1));
            add(new ColorMatch(colors.get(4),shoes9,3));
        }};
        shoes9.setColorMatchList(colorMatches);
        shoesRepository.save(shoes9);


        Shoes shoes10 = new Shoes("Luftani W250", new BigDecimal(329), pictureRepository.findById(11L).get());
        shoes10.setSizesShoes(new SizesShoes(shoes10, 33,41));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes10,3));
        }};
        shoes10.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(0),shoes10,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes10,2));
            add(new ManufacturerMatch(manufacturers.get(3),shoes10,3));
        }};
        shoes10.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(1),shoes10,3));
            add(new TypeMatch(types.get(2),shoes10,2));
            add(new TypeMatch(types.get(3),shoes10,2));
            add(new TypeMatch(types.get(4),shoes10,1));
        }};
        shoes10.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes10,3));
            add(new ColorMatch(colors.get(2),shoes10,2));
            add(new ColorMatch(colors.get(5),shoes10,3));
        }};
        shoes10.setColorMatchList(colorMatches);
        shoesRepository.save(shoes10);


        Shoes shoes11 = new Shoes("Jurki 09", new BigDecimal(79), pictureRepository.findById(12L).get());
        shoes11.setSizesShoes(new SizesShoes(shoes11, 27,42));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes11,3));
            add(new GenderGroupMatch(genderGroups.get(1),shoes11,2));
            add(new GenderGroupMatch(genderGroups.get(2),shoes11,3));
        }};
        shoes11.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(1),shoes11,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes11,2));
            add(new ManufacturerMatch(manufacturers.get(3),shoes11,2));
        }};
        shoes11.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes11,2));
            add(new TypeMatch(types.get(2),shoes11,3));
            add(new TypeMatch(types.get(3),shoes11,3));
        }};
        shoes11.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes11,2));
            add(new ColorMatch(colors.get(2),shoes11,3));
            add(new ColorMatch(colors.get(5),shoes11,3));
        }};
        shoes11.setColorMatchList(colorMatches);
        shoesRepository.save(shoes11);


        Shoes shoes12 = new Shoes("Dadidas L50", new BigDecimal(189), pictureRepository.findById(13L).get());
        shoes12.setSizesShoes(new SizesShoes(shoes12, 35,43));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(1),shoes12,3));
            add(new GenderGroupMatch(genderGroups.get(2),shoes12,1));
        }};
        shoes12.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(2),shoes12,2));
            add(new ManufacturerMatch(manufacturers.get(3),shoes12,3));
        }};
        shoes12.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes12,3));
            add(new TypeMatch(types.get(2),shoes12,1));
            add(new TypeMatch(types.get(3),shoes12,2));
            add(new TypeMatch(types.get(4),shoes12,1));
        }};
        shoes12.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(1),shoes12,2));
            add(new ColorMatch(colors.get(3),shoes12,1));
            add(new ColorMatch(colors.get(4),shoes12,3));
        }};
        shoes12.setColorMatchList(colorMatches);
        shoesRepository.save(shoes12);


        Shoes shoes13 = new Shoes("Gibook DDR3", new BigDecimal(110), pictureRepository.findById(14L).get());
        shoes13.setSizesShoes(new SizesShoes(shoes13, 31,45));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes13,3));
            add(new GenderGroupMatch(genderGroups.get(1),shoes13,3));
            add(new GenderGroupMatch(genderGroups.get(2),shoes13,2));
        }};
        shoes13.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(1),shoes13,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes13,2));
            add(new ManufacturerMatch(manufacturers.get(3),shoes13,1));
        }};
        shoes13.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes13,3));
            add(new TypeMatch(types.get(2),shoes13,3));
            add(new TypeMatch(types.get(3),shoes13,2));
        }};
        shoes13.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes13,3));
            add(new ColorMatch(colors.get(5),shoes13,2));
        }};
        shoes13.setColorMatchList(colorMatches);
        shoesRepository.save(shoes13);


        Shoes shoes14 = new Shoes("Dans RM345", new BigDecimal(199), pictureRepository.findById(15L).get());
        shoes14.setSizesShoes(new SizesShoes(shoes14, 31,45));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes14,3));
            add(new GenderGroupMatch(genderGroups.get(1),shoes14,3));
            add(new GenderGroupMatch(genderGroups.get(2),shoes14,2));
        }};
        shoes14.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(0),shoes14,1));
            add(new ManufacturerMatch(manufacturers.get(2),shoes14,3));
            add(new ManufacturerMatch(manufacturers.get(3),shoes14,3));
        }};
        shoes14.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes14,1));
            add(new TypeMatch(types.get(2),shoes14,3));
            add(new TypeMatch(types.get(3),shoes14,3));
        }};
        shoes14.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes14,2));
            add(new ColorMatch(colors.get(4),shoes14,1));
            add(new ColorMatch(colors.get(5),shoes14,3));
        }};
        shoes14.setColorMatchList(colorMatches);
        shoesRepository.save(shoes14);


        Shoes shoes15 = new Shoes("Sport4W 530K", new BigDecimal(90), pictureRepository.findById(16L).get());
        shoes15.setSizesShoes(new SizesShoes(shoes15, 30,39));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes15,3));
            add(new GenderGroupMatch(genderGroups.get(2),shoes15,1));
        }};
        shoes15.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(1),shoes15,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes15,1));
            add(new ManufacturerMatch(manufacturers.get(3),shoes15,2));
        }};
        shoes15.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes15,1));
            add(new TypeMatch(types.get(2),shoes15,3));
            add(new TypeMatch(types.get(3),shoes15,3));
        }};
        shoes15.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes15,1));
            add(new ColorMatch(colors.get(1),shoes15,1));
            add(new ColorMatch(colors.get(2),shoes15,3));
            add(new ColorMatch(colors.get(4),shoes15,1));
            add(new ColorMatch(colors.get(5),shoes15,3));
        }};
        shoes15.setColorMatchList(colorMatches);
        shoesRepository.save(shoes15);


        Shoes shoes16 = new Shoes("Tracz 560", new BigDecimal(190), pictureRepository.findById(17L).get());
        shoes16.setSizesShoes(new SizesShoes(shoes16, 30,39));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes16,3));
            add(new GenderGroupMatch(genderGroups.get(2),shoes16,1));
        }};
        shoes16.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(2),shoes16,3));
            add(new ManufacturerMatch(manufacturers.get(3),shoes16,2));
        }};
        shoes16.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes16,1));
            add(new TypeMatch(types.get(2),shoes16,2));
            add(new TypeMatch(types.get(4),shoes16,3));
        }};
        shoes16.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(1),shoes16,2));
            add(new ColorMatch(colors.get(2),shoes16,2));
            add(new ColorMatch(colors.get(5),shoes16,3));
        }};
        shoes16.setColorMatchList(colorMatches);
        shoesRepository.save(shoes16);


        Shoes shoes17 = new Shoes("Czonwersy COL4", new BigDecimal(200), pictureRepository.findById(18L).get());
        shoes17.setSizesShoes(new SizesShoes(shoes17, 28,37));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes17,2));
            add(new GenderGroupMatch(genderGroups.get(2),shoes17,3));
        }};
        shoes17.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(1),shoes17,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes17,2));
            add(new ManufacturerMatch(manufacturers.get(3),shoes17,2));
        }};
        shoes17.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes17,1));
            add(new TypeMatch(types.get(2),shoes17,2));
            add(new TypeMatch(types.get(3),shoes17,3));
        }};
        shoes17.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(3),shoes17,3));
            add(new ColorMatch(colors.get(4),shoes17,3));
        }};
        shoes17.setColorMatchList(colorMatches);
        shoesRepository.save(shoes17);


        Shoes shoes18 = new Shoes("Wagnery LR5", new BigDecimal(210), pictureRepository.findById(19L).get());
        shoes18.setSizesShoes(new SizesShoes(shoes18, 39,45));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(1),shoes18,3));
        }};
        shoes18.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(2),shoes18,2));
            add(new ManufacturerMatch(manufacturers.get(3),shoes18,3));
        }};
        shoes18.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(0),shoes18,3));
            add(new TypeMatch(types.get(2),shoes18,2));
            add(new TypeMatch(types.get(3),shoes18,2));
        }};
        shoes18.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(0),shoes18,3));
            add(new ColorMatch(colors.get(3),shoes18,1));
            add(new ColorMatch(colors.get(4),shoes18,1));
            add(new ColorMatch(colors.get(5),shoes18,1));
        }};
        shoes18.setColorMatchList(colorMatches);
        shoesRepository.save(shoes18);
        

        Shoes shoes19 = new Shoes("Szoksy", new BigDecimal(100), pictureRepository.findById(20L).get());
        shoes19.setSizesShoes(new SizesShoes(shoes19, 29,39));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(0),shoes19,3));
            add(new GenderGroupMatch(genderGroups.get(1),shoes19,1));
            add(new GenderGroupMatch(genderGroups.get(2),shoes19,3));
        }};
        shoes19.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(2),shoes19,3));
            add(new ManufacturerMatch(manufacturers.get(3),shoes19,2));
        }};
        shoes19.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(3),shoes19,3));
        }};
        shoes19.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(1),shoes19,2));
            add(new ColorMatch(colors.get(2),shoes19,2));
            add(new ColorMatch(colors.get(3),shoes19,2));
            add(new ColorMatch(colors.get(4),shoes19,3));
        }};
        shoes19.setColorMatchList(colorMatches);
        shoesRepository.save(shoes19);


        Shoes shoes20 = new Shoes("Psybalans K00L", new BigDecimal(299), pictureRepository.findById(21L).get());
        shoes20.setSizesShoes(new SizesShoes(shoes20, 37,45));
        genderGroupMatches = new ArrayList<GenderGroupMatch>() {{
            add(new GenderGroupMatch(genderGroups.get(1),shoes20,3));
        }};
        shoes20.setGenderGroupMatchList(genderGroupMatches);
        manufacturerMatches = new ArrayList<ManufacturerMatch>() {{
            add(new ManufacturerMatch(manufacturers.get(0),shoes20,3));
            add(new ManufacturerMatch(manufacturers.get(2),shoes20,3));
            add(new ManufacturerMatch(manufacturers.get(3),shoes20,2));
        }};
        shoes20.setManufacturerMatchList(manufacturerMatches);
        typeMatches = new ArrayList<TypeMatch>() {{
            add(new TypeMatch(types.get(4),shoes20,3));
        }};
        shoes20.setTypeMatchList(typeMatches);
        colorMatches = new ArrayList<ColorMatch>() {{
            add(new ColorMatch(colors.get(1),shoes20,3));
            add(new ColorMatch(colors.get(2),shoes20,2));
            add(new ColorMatch(colors.get(5),shoes20,3));
        }};
        shoes20.setColorMatchList(colorMatches);
        shoesRepository.save(shoes20);
    }

}

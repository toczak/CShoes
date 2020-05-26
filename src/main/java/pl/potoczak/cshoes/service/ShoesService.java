package pl.potoczak.cshoes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Color;
import pl.potoczak.cshoes.model.parameters.GenderGroup;
import pl.potoczak.cshoes.model.parameters.Manufacturer;
import pl.potoczak.cshoes.model.parameters.Type;
import pl.potoczak.cshoes.repository.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ShoesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoesService.class);

    private ShoesRepository shoesRepository;
    private ColorRepository colorRepository;
    private ManufacturerRepository manufacturerRepository;
    private TypeRepository typeRepository;
    private GenderGroupRepository genderGroupRepository;

    @Autowired
    public ShoesService(ShoesRepository shoesRepository, ColorRepository colorRepository, ManufacturerRepository manufacturerRepository, TypeRepository typeRepository, GenderGroupRepository genderGroupRepository) {
        this.shoesRepository = shoesRepository;
        this.colorRepository = colorRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.typeRepository = typeRepository;
        this.genderGroupRepository = genderGroupRepository;
    }

    public Color getColorById(Long id) {
        Optional<Color> optional = colorRepository.findById(id);
        return optional.orElse(null);
    }

    public Manufacturer getManufacturerById(Long id) {
        Optional<Manufacturer> optional = manufacturerRepository.findById(id);
        return optional.orElse(null);
    }

    public Type getTypeById(Long id) {
        Optional<Type> optional = typeRepository.findById(id);
        return optional.orElse(null);
    }

    public GenderGroup getGenderGroupById(Long id) {
        Optional<GenderGroup> optional = genderGroupRepository.findById(id);
        return optional.orElse(null);
    }
}

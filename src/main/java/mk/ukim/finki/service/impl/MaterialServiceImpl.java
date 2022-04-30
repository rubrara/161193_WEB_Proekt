package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.Material;
import mk.ukim.finki.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.model.exceptions.InvalidMaterialIdException;
import mk.ukim.finki.repository.MaterialRepository;
import mk.ukim.finki.service.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material findById(Long id) {
        return materialRepository.findById(id).orElseThrow(() -> new InvalidMaterialIdException(id));
    }

    @Override
    public Material create(String name) {

        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Material material = new Material(name);
        materialRepository.save(material);

        return material;
    }

    @Override
    public Material update(Long id, String name) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();

        Material material = findById(id);

        material.setName(name);

        return materialRepository.save(material);
    }

    @Override
    public void delete(Long id) {

        Material mat = findById(id);
        materialRepository.delete(mat);

    }

    @Override
    public List<Material> listMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public List<Material> searchMaterials(String searchText) {
        return null;
    }


}

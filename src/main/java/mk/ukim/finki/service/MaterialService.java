package mk.ukim.finki.service;

import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.Material;

import java.util.List;

public interface MaterialService {
    Material findById(Long id);

    // Creating a new material
    Material create(String name);

    // Updating an existing material
    Material update(Long id, String name);

    // Deleting a material
    void delete(Long id);

    // Listing of all existing materials
    List<Material> listMaterials();

    // Listing categories matching or containing given searchText
    List<Material> searchMaterials(String searchText);

}

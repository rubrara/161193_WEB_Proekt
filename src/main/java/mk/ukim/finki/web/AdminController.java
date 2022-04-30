package mk.ukim.finki.web;

import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.Material;
import mk.ukim.finki.service.CategoryService;
import mk.ukim.finki.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CategoryService categoryService;
    private final MaterialService materialService;


    public AdminController(CategoryService categoryService, MaterialService materialService) {
        this.categoryService = categoryService;
        this.materialService = materialService;
    }

    @GetMapping("/categories")
    public String getAdminCategoriesPage(Model model){
        List<Category> categories = categoryService.listCategories();

        model.addAttribute("categories", categories);

        return "adminCategories.html";
    }

    @PostMapping("/categories")
    public String addCategory(@RequestParam String name,
                              @RequestParam String desc){

        categoryService.create(name, desc);

        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/{id}/delete")
    public String deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
        return "redirect:/admin/categories";
    }


    @GetMapping("/materials")
    public String getAdminMaterialsPage(Model model){
        List<Material> materials = materialService.listMaterials();

        model.addAttribute("materials", materials);

        return "adminMaterials.html";
    }

    @PostMapping("/materials")
    public String addMaterial(@RequestParam String name){

        materialService.create(name);

        return "redirect:/admin/materials";
    }

    @PostMapping("/materials/{id}/delete")
    public String deleteMaterial(@PathVariable Long id){
        materialService.delete(id);
        return "redirect:/admin/materials";
    }


}

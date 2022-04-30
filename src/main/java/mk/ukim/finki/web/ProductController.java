package mk.ukim.finki.web;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.Material;
import mk.ukim.finki.model.Product;
import mk.ukim.finki.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.service.AuthorService;
import mk.ukim.finki.service.CategoryService;
import mk.ukim.finki.service.MaterialService;
import mk.ukim.finki.service.ProductService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final MaterialService materialService;
    private final AuthorService authorService;

    public ProductController(ProductService productService, CategoryService categoryService, MaterialService materialService, AuthorService authorService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.materialService = materialService;
        this.authorService = authorService;
    }

    @GetMapping({"/", "/home"})
    public String showHomeProducts(Model model, @RequestParam(required = false) String error) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Category> categories = categoryService.listCategories();
        List<Product> products = productService.findAll();
        List<Material> materials = materialService.listMaterials();
        List<Author> authors = authorService.listAuthors();

        model.addAttribute("categories", categories);
        model.addAttribute("materials", materials);
        model.addAttribute("products", products);
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "home");

        return "master-template";
    }

    @GetMapping("/addArt")
    public String showAddArtPage(Model model){
        List<Category> categories = categoryService.listCategories();
        List<Material> materials = materialService.listMaterials();

        model.addAttribute("categories", categories);
        model.addAttribute("materials", materials);
        model.addAttribute("bodyContent", "addArt");
        return "master-template";
    }

    @GetMapping("/editArt/{id}")
    public String showAddArtPage(@PathVariable Long id,
                                 Model model){

        if (productService.findById(id).isPresent()){
            Product product = productService.findById(id).get();
            List<Category> categories = categoryService.listCategories();
            List<Material> materials = materialService.listMaterials();

            model.addAttribute("categories", categories);
            model.addAttribute("materials", materials);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "addArt");

            return "master-template";
        }
        return "redirect:/home?error=ProductNotFound";
    }

    @PostMapping("/addArt")
    public String saveProduct(HttpServletRequest request,
                              @RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam String desc,
                              @RequestParam Long category,
                              @RequestParam Long material,
                              @RequestParam("image") MultipartFile multipartFile){


        try {

            String username = request.getRemoteUser();

            Category cat = categoryService.findById(category);
            Material mat = materialService.findById(material);

            if (id != null){
                productService.edit(id, name, price, desc, cat, mat, multipartFile, username);
            }
            else {
                productService.save(name, price, desc, cat, mat, multipartFile, username);
            }

            return "redirect:/home";

        } catch (RuntimeException exception) {
            return "redirect:/home?error=" + exception.getMessage();
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/home";
    }


    @GetMapping("/product/{id}")
    public String showProductInfo(Model model,
                                  @PathVariable Long id){

        Product prod = productService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        List<Category> categories = categoryService.listCategories();
        List<Material> materials = materialService.listMaterials();
        List<Author> authors = authorService.listAuthors();

        model.addAttribute("categories", categories);
        model.addAttribute("materials", materials);
        model.addAttribute("authors", authors);
        model.addAttribute("prod", prod);
        model.addAttribute("bodyContent", "productInfo.html");

        return "master-template";
    }

    @GetMapping("/category/{id}")
    public String showProductsByCategory(@PathVariable Long id,
                                         Model model){

        Category category = categoryService.findById(id);

        List<Product> products = productService.listAllProductsByCategory(category);
        List<Category> categories = categoryService.listCategories();
        List<Material> materials = materialService.listMaterials();
        List<Author> authors = authorService.listAuthors();

        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("materials", materials);
        model.addAttribute("authors", authors);
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "productSelected");

        return "master-template";

    }

    @GetMapping("/material/{id}")
    public String showProductsByMaterial(@PathVariable Long id,
                                         Model model){

        Material material = materialService.findById(id);

        List<Product> products = productService.listAllProductsByMaterial(material);
        List<Category> categories = categoryService.listCategories();
        List<Material> materials = materialService.listMaterials();
        List<Author> authors = authorService.listAuthors();

        model.addAttribute("material", material);
        model.addAttribute("categories", categories);
        model.addAttribute("materials", materials);
        model.addAttribute("authors", authors);
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "productSelected");

        return "master-template";

    }

    @GetMapping("/artist/{id}")
    public String showProductsByAuthor(@PathVariable Long id,
                                         Model model){

        Author author = authorService.findById(id);

        List<Product> products = productService.listAllProductsByAuthor(author);
        List<Category> categories = categoryService.listCategories();
        List<Material> materials = materialService.listMaterials();
        List<Author> authors = authorService.listAuthors();

        model.addAttribute("author", author);
        model.addAttribute("categories", categories);
        model.addAttribute("materials", materials);
        model.addAttribute("authors", authors);
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "productSelected");

        return "master-template";

    }




}

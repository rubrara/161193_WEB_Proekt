package mk.ukim.finki.web;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.Role;
import mk.ukim.finki.model.User;
import mk.ukim.finki.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.model.exceptions.InvalidMatchingPasswordException;
import mk.ukim.finki.service.AuthorService;
import mk.ukim.finki.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final AuthorService authorService;

    public RegisterController(UserService userService, AuthorService authorService) {
        this.userService = userService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register.html";
    }

    @PostMapping
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String repeatedPassword,
                               @RequestParam String email,
                               @RequestParam Role role
                               ) {
        try{
            this.userService.register(username, password, repeatedPassword, email, role);
            return "login.html";
        } catch (InvalidArgumentsException | InvalidMatchingPasswordException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

    @GetMapping("/authorReg")
    public String getRegisterAuthorPage(Model model){
        return "registerAuthor.html";
    }

    @PostMapping("/authorReg")
    public String registerAuthor(HttpServletRequest request,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam(required = false) String image){

//        author = true; -> ova ce mi treba za vo home i drugite view da znam dali ima avot logirano za posebni dugmina etc

            String username = request.getRemoteUser();

            authorService.register(username, name, surname, image);
            return "redirect:/home";
    }

}

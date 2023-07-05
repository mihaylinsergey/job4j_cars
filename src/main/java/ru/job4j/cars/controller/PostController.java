package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.CarBodyService;
import ru.job4j.cars.service.CategoryService;
import ru.job4j.cars.service.PostService;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
@Controller
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CarBodyService carBodyService;

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/index";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("categories", categoryService.findAll())
                .addAttribute("carBodies", carBodyService.findAll());
        return "posts/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute PostDto post, @RequestParam List<MultipartFile> photo,
                         @SessionAttribute User user, Model model) {
        try {
            List<FileDto> list = new ArrayList<>();
            for (var i : photo) {
                list.add(new FileDto(i.getOriginalFilename(), i.getBytes()));
            }
            postService.save(post, list, user);
            return "redirect:/posts/index";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/actual")
    public String getActual(Model model) {
        model.addAttribute("posts", postService.findAllPostsForLastDay());
        var rsl = postService.findAllPostsForLastDay();
        rsl.forEach(System.out::println);
        return "posts/index";
    }

    @GetMapping("/withPhoto")
    public String getWithPhoto(Model model) {
        model.addAttribute("posts", postService.findAllPostsWithPhotos());
        return "posts/index";
    }

    @PostMapping("/searchBrand")
    public String getSearchBrand(@RequestParam String searchBrand, Model model) {
        model.addAttribute("posts", postService.findAllPostsWithBrand(searchBrand));
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var postDtoOptional = postService.findById(id);
        if (postDtoOptional.isEmpty()) {
            model.addAttribute("message", "Объявление не найдено");
            return "errors/404";
        }
        model.addAttribute("post", postDtoOptional.get());
        return "posts/post";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id) {
        var postDtoOptional = postService.findById(id);
        model.addAttribute("post", postDtoOptional.get())
                .addAttribute("categories", categoryService.findAll())
                .addAttribute("carBodies", carBodyService.findAll());
        return "posts/update";
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute PostDto post, Model model) {
        try {
            postService.update(post);
            return "redirect:/posts/index";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/sold/{postId}")
    public String makeItSold(@PathVariable("postId") int id) {
        postService.makeItSold(id);
        return "redirect:/posts/index";
    }

    @GetMapping("/delete/{postId}")
    public String delete(@PathVariable("postId") int id) {
        postService.delete(id);
        return "redirect:/posts/index";
    }
}

package guru.springframework.spring5webapp.Controllers;

import guru.springframework.spring5webapp.Repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books") // setting route
    public String getBooks(Model model){

        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
    };

}

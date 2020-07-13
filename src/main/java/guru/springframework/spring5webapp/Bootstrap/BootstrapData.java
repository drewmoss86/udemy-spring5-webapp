package guru.springframework.spring5webapp.Bootstrap;

import guru.springframework.spring5webapp.Model.Author;
import guru.springframework.spring5webapp.Model.Book;
import guru.springframework.spring5webapp.Model.Publisher;
import guru.springframework.spring5webapp.Repositories.AuthorRepository;
import guru.springframework.spring5webapp.Repositories.BookRepository;
import guru.springframework.spring5webapp.Repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Spring will look for instances of this type and run them
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
//        Publisher oNeil = new Publisher("ONeil", "123 Evergreen Terrace", "Springfield", "MA", "02128");
        Publisher oNeil = new Publisher();
        oNeil.setAddressLine1("123 Evergreen Terrace");
        oNeil.setCity("Springfield");
        oNeil.setState("MA");
        oNeil.setZip("02128");

        publisherRepository.save(oNeil);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(oNeil);
        oNeil.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(oNeil);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "241435134");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(oNeil);
        oNeil.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(oNeil);

        System.out.println("Number of Publishers: "+ publisherRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + oNeil.getBooks().size());
    }
}

package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepositoy;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private PublisherRepositoy publisherRepositoy;

    private static final Publisher PUBLISHER = new Publisher(
            "Yellow King", 666, "new heaven",
            "kingdom", "ugly", "999999999",
            "999999999", "Carcosa"
    );
    private static final Author ELIPHAS = new Author("Eliphas", "Leví");
    private static final Author CAMILA = new Author( "Camila", "Garcia" );
    private static final Book CRIA_DOR = new Book( "CriaDor", "777", PUBLISHER );
    private static final Book DOGMA = new Book("Dogma e ritual", "666", PUBLISHER);

    public DevBootstrap (AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepositoy publisherRepositoy ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepositoy = publisherRepositoy;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData () {

        publisherRepositoy.save(PUBLISHER);

        makeRelationBetweenAuthorAndBook(CAMILA, CRIA_DOR);
        makeRelationBetweenAuthorAndBook(ELIPHAS, DOGMA);

        saveAuthorAndBook(CAMILA, CRIA_DOR);
        saveAuthorAndBook(ELIPHAS, DOGMA);
    }

    private static void makeRelationBetweenAuthorAndBook( Author author, Book book ) {
        author.getBooks().add(book);
        book.getAuthors().add(author);
    }

    private void saveAuthorAndBook ( Author author, Book book ) {
        authorRepository.save(author);
        bookRepository.save(book);
    }
}

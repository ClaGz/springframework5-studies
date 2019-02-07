package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap (AuthorRepository authorRepository, BookRepository bookRepository ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData () {

        //Camila
        Author camila = new Author( "Camila", "Garcia" );
        Book criaDor = new Book( "CriaDor", "777", "Camila Garcia" );
        camila.getBooks().add(criaDor);
        criaDor.getAuthors().add(camila);

        authorRepository.save(camila);
        bookRepository.save(criaDor);

        //Eliphas Leví
        Author eliphas = new Author( "Eliphas", "Leví" );
        Book dogma = new Book( "Dogma e ritual", "666", "Eliphas Levi" );
        eliphas.getBooks().add(dogma);
        dogma.getAuthors().add(eliphas);

        authorRepository.save(eliphas);
        bookRepository.save(dogma);
    }
}

package com.example.bookshop;

import com.example.bookshop.dao.*;
import com.example.bookshop.entity.*;
import com.example.bookshop.util.IsbnGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class BookShopApplication {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final PublisherDao publisherDao;
    private final RoleDao roleDao;

    @Bean
    @Transactional
    @Profile("security")
    public ApplicationRunner runner1(){
        return r-> {
            Role admin = new Role();
            admin.setRoleName("ROLE_ADMIN");

            Role user = new Role();
            user.setRoleName("ROLE_USER");

            roleDao.save(admin);
            roleDao.save(user);

        };
    };

    @Bean
    @Transactional
    @Profile("data")
    public ApplicationRunner runner(){
        return r -> {
            Author author1 = new Author("Mg Mg","mgmg@gmail.com");
            Author author2 = new Author("Ko Ko","koko@gmail.com");

            Publisher publisher1 = new Publisher("Zaw Zaw","zawzaw@gmail.com");
            Publisher publisher2 = new Publisher("Aung Aung","aungaung@gmail.com");

            Genre genre1 = new Genre();
            genre1.setGenreName("Tragedy");

            Genre genre2 = new Genre();
            genre2.setGenreName("Adventure");

            Book book1 = new Book(
                    1,
                    IsbnGenerator.generate(),
                    "Oliver Twist",
                    "Excellent",
                    50.3,
                    20,
                    "https://source.unsplash.com/400x300/?flowers"
            );
            Book book2 = new Book(
                    2,
                    IsbnGenerator.generate(),
                    "Great Expectations",
                    "Good Choice",
                    45.3,
                    30,
                    "https://source.unsplash.com/400x300/?nature"
            );
            Book book3 = new Book(
                    3,
                    IsbnGenerator.generate(),
                    "Bleak House",
                    "Nice",
                    49.3,
                    20,
                    "https://source.unsplash.com/400x300/?ocean"
            );
            Book book4 = new Book(
                    4,
                    IsbnGenerator.generate(),
                    "Under The Greenwood Tree",
                    "Excellent",
                    40.3,
                    30,
                    "https://source.unsplash.com/400x300/?flower"
            );
            Book book5 = new Book(
                    5,
                    IsbnGenerator.generate(),
                    "Return of the native",
                    "Excellent",
                    25.3,
                    40,
                    "https://source.unsplash.com/400x300/?nature"
            );
            Book book6 = new Book(
                    6,
                    IsbnGenerator.generate(),
                    "Far From the Maddening Crowd",
                    "Nice",
                    90.3,
                    50,
                    "https://source.unsplash.com/400x300/?people"
            );

            //mapping

            author1.addBook(book1);
            author1.addBook(book2);
            author1.addBook(book3);

            author2.addBook(book4);
            author2.addBook(book5);
            author2.addBook(book6);

            Publisher pub1 = publisherDao.save(publisher1);

            pub1.addBook(book1);
            pub1.addBook(book4);
            pub1.addBook(book6);

            Publisher pub2 = publisherDao.save(publisher2);

            pub2.addBook(book2);
            pub2.addBook(book3);
            pub2.addBook(book5);

            Genre gen1 = genreDao.save(genre1);

            book1.addGenre(gen1);
            book2.addGenre(gen1);
            book3.addGenre(gen1);

            Genre gen2 = genreDao.save(genre2);

            book4.addGenre(gen2);
            book5.addGenre(gen2);
            book6.addGenre(gen2);

//            bookDao.save(book1);
//            bookDao.save(book2);
//            bookDao.save(book3);
//            bookDao.save(book4);
//            bookDao.save(book5);
//            bookDao.save(book6);

            authorDao.save(author1);
            authorDao.save(author2);


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

}

package de.engram.graphql_demo;

import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Book;
import de.engram.graphql_demo.repository.AuthorRepository;
import de.engram.graphql_demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class GraphqlDemoApplication {

	@Bean
	public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
		return (args) -> {
			createDummyData(authorRepository, bookRepository);
		};
	}

	private void createDummyData(AuthorRepository authorRepository, BookRepository bookRepository) {
		Author douglas = authorRepository.save(new Author("Douglas", "Adams"));
		Author terry = authorRepository.save(new Author("Terry", "Pratchett"));

		bookRepository.save(new Book("Per Anhalter durch die Galaxis", "3548224911", 42, Arrays.asList(douglas)));
		bookRepository.save(new Book("Gevatter Tod", "9783492285049", 8, Arrays.asList(terry)));
		bookRepository.save(new Book("Best Book In The World", "1337492285049", 1337, Arrays.asList(terry, douglas)));
	}

	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);
	}
}

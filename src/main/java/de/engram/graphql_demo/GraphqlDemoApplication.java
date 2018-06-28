package de.engram.graphql_demo;

import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Book;
import de.engram.graphql_demo.repository.AuthorRepository;
import de.engram.graphql_demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqlDemoApplication {

	@Bean
	public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
		return (args) -> {
			createDummyData(authorRepository, bookRepository);
		};
	}

	private void createDummyData(AuthorRepository authorRepository, BookRepository bookRepository) {
		Author author = new Author("Douglas", "Adams");
		authorRepository.save(author);
		bookRepository.save(new Book("Per Anhalter durch die Galaxis", "3548224911", 42, author));

		author = new Author("Terry", "Pratchett");
		authorRepository.save(author);
		bookRepository.save(new Book("Gevatter Tod", "9783492285049", 8, author));
	}

	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);
	}
}

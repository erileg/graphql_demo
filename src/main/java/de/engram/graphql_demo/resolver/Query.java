package de.engram.graphql_demo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Book;
import de.engram.graphql_demo.repository.AuthorRepository;
import de.engram.graphql_demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public Iterable<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public long countBooks() {
		return bookRepository.count();
	}

	public long countAuthors() {
		return authorRepository.count();
	}
}
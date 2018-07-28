package de.engram.graphql_demo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Book;
import de.engram.graphql_demo.repository.AuthorRepository;
import de.engram.graphql_demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Comparator.comparing;
import static java.util.stream.StreamSupport.stream;

@Component
public class Query implements GraphQLQueryResolver {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public Iterable<Book> findAllBooks(int first) {
		if (first == 0) {
			return bookRepository.findAll();
		} else {
			return stream(bookRepository.findAll().spliterator(), false)
					.sorted(comparing(Book::getTitle))
					.limit(first)
					.collect(Collectors.toList());
		}
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
package de.engram.graphql_demo.resolver;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Book;
import de.engram.graphql_demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class BookResolver implements GraphQLResolver<Book> {

	@Autowired
	private AuthorRepository authorRepository;

	public List<Author> getAuthors(Book book) {
		return authorRepository.findByIds(book.getAuthors().stream()
				.map(Author::getId)
				.collect(toList()));
	}
}
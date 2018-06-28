package de.engram.graphql_demo.resolver;

import java.util.Optional;
import com.coxautodev.graphql.tools.GraphQLResolver;
import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Book;
import de.engram.graphql_demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookResolver implements GraphQLResolver<Book> {

	@Autowired
	private AuthorRepository authorRepository;

	public Optional<Author> getAuthor(Book book) {
		return authorRepository.findById(book.getAuthor().getId());
	}
}
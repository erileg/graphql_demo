package de.engram.graphql_demo.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Book;
import de.engram.graphql_demo.entity.Movie;
import de.engram.graphql_demo.exception.EntityNotFoundException;
import de.engram.graphql_demo.repository.AuthorRepository;
import de.engram.graphql_demo.repository.BookRepository;
import de.engram.graphql_demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public Movie newMovie(Long id, String title, String director, String releaseDate) {
		Movie movie = new Movie(id, title, director, releaseDate);
		return movieRepository.save(movie);
	}

	public Author newAuthor(String firstName, String lastName) {
		Author author = new Author(firstName, lastName);
		return authorRepository.save(author);
	}

	public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
		Author author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("The author could not be found", authorId));
		Book book = new Book(title, isbn, pageCount != null ? pageCount : 0, author);
		return bookRepository.save(book);
	}

	public boolean deleteMovie(Long id) {
		bookRepository.deleteById(id);
		return true;
	}

	public boolean deleteBook(Long id) {
		bookRepository.deleteById(id);
		return true;
	}

	public Book updateBookPageCount(Integer pageCount, Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("The book to be updated could not be found", bookId));
		book.setPageCount(pageCount);
		bookRepository.save(book);
		return book;
	}
}
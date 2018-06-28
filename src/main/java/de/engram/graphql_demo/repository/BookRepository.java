package de.engram.graphql_demo.repository;

import de.engram.graphql_demo.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	// no methods
}

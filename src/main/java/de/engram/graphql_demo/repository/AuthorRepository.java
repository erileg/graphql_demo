package de.engram.graphql_demo.repository;

import de.engram.graphql_demo.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	// no methods
}

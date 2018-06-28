package de.engram.graphql_demo.repository;

import org.springframework.data.repository.CrudRepository;
import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
	// no methods
}

package de.engram.graphql_demo.repository;

import de.engram.graphql_demo.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	@Query("from Author a where a.id in ?1 ")
	public List<Author> findByIds (List<Long> authorIds);
}

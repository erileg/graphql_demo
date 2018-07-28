package de.engram.graphql_demo.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String isbn;
	private int pageCount;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Author> authors = new ArrayList<>();

	public Book(String title, String isbn, int pageCount, List<Author> authors) {
		this.title = title;
		this.isbn = isbn;
		this.pageCount = pageCount;
		this.authors = authors;
	}
}
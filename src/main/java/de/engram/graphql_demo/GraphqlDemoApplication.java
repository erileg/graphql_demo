package de.engram.graphql_demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Book;
import de.engram.graphql_demo.entity.Movie;
import de.engram.graphql_demo.repository.AuthorRepository;
import de.engram.graphql_demo.repository.BookRepository;
import de.engram.graphql_demo.repository.MovieRepository;

@SpringBootApplication
public class GraphqlDemoApplication {

	@Bean
	public CommandLineRunner demo(MovieRepository movieRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
		return (args) -> {

			importSwapiData(movieRepository);
			//createDummyData(authorRepository, bookRepository);
		};
	}

	private void createDummyData(AuthorRepository authorRepository, BookRepository bookRepository) {
		Author author = new Author("Douglas", "Adams");
		authorRepository.save(author);
		bookRepository.save(new Book("Per Anhalter durch die Galaxis", "3548224911", 42, author));

		author = new Author("Terry", "Pratchett");
		authorRepository.save(author);
		bookRepository.save(new Book("Gevatter Tod", "9783492285049", 8, author));
	}

	private void importSwapiData(MovieRepository movieRepository) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(headers);

		ResponseEntity<Map> response = restTemplate.exchange("https://swapi.co/api/films/", HttpMethod.GET, entity, Map.class);
		List<Map> movies = (List<Map>) response.getBody().get("results");
		movies.forEach(m -> {
			Movie movie = new Movie(((Integer)m.get("episode_id")).longValue(), (String) m.get("title"), (String) m.get("director"), (String) m.get("release_date"));
			System.out.println(movie);
			movieRepository.save(movie);
		});

		response = restTemplate.exchange("https://swapi.co/api/starships/", HttpMethod.GET, entity, Map.class);
		List<Map> starships = (List<Map>) response.getBody().get("results");
		starships.forEach(m -> {
//				Movie movie = new Movie(((Integer)m.get("episode_id")).longValue(), (String) m.get("title"), (String) m.get("director"), (String) m.get("release_date"));
//				System.out.println(movie);
//				movieRepository.save(movie);
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);
	}
}

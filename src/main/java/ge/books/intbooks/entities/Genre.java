package ge.books.intbooks.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GENRES")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
	private List<BookGenre> genres;

	public Genre() {
		super();
	}

	public Genre(long id, String name, List<BookGenre> genres) {
		super();
		this.id = id;
		this.name = name;
		this.genres = genres;
	}



	public List<BookGenre> getGenres() {
		return genres;
	}

	public void setGenres(List<BookGenre> genres) {
		this.genres = genres;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

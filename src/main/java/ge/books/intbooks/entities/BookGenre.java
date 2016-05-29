package ge.books.intbooks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_GENRES")
public class BookGenre {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;

	@ManyToOne()
	@JoinColumn(name = "BOOK_ID")
	private Book book;

	@ManyToOne()
	@JoinColumn(name = "GENRE_ID")
	private Genre genre;

	public BookGenre() {
		super();
	}

	public BookGenre(long id, Book book, Genre genre) {
		super();
		this.id = id;
		this.book = book;
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}

package ge.books.intbooks.entities;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BOOKS")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "RELEASE_YEAR")
	private int releaseYear;

	@Column(name = "DESCRIPTION", length = 1000)
	private String description;

	@Column(name = "RIBBON")
	private String ribbon;

	@ManyToOne()
	@JoinColumn(name = "USER_ID")
	private User publisher;

	@OneToMany(mappedBy = "book")
	private List<BookGenre> genres;

	@OneToMany(mappedBy = "book")
	private List<BookReview> reviews;

	@Column(name = "COVER_IMG_URL")
	private String coverImageUrl;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POST_DATE")
	private Date postDate;

	public Book() {
		super();
	}

	public Book(long id, String title, String author, int releaseYear, String description, String ribbon,
			User publisher, List<BookGenre> genres, List<BookReview> reviews, String coverImageUrl) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.releaseYear = releaseYear;
		this.description = description;
		this.ribbon = ribbon;
		this.publisher = publisher;
		this.genres = genres;
		this.reviews = reviews;
		this.coverImageUrl = coverImageUrl;
	}

	public String getGenresAsString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < genres.size(); i++) {
			builder.append(genres.get(i).getGenre().getName());
			if (i != builder.length() - 1) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}

	public String getRatingAsString() {
		if (reviews.size() == 0) {
			return "N/A";
		}
		int sum = 0;
		for (int i = 0; i < reviews.size(); i++) {
			sum += reviews.get(i).getRating();
		}
		double value = (double) sum / reviews.size();
		DecimalFormat decimalFormat = new DecimalFormat("##.#");
		return decimalFormat.format(value);
	}

	public int getTextReviewsNumber() {
		int amount = 0;
		for (BookReview review : reviews) {
			if (review.getText() != null && !review.getText().trim().equals("")) {
				amount++;
			}
		}
		return amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRibbon() {
		return ribbon;
	}

	public void setRibbon(String ribbon) {
		this.ribbon = ribbon;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	public List<BookGenre> getGenres() {
		return genres;
	}

	public void setGenres(List<BookGenre> genres) {
		this.genres = genres;
	}

	public List<BookReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<BookReview> reviews) {
		this.reviews = reviews;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}

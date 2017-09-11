package tp4;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {

    private String ISBN;
    private String title;
    private LocalDate publicationDate;
    private Author author;

    public Book(String ISBN, String title, String publicationDate, String authorName, String authorSurname) {
        this.ISBN = ISBN;
        this.title = title;
        this.publicationDate = LocalDate.parse(publicationDate);
        this.author = new Author(authorName, authorSurname);
    }

    public Author getAuthor() {
        return author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return ISBN.equals(book.ISBN);
    }

    @Override
    public int hashCode() {
        return ISBN.hashCode();
    }
}

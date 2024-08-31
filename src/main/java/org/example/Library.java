package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Library {
  private List<Book> books = new ArrayList<>();
  private List<Author> authors = new ArrayList<>();
  private List<Borrow> borrows = new ArrayList<>();

  //Add authors and books to the library
  public void addAuthors(List<Author> authorsList) {
    authors.addAll(authorsList);
  }

  public void addBook(List<Book> booksList) {
    books.addAll(booksList);
  }

  //Get available books
  public List<Book> getAvailableBooks() {
    return books.stream()
        .filter(Book::isAvailable)
        .toList();
  }

  public Book getBookById(long id) {
    return books.stream()
        .filter(book -> book.getId() == id)
        .findFirst()
        .orElse(null);
  }

  //Borrow a book
  public boolean borrowBook(long id, String userName) {
    Book book = getBookById(id);
    if(book.isAvailable()) {
      Borrow borrow = new Borrow();
      borrow.setUserName(userName);
      borrow.setBook(book);
      borrows.add(borrow);
      book.setUpdatedAt(LocalDateTime.now());
      book.setAvailable(false);
      return true;
    } else {
      System.out.println("O livro não está disponível para empréstimo.");
      return false;
    }
  }

  //Return a book
  public void returnBook(long id) {
    Book book = getBookById(id);
    if (!book.isAvailable()) {
      book.setUpdatedAt(LocalDateTime.now());
      book.setAvailable(true);
    } else {
      System.out.println("O livro já está disponível.");
    }
  }

  public List<Book> getBorrowedBooksByUser(String username) {
    return borrows.stream()
        .filter(borrow -> borrow.getUserName().equals(username))
        .map(Borrow::getBook)
        .toList();
  }
}

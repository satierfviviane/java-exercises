package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Library library = new Library();
    Scanner scanner = new Scanner(System.in);

    Author author1 = new Author(1, "J.K. Rowling");
    Author author2 = new Author(2, "J.R.R. Tolkien");

    Book book1 = new Book(1, "Harry Potter e a Pedra Filosofal", author1, true, LocalDateTime.now(), LocalDateTime.now());
    Book book2 = new Book(2, "O Senhor dos Anéis: A Sociedade do Anel", author2, true, LocalDateTime.now(), LocalDateTime.now());
    Book book3 = new Book(3, "Harry Potter e a Câmara Secreta", author1, true, LocalDateTime.now(), LocalDateTime.now());

    library.addAuthors(List.of(author1, author2));
    library.addBook(List.of(book1, book2, book3));

    while (true) {
      System.out.println("Deseja ver os livros disponíveis ou devolver um livro? (sim/nao/devolver)");
      String userResponse = scanner.nextLine().toLowerCase();

      if (userResponse.equals("sim")) {
        List<Book> availableBooks = library.getAvailableBooks();

        if (availableBooks.isEmpty()) {
          System.out.println("Não há livros disponíveis no momento.");
        } else {
          System.out.println("Livros disponíveis: ");
          availableBooks.forEach(book -> System.out.println(book.getId() + ": " + book.getTitle()));

          System.out.println("Digite o ID do livro que você deseja emprestar: ");
          int idBook = scanner.nextInt();
          scanner.nextLine();

          Book selectedBook = library.getBookById(idBook);

          if (selectedBook != null && selectedBook.isAvailable()) {
            System.out.println("Digite seu nome: ");
            String username = scanner.nextLine();

            library.borrowBook(selectedBook.getId(), username);
            System.out.println("O livro " + selectedBook.getTitle() + " foi emprestado para " + username);
          } else {
            System.out.println("Livro não encontrado ou não disponível para empréstimo.");
          }
        }
      } else if (userResponse.equals("devolver")) {
        System.out.println("Digite seu nome: ");
        String username = scanner.nextLine();

        List<Book> borrowedBooks = library.getBorrowedBooksByUser(username);

        System.out.println("Livros emprestados para " + username + ". Digite o ID do livro que você deseja devolver: ");
        borrowedBooks.forEach(book -> System.out.println(book.getId() + ": " + book.getTitle()));
        int idBook = scanner.nextInt();

        library.returnBook(idBook);
        System.out.println("Livro emprestado para " + username + " devolvido com sucesso.");

      } else if (userResponse.equals("nao")) {
        System.out.println("Obrigado por utilizar o sistema da biblioteca.");
        break;
      } else {
        System.out.println("Resposta inválida. Por favor, responda com 'sim' ou 'não'.");
      }
    }

    scanner.close();
  }
}
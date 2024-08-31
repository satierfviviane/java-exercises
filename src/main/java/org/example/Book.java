package org.example;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
  private long id;
  private String title;
  private Author author;
  private boolean isAvailable;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Book(final long id, final String title, final Author author) {
    this.id = id;
    this.title = title;
    this.author = author;
  }



}

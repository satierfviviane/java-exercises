package org.example;

import java.util.Date;
import lombok.Data;

@Data
public class Borrow {
  private int id;
  private static int countId = 1;
  private Book book;
  private String userName;
  private Date borrowDate;
  private Date returnDate;
  private boolean isActive;

}

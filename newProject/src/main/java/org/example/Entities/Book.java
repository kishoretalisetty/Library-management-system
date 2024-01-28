package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @NonNull
   @Column(name = "book_name")
   private String name;

   @Column(name = "auother_name")
   private String auothorName;

   @Column(name = "available")
   private boolean available;

   @CreatedDate
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
   @Column(name = "created_name")
   private Date createdDate;

   @LastModifiedDate
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
   @Column(name = "updated_date")
   private Date updatedDate;


}

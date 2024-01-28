package org.example.Dtos;

import lombok.Data;

import java.util.Date;

@Data
public class BookRequestDto {

    private Long id;

    private String name;

    private String auothorName;

    private Boolean available;

    private Date createdDate;

    private Date updatedDate;

}

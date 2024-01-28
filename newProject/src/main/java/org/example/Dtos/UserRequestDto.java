package org.example.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
//@AllArgsConstructor
public class UserRequestDto {

    private Long id;

    private String name;

    private Date createdDate;

    private Date updatedDate;
}

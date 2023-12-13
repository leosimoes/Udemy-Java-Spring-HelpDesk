package com.udemy.helpdesk.api.entities;

import com.udemy.helpdesk.api.enums.PriorityEnum;
import com.udemy.helpdesk.api.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    private String id;

    @DBRef(lazy = true)
    private User user;

    @DBRef(lazy = true)
    private User assignedUser;

    private LocalDate date;

    private String title;

    private Integer number;

    private StatusEnum status;

    private PriorityEnum priority;

    private String description;

    private String image;

}

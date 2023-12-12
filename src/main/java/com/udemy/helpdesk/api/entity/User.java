package com.udemy.helpdesk.api.entity;

import com.udemy.helpdesk.api.enums.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @Email(message = "Email invalid")
    @NotBlank(message = "Email required")
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 6)
    private String password;

    private ProfileEnum profile;
}

package com.example.brandboostserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Имя клиента не может быть пустым!")
    @Size(min = 2, max = 40, message = "В имени клиента должно быть от 2 до 40 символов!")
    @Pattern(regexp = "^[А-яA-Za-z]{2,40}$", message = "Имя клиента должна быть от 2 до 40 символов и содержать только латинские буквы, кириллицу!")
    private String name;
    @NotBlank(message = "Фамилия клиента не может быть пустой!")
    @Size(min = 2, max = 40, message = "В фамилии клиента должно быть от 2 до 40 символов!")
    @Pattern(regexp = "^[А-яA-Za-z]{2,40}$", message = "Фамилия клиента должна быть от 2 до 40 символов и содержать только латинские буквы, кириллицу!")
    private String surname;
    @Size(min = 2, max = 40, message = "В отчестве клиента должно быть от 2 до 40 символов!")
    @Pattern(regexp = "^[А-яA-Za-z]{2,40}$", message = "Отчество пользователя должно быть от 2 до 40 символов и содержать только латинские буквы, кириллицу!")
    private String lastname;
    @Column(nullable = false)
    @NotBlank(message = "Контактный номер не может быть пустым!")
    @Pattern(regexp = "^\\+?[0-9]{10,12}$", message = "Номер телефона должен быть в формате +7**********, содержащий только цифры от 0-9, без пробелом и символов!")
    private String number;
    private String address;
    @Email(message = "Некорректный адрес электронной почты!")
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;
}

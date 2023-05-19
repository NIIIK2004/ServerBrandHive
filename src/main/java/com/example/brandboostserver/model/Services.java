package com.example.brandboostserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Название услуги не может быть пустым!")
    @Size(min = 2, max = 100, message = "В названии услуги должно быть от 2 до 100 символов!")
    @Pattern(regexp = "^[А-яA-Za-z\\s-,!#$%]{2,100}$", message = "Название услуги должна быть от 2 до 100 символов и содержать только латинские буквы, кириллицу!")
    private String name;
    @NotBlank(message = "Описание услуги не может быть пустым!")
    @Size(min = 2, max = 100, message = "В описании услуги должно быть от 2 до 2000 символов!")
    @Pattern(regexp = "^[А-яA-Za-z\\s-,!#$%]{2,2000}$", message = "Описание услуги должна быть от 2 до 2000 символов и содержать только латинские буквы, кириллицу!")
    private String description;
    @NotNull(message = "Цена не может быть пустой!")
    @Min(value = 1, message = "Цена не может быть меньше 1 рубля!")
    @Max(value = 100000000, message = "Цена не может быть больше 100000000")
    private int price;
    @JsonIgnore
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Order> orders;
}

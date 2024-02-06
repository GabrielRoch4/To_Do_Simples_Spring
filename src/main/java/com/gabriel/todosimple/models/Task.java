package com.gabriel.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)
@AllArgsConstructor // Contrutor com todos os atributos
@NoArgsConstructor // Construtor vazio
@Getter // Getters dos atributos
@Setter // Setters dos atributos
@EqualsAndHashCode //
public class Task {
    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    // Define a regra de negócio - Várias taferas são de um usuário
    @ManyToOne
    // Join do user na tabela de tasks
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String description;

}

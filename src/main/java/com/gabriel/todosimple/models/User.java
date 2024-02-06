package com.gabriel.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// Como entidade, pode ser tradado como se fosse uma tabela
@Entity
// Definindo a Table com o nome definido pela classe
@Table(name = User.TABLE_NAME)
@AllArgsConstructor // Contrutor com todos os atributos
@NoArgsConstructor // Construtor vazio
@Getter // Getters dos atributos
@Setter // Setters dos atributos
@EqualsAndHashCode //
public class User {
    public interface CreateUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "user";

    // Definindo os atributos

    // @Id: notação de Id
    @Id
    // "auto_increment" do bd
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Props da tabela
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 6, max = 100)
    private String username;

    // Não retornar a senha ao front
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
    private String password;

    // One user com várias tasks
    @OneToMany(mappedBy = "user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Somente escrita, para não retornar
    private List<Task> tasks = new ArrayList<Task>();

}



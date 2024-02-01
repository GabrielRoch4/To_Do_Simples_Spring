package com.gabriel.todosimple.repositories;

import com.gabriel.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Retornar uma lista de tarefas de um usuário
    List<Task> findByUser_Id(Long id);

}

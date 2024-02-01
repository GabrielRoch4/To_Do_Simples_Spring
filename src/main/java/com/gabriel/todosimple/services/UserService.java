package com.gabriel.todosimple.services;

import com.gabriel.todosimple.models.User;
import com.gabriel.todosimple.repositories.TaskRepository;
import com.gabriel.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id) {
        // Optional faz retornar vazio quando não há algo no bd, e não null
        Optional<User> user = this.userRepository.findById(id);
        // Caso retorne vazio, é lançado uma exception
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! ID: " + id + ", Tipo: " + User.class.getName()
        ));
    }

    // Transactional "ou faz tudo ou faz nada", usar em persistência
    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try{
            this.userRepository.deleteById(id);
        }catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há tasks relacionadas!");
        }
    }

}

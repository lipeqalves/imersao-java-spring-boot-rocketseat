package br.com.filipealves.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITeskRepository extends JpaRepository<TeskModel, UUID>{
    List<TeskModel> findByIdUser(UUID idUser);
}

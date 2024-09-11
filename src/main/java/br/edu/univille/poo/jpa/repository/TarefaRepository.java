package br.edu.univille.poo.jpa.repository;

import br.edu.univille.poo.jpa.entidade.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    List<Tarefa> findByFinalizado(boolean finalizado);

}

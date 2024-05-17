package br.com.diniz.ListaDeTarefas.repositorio;

import br.com.diniz.ListaDeTarefas.entidades.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}

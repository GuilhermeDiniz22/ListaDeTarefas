package br.com.diniz.ListaDeTarefas.service;

import br.com.diniz.ListaDeTarefas.dto.TarefaDto;
import br.com.diniz.ListaDeTarefas.entidades.Tarefa;

import java.util.List;

public interface TarefaService {
    TarefaDto criarTarefa(TarefaDto tarefa);

    TarefaDto getTarefa(Long id);

    List<TarefaDto> getTarefas();

    List<TarefaDto> getTarefasIncompletas();

    TarefaDto updateTarefa(TarefaDto tarefaDto, Long id);

    TarefaDto deleteTarefa(Long id);

    TarefaDto tarefaCompleta(Long id);

    TarefaDto tarefaIncompleta(Long id);
}

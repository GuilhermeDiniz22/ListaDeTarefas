package br.com.diniz.ListaDeTarefas.service;

import br.com.diniz.ListaDeTarefas.dto.TarefaDto;
import br.com.diniz.ListaDeTarefas.dto.TarefaResponse;
import br.com.diniz.ListaDeTarefas.entidades.Tarefa;

import java.util.List;

public interface TarefaService {
    TarefaDto criarTarefa(TarefaDto tarefa);

    TarefaDto getTarefa(Long id);

    TarefaResponse getTarefas(int pageNo, int pageSize, String sortBy, String sortDir);

    TarefaResponse getTarefasIncompletas(int pageNo, int pageSize, String sortBy, String sortDir);

    TarefaDto updateTarefa(TarefaDto tarefaDto, Long id);

    TarefaDto deleteTarefa(Long id);

    TarefaDto tarefaCompleta(Long id);

    TarefaDto tarefaIncompleta(Long id);
}

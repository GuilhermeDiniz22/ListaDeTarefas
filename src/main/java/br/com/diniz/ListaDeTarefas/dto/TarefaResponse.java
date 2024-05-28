package br.com.diniz.ListaDeTarefas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TarefaResponse {
    List<TarefaDto> tarefas;
    private int pageNo;
    private int pageSize;
    private long totalElementos;
    private int totalDePaginas;
    private boolean ultima;
}

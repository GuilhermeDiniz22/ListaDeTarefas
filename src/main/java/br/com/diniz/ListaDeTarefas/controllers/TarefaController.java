package br.com.diniz.ListaDeTarefas.controllers;

import br.com.diniz.ListaDeTarefas.dto.TarefaDto;
import br.com.diniz.ListaDeTarefas.dto.TarefaResponse;
import br.com.diniz.ListaDeTarefas.exceptions.GlobalExceptionHandler;
import br.com.diniz.ListaDeTarefas.service.TarefaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tarefas")
@AllArgsConstructor
public class TarefaController {
    private TarefaService tarefaService;

    private GlobalExceptionHandler handler;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TarefaDto> addTarefa(@RequestBody TarefaDto tarefaDto){
        TarefaDto tarefaSalva = tarefaService.criarTarefa(tarefaDto);
        return new ResponseEntity<>(tarefaSalva, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<TarefaDto> getTarefa(@PathVariable("id") Long id){
        TarefaDto tarefa = tarefaService.getTarefa(id);
        return new ResponseEntity<>(tarefa, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    @GetMapping
    public ResponseEntity<TarefaResponse> getTarefas(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        TarefaResponse lista = tarefaService.getTarefas(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    @GetMapping("/incompletas")
    public ResponseEntity<TarefaResponse> getTarefasIncompletas(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        TarefaResponse lista = tarefaService.getTarefasIncompletas(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TarefaDto> atualizarTarefa(@PathVariable("id") Long id,
                                                     @RequestBody TarefaDto tarefaDto){
        TarefaDto tarefa = tarefaService.updateTarefa(tarefaDto, id);
        return new ResponseEntity<>(tarefa, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<TarefaDto> deletarTarefa(@PathVariable("id") Long id){
        var tarefaDeletada = tarefaService.deleteTarefa(id);
        return new ResponseEntity<>(tarefaDeletada, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    @PatchMapping("{id}/completa")
    public ResponseEntity<TarefaDto> completarTarefa(@PathVariable("id") Long id){
        var tarefaCompleta = tarefaService.tarefaCompleta(id);
        return new ResponseEntity<>(tarefaCompleta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    @PatchMapping("{id}/incompleta")
    public ResponseEntity<TarefaDto> tarefaIncompleta(@PathVariable("id") Long id){
        var tarefaIncompleta = tarefaService.tarefaIncompleta(id);
        return new ResponseEntity<>(tarefaIncompleta, HttpStatus.OK);
    }

}

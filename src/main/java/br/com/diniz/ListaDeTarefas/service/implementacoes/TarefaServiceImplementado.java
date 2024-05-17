package br.com.diniz.ListaDeTarefas.service.implementacoes;

import br.com.diniz.ListaDeTarefas.dto.TarefaDto;
import br.com.diniz.ListaDeTarefas.entidades.Tarefa;
import br.com.diniz.ListaDeTarefas.repositorio.TarefaRepository;
import br.com.diniz.ListaDeTarefas.service.TarefaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TarefaServiceImplementado implements TarefaService {

    private TarefaRepository tarefaRepository;

   private ModelMapper modelMapper;

    @Override
    public TarefaDto criarTarefa(TarefaDto tarefaDto) {
        if(tarefaDto == null) throw new IllegalArgumentException("O objeto não pode ser nulo.");
        //setar um novo objeto tarefa vindo de um dto de tarefa
        Tarefa novaTarefa = modelMapper.map(tarefaDto, Tarefa.class);
        novaTarefa.setData(LocalDate.now());
        //salvar no banco através do metódo do repository
        Tarefa tarefaSalva = tarefaRepository.save(novaTarefa);
        //mapear objeto tarefa pro tarefaDto
        TarefaDto tarefaDtoSalva = modelMapper.map(novaTarefa, TarefaDto.class);
        //setar novo objeto dto de tarefa com os dados da data tarefa do repository
        tarefaDtoSalva.setData(tarefaSalva.getData());

        return tarefaDtoSalva;
    }

    @Override
    public TarefaDto getTarefa(Long id)  {
        Tarefa novaTarefa = tarefaRepository.findById(id).get();

        if(novaTarefa == null) throw new NoSuchElementException();

        TarefaDto retorno = modelMapper.map(novaTarefa, TarefaDto.class);
        return retorno;
    }

    @Override
    public List<TarefaDto> getTarefas() {
        var ListaTarefas = tarefaRepository.findAll().stream().toList(); // aqui a lista vem como List<Tarefa>

        if(ListaTarefas == null) throw new NoSuchElementException();

        List<TarefaDto> ListatarefasDto = ListaTarefas.stream().map((t) -> modelMapper.map(t, TarefaDto.class))
                .collect(Collectors.toList()); //aqui ocore o mapeamento de toda a lista para TarefaDto

        return ListatarefasDto;
    }

    @Override
    public List<TarefaDto> getTarefasIncompletas() {
        List<Tarefa> ListaTarefas = tarefaRepository.findAll().stream().toList();

        if(ListaTarefas == null) throw new NoSuchElementException();

        List<TarefaDto> ListaTarefasDto = ListaTarefas.stream().filter(t -> !t.isCompletado())
                .map((t) -> modelMapper.map(t, TarefaDto.class))
                .toList();

        return ListaTarefasDto;
    }

    @Override
    public TarefaDto updateTarefa(TarefaDto tarefaDto, Long id) {
        Tarefa novaTarefa = tarefaRepository.findById(id).get();

        if(novaTarefa == null) throw new NoSuchElementException();

        novaTarefa.setData(LocalDate.now());
        novaTarefa.setDescricao(tarefaDto.getDescricao());
        novaTarefa.setCompletado(tarefaDto.isCompletado());

        Tarefa tarefaSalva = tarefaRepository.save(novaTarefa);

        TarefaDto retorno = modelMapper.map(tarefaSalva, TarefaDto.class);

        return retorno;
    }

    @Override
    public TarefaDto deleteTarefa(Long id) {
        Tarefa novaTarefa = tarefaRepository.findById(id).get();

        if(novaTarefa == null) throw new NoSuchElementException();

        tarefaRepository.delete(novaTarefa);

        var retorno = modelMapper.map(novaTarefa, TarefaDto.class);

        return retorno;
    }

    @Override
    //o objetivo desse trecho de código é
    // setar o valor booleano de completado para true se uma tarefa for completada
    public TarefaDto tarefaCompleta(Long id) {
        Tarefa novaTarefa = tarefaRepository.findById(id).get();

        if(novaTarefa == null) throw new NoSuchElementException();

        novaTarefa.setCompletado(Boolean.TRUE);

        var tarefaSalva = tarefaRepository.save(novaTarefa);

        var retorno = modelMapper.map(tarefaSalva, TarefaDto.class);

        return retorno;
    }

    @Override
    public TarefaDto tarefaIncompleta(Long id) {
        Tarefa novaTarefa = tarefaRepository.findById(id).get();

        if(novaTarefa ==  null) throw new NoSuchElementException();

        novaTarefa.setCompletado(Boolean.FALSE);

        var tarefaSalva = tarefaRepository.save(novaTarefa);

        var retorno = modelMapper.map(tarefaSalva, TarefaDto.class);

        return retorno;
    }
}

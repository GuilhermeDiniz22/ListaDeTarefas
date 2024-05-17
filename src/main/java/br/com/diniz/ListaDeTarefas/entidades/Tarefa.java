package br.com.diniz.ListaDeTarefas.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tarefas")
@EqualsAndHashCode
public class Tarefa {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String descricao;

    @Column(name="Data", nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private boolean completado;

}

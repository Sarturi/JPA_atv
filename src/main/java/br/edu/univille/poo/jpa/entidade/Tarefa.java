package br.edu.univille.poo.jpa.entidade;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Tarefa {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String desc;
    private boolean finalizado;
    private Date dataPrevista;
    private Date dataFinalizado;
}

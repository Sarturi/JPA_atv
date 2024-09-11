package br.edu.univille.poo.jpa.controller;

import br.edu.univille.poo.jpa.entidade.Tarefa;
import br.edu.univille.poo.jpa.service.TarefaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

@RestController()
@RequestMapping("api/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> obterTodos(){
        return tarefaService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterPeloId(@PathVariable Long id){
        var opt = tarefaService.obterPeloId(id);
        return opt.map(tarefa -> new ResponseEntity<>(tarefa, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/todos-finalizado")
    public List<Tarefa> obterTodosFinalizado(){
        return tarefaService.obterTodosFinalizado();    
    }

    @GetMapping("/todos-nao-finalizado")
    public List<Tarefa> obterTodosNaoFinalizado(){
        return tarefaService.obterTodosNaoFinalizado();    
    }

    @GetMapping("/todos-atrasado")
    public List<Tarefa> obterTodosAtrasados(){
        return tarefaService.obterTodosAtrasados();    
    }
    
    @GetMapping("/todos-nao-Finalizado-entre/{inicio}/{fim}")
    public List<Tarefa> obterTodosNaoFinalizadoEntreData(@PathVariable Date inicio, Date fim){
        return tarefaService.obterTodosNaoFinalizadoEntreData(inicio, fim);
    }

    @PutMapping("/finalizar")
    public ResponseEntity<?> finalizar(@RequestBody Tarefa tarefa){
        try {
            tarefa = tarefaService.finalizar(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody Tarefa tarefa){
        try {
            tarefa = tarefaService.incluir(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody Tarefa tarefa){
        try {
            tarefa = tarefaService.atualizar(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> excluir(@RequestBody Tarefa tarefa){
        try{
            tarefaService.excluir(tarefa);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}

package br.com.alura.forum.config.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.modelo.AtualizacaoTopicoForm;
import br.com.alura.forum.modelo.DetalhesDoTopicoDto;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.TopicoDto;
import br.com.alura.forum.modelo.TopicoForm;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;
    
    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        List<Topico> topicos;
        if(nomeCurso == null)
            topicos = topicoRepository.findTheId("Dúvida");
        else
            topicos = topicoRepository.findByCursoNome(nomeCurso);
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent())
            return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional //atualiza o banco de dados
    public ResponseEntity<TopicoDto> atualizar(@RequestBody @Valid AtualizacaoTopicoForm form, @PathVariable Long id) {
        return ResponseEntity.ok(new TopicoDto(form.atualizar(id, topicoRepository)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

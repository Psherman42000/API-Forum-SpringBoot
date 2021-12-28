package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.TopicoDto;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    
    @RequestMapping("/topicos")
    public List<TopicoDto> lista(String nomeCurso){
        List<Topico> topicos;
        if(nomeCurso == null)
            topicos = topicoRepository.findTheId("Dúvida");
        else
            topicos = topicoRepository.findByCursoNome(nomeCurso);

        return TopicoDto.converter(topicos);
    }
}

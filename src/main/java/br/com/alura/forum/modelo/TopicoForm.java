package br.com.alura.forum.modelo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoForm {

    @NotNull @NotEmpty
    private String titulo;

    @NotNull @NotEmpty
    private String mensagem;

    @NotNull @NotEmpty
    private String nomeCurso;

    public Topico converter(CursoRepository repository) {
        Curso curso = repository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}

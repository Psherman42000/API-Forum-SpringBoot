package br.com.alura.forum.modelo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.forum.repository.TopicoRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtualizacaoTopicoForm {
    @NotNull @NotEmpty
    private String titulo;

    @NotNull @NotEmpty
    private String mensagem;

    public Topico atualizar(Long id, TopicoRepository repository){
        Topico topico = repository.getById(id);
        topico.setTitulo(titulo);
        topico.setMensagem(mensagem);

        return topico;
    }

}

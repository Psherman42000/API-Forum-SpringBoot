package br.com.alura.forum.modelo;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class RespostaDto {
    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDto(Resposta resposta){
        this.id = resposta.getId();
        this.dataCriacao = resposta.getDataCriacao();
        this.mensagem = resposta.getMensagem();
        this.nomeAutor = resposta.getAutor().getNome();
    }
}

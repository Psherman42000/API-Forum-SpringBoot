package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

    List<Topico> findByCursoNome(String nomeCurso);

    @Query("SELECT topico FROM Topico topico WHERE titulo = :titulo")
    List<Topico> findTheId(@Param("titulo")String titulo);
    
}

package br.com.domain.literalura.repository;

import br.com.domain.literalura.entidade.EntidadeLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryLivro extends JpaRepository<EntidadeLivro, Long> {

    @Query("SELECT l FROM EntidadeLivro l WHERE l.idioma >= :idioma")
    List<EntidadeLivro> findForLanguaje(String idioma);

}
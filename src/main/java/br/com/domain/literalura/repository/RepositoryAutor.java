package br.com.domain.literalura.repository;

import br.com.domain.literalura.entidade.EntidadeAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryAutor extends JpaRepository<EntidadeAutor, Long> {

    @Query("SELECT a FROM EntidadeAutor a WHERE :ano between a.nascimiento AND a.falecimento")
    List<EntidadeAutor> findForYear(int anio);

}

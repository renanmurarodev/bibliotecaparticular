package br.com.domain.literalura.entidade;

import br.com.domain.literalura.model.Autor;
import br.com.domain.literalura.utilitario.Limitario;
import jakarta.persistence.*;

public class EntidadeAutor {

    @Entity
    @Table(name = "Autor")
    public class AutorEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        private Integer nascimento;
        private Integer falecimento;


        @OneToOne
        @JoinTable(
                name = "Libro",
                joinColumns = @JoinColumn(name = "autor_id"),
                inverseJoinColumns = @JoinColumn(name = "id"))
        private EntidadeLivro livros;


        public AutorEntity() {

        }

        public AutorEntity(Autor autor) {
            this.nome = Limitario.limitarLongitud(autor.name(), 200);

            if (autor.birthYear() == null)
                this.nascimento = 1980;
            else
                this.nascimento = autor.birthYear();

            if (autor.deathYear() == null)
                this.falecimento = 3044;
            else
                this.falecimento = autor.deathYear();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nombre) {
            this.nome = nome;
        }

        public Integer getNascimento() {
            return nascimento;
        }

        public void setNascimento(Integer nascimento) {
            this.nascimento = nascimento;
        }

        public Integer getFalecimento() {
            return falecimento;
        }

        public void setFalecimento(Integer falecimento) {
            this.falecimento = falecimento;
        }


        @Override
        public String toString() {
            return "AutorEntity [id=" + id + ", nome=" + nome + ", nacimiento=" + nascimento
                    + ", falecimento=" + falecimento + ", livro="  + "]";
        }

        public EntidadeLivro getLivros() {
            return livros;
        }

        public void setLivros(EntidadeLivro livros) {
            this.livros = livros;
        }

    }
}

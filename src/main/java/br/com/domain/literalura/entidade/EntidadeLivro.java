package br.com.domain.literalura.entidade;

import br.com.domain.literalura.model.Autor;
import br.com.domain.literalura.model.Livro;
import br.com.domain.literalura.utilitario.Limitario;
import jakarta.persistence.*;

public class EntidadeLivro {
    public String getTitulo() {
    }

    @Entity
    @Table(name = "Libro")
    public class LibroEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String titulo;
        private String idioma;
        private Integer downloads;
        @OneToOne(mappedBy = "livros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private EntidadeLivro autor;

        public EntidadeLivro() {

        }

        public EntidadeLivro(Livro livro) {
            this.titulo = Limitario.limitarLongitud(livro.title(), 200);
            this.downloads = livro.download();
            if (!livro.languages().isEmpty())
                this.idioma = livro.languages().get(0);
            if (!livro.autores().isEmpty()) {
                for (Autor autor : livro.autores()) {
                    this.autor = new EntidadeAutor(autor);
                    break;
                }
            }

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getLenguaje() {
            return idioma;
        }

        public void setLenguaje(String lenguaje) {
            this.idioma = idioma;
        }

        public Integer getDescargas() {
            return downloads;
        }

        public void setDescargas(Integer descargas) {
            this.downloads = downloads;
        }

        @Override
        public String toString() {
            return "EntidadeLivro [id=" + id + ", titulo=" + titulo + ", idioma=" + idioma + ", download=" + downloads
                    + ", autores=" + autor + "]";
        }

        public EntidadeAutor getAutor() {
            return autor;
        }

        public void setAutor(EntidadeAutor autor) {
            this.autor = autor;
        }

    }
}

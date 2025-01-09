package br.com.domain.literalura.cliente;

import br.com.domain.literalura.entidade.EntidadeAutor;
import br.com.domain.literalura.entidade.EntidadeLivro;
import br.com.domain.literalura.mapper.ConverteDados;
import br.com.domain.literalura.model.Retorno;
import br.com.domain.literalura.repository.RepositoryAutor;
import br.com.domain.literalura.repository.RepositoryLivro;
import br.com.domain.literalura.service.ConsomeAPI;

import java.util.List;
import java.util.Scanner;

public class ClienteUser {
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsomeAPI consumoApi = new ConsomeAPI();
    private ConverteDados conversor = new ConverteDados();

    private RepositoryLivro livroRepositorio;
    private RepositoryAutor autorRepositorio;

    public ClienteLiteratura(RepositoryLivro livroRepositorio, RepositoryAutor autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void menu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
					Escolha uma opção:
						1.- Procurar livro por título
						2.- Lista de livros registrados
						3.- Lista de autores registrados
						4.- Lista de autores vivos
						5.- Livros por idioma
						0 - Sair
						""";
            System.out.println(menu);
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    searchBookWeb();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    searchAutor();
                    break;
                case 4:
                    searchAutorLive();
                    break;
                case 5:
                    searchByLangu();
                    break;
                case 0:
                    System.out.println("Finalizado...");
                    break;
                default:
                    System.out.println("Inválido");
            }
        }

    }

    private void searchBooks() {

        List<EntidadeLivro> livros = livroRepositorio.findAll();

        if (!livros.isEmpty()) {

            for (EntidadeLivro livro : livros) {
                System.out.println("\n\n+++++++++ LIVROS ++++++++++\n");
                System.out.println(" Titulo: " + livro.getTitulo());
                System.out.println(" Autor: " + livro.getAutor().getNombre());
                System.out.println(" Idioma: " + livro.getLenguaje());
                System.out.println(" Baixados: " + livro.getDescargas());
                System.out.println("\n-------------------------\n\n");
            }

        } else {
            System.out.println("\n\n ----- SEM RESULTADO ---- \n\n");
        }

    }

    private void searchAutor() {
        List<EntidadeAutor> autores = autorRepositorio.findAll();

        if (!autores.isEmpty()) {
            for (EntidadeAutor autor : autores) {
                System.out.println("\n\n---------- Autores -------\n");
                System.out.println(" Nome: " + autor.getNombre());
                System.out.println(" Nascimiento: " + autor.getFechaNacimiento());
                System.out.println(" Falecimento: " + autor.getFechaFallecimiento());
                System.out.println(" Livros: " + autor.getLibros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- sem resultado ---- \n\n");

        }

    }

    private void searchAutorLive() {
        System.out.println("Escreva o ano: ");
        var ano = teclado.nextInt();
        teclado.nextLine();

        List<EntidadeAutor> autores = autorRepositorio.findForYear(ano);

        if (!autores.isEmpty()) {
            for (EntidadeAutor.AutorEntity autor : autores) {
                System.out.println("\n\n---------- Autores Vivos -------\n");
                System.out.println(" Nome: " + autor.getNome());
                System.out.println(" Nascimiento: " + autor.getNascimento());
                System.out.println(" Falecimento: " + autor.getFalecimento());
                System.out.println(" Livros: " + autor.getLivros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- SEM RESULTADOS ---- \n\n");

        }
    }

    private void searchByLangu() {

        var menu = """
				Selecione um idioma:
					1.- Espanhol
					2.- Inglês
				
					""";
        System.out.println(menu);
        var idioma = teclado.nextInt();
        teclado.nextLine();

        String selecao = "";

        if(idioma == 1) {
            selecao = "es";
        } else 	if(idioma == 2) {
            selecao = "en";
        }

        List<EntidadeLivro> livros = livroRepositorio.findForLanguaje(selecao);

        if (!livros.isEmpty()) {

            for (EntidadeLivro livro : livros) {
                System.out.println("\n\n---------- LIVROS POR IDIOMA-------\n");
                System.out.println(" Titulo: " + livro.getTitulo());
                System.out.println(" Autor: " + livro.getAutor().getNombre());
                System.out.println(" Idioma: " + livro.getLenguaje());
                System.out.println(" Downloads: " + livro.getDescargas());
                System.out.println("\n-------------------------\n\n");
            }

        } else {
            System.out.println("\n\n ----- SEM RESULTADOS ---- \n\n");
        }


    }

    private void searchBookWeb() {
        Retorno dados = getDadosSerie() ;

        if (!dados.results().isEmpty()) {

            EntidadeLivro livro = new EntidadeLivro(dados.results().get(0));
            livro = livroRepositorio.save(livro);

        }

        System.out.println("Dados: ");
        System.out.println(dados);
    }

    private Retorno getDatosSerie() {
        System.out.println("Digite o nome do livro: ");
        var titulo = teclado.nextLine();
        titulo = titulo.replace(" ", "%20");
        System.out.println("Títlulo : " + titulo);
        System.out.println(URL_BASE + titulo);
        var json = consumoApi.coletarDados(URL_BASE + titulo);
        System.out.println(json);
        Retorno datos = conversor.terDados(json, Retorno.class);
        return datos;
    }

}
}

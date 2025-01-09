package br.com.domain.literalura;

import br.com.domain.literalura.cliente.ClienteUser;
import br.com.domain.literalura.repository.RepositoryAutor;
import br.com.domain.literalura.repository.RepositoryLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAlura implements CommandLineRunner {

    @Autowired
    private RepositoryAutor livroReposit;
    @Autowired
    private RepositoryLivro autorReposit;

    public static void main(String[] args) {
        SpringApplication.run(LiterAlura.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ClienteUser client = new ClienteUser(livroReposit, autorReposit);
        client.menu();
    }

}

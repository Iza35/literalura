package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosLivros;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.model.response.LivroResponse;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class Principal {

    private ConsumoApi consumo = new ConsumoApi();
    private final String ENDERECO = "https://gutendex.com/books";
    private ConverteDados conversor = new ConverteDados();
    private List<DadosLivros> dadosLivros = new ArrayList<>();

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void exibeMenu() {


        while (true) {
            System.out.println("-----------");
            System.out.println("Escolha um número de sua opção: ");
            System.out.println(" 1- buscar livro pelo titulo ");
            System.out.println(" 2- listar livros registrados ");
            System.out.println(" 3- listar autores registrados ");
            System.out.println(" 4- listar autores vivos em um determinado ano ");
            System.out.println(" 5- listar livros em um determinado idioma ");
            System.out.println(" 0- sair");

            Scanner leituraOpcao = new Scanner(System.in);
            int opcao = leituraOpcao.nextInt();

            switch (opcao) {

                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoreRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    leituraOpcao.close();
                    return;
                default:
                    System.out.println(" Opção inválida! ");

            }
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Insira o idioma para realizar a busca: ");
        System.out.println("es- espanhol");
        System.out.println("en- inglês");
        System.out.println("fr- francês");
        System.out.println("pt- portugues");
        Scanner leituraidioma = new Scanner(System.in);
        String idioma = leituraidioma.nextLine();
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        if (livros != null && !livros.isEmpty()) {
            System.out.println(livros);
        } else {
            System.out.println("Não existem livros nesse idioma no banco de dados! ");
        }


    }

    private void listarAutoresVivos() {
        System.out.println(" Insira o ano que deseja pesquisar ");
        Scanner leituraAnofalecimento = new Scanner(System.in);
        int ano = leituraAnofalecimento.nextInt();
        List<Autor> autores = autorRepository.findByAnoBetween(ano);
        if (autores != null && !autores.isEmpty()) {
            System.out.println(autores);
        } else {
            System.out.println("Não existe autor vivo para o ano pesquisado! ");
        }


    }

    private void listarAutoreRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        System.out.println("*************AUTOR **********");
        autores.forEach( autor -> {
            System.out.println("Nome: " + autor.getNome());
            System.out.println("Ano de nascimento: "+ autor.getAnoDeNascimento());
            System.out.println("Ano de falecimento:" + autor.getAnoDeNascimento());

        });

    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        System.out.println(livros);

    }

    private void buscarLivroPorTitulo() {
        System.out.println("Digite o nome do livro para busca ");
        Scanner leituraNomeLivro = new Scanner(System.in);
        String nomeLivro = leituraNomeLivro.nextLine();
        nomeLivro = nomeLivro.replaceAll(" ", "+");
        var json = consumo.obterDados("https://gutendex.com/books?search=" + nomeLivro);
        DadosLivros dadosApi = conversor.obterDados(json, DadosLivros.class);
        if ( dadosApi != null && dadosApi.getResults() != null && dadosApi.getResults().size() >0 ) {
            Livro livro = criarLivro(dadosApi.getResults().get(0));
            inserirLivro(livro);
            System.out.println(livro);
        } else {
            System.out.println(" Livro não encontrado! ");
        }

    }

    private void inserirLivro(Livro livro) {
        Autor autorSalvo = autorRepository.save(livro.getAutor());
        livro.setAutor(autorSalvo);
        livroRepository.save(livro);

    }

    private Livro criarLivro(LivroResponse livroResponse){
        Livro livro = new Livro();
        livro.setTitulo(livroResponse.getTitulo());
        Autor autor = new Autor();
        autor.setNome(livroResponse.getAutores().get(0).getNome());
        autor.setAnoDeNascimento(livroResponse.getAutores().get(0).getAnoDeNascimento());
        autor.setAnoDeFalecimento(livroResponse.getAutores().get(0).getAnoDeFalecimento());
        livro.setAutor(autor);
        livro.setDownloands(livroResponse.getDownloands());
        livro.setIdioma(livroResponse.getIdiomas().get(0));
        return livro;
    }
}



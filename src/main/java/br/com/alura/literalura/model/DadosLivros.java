package br.com.alura.literalura.model;

import br.com.alura.literalura.model.response.LivroResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivros {

    private List<LivroResponse> results;

    public List<LivroResponse> getResults() {
        return results;
    }

    public void setResults(List<LivroResponse> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LivroResponse livro : results) {
            sb.append(livro.toString()).append("\n");
        }
        return sb.toString();
    }
}

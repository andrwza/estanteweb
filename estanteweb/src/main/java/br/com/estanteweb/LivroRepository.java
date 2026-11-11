package br.com.estanteweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.estanteweb.Models.Livro;

@Repository
public class LivroRepository {

    @Autowired 
    private JdbcTemplate jdbcTemplate;

    public int contarLivros() {
        String sql = "SELECT COUNT(*) FROM livro";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<Livro> listarTodos() {
        String sql = "SELECT * FROM livro";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);
        List<Livro> livros = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            livros.add(Livro.deMap(linha));
        }
        return livros;
    }

    public Livro buscarPorId(Long id) {
        String sql = "SELECT * FROM livro WHERE id = ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, id);
        if (linhas.isEmpty()) {
            return null;
        }
        return Livro.deMap(linhas.get(0));
    }

    public List<Livro> listarOrdenadoPor(String coluna) {
        if (coluna == null) {
            return listarTodos();
        }
        String colunaLower = coluna.trim().toLowerCase();
        if (!List.of("titulo", "autor", "ano", "preco").contains(colunaLower)) {
            return listarTodos();
        }
        String sql = "SELECT * FROM livro ORDER BY " + colunaLower;
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);
        List<Livro> livros = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            livros.add(Livro.deMap(linha));
        }
        return livros;
    }

    public List<Livro> listarDoMesmoAutor(String autor, Long idAtual) {
        String sql = "SELECT * FROM livro WHERE autor = ? AND id <> ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, autor, idAtual);
        List<Livro> livros = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            livros.add(Livro.deMap(linha));
        }
        return livros;
    }

    public List<Livro> listarAtePreco(double precoMaximo) {
        String sql = "SELECT * FROM livro WHERE preco <= ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, precoMaximo);
        List<Livro> livros = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            livros.add(Livro.deMap(linha));
        }
        return livros;
    }

    public Double somarValorAcervo() {
        String sql = "SELECT SUM(preco) FROM livro";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, ano, preco) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getPreco());
    }

    // NOVO: corrige um livro. O "WHERE id = ?" garante que só esse livro muda
    // (sem o WHERE, todos os livros seriam alterados, como aconteceu com o estagiário).
    // Usa parâmetros "?", então nenhum valor do usuário é concatenado no SQL.
    // Retorna quantas linhas foram alteradas (0 = livro não existe).
    public int atualizar(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, ano = ?, preco = ? WHERE id = ?";
        return jdbcTemplate.update(sql, livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getPreco(), livro.getId());
    }

    // NOVO: remove apenas o livro com esse id. Também usa parâmetro "?".
    // Retorna quantas linhas foram apagadas (0 = livro não existe).
    public int remover(Long id) {
        String sql = "DELETE FROM livro WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

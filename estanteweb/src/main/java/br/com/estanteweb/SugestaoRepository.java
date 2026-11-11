package br.com.estanteweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.estanteweb.Models.Sugestao;

@Repository
public class SugestaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(Sugestao sugestao) {
        String sql = "INSERT INTO sugestao (nome_cliente, livro_sugerido) VALUES (?, ?)";
        jdbcTemplate.update(sql, sugestao.getNomeCliente(), sugestao.getLivroSugerido());
    }

    public List<Sugestao> listarTodas() {
        String sql = "SELECT * FROM sugestao";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);
        List<Sugestao> sugestoes = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            sugestoes.add(Sugestao.deMap(linha));
        }
        return sugestoes;
    }
}

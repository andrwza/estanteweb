package br.com.estanteweb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.estanteweb.LivroRepository;
import br.com.estanteweb.Models.Livro;

@Controller
public class LivroController {

    @Autowired
    private LivroRepository repositorio;

    @GetMapping({"/livros", "/livros/html"})
    public String listar(@RequestParam(required = false) String ordem, Model container) {
        if (ordem != null && !ordem.isBlank()) {
            container.addAttribute("livros", repositorio.listarOrdenadoPor(ordem));
        } else {
            container.addAttribute("livros", repositorio.listarTodos());
        }
        return "livros";
    }

    @GetMapping("/livros/{id}")
    public String exibirLivro(@PathVariable Long id, Model model) {
        Livro livro = buscarOu404(id);
        model.addAttribute("livro", livro);
        model.addAttribute("outrosLivros", repositorio.listarDoMesmoAutor(livro.getAutor(), id));
        return "livro";
    }

    @GetMapping("/livros/novo")
    public String novoLivro() {
        return "livro-novo";
    }

    @PostMapping("/livros")
    public String salvarLivro(
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam int ano,
            @RequestParam double preco,
            Model model) {

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);

        String erro = validar(livro);
        if (erro != null) {
            model.addAttribute("erro", erro);
            model.addAttribute("titulo", titulo);
            model.addAttribute("autor", autor);
            model.addAttribute("ano", ano);
            model.addAttribute("preco", preco);
            return "livro-novo";
        }

        repositorio.salvar(livro);

        return "redirect:/livros/html";
    }

    private String validar(Livro livro) {
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            return "O título não pode estar em branco.";
        }
        if (livro.getAutor() == null || livro.getAutor().isBlank()) {
            return "O autor não pode estar em branco.";
        }
        if (livro.getPreco() <= 0) {
            return "O preço deve ser maior que zero.";
        }
        return null;
    }

    private Livro buscarOu404(Long id) {
        Livro livro = repositorio.buscarPorId(id);
        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado");
        }
        return livro;
    }

    @GetMapping("/livros/{id}/editar")
    public String editarLivro(@PathVariable Long id, Model model) {
        model.addAttribute("livro", buscarOu404(id));
        return "livro-editar";
    }

    @PostMapping("/livros/{id}")
    public String atualizarLivro(
            @PathVariable Long id,
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam int ano,
            @RequestParam double preco,
            Model model) {

        buscarOu404(id);

        Livro livro = new Livro();
        livro.setId(id);
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);

        String erro = validar(livro);
        if (erro != null) {
            model.addAttribute("erro", erro);
            model.addAttribute("livro", livro);
            return "livro-editar";
        }
        if (repositorio.atualizar(livro) == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado");
        }
        return "redirect:/livros/" + id;
    }

    @GetMapping("/livros/{id}/remover")
    public String confirmarRemocao(@PathVariable Long id, Model model) {
        model.addAttribute("livro", buscarOu404(id));
        return "livro-remover";
    }

    @PostMapping("/livros/{id}/remover")
    public String removerLivro(@PathVariable Long id) {
        if (repositorio.remover(id) == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado");
        }
        return "redirect:/livros/html";
    }

    @GetMapping("/livros/total")
    @ResponseBody
    public String total() {
        return "O Acervo tem " + repositorio.contarLivros() + " Livros";
    }

    @GetMapping("/livros/busca")
    @ResponseBody
    public ResponseEntity<String> listarAtePreco(@RequestParam(defaultValue = "1000000") double precoMaximo) {
        List<Livro> filtrados = repositorio.listarAtePreco(precoMaximo);
        
        return ResponseEntity.ok()
                .header("X-Total-Encontrados", String.valueOf(filtrados.size()))
                .body(filtrados.toString());
    }

    @GetMapping("/livros/valor-total")
    @ResponseBody
    public String valorTotal() {
        return "Valor total do acervo: R$ " + repositorio.somarValorAcervo();
    }

    @GetMapping("/livros/quem-acessa")
    @ResponseBody
    public String quemAcessa(@RequestHeader("User-Agent") String userAgent) {
        return userAgent;
    }
}

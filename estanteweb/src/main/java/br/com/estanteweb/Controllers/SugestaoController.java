package br.com.estanteweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.estanteweb.SugestaoRepository;
import br.com.estanteweb.Models.Sugestao;

@Controller
public class SugestaoController {

    @Autowired
    private SugestaoRepository sugestaoRepository;

    @GetMapping("/sugestoes")
    public String sugestoes(Model model, @RequestParam(required = false) Boolean enviada) {
        model.addAttribute("sugestoes", sugestaoRepository.listarTodas());
        if (enviada != null && enviada) {
            model.addAttribute("enviada", true);
        }
        return "sugestoes";
    }
    
    @GetMapping("/sugestoes/nova")
    public String novaSugestao(Model model){
        return "sugestao-nova";
    } 

    @PostMapping("/sugestoes")
    public String receberSugestao(
            @RequestParam(required = false) String nomeCliente, 
            @RequestParam(required = false) String livroSugerido, 
            Model model){
        if (nomeCliente == null || nomeCliente.trim().isEmpty() || livroSugerido == null || livroSugerido.trim().isEmpty()) {
            model.addAttribute("erro", "Nome do cliente e livro sugerido não podem estar em branco.");
            model.addAttribute("nomeCliente", nomeCliente);
            model.addAttribute("livroSugerido", livroSugerido);
            return "sugestao-nova";
        }
        
        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);
        sugestaoRepository.salvar(sugestao);
        
        return "redirect:/sugestoes?enviada=true";
    }
}

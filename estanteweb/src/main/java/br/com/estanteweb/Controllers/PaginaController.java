package br.com.estanteweb.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaginaController {

    @GetMapping("/")
    @ResponseBody
    public String inicio(){
        return "<h1> EstanteWeb: livros usados com história </h1>" + 
                "<p> <a href='/'>Início</a> </p>" +
                "<p> <a href='/livros'>Estante</a> </p>" +
                "<p> <a href='/sobre'>Sobre</a> </p>" +
                "<p> <a href='/contato'>Contato</a> </p>";
            }

    @GetMapping("/sobre")
    @ResponseBody
    public String sobre(){
        return "EstanteWeb: Sobre";
    }

    @GetMapping("/contato")
    public String contatoForm(Model model){
        return "contato";
    }

    @PostMapping("/contato")
    public String contatoFormPost(@RequestParam String nome, @RequestParam String mensagem, Model model){
        if(mensagem == null || mensagem.trim().isEmpty()){
            model.addAttribute("erro", "A mensagem não pode estar em branco");
            model.addAttribute("nome", nome);
            return "contato";        
        }

        return "redirect:/contato/sucesso";
    }

    @GetMapping("/contato/sucesso")
    public String contatoSucesso() {
        return "contato-sucesso";
    }
}
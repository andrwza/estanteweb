package br.com.estanteweb.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TesteController {
    
    @GetMapping("/ola")
    public String free(Model container) {    
        container.addAttribute("nomeLoja", "EstanteWeb");
        container.addAttribute("anoFundacao", 1998);
        return "ola";
    }
}

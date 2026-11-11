package br.com.estanteweb.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public record ReservaController() {
    
    @GetMapping("/reserva")
    public String reserva() {
        return "reserva";  
    }

    @PostMapping("/reserva")
    public String processarReserva(
        @RequestParam String nome,
        @RequestParam String telefone,
        Model container) {
        
        if(telefone.isBlank() || nome.isBlank()){
            container.addAttribute("erro", "Complete todos os campos obrigatórios.");
            return "reserva";
        }else{
            container.addAttribute("nome", nome);
            container.addAttribute("telefone", telefone);
        }

        return "sucesso";
    }
}

package br.com.gabriel.screenmatch.controller;

import br.com.gabriel.screenmatch.domains.filme.DadosCadastroFilme;
import br.com.gabriel.screenmatch.domains.filme.Filme;
import br.com.gabriel.screenmatch.domains.filme.FilmeRepository;
import br.com.gabriel.scrrenmatch.domains.filme.DadosAlteracaoFilme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model){
        if (id != null){
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme",filme);
        }
        return "filmes/formulario";
    }
    @GetMapping
    public String carregaPaginalistagem(Model model){
         model.addAttribute("lista",repository.findAll());
         return "filmes/listagem";
    }
    @PostMapping
    @Transactional
    public String cadastrarFormulario(DadosCadastroFilme dados){
        var filme = new Filme(dados);
        repository.save(filme);
        return "redirect:/filmes";
    }

    @DeleteMapping
    @Transactional
    public String removeFilme(Long id){
        repository.deleteById(id);
        return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    public String alteraFilme(DadosAlteracaoFilme dados){
        var filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return "redirect:/filmes";
    }


}

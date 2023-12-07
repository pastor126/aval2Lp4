package com.eduardo.aval2Lp4.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eduardo.aval2Lp4.excecao.MotoNotFoundException;
import com.eduardo.aval2Lp4.modelo.Moto;
import com.eduardo.aval2Lp4.modelo.Revisao;
import com.eduardo.aval2Lp4.servico.MotoServico;
import com.eduardo.aval2Lp4.servico.RevisaoServico;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/revisao")
public class RevisaoController {

    @Autowired
    private MotoServico motoServico;

    @Autowired
    private RevisaoServico revisaoServico;

    @GetMapping("/listaRevisao/{id}")
    public String listaRevisao(@PathVariable("id") long id, Model model) throws MotoNotFoundException {
    	Moto moto = motoServico.buscaPorId(id);
    	List<Revisao> revisoes = revisaoServico.buscarRevisoesPorIdMoto(id);
        model.addAttribute("revisoes", revisoes);
        model.addAttribute("moto", moto);
        return "listaRevisao";
    }

    @GetMapping("/novaRevisao/{id}")
    public String novaRevisao(@PathVariable("id") long id, Model model) throws MotoNotFoundException {
        Revisao revisao = new Revisao();
        Moto moto = motoServico.buscaPorId(id);
        revisao.setMoto(moto);
        model.addAttribute("revisao", revisao);
        return "formRevisao";
    }

    @PostMapping("/salvarRevisao")
    public String salvar(@Valid Revisao revisao, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            List<Moto> motos = motoServico.buscarTodasMotos();
            model.addAttribute("motos", motos);
            return "formRevisao";
        }
        Long id = revisao.getMoto().getId();
        revisaoServico.salvar(revisao);
        attributes.addFlashAttribute("mensagem", "Revisão salva!");
        return "redirect:/revisao/listaRevisao/" + id;
    }


	
	@GetMapping("/editarRevisao/{id}")
	public String editarRevisao(@PathVariable("id") long id, RedirectAttributes attributes, Model model) {
			model.addAttribute("revisao", revisaoServico.buscarRevisaoPorId(id)) ;
			return "editaRevisao";
	}
	
	@PostMapping("/editarRevisao/{id}")
	public String editarRevisao(@PathVariable("id") long id, 
			@Valid Revisao revisao, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			revisao.setId(id);
			return "editaRevisao";
		}
		revisao.setId(id);
		revisaoServico.salvar(revisao);
		attributes.addFlashAttribute("mensagem", "Revisão atualizado com sucesso.");
		return "redirect:/revisao/listaRevisao/" + revisao.getMoto().getId();
	}
	
	@GetMapping("/deletar/{id}")
	public String apagar(@PathVariable("id")Long id, RedirectAttributes atributos) {
		try {		
			revisaoServico.deletarRevisao(id);
		} catch (MotoNotFoundException e) {
			atributos.addFlashAttribute("mensageErro", e.getMessage());
		}
		return "redirect:/listar";
	}
	
	
}

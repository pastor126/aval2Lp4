package com.eduardo.aval2Lp4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eduardo.aval2Lp4.excecao.MotoNotFoundException;
import com.eduardo.aval2Lp4.modelo.Moto;
import com.eduardo.aval2Lp4.servico.MotoServico;

import jakarta.validation.Valid;

@Controller
public class MotoController {

	@Autowired
	private MotoServico motoservico;

	@GetMapping("/listar")
	public String listarMotos(Model model) {
		List<Moto> motos = motoservico.buscarTodasMotos();
		model.addAttribute("motos", motos);
		return "/listaMoto";
	}

	@GetMapping("/")
	public String nova(Model model) {
		Moto moto = new Moto();
		model.addAttribute("novaMoto", moto);
		return "/formMoto";
	}

	@PostMapping("/gravar")
// Pega o objeto "novaMoto" e transfere para o objeto Moto moto
	public String salvarMoto(@ModelAttribute("novaMoto") @Valid Moto moto, BindingResult erro,
			RedirectAttributes atributos) {// Redireciona atributos vindos do BD ao salvar
		if (erro.hasErrors()) {
			return "/formMoto";
		}
		motoservico.novaMoto(moto);
		atributos.addFlashAttribute("mensage", "Moto salva com sucesso");
		return "redirect:/listar"; // redireciono para o metodo listar 
	}

	@GetMapping("/apagar/{id}")
	public String apagar(@PathVariable("id")Long id, RedirectAttributes atributos) {
		try {		
			motoservico.apagarMoto(id);
		} catch (MotoNotFoundException e) {
			atributos.addFlashAttribute("mensageErro", e.getMessage());
		}
		return "redirect:/listar";
	}
	

	

	


}

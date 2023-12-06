package com.eduardo.aval2Lp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eduardo.aval2Lp4.excecao.MotoNotFoundException;
import com.eduardo.aval2Lp4.modelo.Documento;
import com.eduardo.aval2Lp4.modelo.Moto;
import com.eduardo.aval2Lp4.servico.DocumentoServico;
import com.eduardo.aval2Lp4.servico.MotoServico;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/documento")
public class DocumentoController {
	
		@Autowired
		private MotoServico motoServico;
		
		@Autowired
		private DocumentoServico documentoServico;
		
		@GetMapping("/buscarDocumento/{id}")
		public String novoDocumento(@PathVariable("id") Long id, Model model) {
			String pagina = ""; 
			try {
				Moto moto = motoServico.buscaPorId(id);
				if (moto.getDocumento() == null) {
					Documento documento = new Documento();
					documento.setMoto(moto);
					model.addAttribute("item", documento);
					pagina = "/novoDocumento";
				} else {
					model.addAttribute("item", moto.getDocumento());
					pagina = "/alterarDocumento";
				}

			} catch (MotoNotFoundException e) {
				System.out.println(e.getMessage());
				return "redirect:/";
			}
			return pagina;
		}
		
		@PostMapping("/gravarDocumento/{idMoto}")
		public String gravarDocumento(@PathVariable("idMoto") Long idMoto,
				@ModelAttribute("item") @Valid Documento documento, BindingResult result, RedirectAttributes attributes) {
			try {
				Moto moto = motoServico.buscaPorId(idMoto);
				documento.setMoto(moto);
				moto.setDocumento(documento);
			} catch (MotoNotFoundException e) {
				e.printStackTrace();
			}

			if (result.hasErrors()) {
				return "/novoDocumento";
			}
			documentoServico.criarDocumento(documento);
			attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
			return "redirect:/listar";
		}
		
		@PostMapping("/alterarDocumento/{idMoto}/{idDocumento}")
		public String alterarDocumento(@PathVariable("idMoto") Long idMoto,
				@PathVariable("idDocumento") Long idDocumento, @ModelAttribute("item") @Valid Documento documento,
				BindingResult result, RedirectAttributes attributes) {
			try {
				Moto moto = motoServico.buscaPorId(idMoto);
				documento.setMoto(moto);
				documento.setId(idDocumento);	
			} catch (MotoNotFoundException e) {
				e.printStackTrace();
			}

			if (result.hasErrors()) {			
				return "/alterarDocumento";
			}
			documentoServico.alterarDocumento(documento);
			attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
			return "redirect:/listar";
		}
		

	}

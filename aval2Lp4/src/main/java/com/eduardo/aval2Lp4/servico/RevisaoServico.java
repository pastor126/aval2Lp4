package com.eduardo.aval2Lp4.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.aval2Lp4.excecao.MotoNotFoundException;
import com.eduardo.aval2Lp4.modelo.Moto;
import com.eduardo.aval2Lp4.modelo.Revisao;
import com.eduardo.aval2Lp4.repositorio.RevisaoRepositorio;


@Service
public class RevisaoServico {
	
	@Autowired
	private RevisaoRepositorio revisaoRepositorio;
	
	public Revisao salvar(Revisao revisao) {
		return revisaoRepositorio.save(revisao);
	}
	
	public List<Revisao> buscarRevisoes() {
		return revisaoRepositorio.findAll();
	}
	
	  @SuppressWarnings("null")
	public List<Revisao> buscarRevisoesPorIdMoto(Long idMoto) {
		  List<Revisao> revisoes = new ArrayList();
		  List<Revisao> todasRev = revisaoRepositorio.findAll();
		  for (Revisao revisao : todasRev) {
			if(revisao.getMoto().getId()== idMoto) {
				revisoes.add(revisao);
			}
		}
	        return revisoes;
	    }
	
	public Revisao buscarRevisaoPorId(Long id) {
		Optional<Revisao> revisao = revisaoRepositorio.findById(id);
		if (revisao.isPresent()) {
			return revisao.get();
		} else {
			throw new IllegalArgumentException("Revisão com id : " + id + " não existe");
		}
	}
		
	public void deletarRevisao(Long id) throws MotoNotFoundException {
		Revisao revisao = buscarRevisaoPorId(id);
		revisaoRepositorio.delete(revisao);
	}
	}


package com.eduardo.aval2Lp4.servico;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.aval2Lp4.excecao.MotoNotFoundException;
import com.eduardo.aval2Lp4.modelo.Documento;
import com.eduardo.aval2Lp4.repositorio.DocumentoRepositorio;

@Service
public class DocumentoServico {

		@Autowired
		private DocumentoRepositorio documentoRepositorio;
		
		public Documento criarDocumento(Documento moto) {
			return documentoRepositorio.save(moto);
		}
		
		public Documento alterarDocumento(Documento moto) {
			return documentoRepositorio.save(moto);
		}
		
		public Documento buscaPorId(Long id) throws MotoNotFoundException {
			Optional<Documento> opt = documentoRepositorio.findById(id);
			if (opt.isPresent()) {
				return opt.get();
			}else {
				throw new MotoNotFoundException("A moto informada não existe!");
			}
		}
		
		public void apagarDocumento(Long id) throws MotoNotFoundException {
			Documento documento = buscaPorId(id);
			documentoRepositorio.delete(documento);
		}
	}

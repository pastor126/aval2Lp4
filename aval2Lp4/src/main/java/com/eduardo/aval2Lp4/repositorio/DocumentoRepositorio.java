package com.eduardo.aval2Lp4.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.aval2Lp4.modelo.Documento;

public interface DocumentoRepositorio extends JpaRepository<Documento, Long> {

}
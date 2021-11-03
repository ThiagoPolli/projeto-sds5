package com.devsuperior.dsvendas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsvendas.dto.SaleDTO;
import com.devsuperior.dsvendas.entities.Sale;
import com.devsuperior.dsvendas.repositories.SaleRepository;
import com.devsuperior.dsvendas.repositories.SellerRepository;

/* Adicionar SaleRepositori em cache para não fazer varios select, mas só funciona para esse caso que tem poucos 
 * Vendedores, com muitos  esse metodo não é recomendado*/
@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repository;
	
	
	@Autowired
	private SellerRepository sellerRepository;//Esse foi adicionado// 
	
	@Transactional(readOnly = true)//Transectional também//
	public Page<SaleDTO> findAll(Pageable pageable){
		
		
		sellerRepository.findAll();//Esse foi adicionado//
		
		Page<Sale> result = repository.findAll(pageable);
		
		return  result.map(x -> new SaleDTO(x));
	}

}

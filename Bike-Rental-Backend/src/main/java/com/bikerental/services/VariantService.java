package com.bikerental.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerental.daos.VariantRepository;
import com.bikerental.entities.Variant;
import com.bikerental.models.VariantDTO;
import com.bikerental.utils.StorageService;

@Service
public class VariantService {

	@Autowired private VariantRepository repo;
	@Autowired private CompanyService cservice;
	@Autowired private StorageService storageService;
	
	public void saveVariant(VariantDTO dto) {
		Variant variant=new Variant();
		variant.setPrice(dto.getPrice());
		variant.setTitle(dto.getTitle());
		variant.setCompany(cservice.findById(dto.getCompany_id()));
		String photo=storageService.store(dto.getPhoto());
		variant.setPhoto(photo);		
		repo.save(variant);
	}
	
	public List<Variant> listall(){
		return repo.findAll();
	}
	
	public List<Variant> listByCompany(int id){
		return repo.findByCompany(cservice.findById(id));
	}
	
	public void deleteVariant(int id) {
		repo.delete(repo.getById(id));
	}
	
	public Variant findById(int id) {
		return repo.getById(id);
	}
}


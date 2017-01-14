package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.repository.BrandRepository;

@Service
public class BrandService {
	@Autowired
	private BrandRepository repository;

	public void createBrand(String brandName) {
		if (brandName == null || brandName == "") {
			return;
		}
		repository.create(brandName);
	}

	public List<Brand> getAllBrands() {
		return repository.queryAll();
	}

	public Brand getBrandById(String brandId) {
		String[] args = new String[1];
		args[0] = brandId;
		return repository.queryByPrimaryKey(args);
	}
}

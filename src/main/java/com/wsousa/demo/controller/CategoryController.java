package com.wsousa.demo.controller;

import com.wsousa.demo.domain.Category;
import com.wsousa.demo.logger.Log5wBuilder;
import com.wsousa.demo.request.CategoryRequest;
import com.wsousa.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@RestController
@RequestMapping("/category/v1")
public class CategoryController {

	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping(value = "/category")
	@Transactional
	public String create(@RequestBody CategoryRequest request) {

		Category novaCategoria =categoryService.saveCategory(request.getNome());

		Log5wBuilder
				.loadMethod()
				.loadWhatIsHappening("Salvando nova Categoria")
				.loadAddInfo("idCategoria", novaCategoria.getId().toString())
				.info(log);

		return novaCategoria.toString();
	}
		
	

}

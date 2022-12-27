package com.wsousa.demo.service.impl;

import com.wsousa.demo.domain.Category;
import com.wsousa.demo.logger.Log5wBuilder;
import com.wsousa.demo.repository.CategoryRepository;
import com.wsousa.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(String nome) {
        Log5wBuilder
                .loadMethod()
                .loadWhatIsHappening("Salvando nova Categoria")
                .loadAddInfo("name", nome)
                .info(log);
        Category novaCategoria = new Category(nome);
        return categoryRepository.save(novaCategoria);
    }
}

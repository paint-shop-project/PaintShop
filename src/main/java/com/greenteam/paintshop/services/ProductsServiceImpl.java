package com.greenteam.paintshop.services;


import com.greenteam.paintshop.dtos.ClientsDto;
import com.greenteam.paintshop.dtos.ProductsDto;
import com.greenteam.paintshop.entities.Clients;
import com.greenteam.paintshop.entities.Jobs;
import com.greenteam.paintshop.entities.Products;
import com.greenteam.paintshop.repositories.JobsRepository;
import com.greenteam.paintshop.repositories.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private JobsRepository jobsRepository;
   @Autowired
    private ProductsRepository productsRepository;


   //Adding a product
//   @Override
//   @Transactional
//    public void addProduct(ProductsDto productsDto, Long jobId) {
//       Optional<Jobs> jobsOptional = jobsRepository.findById(jobId);
//       Products products = new Products(productsDto);
//       jobsOptional.ifPresent(products::setJobs);
//       productsRepository.saveAndFlush(products);
//   }
   @Override
   @org.springframework.transaction.annotation.Transactional
   public void addProduct(ProductsDto productsDto){
       Products products = new Products(productsDto);
       productsRepository.saveAndFlush(products);
       // System.out.println("product saved" + productss);
   }


    @Override
    @Transactional
    public void addAProduct(ProductsDto productsDto) {
        Products products = new Products(productsDto);
        productsRepository.saveAndFlush(products);
    }



    //Deleting a product
    @Override
    @Transactional
    public void deleteProductById(Long productId) {
       Optional<Products> productsOptional = productsRepository.findById(productId);
       productsOptional.ifPresent(products -> productsRepository.delete(products));
    }

    //Updating a Product
    @Override
    public void updateProductById(ProductsDto productsDto) {
       Optional<Products> productsOptional = productsRepository.findById(productsDto.getId());
       productsOptional.ifPresent(products -> {products.setPaintColor(productsDto.getPaintColor());
           products.setDescription(productsDto.getDescription());
           products.setTools(productsDto.getTools());
       productsRepository.saveAndFlush(products);
       });
    }



    //Finding all products
//    @Override
//    public List<ProductsDto> getAllProductsByJobId(Long jobId){
//       Optional<Jobs> jobsOptional = jobsRepository.findById(jobId);
//       if (jobsOptional.isPresent()){
//           List<Products> productsList = productsRepository.findAllByJobsEquals(jobsOptional.get());
//           return productsList.stream().map(products -> new ProductsDto(products)).collect(Collectors.toList());
//       }
//       return Collections.emptyList();
//    }

    // get all clients
    @Override
    public List<ProductsDto> getAllProducts(){
        List<Products> productsList = productsRepository.findAll();
        return productsList.stream().map(products -> new ProductsDto(products)).collect(Collectors.toList());
    }


    //Getting a product by the product id
    @Override
    public Optional<ProductsDto> getProductById(Long productId) {
       Optional<Products> productsOptional = productsRepository.findById(productId);
       if (productsOptional.isPresent()){
           return Optional.of(new ProductsDto(productsOptional.get()));
       }
       return Optional.empty();
    }
}

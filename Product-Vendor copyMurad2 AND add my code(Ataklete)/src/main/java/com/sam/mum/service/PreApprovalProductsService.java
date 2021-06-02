package com.sam.mum.service;

import com.sam.mum.model.PreApprovalProducts;
import com.sam.mum.model.Product;
import com.sam.mum.repository.PreApprovalProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreApprovalProductsService {

    @Autowired
    PreApprovalProductsRepository preApprovalProductsRepository;

    public void addProduct(PreApprovalProducts product) {

        preApprovalProductsRepository.save(product);
    }

    public List<PreApprovalProducts> getAllProducts(){

        return preApprovalProductsRepository.findAll();
    }

    public void deleteProductById(Long id) {

        preApprovalProductsRepository.deleteById(id);

    }
    public Optional<PreApprovalProducts> getProductById(long id){

        return preApprovalProductsRepository.findById(id);
    }


    public List<PreApprovalProducts> getAllProductsByCategoryId(int id) {

        return preApprovalProductsRepository.findAllByCategory_Id(id);
    }

    public void removeApprovedProductsFromApprovalPage(){

        List<PreApprovalProducts> preApprovalProducts = getAllProducts();

        for(PreApprovalProducts product :preApprovalProducts){
            if(product.isApproved()){
                preApprovalProductsRepository.delete(product);
            }
        }
    }
}

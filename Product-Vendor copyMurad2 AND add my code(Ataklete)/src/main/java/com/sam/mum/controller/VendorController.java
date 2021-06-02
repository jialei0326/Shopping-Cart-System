package com.sam.mum.controller;

import com.sam.mum.dto.productDTO;
import com.sam.mum.model.Category;
import com.sam.mum.model.PreApprovalProducts;
import com.sam.mum.model.Product;
import com.sam.mum.service.CategoryService;
import com.sam.mum.service.PreApprovalProductsService;
import com.sam.mum.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class VendorController {


    public static String UrlDirectory = System.getProperty("user.dir") + "/src/main/resources/static/productImages";




    @Autowired
    CategoryService categoryservice;

    @Autowired
    ProductService productservice;

    @Autowired
    PreApprovalProductsService preApprovalProductsService;


//xxxxxxxxxxxxxxxxxxxxxxxxxxxxx    Product Code for Vendor xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

    @GetMapping("/vendor")
    public String getVendorHomePage(){
        return "vendorHome";
    }


    @GetMapping("/vendor/products")
    public String getProduct(Model model) {

        model.addAttribute("products", productservice.getAllProducts());

        return "products";
    }

    @GetMapping("/vendor/products/add")
    public String addProductGet(Model model) {

        model.addAttribute("productDTO", new productDTO());
        model.addAttribute("categories", categoryservice.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/vendor/products/add")
    public String addProductPost(@ModelAttribute("productDTO") productDTO productDTO,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName, RedirectAttributes redirectAttributes) throws IOException {

        PreApprovalProducts product = new PreApprovalProducts();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setCategory(categoryservice.findCategoryById(productDTO.getCategoryId()).get());

        String imageUUID;

        if(!file.isEmpty()) {

            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(UrlDirectory, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else {

            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        preApprovalProductsService.addProduct(product);

        String message = "Thank you for adding a product. After the admin approves the product it will show down below!";
        redirectAttributes.addAttribute("ApprovalConfirmation", message);

        return "redirect:/vendor/products";
    }

    @GetMapping("/vendor/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {

        productservice.deleteProductById(id);

        return "redirect:/vendor/products";
    }

    @GetMapping("/vendor/product/update/{id}")
    public String productUpdate(@PathVariable long id, Model model) {


        Product product = productservice.getProductById(id).get();
        productDTO productDTO = new productDTO();


        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        productDTO.setCategoryId(product.getCategory().getId());
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categories", categoryservice.getAllCategory());



        return "productsAdd";
    }
}

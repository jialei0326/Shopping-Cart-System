package com.sam.mum.repository;

import com.sam.mum.model.PreApprovalProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreApprovalProductsRepository extends JpaRepository<PreApprovalProducts , Long> {

    List<PreApprovalProducts> findAllByCategory_Id(int id);
}

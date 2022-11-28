package com.xibuguigu.yygh.hosp.repository;


import com.xibuguigu.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

    Hospital findByHoscode(String hoscode);

}

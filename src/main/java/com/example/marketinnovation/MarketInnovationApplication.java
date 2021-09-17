package com.example.marketinnovation;

import com.example.marketinnovation.model.ModelBase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@SpringBootApplication
public class MarketInnovationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketInnovationApplication.class, args);
    }

}

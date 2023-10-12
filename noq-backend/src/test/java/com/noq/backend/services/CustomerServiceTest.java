package com.noq.backend.services;

import com.noq.backend.DTO.CustomerDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled("tests require setup application-secret.properties")
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

/*
    @Test
    void getItem() {
        var id = "789";
        var result = customerService.getItem(id);
        System.out.println(result);
    }
*/
@Disabled
    @Test
    void getAllItems() {
        var result = customerService.getAllItems().collectList().block();
        System.out.println(result);
    }
/*
    @Test
    void addItemSuccess() {
        CustomerDTO.Address address = new CustomerDTO.Address("street", "city", "1234", "country");
        CustomerDTO customerDTO = new CustomerDTO(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "firstName", "lastName", "email", "phone", address);

        List<CustomerDTO> customerDTOS = customerService.addItem(customerDTO);
        System.out.println(customerDTOS);
    }*/
@Disabled
    @Test
    void updatePhoneNumberSuccess() {
        String itemId = "d9f8429d-614f-496c-b230-5b5438f78ab5";
        String partitionKey = "97bea79c-0ec9-4190-be1f-5cbb49d853d3";
        String phonePhoneNumber = "000 000 00 00";
        var customerDTOS = customerService.updatePhoneNumber(itemId, partitionKey, phonePhoneNumber);
        System.out.println(customerDTOS);
    }

    @Disabled
    @Test
    void UUID() {
        for (int i = 0; i < 10; i++) {
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid);
        }
    }

}

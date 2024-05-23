package com.store.api.controller;

import com.store.api.dto.CustomerDto;
import com.store.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerDto));
    }

    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Customer successfully deleted.");
    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.updateCustomerById(customerId, customerDto));
    }

}

package com.store.api.mapper;

import com.store.api.dto.CustomerDto;
import com.store.api.model.Customer;

public class CustomerMapper {
    public static CustomerDto convertEntityToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setAddress(customer.getAddress());
        customerDto.setCreatedAt(customer.getCreatedAt());
        return customerDto;
    }

    public static Customer convertDtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAddress(customerDto.getAddress());
        customer.setCreatedAt(customerDto.getCreatedAt());
        return customer;
    }
}

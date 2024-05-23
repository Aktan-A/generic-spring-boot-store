package com.store.api.service;

import com.store.api.dto.CustomerDto;
import com.store.api.exception.ResourceNotFoundException;
import com.store.api.mapper.CustomerMapper;
import com.store.api.model.Customer;
import com.store.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto getCustomerById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with id " + id + " does not exist.");
        }
        return CustomerMapper.convertEntityToDto(customer.get());
    }

    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public CustomerDto addNewCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.convertDtoToEntity(customerDto);
        return CustomerMapper.convertEntityToDto(customerRepository.save(customer));
    }

    public void deleteCustomerById(long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Customer with id " + id + " does not exist.");
        }
        customerRepository.deleteById(id);
    }

    public CustomerDto updateCustomerById(long customerId, CustomerDto customerDto) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with id " + customerId + " does not exist.");
        }
        Customer customerModel = customer.get();
        customerModel.setFirstName(customerDto.getFirstName());
        customerModel.setLastName(customerDto.getLastName());
        customerModel.setAddress(customerDto.getAddress());

        return CustomerMapper.convertEntityToDto(customerRepository.save(customerModel));
    }
}

package com.store.api.service;

import com.store.api.dto.TransactionDto;
import com.store.api.exception.ResourceNotFoundException;
import com.store.api.mapper.TransactionMapper;
import com.store.api.model.Order;
import com.store.api.model.Transaction;
import com.store.api.repository.OrderRepository;
import com.store.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public TransactionService(
            TransactionRepository transactionRepository,
            OrderRepository orderRepository
            ) {
        this.transactionRepository = transactionRepository;
        this.orderRepository = orderRepository;
    }

    public TransactionDto getTransactionById(long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id " + id + " does not exist.");
        }

        return TransactionMapper.convertEntityToDto(transaction.get());
    }

    public TransactionDto createTransaction(long orderId, TransactionDto transactionDto) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order with id " + orderId + " does not exist.");
        }

        Transaction transaction = TransactionMapper.convertDtoToEntity(transactionDto);
        transaction.setOrder(order.get());

        return TransactionMapper.convertEntityToDto(transactionRepository.save(transaction));
    }

    public void deleteTransactionById(long id) {
        boolean exists = transactionRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Transaction with id " + id + " does not exist.");
        }
        transactionRepository.deleteById(id);
    }

    public TransactionDto updateTransactionById(long id, TransactionDto transactionDto) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id " + id + " does not exist.");
        }
        Transaction transactionModel = transaction.get();
        transactionModel.setStatus(transactionDto.getStatus());
        transactionModel.setTotalPrice(transactionDto.getTotalPrice());

        return TransactionMapper.convertEntityToDto(transactionModel);
    }

}

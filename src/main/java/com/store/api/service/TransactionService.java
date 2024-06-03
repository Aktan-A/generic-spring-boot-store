package com.store.api.service;

import com.store.api.dto.TransactionDto;
import com.store.api.enums.TransactionStatus;
import com.store.api.exception.ResourceNotFoundException;
import com.store.api.mapper.TransactionMapper;
import com.store.api.model.Order;
import com.store.api.model.Transaction;
import com.store.api.repository.OrderRepository;
import com.store.api.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final OrderRepository orderRepository;

    public TransactionDto getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id " + id + " does not exist.");
        }

        return TransactionMapper.convertEntityToDto(transaction.get());
    }

    public List<TransactionDto> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(TransactionMapper::convertEntityToDto).collect(Collectors.toList());
    }

    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Optional<Order> order = orderRepository.findById(transactionDto.getOrderId());
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order with id " + transactionDto.getOrderId() + " does not exist.");
        }

        Transaction transaction = TransactionMapper.convertDtoToEntity(transactionDto);
        transaction.setStatus(TransactionStatus.CREATED);
        transaction.setOrder(order.get());

        return TransactionMapper.convertEntityToDto(transactionRepository.save(transaction));
    }

    public void deleteTransactionById(Long id) {
        boolean exists = transactionRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Transaction with id " + id + " does not exist.");
        }
        transactionRepository.deleteById(id);
    }

    public TransactionDto updateTransactionById(Long id, TransactionDto transactionDto) {
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

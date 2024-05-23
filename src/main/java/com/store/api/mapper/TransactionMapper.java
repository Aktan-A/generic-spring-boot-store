package com.store.api.mapper;

import com.store.api.dto.TransactionDto;
import com.store.api.model.Transaction;

public class TransactionMapper {

    public static TransactionDto convertEntityToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setStatus(transaction.getStatus());
        transactionDto.setTotalPrice(transaction.getTotalPrice());
        transactionDto.setCreatedAt(transaction.getCreatedAt());
        return transactionDto;
    }

    public static Transaction convertDtoToEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setStatus(transactionDto.getStatus());
        transaction.setTotalPrice(transactionDto.getTotalPrice());
        transaction.setCreatedAt(transactionDto.getCreatedAt());
        return transaction;
    }
}

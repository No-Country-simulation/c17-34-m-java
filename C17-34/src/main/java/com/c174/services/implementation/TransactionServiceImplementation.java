package com.c174.services.implementation;

import com.c174.models.transaction.TransactionRequest;
import com.c174.models.transaction.TransactionResponse;
import com.c174.services.abstraccion.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImplementation implements TransactionService {
    @Override
    public TransactionResponse getTransactionById(Long id) {
        return null;
    }

    @Override
    public List<TransactionResponse> getTransactionsByProfile(Long id) {
        return null;
    }

    @Override
    public TransactionResponse saveTransaction(TransactionRequest transaction) {
        return null;
    }

    @Override
    public String deleteTransaction(Long id) {
        return null;
    }

}

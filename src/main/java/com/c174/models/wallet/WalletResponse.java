package com.c174.models.wallet;

import com.c174.models.embed.Audit;
import com.c174.models.enumerators.StateAccount;
import com.c174.models.transaction.TransactionResponse;
import com.c174.models.profile.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {
    private Long id;
    private ProfileResponse profile;
    private double balance;
    private StateAccount stateAccount;
    private List<TransactionResponse> purchases;
    private List<TransactionResponse> sales;
    private Audit audit;

    public WalletResponse(WalletUserEntity walletEntity) {
        this.id = walletEntity.getId();
        this.balance = walletEntity.getBalance();
        this.stateAccount = walletEntity.getStateAccount();
        this.purchases = walletEntity.getPurchases().stream()
                .map(TransactionResponse::new).collect(Collectors.toList());
        this.sales = walletEntity.getSales().stream()
                .map(TransactionResponse::new).collect(Collectors.toList());
        this.audit = walletEntity.getAudit();
    }

}

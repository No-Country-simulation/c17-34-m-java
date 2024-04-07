package com.c174.models.wallet;

import com.c174.models.embed.Audit;
import com.c174.models.enumerators.StateAccount;
import com.c174.models.transaction.TransactionEntity;
import com.c174.models.profile.ProfileEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="wallet_user")
@Data
public class WalletUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "walletUser")
    private ProfileEntity profile;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private StateAccount stateAccount;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "wallet_buyer_id")
    private List<TransactionEntity> purchases;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "wallet_seller_id")
    private List<TransactionEntity> sales;
    @Embedded
    private Audit audit = new Audit();
    private Boolean isPresent;

}

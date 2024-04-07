package com.c174.models.profile;

import com.c174.models.embed.Audit;
import com.c174.models.databank.DataBankEntity;
import com.c174.models.wallet.WalletUserEntity;
import com.c174.models.ticket.TicketEntity;
import com.c174.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastname;
    private String name;
    private String document;
    private Boolean isPresent;
    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    @Embedded
    private Audit audit = new Audit();
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<DataBankEntity> dataBankEntities;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<TicketEntity> tickets;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private WalletUserEntity walletUser;

    @PrePersist
    public void prePersist() {
        this.isPresent = Boolean.TRUE;
    }

}

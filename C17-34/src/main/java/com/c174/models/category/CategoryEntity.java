package com.c174.models.category;

import com.c174.models.embed.Audit;
import com.c174.models.ticket.TicketEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name="categorias")
@Data
@NoArgsConstructor
public class CategoryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "category_ticket",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id")
    )
    private List<TicketEntity> tickets;
    @Embedded
    private Audit audit = new Audit();

    public CategoryEntity(String name) {
        this.name = name;
    }}

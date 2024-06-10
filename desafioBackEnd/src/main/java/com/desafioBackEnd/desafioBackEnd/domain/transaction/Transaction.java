package com.desafioBackEnd.desafioBackEnd.domain.transaction;


import com.desafioBackEnd.desafioBackEnd.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne// A transação pode ser vinculada apenas a sender
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne   // E a um receiver
    @JoinColumn(name = "receive_id")
    private User receiver;
    private LocalDateTime timestamp;

}

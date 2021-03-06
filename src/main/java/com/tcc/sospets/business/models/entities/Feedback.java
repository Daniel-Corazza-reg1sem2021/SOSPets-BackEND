package com.tcc.sospets.business.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ToString.Exclude
    private String feedbackId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    private String sugestao;

}

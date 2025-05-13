package Projetct.reservas.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="tb_reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeResponsavel;

    private LocalDateTime  horaInicio;

    private LocalDateTime horaFinal;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
}

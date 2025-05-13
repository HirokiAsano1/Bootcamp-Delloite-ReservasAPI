package Projetct.reservas.DTOs;

import Projetct.reservas.Entities.Reserva;
import Projetct.reservas.Entities.Sala;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservaDTO {

    private Long id;
    private String nomeResponsavel;

    private LocalDateTime horaInicio;

    private LocalDateTime horaFinal;

    private Long salaId;

    public ReservaDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.nomeResponsavel = reserva.getNomeResponsavel();
        this.horaInicio = reserva.getHoraInicio();
        this.horaFinal = reserva.getHoraFinal();
        this.salaId = reserva.getSala() != null ? reserva.getSala().getId() : null;

    }
}

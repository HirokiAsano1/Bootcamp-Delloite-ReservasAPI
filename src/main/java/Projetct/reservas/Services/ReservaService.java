package Projetct.reservas.Services;

import Projetct.reservas.DTOs.ReservaDTO;
import Projetct.reservas.DTOs.SalaDTO;
import Projetct.reservas.Entities.Reserva;
import Projetct.reservas.Entities.Sala;
import Projetct.reservas.Repositories.ReservaRepository;
import Projetct.reservas.Repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Transactional(readOnly = true)
    public List<ReservaDTO> findAll()
    {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(ReservaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public  List<ReservaDTO> findbySalaId(Long salaId)
    {
        List<Reserva> reservas = reservaRepository.findBySalaId(salaId);
        return reservas.stream().map(ReservaDTO::new).toList();
    }

    @Transactional
    public ReservaDTO insert(ReservaDTO reservaDTO) {
        if (reservaDTO.getHoraInicio().isAfter(reservaDTO.getHoraFinal()) ||
                reservaDTO.getHoraInicio().isEqual(reservaDTO.getHoraFinal())) {
            throw new IllegalArgumentException("A hora de início deve ser anterior à hora de término.");
        }
            if (reservaDTO.getHoraInicio().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Não é possível reservar uma sala para o passado.");
            }

            Sala sala = salaRepository.findById(reservaDTO.getSalaId())
                    .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

            List<Reserva> reservasExistentes = reservaRepository.findBySalaId(sala.getId());

            boolean conflito = reservasExistentes.stream().anyMatch(r ->
                    reservaDTO.getHoraInicio().isBefore(r.getHoraFinal()) &&
                            reservaDTO.getHoraFinal().isAfter(r.getHoraInicio())
            );

            if (conflito) {
                throw new IllegalArgumentException("A sala já está reservada nesse horário.");
            }

            Reserva reserva = new Reserva();
            reserva.setId(reservaDTO.getId());
            reserva.setNomeResponsavel(reservaDTO.getNomeResponsavel());
            reserva.setHoraInicio(reservaDTO.getHoraInicio());
            reserva.setHoraFinal(reservaDTO.getHoraFinal());
            reserva.setSala(sala);

            reserva = reservaRepository.save(reserva);
            return new ReservaDTO(reserva);
        }


    @Transactional
    public void delete (Long id)
    {
        reservaRepository.deleteById(id);
    }
}

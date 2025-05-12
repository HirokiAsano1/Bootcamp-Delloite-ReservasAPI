package Projetct.reservas.Controllers;

import Projetct.reservas.DTOs.ReservaDTO;
import Projetct.reservas.DTOs.SalaDTO;
import Projetct.reservas.Entities.Sala;
import Projetct.reservas.Services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas()
    {
        List <ReservaDTO> reservas = reservaService.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{salaId}")
    public ResponseEntity<List<ReservaDTO>> listarReservaSalas(@PathVariable Long salaId)
    {
        List <ReservaDTO> reservasSalas = reservaService.findbySalaId(salaId);
        return ResponseEntity.ok(reservasSalas);
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> criarReserva(@RequestBody ReservaDTO reservaDTO)
    {
        reservaDTO = reservaService.insert(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id)
    {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

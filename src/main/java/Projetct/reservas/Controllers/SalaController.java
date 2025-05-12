package Projetct.reservas.Controllers;

import Projetct.reservas.DTOs.SalaDTO;
import Projetct.reservas.Entities.Sala;
import Projetct.reservas.Services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    SalaService salaService;

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> findById(@PathVariable Long id)
    {
        SalaDTO salaDTO = salaService.findById(id);
        return  ResponseEntity.ok(salaDTO);
    }

    @GetMapping
    public ResponseEntity<List<SalaDTO>> listarSalas()
    {
        List<SalaDTO> salas = salaService.findAll();
        return ResponseEntity.ok(salas);
    }

    @PostMapping
    public ResponseEntity<SalaDTO> cadastrarSala(@RequestBody SalaDTO salaDTO)
    {
         salaDTO = salaService.insert(salaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaDTO> atualizarSala(@PathVariable Long id,@RequestBody SalaDTO salaDTO)
    {
        salaDTO = salaService.update(id,salaDTO);
        return ResponseEntity.ok(salaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable long id){
        salaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

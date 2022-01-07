package pl.edu.zut.gk45098backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.zut.gk45098backend.dto.LayerDTO;
import pl.edu.zut.gk45098backend.service.LayerService;

@RequestMapping("/api")
@RestController
public class LayerController {

    private LayerService layerService;

    public LayerController(LayerService layerService) {
        this.layerService = layerService;
    }

    @PostMapping("/layers")
    public ResponseEntity<String> addLayer(@RequestBody LayerDTO layerDTO) {
        layerService.addLayer(layerDTO);
        return new ResponseEntity<String>("created", HttpStatus.CREATED);
    }

}

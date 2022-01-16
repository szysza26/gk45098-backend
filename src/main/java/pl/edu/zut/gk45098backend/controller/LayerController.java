package pl.edu.zut.gk45098backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.zut.gk45098backend.projection.LayerInListReadModel;
import pl.edu.zut.gk45098backend.projection.LayerReadModel;
import pl.edu.zut.gk45098backend.projection.LayerWriteModel;
import pl.edu.zut.gk45098backend.service.LayerService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class LayerController {

    private final LayerService layerService;

    public LayerController(LayerService layerService) {
        this.layerService = layerService;
    }

    @PostMapping("/layers")
    public ResponseEntity<String> addLayer(@RequestBody LayerWriteModel layerWriteModel) {
        layerService.addLayer(layerWriteModel);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @GetMapping("/layers/{id}")
    public ResponseEntity<LayerReadModel> getLayer(@PathVariable Long id) {
        LayerReadModel layerReadModel = layerService.getLayer(id);
        return new ResponseEntity<>(layerReadModel, HttpStatus.OK);
    }

    @GetMapping("/layers")
    public ResponseEntity<List<LayerInListReadModel>> getLayers() {
        List<LayerInListReadModel> layers = layerService.getLayers();
        return new ResponseEntity<>(layers, HttpStatus.OK);
    }

    @PutMapping("/layers/{id}")
    public ResponseEntity<String> editLayer(@RequestBody LayerWriteModel layerWriteModel, @PathVariable Long id) {
        layerService.editLayer(layerWriteModel, id);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @DeleteMapping("/layers/{id}")
    public ResponseEntity<String> removeLayer(@PathVariable Long id) {
        layerService.deleteLayer(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}

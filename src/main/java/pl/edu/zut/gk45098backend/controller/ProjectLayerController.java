package pl.edu.zut.gk45098backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.zut.gk45098backend.projection.*;
import pl.edu.zut.gk45098backend.service.ProjectLayerService;
import javax.validation.Valid;

@RequestMapping("/api")
@RestController
public class ProjectLayerController {

    private final ProjectLayerService projectLayerService;

    public ProjectLayerController(ProjectLayerService projectLayerService) {
        this.projectLayerService = projectLayerService;
    }

    @PostMapping("/projectlayers")
    public ResponseEntity<String> create(@Valid @RequestBody ProjectLayerWriteModel projectLayerWriteModel) {
        projectLayerService.addProjectLayer(projectLayerWriteModel);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @GetMapping("/projectlayers/{id}")
    public ResponseEntity<ProjectLayerReadModel> show(@PathVariable Long id) {
        return new ResponseEntity<>(projectLayerService.getProjectLayer(id), HttpStatus.OK);
    }

    @PutMapping("/projectlayers/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody ProjectLayerWriteModel projectLayerWriteModel, @PathVariable Long id) {
        projectLayerService.editProjectLayer(projectLayerWriteModel, id);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @DeleteMapping("/projectlayers/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        projectLayerService.deleteProjectLayer(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}

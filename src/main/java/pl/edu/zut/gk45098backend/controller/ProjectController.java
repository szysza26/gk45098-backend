package pl.edu.zut.gk45098backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.zut.gk45098backend.projection.ProjectInListReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectWriteModel;
import pl.edu.zut.gk45098backend.service.ProjectService;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public ResponseEntity<String> create(@RequestBody ProjectWriteModel projectWriteModel) {
        projectService.addProject(projectWriteModel);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectReadModel> show(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.getProject(id), HttpStatus.OK);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectInListReadModel>> index() {
        return new ResponseEntity<>(projectService.getProjects(), HttpStatus.OK);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<String> update(@RequestBody ProjectWriteModel projectWriteModel, @PathVariable Long id) {
        projectService.editProject(projectWriteModel, id);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}

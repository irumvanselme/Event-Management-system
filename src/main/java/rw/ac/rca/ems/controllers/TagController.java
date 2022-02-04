package rw.ac.rca.ems.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.ems.services.ITagService;
import rw.ac.rca.ems.utils.Constants;
import rw.ac.rca.ems.utils.Formatter;
import rw.ac.rca.ems.utils.Utility;
import rw.ac.rca.ems.utils.dtos.CreateOrUpdateTagDTO;
import rw.ac.rca.ems.utils.payload.ApiResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/controllers")
public class TagController {

    private final ITagService tagService;

    @Autowired
    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> all() {
        return Formatter.ok(tagService.all());
    }

    @GetMapping
    public ResponseEntity<ApiResponse> all(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(tagService.all(pageable));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CreateOrUpdateTagDTO dto) {
        return Formatter.ok(tagService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        return Formatter.ok(tagService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> search(@RequestParam(defaultValue = "") String query) {
        return Formatter.ok(tagService.searchByName(query));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody CreateOrUpdateTagDTO dto) {
        return Formatter.ok(tagService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        tagService.remove(id);

        return Formatter.done();
    }
}

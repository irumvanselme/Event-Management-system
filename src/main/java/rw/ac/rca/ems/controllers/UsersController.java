package rw.ac.rca.ems.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.ems.models.enums.ERole;
import rw.ac.rca.ems.services.IUserService;
import rw.ac.rca.ems.utils.Constants;
import rw.ac.rca.ems.utils.Formatter;
import rw.ac.rca.ems.utils.Utility;
import rw.ac.rca.ems.utils.dtos.UserTagDTO;
import rw.ac.rca.ems.utils.payload.ApiResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final IUserService userService;

    public UsersController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> all(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(userService.all(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> byId(@PathVariable Long id) {

        return Formatter.ok(userService.findById(id));
    }

    @GetMapping("/by-role/{role}")
    public ResponseEntity<ApiResponse> byRole(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit, @PathVariable ERole role) {
        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(userService.byRole(role, pageable));
    }

    @PostMapping("/add-tag")
    public ResponseEntity<ApiResponse> addTag(@Valid @RequestBody UserTagDTO dto) {
        return Formatter.created(userService.addTag(dto.getUserId(), dto.getTagId()));
    }

    @DeleteMapping("/remove-tag")
    public ResponseEntity<ApiResponse> removeTag(@Valid @RequestBody UserTagDTO dto) {
        return Formatter.created(userService.removeTag(dto.getUserId(), dto.getTagId()));
    }
}

package rw.ac.rca.ems.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.ems.utils.payload.ApiResponse;

public class Formatter {

    public static ResponseEntity<ApiResponse> done() {
        return ResponseEntity.ok(ApiResponse.success("done"));
    }

    public static ResponseEntity<ApiResponse> ok(Object data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    public static ResponseEntity<ApiResponse> created(Object data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(data));
    }
}

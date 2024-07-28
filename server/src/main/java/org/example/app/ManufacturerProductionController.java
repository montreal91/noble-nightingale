package org.example.app;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class ManufacturerProductionController {
    private final ManufacturerProductionQueryHandler queryHandler;

    @PostMapping("/manufacturer-production-query/")
    ResponseEntity<ManufacturerProductionQueryResult> manufacturerProduction(
            @Valid @RequestBody ManufacturerProductionQuery query
    ) {
        return ResponseEntity.ok(queryHandler.handle(query));
    }
}

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
public class ManufacturerTradingQueryController {
    private final ManufacturerTradingQueryHandler handler;
    private final ManufacturerTradingQueryMapper mapper;

    @PostMapping("/manufacturer-trading-query/")
    ResponseEntity<ManufacturerTradingQueryResult> manufacturerTradingQuery(
            @RequestBody @Valid
            ManufacturerTradingQueryRequest request
    ) {
        return ResponseEntity.ok(handler.handle(mapper.map(request)));
    }
}

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
public class SetSellingPriceCommandController {
    private final SetSellingPriceMapper mapper;
    private final SetSellingPriceCommandHandler handler;

    @PostMapping("/set-selling-price/")
    ResponseEntity<SetSellingPriceCommandResult> setSellingPrice(
            @Valid @RequestBody SetSellingPriceCommandRequest request
    ) {
        return ResponseEntity.ok(handler.handle(mapper.map(request)));
    }
}

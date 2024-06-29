package com.example.jazs27151nbp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Average gold prices", description = "A gold average prices published in the date range from {startDate} to {endDate}")
public class CurrencyController {

    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/rate/{startDate}/{endDate}")
    @Operation(summary = "Get average gold exchange rate", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully pushed query to database.")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = GoldQuery.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Double> getAverageRate(
            @Parameter(description = "The start date in YYYY-MM-DD format. (ISO 8601 standard)") @PathVariable String startDate,
            @Parameter(description = "The end date in YYYY-MM-DD format. (ISO 8601 standard)") @PathVariable String endDate) {
        double averageRate = service.getAverageRate(startDate, endDate);
        return ResponseEntity.ok(averageRate);
    }
}

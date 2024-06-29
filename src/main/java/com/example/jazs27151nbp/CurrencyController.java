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
@Tag(name = "AverageCurrencyExchangeApplication", description = "The purpose of the application is to display to the user calculated average exchange rate of a given currency by provided date. ")
public class CurrencyController {

    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/rate/{table}/{currency}/{startDate}/{endDate}")
    @Operation(summary = "Calculate average exchange rate", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully pushed query to database.")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CurrencyQuery.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Double> getAverageRate(
            @Parameter(description = "The type of the table. (A, B, C)") @PathVariable String table,
            @Parameter(description = "The currency for the average exchange rate..") @PathVariable String currency,
            @Parameter(description = "The start date in YYYY-MM-DD format. (ISO 8601 standard)") @PathVariable String startDate,
            @Parameter(description = "The end date in YYYY-MM-DD format. (ISO 8601 standard)") @PathVariable String endDate) {
        double averageRate = service.getAverageRate(table, currency, startDate, endDate);
        return ResponseEntity.ok(averageRate);
    }
}

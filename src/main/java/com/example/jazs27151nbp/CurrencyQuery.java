package com.example.jazs27151nbp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Details about CurrencyQuery")
@Entity
public class CurrencyQuery {

    @Schema(description = "The unique ID of the Query", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "The type of the table (A, B, C)", example = "A")
    private String tableType;

    @Schema(description = "The name of the currency", example = "GBP")
    private String currency;

    @Schema(description = "The average rate for the chosen scope", example = "5.0938")
    private double averageRate;

    @Schema(description = "Time of query creation", example = "2024-06-29T15:25:48.345178")
    private LocalDateTime queryTime;

    @Schema(description = "The start date in YYYY-MM-DD format (ISO 8601 standard)", example = "2024-06-28")
    private String startDate;

    @Schema(description = "The end date in YYYY-MM-DD format (ISO 8601 standard)", example = "2024-06-28")
    private String endDate;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public LocalDateTime getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(LocalDateTime queryTime) {
        this.queryTime = queryTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

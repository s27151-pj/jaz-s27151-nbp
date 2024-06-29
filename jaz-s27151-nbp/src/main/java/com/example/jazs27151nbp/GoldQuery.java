package com.example.jazs27151nbp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Details about Goldquerry")
@Entity
public class GoldQuery {

    @Schema(description = "The unique ID of the Query", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "The average rate for gold", example = "5.0938")
    private double averageRate;
    @Schema(description = "The value", example = "GOLD")
    private Gold gold;
    @Schema(description = "Time of query creation", example = "2024-06-29T15:25:48.345178")
    private LocalDateTime queryTime;

    @Schema(description = "The start date in YYYY-MM-DD format (ISO 8601 standard)", example = "2024-06-28")
    private String startDate;

    @Schema(description = "The end date in YYYY-MM-DD format (ISO 8601 standard)", example = "2024-06-28")
    private String endDate;

    public Long getId() {
        return id;
    }

    public Gold getGold() {
        return gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public void setId(Long id) {
        this.id = id;
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

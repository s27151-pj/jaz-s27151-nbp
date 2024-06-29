package com.example.jazs27151nbp;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CurrencyService {

    private final CurrencyQueryRepository repository;

    public CurrencyService(CurrencyQueryRepository repository) {
        this.repository = repository;
    }

    public double getAverageRate(String table, String currency, String startDate, String endDate) {
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/%s/%s/%s/%s/?format=json", table, currency, startDate, endDate);
        RestTemplate restTemplate = new RestTemplate();
        NbpResponse response = restTemplate.getForObject(url, NbpResponse.class);

        double sum = 0;
        for (Rate rate : response.getRates()) {
            sum += rate.getMid();
        }
        double averageRate = sum / response.getRates().length;

        CurrencyQuery query = new CurrencyQuery();
        query.setTableType(table);
        query.setCurrency(currency);
        query.setStartDate(startDate);
        query.setEndDate(endDate);
        query.setAverageRate(averageRate);
        query.setQueryTime(LocalDateTime.now());
        repository.save(query);

        return averageRate;
    }

    private static class NbpResponse {
        private Rate[] rates;

        public Rate[] getRates() {
            return rates;
        }

        public void setRates(Rate[] rates) {
            this.rates = rates;
        }
    }

    private static class Rate {
        private double mid;
        private String effectiveDate;

        public double getMid() {
            return mid;
        }

        public void setMid(double mid) {
            this.mid = mid;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(String effectiveDate) {
            this.effectiveDate = effectiveDate;
        }
    }
}

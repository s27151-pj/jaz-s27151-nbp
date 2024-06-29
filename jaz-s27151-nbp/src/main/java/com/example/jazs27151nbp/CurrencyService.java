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
    public double getAverageRate(String startDate, String endDate) {
        String url = "http://api.nbp.pl/api/cenyzlota/" + startDate + "/" + endDate + "/?format=json";
        RestTemplate restTemplate = new RestTemplate();
        NbpResponse response = restTemplate.getForObject(url, NbpResponse.class);

        double sum = 0;
        for (Rate rate : response.getRates()) {
            sum += rate.getCena();
        }
        double averageRate = sum / response.getRates().length;

        GoldQuery query = new GoldQuery();
        query.setStartDate(startDate);
        query.setGold(Gold.GOLD);
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
    }

    private static class Rate {
        private String data;
        private double cena;

        public String getData() {
            return data;
        }

        public double getCena() {
            return cena;
        }

        public void setData(String data) {
            this.data = data;
        }

        public void setCena(double cena) {
            this.cena = cena;
        }

        public String getEffectiveDate() {
            return data ;
        }

        public void setEffectiveDate(String effectiveDate) {
            this.data  = effectiveDate;
        }
    }
}

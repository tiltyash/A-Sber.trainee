package org.example.atm.model;

import lombok.Data;

    @Data
    public class Atm {
        private String id;
        private String name;
        private String streetType;
        private String street;
        private String house;
        private Double atmLatitude;
        private Double atmLongitude;
        private String metroStation;
}

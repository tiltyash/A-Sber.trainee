package org.example.atm.model;

import lombok.Data;
import java.util.List;

@Data
public class AtmResponse {
    private Integer count;
    private Integer pageNumber;
    private Integer pageSize;
    private List<Atm> atm;
}

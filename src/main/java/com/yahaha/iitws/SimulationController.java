package com.yahaha.iitws;

import com.yahaha.iit.calc.IITCalculator;
import com.yahaha.iit.calc.IITRequest;
import com.yahaha.iit.calc.IITResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SimulationController {
    public static final String PATH = "/api/v1";

    private final IITCalculator calculator;

    @Autowired
    public SimulationController(IITCalculator calculator) {
        this.calculator = calculator;
    }

    @PostMapping(path = PATH + "/simulate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IITResponse> simulate(@RequestBody IITRequest request) {
        IITResponse response = calculator.simulate(request);
        return ResponseEntity.ok(response);
    }
}

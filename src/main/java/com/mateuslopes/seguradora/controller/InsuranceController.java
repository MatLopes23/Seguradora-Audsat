package com.mateuslopes.seguradora.controller;

import com.mateuslopes.seguradora.domain.Insurance;
import com.mateuslopes.seguradora.service.InsuranceService;
import com.mateuslopes.seguradora.service.dto.BudgetDto;
import com.mateuslopes.seguradora.service.dto.InsuranceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;


    @PostMapping("/budget")
    public ResponseEntity<Insurance> createInsurance(@RequestBody final InsuranceDto insuranceDto) {
        final Insurance insurance = insuranceService.createInsurance(insuranceDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(insurance);
    }

    @GetMapping("/budget/{insuranceId}")
    public ResponseEntity<BudgetDto> getBudgetByInsuranceId(
            @PathVariable("insuranceId") final Long idInsurance
    ) {
        final BudgetDto budget = insuranceService.calculateBudgetByInsurance(idInsurance);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(budget);
    }

    @PutMapping("/budget/{insuranceId}")
    public ResponseEntity<Insurance> updateInsurance(
            @PathVariable("insuranceId") final Long idInsurance, @RequestBody final InsuranceDto insuranceDto
    ) {
        final Insurance insurance = insuranceService.updateById(idInsurance, insuranceDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(insurance);
    }

    @DeleteMapping("/budget/{insuranceId}")
    public ResponseEntity deleteInsurance(@PathVariable("insuranceId") final Long idInsurance) {
        insuranceService.deleteById(idInsurance);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Insurance excluída com sucesso.");
    }
}

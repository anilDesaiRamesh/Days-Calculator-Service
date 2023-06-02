package com.qantas.insurance.controller;

import com.qantas.insurance.model.InsuranceResponseModel;
import com.qantas.insurance.service.IInsuranceService;
import com.qantas.insurance.util.InputValidatorUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsuranceController {

    private IInsuranceService iInsuranceService;

    private InputValidatorUtil inputValidatorUtil;

    public InsuranceController(IInsuranceService iInsuranceService, InputValidatorUtil inputValidatorUtil) {
        this.iInsuranceService = iInsuranceService;
        this.inputValidatorUtil = inputValidatorUtil;
    }

    @GetMapping("v1/date/difference")
    public ResponseEntity<InsuranceResponseModel> getDays(@RequestParam String startDate,
                                                          @RequestParam String endDate) {

        inputValidatorUtil.validateDateStructure(startDate);
        inputValidatorUtil.validateDateStructure(endDate);
        inputValidatorUtil.validateInputs(startDate, endDate);

        return ResponseEntity.ok(InsuranceResponseModel
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .difference(iInsuranceService.process(startDate, endDate)).build());
    }
}

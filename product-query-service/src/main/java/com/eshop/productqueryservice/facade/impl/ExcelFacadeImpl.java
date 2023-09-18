package com.eshop.productqueryservice.facade.impl;

import com.eshop.productqueryservice.facade.ExcelFacade;
import com.eshop.productqueryservice.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelFacadeImpl implements ExcelFacade {
    private final ExcelService excelService;

    @Override
    public InputStream generateEmptyTemplate() {
        return excelService.generateEmptyTemplate();
    }
}

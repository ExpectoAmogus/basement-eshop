package com.eshop.productcommandservice.facade;

import jakarta.servlet.http.HttpServletRequest;

import java.io.InputStream;

public interface ExcelFacade {
    void processExcelFile(InputStream excelFileStream, HttpServletRequest request);
}

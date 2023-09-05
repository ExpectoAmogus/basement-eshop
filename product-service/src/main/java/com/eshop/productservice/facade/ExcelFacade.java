package com.eshop.productservice.facade;

import jakarta.servlet.http.HttpServletRequest;

import java.io.InputStream;

public interface ExcelFacade {
    void processExcelFile(InputStream excelFileStream, HttpServletRequest request);
    InputStream generateEmptyTemplate();
}

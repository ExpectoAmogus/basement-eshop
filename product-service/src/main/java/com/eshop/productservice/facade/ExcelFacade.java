package com.eshop.productservice.facade;

import java.io.InputStream;

public interface ExcelFacade {
    void processExcelFile(InputStream excelFileStream);
    InputStream generateEmptyTemplate();
}

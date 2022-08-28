package com.easyexcel.service;

import java.util.List;

public interface ExcelBaseService<T> {
    void excelDataBatchSave(List<T> list);
}

package com.bloody.motushack.bloody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

public class XLSB2Lists implements XSSFSheetXMLHandler.SheetContentsHandler {

    private final List<List<String>> sheetAsList = new ArrayList<>();
    private List<String> rowAsList;
    private final List<List<String>> sheetCommentAsList = new ArrayList<>();
    private List<String> rowCommentAsList;
    private final Map<String, String> propertyMap = new HashMap<>();

    public void startSheet(String sheetName) {
        propertyMap.put("sheetName", sheetName);
    }

    @Override
    public void startRow(int rowNum) {
        rowAsList = new ArrayList<>();
        rowCommentAsList = new ArrayList<>();
    }

    @Override
    public void endRow(int rowNum) {
        sheetAsList.add(rowNum, rowAsList);
        sheetCommentAsList.add(rowNum, rowCommentAsList);
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        formattedValue = (formattedValue == null) ? "" : formattedValue;
        rowAsList.add(formattedValue);
        if (comment == null) {
            rowCommentAsList.add("");
        } else {
            propertyMap.put("comment author at "+comment.getRow()+":"+cellReference, comment.getAuthor());
            rowCommentAsList.add(comment.getString().toString().trim());
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        if (isHeader) {
            propertyMap.put("header tag", tagName);
            propertyMap.put("header text", text);
        } else { // footer
            propertyMap.put("header tag", tagName);
            propertyMap.put("header text", text);
        }
    }

    public List<List<String>> getSheetContentAsList(){
        return sheetAsList;
    }

    public List<List<String>> getSheetCommentAsList(){
        return sheetCommentAsList;
    }

    public Map<String, String> getMapOfInfo(){
        return propertyMap;
    }
}
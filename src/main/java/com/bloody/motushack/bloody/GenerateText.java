package com.bloody.motushack.bloody;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.binary.XSSFBSharedStringsTable;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.apache.poi.xssf.binary.XSSFBStylesTable;
import org.apache.poi.xssf.eventusermodel.XSSFBReader;
import org.xml.sax.SAXException;

public class GenerateText {
	public static void main (String [] args) throws IOException{

        String xlsbFileName = "resources/Lexique383.xlsb";
        List<XLSB2Lists> workBookAsList = callXLToList(xlsbFileName);
        List<String> words = new ArrayList<>();
        for (List<String> list : workBookAsList.get(0).getSheetContentAsList()) {
			words.add(stripAccents(list.get(0).toUpperCase()));
		}
        List<String> finalList = words.stream().collect(Collectors.toList());
        HashSet<String> set = new HashSet<>(finalList);
        finalList = new ArrayList<String>(set);
        finalList.sort((p1, p2) -> p1.compareTo(p2));

        BufferedWriter writer = new BufferedWriter(new FileWriter("resources/words.txt"));
        finalList.remove(0);
        finalList.stream().forEach(s->{
			try {
				writer.write(s+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
        
        writer.close();
    }

    static List<XLSB2Lists> callXLToList(String xlsbFileName){
        OPCPackage pkg;
        List<XLSB2Lists> workBookAsList = null;
        try {
            pkg = OPCPackage.open(xlsbFileName);
            XSSFBReader r = new XSSFBReader(pkg);
            XSSFBSharedStringsTable sst = new XSSFBSharedStringsTable(pkg);
            XSSFBStylesTable xssfbStylesTable = r.getXSSFBStylesTable();
            XSSFBReader.SheetIterator it = (XSSFBReader.SheetIterator) r.getSheetsData();

            workBookAsList = new ArrayList<>();
            int sheetNr = 1;
            while (it.hasNext()) {
                InputStream is = it.next();
                String name = it.getSheetName();

                System.out.println("Begin parsing sheet "+sheetNr+": "+name);

                XLSB2Lists testSheetHandler = new XLSB2Lists();
                testSheetHandler.startSheet(name);
                XSSFBSheetHandler sheetHandler = new XSSFBSheetHandler(is,
                        xssfbStylesTable,
                        it.getXSSFBSheetComments(),
                        sst, testSheetHandler,
                        new DataFormatter(),
                        false);
                sheetHandler.parse();
                testSheetHandler.endSheet();

                System.out.println("End parsing sheet "+sheetNr+": "+name);
                sheetNr++;

                workBookAsList.add(testSheetHandler);
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        
        return workBookAsList;
    }
    
    public static String stripAccents(String s) 
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}

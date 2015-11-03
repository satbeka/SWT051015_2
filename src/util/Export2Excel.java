package util;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;

import dboperation.UserData;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

public class Export2Excel {

    public Export2Excel(Shell shell, String[] coloumNames, ArrayList<String[]> arrayListData,
                        String sheetName) {
        FileDialog fileDialog = new FileDialog(shell, SWT.NONE);
        fileDialog.setFilterExtensions(new String[]{"*.xls"});
        fileDialog.setText("");
        fileDialog.setFileName("ReportSport.xls");
        fileDialog.open();
        String path = fileDialog.getFilterPath();
        String fileName = fileDialog.getFileName();
        try {
            WritableWorkbook book = Workbook.createWorkbook((new File(path
                    + "\\" + fileName)));
            WritableSheet sheet = book.createSheet(sheetName, 0);
            for (int i = 0; i < coloumNames.length; i++) {
                sheet.setColumnView(100, 400);
                Label label = new Label(i, 0, coloumNames[i]);
                sheet.addCell(label);
            }


            int sizeData= arrayListData.size();
            //String[] arrV=new String[size];
            for (int i = 0; i < sizeData; i++) {

                String[] rowstr= arrayListData.get(i);
                i = i + 1;
                Label label1A = new Label(0, i, i + "");
                sheet.addCell(label1A);
                for (int j = 0; j < coloumNames.length; j++) {

                    if (j == 0) {
                        Label label = new Label(j, i, i + "");
                        sheet.addCell(label);
                    } else {
                        System.out.println("rowstr[j].toString() =" + rowstr[j].toString());
                        Label label = new Label(j, i, rowstr[j].toString());
                        sheet.addCell(label);
                    }
                }


            }

            /*
            int i = 0;
            while (rSet.next()) {
                i = i + 1;
                Label label1A = new Label(0, i, rSet.getRow() + "");
                sheet.addCell(label1A);
                for (int j = 0; j < coloumNames.length; j++) {
                    System.out.println("j =" + j);
                    if (j == 0) {
                        Label label = new Label(j, i, rSet.getRow() + "");
                        sheet.addCell(label);
                    } else {
                        Label label = new Label(j, i, rSet.getString(j));
                        sheet.addCell(label);
                    }
                }
            }
            */


            book.write();
            book.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

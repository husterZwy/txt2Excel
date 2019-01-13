import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @auth Abel
 * @date 2019/1/13 下午6:34
 */
public class processTxtToExcel {
    static String txtFilePath = "/Users/wenyuanzhao/Downloads/索引导出";
    static String excelFilePath = "/Users/wenyuanzhao/Downloads/output.xls";
    static String colName1 = "time";
    static String colName2 = "name";
    static String colName3 = "author";
    static String colName4 = "category";
    static String colName5 = "serial number";


    public static void main(String[] args) {
        List<TxtObject> txtObjectList = getTxtInfos();
        transToExcel(txtObjectList);
    }

    public static List<TxtObject> getTxtInfos() {
        List<TxtObject> txtObjectList = new ArrayList<>();
        BufferedReader br = null;
        for (int i = 1; i <= 6; i++) {
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilePath + i +".txt"), "utf8"));
                String element = null;
                int index = 0;
                int cycle = 0;
                TxtObject txtObject = new TxtObject();
                while ((element = br.readLine()) != null) {
                    cycle++;
                    if (element.trim().equals("")) {
                        cycle = 0;
                        txtObject = new TxtObject();
                        continue;
                    }
                    switch (cycle) {
                        case 1: {
                            int bracketIndex = element.indexOf(")");
                            txtObject.setName(element.substring(bracketIndex + 5));
                            break;
                        }
                        case 2:
                            txtObject.setAuthor(element.substring(3));
                            break;
                        case 3:
                            break;
                        case 4:
                            txtObject.setTime(element.substring(2));
                            break;
                        case 5: {
                            int doubleSpaceIndex = element.indexOf("  ");
                            txtObject.setSerialNum(element.substring(2, doubleSpaceIndex));
                            break;
                        }
                        case 6:
                            break;
                        default:
                            break;
                    }
                    if (cycle == 6) {
                        txtObjectList.add(txtObject);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return txtObjectList;
    }

    public static void transToExcel(List<TxtObject> txtObjectList) {
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(new File(excelFilePath));
            WritableSheet sheet = book.createSheet("书籍索引", 0);
            Label label1 = new Label(0, 0, colName1);
            Label label2 = new Label(1, 0, colName2);
            Label label3 = new Label(2, 0, colName3);
            Label label4 = new Label(3, 0, colName4);
            Label label5 = new Label(4, 0, colName5);
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            for (int i = 0; i < txtObjectList.size(); i++) {
                TxtObject txtObject = txtObjectList.get(i);
                Label time = new Label(0, (i + 1), txtObject.getTime());
                Label name = new Label(1, (i + 1), txtObject.getName());
                Label author = new Label(2, (i + 1), txtObject.getAuthor());
                Label category = new Label(3, (i + 1), txtObject.getCategory());
                Label serialNum = new Label(4, (i + 1), txtObject.getSerialNum());

                sheet.addCell(time);
                sheet.addCell(name);
                sheet.addCell(author);
                sheet.addCell(category);
                sheet.addCell(serialNum);
            }
            book.write();
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

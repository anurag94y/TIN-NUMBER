package com.anurag;

import com.dropbox.core.DbxException;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static helper.FileOperations.writeInFile;
import static helper.parser.parseHTML;
import static helper.uploader.uploadFile;
import static helper.validator.validateTinNumber;

public class FindTinNumber {

    //07490304055
    public static void main(String[] args) {
        String tinNumber = "07490304055";
        ClassLoader classLoader = new ClassLoader();
        Scanner sc = new Scanner(System.in);
        tinNumber = sc.nextLine();
        Map<String, String> detailMap = null;
        if (validateTinNumber(tinNumber)) {
            try {
                Document doc = Jsoup.connect("http://tinxsys.com/TinxsysInternetWeb/dealerControllerServlet?tinNumber=" + tinNumber + "&searchBy=TIN&backPage=searchByTin_Inter.jsp").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36").get();
                detailMap = parseHTML(doc);
                if (detailMap != null && detailMap.size() > 0) {
                    writeInFile(detailMap, tinNumber);
                    String filePath = String.valueOf(classLoader.getResource("temp.txt").getPath());
                    uploadFile(filePath, tinNumber);
                } else {
                    System.out.println("----------------------------- No details found for Tin Number -> " + tinNumber + "---------------------------------------");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DbxException e) {
                e.printStackTrace();
            }
        }
    }
}

package helper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anurag.yadav on 12/7/16.
 */
public class parser {

    public static Map parseHTML(Document html) {
        Elements table = html.select("table");
        Map<String, String> data = new HashMap<String, String>();
        if (table.size() > 2) {
            Elements row = table.get(2).select("tr");
            for (Element column : row) {
                if (column.select("td").size() > 1) {
                    //System.out.println("1->" + column.select("td").get(0).text());
                    //System.out.println("1->" + column.select("td").get(1).text());
                    data.put(column.select("td").get(0).text(), column.select("td").get(1).text());
                }

            }
            //System.out.println(data);
            return data;
        } else
            return null;
    }
}

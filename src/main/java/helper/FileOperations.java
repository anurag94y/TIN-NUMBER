package helper;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by anurag.yadav on 12/7/16.
 */
public class FileOperations {

    public static void writeInFile(Map<String, String> detailMap, String tinNumber) throws IOException {
        PrintWriter writer = new PrintWriter("src/main/resources/temp.txt", "UTF-8");
        writer.println(getFormatData(detailMap, tinNumber));
        writer.close();
    }

    public static String getFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    private static String getFormatData(Map<String, String> detailMap, String tinNumber) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(String.valueOf(new JSONObject(detailMap)), Object.class);
        String indented = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(json);
        if (detailMap != null && detailMap.size() > 0) {
            System.out.println("--------------------------Details of Tin Number -> " + tinNumber + " ---------------------------\n" + indented);
        }
        return indented;
    }
}

package helper;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static helper.FileOperations.getFileName;

/**
 * Created by anurag.yadav on 12/7/16.
 */
public class uploader {

    private static final String ACCESS_TOKEN = "GnHTvfY7cZAAAAAAAAAADqN2Z8G22eVsqsae89JD9nDdW3Y2C7wQEwYNokMhq5_y";

    public static boolean uploadFile(String fileName, String tinNumber) throws DbxException {
        DbxRequestConfig config = new DbxRequestConfig("dropbox/TIN_NUMBER", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try  {
            InputStream in = new FileInputStream(fileName);
            FileMetadata metadata = client.files().uploadBuilder("/" + getFileName() + "/" + tinNumber + ".txt")
                    .uploadAndFinish(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}


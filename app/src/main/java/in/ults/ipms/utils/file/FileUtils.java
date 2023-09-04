package in.ults.ipms.utils.file;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class FileUtils {

    public static final String MIME_TYPE_IMAGE = "image/*";
    private static final String[] FILE_TYPES_IMAGES = {".jpg", ".png", ".jpeg"};

    //App default document storage location

    public static File getCaptureImageDirectory() {
        File myDir = new File(Environment.getExternalStorageDirectory(), "files");
        try {
            // Find the dir to save cached images
            myDir = new File(Environment.getExternalStorageDirectory(), "drishti");
            if (!myDir.exists())
                if (myDir.mkdirs()) {
                    Log.d("FileUtils", "Files Directory Created");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDir;
    }

    public static Boolean isValidImageFile(String fileUri) {
        boolean supportedConfiguration = false;
        try {
            if (fileUri != null) {
                String fileType = FileUtils.getExtension(fileUri);
                for (String FILE_TYPES_IMAGE : FILE_TYPES_IMAGES) {
                    if (fileType.equalsIgnoreCase(FILE_TYPES_IMAGE)) {
                        supportedConfiguration = true;
                    }
                }
            }
        } catch (Exception e) {
            supportedConfiguration = false;
            e.printStackTrace();
        }
        return supportedConfiguration;
    }

    private static String getExtension(String uri) {
        if (uri == null) {
            return null;
        }
        int dot = uri.lastIndexOf(".");
        if (dot >= 0) {
            return uri.substring(dot);
        } else {
            // No extension.
            return "";
        }
    }

}

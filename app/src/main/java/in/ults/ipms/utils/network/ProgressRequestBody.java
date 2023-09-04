package in.ults.ipms.utils.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by Mohammed Shafeeq on 12/8/2017.
 */

public class ProgressRequestBody extends RequestBody {
    private final File mFile;
    private final UploadCallbacks mListener;
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private final String mediaType;

    public interface UploadCallbacks {
        void onProgressUpdate(int percentage);
        void onError();
        void onFinish();
    }

    public ProgressRequestBody(String mediaType, File mFile, UploadCallbacks mListener) {
        this.mFile = mFile;
        this.mListener = mListener;
        this.mediaType = mediaType;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse(mediaType);
    }

    @Override
    public long contentLength() {
        return mFile.length();
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        long fileLength = mFile.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        try (FileInputStream in = new FileInputStream(mFile)) {
            long uploaded = 0;
            int read;
            Handler handler = new Handler(Looper.getMainLooper());
            while ((read = in.read(buffer)) != -1) {
                // update the progress bar
                handler.post(new ProgressUpdater(uploaded, fileLength));
                uploaded += read;
                sink.write(buffer, 0, read);
            }
        }
    }

    private class ProgressUpdater implements Runnable {
        private final long mUploaded;
        private final long mTotal;

        ProgressUpdater(long uploaded, long total) {
            mUploaded = uploaded;
            mTotal = total;
        }

        @Override
        public void run() {
            Log.d("status", "" + mUploaded);
            Log.d("status", "" + mTotal);
            int percentage = (int) (100 * mUploaded / mTotal);
            mListener.onProgressUpdate(percentage);
            if ((percentage + 1) == 100) {
                mListener.onFinish();
            }
        }
    }
}
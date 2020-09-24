package ru.itis.images.utils;
import java.io.*;
import java.net.URL;

public class ImagesDownloader {
	public void download(String file, String folder, String filename) {
		try {
			URL u = new URL(file);
			InputStream is = u.openStream();
			OutputStream os = new FileOutputStream(folder + filename + ".jpg");
			int x = is.read();
			while (x >= 0) {
				os.write(x);
				x = is.read();
			}
			os.close();
			is.close();
		}
		catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
    }
}
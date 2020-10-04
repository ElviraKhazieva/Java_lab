package ru.itis;
import java.io.*;
import java.net.URL;

public class ImagesDownloader {
	public void download(String file, String folder, String filename) {
		try {
			URL u = new URL(file);
			InputStream inputStream = u.openStream();
			OutputStream outputStream = new FileOutputStream(folder + filename + ".jpg");
			int x = inputStream.read();
			while (x >= 0) {
				outputStream.write(x);
				x = inputStream.read();
			}
			outputStream.close();
			inputStream.close();
		}
		catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
    }
}
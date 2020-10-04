package ru.itis;

import com.beust.jcommander.JCommander;
import ru.itis.ImagesDownloader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {
	public static void main(String[] argv) {
		Args args = new Args();
		JCommander.newBuilder()
  			.addObject(args)
  			.build()
  			.parse(argv);

		ExecutorService executorService = null;
  		if ((args.mode.equals("multi-thread")) && (args.count > 1)) {
			executorService = Executors.newFixedThreadPool(args.count);
			ImagesDownloader imagesDownloader = new ImagesDownloader();
			for (int i = 0; i < args.files.size(); i++) {
				int finalI = i;
				executorService.submit(() ->
						imagesDownloader.download(args.files.get(finalI), args.folder, "picture" + finalI)
				);
			}
		} else if (args.mode.equals("one-thread")) {
			ImagesDownloader imagesDownloader = new ImagesDownloader();
			for (int i = 0; i < args.files.size(); i++) {
				imagesDownloader.download(args.files.get(i), args.folder, "picture" + i);
			}
		} else {
  			throw new IllegalArgumentException();
		}
	}
}
package com.yet.spring.core.loggers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.yet.spring.core.EventLogger;
import com.yet.spring.core.beans.Event;

public class FileEventLogger implements EventLogger {

	private String filename;
	
	public FileEventLogger(String filename) {
		this.filename = filename;
	}

	// will append events into file
	public void logEvent(Event event) {
		
		File file = new File(filename);
		
		try {
			file.createNewFile();
			FileUtils.writeStringToFile(file, event.toString() + "\n", Charset.defaultCharset(), true);
			System.out.println("ok.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

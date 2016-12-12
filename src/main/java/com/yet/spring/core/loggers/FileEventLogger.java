package com.yet.spring.core.loggers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.yet.spring.core.EventLogger;
import com.yet.spring.core.beans.Event;

public class FileEventLogger implements EventLogger {

	private String filename;
	private File file;
	
	public FileEventLogger(String filename) {
		this.filename = filename;
	}
	
	public void init() throws IOException {
		
		this.file = new File(filename);
		
		// check file exists
		if(!file.exists()) {
			file.createNewFile();
		}
		// check file write access
		if (!file.canWrite()) {
			throw new IOException();
		}
		
	}

	// will append events into file
	public void logEvent(Event event) {
			
		try {
			System.out.println(event);
			FileUtils.writeStringToFile(file, event.toString() + "\n", Charset.defaultCharset(), true);
			System.out.println("ok.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

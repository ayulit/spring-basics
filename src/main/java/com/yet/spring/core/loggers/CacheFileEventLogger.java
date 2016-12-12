package com.yet.spring.core.loggers;

import java.util.ArrayList;
import java.util.List;

import com.yet.spring.core.beans.Event;

public class CacheFileEventLogger extends FileEventLogger {

	private int cacheSize;
	private List<Event> cache;
	
	public CacheFileEventLogger(String filename, int cacheSize) {
		super(filename);
		
		this.cacheSize = cacheSize;
		
		this.cache = new ArrayList<Event>();
						
	}
	
	@Override
	public void logEvent(Event event) {
		
		cache.add(event);
		
		if(cache.size() == cacheSize) {
			writeEventsFromCahce();
			cache.clear();
		}
				
	}

	private void writeEventsFromCahce() {
		
		for (Event event: cache) {
			super.logEvent(event);
		}
				
	}

}

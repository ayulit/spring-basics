package com.yet.spring.core.loggers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.yet.spring.core.EventLogger;
import com.yet.spring.core.beans.Event;

public class DBLogger implements EventLogger {
	
	private static final String SQL_ERROR_STATE_SCHEMA_EXISTS = "X0Y68";
	private static final String SQL_ERROR_STATE_TABLE_EXISTS = "X0Y32";

	private JdbcTemplate jdbcTemplate;
	private String schema;

    public DBLogger(JdbcTemplate jdbcTemplate, String schema) {
        
    	this.jdbcTemplate = jdbcTemplate;
    	this.schema = schema.toUpperCase();
    }
	
	// for DB initialization (create table)
	public void init() {
        // DB create
		createDBSchemaIfNotExists();
		
        createTableIfNotExists();
        	
	}
	
	private void createTableIfNotExists() {

		try {
			jdbcTemplate.update("CREATE TABLE t_event (" + 
		                        "id INT NOT NULL PRIMARY KEY," + 
					            "date TIMESTAMP," + 
		                        "msg VARCHAR(255)" + ")");
			System.out.println("Created table t_event");
		} catch (DataAccessException e) {
			
			Throwable causeException = e.getCause();
			
			if (causeException instanceof SQLException) {
				
				SQLException sqlException = (SQLException) causeException;
				
				if (sqlException.getSQLState().equals(SQL_ERROR_STATE_TABLE_EXISTS)) {
					System.out.println("Table already exists");
				} else {
					throw e;
				}				
			} else {
				throw e;
			}
		}
		
	}

	/** Creating DB */
    private void createDBSchemaIfNotExists() {
		try {
			jdbcTemplate.update("CREATE SCHEMA " + schema);
		} catch (DataAccessException e) {
			
			Throwable causeException = e.getCause();
			
			if (causeException instanceof SQLException) {
				
				SQLException sqlException = (SQLException) causeException;
				
				if (sqlException.getSQLState().equals(SQL_ERROR_STATE_SCHEMA_EXISTS)) {
					System.out.println("Schema already exists");
				} else {
					throw e;
				}
				
			} else {
				throw e;
			}
		}
		
	}

	public void destroy() {

    }
	
	// writes events to DB
	@Override
	public void logEvent(Event event) {
				
        jdbcTemplate.update("INSERT INTO t_event (id, date, msg) VALUES (?,?,?)",
        		event.getId(),
        		event.getDate(),
                event.toString());
        
        System.out.println("Saved to DB event with id " + event.getId());
		
		/* TESTING */
		
		int count;
		String msg;
		Event readEvent;
		
		count = jdbcTemplate.queryForObject("SELECT count(*) from t_event", 
				Integer.class);		
		System.out.println("Table in DB has " + count + " rows.");
		
		msg = jdbcTemplate.queryForObject("SELECT msg from t_event WHERE id = ?",
				new Object[]{1}, String.class);
		System.out.println("Message #1 : " + msg);
		
/*		// for getting whole row
		readEvent = jdbcTemplate.queryForObject("",
				new Object[]{1},
				new RowMapper<Event>() {

					@Override
					public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						int id = rs.getInt("id");
						Date date = rs.getDate("date");
						String msg = rs.getString("msg");
						
						Event event = new Event(id, date);
						event.setMsg(msg);
						
						return event;
					} // mapRow			
		        } // RowMapper
		); // queryForObject
		
		System.out.println("Event message = " + readEvent.getMsg());*/
	}

}

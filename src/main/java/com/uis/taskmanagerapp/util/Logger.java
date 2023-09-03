package com.uis.taskmanagerapp.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.uis.taskmanagerapp.constants.Constants;


//Logger class Implemented using Singleton Desin Pattern
public class Logger {

	private static final boolean LOGTOMONITOR = true;
	private static Logger log=null; 
	
	private Logger()
	{
		
	}
	
	public static Logger getInstance()
	{
		if(log==null) {
			log = new Logger();
		}
		return log;
	}
	
	public void log(String data)
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Date date = new Date();
				BufferedWriter writer=null;
				try 
				{
					String message = date+":"+data;
					writer = new BufferedWriter(new FileWriter(Constants.PATH+"\\src\\main\\resources\\log.txt",true));
					writer.write(message);
					writer.newLine();
					
					if(Logger.LOGTOMONITOR) {
						System.out.println("Logger: "+message);
					}
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}finally 
				{
					if(writer!=null) 
					{
						try 
						{
							writer.close();
						} catch (IOException e) 
						{
							e.printStackTrace();
						}
					}
				}
			}
			
		}).start();
		
	}
	
	public static void flush() 
	{
		if(log!=null) {
			log = null;
		}
	}
}

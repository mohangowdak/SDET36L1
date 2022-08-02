package org.vtiger.practice;



import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateCurrentDate {
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		
		String data=new SimpleDateFormat("dd:MM:yyyy").format(date);
		System.out.println(data);
	}

}

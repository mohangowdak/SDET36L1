package org.vtiger.practice;

public class replaceValueInString {
	public static void main(String[] args) {
//String statement="My name is ###";
//String result=statement.replace("###", "Mohan");
//System.out.println(result);
		
		
		String statement="My name is %s";
		String result=String.format(statement, 12);
		System.out.println(result);
	}
}

package org.vtiger.practice;

import java.util.Iterator;

public class Checkwheatherstringorint {
	
	public static void main(String[] args) {
		String s="5786a";
		String s1="";
		
		for (int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i)))
			{
				s1=s1+s.charAt(i);
			}
			else {
				break;
			}
		}
		int number=0;
		if(s.length()==s1.length()) {
			 number=Integer.parseInt(s1);
		}
		System.out.println(number);
	}

}

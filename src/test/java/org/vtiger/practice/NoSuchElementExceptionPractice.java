package org.vtiger.practice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NoSuchElementExceptionPractice {
public static void main(String[] args) {
Set<String> set=new HashSet<>();
set.add("hi");
set.add("hello");
set.add("welcome");
set.add("bangalore");

Iterator<String> itr = set.iterator();
	System.out.println(itr.next());
	System.out.println(itr.next());
	System.out.println(itr.next());
	System.out.println(itr.next());
	System.out.println(itr.next());

	
}
}

package org.vtiger.practice;

public class SplitProgram {
	public static void main(String[] args) {
		String path="/src/test/resources/Vtiger Scenarios.txt";
		String[] data = path.split("/");
		System.out.println(data[data.length-1]);
	}

}

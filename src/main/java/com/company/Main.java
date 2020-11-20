package com.company;

public class Main {

    public static void main(String[] args) {
    	ReportService reportService = new ReportService();
		System.out.println(reportService.foundDifference("horse", "horses")); //1
		System.out.println(reportService.foundDifference("horse", "ohrse")); //1
		System.out.println(reportService.foundDifference("orse", "horse")); //1
		System.out.println(reportService.foundDifference("ors", "horse")); //2
		System.out.println(reportService.foundDifference("horse", "horse")); //0
		System.out.println(reportService.foundDifference("horse", "ohsre")); //2
		System.out.println(reportService.foundDifference("horse", "ohsroe")); //3
    }
}

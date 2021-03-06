package com.example.RPAValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Rahul Reddy Ravipally
 *
 */

public class Validation {
	public static void main(String args[]) throws IOException {
		
		String extracted_value="";
		String actual_value="";
		int rulecount = 0;
		int logcount = 0;
		
		   final String dir = System.getProperty("user.dir");
		   String path_rules = ""; // Update this with absolute path of rules.txt file
		   String path_logs = ""; // Update this with absolute path of validation.txt file
		
		BufferedReader in = new BufferedReader(new FileReader(path_rules));
		String line;
		List<String> allStrings = new ArrayList<String>();
		while((line = in.readLine()) != null)
		{
		    allStrings.add(line);
		}
		for (int i=0;i < allStrings.size();i++)
		{
			
		String regex = "and when at least [0-9]* events or flows are seen with the same Event Name, Source IP in [0-9]* minutes"; 
			  
	        Pattern pattern 
	            = Pattern.compile(regex);
	  
	        Matcher matcher 
	            = pattern 
	                  .matcher(allStrings.get(i).toString()); 
	  
	        if(matcher.find()) { 
			  Pattern p = Pattern.compile("\\d+");
		      Matcher m = p.matcher(allStrings.get(i).toString());
		      if(m.find()) {
		    	  	extracted_value=m.group().toString();
		    	  	 rulecount=Integer.parseInt(extracted_value);  
		        }
	        }
	        }
		
		in.close();
		
		BufferedReader i = new BufferedReader(new FileReader(path_logs));
		String num;
		if((num = i.readLine()) != null)
		{
		    actual_value=num.toString();
		    logcount=Integer.parseInt(actual_value); 
		}
		if(rulecount <= logcount) {
			System.out.println("Offence is valid");
		}
		else {
			System.out.println("Offence is invalid");
		}
		i.close();
	}
}

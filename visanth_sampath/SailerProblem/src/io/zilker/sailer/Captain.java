package io.zilker.sailer;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class Captain {
	public static Logger logger = Logger.getLogger(Captain.class.getName());
	private static String fileName = "/home/visanth/input.csv";
		//search the name in ArrayList A
	private static GroupA searchInArrayListA(String name,ArrayList<GroupA> personsInGroupA) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<personsInGroupA.size();i++) {
			//logger.info(personsInGroupA.get(i).getName());
			if(personsInGroupA.get(i).personName.equals(name)) {
				
				return personsInGroupA.get(i);
			}
		}
		return null;
	}
	
	//search the name in ArrayList B
		private static GroupB searchInArrayListB(String name,ArrayList<GroupB> personsInGroupB) {
			// TODO Auto-generated method stub
			for(int i=0;i<personsInGroupB.size();i++) {
				personsInGroupB.get(i);
				if(personsInGroupB.get(i).name.equals(name)) {
					return personsInGroupB.get(i);
					
				}
			}
			return null;
		}
	
	public static void main(String [] args) {
		try {
			 ArrayList<GroupA> personsInGroupA = new ArrayList<GroupA> ();
			 ArrayList<GroupB> personsInGroupB = new ArrayList<GroupB> ();

			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String newLine;
		    while ((newLine = br.readLine()) != null) {
		        
		        String [] inputFromFile = newLine.split(",");
		        //logger.info(inputFromFile[1]);
		        if(inputFromFile[1].equals("banana") || inputFromFile[1].equals("apple")||inputFromFile[1].equals("mango")) {
		        	GroupA person = searchInArrayListA(inputFromFile[0],personsInGroupA);
		        	if(person != null) {
		        		
		        		if(inputFromFile[1].equals("banana")) {
		        			person.numberOfBanana=Integer.parseInt(inputFromFile[2]);
		        		}
		        		else if(inputFromFile[1].equals("apple")) {
		        			person.numberOfApples=Integer.parseInt(inputFromFile[2]);
		        		}
		        		else if(inputFromFile[1].equals("mango")) {
		        			person.numberOfMangoes=Integer.parseInt(inputFromFile[2]);
		        		}
		        		
		        	}
		        	else {
		        		//logger.info("Enters");
		        		GroupA newPerson = new GroupA();
		        		newPerson.personName=inputFromFile[0];
		        		if(inputFromFile[1].equals("banana")) {
		        			newPerson.numberOfBanana=Integer.parseInt(inputFromFile[2]);
		        		}
		        		else if(inputFromFile[1].equals("apple")) {
		        			newPerson.numberOfApples=Integer.parseInt(inputFromFile[2]);
		        		}
		        		else if(inputFromFile[1].equals("mango")) {
		        			newPerson.numberOfMangoes=Integer.parseInt(inputFromFile[2]);
		        		}
		        		//logger.info(newPerson.getName());
		        		personsInGroupA.add(newPerson);
		        	}
		        	
		        }
		        else {
		        	GroupB person = searchInArrayListB(inputFromFile[0],personsInGroupB);
		        	if(person !=null) {
		        		if(inputFromFile[1].equals("bigfish")) {
		        			person.numberOfBigFish=Integer.parseInt(inputFromFile[2]);
		        		}
		        		else if(inputFromFile[1].equals("smallfish")) {
		        			person.numberOfSmallFish=Integer.parseInt(inputFromFile[2]);
		        		}	
		        	}
		        	else {
		        		GroupB newPerson = new GroupB();
		        		newPerson.name=(inputFromFile[0]);
		        		if(inputFromFile[1].equals("bigfish")) {
		        			newPerson.numberOfBigFish=Integer.parseInt(inputFromFile[2]);
		        		}
		        		else if(inputFromFile[1].equals("smallfish")) {
		        			newPerson.numberOfSmallFish=Integer.parseInt(inputFromFile[2]);
		        		}	
		        		personsInGroupB.add(newPerson);
		        	
		        	}
		        
		        }
		    
		    }
		    br.close();
		    Collections.sort(personsInGroupA, new SortGroupA());
		    Collections.sort(personsInGroupB, new SortGroupB());
		    for(int i=0;i<personsInGroupA.size();i++) {
		    	GroupA person = personsInGroupA.get(i);
		    	logger.info(person.personName+" "+person.numberOfBanana+" "+person.numberOfApples +" "+person.numberOfMangoes);
		    }
		    for(int i=0;i<personsInGroupB.size();i++) {
		    	GroupB person = personsInGroupB.get(i);
		    	logger.info(person.name+" "+person.numberOfBigFish+" "+person.numberOfSmallFish );
		    }
		}
		catch(Exception e) {
			logger.info(e.toString());
			e.printStackTrace();
		}
		finally {
			
		}
	}
	

}

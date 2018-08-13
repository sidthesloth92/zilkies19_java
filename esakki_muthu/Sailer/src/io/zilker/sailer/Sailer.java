package io.zilker.sailer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Logger;

public class Sailer {

	static int grpAcount = 0, grpBcount = 0;

	static String csv_path = "/home/administrator/Desktop/sailer.csv";

	static BufferedReader br = null;

	static String line = "";

	static String cvsSplitBy = ",";

	static Scanner in = new Scanner(System.in);

	static ArrayList<GroupA> grpA = new ArrayList<GroupA>();

	static ArrayList<GroupB> grpB = new ArrayList<GroupB>();
	
	static Logger logger=Logger.getLogger(Sailer.class.getName());

	public static void main(String[] args) {

		logger.info("Enter the A Count");

		grpAcount = in.nextInt();

		in.nextLine();

		String name;

		// grpAmembers=new ArrayList<HashMap<String,HashMap<String,Integer>>>();

		// System.out.println("Enter A Group Member Names");

		for (int pos = 0; pos < grpAcount; pos++) {

			logger.info("\nEnter Name : ");

			GroupA aGroup = new GroupA();

			aGroup.apple = 0;

			aGroup.total = 0;

			aGroup.banana = 0;

			aGroup.mango = 0;

			aGroup.name = in.nextLine();

			grpA.add(aGroup);

		}

		logger.info("\nEnter the B Count");

		grpBcount = in.nextInt();

		in.nextLine();

		for (int pos = 0; pos < grpBcount; pos++) {

			logger.info("\nEnter Name : ");

			GroupB bGroup = new GroupB();

			bGroup.name = in.nextLine();

			bGroup.bigFish = 0;

			bGroup.smalFish = 0;

			bGroup.total = 0;

			grpB.add(bGroup);

		}

		readCsv();

		Collections.sort(grpA,new SortDataA());
        
        Collections.sort(grpB,new SortDataB());
        
        logger.info("\nGroup A\n");
        
        for(GroupA obj:grpA){
        	
            int total=obj.apple+obj.banana+obj.mango;
            
           logger.info("\nName   : "+obj.name+"\nApple  :  "+obj.apple+"\nMango  : "+obj.mango+"\nBanana  : "+obj.banana+"\nTotal :  "+total+" \n");
            
        }
        
        logger.info("\nGroup B\n");
        
        for(GroupB obj:grpB){
        	
            int total=obj.smalFish+obj.bigFish;
            
            logger.info("\nName   : "+obj.name+"\nSmallFish : "+obj.smalFish+"\nBigFish   : "+obj.bigFish+"\nTotal    : "+total+"\n");
            
        }
		
	}

	public static void readCsv() {

		try {

			br = new BufferedReader(new FileReader(csv_path));

			while ((line = br.readLine()) != null) {

				String[] datas = line.split(",");

				String name = datas[0];

				String type = datas[1];

				int count = Integer.parseInt(datas[2]);

				for (GroupA obj : grpA) {
					if (obj.name.equals(name)) {
						if (type.equals("banana")) {
							obj.banana = count;
						}
						if (type.equals("apple")) {
							obj.apple = count;
						}
						if (type.equals("mango")) {
							obj.mango = count;
						}
					}
				}
				for (GroupB obj : grpB) {
					if (obj.name.equals(name)) {
						if (type.equals("smallfish")) {
							obj.smalFish = count;
						}
						if (type.equals("bigfish")) {
							obj.bigFish = count;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

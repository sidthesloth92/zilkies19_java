package io.ztech.jkingsley.captainofship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class CaptainLog {

	private static final Logger LOGGER = Logger.getLogger(CaptainLog.class.getName());
	private static FileReader fileReader;

	private static HashMap<Sailor, GroupA> groupAList;
	private static HashMap<Sailor, GroupB> groupBList;

	private static int numberOfSailors = 0;

	private static void openFileReader(String path) {
		try {
			fileReader = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void closeFileReader() {
		try {
			fileReader.close();
		} catch (Exception e) {
			LOGGER.info("Exception " + e);
		}
	}

	public static void main(String[] args) {

		openFileReader("/home/joelkingsley/zilkies2019_java/joel_kingsley/CaptainOfShip/src/io/ztech/captainofship/assets/log.txt");

		groupAList = new HashMap<>();
		groupBList = new HashMap<>();

		try (BufferedReader br = new BufferedReader(fileReader)) {

			for (String line = ""; (line = br.readLine()) != null;) {
				// process the line.

				if (line.isEmpty()) {
					continue;
				}

				String[] sailorString = line.trim().split(" ");
				Long userId = Long.valueOf(sailorString[0]);
				String name = sailorString[1];
				char group = Character.valueOf(sailorString[2].charAt(0));

				Sailor sailor = new Sailor(userId, name);
				if (group == 'A') {
					GroupA groupA = new GroupA();
					for (int i = 0; i < 3; i++) {
						line = br.readLine();
						String[] inputString = line.trim().split(" ");
						switch (inputString[0]) {
						case "apple":
							int applesCollected = Integer.parseInt(inputString[1]);
							groupA.setApplesCollected(applesCollected);
							break;
						case "mango":
							int mangoesCollected = Integer.parseInt(inputString[1]);
							groupA.setMangoesCollected(mangoesCollected);
							break;
						case "banana":
							int bananasCollected = Integer.parseInt(inputString[1]);
							groupA.setBananasCollected(bananasCollected);
							break;
						}
					}
					groupAList.put(sailor, groupA);
				} else if (group == 'B') {
					GroupB groupB = new GroupB();
					for (int i = 0; i < 3; i++) {
						line = br.readLine();
						String[] inputString = line.trim().split(" ");
						switch (inputString[0]) {
						case "smallfish":
							int smallFishCollected = Integer.parseInt(inputString[1]);
							groupB.setSmallFishCollected(smallFishCollected);
							break;
						case "mediumfish":
							int mediumFishCollected = Integer.parseInt(inputString[1]);
							groupB.setMediumFishCollected(mediumFishCollected);
							break;
						case "largefish":
							int bigFishCollected = Integer.parseInt(inputString[1]);
							groupB.setBigFishCollected(bigFishCollected);
							break;
						}
					}
					groupBList.put(sailor, groupB);
				}

			}

			Iterator it = groupAList.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Sailor, GroupA> pair = (Map.Entry<Sailor, GroupA>) it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());
				it.remove(); // avoids a ConcurrentModificationException
			}
			Iterator it2 = groupBList.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry<Sailor, GroupB> pair = (Map.Entry<Sailor, GroupB>) it2.next();
				System.out.println(pair.getKey().toString() + " = " + pair.getValue().toString());
				it2.remove(); // avoids a ConcurrentModificationException
			}
			// line is not visible here.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeFileReader();
		}
	}
}

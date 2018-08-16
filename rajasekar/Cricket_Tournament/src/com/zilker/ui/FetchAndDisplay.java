package com.zilker.ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.UserDetails;
import com.zilker.constant.ConsoleStrings;
import com.zilker.constant.RegexStrings;
import com.zilker.beans.Schedule;

public class FetchAndDisplay {
	Scanner input = new Scanner(System.in);
	Logger logger = Logger.getLogger(FetchAndDisplay.class.getName());
	String player_name = "", player_role = "", password = "", age = "", playingFormat = "";;
	ArrayList<Player> al = new ArrayList<Player>();
	String team[] = new String[2];
	ArrayList<String> arrayList = new ArrayList<String>();
	int option = 0, loginOption = 0;
	boolean flag = false, flagvar = false, check = false;
	char ch = ' ';

	public int loginOption() {
		do {
			System.out.println(ConsoleStrings.LOGIN_OPTION);
			try {
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int loginRegisterChoice() {
		do {
			System.out.println(ConsoleStrings.LOGIN_REGISTER_CHOICE);
			try {
				loginOption = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return loginOption;
	}

	public void displayInvalid() {
		System.out.println(ConsoleStrings.INVALID);
	}

	public Login login() {
		Login loginObj = new Login();
		System.out.println(ConsoleStrings.WELCOME_LOGIN);
		System.out.println(ConsoleStrings.EMAIL);
		loginObj.setEmail(input.nextLine());
		System.out.println(ConsoleStrings.PASSWORD);
		loginObj.setPassword(input.nextLine());
		return loginObj;
	}

	public int formatOption() {
		do {
			System.out.println(ConsoleStrings.FORMAT_OPTION);
			try {
				int option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int adminOption() {
		do {
			System.out.println(ConsoleStrings.ADMIN_OPTION);
			try {
				int option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int tournament_id() {
		int tour_id = 0;
		do {
			System.out.println(ConsoleStrings.TOURNAMENT_ID);
			try {
				tour_id = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return tour_id;
	}

	public char view_tournament() {
		do {
			try {
				System.out.println(ConsoleStrings.VIEW_TOURNAMENT);
				ch = input.next().charAt(0);
				check = false;
			} catch (Exception e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}

	public char register_tournament() {
		try {
			System.out.println(ConsoleStrings.REGISTER_TOURNAMENT);
			ch = input.next().charAt(0);
			check = false;
		} catch (Exception e) {
			System.out.println(ConsoleStrings.EXCEPTION_MSG);
			check = true;
		}
		return ch;
	}

	public void logout() {
		System.out.println(ConsoleStrings.LOGOUT);
	}

	public Team teamDetails(int tournament_id, String email) {
		Team teamobj = new Team();
		teamobj.setTournamentId(tournament_id);
		System.out.println(ConsoleStrings.TEAM_DETAILS);
		System.out.println(ConsoleStrings.TEAM_NAME);
		teamobj.setTeamName(input.nextLine());
		teamobj.setEmail(email);
		return teamobj;
	}

	public AdminDetails getAdminDetails() {
		AdminDetails adminobj = new AdminDetails();
		while (flag == false) {
			System.out.println(ConsoleStrings.FIRST_NAME);
			adminobj.setFirstName(input.nextLine());
			flag = Validation.isValidated(adminobj.getFirst_name(), RegexStrings.NAME_REGEX);
		}
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.LAST_NAME);
			adminobj.setLastName(input.nextLine());
			flag = Validation.isValidated(adminobj.getLast_name(), RegexStrings.NAME_REGEX);
		}
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.PASSWORD);
			adminobj.setPassword(input.nextLine());
			flag = Validation.isValidated(adminobj.getPassword(), RegexStrings.PASSWORD_REGEX);
		}
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.AGE);
			age = input.nextLine();
			flag = Validation.isValidated(age, RegexStrings.AGE_REGEX);
		}
		adminobj.setAge(Integer.parseInt(age));
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.MOBILE);
			adminobj.setMobile(input.nextLine());
			flag = Validation.isValidated(adminobj.getMobile(), RegexStrings.MOBILE_REGEX);
		}
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.EMAIL);
			adminobj.setEmail(input.nextLine());
			flag = Validation.isValidated(adminobj.getEmail(), RegexStrings.EMAIL_REGEX);
		}
		flag = false;
		return adminobj;
	}

	public ArrayList<Player> getPlayerDetails(int teamid) {
		for (int i = 0; i < 13; i++) {
			Player obj = new Player();
			obj.setTeamId(teamid);
			flagvar = false;
			System.out.println((i + 1) + ConsoleStrings.PLAYER_NAME);
			player_name = input.nextLine();
			obj.setPlayerName(player_name);
			while (flagvar == false) {
				System.out.println(ConsoleStrings.PLAYER_ROLE);
				do {
					try {
						System.out.println(ConsoleStrings.GET_ROLE);
						int player_option = Integer.parseInt(input.nextLine());
						if (player_option == 1) {
							player_role = ConsoleStrings.BATSMAN;
							flagvar = true;
						} else if (player_option == 2) {
							player_role = ConsoleStrings.BOWLER;
							flagvar = true;
						} else if (player_option == 3) {
							player_role = ConsoleStrings.ALL_ROUNDER;
							flagvar = true;
						} else if (player_option == 4) {
							player_role = ConsoleStrings.WK_BATSMAN;
							flagvar = true;
						} else {
							System.out.println(ConsoleStrings.INVALID);
							flagvar = false;
						}
						check = false;
					} catch (NumberFormatException e) {
						System.out.println(ConsoleStrings.EXCEPTION_MSG);
						check = true;
					}

				} while (check);
			}
			obj.setPlayerRole(player_role);
			al.add(obj);
		}
		return al;
	}

	public UserDetails getUserDetails() {
		UserDetails userobj = new UserDetails();
		while (flag == false) {
			System.out.println(ConsoleStrings.FIRST_NAME);
			userobj.setFirstName(input.nextLine());
			flag = Validation.isValidated(userobj.getFirst_name(), RegexStrings.NAME_REGEX);
		}
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.LAST_NAME);
			userobj.setLastName(input.nextLine());
			flag = Validation.isValidated(userobj.getLast_name(), RegexStrings.NAME_REGEX);
		}
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.PASSWORD);
			userobj.setPassword(input.nextLine());
			flag = Validation.isValidated(userobj.getPassword(), RegexStrings.PASSWORD_REGEX);
		}
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.AGE);
			age = input.nextLine();
			flag = Validation.isValidated(age, RegexStrings.AGE_REGEX);
		}
		userobj.setAge(Integer.parseInt(age));
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.MOBILE);
			userobj.setMobile(input.nextLine());
			flag = Validation.isValidated(userobj.getMobile(), RegexStrings.MOBILE_REGEX);
		}
		flag = false;
		while (flag == false) {
			System.out.println(ConsoleStrings.EMAIL);
			userobj.setEmail(input.nextLine());
			flag = Validation.isValidated(userobj.getEmail(), RegexStrings.EMAIL_REGEX);
		}
		flag = false;
		return userobj;
	}

	public Tournament getTournamentDetails() {
		Tournament tourobj = new Tournament();
		System.out.println(ConsoleStrings.TOURNAMENT_NAME);
		tourobj.setTournamentName(input.nextLine());
		while (flagvar == false) {
			System.out.println(ConsoleStrings.FORMAT);
			do {
				try {
					System.out.println(ConsoleStrings.SHOW_OPTION);
					int format = Integer.parseInt(input.nextLine());
					if (format == 1) {
						playingFormat = ConsoleStrings.ODI;
						flagvar = true;
					} else if (format == 2) {
						playingFormat = ConsoleStrings.T20;
						flagvar = true;
					} else if (format == 3) {
						playingFormat = ConsoleStrings.TEST;
						flagvar = true;
					} else {
						flagvar = false;
					}
					check = false;
				} catch (NumberFormatException e) {
					System.out.println(ConsoleStrings.EXCEPTION_MSG);
					check = true;
				}
			} while (check);
		}
		tourobj.setFormat(playingFormat);
		return tourobj;
	}

	public String getAdminPassword() throws IOException {
		FileReader fr = new FileReader("/home/rajasekar/Desktop/authentication.txt");
		int i;
		while ((i = fr.read()) != -1)
			password += (char) i;
		fr.close();
		return password;
	}

	public void registerStatus() {
		System.out.println(ConsoleStrings.REGISTRATION_SUCCESS);
	}

	public void teamRegister() {
		System.out.println(ConsoleStrings.TEAM_REGISTRATION_SUCCESS);
		System.out.println(ConsoleStrings.TOURNAMENT_TEAMS);
	}

	public int displayTournament(ArrayList hm) {
		if (hm.size() == 0) {
			System.out.println(ConsoleStrings.EMPTY_TOURNAMENT);
			return 0;
		}
		System.out.println(ConsoleStrings.DASHED_LINES);
		for (int i = 0; i < hm.size(); i += 3) {
			System.out.printf("%20d%20s%20s\n", hm.get(i), hm.get(i + 1), hm.get(i + 2));
		}
		System.out.println(ConsoleStrings.DASHED_LINES);
		System.out.println(ConsoleStrings.TOURNAMENT_ID);
		int tour_id = input.nextInt();
		input.nextLine();
		return tour_id;
	}

	public void displayTeam(ArrayList<String> teamList) {
		System.out.println(ConsoleStrings.DASHED_LINES);
		System.out.println(ConsoleStrings.AVAILABLE_TEAMS);
		for (int i = 0; i < teamList.size(); i++) {
			System.out.println(teamList.get(i));
		}
		System.out.println(ConsoleStrings.DASHED_LINES);
	}

	public Schedule fixSchedule(int[] teamId, String match, int tournament_id) {
		Schedule scheduleobj = new Schedule();
		scheduleobj.setTeam1Id(teamId[0]);
		scheduleobj.setTeam2Id(teamId[1]);
		scheduleobj.setMatches(match);
		scheduleobj.setTournamentId(tournament_id);
		return scheduleobj;
	}

	public void showMessage(int flag) {
		if (flag == 0) {
			System.out.println(ConsoleStrings.DASHED_LINES);
			System.out.println(ConsoleStrings.TEAM_NAME_EXISTS);
			System.out.println(ConsoleStrings.DASHED_LINES);
		}
	}

	public int getUserOption() {
		int option = 0;
		do {
			try {
				System.out.printf(ConsoleStrings.USER_OPTION);
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int getEditOption() {
		int option = 0;
		do {
			try {
				System.out.println(ConsoleStrings.EDIT_OPTION);
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public String[] getTeamName() {
		System.out.println(ConsoleStrings.OLD_TEAM_NAME);
		team[0] = input.nextLine();
		System.out.println(ConsoleStrings.NEW_TEAM_NAME);
		team[1] = input.nextLine();
		return team;
	}

	public ArrayList<String> updatePlayerDetails(String team) {
		arrayList.clear();
		arrayList.add(team);
		System.out.println(ConsoleStrings.NEW_PLAYER_NAME);
		arrayList.add(input.nextLine());
		System.out.println(ConsoleStrings.NEW_PLAYER_ROLE);
		do {
			try {
				System.out.println(ConsoleStrings.GET_ROLE);
				int player_option = Integer.parseInt(input.nextLine());
				if (player_option == 1) {
					player_role = ConsoleStrings.BATSMAN;
					flagvar = true;
				} else if (player_option == 2) {
					player_role = ConsoleStrings.BOWLER;
					flagvar = true;
				} else if (player_option == 3) {
					player_role = ConsoleStrings.ALL_ROUNDER;
					flagvar = true;
				} else if (player_option == 4) {
					player_role = ConsoleStrings.WK_BATSMAN;
					flagvar = true;
				}
				check = false;
			} catch (Exception e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		arrayList.add(player_role);
		return arrayList;
	}

	public void displaySchedule(ArrayList schedule) {
		System.out.println(ConsoleStrings.DASHED_LINES);
		for (int i = 0; i < schedule.size(); i += 5) {
			System.out.printf("%20d%20d%20d%20s%20d\n", schedule.get(i), schedule.get(i + 1), schedule.get(i + 2),
					schedule.get(i + 3), schedule.get(i + 4));
		}
		System.out.println(ConsoleStrings.DASHED_LINES);
	}

	public char tournamentMenu() {
		do {
			try {
				System.out.println(ConsoleStrings.MAIN_MENU);
				ch = input.nextLine().charAt(0);
				check = false;
			} catch (Exception e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}

	public void displayInvalidTeamName(int status) {
		if (status == 0)
			System.out.println(ConsoleStrings.INVALID_TEAM_NAME);
		else
			System.out.println(ConsoleStrings.TEAM_NAME_UPDATED);
	}

	public char getAdminMenu() {
		do {
			try {
				System.out.println(ConsoleStrings.ADMIN_MENU);
				ch = input.nextLine().charAt(0);
				check = false;
			} catch (Exception e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}

	public void displayNoTeamsAvailable() {
		System.out.println(ConsoleStrings.TEAM_AVAILABILITY);
	}

	public int getPlayerId(ArrayList al) {
		System.out.println(ConsoleStrings.DASHED_LINES);
		for (int i = 0; i < al.size(); i += 2) {
			System.out.printf("%20d%20s\n", al.get(i), al.get(i + 1));
		}
		System.out.println(ConsoleStrings.DASHED_LINES);
		System.out.println(ConsoleStrings.PLAYER_ID);
		int player_id = Integer.parseInt(input.nextLine());
		return player_id;
	}

	public String getTeam() {
		System.out.println(ConsoleStrings.TEAM_NAME);
		return input.nextLine();
	}

	public char option() {
		do {
			try {
				System.out.println(ConsoleStrings.CONTINUE_MENU);
				ch = input.nextLine().charAt(0);
				check = false;
			} catch (Exception e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}

	public void noPlayers() {
		System.out.println(ConsoleStrings.NO_PLAYERS);
	}

	public void showScheduled() {
		System.out.println(ConsoleStrings.SCHEDULE_SUCCESS);
	}

	public char getMainmenu() {
		do {
			try {
				System.out.println(ConsoleStrings.ROOT_MENU);
				ch = input.nextLine().charAt(0);
				check = false;
			} catch (Exception e) {
				System.out.println(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}
}

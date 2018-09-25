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
import com.zilker.beans.Score;
import com.zilker.beans.UserDetails;
import com.zilker.constant.ConsoleStrings;
import com.zilker.constant.RegexStrings;
import com.zilker.beans.Schedule;
import com.zilker.beans.Scorecard;

public class FetchAndDisplay {
	Scanner input = new Scanner(System.in);
	Logger logger = Logger.getLogger(FetchAndDisplay.class.getName());
	String playerName, playerRole, password, age, playingFormat;
	ArrayList<Player> al = new ArrayList<Player>();
	String team[] = new String[2];
	ArrayList<String> arrayList = new ArrayList<String>();
	int option, loginOption,maxMatch,minMatch;
	Validation valid = new Validation();
	boolean flag, flagvar, check;
	char ch;

	public FetchAndDisplay() {
		ch = ' ';
		flag = false;
		flagvar = false;
		check = false;
		option = 0;
		loginOption = 0;
		maxMatch=0;
		minMatch=0;
		playerName = "";
		playerRole = "";
		password = "";
		age = "";
		playingFormat = "";
	}

	public int loginOption() {
		do {
			logger.info(ConsoleStrings.LOGIN_OPTION);
			try {
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int loginRegisterChoice() {
		do {
			logger.info(ConsoleStrings.LOGIN_REGISTER_CHOICE);
			try {
				loginOption = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return loginOption;
	}

	public void displayInvalid() {
		logger.info(ConsoleStrings.INVALID);
	}

	public Login login() {
		Login loginObj = new Login();
		logger.info(ConsoleStrings.WELCOME_LOGIN);
		logger.info(ConsoleStrings.EMAIL);
		loginObj.setEmail(input.nextLine());
		logger.info(ConsoleStrings.PASSWORD);
		loginObj.setPassword(input.nextLine());
		return loginObj;
	}

	public int formatOption() {
		do {
			logger.info(ConsoleStrings.FORMAT_OPTION);
			try {
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int adminOption() {
		do {
			logger.info(ConsoleStrings.ADMIN_OPTION);
			try {
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int tournamentId() {
		int tour_id = 0;
		do {
			logger.info(ConsoleStrings.TOURNAMENTID);
			try {
				tour_id = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return tour_id;
	}

	public char view_tournament() {
		do {
			try {
				logger.info(ConsoleStrings.VIEW_TOURNAMENT);
				ch = input.next().charAt(0);
				check = false;
			} catch (Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}

	public char register_tournament() {
		try {
			logger.info(ConsoleStrings.REGISTER_TOURNAMENT);
			ch = input.next().charAt(0);
			check = false;
		} catch (Exception e) {
			logger.info(ConsoleStrings.EXCEPTION_MSG);
			check = true;
		}
		return ch;
	}

	public void logout() {
		logger.info(ConsoleStrings.LOGOUT);
	}

	public Team teamDetails(int tournamentId, String email) {
		Team teamobj = new Team();
		teamobj.setTournamentId(tournamentId);
		logger.info(ConsoleStrings.TEAM_DETAILS);
		logger.info(ConsoleStrings.TEAMNAME);
		teamobj.setTeamName(input.nextLine());
		teamobj.setEmail(email);
		return teamobj;
	}

	public AdminDetails getAdminDetails() {
		AdminDetails adminobj = new AdminDetails();
		while (flag == false) {
			logger.info(ConsoleStrings.FIRSTNAME);
			adminobj.setFirstName(input.nextLine());
			flag = valid.isValidated(adminobj.getfirstName(), RegexStrings.NAME_REGEX);
		}
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.LASTNAME);
			adminobj.setLastName(input.nextLine());
			flag = valid.isValidated(adminobj.getlastName(), RegexStrings.NAME_REGEX);
		}
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.PASSWORD);
			adminobj.setPassword(input.nextLine());
			flag = valid.isValidated(adminobj.getPassword(), RegexStrings.PASSWORD_REGEX);
		}
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.AGE);
			age = input.nextLine();
			flag = valid.isValidated(age, RegexStrings.AGE_REGEX);
		}
		adminobj.setAge(Integer.parseInt(age));
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.MOBILE);
			adminobj.setMobile(input.nextLine());
			flag = valid.isValidated(adminobj.getMobile(), RegexStrings.MOBILE_REGEX);
		}
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.EMAIL);
			adminobj.setEmail(input.nextLine());
			flag = valid.isValidated(adminobj.getEmail(), RegexStrings.EMAIL_REGEX);
		}
		flag = false;
		return adminobj;
	}

	public ArrayList<Player> getPlayerDetails(int teamid) {
		for (int i = 0; i < 13; i++) {
			Player obj = new Player();
			obj.setTeamId(teamid);
			flagvar = false;
			logger.info((i + 1) + ConsoleStrings.PLAYERNAME);
			playerName = input.nextLine();
			obj.setPlayerName(playerName);
			while (flagvar == false) {
				logger.info(ConsoleStrings.PLAYERROLE);
				do {
					try {
						logger.info(ConsoleStrings.GET_ROLE);
						int player_option = Integer.parseInt(input.nextLine());
						if (player_option == 1) {
							playerRole = ConsoleStrings.BATSMAN;
							flagvar = true;
						} else if (player_option == 2) {
							playerRole = ConsoleStrings.BOWLER;
							flagvar = true;
						} else if (player_option == 3) {
							playerRole = ConsoleStrings.ALL_ROUNDER;
							flagvar = true;
						} else if (player_option == 4) {
							playerRole = ConsoleStrings.WK_BATSMAN;
							flagvar = true;
						} else {
							logger.info(ConsoleStrings.INVALID);
							flagvar = false;
						}
						check = false;
					} catch (NumberFormatException e) {
						logger.info(ConsoleStrings.EXCEPTION_MSG);
						check = true;
					}

				} while (check);
			}
			obj.setPlayerRole(playerRole);
			al.add(obj);
		}
		return al;
	}

	public UserDetails getUserDetails() {
		UserDetails userobj = new UserDetails();
		while (flag == false) {
			logger.info(ConsoleStrings.FIRSTNAME);
			userobj.setFirstName(input.nextLine());
			flag = valid.isValidated(userobj.getfirstName(), RegexStrings.NAME_REGEX);
		}
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.LASTNAME);
			userobj.setLastName(input.nextLine());
			flag = valid.isValidated(userobj.getlastName(), RegexStrings.NAME_REGEX);
		}
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.PASSWORD);
			userobj.setPassword(input.nextLine());
			flag = valid.isValidated(userobj.getPassword(), RegexStrings.PASSWORD_REGEX);
		}
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.AGE);
			age = input.nextLine();
			flag = valid.isValidated(age, RegexStrings.AGE_REGEX);
		}
		userobj.setAge(Integer.parseInt(age));
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.MOBILE);
			userobj.setMobile(input.nextLine());
			flag = valid.isValidated(userobj.getMobile(), RegexStrings.MOBILE_REGEX);
		}
		flag = false;
		while (flag == false) {
			logger.info(ConsoleStrings.EMAIL);
			userobj.setEmail(input.nextLine());
			flag = valid.isValidated(userobj.getEmail(), RegexStrings.EMAIL_REGEX);
		}
		flag = false;
		userobj.setRole("user");
		return userobj;
	}

	public Tournament getTournamentDetails() {
		Tournament tourobj = new Tournament();
		logger.info(ConsoleStrings.TOURNAMENTNAME);
		tourobj.setTournamentName(input.nextLine());
		while (flagvar == false) {
			logger.info(ConsoleStrings.FORMAT);
			do {
				try {
					logger.info(ConsoleStrings.SHOW_OPTION);
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
					logger.info(ConsoleStrings.EXCEPTION_MSG);
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
		logger.info(ConsoleStrings.REGISTRATION_SUCCESS);
	}

	public void teamRegister() {
		logger.info(ConsoleStrings.TEAM_REGISTRATION_SUCCESS);
		logger.info(ConsoleStrings.TOURNAMENT_TEAMS);
	}

	public int displayTournament(ArrayList hm) {
		if (hm.size() == 0) {
			logger.info(ConsoleStrings.EMPTY_TOURNAMENT);
			return 0;
		}
		logger.info(ConsoleStrings.DASHED_LINES);
		for (int i = 0; i < hm.size(); i += 3) {
			logger.info(String.format("%20d%20s%20s\n", hm.get(i), hm.get(i + 1), hm.get(i + 2)));
		}
		logger.info(ConsoleStrings.DASHED_LINES);
		logger.info(ConsoleStrings.TOURNAMENTID);
		int tour_id = 0;
		do {
			try {
				tour_id = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return tour_id;
	}

	public void displayTeam(ArrayList<String> teamList) {
		logger.info(ConsoleStrings.DASHED_LINES);
		logger.info(ConsoleStrings.AVAILABLE_TEAMS);
		for (int i = 0; i < teamList.size(); i++) {
			logger.info(teamList.get(i));
		}
		logger.info(ConsoleStrings.DASHED_LINES);
	}

	public Schedule fixSchedule(int[] teamId, String match, int tournamentId) {
		Schedule scheduleobj = new Schedule();
		scheduleobj.setTeamOneId(teamId[0]);
		scheduleobj.setTeamtwoId(teamId[1]);
		scheduleobj.setMatches(match);
		scheduleobj.setTournamentId(tournamentId);
		return scheduleobj;
	}

	public void showMessage(int flag) {
		if (flag == 0) {
			logger.info(ConsoleStrings.DASHED_LINES);
			logger.info(ConsoleStrings.TEAMNAME_EXISTS);
			logger.info(ConsoleStrings.DASHED_LINES);
		}
	}

	public int getUserOption() {
		int option = 0;
		do {
			try {
				logger.info(ConsoleStrings.USER_OPTION);
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int getEditOption() {
		int option = 0;
		do {
			try {
				logger.info(ConsoleStrings.EDIT_OPTION);
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public String[] getTeamName() {
		logger.info(ConsoleStrings.OLD_TEAMNAME);
		team[0] = input.nextLine();
		logger.info(ConsoleStrings.NEW_TEAMNAME);
		team[1] = input.nextLine();
		return team;
	}

	public ArrayList<String> updatePlayerDetails(String team) {
		arrayList.clear();
		arrayList.add(team);
		logger.info(ConsoleStrings.NEW_PLAYERNAME);
		arrayList.add(input.nextLine());
		logger.info(ConsoleStrings.NEW_PLAYERROLE);
		do {
			try {
				logger.info(ConsoleStrings.GET_ROLE);
				int player_option = Integer.parseInt(input.nextLine());
				if (player_option == 1) {
					playerRole = ConsoleStrings.BATSMAN;
					flagvar = true;
				} else if (player_option == 2) {
					playerRole = ConsoleStrings.BOWLER;
					flagvar = true;
				} else if (player_option == 3) {
					playerRole = ConsoleStrings.ALL_ROUNDER;
					flagvar = true;
				} else if (player_option == 4) {
					playerRole = ConsoleStrings.WK_BATSMAN;
					flagvar = true;
				}
				check = false;
			} catch (Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		arrayList.add(playerRole);
		return arrayList;
	}

	public void displaySchedule(ArrayList schedule) {
		if (schedule.size() == 0) {
			logger.info(ConsoleStrings.NO_SCHEDULE);
			return;
		}
		logger.info(ConsoleStrings.DASHED_LINES);
		for (int i = 0; i < schedule.size(); i += 5) {
			if(i==0) {
				minMatch=(int) schedule.get(i);
			}
			if(i==schedule.size()-1) {
				maxMatch=(int) schedule.get(i);
			}
			logger.info(String.format("%20d%20d%20d%20s%20d\n", schedule.get(i), schedule.get(i + 1),
					schedule.get(i + 2), schedule.get(i + 3), schedule.get(i + 4)));
		}
		logger.info(ConsoleStrings.DASHED_LINES);
	}

	public char tournamentMenu() {
		do {
			try {
				logger.info(ConsoleStrings.MAIN_MENU);
				ch = input.nextLine().charAt(0);
				check = false;
			} catch (Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}

	public void displayInvalidTeamName(int status) {
		if (status == 0)
			logger.info(ConsoleStrings.INVALID_TEAMNAME);
		else
			logger.info(ConsoleStrings.TEAMNAME_UPDATED);
	}

	public char getAdminMenu() {
		do {
			try {
				logger.info(ConsoleStrings.ADMIN_MENU);
				ch = input.nextLine().charAt(0);
				check = false;
			} catch (Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}

	public void displayNoTeamsAvailable() {
		logger.info(ConsoleStrings.TEAM_AVAILABILITY);
	}

	public int getPlayerId(ArrayList al) {
		logger.info(ConsoleStrings.DASHED_LINES);
		for (int i = 0; i < al.size(); i += 2) {
			logger.info(String.format("%20d%20s\n", al.get(i), al.get(i + 1)));
			// System.out.printf("%20d%20s\n", al.get(i), al.get(i + 1));
		}
		logger.info(ConsoleStrings.DASHED_LINES);
		int playerId=0;
		do {
			try {
				logger.info(ConsoleStrings.PLAYERID);
				playerId = Integer.parseInt(input.nextLine());
			}
			catch(Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		}while(check);
		return playerId;
	}

	public String getTeam() {
		logger.info(ConsoleStrings.TEAMNAME);
		return input.nextLine();
	}

	public char option() {
		do {
			try {
				logger.info(ConsoleStrings.CONTINUE_MENU);
				ch = input.nextLine().charAt(0);
				check = false;
			} catch (Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}

	public void noPlayers() {
		logger.info(ConsoleStrings.NO_PLAYERS);
	}

	public void showScheduled() {
		logger.info(ConsoleStrings.SCHEDULE_SUCCESS);
	}

	public char getMainmenu() {
		do {
			try {
				logger.info(ConsoleStrings.ROOT_MENU);
				ch = input.nextLine().charAt(0);
				check = false;
			} catch (Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return ch;
	}

	public ArrayList<Scorecard> getScorecardDetails(int tournamentId) {
		Scorecard team1 = new Scorecard();
		Scorecard team2 = new Scorecard();
		int matchNo = 0, team1Id = 0, team2Id = 0;
		ArrayList<Scorecard> al = new ArrayList<Scorecard>();
		do {
			try {
				logger.info(ConsoleStrings.matchNo);
				matchNo = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		do {
			try {
				logger.info(ConsoleStrings.TEAM1ID);
				team1Id = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		do {
			try {
				logger.info(ConsoleStrings.TEAM2ID);
				team2Id = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		team1.setmatchNo(matchNo);
		team1.setTeamId(team1Id);
		team1.setTournamentId(tournamentId);
		team2.setmatchNo(matchNo);
		team2.setTeamId(team2Id);
		team2.setTournamentId(tournamentId);
		al.add(team1);
		al.add(team2);
		return al;
	}

	public Score getScore() {
		Score obj = new Score();
		int runs = 0, wickets = 0;
		float overs = 0f;
		do {
			logger.info(ConsoleStrings.RUNS);
			try {
				runs = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		do {
			logger.info(ConsoleStrings.OVERS);
			try {
				overs = Float.parseFloat(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		do {
			logger.info(ConsoleStrings.WICKETS);
			try {
				wickets = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		obj.setRuns(runs);
		obj.setOvers(overs);
		obj.setWickets(wickets);
		return obj;
	}

	public int[] matchInfo() {
		int info[] = new int[2];
		do {
			logger.info(ConsoleStrings.matchNo);
			try {
				info[0] = Integer.parseInt(input.nextLine());
				check = false;
				if(!(info[0]<minMatch || info[0]>maxMatch)) {
					System.out.println(minMatch);
					logger.info(ConsoleStrings.INVALID_MATCH_NO);
					matchInfo();
				}
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		do {
			logger.info(ConsoleStrings.TEAMID);
			try {
				info[1] = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return info;
	}

	public void displayUpdate() {
		logger.info(ConsoleStrings.UPDATE_MODULE);
	}

	public int getUpdateOption() {
		do {
			logger.info(ConsoleStrings.INSERT_UPDATE_OPTION);
			try {
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int getMatchNo() {
		do {
			logger.info(ConsoleStrings.matchNo);
			try {
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public void displayScorecard(ArrayList hm) {
		if (hm.size() == 0) {
			logger.info(ConsoleStrings.NOT_UPDATED_IN_SCORECARD);
		} else {
			logger.info(ConsoleStrings.DASHED_LINES);
			for (int i = 0; i < hm.size(); i += 6) {
				String str = String.format("%.1f", hm.get(i + 3));
				logger.info(String.format("%20d%20d%20d%20s%20d%20d\n", hm.get(i), hm.get(i + 1), hm.get(i + 2), str,
						hm.get(i + 4), hm.get(i + 5)));
			}
			logger.info(ConsoleStrings.DASHED_LINES);
		}
	}

	public void deleteInfo(int status) {
		if (status == 1) {
			logger.info(ConsoleStrings.SUCCESSFUL_DELETION);
		} else {
			logger.info(ConsoleStrings.DELETION_ERROR);
		}
	}

	public int showViewMenu() {
		do {
			try {
				logger.info(ConsoleStrings.VIEW_ADMIN);
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}

	public int getTournamentId() {
		int tournamentId = 0;
		do {
			try {
				logger.info(ConsoleStrings.TOURNAMENTID);
				tournamentId = Integer.parseInt(input.nextLine());
				check = false;
			} catch (Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return tournamentId;
	}
	public String adminLogin() {
		String pass="";
		do {
		try {
		logger.info(ConsoleStrings.PASSWORD);
		pass= input.nextLine();
		}
		catch(Exception e) {
			logger.info(ConsoleStrings.EXCEPTION_MSG);
		}
		}while(pass.length()<2);
		return pass;
	}
	
	public int getChoice() {
		int option=0;
		do {
			try {
				logger.info(ConsoleStrings.REQUEST_CHOICE);
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}
	
	public void showApprovalStatus(ArrayList approvalStatus) {
		logger.info(ConsoleStrings.DASHED_LINES);
		for (int i = 0; i < approvalStatus.size(); i += 3) {
			int status= (int)approvalStatus.get(i + 2);
			String approval="";
			if(status==0) {
				approval=ConsoleStrings.NOT_APPROVED;
			}
			else {
				approval=ConsoleStrings.APPROVED;
			}
			logger.info(String.format("%20d%20s%20s\n", approvalStatus.get(i), approvalStatus.get(i + 1), approval));
		}
		logger.info(ConsoleStrings.DASHED_LINES);
	}
	
	public int getRequestStatus() {
		int option=0;
		do {
			try {
				logger.info(ConsoleStrings.REQUEST_STATUS);
				option = Integer.parseInt(input.nextLine());
				check = false;
			} catch (Exception e) {
				logger.info(ConsoleStrings.EXCEPTION_MSG);
				check = true;
			}
		} while (check);
		return option;
	}
	
	public Team setTournamentId(int tournamentId) {
		Team team=new Team();
		team.setTournamentId(tournamentId);
		return team;
	}
}

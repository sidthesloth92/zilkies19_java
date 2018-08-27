package com.zilker.ui;

import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Scorecard;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.Score;
import com.zilker.beans.UserDetails;
import com.zilker.constant.ConsoleStrings;
import com.zilker.service.ServiceCrudOperations;
import com.zilker.delegates.Schedule;
import com.zilker.ui.FetchAndDisplay;
import com.zilker.service.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class DisplayMenu {

	int option, loginOption, userOption, adminOption, tournamentId, totalTeams, status, flagStatus, teamCount, playerId;
	boolean flag;
	int[] teamId = new int[2];
	Logger logger = Logger.getLogger(DisplayMenu.class.getName());
	char ch, flagstatus;
	String email, password, adminPassword, team;
	String teamName[] = new String[2];
	ArrayList<Player> al = new ArrayList<Player>();
	ArrayList<String> teamList = new ArrayList<String>();
	int teamid;
	ArrayList hm = new ArrayList();
	ArrayList<String> arrayList = new ArrayList<String>();
	Login loginObj;
	Team obj;
	FetchAndDisplay fetch = new FetchAndDisplay();
	ServiceCrudOperations service = new ServiceCrudOperations();
	Schedule scheduleObj = new Schedule();

	public DisplayMenu() {
		obj = null;
		loginObj = null;
		email = "";
		password = "";
		adminPassword = null;
		team = "";
		ch = ' ';
		flagstatus = ' ';
		flag = false;
		playerId = 0;
		option = 0;
		loginOption = 0;
		userOption = 0;
		adminOption = 0;
		teamid = 0;
		tournamentId = 0;
		totalTeams = 0;
		status = 0;
		flagStatus = 0;
		teamCount = 0;
	}

	public void Login() {
		loginObj = fetch.login();
		try {
			flag = service.getUserLoginInfo(loginObj);
		} catch (Exception e) {
			logger.info(ConsoleStrings.DB_ERROR);
		}
		if (flag == false) {
			fetch.displayInvalid();
			Login();
		}
		ch = ' ';
		while (ch == ' ') {
			hm.clear();
			try {
				hm = service.showTournament();
			} catch (Exception e) {
				logger.info(ConsoleStrings.DB_ERROR);
			}
			tournamentId = fetch.displayTournament(hm);
			if (tournamentId == 0) {
				continue;
			}
			do {
				option = fetch.getUserOption();
				Team teamobject = new Team();
				teamobject.setTournamentId(tournamentId);
				switch (option) {
				case 1:
					while (flagStatus == 0) {
						obj = fetch.teamDetails(tournamentId, loginObj.getEmail());
						try {
							flagStatus = service.checkTeamName(obj, teamobject);
						} catch (Exception e) {
							logger.info(ConsoleStrings.DB_ERROR);
						}
						fetch.showMessage(flagStatus);
					}
					try {
						teamid = service.addTeam(obj);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					al = fetch.getPlayerDetails(teamid);
					try {
						service.addPlayer(al);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					fetch.teamRegister();
					try {
						teamList = service.viewTeams(teamobject);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					fetch.displayTeam(teamList);
					teamList.clear();
					break;
				case 2:
					option = fetch.getEditOption();
					if (option == 1) {
						teamList.clear();
						try {
							teamList = service.viewTeams(teamobject, loginObj);
						} catch (Exception e) {
							logger.info(ConsoleStrings.DB_ERROR);
						}
						fetch.displayTeam(teamList);
						if (teamList.size() == 0) {
							fetch.displayNoTeamsAvailable();
						} else {
							teamName = fetch.getTeamName();
							try {
								teamCount = service.updateTeamName(teamName, teamobject);
							} catch (Exception e) {
								logger.info(ConsoleStrings.DB_ERROR);
							}
							if (teamCount == 0) {
								ch = 'y';
								fetch.displayInvalidTeamName(teamCount);
								continue;
							} else {
								fetch.displayInvalidTeamName(teamCount);
							}
						}
					} else if (option == 2) {
						teamList.clear();
						try {
							teamList = service.viewTeams(teamobject, loginObj);
						} catch (Exception e) {
							logger.info(ConsoleStrings.DB_ERROR);
						}
						fetch.displayTeam(teamList);
						if (teamList.size() == 0) {
							fetch.displayInvalid();
						} else {
							hm.clear();
							team = fetch.getTeam();
							teamobject.setTeamName(team);
							try {
								hm = service.showPlayer(teamobject);
							} catch (Exception e) {
								logger.info(ConsoleStrings.DB_ERROR);
							}
							if (hm.size() != 0) {
								do {
									playerId = fetch.getPlayerId(hm);
									arrayList = fetch.updatePlayerDetails(team);
									Player playerobject = new Player();
									playerobject.setPlayerId(playerId);
									try {
										service.updatePlayer(arrayList, playerobject);
									} catch (Exception e) {
										logger.info(ConsoleStrings.DB_ERROR);
									}
									ch = fetch.option();
								} while (ch == 'y' || ch == 'Y');
							} else {
								fetch.noPlayers();
							}
						}
					} else {
						fetch.displayInvalid();
					}
					break;
				case 3:
					try {
						hm = service.viewSchedule(teamobject);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					fetch.displaySchedule(hm);
					hm.clear();
					break;
				case 4:
					try {
						hm = service.viewSchedule(teamobject);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					fetch.displaySchedule(hm);
					int matchNo = 0;
					if (hm.size() != 0)
						matchNo = fetch.getMatchNo();
					hm.clear();
					Scorecard score = new Scorecard();
					score.setmatchNo(matchNo);
					try {
						hm = service.viewScorecard(score);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					fetch.displayScorecard(hm);
					break;
				}
				ch = fetch.tournamentMenu();
			} while (ch == 'y' || ch == 'Y');
			ch = 'f';
		}
	}

	public void adminLogin() throws IOException {
		String password = fetch.adminLogin();
		adminPassword = fetch.getAdminPassword();
		  if ((password.equals(adminPassword))) {
			  fetch.displayInvalid();
			  adminLogin();
		  }
		do {
			Team teamobject = new Team();
			adminOption = fetch.adminOption();
			if (adminOption == 1) {
				Tournament obj = fetch.getTournamentDetails();
				try {
					service.addTournament(obj);
				} catch (Exception e) {
					logger.info(ConsoleStrings.DB_ERROR);
				}
			} else if (adminOption == 2) {
				hm.clear();
				teamList.clear();
				try {
					hm = service.showTournament();
				} catch (Exception e) {
					logger.info(ConsoleStrings.DB_ERROR);
				}
				tournamentId = fetch.displayTournament(hm);
				/*
				 * if (tournamentId == 0) { return; }
				 */
				teamobject.setTournamentId(tournamentId);
				try {
					teamList = service.viewTeams(teamobject);
				} catch (Exception e) {
					logger.info(ConsoleStrings.DB_ERROR);
				}
				fetch.displayTeam(teamList);
				if (teamList.size() == 0) {
					fetch.displayNoTeamsAvailable();
				} else {
					try {
						status = service.deleteFixtures(teamobject);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					arrayList = scheduleObj.scheduleMatches(teamList);
					for (int i = 0; i < arrayList.size(); i++) {
						String array[] = arrayList.get(i).split("v");
						try {
							teamId = service.getTeamId(array);
						} catch (Exception e) {
							logger.info(ConsoleStrings.DB_ERROR);
						}
						com.zilker.beans.Schedule obj = fetch.fixSchedule(teamId, arrayList.get(i), tournamentId);
						if (status == 1) {
							try {
								service.fixtures(obj);
							} catch (Exception e) {
								logger.info(ConsoleStrings.DB_ERROR);
							}
						}
					}
					fetch.showScheduled();
				}
			} else if (adminOption == 3) {
				hm.clear();
				try {
					hm = service.showTournament();
				} catch (Exception e) {
					logger.info(ConsoleStrings.DB_ERROR);
				}
				tournamentId = fetch.displayTournament(hm);
				teamobject.setTournamentId(tournamentId);
				hm.clear();
				try {
					hm = service.viewSchedule(teamobject);
				} catch (Exception e) {
					logger.info(ConsoleStrings.DB_ERROR);
				}
				fetch.displaySchedule(hm);
				int choice = 0;
				if (hm.size() != 0) {
					fetch.displayUpdate();
					int info[] = new int[2];
					info = fetch.matchInfo();
					Score obj = fetch.getScore();
					try {
						service.updateScore(info, obj);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
				}
			} else if (adminOption == 4) {
				hm.clear();
				try {
					hm = service.showTournament();
				} catch (Exception e) {
					logger.info(ConsoleStrings.DB_ERROR);
				}
				tournamentId = fetch.displayTournament(hm);
				teamobject.setTournamentId(tournamentId);
				try {
					status = service.deleteTournament(teamobject);
				} catch (Exception e) {
					logger.info(ConsoleStrings.DB_ERROR);
				}
				fetch.deleteInfo(status);
			} else if (adminOption == 5) {
				int choice = fetch.showViewMenu();
				if (choice == 1) {
					hm.clear();
					try {
						hm = service.showTournament();
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					tournamentId = fetch.displayTournament(hm);
					teamobject.setTournamentId(tournamentId);
					try {
						hm = service.viewSchedule(teamobject);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					fetch.displaySchedule(hm);
				} else if (choice == 2) {
					hm.clear();
					try {
						hm = service.showTournament();
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					tournamentId = fetch.displayTournament(hm);
					teamobject.setTournamentId(tournamentId);
					try {
						hm = service.viewSchedule(teamobject);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					fetch.displaySchedule(hm);
					hm.clear();
					int match = fetch.getMatchNo();
					Scorecard score = new Scorecard();
					score.setmatchNo(match);
					try {
						hm = service.viewScorecard(score);
					} catch (Exception e) {
						logger.info(ConsoleStrings.DB_ERROR);
					}
					fetch.displayScorecard(hm);
				}
			}
			flagstatus = fetch.getAdminMenu();
		} while (flagstatus == 'y' || flagstatus == 'Y');
	}

	public void UserMenu() {
		loginOption = fetch.loginRegisterChoice();
		if (loginOption == 1) {
			Login();
		} else if (loginOption == 2) {
			UserDetails userObj = fetch.getUserDetails();
			try {
				service.insertUserDetails(userObj);
			} catch (Exception e) {
				logger.info(ConsoleStrings.DB_ERROR);
				UserMenu();
			}
			fetch.registerStatus();
			Login();
		} else {
			fetch.displayInvalid();
		}

	}

	public void AdminMenu() throws IOException {
			adminLogin();
	}

	public void showOption() throws IOException {
		do {
			option = fetch.loginOption();
			switch (option) {
			case 1:
				UserMenu();
				break;
			case 2:
				AdminMenu();
				break;
			default:
				fetch.displayInvalid();
				break;
			}
			ch = fetch.getMainmenu();
		} while (ch == 'y' || ch == 'Y');

	}
}

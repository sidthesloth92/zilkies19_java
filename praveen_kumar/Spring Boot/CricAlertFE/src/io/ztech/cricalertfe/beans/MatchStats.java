package io.ztech.cricalertfe.beans;

public class MatchStats {
	private int matchId, teamAscore, teamBscore, teamAwickets, teamBwickets, inning;
	private float overs;
	private Team battingTeam, bowlingTeam;
	private Player onstrike, offstrike, bowler;

	/*public MatchStats(int matchId, int battingTeam, int bowlingTeam, int teamAscore, int teamBscore, int teamAwickets,
			int teamBwickets, int onstrike, int offstrike, int bowler, int inning, float overs) {
		super();
		this.matchId = matchId;
		this.battingTeam = battingTeam;
		this.bowlingTeam = bowlingTeam;
		this.teamAscore = teamAscore;
		this.teamBscore = teamBscore;
		this.teamAwickets = teamAwickets;
		this.teamBwickets = teamBwickets;
		this.onstrike = onstrike;
		this.offstrike = offstrike;
		this.bowler = bowler;
		this.inning = inning;
		this.overs = overs;
	}*/

	public Team getBattingTeam() {
		return battingTeam;
	}

	public void setBattingTeam(Team battingTeam) {
		this.battingTeam = battingTeam;
	}

	public Team getBowlingTeam() {
		return bowlingTeam;
	}

	public void setBowlingTeam(Team bowlingTeam) {
		this.bowlingTeam = bowlingTeam;
	}

	public Player getOnstrike() {
		return onstrike;
	}

	public void setOnstrike(Player onstrike) {
		this.onstrike = onstrike;
	}

	public Player getOffstrike() {
		return offstrike;
	}

	public void setOffstrike(Player offstrike) {
		this.offstrike = offstrike;
	}

	public Player getBowler() {
		return bowler;
	}

	public void setBowler(Player bowler) {
		this.bowler = bowler;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getTeamAscore() {
		return teamAscore;
	}

	public void setTeamAscore(int teamAscore) {
		this.teamAscore = teamAscore;
	}

	public int getTeamBscore() {
		return teamBscore;
	}

	public void setTeamBscore(int teamBscore) {
		this.teamBscore = teamBscore;
	}

	public int getTeamAwickets() {
		return teamAwickets;
	}

	public void setTeamAwickets(int teamAwickets) {
		this.teamAwickets = teamAwickets;
	}

	public int getTeamBwickets() {
		return teamBwickets;
	}

	public void setTeamBwickets(int teamBwickets) {
		this.teamBwickets = teamBwickets;
	}

	public int getInning() {
		return inning;
	}

	public void setInning(int inning) {
		this.inning = inning;
	}

	public float getOvers() {
		return overs;
	}

	public void setOvers(float overs) {
		this.overs = overs;
	}
}

package io.ztech.cricalertfe.beans;

public class BallStats {
	private int matchId, overNo, bowlerId, onstrikeId, offstrikeId;
	private String summary;
	
	public BallStats(int matchId, int overNo, int bowlerId, int onstrikeId, int offstrikeId, String summary) {
		super();
		this.matchId = matchId;
		this.overNo = overNo;
		this.bowlerId = bowlerId;
		this.onstrikeId = onstrikeId;
		this.offstrikeId = offstrikeId;
		this.summary = summary;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getOverNo() {
		return overNo;
	}

	public void setOverNo(int overNo) {
		this.overNo = overNo;
	}

	public int getBowlerId() {
		return bowlerId;
	}

	public void setBowlerId(int bowlerId) {
		this.bowlerId = bowlerId;
	}

	public int getOnstrikeId() {
		return onstrikeId;
	}

	public void setOnstrikeId(int onstrikeId) {
		this.onstrikeId = onstrikeId;
	}

	public int getOffstrikeId() {
		return offstrikeId;
	}

	public void setOffstrikeId(int offstrikeId) {
		this.offstrikeId = offstrikeId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}

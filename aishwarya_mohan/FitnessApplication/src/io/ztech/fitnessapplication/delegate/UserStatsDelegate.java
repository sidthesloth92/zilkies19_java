package io.ztech.fitnessapplication.delegate;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserStats;
import io.ztech.fitnessapplication.dao.UserStatsDao;

public class UserStatsDelegate {
	public boolean addStats(UserStats newStats) {
		return new UserStatsDao().addStats(newStats);
	}

	public UserStats getStats(UserAccount account) {
		return new UserStatsDao().getStats(account);
	}

	public boolean updateStats(UserStats stats) {
		return new UserStatsDao().updateStats(stats);
	}

	public boolean setTarget(UserStats stats) {
		return new UserStatsDao().setTarget(stats);
	}

	public boolean trackWeight(UserStats stats) {
		return new UserStatsDao().trackWeight(stats);
	}

}

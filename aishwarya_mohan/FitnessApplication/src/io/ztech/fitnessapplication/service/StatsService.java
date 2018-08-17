package io.ztech.fitnessapplication.service;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserStats;
import io.ztech.fitnessapplication.delegate.UserStatsDelegate;
import io.ztech.fitnessapplication.ui.EditUI;

public class StatsService {
	public boolean addStats(UserStats newAccountStats) {
		return new UserStatsDelegate().addStats(newAccountStats);
	}

	public UserStats getStats(UserAccount account) {
		return new UserStatsDelegate().getStats(account);
	}

	public boolean updateStats(UserStats stats) {
		stats.setBmi(CalculateService.calculateBMI(stats));
		stats.setBmr(CalculateService.calculateBMR(stats));
		stats.setDailyTarget(new EditUI().askTarget(stats));
		return new UserStatsDelegate().updateStats(stats);
	}

	public boolean setTarget(UserStats stats) {
		return new UserStatsDelegate().setTarget(stats);
	}

	public boolean trackWeight(UserStats stats) {
		new UserStatsDelegate().trackWeight(stats);

		return new StatsService().updateStats(stats);
	}

}

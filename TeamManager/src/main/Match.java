package main;

import java.util.ArrayList;
import java.util.Date;


@SuppressWarnings("serial")
public class Match extends FileHandler{
	private String opponent;
	private Date date;
	ArrayList<Goal> goals = new ArrayList<Goal>();
		
	Match(Date date, String opponent){
		this.date = date;
		this.opponent = opponent;
	}
	
	public ArrayList<Goal> getGoals(){
		return goals;
	}
	
	public void setGoal(Goal goal) {
		goals.add(goal);
	}
	
	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getResult() {
		int score = 0;
		int opponentScore = 0;
		for (int i = 0; i < goals.size();i++) {
			if(goals.get(i).getPlayer().equals("Opponent")) {
				opponentScore = opponentScore + 1;
			}
			else {
				score = score + 1;
			}
		}
		if(score > opponentScore) {
			return "WIN";
		}
		else if(score == opponentScore) {
			return "TIE";
		}
		else {
			return"LOSS";
		}
	}
	
	public String getScoreline() {
		int score = 0;
		int opponentScore = 0;
		for (Goal goal : goals) {
			
			if(goal.getPlayer().equals("Opponent")) {
				opponentScore = opponentScore + 1;
			}
			else {
				score = score + 1;
			}
					
		}
		return score + " - " + opponentScore;
	}
	
}

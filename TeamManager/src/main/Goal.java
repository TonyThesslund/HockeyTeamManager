package main;

import java.util.Date;

@SuppressWarnings("serial")
public class Goal extends FileHandler{
	private Date date;
	private String player;
	private String assist;
	private int period;
	private int minute;
	private int second;
	
	Goal(Date date, String player, String assist,int period, int minute, int second) throws Exception{

		this.date = date;	
		this.player = player;
		this.assist = assist;
		this.period = period;
		this.minute = minute;
		this.second = second;
	}
	
	Goal(String player, String assist,int period, int minute, int second){
		this.player = player;
		this.assist = assist;
		this.period = period;
		this.minute = minute;
		this.second = second;
	}

	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public String getTime() {
		if (period == 1) {
			return period + "st Period, " + minute + "m" + second + "s";
		}
		else if (period == 2) {
			return period + "nd Period, " + minute + "m" + second + "s";
		}
		else {
			return period + "rd Period, " + minute + "m" + second + "s";
		}
	}
	
	public int getTimeInSeconds() {
		return (period * 20 * 60) + (minute * 60) + second;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAssist() {
		return assist;
	}

	public void setAssist(String assist) {
		this.assist = assist;
	}

}

package ElectricityMeter;

public class TimeStamp {

	public static volatile boolean perThreeHour1;
	public static volatile boolean perThreeHour2;
	public static volatile boolean perDay1;
	public static volatile boolean perDay2;
	public static volatile boolean perWeek;
	public static volatile boolean perMonth;
	
//	public boolean isPerThreeHour() {
//		return perThreeHour;
//	}
//	public void setPerThreeHour(boolean perThreeHour) {
//		this.perThreeHour = perThreeHour;
//	}
//	public boolean isPerDay() {
//		return perDay;
//	}
//	public void setPerDay(boolean perDay) {
//		this.perDay = perDay;
//	}
//	public boolean isPerWeek() {
//		return perWeek;
//	}
//	public void setPerWeek(boolean perWeek) {
//		this.perWeek = perWeek;
//	}
//	public boolean isPerMonth() {
//		return perMonth;
//	}
//	public void setPerMonth(boolean perMonth) {
//		this.perMonth = perMonth;
//	}
	
	public static void main(String[] args) {
		Time time = new Time();
		time.start();
	
		while(true) {
		//	System.out.println(perThreeHour);
			System.out.println(TimeStamp.perMonth);
//			if(perThreeHour == true) {
//				perThreeHour = false;
//			}
		}
	}
}

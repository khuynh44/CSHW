package PE03;

import java.util.Random;

public class DataLeakSimulator {
		
    public static void main(String[] args) {
  Random myRandom = new Random();
    int num = (int)Math.sqrt(myRandom.nextInt(26));
	    System.out.println(leakData(num) + " data was leaked");
		int num2 = (int)(Math.random()*((10 + 10) + 1 ) - 10);
	    System.out.println(leakData(num2) + " data was leaked");
	}
	public static SchoolDataType leakData(int a) { 
		a = Math.abs(a);
		a = a%4;
		switch(a) { 
			case 0:
				return SchoolDataType.STUDENTS;
			case 1:
				return SchoolDataType.CLASSES;
			case 2:
				return SchoolDataType.HOUSING;
			default:
				return SchoolDataType.DINING;
			}
		}

}

package arduinoTest;

import rxtxrobot.RXTXRobot;

public class MainMethod {
	public static void main(String[] args) {
		 Robot r = new Robot();
		 //Get the average thermistor reading
		 int thermistorReading = r.getThermistorReading(); // Create RXTXRobot object 
	
		 System.out.println("The probe read the value: " + thermistorReading);
		 
		 System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
		 r.motor();
		 //r.getPing();
		 //System.out.println();
		 r.close();

	}
	
	

}

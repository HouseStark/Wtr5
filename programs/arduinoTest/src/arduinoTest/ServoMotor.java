package arduinoTest;

//import java.util.Scanner;

import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;

public class ServoMotor {
	public static void main(String[] args) 
	{ 
		//Scanner user_input = new Scanner( System.in );
		
		//int number;
		//number = user_input.nextInt();
		
		RXTXRobot r = new ArduinoUno(); // Create RXTXRobot object 
		r.setPort("COM6"); // Set the port to COM3 
		r.connect(); 
		
		r.attachServo(RXTXRobot.SERVO1, 9);//attach to pin 9
		r.moveServo(RXTXRobot.SERVO1, 90);
		r.sleep(1000);
		//r.moveServo(RXTXRobot.SERVO1, 180);
		
		r.close(); 
	} 

}

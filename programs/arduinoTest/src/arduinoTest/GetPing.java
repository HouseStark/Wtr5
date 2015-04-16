package arduinoTest;

import rxtxrobot.*;
import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;
//Ping sensor works!
//create a second ping + equation for equal wall
public class GetPing 
{ 
	final static int PING_PIN = 10; 
	final static int PING_PIN2 = 8;
	public static void main(String[] args) 
	{ 
		RXTXRobot r = new ArduinoUno(); // Create RXTXRobot object 
		r.setPort("COM6"); // Set the port to COM3 
		r.connect(); 
		for (int x=0; x < 100; ++x) 
		{ 
			//Read the ping sensor value, which is connected to pin 12 
			System.out.println("Response from right: " + r.getPing(PING_PIN) + " cm"); 
			System.out.println("Response from left: " + r.getPing(PING_PIN2) + " cm"); 
			r.sleep(1000); 
			//turn
			if (r.getPing(PING_PIN2) < r.getPing(PING_PIN))
			{
				r.runEncodedMotor(RXTXRobot.MOTOR1, 10, 10);
				if (r.getPing(PING_PIN2) == r.getPing(PING_PIN))
				{
					System.out.println("It works!");
					r.runEncodedMotor(r.MOTOR1, 250, 200, r.MOTOR2, 250, 200);
				}
			}
			//turn
			if (r.getPing(PING_PIN2) > r.getPing(PING_PIN))
			{
				r.runEncodedMotor(RXTXRobot.MOTOR2, 10, 10);
				if (r.getPing(PING_PIN2) == r.getPing(PING_PIN))
				{
					System.out.println("It works!");
					r.runEncodedMotor(r.MOTOR1, 250, 200, r.MOTOR2, 250, 200);
				}
			}
			//go forward
			if (r.getPing(PING_PIN2) == r.getPing(PING_PIN))
			{
				System.out.println("It works!");
				r.runEncodedMotor(r.MOTOR1, 250, 200, r.MOTOR2, 250, 200);
			}
		} 
		r.close(); 
	} 
} 
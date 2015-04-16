package arduinoTest;

import rxtxrobot.*;

public class Robot {
	static RXTXRobot r;
	
	Robot(){
		r = new ArduinoUno(); // Create RXTXRobot object 
		r.setPort("COM6"); // Set the port to COM3 
		r.setVerbose(true); // Turn on debugging messages 
		r.connect(); 
	}
	public static void main(String[] args) 
	{
		Robot y = new Robot();
		//r.setVerbose(true); // Turn on debugging messages 
		
		//****************************************************************
		///////////////////order of operations for robot//////////////////
		//****************************************************************
		//step 1: robot will move to water dish
		y.motor();
		//step 2: robot will stop at dish, lower arm, and test water
		//this while loop will deactivate and allow robot to test water once button is pushed
		while (y.bumpSensor() != 1)
		{
		}
		//y.inchMotor();
		y.stopMotor();
		y.servoFishRod();
		
		//step 3: back up
		y.inchMotor();
		
		//step 4: turn 90 so ping sensors can adjust
		r.runEncodedMotor(RXTXRobot.MOTOR2, 255, 250);
		
		//step 5: robot adjust straight so go straight to ping pong balls
		y.getPingSensors();
		
		//Last Step: Turn off robot
		y.close();
	}
	
	public int getThermistorReading() {
		 int sum = 0;
		 int readingCount = 10;

		 //Read the analog pin values ten times, adding to sum each time
		 for (int i = 0; i < readingCount; i++) {

		 //Refresh the analog pins so we get new readings
		 r.refreshAnalogPins();
		 int reading = r.getAnalogPin(0).getValue();
		 sum += reading;
		 }

		 //Return the average reading
		 return sum / readingCount;
	}//End of getThermistorReading
	//Motors
	public void motor() //12 volts + 4Amp
	{ 
		//r.attachMotor(r.MOTOR4, 8);
		r.runMotor(RXTXRobot.MOTOR1, 250, RXTXRobot.MOTOR2, -250, 0);
	}
	
	public void inchMotor()
	{
		//back up a little
		//r.runEncodedMotor(RXTXRobot.MOTOR1, -255, 10);
		//r.runEncodedMotor(RXTXRobot.MOTOR2, 255, 10);
		r.runEncodedMotor(RXTXRobot.MOTOR1, -255, 500, RXTXRobot.MOTOR2, 255, 500);
	}
	
	public void stopMotor()
	{
		r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
	}
	
	public void servoFishRod()
	{
		//Lower fishing Rod 
		r.attachServo(RXTXRobot.SERVO1, 9);
		r.moveServo(RXTXRobot.SERVO1, 0);
		
		//Find how many balls to pick up based on water Us/cm
		//Test water conductivity
		r.getConductivity();
		double conductivityTotal = 0;
		double n = r.getConductivity();
		//y=-0.1351x + 908.22
		conductivityTotal = (-0.1351 * n) + 908.22;
		System.out.println("Conductivity and pick up: " + conductivityTotal + " ping pong balls");
		
		
		//Test water turbidity
		r.refreshAnalogPins(); // Cache the Analog pin information 
		
		AnalogPin temp = r.getAnalogPin(1); 
		
		for (int x=0; x < 2; ++x) 
		{ 
			
			System.out.println("Turbidity Sensor " + x + " has value: " + temp.getValue()); 
			//need to average values and return number
		} 
		//Finds how many balls to pick up based on turbidity NTU
		//y = -0.9082x + 634.27
		double turbidityTotal = 0;
		double turbidityValue =  temp.getValue();
		turbidityTotal = (-0.9082 * turbidityValue) + 634.27;
		turbidityTotal = (turbidityTotal * 3) / 5;
		System.out.println("Turbidity value and pick up: " + turbidityTotal + " Ping pong balls");
		
		
		//test water temperature
		int thermistorReading = getThermistorReading(); // Create RXTXRobot object 
		 	System.out.println("The probe read the value: " + thermistorReading);
		 	System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
		 	
		//equation to return how many balls to pick up
		
		//wait several seconds
		r.sleep(3000);
		//raise fishing Rod
		r.moveServo(RXTXRobot.SERVO1, 180);
		System.out.println("Conductivity and pick up a total value of: " + conductivityTotal + " for ping pong balls");
		System.out.println("Turbidity and pick up a total value of: " + turbidityTotal + "  for ping pong balls");
		
		
	}
	//Ping sensor 
	public void getPing()
	{
		int PING_PIN = 10; 
		for (int x=0; x < 100; ++x) 
		{ 
			//Read the ping sensor value, which is connected to pin 12 
			System.out.println("Response: " + r.getPing(PING_PIN) + " cm"); 
			r.sleep(300); 
		} 
	}
	//Bump sensor
	public int bumpSensor()
	{
		while(r.getDigitalPin(11).getValue() != 1)
		{
			r.refreshDigitalPins();
			System.out.println(r.getDigitalPin(11).getValue());
			//else if (r.getDigitalPin(13).getValue() == 1)
				//System.out.println("touch sensor");
		}
		return r.getDigitalPin(11).getValue();
	}
	//Ping sensors one and 2 logic
	public void getPingSensors()
	{
		boolean input = true;// maybe not working?
		int PING_PIN = 10; 
		int PING_PIN2 = 8;
		//while loop will run until input == true
		while(input == false)
		{
			//Read the ping sensor value, one and two
			System.out.println("Response from right: " + r.getPing(PING_PIN) + " cm");
			System.out.println("Response from left: " + r.getPing(PING_PIN2) + " cm");
			
			//if distance equal, move forward
			if(r.getPing(PING_PIN) == r.getPing(PING_PIN2))
			{
				r.runEncodedMotor(RXTXRobot.MOTOR1, 255, 500, RXTXRobot.MOTOR2, 255, -500);
				//conditional statement to stop robot based on certain distance
				if(r.getPing(PING_PIN) <= 10 && r.getPing(PING_PIN2) <= 10)
				{
					//motor stop and activate moving arm
					r.close();
				}
			}
			//if right ping shorter than left ping, sleep right tread for a bit.
			if(r.getPing(PING_PIN) <= r.getPing(PING_PIN2))// make sure to include minimum distance
			{
				r.runEncodedMotor(RXTXRobot.MOTOR1, 255, 500, RXTXRobot.MOTOR2, 255, -500);
				//conditional statement to stop robot based on certain distance
				if(r.getPing(PING_PIN) <= 10 && r.getPing(PING_PIN2) <= 10)
				{
					//motor stop and activate moving arm
					r.close();
				}
			}
			//if left ping shorter than right ping, sleep left tread for a bit
			if(r.getPing(PING_PIN) == r.getPing(PING_PIN2))
			{
				r.runEncodedMotor(RXTXRobot.MOTOR1, 255, 500, RXTXRobot.MOTOR2, 255, -500);
				//conditional statement to stop robot based on certain distance
				if(r.getPing(PING_PIN) <= 10 && r.getPing(PING_PIN2) <= 10)
				{
					//motor stop and activate moving arm
					r.close();
				}
			}
			r.sleep(300); 
		} 
	}
	
	public void waterTest()
	{
		//function will test water 
	}
	public void spinBallGrabber()
	{
		//grabs balls 
	}
	
	//Close arduino port
	public void close(){
		 r.close();
	}
}//End of robot class


//r.runMotor(RXTXRobot.MOTOR1, 125, 3000); // Run motor 1 forward (speed of 125) for 5 seconds 
//// Program stops until the command above is completed (5 seconds) 
//r.runMotor(RXTXRobot.MOTOR1, -125, 300); // Run motor 1 backward (speed of 125) for 3 seconds 
//r.close(); 
 



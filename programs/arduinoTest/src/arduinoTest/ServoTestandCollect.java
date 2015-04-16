package arduinoTest;

import rxtxrobot.AnalogPin;
import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;

public class ServoTestandCollect {
	public static void main(String[] args) 
	{ 
		//Scanner user_input = new Scanner( System.in );
		
		//int number;
		//number = user_input.nextInt();
		
		RXTXRobot r = new ArduinoUno(); // Create RXTXRobot object 
		r.setPort("COM6"); // Set the port to COM3 
		r.connect(); 
		r.setVerbose(true);
		boolean collect = false;
		r.attachServo(RXTXRobot.SERVO2, 7);//attach to pin 7
		r.sleep(3000);
		for (int i = 0; i < 100; i++){
			r.moveServo(RXTXRobot.SERVO2, 80);
			r.sleep(10);
				
		}
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
		/*r.attachServo(RXTXRobot.SERVO1, 9);
		r.moveServo(RXTXRobot.SERVO1, 0);
		
		//Test water conductivity
		r.getConductivity();
		double conductivityTotal = 0;
		double n = r.getConductivity();
		//y=-42.185x + 99.725
		conductivityTotal = (-42.185 * n) + 99.725;
		System.out.println("Conductivity and pick up: " + conductivityTotal + " ping pong balls");
		/*
		for (int x=0; x < 100; ++x) 
		{ 
			//Read the ping sensor value, which is connected to pin 12 
			System.out.println("Conductivity: " + r.getConductivity()); 
			r.sleep(50); 
			//need to average values and return number
		}
		*/ 
		
		//Test water turbidity
		r.refreshAnalogPins(); // Cache the Analog pin information 
		
		AnalogPin temp = r.getAnalogPin(1); 
		
		for (int x=0; x < 2; ++x) 
		{ 
			
			System.out.println("Turbidity Sensor " + x + " has value: " + temp.getValue()); 
			//need to average values and return number
		} 
		//y = -0.8096x + 533.07
		int ball1 = 50;
		int ball2 = 250;
		double turbidityTotal = 0;
		double turbidityValue =  temp.getValue();
		turbidityTotal = (-0.8096 * turbidityValue) + 533.07;
		turbidityTotal = (turbidityTotal * 3) / 5;
		System.out.println("Turbidity value and pick up: " + turbidityTotal + " Ping pong balls");
		
		//if(turbidityTotal > )
//////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
		//turbidity total, each ball 
		//salinity total, 
		
	
		System.out.println("i am here");
		r.sleep(1000);
		//r.moveServo(RXTXRobot.SERVO1, 180);
		//r.moveServo(RXTXRobot.SERVO2, 90);*/
		r.close();
	} 
}

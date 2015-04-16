package arduinoTest;

import rxtxrobot.*;
//conductivity works!
public class getConductivity {
	public static void main(String[] args) 
	{ 
		RXTXRobot r = new ArduinoUno(); // Create RXTXRobot object 
		r.setPort("COM6"); // Set the port to COM3 
		r.connect(); 
		r.getConductivity();
		for (int x=0; x < 100; ++x) 
		{ 
			//Read the ping sensor value, which is connected to pin 12 
			System.out.println("Conductivity: " + r.getConductivity()); 
			r.sleep(300); 
		} 
		//y = 176.15 (e)^-1.397x)
		//r.getConductivity();
		double conductivityTotal = 0;
		double n = r.getConductivity();
		//y=-0.1351x + 908.22
		conductivityTotal = (-0.1351 * n) + 908.22;
		//System.out.println("Conductivity and pick up: " + conductivityTotal + " ping pong balls");
		//System.out.println("Conductivity and pick up: " + conductivityTotal + " ping pong balls");
		r.close(); 
		//difference in voltage 
	} 
}

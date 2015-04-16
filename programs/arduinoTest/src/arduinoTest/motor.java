package arduinoTest;
import rxtxrobot.*;
import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;
//motors work!
public class motor {
	public static void main(String[] args){
		RXTXRobot r = new ArduinoUno();
		r.setPort("COM6");//usb port
		r.connect();//connect
		//run motor
		//r.runEncodedMotor(r.MOTOR1, 250, 1000, r.MOTOR2, 270, 1000);//Motor 2 -- D6
		//r.runEncodedMotor(RXTXRobot.MOTOR1, 255, 100);
		//r.runEncodedMotor(RXTXRobot.MOTOR2, 255, 100);
		//Encoder for motor 1: D2, Encoder Motor 2: D3
		//Motor 1 -- D
		
		//debugger
		//motor 2 is right side looking at tray
		//motor 1 is left side
		//r.attachMotor(arg0, arg1);
		r.attachMotor(RXTXRobot.MOTOR1, 5);
		r.runMotor(RXTXRobot.MOTOR1, 500, RXTXRobot.MOTOR2, -500, 0);
		
		
		/*for(int i = 0; i < 100; ++i)
		{
			r.refreshAnalogPins();
			//System.out.println(r.getAnalogPin(1).getValue());
			if (r.getAnalogPin(1).getValue() == 0)
			{
				System.out.println("touch sensor!");
				r.close();
			}
		}*/
		r.close();
		
	} 
	
}

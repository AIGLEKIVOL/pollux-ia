package polluxPak;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import java.util.Arrays;

public class Agents {
	private Capteurs capteurs;
	private Actionneurs moteurs;

	public Agents() {
		// TODO Auto-generated constructor stub
	}
	public Agents(Port A, Port B, Port D, Port s1, Port s3,Port s4) throws IOException {
		capteurs= new Capteurs (s1,s3,s4);
		moteurs= new Actionneurs (A,B,D);
	}
	public boolean detectionPallet() {
		moteurs.avance();
		if(capteurs.getDistance()<0.4) {
			double dist=capteurs.getDistance();
			Delay.msDelay(300);
			if(capteurs.getDistance()>dist)
			{
				moteurs.actionPince();
				return true;
			}else {
				while(capteurs.getDistance()<0.2) {
					moteurs.tourner(true,1);
			}
				return false;

			}

	
		}
		return false;
	}
	public boolean tournerLigne(String c, int i) {
		if(capteurs.getColor().equals(c)) {
			return false;
		}else {
			if(i%2==1) {
				moteurs.tourner(true,1);
			}else {
				moteurs.tourner(false,1);
			}
			return true;
		}
		
	}
	public void majColor() {
		
	}
	public void action() {
		LCD.clearDisplay();
		int i=1;
		while(!Button.ESCAPE.isDown()) {
			String colorT=capteurs.getColor();
			LCD.clear(4);
			LCD.clear(5);
			LCD.drawString("Distance : "+capteurs.getDistance(), 0,4);
			LCD.drawString("couleur : "+colorT, 0,5);
			
			capteurs.maj();
			moteurs.avance();
			Delay.msDelay(100);
			if(tournerLigne(colorT,i)) {
				i++;
			}
			if(detectionPallet()) {
				if(!(moteurs.getAngle()==0)) {
					while(moteurs.getAngle()<0) {
						moteurs.tourner(true,1);
					}
					while(moteurs.getAngle()>0) {
						moteurs.tourner(false,1);
					}

				}
				while(!capteurs.getColor().equals("white")) {
					moteurs.avance();
				}
				moteurs.stop();
				moteurs.lacherPallet();
			}
			
			}
		
		capteurs.close();
		moteurs.stop();
		moteurs.l1.close();
		moteurs.r1.close();
		
	}
	public static void main (String[]args) throws IOException {
		Agents robot= new Agents (MotorPort.A,MotorPort.B,MotorPort.D,SensorPort.S1,SensorPort.S3,SensorPort.S4);
		robot.action();
	}

}


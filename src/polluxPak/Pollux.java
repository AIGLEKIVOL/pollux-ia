package polluxPak;
import java.io.IOException;
import java. util.Scanner;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.EncoderMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.robotics.filter.PublishFilter;
import lejos.hardware.Device;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Pollux{

	public Pollux() {
		// TODO Auto-generated constructor stub
	}
	public static void main (String[]args) throws IOException {
		// Creation d'instance des classe UltrasonicSensor et TouchSensor
		EV3UltrasonicSensor uss = new EV3UltrasonicSensor(SensorPort.S1);
		EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S3);
		
		
		
		//Creation d'instance de la classe RegulatedMotor pour controller
		//les 2 roues et la pince
		RegulatedMotor l1 = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor r1= new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor[]sr1= new RegulatedMotor[1];
		l1.synchronizeWith(sr1);
		RegulatedMotor pince= new EV3LargeRegulatedMotor(MotorPort.D);
		
		
		
		float frequency=1;
		// definition de sample pour traiter l'info sensoriel recu des capteurs sensoriel
		SampleProvider sp2= new PublishFilter(uss.getDistanceMode(),"Ultrasonic readings", frequency);
		float [] sample2 = new float [sp2.sampleSize()];
		SampleProvider sp3= new PublishFilter(touch.getTouchMode(),"Touch readings", frequency);
		float [] sample3 = new float [sp3.sampleSize()];
		
		
		///Boucle d'action
		int pallet=0;
		//La condition d'arret est que l'on presse la touche "echap"
		while(!Button.ESCAPE.isDown()) {
			
			//Affichage de la distance et de la possession du pallet ou non
			LCD.clear(4);
			LCD.drawString("Distance : "+sample2[0], 0,4);
			LCD.drawString("Pallet : "+pallet, 0,5);
			
			// Condition temporaire permettant d'ouvrir la pince
			if(Button.ENTER.isDown()) {
				pince.rotate(720);
				pallet=0;
			}
			
			// recuperation des infos sensoriels
			sp2.fetchSample(sample2,0);
			sp3.fetchSample(sample3,0);
			
			//activation des roues pour avancer
			l1.startSynchronization();
			l1.forward();
			r1.forward();
			Delay.msDelay((long) (200/frequency));
			
			
			// condition permetant d'activer la pince si un pallet touche le recepteur
			if (sample3[0]==1) {
				
				l1.stop();
				r1.stop();
				pince.rotate(-360);
				l1.forward();
				r1.forward();
				pallet=1;
				
			}
			
			// condition qui permet d'utiliser le capteur ultrason pour eviter les obstacle en faisant un quart de tour
			if(sample2[0]<0.5) {
				Sound.playTone(2000 ,100);
				l1.endSynchronization();
				l1.stop();
				r1.stop();
				l1.rotate(360);
				l1.startSynchronization();
			}
		}
		l1.close();
		r1.close();
		uss.close();
		touch.close();
		pince.close();
		
		
		
	}

}
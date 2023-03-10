package multi_;

import java.text.*;

public class Multifor02 {

	public static void main(String[] args) {
		int dan;
		int i;
		
		DecimalFormat df = new DecimalFormat("00");
		
		for(dan=2; dan<10; dan++) {
			for(i=1; i<10; i++) {
				System.out.print(String.format("%02d", dan) + "*" +  df.format(i) + "=" + df.format(dan*i) + "\t");
				// String format: String.format or DecimalFormat
			} // for i
			System.out.println();
		} // for dan

		
//		for(dan=2; dan<10; dan++); // for(;;); for문 종료 { 
//			for(i=1; i<10; i++) {
//				System.out.println(String.format("%2d", dan) + "*" +  df.format(i) + "=" + df.format(dan*i) + "\t");
//			} // for i
//			System.out.println();
//		} // useless
		
	}
}


/*
[문제] 2~9단
02*01=02	02*02=04	02*03=06	02*04=08	02*05=10	02*06=12	02*07=14	02*08=16	02*09=18	
03*01=03	03*02=06	03*03=09	03*04=12	03*05=15	03*06=18	03*07=21	03*08=24	03*09=27	
04*01=04	04*02=08	04*03=12	04*04=16	04*05=20	04*06=24	04*07=28	04*08=32	04*09=36	
05*01=05	05*02=10	05*03=15	05*04=20	05*05=25	05*06=30	05*07=35	05*08=40	05*09=45	
06*01=06	06*02=12	06*03=18	06*04=24	06*05=30	06*06=36	06*07=42	06*08=48	06*09=54	
07*01=07	07*02=14	07*03=21	07*04=28	07*05=35	07*06=42	07*07=49	07*08=56	07*09=63	
08*01=08	08*02=16	08*03=24	08*04=32	08*05=40	08*06=48	08*07=56	08*08=64	08*09=72	
09*01=09	09*02=18	09*03=27	09*04=36	09*05=45	09*06=54	09*07=63	09*08=72	09*09=81

 */

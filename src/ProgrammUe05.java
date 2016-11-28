public class ProgrammUe05 {
	
	/*	In diesem Programm soll die Flaeche und der Umfang des 
		kleinsten umfassenden Kreises berechnet werden.
	*/
	public static void main(String[] args) {
		
		/* 	Zwei Kreise k1, k2 seien definiert durch 
			einen Mittelpunkt m = (x,y) und einen Radius r
		*/
		
		double x1 = -20.0;
		double y1 = 10.0;
		double r1 = 15.0;
		
		double x2 = 5.0;
		double y2 = 0.0;
		double r2 = 5.0;
		

		
		/* Die Entfernung beider Mittelpunkte
		*/
		double distanz = Kreis.berechneDistanz(x1,y1,x2,y2);
		System.out.println("Die Distanz zwischen den Mittelpunkten betraegt " + distanz);
		
		/* 	Der kleinste Kreis, der beide Kreise enthält, sei k3 mit
			m3 = (x3,y3) und Radius r3.
		*/
		
		double x3 = Kreis.berechneX(x1,y1,r1,x2,y2,r2);
		double y3 = Kreis.berechneY(x1,y1,r1,x2,y2,r2);
		double r3 = Kreis.berechneR(x1,y1,r1,x2,y2,r2);
		
		
		/*	Sonderfall: Ein Kreis kann im anderen Kreis enthalten sein. Dann funktioniert unsere
			Herangehensweise über die Pole nicht! Finde dann den größeren Kreis.
		*/
		/* 	Fall 1: k1 in k2 */
		if(distanz < r2) {
			x3 = x2;
			y3 = y2;
			r3 = r2;
		}
		/* 	Fall 2: k2 in k1 */
		else if(distanz < r1) {
			x3 = x1;
			y3 = y1;
			r3 = r1;
		}
		
		System.out.println("k3 ist definiert durch:");
		System.out.println("x = " + x3);
		System.out.println("y = " + y3);
		System.out.println("r = " + r3);
		
		/* 	Flaeche und Umfang von k3
		*/
		System.out.println("Pi = " + pi());
		
		System.out.println("k3 hat eine Flaeche von " + pi()*r3*r3);
		System.out.println("k3 hat einen Umfang von " + 2.0*pi()*r3);
	}
	
	/**
	  * Approximation des Wertes von Pi mit Hilfe der analytischen Darstellung von Vieta.
	  * Die Rekursionstiefe ist hier immer 15, sodass die Methode 0-stellig ist.
	  * @return  der approximierte Wert von Pi
	  */
	public static double pi() {	
		return Kreis.pi(15);
	}
}

public class Kreis {
	
	/** 
	 * Java Class mit Methoden im Zusammenhang mit der Berechnung 
	 * der Bounding Sphere zweier Kreise
	 * 
	 * @author  Zin Yosrim
	 * @version 1.0, 22 Nov 2016
	 */

	public static double quadratwurzel (double x, int n){
		/** 
		 * rekursive Berechnung der Quadratwurzel nach dem Heronverfahren
		 * 
		 * @param x		Die Zahl, deren Quadratwurzel berechnet werden soll
		 * @param n		Der Faktor, der die Genauigkeit steuert
		 * 
		 * @return		Die Quadratwurzel von x
		 * 
		 */
		if (n == 0) {
			return (x+1)/2;
		}
		else {
			return 0.5*(quadratwurzel(x,n-1)+x/quadratwurzel(x,n-1));
		}
		
	}
	public static double vietaFaktor(int n){
		/** 
		 * rekursive Berechnung des n-ten Vieta Faktors
		 * Wird benötigt für die Berechnung von pi(n)
		 * 
		 * @param n		Legt fest, der wievielte Vietafaktor berechnet wird
		 * 
		 * @return		Der n-te Vietafaktor
		 * 
		 */
		
		int genauigkeitQuadratWurzel = 15; /** liefert auf 15 Stellen nach dem Komma das gleiche Ergebnis wie Math.sqrt(2)*/
		if (n == 0){
			return quadratwurzel(2,genauigkeitQuadratWurzel)/2;
		}
		else {
			return (quadratwurzel(2+2*vietaFaktor(n-1),genauigkeitQuadratWurzel))/2;
		}
	
	}
	
	public static double pi(int n){
		/** 
		 * rekursive Berechnung der Annäherung an pi. Liefert bei pi(10) 3,14159
		 * 2/pi entspricht dem Produkt der Vietafaktoren 0..n
		 * 
		 * @param n		Legt anhand der Anzahl der Vietafaktoren fest, wie genau die Berechnung erfolgen soll
		 * 
		 * @return		Annäherung an pi
		 * 
		 */
		if (n == 0){
			return 2/vietaFaktor(0);
		}
		else {
			return pi(n-1)/vietaFaktor(n);
		}
	}
	
	public static double berechneDistanz(double x1, double y1, double x2, double y2){
		/** 
		 * Berechnet die Distanz zweier Punkte im zweidimensionalen Koordinatensystem
		 * 
		 * @param 	x1	x koordinate des ersten Punktes
		 * @param 	y1	y koordinate des ersten Punktes
		 * @param 	x2	x koordinate des zweiten Punktes
		 * @param 	y2	y koordinate des zweiten Punktes	
		 * 
		 * @return		Distanz zwischen zwei Punkten
		 * 	 
		 */
		int n = 15; // Für die Genauigkeit der Quadratwurzelfunktion
		return quadratwurzel((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2), n);
	}
	
	public static double berechnePol(double x1, double y1, double r1, double x2, double y2, double r2){
		/** 
		 * Berechnet die äusseren Pole zweier Kreise, die von der Bounding Sphere umschlossen wird 
		 * Verwendet die Strahlensätze zu Berechnung der Pole. 
		 * 
		 * @param 	x1	x Koordinate des Mittelpunktes des ersten Kreises
		 * @param 	y1	y Koordinate des Mittelpunktes des ersten Kreises
		 * @param 	r1	radius des ersten Kreises
		 * @param 	x2	x Koordinate des Mittelpunktes des zweiten Kreises
		 * @param 	y2	y Koordinate des Mittelpunktes des zweiten Kreises	 
		 * @param 	r2	radius des zweiten Kreises
		 * 
		 * @return		die x koordinate eines Pols 
		 * 				für die weiteren Koordinaten müssen die Eingabeparameter vertauscht werden
		 */
		double distanzZwischenMittelpunkten = berechneDistanz(x1, y1, x2, y2);
		if (distanzZwischenMittelpunkten> r1+r2){ 
			/** 
			 * kein kreis schliesst den anderen ein
			 * 
			 */
			return x1-r1*(x2-x1)/distanzZwischenMittelpunkten;
		}
		else if (r1>=r2){ 
			/** 
			 * Ein Kreis schliesst den anderen ein:
			 * Suche nach dem größeren Kreis: k2 ist teilmenge von k1
			 * 
			 */
			return x1-r1*(x2-x1)/distanzZwischenMittelpunkten;
		}
		else {
			/** 
			 * k1 ist teilmenge von k2
			 * 
			 */
			return x2-r2*(x2-x1)/distanzZwischenMittelpunkten;
		}
	}
	
	
	public static double berechneX(double x1, double y1, double r1, double x2, double y2, double r2){
		/** 
		 * Berechnet die X Koordinate des Mittelpunktes der Bounding Sphere
		 * 
		 * @param 	x1	x Koordinate des Mittelpunktes des ersten Kreises
		 * @param 	y1	y Koordinate des Mittelpunktes des ersten Kreises
		 * @param 	r1	radius des ersten Kreises
		 * @param 	x2	x Koordinate des Mittelpunktes des zweiten Kreises
		 * @param 	y2	y Koordinate des Mittelpunktes des zweiten Kreises	 
		 * @param 	r2	radius des zweiten Kreises
		 * 
		 * @return		Die x Koordinate des Mittelpunktes der Bounding Sphere
		 * 
		 */
		return berechnePol(x1, y1, r1, x2, y2, r2)+(berechnePol(x2, y2, r2, x1, y1, r1)-berechnePol(x1, y1, r1, x2, y2, r2))/2;
	}
	
	public static double berechneY(double x1, double y1, double r1, double x2, double y2, double r2){
		/** 
		 * Berechnet die Y Koordinate des Mittelpunktes der Bounding Sphere
		 * 
		 * @param 	x1	x Koordinate des Mittelpunktes des ersten Kreises
		 * @param 	y1	y Koordinate des Mittelpunktes des ersten Kreises
		 * @param 	r1	radius des ersten Kreises
		 * @param 	x2	x Koordinate des Mittelpunktes des zweiten Kreises
		 * @param 	y2	y Koordinate des Mittelpunktes des zweiten Kreises	 
		 * @param 	r2	radius des zweiten Kreises
		 * 
		 * @return		Die y Koordinate des Mittelpunktes der Bounding Sphere
		 */
		return berechnePol(y1, x1, r1, y2, x2, r2)+(berechnePol(y2, x2, r2, y1, x1, r1)-berechnePol(y1, x1, r1, y2, x2, r2))/2;
	}
	public static double berechneR(double x1, double y1, double r1, double x2, double y2, double r2){
		/** 
		 * Berechnet den Radius der Bounding Sphere
		 * 
		 * @param 	x1	x Koordinate des Mittelpunktes des ersten Kreises
		 * @param 	y1	y Koordinate des Mittelpunktes des ersten Kreises
		 * @param 	r1	radius des ersten Kreises
		 * @param 	x2	x Koordinate des Mittelpunktes des zweiten Kreises
		 * @param 	y2	y Koordinate des Mittelpunktes des zweiten Kreises	 
		 * @param 	r2	Radius des zweiten Kreises
		 * 
		 * @return		Radius der Bounding Sphere
		 * 
		 */
		return (berechneDistanz(x1, y1, x2, y2)+r1+r2)/2;
	}
	
	public static void main(String[] args) {
		/** 
		 * Testprogramm zur Überprüfung der Berechnung mit Testwerten
		 * 
		 */
		double distanz = berechneDistanz(-20, 10, 5, 0);
		System.out.println("Distanz zwischen Kreismittelpunkten = "+distanz); 
		
		double x3 = berechnePol(-20, 10, 15, 5, 0, 5);
		System.out.println("E: x3 = "+x3); 
		double y3 = berechnePol(10, -20, 15, 0, 5, 5);
		System.out.println("E: y3 = "+y3); 
		
		double x4 = berechnePol(5, 0, 5, -20, 10, 15);
		System.out.println("F: x4 = "+x4); 
		double y4 = berechnePol(0, 5, 5, 10, -20, 15);
		System.out.println("F: y4 = "+y4); 
		
		System.out.println("berechneX: "+berechneX(-20, 10, 15, 5, 0, 5));
		System.out.println("berechneY: "+berechneY(-20, 10, 15, 5, 0, 5));
		System.out.println("berechneR: "+berechneR(-20, 10, 15, 5, 0, 5));
		
		System.out.println(quadratwurzel(16,2));

	}

}

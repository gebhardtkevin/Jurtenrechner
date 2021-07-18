package gitterrechner;

public class DrawSimulator {
	
	private Double poleLenght;
	private Double edgeDistance;
	private Double innerDistance;
	private Double distanceBeetweenPoles;
	private Double distanceToDoor;
	private Double distanceBeetweenLattices;
	private Integer knotsOnPole;
	
	public DrawSimulator(double latticeHeight, 
						 double poleWidth, 
						 double roofPoleDiameter, 
						 double targetRhombusEdgeDimesion) {		
		this.poleLenght = calcPoleLenght(latticeHeight, poleWidth);
		this.edgeDistance = calcEdgeDistance(roofPoleDiameter, poleWidth);
		this.innerDistance = calculateInnerDistance(poleLenght,edgeDistance, targetRhombusEdgeDimesion);
		this.distanceBeetweenPoles = calculateDistanceBeetweenPoles();
		this.distanceToDoor = calculateToDoor(poleWidth);
		this.distanceBeetweenLattices = calculateDistanceBeetweenLattices(poleWidth);
		
	}

	private Double calculateDistanceBeetweenLattices(double poleWidth) {
		//Abstand Wandstange-Wandstange bei Gitterverbindungen
		return calculateDistanceBeetweenPoles()+Math.sqrt(2*Math.pow(poleWidth,2));
	}

	private Double calculateToDoor(double poleWidth) {
		//Abstand Türanschlag bis Mitte erste Wandstange
		double lengthOnPole = innerDistance + edgeDistance+poleWidth/2; 
		return Math.cos(Math.toRadians(45))*lengthOnPole;
	}

	private Double calculateDistanceBeetweenPoles() {
		//Abstand der Wandstangen
		return Math.sqrt(2*Math.pow(innerDistance, 2));
	}

	private double calculateInnerDistance(double poleLenght, double edgeDistance, double targetRhombusEdgeDimension) {
		double dist = Double.MAX_VALUE;
		knotsOnPole = 3;
		
		//TODO Fehler abfangen, wenn Target zu gross
		while (dist > targetRhombusEdgeDimension) {
			dist = (poleLenght-2*edgeDistance)/(knotsOnPole-1);
			knotsOnPole=knotsOnPole+2;
		}
		double formerDist = (poleLenght-2*edgeDistance)/(knotsOnPole-5);
		if (Math.abs(dist-targetRhombusEdgeDimension)
			<
			Math.abs(formerDist-targetRhombusEdgeDimension)){
			knotsOnPole = knotsOnPole-2;		
			return dist;
				}else {
					knotsOnPole = knotsOnPole-4;
					return formerDist;
				}
	}

	private double calcEdgeDistance(double roofPoleDiameter, double poleWidth) {
		//Das ergibt sich erstaunlicherweise durch Dreieckskonstruktionen 
		//am Wandstangenende in Kombination mit der Dachstange
		//Der Dachstangenmittelpunkt muss auf Höhe Mittelpunkt des Stirnholzes der Wandstange
		return roofPoleDiameter + poleWidth;
	}

	private double calcPoleLenght(double LatticeHeight, double poleWidth) {
		//Länge bei 45 Grad,
		//Durch die Breite entsteht oben und unten ein kleines Dreieck
		//mit der Höhe Breite/2, 
		//wir müssen also einmal die Breite abziehen	
		return Math.sqrt(2*Math.pow(LatticeHeight,2))-poleWidth;
	}
	
	public DrawResults getResults() {
		DrawResults out = new DrawResults();
		out.setEdgeDistance(this.edgeDistance);
		out.setInnerDistance(this.innerDistance);
		out.setPoleLength(this.poleLenght);	
		out.setDistanceBeetweenPoles(this.distanceBeetweenPoles);
		out.setDistanceToDoor(this.distanceToDoor);
		out.setDistanceBeetweenLattices(this.distanceBeetweenLattices);
		out.setKnotsOnPole(this.knotsOnPole);
		return out;
	}
}

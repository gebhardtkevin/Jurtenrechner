package gitterrechner;

public class DrawResults {

	double edgeDistance;
	double innerDistance;
	double poleLength;
	double distanceBeetweenPoles;
	double distanceToDoor;
	double distanceBeetweenLattices;
	int knotsOnPole;
	
	
	public double getInnerDistance() {
		return this.innerDistance;
	}

	public double getEdgeDistance() {
		return edgeDistance;
	}

	public double getPoleLength() {
		return poleLength;
	}

	public double getDistanceBeetweenPoles() {
		return distanceBeetweenPoles;
	}

	public double getDistanceToDoor() {
		return distanceToDoor;
	}

	public double getDistanceBeetweenLattices() {
		return distanceBeetweenLattices;
	}

	public int getKnotsOnPole() {
		return knotsOnPole;
	}

	public void setInnerDistance(double innerDistance) {
		this.innerDistance = innerDistance;
	}

	public void setEdgeDistance(double edgeDistance) {
		this.edgeDistance= edgeDistance;		
	}

	public void setPoleLength(double poleLenght) {
		this.poleLength = poleLenght;		
	}

	public void setDistanceBeetweenPoles(double distanceBeetweenPoles) {
		this.distanceBeetweenPoles= distanceBeetweenPoles;
		
	}

	public void setDistanceToDoor(double distanceToDoor) {
		this.distanceToDoor = distanceToDoor;
	}

	public void setDistanceBeetweenLattices(double distanceBeetweenLattices) {
		this.distanceBeetweenLattices = distanceBeetweenLattices;
		
	}

	public void setKnotsOnPole(int knotsOnPole) {
		this.knotsOnPole = knotsOnPole;
	}
}
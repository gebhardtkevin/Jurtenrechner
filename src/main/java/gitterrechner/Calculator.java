package gitterrechner;

import java.util.ArrayList;

import graphics.Printer;

public class Calculator {
	private DrawResults results;
	private InputData data;


	public Calculator(InputData data, DrawResults results) {

		this.data = data;
		this.results= results;
		Printer printer = new Printer(this);
		printer.print();
	}

	private boolean isEven(int x) {
		return (((x & 1) == 0 )?true:false);
	}

	public int getRoofPoleCount(){
		int roofPoleCount = getKnots();
		for (double doorWidth : data.getDoorWidths()) {
			double totalWidth = doorWidth+2*results.getDistanceToDoor();
			
			int polesOverDoor1 = (int) Math.floor(totalWidth/getKnotDistance())-1;
			int polesOverDoor2 = (int) Math.ceil(totalWidth/getKnotDistance())-1;
			
			double distanceBeetweenDoorPoles1 = totalWidth/(polesOverDoor1+1);
			double distanceBeetweenDoorPoles2 = totalWidth/(polesOverDoor2+1);
			
			int polesOverDoor = 0;
			if (distanceBeetweenDoorPoles1<distanceBeetweenDoorPoles2){
				polesOverDoor = polesOverDoor1;
			}else {
				polesOverDoor = polesOverDoor2;
			}
			
			roofPoleCount += polesOverDoor;
		}
		return roofPoleCount;
	}

	public double getLengthError() {
		return results.getEdgeDistance()-data.getTargetRhombusEdgeDimension();
	}

	public int getStandardKnots() {
		int edgeKnots = (results.getKnotsOnPole()-1)/2;
		return getKnots()-edgeKnots;
	}

	public int getLatticeConnectionPoints() {
		return data.getLatticeCount()-data.getDoorCount();
	}

	public int getDoorSites() {
		return data.getDoorCount()*2;
		
	}
	
	public double getKnotDistance() {
		return results.getDistanceBeetweenPoles();
	}

	public ArrayList<Integer> getPoleDistribution() {
		
		int remainingLattices=data.getLatticeCount();
		int remainingPoles = 2*getStandardKnots();
		
		ArrayList<Integer> poleDistribution = new ArrayList<Integer>();
		while (remainingLattices>0) {
			int polesOnLattice = (int) Math.floor(remainingPoles/remainingLattices); 
			if (!isEven(polesOnLattice)) {
				polesOnLattice--;
			}
			poleDistribution.add(polesOnLattice);
			remainingPoles = remainingPoles-polesOnLattice;
			remainingLattices--;			
		}
		return poleDistribution;
	}

	public int getEvenPoleLength(int i) {
		return (int) Math.round(2*results.getEdgeDistance()+results.getInnerDistance()*i);
	}

	public int getUnEvenPoleLength(int i) {
		return (int) Math.round(2*results.getEdgeDistance()+results.getInnerDistance()*(i+1));
	}
	
	public double getPoleLength() {
		double length = (data.getDiameter()-data.getCrownDiameter())/2;
		double sin = Math.cos(Math.toRadians(data.getRoofAngle()));
		Settings settings = new Settings(true);
		return length/sin+settings.getLowerPoleAddition()+settings.getUpperPoleAddition();	
	}

	private int getKnots() {
		double circumference = data.getDiameter()*Math.PI;
		double targetTotalLatticeLength = circumference;
	
		for (double doorWidth : data.getDoorWidths()) {
			double alpha = Math.asin(doorWidth/data.getDiameter());
			double doorOnCircleLength = circumference*alpha/(Math.PI);
			targetTotalLatticeLength=targetTotalLatticeLength-doorOnCircleLength;
		}
	
		double latticeEdgeSum = getDoorSites()*results.getDistanceToDoor()+
		getLatticeConnectionPoints()*results.getDistanceBeetweenLattices();
		double latticeSum = latticeEdgeSum;
		int knots = 0;
		while  (latticeSum<targetTotalLatticeLength) {
			knots++;
			latticeSum+=results.getDistanceBeetweenPoles();
		}
		double lastLatticeSum = latticeSum-results.getDistanceBeetweenPoles();
	
		double diff = latticeSum-targetTotalLatticeLength;
		double lastDiff = lastLatticeSum-targetTotalLatticeLength;
		if (Math.abs(diff)>Math.abs(lastDiff)) {
			knots--;
			diff = lastDiff;
		}
		return knots;
	}

	public InputData getData() {
		return this.data;
	}
	
	public DrawResults getResults() {
		return this.results;
	}
}
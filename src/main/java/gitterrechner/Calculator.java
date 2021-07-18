package gitterrechner;

import java.util.ArrayList;

public class Calculator {
	
	

	public Calculator(InputData data, DrawResults results) {

		int doorSites = data.getDoorCount()*2;
		int latticeConnectionPoints =data.getLatticeCount()-data.getDoorCount();
		
		double circumference = data.getDiameter()*Math.PI;
		double targetTotalLatticeLength = circumference;
		
		for (double doorWidth : data.getDoorWidths()) {
			double alpha = Math.asin(doorWidth/data.getDiameter());
			double doorOnCircleLength = circumference*alpha/(Math.PI);
			targetTotalLatticeLength=targetTotalLatticeLength-doorOnCircleLength;
		}
		
		double latticeEdgeSum = doorSites*results.getDistanceToDoor()+
						        latticeConnectionPoints*results.getDistanceBeetweenLattices();
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
		
		int edgeKnots = (int) Math.round((results.getKnotsOnPole()-1)/2);
		int standardKnots = knots-edgeKnots;
		
		int remainingLattices=data.getLatticeCount();
		int remainingPoles = 2*standardKnots;
		
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
	
		getPieceList(results, standardKnots, poleDistribution, doorSites, latticeConnectionPoints, diff);
	}
	
	private boolean isEven(int x) {
		
		return (((x & 1) == 0 )?true:false);
	}
	
	public void getPieceList(DrawResults results, int standardKnots, ArrayList<Integer> poledistribution,int doorSites, int latticeConnectionPoints, double lengthError) {

		//ungerade Ecken: Längste Stange ist eine zusätzliche mit knotsOnPole-1, da die 1x-Stangen wegfallen und eine Vollstange entsprechend gekürzt wird

		//Todo: (wird noch nicht geprüft)
		//die Minimallänge des Gitters ohne Vollstangen ist : ((knotsOnPole-1)/2)-1 + die entsprechenden Enden
		//bei kürzerem Gitter: Bei n fehlenden Knoten knotsOnPole-1-n ist die maximale Stangenlänge. diese ist doppelt vorhanden 
		
		int evenEdges = doorSites + latticeConnectionPoints;
		int unevenEdges = latticeConnectionPoints;
		System.out.println("=============================================================");
		System.out.println("=============================================================");
		System.out.println("=                                                           =");
		System.out.println("=                     LATTICE PART LIST                     =");
		System.out.println("=                                                           =");
		System.out.println("=============================================================");
		System.out.println("=============================================================");
		System.out.println();
		System.out.println();
		System.out.println("=============================================================");
		int i;
		for (i = 1; i<((int) Math.round(results.getKnotsOnPole())-2); i=i+2) {
			System.out.println("  " + 2*evenEdges   + " x " + Math.round(2*results.getEdgeDistance()+results.getInnerDistance()*i) + " mm");
			System.out.println("  " + 2*unevenEdges + " x " + Math.round(2*results.getEdgeDistance()+results.getInnerDistance()*(i+1)) + " mm");
		}
		System.out.println("  " + 2*(evenEdges+unevenEdges)   + " x " + Math.round(2*results.getEdgeDistance()+results.getInnerDistance()*i) + " mm");
		System.out.print("= " + 2*standardKnots + " x " + Math.round(2*results.getEdgeDistance()+results.getInnerDistance()*(i+1)) + " mm");
		System.out.print(" [ ");
		for (int poles: poledistribution) {
			System.out.print(poles + " ");
		}
		System.out.println("]");
		
		System.out.println("=============================================================");
		System.out.println("  Edge Distance: " + results.getEdgeDistance());
		System.out.println("  Inner Distance: " + results.getInnerDistance());
		System.out.println("  Differenz zur optimalen Länge: " + lengthError + "mm");
	}


}

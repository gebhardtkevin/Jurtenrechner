package graphics;

import java.text.DecimalFormat;

import ConsoleGui.ConsoleGui;
import gitterrechner.Calculator;
import gitterrechner.DrawResults;
import gitterrechner.InputData;
import gitterrechner.Settings;

public class Printer {
	
	private Calculator calculator;
	private InputData data;
	private DrawResults results;
	private DecimalFormat oneDecimal =  new DecimalFormat( "0.0" );
	private ConsoleGui gui = new ConsoleGui(80, '*', ' ');
	private Settings settings = new Settings();
	
	public Printer(Calculator calculator) {
		this.calculator=calculator;
		this.data = calculator.getData();
		this.results = calculator.getResults();
	}
	
	private String format(double value) {
		 return oneDecimal.format(value);
	}

	private void printTitle() {
		gui.printDivider();
		gui.printDivider();
		gui.printBorder();
		gui.printBorder("YurtCalc", Alignment.CENTER);
		gui.printBorder("V1.0",Alignment.CENTER);
		gui.printBorder();
		gui.printDivider();
		gui.endChapter();
	}

	private void printPieceList() {
		//ungerade Ecken: Längste Stange ist eine zusätzliche mit knotsOnPole-1, da die 1x-Stangen wegfallen und eine Vollstange entsprechend gekürzt wird
	
		//Todo: (wird noch nicht geprüft)
		//die Minimallänge des Gitters ohne Vollstangen ist : ((knotsOnPole-1)/2)-1 + die entsprechenden Enden
		//bei kürzerem Gitter: Bei n fehlenden Knoten knotsOnPole-1-n ist die maximale Stangenlänge. diese ist doppelt vorhanden 
		
		int evenEdges = calculator.getDoorSites() + calculator.getLatticeConnectionPoints();
		int unevenEdges = calculator.getLatticeConnectionPoints();
		gui.printChapterTitle("Lattice Data");
		int i;
		for (i = 1; i<(results.getKnotsOnPole()-2); i=i+2) {
			gui.printBorder("  " + 2*evenEdges   + " x " + format(calculator.getEvenPoleLength(i)) + " mm", Alignment.LEFT);
			gui.printBorder("  " + 2*unevenEdges + " x " + format(calculator.getUnEvenPoleLength(i)) + " mm", Alignment.LEFT);
		}
		gui.printBorder("  " + 2*(evenEdges+unevenEdges)   + " x " + format(calculator.getEvenPoleLength(i)) + " mm", Alignment.LEFT);
		gui.printBorder("  " + 2*calculator.getStandardKnots() + " x " + format(calculator.getUnEvenPoleLength(i)) + " mm",Alignment.LEFT);
		
		String out = "  [ ";
		for (int poles: calculator.getPoleDistribution()) {
			out += (poles + " ");
		}
		out += ("]");
		gui.printBorder(out,Alignment.LEFT);
		gui.printDivider();
		gui.printBorder("  Edge Distance: " + format(results.getEdgeDistance())+ " mm",Alignment.CENTER);
		gui.printBorder("  Inner Distance: " + format(results.getInnerDistance())+ " mm",Alignment.CENTER);
		gui.printBorder("  Differenz zur optimalen Länge: " + format(calculator.getLengthError()) + " mm",Alignment.CENTER);
		gui.endChapter();
	}

	private void printRoofInfo() {
		gui.printChapterTitle("Roof Data");
		gui.printBorder("  Anzahl der Dachstangen: " + calculator.getRoofPoleCount(), Alignment.CENTER);
		gui.printBorder("  Länge der Dachstangen: " + format(calculator.getPoleLength()) + " mm", Alignment.CENTER);
		gui.endChapter();
	}
	
	
	
	private void printDoorInfo() {
		//TODO noch ungeprüft: Erste Stange auf der Tür kann noch zu weit aussen sitzen
		
		int doorNum = 1;
		gui.printChapterTitle("Door Data");
		for (double doorWidth : data.getDoorWidths()) {
			double totalWidth = doorWidth+2*results.getDistanceToDoor();
			int polesOverDoor1 = (int) Math.floor(totalWidth/calculator.getKnotDistance())-1;
			int polesOverDoor2 = (int) Math.ceil(totalWidth/calculator.getKnotDistance())-1;
			
			double distanceBeetweenDoorPoles1 = totalWidth/(polesOverDoor1+1);
			double distanceBeetweenDoorPoles2 = totalWidth/(polesOverDoor2+1);

			int polesOverDoor = 0;
			double distanceBeetweenDoorPoles = 0; 
			if (distanceBeetweenDoorPoles1<distanceBeetweenDoorPoles2){
				polesOverDoor = polesOverDoor1;
				distanceBeetweenDoorPoles = distanceBeetweenDoorPoles1;
			}else {
				polesOverDoor = polesOverDoor2;
				distanceBeetweenDoorPoles = distanceBeetweenDoorPoles2;
			}
			
			double doorLengthError = distanceBeetweenDoorPoles-calculator.getKnotDistance(); 
			gui.printBorder();
			gui.printBorder("Tür "+ doorNum, Alignment.CENTER, '*');
			gui.printDivider();
			gui.printBorder("  Stangen über Tür "+ doorNum + ": " + polesOverDoor, Alignment.CENTER);
			gui.printBorder("  Abweichung im Abstand der Stangen an Tür "+ doorNum + ": " + format(doorLengthError) + " mm", Alignment.CENTER);
			gui.printBorder("  Abstand Türrand zu Mittelpunkt erster Stange (gerade) "+ doorNum + ": " + format(distanceBeetweenDoorPoles-results.getDistanceToDoor()) + " mm", Alignment.CENTER);
			gui.printBorder("  Abstand zwischen Stangen über Tür (gerade) "+ doorNum + ": " + format(distanceBeetweenDoorPoles) + " mm", Alignment.CENTER);
			doorNum++;
		}
		gui.endChapter();
	}
	
	private void printFabricInfo() {
		gui.printChapterTitle("Fabric Data");
		gui.printBorder("Äusserer Radius Dackkreis: " + format(getRoofFabricOuterRadius())+" mm", Alignment.CENTER);
		gui.printBorder("Innerer Radius Dackkreis: " + format(getRoofFabricInnerRadius())+" mm", Alignment.CENTER);
		gui.printBorder("Mindest-Segmentwinkel: " + format(getRoofFabricSegmentAngle())+" mm", Alignment.CENTER);		
		gui.printDivider();
		gui.printBorder("Gesamtlänge Wand : "+format(getWallLength()) + " mm", Alignment.CENTER);
		gui.endChapter();
	}
			
	private double getWallLength() {
		double length = Math.PI*data.getDiameter();
		for (double door : data.getDoorWidths()) {
			length = length-door;
		}
		length = length + (2*data.getDoorCount()*settings.getSeamAdditionDoor());
		return length;
	}

	private double getRoofFabricSegmentAngle() {
		return (data.getDiameter()*360)/(2*getRoofFabricOuterRadius());
	}

	private double getRoofFabricInnerRadius() {
		double r = data.getCrownDiameter()/2;
		double h = r*Math.tan(Math.toRadians(data.getRoofAngle()));
		return Math.sqrt((h*h)+(r*r))+settings.getSeamAdditionCrown();
	}

	private double getRoofFabricOuterRadius() {
		double r = data.getDiameter()/2;
		double h = r*Math.tan(Math.toRadians(data.getRoofAngle()));
		return Math.sqrt((h*h)+(r*r))+settings.getSeamAdditionRoofOverhang();
	}

	public void print() {
		printTitle();
		printPieceList();
		printRoofInfo();
		printDoorInfo();
		printFabricInfo();
	}
}

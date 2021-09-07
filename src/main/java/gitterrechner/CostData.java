package gitterrechner;

	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.Properties;

	public class CostData {

		private static final double initialLatticeWoodPerMeter = 1.00;
		private static final double initialRoofPolePerPole = 3.00;
		private static final double initialRingWoodPerM3 = 700.00;
		private static final double initialDoorUsed = 100.0;
		private static final double initialDoorWoodPerM3 = 2000.00;
		private static final double initialSmallParts = 1000.00;
		private static final double initialInnerFabricPerM2 = 2.00;
		private static final double initialFeltPerM2 = 10.00;
		private static final double initialRainFabricPerM2 = 14.00;
		private static final double initialOuterFabricPerM2 = 4.00;
		private static final double initialUpperFloorPerM2 = 35.00;
		private static final double initialFloorInsulationPerM2 = 14.00;
		private static final double initialStrucuralFloorWoodPerMeter = 10.00;
		private static final double initialLowerLayerPerM2 = 10.00;

		private static double latticeWoodPerMeter;
		private static double roofPolePerPole;
		private static double ringWoodPerM3;
		private static double doorUsed;
		private static double doorWoodPerM3;
		private static double smallParts;
		private static double innerFabricPerM2;
		private static double feltPerM2;
		private static double rainFabricPerM2;
		private static double outerFabricPerM2;
		private static double upperFloorPerM2;
		private static double floorInsulationPerM2;
		private static double strucuralFloorWoodPerMeter;
		private static double lowerLayerPerM2;
		
		
		public CostData() {
			this(false);
		}
		
		public CostData(boolean reset) {
			if (reset) {
				reset();
				save();
			}
			load();
		
		}	
		
		private void load() {
			// Load Settings
			Properties loadProps = new Properties();
			try {
				loadProps.loadFromXML(new FileInputStream("costData.xml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  roofPolePerPole = Double.valueOf(loadProps.getProperty("roofPolePerPole"));;
			  ringWoodPerM3 = Double.valueOf(loadProps.getProperty("ringWoodPerM3"));
			  doorUsed = Double.valueOf(loadProps.getProperty("doorUsed"));
			  doorWoodPerM3 = Double.valueOf(loadProps.getProperty("doorWoodPerM3"));
			  smallParts = Double.valueOf(loadProps.getProperty("smallParts"));
			  innerFabricPerM2 = Double.valueOf(loadProps.getProperty("innerFabricPerM2"));
			  feltPerM2 = Double.valueOf(loadProps.getProperty("feltPerM2"));
			  rainFabricPerM2 = Double.valueOf(loadProps.getProperty("rainFabricPerM2"));
			  outerFabricPerM2 = Double.valueOf(loadProps.getProperty("outerFabricPerM2"));
			  upperFloorPerM2 = Double.valueOf(loadProps.getProperty("upperFloorPerM2"));
			  floorInsulationPerM2 = Double.valueOf(loadProps.getProperty("floorInsulationPerM2"));
			  strucuralFloorWoodPerMeter = Double.valueOf(loadProps.getProperty("strucuralFloorWoodPerMeter"));
			  lowerLayerPerM2 = Double.valueOf(loadProps.getProperty("lowerLayerPerM2"));
		}
		
		public void reset(){
			 latticeWoodPerMeter = initialLatticeWoodPerMeter;
			  roofPolePerPole = initialRoofPolePerPole;;
			  ringWoodPerM3 = initialRingWoodPerM3;
			  doorUsed = initialDoorUsed;
			  doorWoodPerM3 = initialDoorWoodPerM3;
			  smallParts = initialSmallParts;
			  innerFabricPerM2 = initialInnerFabricPerM2;
			  feltPerM2 = initialFeltPerM2;
			  rainFabricPerM2 = initialRainFabricPerM2;
			  outerFabricPerM2 = initialOuterFabricPerM2;
			  upperFloorPerM2 = initialUpperFloorPerM2;
			  floorInsulationPerM2 = initialFloorInsulationPerM2;
			  strucuralFloorWoodPerMeter = initialStrucuralFloorWoodPerMeter;
			  lowerLayerPerM2 = initialLowerLayerPerM2;	
		}
	    
	    public void save(){
	    	 // Save Settings
	        Properties saveProps = new Properties();
	        

			saveProps.setProperty("latticeWoodPerMeter", String.valueOf(latticeWoodPerMeter));
			saveProps.setProperty("roofPolePerPole", String.valueOf(roofPolePerPole));
			saveProps.setProperty("ringWoodPerM3", String.valueOf(ringWoodPerM3));
			saveProps.setProperty("doorUsed", String.valueOf(doorUsed));
			saveProps.setProperty("doorWoodPerM3", String.valueOf(doorWoodPerM3));
			saveProps.setProperty("smallParts", String.valueOf(smallParts));
			saveProps.setProperty("innerFabricPerM2", String.valueOf(innerFabricPerM2));
			saveProps.setProperty("feltPerM2", String.valueOf(feltPerM2));
			saveProps.setProperty("rainFabricPerM2", String.valueOf(rainFabricPerM2));
			saveProps.setProperty("outerFabricPerM2", String.valueOf(outerFabricPerM2));
			saveProps.setProperty("upperFloorPerM2", String.valueOf(upperFloorPerM2));
			saveProps.setProperty("floorInsulationPerM2", String.valueOf(floorInsulationPerM2));
			saveProps.setProperty("strucuralFloorWoodPerMeter", String.valueOf(strucuralFloorWoodPerMeter));
			saveProps.setProperty("lowerLayerPerM2", String.valueOf(lowerLayerPerM2)); 
	        try {
				saveProps.storeToXML(new FileOutputStream("settings.xml"), "");
			} catch (IOException e) {
				e.printStackTrace();
			} 
	    }
	    
		public static double getLatticeWoodPerMeter() {
			return latticeWoodPerMeter;
		}

		public static void setLatticeWoodPerMeter(double latticeWoodPerMeter) {
			CostData.latticeWoodPerMeter = latticeWoodPerMeter;
		}

		public static double getRoofPolePerPole() {
			return roofPolePerPole;
		}

		public static void setRoofPolePerPole(double roofPolePerPole) {
			CostData.roofPolePerPole = roofPolePerPole;
		}

		public static double getRingWoodPerM3() {
			return ringWoodPerM3;
		}

		public static void setRingWoodPerM3(double ringWoodPerM3) {
			CostData.ringWoodPerM3 = ringWoodPerM3;
		}

		public static double getDoorUsed() {
			return doorUsed;
		}

		public static void setDoorUsed(double doorUsed) {
			CostData.doorUsed = doorUsed;
		}

		public static double getDoorWoodPerM3() {
			return doorWoodPerM3;
		}

		public static void setDoorWoodPerM3(double doorWoodPerM3) {
			CostData.doorWoodPerM3 = doorWoodPerM3;
		}

		public static double getSmallParts() {
			return smallParts;
		}

		public static void setSmallParts(double smallParts) {
			CostData.smallParts = smallParts;
		}

		public static double getInnerFabricPerM2() {
			return innerFabricPerM2;
		}

		public static void setInnerFabricPerM2(double innerFabricPerM2) {
			CostData.innerFabricPerM2 = innerFabricPerM2;
		}

		public static double getFeltPerM2() {
			return feltPerM2;
		}

		public static void setFeltPerM2(double feltPerM2) {
			CostData.feltPerM2 = feltPerM2;
		}

		public static double getRainFabricPerM2() {
			return rainFabricPerM2;
		}

		public static void setRainFabricPerM2(double rainFabricPerM2) {
			CostData.rainFabricPerM2 = rainFabricPerM2;
		}

		public static double getOuterFabricPerM2() {
			return outerFabricPerM2;
		}

		public static void setOuterFabricPerM2(double outerFabricPerM2) {
			CostData.outerFabricPerM2 = outerFabricPerM2;
		}

		public static double getUpperFloorPerM2() {
			return upperFloorPerM2;
		}

		public static void setUpperFloorPerM2(double upperFloorPerM2) {
			CostData.upperFloorPerM2 = upperFloorPerM2;
		}

		public static double getFloorInsulationPerM2() {
			return floorInsulationPerM2;
		}

		public static void setFloorInsulationPerM2(double floorInsulationPerM2) {
			CostData.floorInsulationPerM2 = floorInsulationPerM2;
		}

		public static double getStrucuralFloorWoodPerMeter() {
			return strucuralFloorWoodPerMeter;
		}

		public static void setStrucuralFloorWoodPerMeter(double strucuralFloorWoodPerMeter) {
			CostData.strucuralFloorWoodPerMeter = strucuralFloorWoodPerMeter;
		}

		public static double getLowerLayerPerM2() {
			return lowerLayerPerM2;
		}

		public static void setLowerLayerPerM2(double lowerLayerPerM2) {
			CostData.lowerLayerPerM2 = lowerLayerPerM2;
		}
}

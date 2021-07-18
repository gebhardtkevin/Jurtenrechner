package gitterrechner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class InputData {

	private static final Double initialPoleWidth = 35.0;
	private static final Double initialRoofPoleDiameter = 40.0;
	private static final Double initialTargetRhombusEdgeDimension = 200.0; 
	private static final Integer initialLatticeCount = 5;
	private static final Double initialLatticeHeight = 1800.0;
	private static final double initialDiameter = 5000.0;
	private static final double initialDoorWidth = 1000.0;
	
	private Double poleWidth;
	private Double roofPoleDiameter;
	private Double targetRhombusEdgeDimension; 
	private Integer latticeCount;
	private Double latticeHeight;
	private double diameter;
	private ArrayList<Double> doorWidths;
	
	
	public InputData() {
		this.doorWidths = new ArrayList<Double>();
		//resetStandardData();
		load();
	}
	
	public double getLatticeHeight() {
		return latticeHeight;
	}

	public double getPoleWidth() {
		return poleWidth;
	}

	public double getRoofPoleDiameter() {
		return roofPoleDiameter;
	}

	public double getTargetRhombusEdgeDimension() {
		return targetRhombusEdgeDimension;
	}

	public Integer getLatticeCount() {
		return latticeCount;
	}
	
	public Integer getDoorCount() {
		return doorWidths.size();
	}
	
	public void setLatticeHeight(Double latticeHeight) {
		this.latticeHeight = latticeHeight;
	}

	public void setPoleWidth(Double poleWidth) {
		this.poleWidth = poleWidth;
	}

	public void setRoofPoleDiameter(Double roofPoleDiameter) {
		this.roofPoleDiameter = roofPoleDiameter;
	}

	public void setTargetRhombusEdgeDimension(Double targetRhombusEdgeDimension) {
		this.targetRhombusEdgeDimension = targetRhombusEdgeDimension;
	}

	public void setLatticeCount(Integer latticeCount) {
		this.latticeCount = latticeCount;
	}

	public double getDiameter() {
		return this.diameter;
	}

	public void setDiameter(Double diameter) {
		this.diameter = diameter;
	}

	public void addDoor(double doorWidth) {
		this.doorWidths.add(doorWidth);		
	}

	public ArrayList<Double> getDoorWidths() {
		return this.doorWidths;
	}

	public void setDoors(ArrayList<Double> widths) {
		this.doorWidths = widths;
		
	}

	public void load(){
		// Load Settings
		Properties loadProps = new Properties();
		try {
			loadProps.loadFromXML(new FileInputStream("standardData.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			poleWidth = Double.valueOf(loadProps.getProperty("poleWidth"));
			diameter = Double.valueOf(loadProps.getProperty("diameter"));
			latticeHeight = Double.valueOf(loadProps.getProperty("latticeHeight"));
			latticeCount = Integer.valueOf(loadProps.getProperty("latticeCount"));
			targetRhombusEdgeDimension = Double.valueOf(loadProps.getProperty("targetRhombusEdgeDimension"));
			roofPoleDiameter = Double.valueOf(loadProps.getProperty("roofPoleDiameter"));
			String doorWidthString = loadProps.getProperty("doorWidth");
		
			int startIndex = doorWidthString.lastIndexOf("{");
			int endIndex = doorWidthString.lastIndexOf("}");
			doorWidthString = doorWidthString.substring(startIndex+1, endIndex-1);
			List<String> doorWidthStrings = Arrays.asList(doorWidthString.split(","));
			for (String dws : doorWidthStrings) {
				doorWidths.add(Double.valueOf(dws));
			}
			
			if (doorWidths.isEmpty()) {
				doorWidths.add(initialDoorWidth);
			}
	}
	
	public void reset(){
			poleWidth = initialPoleWidth;
			diameter = initialDiameter;
			latticeHeight = initialLatticeHeight;
			latticeCount = initialLatticeCount;
			targetRhombusEdgeDimension = initialTargetRhombusEdgeDimension;
			roofPoleDiameter = initialRoofPoleDiameter;
			doorWidths.clear();
			doorWidths.add(initialDoorWidth);
}
    
    public void save(){
    	 // Save Settings
        Properties saveProps = new Properties();
        
        saveProps.setProperty("diameter", String.valueOf(diameter));
        saveProps.setProperty("latticeHeight", String.valueOf(latticeHeight));
        saveProps.setProperty("latticeCount", String.valueOf(latticeCount));
        saveProps.setProperty("targetRhombusEdgeDimension", String.valueOf(targetRhombusEdgeDimension));
        saveProps.setProperty("roofPoleDiameter", String.valueOf(roofPoleDiameter));
        saveProps.setProperty("poleWidth", String.valueOf(poleWidth));
        
        String  doorWidthString = "{";
        for (double width : doorWidths) {
        	doorWidthString += width + " , ";
        }
        doorWidthString = doorWidthString.substring(0, doorWidthString.length()-2);
        doorWidthString += "}";
        saveProps.setProperty("doorWidth", doorWidthString);
        
        try {
			saveProps.storeToXML(new FileOutputStream("standardData.xml"), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
}

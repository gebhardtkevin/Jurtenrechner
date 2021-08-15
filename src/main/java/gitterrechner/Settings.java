package gitterrechner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
	private static final double initialLowerPoleAddition = 100;
	private static final double initialUpperPoleAddition = 50;
	private static final double initialCroneWidth = 70;
	private static final double initialSeamAdditionDoor = 50;
	private static final double initialSeamAdditionCrown = 50;
	private static final double initialSeamAdditionRoofOverhang = 220;
	
	private static double upperPoleAddition;
	private static double lowerPoleAddition;
	private static double crownWidth;
	private static double seamAdditionDoor;
	private static double seamAdditionCrown;
	private static double seamAdditionRoofOverhang;
	
	public Settings() {
		this(false);
	}
	
	public Settings(boolean reset) {
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
			loadProps.loadFromXML(new FileInputStream("settings.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			upperPoleAddition = Double.valueOf(loadProps.getProperty("upperPoleAddition"));
			lowerPoleAddition = Double.valueOf(loadProps.getProperty("lowerPoleAddition"));
			crownWidth = Double.valueOf(loadProps.getProperty("crownWidth"));				
			seamAdditionDoor = Double.valueOf(loadProps.getProperty("seamAdditionDoor"));
			seamAdditionCrown = Double.valueOf(loadProps.getProperty("seamAdditionCrown"));
			seamAdditionRoofOverhang = Double.valueOf(loadProps.getProperty("seamAdditionRoofOverhang"));
	}
	
	public void reset(){
			upperPoleAddition = initialUpperPoleAddition;
			lowerPoleAddition = initialLowerPoleAddition;
			crownWidth = initialCroneWidth;
			seamAdditionDoor = initialSeamAdditionDoor;
			seamAdditionCrown = initialSeamAdditionCrown;
			seamAdditionRoofOverhang = initialSeamAdditionRoofOverhang;
	}
    
    public void save(){
    	 // Save Settings
        Properties saveProps = new Properties();
        
        saveProps.setProperty("upperPoleAddition", String.valueOf(upperPoleAddition));
        saveProps.setProperty("lowerPoleAddition", String.valueOf(lowerPoleAddition));
        saveProps.setProperty("crownWidth", String.valueOf(crownWidth));        
        saveProps.setProperty("seamAdditionDoor", String.valueOf(seamAdditionDoor)); 
        saveProps.setProperty("seamAdditionCrown", String.valueOf(seamAdditionCrown)); 
        saveProps.setProperty("seamAdditionRoofOverhang", String.valueOf(seamAdditionRoofOverhang)); 
        try {
			saveProps.storeToXML(new FileOutputStream("settings.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
	
	public double getUpperPoleAddition() {
		return upperPoleAddition;
	}
	
	public double getLowerPoleAddition() {
		return lowerPoleAddition;
	}		
	
	public double getCrownWidth() {
		return crownWidth;
	}	
	
	public double getSeamAdditionDoor() {
		return seamAdditionDoor;
	}

	public double getSeamAdditionCrown() {
		return seamAdditionCrown;
	}

	public double getSeamAdditionRoofOverhang() {
		return seamAdditionRoofOverhang;
	}
}

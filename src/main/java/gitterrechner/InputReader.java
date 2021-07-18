package gitterrechner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InputReader {
	
	InputData data = new InputData();
	int doorCount = data.getDoorWidths().size();
	
	public InputReader() {
	}
	
	public InputData read(){
		System.out.println("Durchmesser der Jurte? (" + data.getDiameter() + ")");
		while(!readDiameter());
		System.out.println("Höhe des Scherengitters in mm? (" + data.getLatticeHeight() + ")");
		while (!readHeight());
		System.out.println("Breite der Gitterstangen in mm? (" + data.getPoleWidth() + ")");
		while(!readWidth());
		System.out.println("optimales Rautenseitenmass in mm? (" + data.getTargetRhombusEdgeDimension() + ")");
		while (!readRhombusEdgeDimension());
		System.out.println("Durchmesser der Dachstangen in mm? (" + data.getRoofPoleDiameter() + ")");
		while (!readRoofPoleDiameter());
		System.out.println("Anzahl Gitter? (" + data.getLatticeCount() + ")");
		while(!readLatticeCount());
		while(!readDoorCount());
		this.data.save();
		return this.data;
	}

	private boolean readDoorCount() {
		
		System.out.println("Anzahl Türen? (" + data.getDoorWidths().size() + ")");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = br.readLine();
			if (input.isBlank()){
				doorCount = data.getDoorCount();
			}else {
				doorCount = Integer.parseInt(input);
			}
			return readDoorWidths();
		} catch (NumberFormatException | IOException e) {
			System.out.println("Nöö. Ich hätte gern eine Integer");
			return false;
		}
	}
	
	private boolean readDoorWidths() {
		boolean sucess = true;
		ArrayList<Double> widths = new ArrayList<Double>();
		for (int i = 1; i<=doorCount;i++) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				System.out.print("Breite " + (i) + ".te Tür? ");
				if (data.getDoorCount()>=i) {
					System.out.println("(" + data.getDoorWidths().get(i-1)+ ")");
				}else {
					System.out.println();
				}
				
				String input = br.readLine();
					if (input.isBlank()){
						widths.add(data.getDoorWidths().get(i-1));
					}else {
						widths.add(Double.parseDouble(input));
					}
			}catch (NumberFormatException | IOException e) {
			System.out.println("Nöö. Ich hätte gern eine Double");
			sucess = false;
			}
		}
		data.setDoors(widths);
		return sucess;
	}
	

	private boolean readDiameter() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = br.readLine();
			if (!input.isBlank()){
				data.setDiameter(Double.parseDouble(input));
			}
			return true;
		} catch (NumberFormatException | IOException e) {
			System.out.println("Nöö. Ich hätte gern eine Double");
			return false;
		}
	}
	
	private boolean readLatticeCount() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = br.readLine();
			if (!input.isBlank()){
				data.setLatticeCount(Integer.parseInt(input));
			}
			return true;
		} catch (NumberFormatException | IOException e) {
			System.out.println("Nöö. Ich hätte gern eine Integer");
			return false;
		}
	}

	private boolean readHeight(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = br.readLine();
			if (!input.isBlank()){
				data.setLatticeHeight(Double.parseDouble(input));
			}
			return true;
		} catch (NumberFormatException | IOException e) {
			System.out.println("Nöö. Ich hätte gern eine Double");
			return false;
		}
	}

	private boolean readWidth(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = br.readLine();
			if (!input.isBlank()){
				data.setPoleWidth(Double.parseDouble(input));
			}
			return true;
		} catch (NumberFormatException | IOException e) {
			System.out.println("Nöö. Ich hätte gern eine Double");
			return false;
		}
	}
	
	private boolean readRoofPoleDiameter() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = br.readLine();
			if (!input.isBlank()){
				data.setRoofPoleDiameter(Double.parseDouble(input));
			}
			return true;
		} catch (NumberFormatException | IOException e) {
			System.out.println("Nöö. Ich hätte gern eine Double");
			return false;
		}
	}
	
	private boolean readRhombusEdgeDimension() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = br.readLine();
			if (!input.isBlank()){
				data.setTargetRhombusEdgeDimension(Double.parseDouble(input));
			}
			return true;
		} catch (NumberFormatException | IOException e) {
			System.out.println("Nöö. Ich hätte gern eine Double");
			return false;
		}
	}
}
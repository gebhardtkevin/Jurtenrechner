package gitterrechner;

public class App {

	public static void main(String[] args) {
		InputReader reader = new InputReader();
		InputData data = reader.read();
		DrawSimulator simulator = new DrawSimulator(data.getLatticeHeight(),
				                                    data.getPoleWidth(), 
				                                    data.getRoofPoleDiameter(), 
				                                    data.getTargetRhombusEdgeDimension());
		
		new Calculator(data, simulator.getResults());
	}

}

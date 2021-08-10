package ConsoleGui;

import graphics.Alignment;

public class ConsoleGui {
	
	private int borderWidth; 
	private char borderChar;
	private char fillChar;

	
	public ConsoleGui(int width, char border, char standardFill) {
		this.borderWidth = width;
		this.borderChar = border;
		this.fillChar = standardFill;
	}
	
	public void printDivider() {
		for (int i=0;i<borderWidth;i++) {
			System.out.print(borderChar);
		}
		System.out.println();
	}
	
	public void printBorder(String text, Alignment orientation) {
		printBorder(text, orientation,this.fillChar);
	}
		
	public void printBorder(String text, Alignment orientation,char fillChar) {
		String out = new String();
		out += borderChar;
		int space = borderWidth-2; 
		if (text.length()<space) {
			int free1 = (int) (Math.floor(space-text.length())/2);
			int free2;
			if (isEven(text.length())){
				free2 = free1;				
			}else {
				free2 = free1+1;
			}
			if (orientation.equals(Alignment.CENTER)) {
				for (int i= 0; i<free1; i++) {
					out += fillChar;
				}
				out += text;
				for (int i= 0; i<free2; i++) {
					out += fillChar;
				}
			}	
			
			if (orientation.equals(Alignment.LEFT)) {
				
				out += text;
				for (int i= 0; i<free1+free2; i++) {
					out += fillChar;
				}
			}	
			
			if (orientation.equals(Alignment.RIGHT)) {
				for (int i= 0; i<free1+free2; i++) {
					out += fillChar;
				}
				out += text;
			}	
		}
		out+=borderChar;
		System.out.println(out);
	}
	
	public void printBorder(String text, Alignment align, boolean fill) {
		if (fill) {
			printBorder(text,align,borderChar);			
		}else {
			printBorder(text,align,fillChar);
		}
	}
	
	public void printBorder() {
		printBorder("", Alignment.LEFT);
	}
	
	public void endChapter() {
		printDivider();
		System.out.println();
		System.out.println();
	}
	
	public void setFillChar(char fillChar) {
		this.fillChar = fillChar;
	}
	
	public void setBorderChar(char borderChar) {
		this.borderChar = borderChar;
	}
	
	private boolean isEven(int x) {
		return (((x & 1) == 0 )?true:false);
	}
}

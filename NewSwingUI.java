import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.JFrame;

public class NewSwingUI implements UIContext {
	private Graphics graphics;
	private static NewSwingUI swingUI;

	private NewSwingUI() {
	}

	public static NewSwingUI getInstance() {
		if (swingUI == null) {
			swingUI = new NewSwingUI();
		}
		return swingUI;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void draw(Label label) {
		if (label.getStartingPoint() != null) {
			if (label.getText() != null) {
				graphics.drawString(label.getText(), (int) label.getStartingPoint().getX(),
						(int) label.getStartingPoint().getY());
			}
		}
		int length = graphics.getFontMetrics().stringWidth(label.getText());
		graphics.drawString("_", (int) label.getStartingPoint().getX() + length, (int) label.getStartingPoint().getY());
	}

	public void draw(Line line) {
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		if (line.getPoint1() != null) {
			i1 = Math.round((float) (line.getPoint1().getX()));
			i2 = Math.round((float) (line.getPoint1().getY()));
			if (line.getPoint2() != null) {
				i3 = Math.round((float) (line.getPoint2().getX()));
				i4 = Math.round((float) (line.getPoint2().getY()));
			} else {
				i3 = i1;
				i4 = i2;
			}
			graphics.drawLine(i1, i2, i3, i4);

		}
	}

	public void draw(Ellipse ellipse) {

		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		if (ellipse.getPoint1() != null) {
			i1 = Math.round((float) (ellipse.getPoint1().getX()));
			i2 = Math.round((float) (ellipse.getPoint1().getY()));
			if (ellipse.getPoint2() != null) {
				i3 = Math.round((float) (ellipse.getPoint2().getX()));
				i4 = Math.round((float) (ellipse.getPoint2().getY()));
			} else {
				i3 = i1;
				i4 = i2;
			}

			// System.out.println(i1+ " " + i2 +" " + i3+" " +i4 );
			int width = Math.abs(i3 - i1);
			int height = Math.abs(i4 - i2);

			if (i1 < i3 && i2 < i4) {
				graphics.drawOval(i1, i2, width, height);
			} else if (i1 < i3 && i2 > i4) {
				graphics.drawOval(i1, i4, width, height);
			} else if (i1 > i3 && i2 < i4) {

				graphics.drawOval(i3, i2, width, height);
			}

			else {
				graphics.drawOval(i3, i4, width, height);

			}

		}
	}

	public void draw(Polygon polygon) {

		ArrayList<Integer> listX = PolygonButton.listX;
		ArrayList<Integer> listY = PolygonButton.listY;
		try {
			Integer x1 = listX.get(0);
			Integer y1 = listY.get(0);

			Integer x2 = listX.get(1);
			Integer y2 = listY.get(1);

			graphics.drawLine(x1, y1, x2, y2);

			Integer size = listX.size();
			
			
			if (distance(x1, y1, x2, y2)>10){
				
			}
				
			for (Integer i=2; i <= size; i++){
				Integer tempX = listX.get(i);
				Integer tempY = listY.get(i);
				graphics.drawLine(x2, y2, tempX, tempY);
				x2 = tempX;
				y2 = tempY;
				
			}
			


			// graphics.drawLine(x1, y1, x2, y2);

		} 
		
		catch (Exception e) {

		}

		// System.out.println("here");

	}


	public void draw(Item item) {
		System.out.println("Cant draw unknown Item \n");
	}
	
	protected double distance(Integer x1, Integer y1, Integer x2, Integer y2) {
	    double xDifference = x2 - x1;
	    double yDifference = y2 - y1;
	    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference)));
	  }
}
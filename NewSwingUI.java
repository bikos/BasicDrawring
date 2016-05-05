import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

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

		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		int i5 = 0;
		int i6 = 0;
		if (polygon.getPoint1() != null) {
			i1 = Math.round((float) (polygon.getPoint1().getX()));
			i2 = Math.round((float) (polygon.getPoint1().getY()));
			if (polygon.getPoint2() != null) {
				i3 = Math.round((float) (polygon.getPoint2().getX()));
				i4 = Math.round((float) (polygon.getPoint2().getY()));
			}
			if (polygon.getPoint3() != null){
				i5 = Math.round((float) (polygon.getPoint2().getX()));
				i6 = Math.round((float) (polygon.getPoint2().getY()));
				
			}
			else {
				i3 = i1;
				i4 = i2;
			}
			
			
			graphics.drawLine(i1, i2, i3, i4);
			//graphics.drawLine(i3, i4, i5, i6);

		}

	}
	
	public void draw(Bspline bspline) {

		

	}

	public void draw(Item item) {
		System.out.println("Cant draw unknown Item \n");
	}
}
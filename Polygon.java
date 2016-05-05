import java.awt.*;

public class Polygon extends Item {
	private Point point1;
	private Point point2;
	private Point point3;

	public Polygon(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;

	}

	public Polygon(Point point1) {
		this.point1 = point1;
	}

	public Polygon() {
	}

	public boolean includes(Point point) {
		return ((distance(point, point1) < 10.0) || (distance(point, point2) < 10.0));
	}

	public void render() {
		uiContext.draw(this);
	}

	public void setPoint1(Point point) {
		point1 = point;
	}

	public void setPoint2(Point point) {
		point2 = point;
	}

	public void setPoint3(Point point) {
		point3 = point;
	}
	
	public Point getPoint1() {
		return point1;
	}

	public Point getPoint2() {
		return point2;
	}
	public Point getPoint3() {
		return point3;
	}

	
	public String toString() {
		return "Polygon  from " + point1 + " to " + point2;
	}
}


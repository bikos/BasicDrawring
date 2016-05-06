import java.awt.*;
import java.text.*;
public class PolygonCommand extends Command {
  private Polygon polygon;
  protected int pointCount;
  public PolygonCommand() {
    this(null, null);
    pointCount = 0;
  }
  public PolygonCommand(Point point) {
    this(point, null);
    pointCount = 1;
  }
  public PolygonCommand(Point point1, Point point2) {
    polygon = new Polygon(point1, point2);
    pointCount = 2;
  }
  public void setPolygonPoint(Point point) {
    if (++pointCount == 1) {
      polygon.setPoint1(point);
    } else if (pointCount == 2) {
      polygon.setPoint2(point);
    }
  }
  public void execute() {
    model.addItem(polygon);
  }
  public boolean undo() {
    model.removeItem(polygon);
    return true;
  }
  public boolean redo() {
    execute();
    return true;
  }
  public boolean end() {
    if (polygon.getPoint1() == null) {
      undo();
      return false;
    }
    if (polygon.getPoint2() == null) {
       polygon.setPoint2(polygon.getPoint1());
    }
    return true;
  }
}

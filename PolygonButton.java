import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class PolygonButton extends JButton implements ActionListener {
	public static ArrayList<Integer> listX = new ArrayList<Integer>();
	public static ArrayList<Integer> listY = new ArrayList<Integer>();
	public static boolean endPoly = false;
	protected JPanel drawingPanel;
	protected View view;
	private MouseHandler mouseHandler;
	private PolygonCommand polygonCommand;
	private UndoManager undoManager;

	public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
		super("Polygon");
		this.undoManager = undoManager;
		addActionListener(this);
		view = jFrame;
		drawingPanel = jPanel;
		mouseHandler = new MouseHandler();
	}

	public void actionPerformed(ActionEvent event) {
		view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		// Change cursor when button is clicked
		drawingPanel.addMouseListener(mouseHandler);
		// Start listening for mouseclicks on the drawing panel
	}

	private class MouseHandler extends MouseAdapter {

		public void mouseClicked(MouseEvent event) {
			{
				if (!endPoly) {
					polygonCommand = new PolygonCommand(View.mapPoint(event.getPoint()));

					int tempX = event.getPoint().x;
					int tempY = event.getPoint().y;
					listX.add(tempX);
					listY.add(tempY);
					//System.out.println(event.getClickCount());
					undoManager.beginCommand(polygonCommand);
				} else {
					endPoly = false;
					drawingPanel.removeMouseListener(this);
					view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					undoManager.endCommand(polygonCommand);
					// listX.clear();
					// listY.clear();

				}
			}
		}
	}
}

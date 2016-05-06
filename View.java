import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class View extends JFrame {
	private UIContext uiContext;
	private JPanel drawingPanel;
	private JPanel buttonPanel;
	private JPanel filePanel;
	private JButton lineButton;
	
	private JButton ellipse;
	private MoveButton moveButton;
	private JButton deleteButton;
	private JButton labelButton;
	private JButton selectButton;
	private JButton saveButton;
	private JButton openButton;
	private JButton undoButton;
	private JButton redoButton;
	private JButton btnEllipse;
	private static UndoManager undoManager;
	private String fileName;
	// other buttons to be added as needed;
	private static Model model;
	private JButton btnPolygon;

	public UIContext getUI() {
		return uiContext;
	}

	private void setUI(UIContext uiContext) {
		this.uiContext = uiContext;
	}

	public static void setModel(Model model) {
		View.model = model;
	}

	public static void setUndoManager(UndoManager undoManager) {
		View.undoManager = undoManager;
	}

	private class DrawingPanel extends JPanel {
		private MouseListener currentMouseListener;
		private KeyListener currentKeyListener;
		private FocusListener currentFocusListener;

		public DrawingPanel() {
			setLayout(null);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			(NewSwingUI.getInstance()).setGraphics(g);
			g.setColor(Color.BLUE);
			Enumeration enumeration = model.getItems();
			while (enumeration.hasMoreElements()) {
				((Item) enumeration.nextElement()).render();
			}
			g.setColor(Color.RED);
			enumeration = model.getSelectedItems();
			while (enumeration.hasMoreElements()) {
				((Item) enumeration.nextElement()).render();
			}
		}

		public void addMouseListener(MouseListener newListener) {
			removeMouseListener(currentMouseListener);
			currentMouseListener = newListener;
			super.addMouseListener(newListener);
		}

		public void addKeyListener(KeyListener newListener) {
			removeKeyListener(currentKeyListener);
			currentKeyListener = newListener;
			super.addKeyListener(newListener);
		}

		public void addFocusListener(FocusListener newListener) {
			removeFocusListener(currentFocusListener);
			currentFocusListener = newListener;
			super.addFocusListener(newListener);
		}
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		setTitle("Drawing Program 1.1  " + fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public View() {
		super("Drawing Program 1.1  Untitled");
		fileName = null;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		model.setUI(NewSwingUI.getInstance());
		drawingPanel = new DrawingPanel();
		buttonPanel = new JPanel();
		Container contentpane = getContentPane();
		contentpane.add(buttonPanel, "North");
		contentpane.add(drawingPanel);
		lineButton = new LineButton(undoManager, this, drawingPanel);
		labelButton = new LabelButton(undoManager, this, drawingPanel);
		selectButton = new SelectButton(undoManager, this, drawingPanel);
		moveButton = new MoveButton(undoManager, this, drawingPanel);
		// ellipseButton = new EllipseButton(undoManager, this, drawingPanel);
		deleteButton = new DeleteButton(undoManager);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveButton = new SaveButton(undoManager, this);
		openButton = new OpenButton(undoManager, this);
		
		undoButton = new UndoButton(undoManager);
		redoButton = new RedoButton(undoManager);

		buttonPanel.add(lineButton);
		buttonPanel.add(labelButton);
		buttonPanel.add(selectButton);
		

		//MoveButton = new JButton("Move");
		btnPolygon = new JButton("Polygon");
		btnEllipse = new JButton("Ellipse");
		btnEllipse = new EllipseButton(undoManager, this, drawingPanel);
		btnPolygon = new PolygonButton(undoManager, this, drawingPanel);
		//btnBspline = new Bspline();
		

		buttonPanel.add(btnEllipse);
		// btnBspline.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		//
		// model.setUI(NewSwingUI.getInstance());
		// drawingPanel = new DrawingPanel();
		// buttonPanel = new JPanel();
		// Container contentpane = getContentPane();
		// contentpane.add(buttonPanel, "North");
		// contentpane.add(drawingPanel);
		//
		// Bspline test = new Bspline();
		// PointMover mover = new PointMover(test);
		// test.addMouseListener(mover);
		// test.addMouseMotionListener(mover);
		//// //JFrame f = new JFrame();
		//// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//// f.add(test);
		//// f.add(test.getButtonPanel(), "Last");
		//// f.setSize(500,500);
		//// f.setLocation(200,200);
		//// f.setVisible(true);
		// }
		// });
		buttonPanel.add(btnPolygon);
		buttonPanel.add(deleteButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(openButton);
		buttonPanel.add(moveButton);
		buttonPanel.add(undoButton);
		buttonPanel.add(redoButton);
		this.setSize(700, 400);
	}

	public void refresh() {
		// code to access the Model update the contents of the drawing panel.
		drawingPanel.repaint();
	}

	public static Point mapPoint(Point point) {
		// maps a point on the drawing panel to a point
		// on the figure being created. Perhaps this
		// should be in drawing panel
		return point;
	}
}
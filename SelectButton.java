import javax.swing.*;
import java.awt.event.*;
public class SelectButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private MoveCommand moveCommand;
  private UndoManager undoManager;
  public SelectButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("Select");
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
    this.undoManager = undoManager;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    moveCommand = new MoveCommand();
    drawingPanel.addMouseListener(mouseHandler);
    undoManager.beginCommand(moveCommand);
  }
  private class MouseHandler extends MouseAdapter {
    public void mouseClicked(MouseEvent event) {
      moveCommand.setPoint(View.mapPoint(event.getPoint()));
      drawingPanel.removeMouseListener(this);
      undoManager.endCommand(moveCommand);
    }
  }
}

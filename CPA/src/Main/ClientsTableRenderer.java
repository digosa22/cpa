package Main;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;


class ClientsTableButtonRenderer extends JButton implements TableCellRenderer
  {
    public ClientsTableButtonRenderer()
    {
      setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
      setForeground(Color.black);
      setBackground(UIManager.getColor("Button.background"));
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }
  public class ClientsTableRenderer extends DefaultCellEditor
  {
    private JButton button;
    private String label;
    private boolean clicked;
    private int row, col;
    private JTable table;

    private String[] imgs;
    private JDialog padre;
    private int accion;
    
    public ClientsTableRenderer(JCheckBox checkBox, String[] imgs, JDialog padre, int accion)
    {
      super(checkBox);
      
      this.padre = padre;
      this.imgs = imgs;
      this.accion = accion;
      button = new JButton();
      button.setOpaque(true);
      button.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          fireEditingStopped();
        }
      });
    }
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
      this.table = table;
      this.row = row;
      this.col = column;

      button.setForeground(Color.black);
      button.setBackground(UIManager.getColor("Button.background"));
      label = (value == null) ? "" : value.toString();
      button.setText(label);
      clicked = true;
      return button;
    }
    public Object getCellEditorValue()
    {
      if (clicked)
      {
//        JOptionPane.showMessageDialog(button, "Click en la fila: "+imgs[row]);
//    	  imgs[row] = "http://cdn.revistagq.com/uploads/images/thumbs/201430/james_rodriguez_real_madrid_cristiano_ronaldo_7238_200x200.jpg#;#http://www.fotosdecamiones.com/wp-content/uploads/2015/07/camion-bordo.jpg";
    	  
    	  new VentanaImagenes(padre, imgs[row], row, table.getValueAt(row, 0).toString(), accion).setVisible(true);
      }
      clicked = false;
      return new String(label);
    }

    public boolean stopCellEditing()
    {
      clicked = false;
      return super.stopCellEditing();
    }

    protected void fireEditingStopped()
    {
      super.fireEditingStopped();
    }
  }

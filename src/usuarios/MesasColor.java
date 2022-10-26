
package usuarios;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JToggleButton;

public class MesasColor extends JToggleButton{
    
    //metodo para cambiar el color de las mesas al seleccionarla.
    public void paintComponent(Graphics g){
	Color bg,text;
	if(isSelected()){
		bg = Color.decode("#FF6347");
		text = Color.decode("#7FFF00");
	} else{
		bg = Color.decode("#7FFF00");
		text = Color.decode("#FF6347");
	}

	setBackground(bg);
	setForeground(text);
	super.paintComponent(g);
}
}

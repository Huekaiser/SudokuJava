package clasesPropias.Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import clasesPropias.Dominio.Clases.Tablero;
import clasesPropias.Dominio.Controladores.ControladorCreacionTablero;
import clasesPropias.Presentacion.Controladores.ControladorPresentacion;

public class TableroCreacion9 extends SuperTableroCreacion{

	public TableroCreacion9(final int posx, final int posy, final int width, final int height, String str, Tablero t){
		setBounds(posx,posy,width,height);
		//Set dif
		lblDif.setText(lblDif.getText() + " " + str);
		//Creaci�n celdas
		ArrayList<JTextField> celdas = new ArrayList<JTextField>();
		panelJuego.setLayout(new GridLayout(9, 0, 0, 0));
		int regionBorder = 4;
		for(int i = 0; i < 9*9; ++i){
			JTextField tmp = new JTextField();
			if (t.getValorTauler(i/9, i%9) != 0) tmp.setText(String.valueOf(t.getValorTauler(i/9, i%9)));
			tmp.setPreferredSize(new Dimension(30,30));
			tmp.setHorizontalAlignment(JTextField.CENTER);
			tmp.setEditable(false);
			tmp.setFont(new Font("Verdana", Font.BOLD, 12));
			tmp.setBackground(Color.WHITE);
			//Calculo borde
			Double x = getBounds().getX();
			Double y = getBounds().getY();
			Double w = getBounds().getWidth();
			Double h = getBounds().getHeight();
			if((i%27 >= 18 && i %27 <=26)  && i < 72){
				if((i % 3 == 2 && i % 9 != 8)){
					tmp.setBorder(new MatteBorder(1, 1, regionBorder, regionBorder, (Color) new Color(0, 0, 0)));
				}else
					tmp.setBorder(new MatteBorder(1, 1, regionBorder, 1, (Color) new Color(0, 0, 0)));
			}else if(i % 3 == 2 && i % 9 != 8){
				tmp.setBorder(new MatteBorder(1, 1, 1, regionBorder, (Color) new Color(0, 0, 0)));
			}else{
				tmp.setBorder(new LineBorder(new Color(0, 0, 0), 1));
			}
			celdas.add(tmp);
			panelJuego.add(celdas.get(i));
			Jresolver.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					for (int j = 0; j < 9; j++) {
						for (int k = 0; k < 9; k++) {
							if(celdas.get(j*9+k).getText().equals("")){
								celdas.get(j*9+k).setText(t.getPosicionMatrizSolucion(j, k).toString());
								celdas.get(j*9+k).setFont(new Font("Verdana", Font.PLAIN, 12));
								celdas.get(j*9+k).setForeground(Color.BLUE);
							}
						}
					}

				}
			});
			
			btnLogout.removeActionListener(btnLogout.getActionListeners()[0]);
			btnLogout.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ControladorCreacionTablero cct= new ControladorCreacionTablero();
					Integer[] id= new Integer[1];
					
					try {
						cct.guardarTablero(t, id);
						System.out.println("voy a leer el: " + id[0]);
						String diff;
						System.out.println("el puto talbero es de dificultad :" + t.getDificultad());
						if(t.getDificultad()==1) diff="Facil";
						else if (t.getDificultad() ==2) diff = "Medio";
						else diff = "Dificil";
						setVisible(false);
						ControladorPresentacion.Visible9TableroJuego(x.intValue(),y.intValue(),w.intValue(),h.intValue(),diff, false,  " ",id[0]);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
	}
}

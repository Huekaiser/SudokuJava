package clasesPropias.Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import clasesPropias.Presentacion.Controladores.ControladorPresentacion;

abstract class SuperTableroJuego extends SuperTablero {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JMenuItem menuGuardar;
	protected JMenuItem menuAyuda;
	protected long time= 0;
	protected Timer JTime;
	protected JLabel label;
	/**
	 * Create the panel.
	 */
	/**
	 * Create the panel.
	 */

	public SuperTableroJuego() {

		//Parte Inferior
		int timertic=1000;
		JPanel BottomJPanel = new JPanel();
		BottomJPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout_1 = (FlowLayout) BottomJPanel.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		this.BottomJPanel.add(BottomJPanel, BorderLayout.SOUTH);
		this.BottomJPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		BottomJPanel.add(panel);

		JLabel lblTime = new JLabel("TIME:");
		panel.add(lblTime);

		label = new JLabel("00:00:00");
		panel.add(label);		

		//Menu
		JMenu menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);
		menuGuardar = new JMenuItem("Guardar");
		menuArchivo.add(menuGuardar);
		menuAyuda = new JMenuItem("Ayuda");
		menuBar.add(menuAyuda);

		JTime = new Timer(timertic, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				++time;
				String segundos = String.format("%02d", time %60);
				String horas = String.format("%02d",time /(3600));
				String minutos = String.format("%02d",(time /60)%60);
				label.setText(horas+":"+minutos+":"+segundos);
			}		
		});
		JTime.setInitialDelay(0);


		btnLogout.setVisible(false);
		backListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				Double x = getBounds().getX();
				Double y = getBounds().getY();
				Double w = getBounds().getWidth();
				Double h = getBounds().getHeight();
				ControladorPresentacion.VisibleMenu(x.intValue(),y.intValue(),w.intValue(),h.intValue());
			}
		});
	}

}

package clasesPropias.Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import clasesPropias.Presentacion.Controladores.ControladorPresentacion;

abstract class SuperTableroCreacion extends SuperTablero {

	private static final long serialVersionUID = 1L;
	protected JLabel lblDif;
	protected JButton Jresolver;

	public SuperTableroCreacion() {
		//Set Label
		setTitle(getTitle()+ " - Previsualizacion");
		//Parte Inferior
		JPanel BottomJPanel = new JPanel();
		BottomJPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout_1 = (FlowLayout) BottomJPanel.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		this.BottomJPanel.add(BottomJPanel, BorderLayout.CENTER);
		this.BottomJPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		FlowLayout layonhands = new FlowLayout();
		layonhands.setVgap(0);
		layonhands.setHgap(0);
		layonhands.setAlignment(FlowLayout.CENTER);
		this.BottomJPanel.setLayout(layonhands);
		JPanel panel = new JPanel();
		//panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		BottomJPanel.add(panel);
		
		JLabel lblMsg = new JLabel("Tablero Creado Satisfactoriamente");
		panel.add(lblMsg);
		
		lblDif = new JLabel("Dificultad:");
		panel.add(lblDif);		
				
		//Botones abajo del todo
		btnSalir.setText("Salir sin guardar");
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

		btnLogout.setText("Guardar y Jugar");
		Jresolver = new JButton();
		Jresolver.setText("Resolver");
		panelAuxMedio.add(Jresolver);
		}
}

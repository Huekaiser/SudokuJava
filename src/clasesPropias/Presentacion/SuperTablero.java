package clasesPropias.Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

abstract class SuperTablero extends SuperJFrame {
	protected JPanel BottomJPanel;
	protected JPanel panelJuego;
	public SuperTablero() {		
		JPanel panelf = new JPanel();
		panelf.setLayout(new BorderLayout(0, 0));
		setBounds(100,100,550,600);
		menuPanel.setLayout(new BorderLayout(0, 0));
		menuPanel.add(panelf);
		
		JPanel panel_1 = new JPanel();
		menuPanel.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0};
		gbl_panel_1.rowHeights = new int[] {0};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{1.0};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelJuego = new JPanel();
		panelJuego.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_2.add(panelJuego);
		
		
		//Parte Inferior
		BottomJPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) BottomJPanel.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		menuPanel.add(BottomJPanel, BorderLayout.SOUTH);
	}

}

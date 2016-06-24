package clasesPropias.Presentacion;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import clasesComunes.Usuario_comp;
import clasesPropias.Dominio.Clases.Partida;
import clasesPropias.Persistencia.GestorFicherosPartidas;
import clasesPropias.Presentacion.Controladores.ControladorPresentacion;

public class CargarSudoku extends SuperJFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	/**
	 * Create the frame.
	 */
	public CargarSudoku(int posx, int posy, int width, int height) {
		setBounds(posx, posy, width, height);
		contentPane = new JPanel();
		menuPanel.add(contentPane,BorderLayout.SOUTH);
		setTitle(getTitle() + " - Cargar Sudoku");
		btnLogout.setVisible(false);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0};
		gbl_contentPane.rowHeights = new int[] {0};
		gbl_contentPane.columnWeights = new double[]{0.0};
		gbl_contentPane.rowWeights = new double[]{0.0};
		menuPanel.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0, 0};
		gbl_panel.columnWeights = new double[]{0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		comboBox = new JComboBox<String>();
		comboBox.setSize(30,250);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		//Cargar partidas
		GestorFicherosPartidas cfp = new GestorFicherosPartidas();
		ArrayList<String> partidas = cfp.mostrarPartidas(ControladorPresentacion.getUser());
		for(int i = 0; i < partidas.size(); ++i){
			comboBox.addItem(partidas.get(i));
		}
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		GridBagConstraints gbc_btnSeleccionar = new GridBagConstraints();
		gbc_btnSeleccionar.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeleccionar.gridx = 0;
		gbc_btnSeleccionar.gridy = 1;
		panel.add(btnSeleccionar, gbc_btnSeleccionar);
		if(comboBox.getItemCount() == 0){
			btnSeleccionar.setEnabled(false);
		}
		btnSeleccionar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Double x = getBounds().getX();
				Double y = getBounds().getY();
				Double w = getBounds().getWidth();
				Double h = getBounds().getHeight();
				String nameFile = (String) comboBox.getSelectedItem();
				int kaka=0;
				GestorFicherosPartidas gfp= new GestorFicherosPartidas();
				Partida p = new Partida();
				try {
					p=gfp.cargarPartida(new Usuario_comp(ControladorPresentacion.getUser(),""), nameFile);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(p.getTablero().getMida()==9)
				ControladorPresentacion.Visible9TableroJuego(x.intValue(),y.intValue(),w.intValue(),h.intValue()," ", true,  nameFile, kaka );
				else ControladorPresentacion.Visible16TableroJuego(x.intValue(),y.intValue(),w.intValue(),h.intValue()," ", true,  nameFile, kaka );
			}
		});
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

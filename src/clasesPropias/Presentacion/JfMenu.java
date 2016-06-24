package clasesPropias.Presentacion;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import clasesPropias.Presentacion.Controladores.ControladorPresentacion;
import clasesPropias.Presentacion.Controladores.ControladorUsuario;

public class JfMenu extends SuperJFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public JfMenu(int posx, int posy, int width, int height) {
		btnSalir.setVisible(false);
		setTitle(getTitle()+" - Menu");
		setBounds(posx,posy,width,height);
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[] {0};
		gridBagLayout_1.rowHeights = new int[] {0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout_1.rowWeights = new double[]{0.0};
		menuPanel.setLayout(gridBagLayout_1);
		//Contenido
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		menuPanel.add(panel_1, gbc_panel_1);
		
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setLayout(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_1.setLayout(gridBagLayout);
		
		JButton button = new JButton("Cargar Partida");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		panel_1.add(button, gbc_button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double x = getBounds().getX();
				Double y = getBounds().getY();
				Double w = getBounds().getWidth();
				Double h = getBounds().getHeight();
				if(!ControladorPresentacion.VisibleCargarSudoku(x.intValue(),y.intValue(),w.intValue(),h.intValue())){
					setVisible(false);
				}
			}
		});
		
		
		JButton btnJugar = new JButton("Jugar Partida");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.BOTH;
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 0;
		panel_1.add(btnJugar, gbc_button_1);
		
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Double x = getBounds().getX();
				Double y = getBounds().getY();
				Double w = getBounds().getWidth();
				Double h = getBounds().getHeight();
				ControladorPresentacion.VisibleOpcionesJugar(x.intValue(),y.intValue(),w.intValue(),h.intValue());
			}
		});
		
		JButton btnCrear = new JButton("Crear Partida");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.fill = GridBagConstraints.BOTH;
		gbc_btnCrear.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrear.gridx = 0;
		gbc_btnCrear.gridy = 1;
		panel_1.add(btnCrear, gbc_btnCrear);
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Double x = getBounds().getX();
				Double y = getBounds().getY();
				Double w = getBounds().getWidth();
				Double h = getBounds().getHeight();
				ControladorPresentacion.VisibleOpcionesCrear(x.intValue(),y.intValue(),w.intValue(),h.intValue());
			}
		});
		JButton btnRank = new JButton("Ranking");
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.fill = GridBagConstraints.BOTH;
		gbc_button_3.insets = new Insets(0, 0, 5, 0);
		gbc_button_3.gridx = 1;
		gbc_button_3.gridy = 1;
		panel_1.add(btnRank, gbc_button_3);
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Double x = getBounds().getX();
				Double y = getBounds().getY();
				Double w = getBounds().getWidth();
				Double h = getBounds().getHeight();
				ControladorPresentacion.VisibleRanking(x.intValue(),y.intValue(),w.intValue(),h.intValue());
			}
		});
		
		JButton btnDelUsr = new JButton("Borrar Usuario");
		GridBagConstraints gbc_btnDelUsr = new GridBagConstraints();
		gbc_btnDelUsr.anchor = GridBagConstraints.NORTH;
		gbc_btnDelUsr.gridwidth = 2;
		gbc_btnDelUsr.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelUsr.gridx = 0;
		gbc_btnDelUsr.gridy = 2;
		panel_1.add(btnDelUsr, gbc_btnDelUsr);
		btnDelUsr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ControladorUsuario cu = new ControladorUsuario();
				String pass = JOptionPane.showInputDialog("Di tu contrasena","");
				if(pass != null){
					if(cu.borrarUsuario(ControladorPresentacion.getUser(),pass)){
						setVisible(false);
						Double x = getBounds().getX();
						Double y = getBounds().getY();
						Double w = getBounds().getWidth();
						Double h = getBounds().getHeight();
						ControladorPresentacion.VisibleLogin(x.intValue(),y.intValue(),w.intValue(),h.intValue());
					}
				}
			}
		});
		
	}

}

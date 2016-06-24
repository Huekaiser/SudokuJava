package clasesPropias.Presentacion;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import clasesPropias.Presentacion.Controladores.ControladorPresentacion;
import clasesPropias.Presentacion.Controladores.ControladorUsuario;

public class JfLogin extends SuperJFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int posx;
	private static int posy;
	private static int width;
	private static int height;
	private JTextField usuario;
	private JPasswordField contrasena;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JfLogin frame = new JfLogin(100, 100, 450, 300);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JfLogin(final int posx, final int posy, final int width, final int height) {
		//Ocutar panelAux
		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.height = height;
		setBottomVisibility(false);

		//SetLabelLogin
		setTitle(getTitle()+" - Login");
		setBounds(posx, posy, width, height);
		menuPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		menuPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0};
		gbl_contentPane.rowHeights = new int[] {0};
		gbl_contentPane.columnWeights = new double[]{1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0};
		menuPanel.setLayout(gbl_contentPane);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		menuPanel.add(panel_1, gbc_panel_1);

		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{86, 86, 0};
		gbl_panel.rowHeights = new int[]{23, 23, 23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel label = new JLabel("Usuario");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);

		usuario = new JTextField();
		usuario.setColumns(10);
		GridBagConstraints gbc_usuario = new GridBagConstraints();
		gbc_usuario.fill = GridBagConstraints.BOTH;
		gbc_usuario.insets = new Insets(0, 0, 5, 0);
		gbc_usuario.gridx = 1;
		gbc_usuario.gridy = 0;
		panel.add(usuario, gbc_usuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.fill = GridBagConstraints.BOTH;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 1;
		panel.add(lblContrasea, gbc_lblContrasea);

		contrasena = new JPasswordField();
		contrasena.setColumns(10);
		GridBagConstraints gbc_contrasena = new GridBagConstraints();
		gbc_contrasena.fill = GridBagConstraints.BOTH;
		gbc_contrasena.insets = new Insets(0, 0, 5, 0);
		gbc_contrasena.gridx = 1;
		gbc_contrasena.gridy = 1;
		panel.add(contrasena, gbc_contrasena);

		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.BOTH;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 2;
		panel.add(btnLogin, gbc_btnLogin);
		//BotonLogin
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorUsuario cu = new ControladorUsuario();
				String User = usuario.getText();
				String Pass = new String(contrasena.getPassword());
				//System.out.println(User);
				//System.out.println(Pass);
				if (!User.equals("") && !Pass.equals("")){
					//System.out.println("holaaa");
					try {
						if(cu.uilogin(User,Pass)){
							setVisible(false);
							Double x = getBounds().getX();
							Double y = getBounds().getY();
							Double w = getBounds().getWidth();
							Double h = getBounds().getHeight();
							ControladorPresentacion.VisibleMenu(x.intValue(),y.intValue(),w.intValue(),h.intValue());
							ControladorPresentacion.setUser(User);
						}
						else JOptionPane.showMessageDialog(null, "Combinacion nombre de usuario/contrase\u00F1a no valida");
					} catch (HeadlessException | ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Combinacion nombre de usuario/contrase\u00F1a no valida");
				}
					
			}});


		JButton btnReg = new JButton("Registro");
		GridBagConstraints gbc_btnReg = new GridBagConstraints();
		gbc_btnReg.fill = GridBagConstraints.BOTH;
		gbc_btnReg.gridx = 1;
		gbc_btnReg.gridy = 2;
		panel.add(btnReg, gbc_btnReg);
		//Registrar
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorUsuario cu = new ControladorUsuario();
				String User = usuario.getText();
				String Pass = new String(contrasena.getPassword());
				try {
					if(cu.uiregister(User,Pass)){
						JOptionPane.showMessageDialog(null, "El Usuario se ha creado satisfactoriamente");
						setVisible(false);
						Double x = getBounds().getX();
						Double y = getBounds().getY();
						Double w = getBounds().getWidth();
						Double h = getBounds().getHeight();
						ControladorPresentacion.VisibleMenu(x.intValue(),y.intValue(),w.intValue(),h.intValue());
						ControladorPresentacion.setUser(User);
					}else
						JOptionPane.showMessageDialog(null, "Usuario ya existente");
				} catch (HeadlessException | IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error, no se ha podido acceder al fichero");
				}

			}
		});

		}
	}
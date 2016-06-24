package clasesPropias.Presentacion.Controladores;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import clasesPropias.Persistencia.GestorFicherosPartidas;
import clasesPropias.Presentacion.CargarSudoku;
import clasesPropias.Presentacion.JfLogin;
import clasesPropias.Presentacion.JfMenu;
import clasesPropias.Presentacion.Popupcreacion;
import clasesPropias.Presentacion.Ranking;
import clasesPropias.Presentacion.TableroJuego16;
import clasesPropias.Presentacion.TableroJuego9;
import clasesPropias.Presentacion.opcionesPartida;
import clasesPropias.Presentacion.top5;

public class ControladorPresentacion {
	private static int posx;
	private static int posy;
	private static int width;
	private static int height;
	private static String usr;
	private static boolean b;
	public ControladorPresentacion(){
		posx = 100;
		posy = 100;
		width = 650;
		height = 650;
		VisibleLogin(posx,posy,width,height);
	
	}
	public static void VisibleLogin(final int posx, final int posy, final int width, final int height){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JfLogin frame = new JfLogin(posx, posy, width, height);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void VisibleMenu(final int posx, final int posy, final int width, final int height){
		ControladorPresentacion.posx = posx;
		ControladorPresentacion.posy = posy;
		ControladorPresentacion.width = width;
		ControladorPresentacion.height = height;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JfMenu frame = new JfMenu(posx,posy,width,height);		
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void VisibleRanking(final int posx, final int posy, final int width, final int height){
		ControladorPresentacion.posx = posx;
		ControladorPresentacion.posy = posy;
		ControladorPresentacion.width = width;
		ControladorPresentacion.height = height;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ranking frame = new Ranking(posx,posy,width,height);		
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void VisibleOpcionesJugar(final int posx, final int posy, final int width, final int height){
		ControladorPresentacion.posx = posx;
		ControladorPresentacion.posy = posy;
		ControladorPresentacion.width = width;
		ControladorPresentacion.height = height;
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try {
					opcionesPartida frame = new opcionesPartida(posx,posy,width,height);		
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void VisibleOpcionesCrear(final int posx, final int posy, final int width, final int height){
		ControladorPresentacion.posx = posx;
		ControladorPresentacion.posy = posy;
		ControladorPresentacion.width = width;
		ControladorPresentacion.height = height;
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try {
					Popupcreacion frame = new Popupcreacion(posx,posy,width,height);		
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public static void Visible9TableroJuego(final int posx, final int posy, final int width, final int height, final String dificultad, final boolean crear, final String nomP, Integer id){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroJuego9 frame = new TableroJuego9(posx,posy,width,height,dificultad,crear,nomP,id);		
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void Visible16TableroJuego(final int posx, final int posy, final int width, final int height, final String dificultad, final boolean crear, final String nomP,Integer id){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroJuego16 frame = new TableroJuego16(posx,posy,width,height, dificultad,crear,nomP,id);	
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static boolean VisibleCargarSudoku(final int posx, final int posy, final int width, final int height){
		b = true;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GestorFicherosPartidas gfp = new GestorFicherosPartidas();
				if(gfp.mostrarPartidas(usr).size() != 0){
				try {
					CargarSudoku frame = new CargarSudoku(posx,posy,width,height);		
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				}else{
					JOptionPane.showMessageDialog(null, "No tienes ninguna partida gurardada", "Error", JOptionPane.ERROR_MESSAGE);
					b = false;
				}
			}
		});
		return b;
	}
	public static void VisibleTop5(final int posx, final int posy, final int width, final int height,final String[][]s, long time){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					top5 frame = new top5(posx,posy,width,height,s,(int) time);	
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void setUser(String user){
		usr = user;
	}
	public static String getUser(){
		return usr;
	}
	 
}

package gui;

import java.awt.Color;
import javax.swing.*;

public class Ventana extends JFrame{

	/**
	 * 
	 */
	private JPanel panelPrincipal;
	
	/**
	 * 
	 */
	private JButton btAceptar;
	
	/**
	 * 
	 */
	private JButton btCancelar;
	
	private JLabel lbLetrero;
	
	/**
	 * 
	 */
	private JTextField tfTexto;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ventana() {
		
		this.setTitle("Primera Ventana");
		this.setBounds(100,100,450,300);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.blue);
		this.setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btAceptar = new JButton();
		btAceptar.setText("Aceptar");
		btAceptar.setBackground(Color.green);
		btAceptar.setBounds(120,220,100,30);
		
		
		btCancelar = new JButton();
		btCancelar.setText("Cancelar");
		btCancelar.setBackground(Color.red);
		btCancelar.setBounds(230,220,100,30);
		
		lbLetrero = new JLabel();
		lbLetrero.setText("Introduzca su nombre:");
		
		
		tfTexto = new JTextField();
		tfTexto.setBounds(123,123,1231,233);
		
		panelPrincipal.add(btAceptar);
		panelPrincipal.add(btCancelar);
		panelPrincipal.add(btCancelar);
		
	}

	public static void main(String[] args) {
		Ventana v = new Ventana();
		v.setVisible(true);
		
	}

}

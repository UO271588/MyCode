package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Articulo;
import logic.Carta;
import logic.Pedido;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Eloy Alfredo Schmidt Rguez UO271588
 * @version 1.0.0
 *
 */

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblLogotitle;
	private JLabel lblArticulos;
	private JLabel lblUnidades;
	private JComboBox<Articulo> comboBox;
	private JSpinner spinner;
	private JButton btnAadir;
	private JButton btnCancelar;
	private JButton btnSiguiente;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private Carta carta;
	private Pedido pedido;
	private JLabel lblDescuento;
	private JLabel lblUnidades_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		
		carta = new Carta();
		pedido = new Pedido();
		
		setTitle("McDonald's - Espa\u00F1a");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblLogo());
		contentPane.add(getLblLogotitle());
		contentPane.add(getLblArticulos());
		contentPane.add(getLblUnidades());
		contentPane.add(getComboBox());
		contentPane.add(getSpinner());
		contentPane.add(getBtnAadir());
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnSiguiente());
		contentPane.add(getLblPrecio());
		contentPane.add(getTxtPrecio());
		contentPane.add(getLblDescuento());
		contentPane.add(getLblUnidades_1());
	}
	
	private void inicializar() {
		pedido.inicializar();
		getTxtPrecio().setText("");
		getComboBox().setSelectedIndex(0);
		getSpinner().setValue(1);
		getBtnSiguiente().setEnabled(false);
		getLblDescuento().setVisible(false);
		for(int i=0; i<getComboBox().getItemCount(); i++) {
			getComboBox().getItemAt(i).setUnidades(0);;
		}
	}
	
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.PNG")));
			lblLogo.setBounds(71, 13, 204, 140);
		}
		return lblLogo;
	}
	private JLabel getLblLogotitle() {
		if (lblLogotitle == null) {
			lblLogotitle = new JLabel("McDonald's");
			lblLogotitle.setFont(new Font("Arial Black", Font.BOLD, 32));
			lblLogotitle.setBounds(287, 63, 293, 57);
		}
		return lblLogotitle;
	}
	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Articulos:");
			lblArticulos.setDisplayedMnemonic('r');
			lblArticulos.setLabelFor(getComboBox());
			lblArticulos.setBounds(33, 177, 87, 16);
		}
		return lblArticulos;
	}
	private JLabel getLblUnidades() {
		if (lblUnidades == null) {
			lblUnidades = new JLabel("Unidades:");
			lblUnidades.setDisplayedMnemonic('U');
			lblUnidades.setLabelFor(getSpinner());
			lblUnidades.setBounds(460, 177, 132, 16);
		}
		return lblUnidades;
	}
	private JComboBox<Articulo> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Articulo>();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getSpinner().setValue(1);
				}
			});
			comboBox.setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulos()));
			comboBox.setBounds(33, 206, 386, 22);
		}
		return comboBox;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spinner.setBounds(460, 206, 52, 22);
		}
		return spinner;
	}
	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
			btnAadir.setMnemonic('A');
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Articulo articulo=(Articulo)getComboBox().getSelectedItem();
					pedido.add(articulo,(int) getSpinner().getValue());
					articulo.setUnidades((int)getSpinner().getValue()+articulo.getUnidades());
					getTxtPrecio().setText(String.format("%.2f €",pedido.calcularTotal()));
					if(pedido.getDescuento()) {
						getLblDescuento().setVisible(true);
					}
					if(!getBtnSiguiente().isEnabled()) {
						getBtnSiguiente().setEnabled(true);
					}
				}
			});
			btnAadir.setBackground(Color.GREEN);
			btnAadir.setBounds(526, 205, 97, 25);
		}
		return btnAadir;
	}
	

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setMnemonic('C');
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			btnCancelar.setBackground(Color.RED);
			btnCancelar.setBounds(555, 377, 97, 25);
		}
		return btnCancelar;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setMnemonic('S');
			btnSiguiente.setEnabled(false);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaRegistro frame = new VentanaRegistro(pedido);
					frame.setVisible(true);
				}
			});
			btnSiguiente.setBackground(Color.GREEN);
			btnSiguiente.setBounds(446, 377, 97, 25);
		}
		return btnSiguiente;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio pedido:");
			lblPrecio.setBounds(460, 241, 97, 16);
		}
		return lblPrecio;
	}
	private JTextField getTxtPrecio() {
		if (txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrecio.setText("");
			txtPrecio.setEditable(false);
			txtPrecio.setBounds(460, 270, 79, 22);
			txtPrecio.setColumns(10);
		}
		return txtPrecio;
	}
	private JLabel getLblDescuento() {
		if (lblDescuento == null) {
			lblDescuento = new JLabel("Descuento del 10%");
			lblDescuento.setForeground(Color.RED);
			lblDescuento.setBounds(460, 305, 124, 16);
			lblDescuento.setVisible(false);
		}
		return lblDescuento;
	}
	private JLabel getLblUnidades_1() {
		if (lblUnidades_1 == null) {
			lblUnidades_1 = new JLabel("");
			lblUnidades_1.setBounds(33, 241, 56, 16);
		}
		return lblUnidades_1;
	}
}

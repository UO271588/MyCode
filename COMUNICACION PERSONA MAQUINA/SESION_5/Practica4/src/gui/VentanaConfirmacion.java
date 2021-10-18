package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;

import logic.FileUtil;
import logic.Pedido;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Eloy Alfredo Schmidt Rguez UO271588
 * @version 1.0.0
 *
 */
public class VentanaConfirmacion extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblIcono;
	private JLabel lblAviso;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JButton btnFinalizar;
	private Pedido pedido;
	private JLabel lblPrecioPedido;
	private JTextField txtPrecio;
	private VentanaRegistro ventReg;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public VentanaConfirmacion() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setTitle("McDonald - Confirmacion de pedido");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacion.class.getResource("/img/logo.PNG")));
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 543, 346);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().add(getLblIcono());
		getContentPane().add(getLblAviso());
		getContentPane().add(getLblCodigo());
		getContentPane().add(getTxtCodigo());
		getContentPane().add(getBtnFinalizar());
		getContentPane().add(getLblPrecioPedido());
		getContentPane().add(getTxtPrecio());

	}
	
	/**
	 * Create the dialog.
	 */
	public VentanaConfirmacion(Pedido pedido, VentanaRegistro ventReg) {
		this.pedido = pedido;
		this.ventReg = ventReg;
		setResizable(false);
		setTitle("McDonald - Confirmacion de pedido");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacion.class.getResource("/img/logo.PNG")));
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 543, 346);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().add(getLblIcono());
		getContentPane().add(getLblAviso());
		getContentPane().add(getLblCodigo());
		getContentPane().add(getTxtCodigo());
		getContentPane().add(getBtnFinalizar());
		getContentPane().add(getLblPrecioPedido());
		getContentPane().add(getTxtPrecio());

	}
	private JLabel getLblIcono() {
		if (lblIcono == null) {
			lblIcono = new JLabel("Estamos procesando su pedido");
			lblIcono.setFont(new Font("Arial Black", Font.BOLD, 18));
			lblIcono.setIcon(new ImageIcon(VentanaConfirmacion.class.getResource("/img/ok.png")));
			lblIcono.setBounds(62, 72, 49, 78);
		}
		return lblIcono;
	}
	private JLabel getLblAviso() {
		if (lblAviso == null) {
			lblAviso = new JLabel("Estamos procesando su pedido");
			lblAviso.setFont(new Font("Arial Black", Font.BOLD, 20));
			lblAviso.setBounds(123, 88, 390, 45);
		}
		return lblAviso;
	}
	private JLabel getLblCodigo() {
		if (lblCodigo == null) {
			lblCodigo = new JLabel("El c\u00F3digo de recogida es:");
			lblCodigo.setBounds(96, 163, 150, 16);
		}
		return lblCodigo;
	}
	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			txtCodigo.setFont(new Font("Arial Black", Font.PLAIN, 15));
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(258, 157, 133, 25);
			txtCodigo.setColumns(10);
			txtCodigo.setText(FileUtil.setFileName());
		}
		return txtCodigo;
	}
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pedido.grabarPedido(getTxtCodigo().getText());
					ventReg.getVentPrin().inicializar();
					ventReg.dispose();
					dispose();
				}
			});
			btnFinalizar.setMnemonic('F');
			btnFinalizar.setBackground(Color.GREEN);
			btnFinalizar.setBounds(361, 273, 97, 25);
		}
		return btnFinalizar;
	}
	private JLabel getLblPrecioPedido() {
		if (lblPrecioPedido == null) {
			lblPrecioPedido = new JLabel("Precio del pedido:");
			lblPrecioPedido.setBounds(96, 198, 112, 16);
		}
		return lblPrecioPedido;
	}
	private JTextField getTxtPrecio() {
		if (txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setEditable(false);
			txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrecio.setFont(new Font("Arial Black", Font.PLAIN, 15));
			txtPrecio.setBounds(220, 192, 116, 25);
			txtPrecio.setVisible(true);
			txtPrecio.setColumns(10);
			txtPrecio.setText(String.format("%.2f €", pedido.calcularTotal()));
		}
		return txtPrecio;
	}
}

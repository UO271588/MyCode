package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logic.Pedido;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

import javax.swing.ButtonGroup;


/**
 * 
 * @author Eloy Alfredo Schmidt Rguez UO271588
 * @version 1.0.0
 *
 */

public class VentanaRegistro extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSiguiente;
	private JButton btnCancelar;
	private JPanel cpDatosCliente;
	private JTextField tfNombreApellidos;
	private JLabel lblNombreApellidos;
	private JPasswordField pwdPassword;
	private JComboBox<String> cbAño;
	private JLabel lblAño;
	private JPasswordField pwdReintroduzcaPassword;
	private JLabel lblPassword;
	private JLabel lblReintroduzcaPassword;
	private JPanel cpPedido;
	private JRadioButton rdbtnLocal;
	private JRadioButton rdbtnLlevar;
	private JLabel lblIncorrectPassword;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Pedido pedido;

	/**
	 * Create the frame.
	 */
	public VentanaRegistro() {
		setModal(true);
		setResizable(false);
		setTitle("McDonald's - Datos del Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 346);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnSiguiente());
		contentPane.add(getBtnCancelar());
		contentPane.add(getCpDatosCliente());
		contentPane.add(getCpPedido());
	}
	
	/**
	 * Create the frame.
	 */
	public VentanaRegistro(Pedido pedido) {
		
		this.pedido = pedido; 
		setModal(true);
		setResizable(false);
		setTitle("McDonald's - Datos del Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 346);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnSiguiente());
		contentPane.add(getBtnCancelar());
		contentPane.add(getCpDatosCliente());
		contentPane.add(getCpPedido());
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setMnemonic('S');
			btnSiguiente.setBackground(Color.GREEN);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (checkSiguiente()) {
						VentanaConfirmacion frame = new VentanaConfirmacion(pedido);
						frame.setVisible(true);
					}
				}
			});
			btnSiguiente.setBounds(291, 260, 97, 25);
		}
		return btnSiguiente;
	}
	
	private boolean checkSiguiente() {
		boolean emptyName = checkNombreIsEmpty();
		boolean emptyPassword = checkPasswordIsEmpty();
		boolean equalPassword = checkPasswordEqual();
		if( emptyName || emptyPassword || equalPassword) {
			return false;
		}
		return true;
	}
	
	private boolean checkNombreIsEmpty() {
		if(getTfNombreApellidos().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nombre y apellidos\nno puede estar vacio");
			return true;
		}
		return false;
	}
	
	private boolean checkPasswordIsEmpty() {
		String password = String.valueOf(pwdPassword.getPassword());
		String repitedPassword = String.valueOf(pwdReintroduzcaPassword.getPassword());
		
		if(password.isEmpty() || repitedPassword.isEmpty()) {
			getLblIncorrectPassword().setVisible(true);
			JOptionPane.showMessageDialog(null, "Algun campo de contraseña vacio");
			return true;
		}
		return false;
	}
	
	private boolean checkPasswordEqual() {
		String password = String.valueOf(pwdPassword.getPassword());
		String repitedPassword = String.valueOf(pwdReintroduzcaPassword.getPassword());
		if(!password.equals(repitedPassword)) {
			getLblIncorrectPassword().setVisible(true);
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
			return true;
		}
		return false;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setMnemonic('C');
			btnCancelar.setBackground(Color.RED);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setBounds(400, 260, 97, 25);
		}
		return btnCancelar;
	}
	private JPanel getCpDatosCliente() {
		if (cpDatosCliente == null) {
			cpDatosCliente = new JPanel();
			cpDatosCliente.setBackground(Color.WHITE);
			cpDatosCliente.setForeground(new Color(0, 0, 0));
			cpDatosCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			cpDatosCliente.setBounds(12, 13, 519, 200);
			cpDatosCliente.setLayout(null);
			cpDatosCliente.add(getTfNombreApellidos());
			cpDatosCliente.add(getLblNombreApellidos());
			cpDatosCliente.add(getPwdPassword());
			cpDatosCliente.add(getCbAño());
			cpDatosCliente.add(getLblAño());
			cpDatosCliente.add(getPwdReintroduzcaPassword());
			cpDatosCliente.add(getLblPassword());
			cpDatosCliente.add(getLblReintroduzcaPassword());
			cpDatosCliente.add(getLblIncorrectPassword());
		}
		return cpDatosCliente;
	}
	private JTextField getTfNombreApellidos() {
		if (tfNombreApellidos == null) {
			tfNombreApellidos = new JTextField();
			tfNombreApellidos.setBounds(188, 42, 314, 22);
			tfNombreApellidos.setColumns(10);
		}
		return tfNombreApellidos;
	}
	private JLabel getLblNombreApellidos() {
		if (lblNombreApellidos == null) {
			lblNombreApellidos = new JLabel("Nombre y Apellidos:");
			lblNombreApellidos.setLabelFor(getTfNombreApellidos());
			lblNombreApellidos.setDisplayedMnemonic('N');
			lblNombreApellidos.setBounds(36, 45, 140, 19);
		}
		return lblNombreApellidos;
	}
	private JPasswordField getPwdPassword() {
		if (pwdPassword == null) {
			pwdPassword = new JPasswordField();
			pwdPassword.setBounds(188, 112, 198, 22);
		}
		return pwdPassword;
	}
	private JComboBox<String> getCbAño() {
		if (cbAño == null) {
			String[] años = new String[90];
			for (int i=0;i<90;i++){
				String año = ""+((90-i)+1920);
				años[i]= año;
			}
			cbAño = new JComboBox<String>();
			cbAño.setModel(new DefaultComboBoxModel<String>(años));
			cbAño.setBounds(188, 77, 110, 22);
		}
		return cbAño;
	}
	private JLabel getLblAño() {
		if (lblAño == null) {
			lblAño = new JLabel("A\u00F1o de nacimiento:");
			lblAño.setDisplayedMnemonic('A');
			lblAño.setLabelFor(getCbAño());
			lblAño.setBounds(36, 80, 140, 16);
		}
		return lblAño;
	}
	private JPasswordField getPwdReintroduzcaPassword() {
		if (pwdReintroduzcaPassword == null) {
			pwdReintroduzcaPassword = new JPasswordField();
			pwdReintroduzcaPassword.setBounds(188, 147, 198, 22);
		}
		return pwdReintroduzcaPassword;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setDisplayedMnemonic('P');
			lblPassword.setLabelFor(getPwdPassword());
			lblPassword.setBounds(36, 115, 140, 16);
		}
		return lblPassword;
	}
	private JLabel getLblReintroduzcaPassword() {
		if (lblReintroduzcaPassword == null) {
			lblReintroduzcaPassword = new JLabel("Reintroduzca password:");
			lblReintroduzcaPassword.setLabelFor(getPwdReintroduzcaPassword());
			lblReintroduzcaPassword.setDisplayedMnemonic('R');
			lblReintroduzcaPassword.setBounds(36, 150, 140, 16);
		}
		return lblReintroduzcaPassword;
	}
	private JPanel getCpPedido() {
		if (cpPedido == null) {
			cpPedido = new JPanel();
			cpPedido.setBackground(Color.WHITE);
			cpPedido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			cpPedido.setBounds(12, 220, 166, 65);
			cpPedido.setLayout(null);
			cpPedido.add(getRdbtnLocal());
			cpPedido.add(getRdbtnLlevar());
		}
		return cpPedido;
	}
	private JRadioButton getRdbtnLocal() {
		if (rdbtnLocal == null) {
			rdbtnLocal = new JRadioButton("Local");
			rdbtnLocal.setMnemonic('L');
			rdbtnLocal.setBackground(Color.WHITE);
			rdbtnLocal.setSelected(true);
			buttonGroup.add(rdbtnLocal);
			rdbtnLocal.setBounds(8, 27, 72, 25);
		}
		return rdbtnLocal;
	}
	private JRadioButton getRdbtnLlevar() {
		if (rdbtnLlevar == null) {
			rdbtnLlevar = new JRadioButton("Llevar");
			rdbtnLlevar.setMnemonic('v');
			rdbtnLlevar.setBackground(Color.WHITE);
			buttonGroup.add(rdbtnLlevar);
			rdbtnLlevar.setBounds(84, 27, 74, 25);
		}
		return rdbtnLlevar;
	}
	
	
	private JLabel getLblIncorrectPassword() {
		if (lblIncorrectPassword == null) {
			lblIncorrectPassword = new JLabel("Incorrect Password");
			lblIncorrectPassword.setBounds(188, 171, 166, 16);
			lblIncorrectPassword.setVisible(false);
		}
		return lblIncorrectPassword;
	}
}

package project;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "dd-MM-yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}

public class Project {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project window = new Project();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Project() {
		initialize();

		JLabel question=new JLabel("Ce sunteti?");
		question.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		question.setBounds(345,40,200,40);
		question.setForeground(Color.WHITE);
		frame.getContentPane().add(question);

		JButton btnClient=new JButton("Client");
		btnClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);

				JFrame frameClt=new JFrame("Rezervare zbor");
				frameClt.setBounds(100, 100, 852, 480);
				frameClt.setResizable(false);
				frameClt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ImageIcon img=new ImageIcon("plane.jpg");
				JLabel contentPane=new JLabel(img);
				frameClt.setContentPane(contentPane);
				frameClt.getContentPane().setLayout(null);
				frameClt.setVisible(true);
				
				JButton btnBack=new JButton("Inapoi");
				btnBack.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frameClt.setVisible(false);
						frame.setVisible(true);
					}
				});
				btnBack.setFocusable(false);
				btnBack.setBounds(0,0,100,50);
				frameClt.getContentPane().add(btnBack);
				
				JLabel lblPlec=new JLabel("Oras plecare");
				lblPlec.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblPlec.setForeground(Color.WHITE);
				lblPlec.setBounds(110,20,100,30);
				frameClt.getContentPane().add(lblPlec);
				
				JTextField txtPlec=new JTextField();
				txtPlec.setBounds(110,51,80,30);
				frameClt.getContentPane().add(txtPlec);
				
				JLabel lblDest=new JLabel("Oras destinatie");
				lblDest.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblDest.setForeground(Color.WHITE);
				lblDest.setBounds(200,20,110,30);
				frameClt.getContentPane().add(lblDest);
				
				JTextField txtDest=new JTextField();
				txtDest.setBounds(200,51,100,30);
				frameClt.getContentPane().add(txtDest);
				
				JLabel lblDataPlec=new JLabel("Data plecare");
				lblDataPlec.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblDataPlec.setForeground(Color.WHITE);
				lblDataPlec.setBounds(110,81,110,30);
				frameClt.getContentPane().add(lblDataPlec);
				
				UtilDateModel model = new UtilDateModel();
				Properties p1 = new Properties();
				p1.put("text.today", "Today");
				p1.put("text.month", "Month");
				p1.put("text.year", "Year");
				JDatePanelImpl datePanel = new JDatePanelImpl(model, p1);
				JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
				datePicker.setBounds(110,111,160,30);
				frameClt.getContentPane().add(datePicker);
				
				JLabel lblDataSos=new JLabel("Data sosire");
				lblDataSos.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblDataSos.setForeground(Color.WHITE);
				lblDataSos.setBounds(110,141,110,30);
				frameClt.getContentPane().add(lblDataSos);
				
				UtilDateModel modelSos = new UtilDateModel();
				Properties p2 = new Properties();
				p2.put("text.today", "Today");
				p2.put("text.month", "Month");
				p2.put("text.year", "Year");
				JDatePanelImpl datePanelSos = new JDatePanelImpl(modelSos, p2);
				JDatePickerImpl datePickerSos = new JDatePickerImpl(datePanelSos, new DateLabelFormatter());
				datePickerSos.setBounds(110,171,160,30);
				frameClt.getContentPane().add(datePickerSos);
				
				JLabel lblAdulti=new JLabel("Adulti");
				lblAdulti.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblAdulti.setForeground(Color.WHITE);
				lblAdulti.setBounds(110,201,50,30);
				frameClt.getContentPane().add(lblAdulti);
				
				JTextField txtAdulti=new JTextField();
				txtAdulti.setText("1");
				txtAdulti.setBounds(110,231,40,30);
				frameClt.getContentPane().add(txtAdulti);
				
				JLabel lblCopii=new JLabel("Copii");
				lblCopii.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblCopii.setForeground(Color.WHITE);
				lblCopii.setBounds(170,201,50,30);
				frameClt.getContentPane().add(lblCopii);
				
				JTextField txtCopii=new JTextField();
				txtCopii.setText("0");
				txtCopii.setBounds(170,231,40,30);
				frameClt.getContentPane().add(txtCopii);
				
				JRadioButton radioEconomic = new JRadioButton("Economic Class");
				radioEconomic.setBounds(110, 267, 120, 20);
				radioEconomic.setSelected(true);
				frameClt.getContentPane().add(radioEconomic);
				
				JRadioButton radioBusiness = new JRadioButton("Business Class");
				radioBusiness.setBounds(110, 291, 120, 20);
				frameClt.getContentPane().add(radioBusiness);
				
				radioEconomic.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						radioBusiness.setSelected(false);
					}
					
				});
				
				radioBusiness.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						radioEconomic.setSelected(false);
					}
					
				});
				
				JCheckBox checkDusIntors = new JCheckBox("Dus-Intors");
				checkDusIntors.setBounds(110, 315, 90, 20);
				frameClt.getContentPane().add(checkDusIntors);
				
				JLabel lblDataRevenire=new JLabel("Data revenire");
				lblDataRevenire.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblDataRevenire.setVisible(false);
				lblDataRevenire.setForeground(Color.WHITE);
				lblDataRevenire.setBounds(600,20,110,30);
				frameClt.getContentPane().add(lblDataRevenire);
				
				UtilDateModel modelRev = new UtilDateModel();
				Properties p3 = new Properties();
				p3.put("text.today", "Today");
				p3.put("text.month", "Month");
				p3.put("text.year", "Year");
				JDatePanelImpl datePanelRev = new JDatePanelImpl(modelRev, p3);
				JDatePickerImpl datePickerRev = new JDatePickerImpl(datePanelRev, new DateLabelFormatter());
				datePickerRev.setVisible(false);
				datePickerRev.setBounds(600,51,160,30);
				frameClt.getContentPane().add(datePickerRev);
				
				checkDusIntors.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(checkDusIntors.isSelected())
						{
							lblDataRevenire.setVisible(true);
							datePickerRev.setVisible(true);
						}
						else
						{
							lblDataRevenire.setVisible(false);
							datePickerRev.setVisible(false);
						}
					}
					
				});
				
				JButton btnCautare = new JButton("Cauta");
				btnCautare.setBounds(145, 350, 120, 30);
				frameClt.getContentPane().add(btnCautare);
				
				btnCautare.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Date selectedValue = (Date) datePicker.getModel().getValue();						
						Date selectedValue2 = (Date) datePickerSos.getModel().getValue();
						Date selectedValue3 = (Date) datePickerRev.getModel().getValue();
						
						if(txtPlec.getText().equals("") || txtDest.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Introduceti numele orasului pentru plecare/ destinatie");
						else if(selectedValue == null || selectedValue2 == null)
							JOptionPane.showMessageDialog(null, "Introduceti data pentru plecare/ destinatie");
						else if(txtAdulti.getText().equals("") || txtAdulti.getText().equals("0"))
							JOptionPane.showMessageDialog(null, "Introduceti minim un adult pentru rezervare");
						else if(!radioEconomic.isSelected() && !radioBusiness.isSelected())
							JOptionPane.showMessageDialog(null, "Alegeti o clasa pentru rezervare");
						else if(checkDusIntors.isSelected() && selectedValue3 == null)
							JOptionPane.showMessageDialog(null, "Introduceti data pentru intoarcere");
						else
						{
							String url = "jdbc:mysql://localhost:3306/test";
							Connection con;
							try {
								con = DriverManager.getConnection (url, "root", "root");
								Statement sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
								ResultSet rs = sql.executeQuery("select * from zboruri");
								
								int okDus = 0;
								int okIntors = 0;
								
								String date = new SimpleDateFormat("dd-MM-yyyy").format(selectedValue);
								String date2 = new SimpleDateFormat("dd-MM-yyyy").format(selectedValue2);
								String date3 = null;
								
								if(checkDusIntors.isSelected())
									date3 = new SimpleDateFormat("dd-MM-yyyy").format(selectedValue3);
								
								LocalDate dateLocal = LocalDate.now();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
								String dateNow = dateLocal.format(formatter);
								
								JLabel lblDus=new JLabel("Zbor Dus");
								lblDus.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
								lblDus.setForeground(Color.WHITE);
								lblDus.setBounds(7,50,80,30);
								lblDus.setVisible(false);
								frameClt.getContentPane().add(lblDus);
								
								JScrollPane scrollPane=new JScrollPane();
								scrollPane.setBounds(7,80,822,150);
								scrollPane.setVisible(false);
								frameClt.getContentPane().add(scrollPane);
								
								DefaultTableModel tableModel=new DefaultTableModel(0,0);
								String[] header;
								if(radioEconomic.isSelected())
								{
									header=new String[] {"Cod cursa","Tip avion","Data plec","Data sos","Ora plec","Ora sos","Pret econ"};
								}
								else
								{
									header=new String[] {"Cod cursa","Tip avion","Data plec","Data sos","Ora plec","Ora sos","Pret business"};
								}
								tableModel.setColumnIdentifiers(header);
								
								JTable table = new JTable();
								table.setModel(tableModel);
								scrollPane.setViewportView(table);
								
								JLabel lblIntors=new JLabel("Zbor Intors");
								lblIntors.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
								lblIntors.setForeground(Color.WHITE);
								lblIntors.setBounds(7,240,150,30);
								lblIntors.setVisible(false);
								frameClt.getContentPane().add(lblIntors);
								
								JScrollPane scrollPane2=new JScrollPane();
								scrollPane2.setBounds(7,270,822,150);
								scrollPane2.setVisible(false);
								frameClt.getContentPane().add(scrollPane2);
								
								DefaultTableModel tableModel2=new DefaultTableModel(0,0);
								String[] header2;
								if(radioEconomic.isSelected())
								{
									header2=new String[] {"Cod cursa","Tip avion","Data plec","Data sos","Ora plec","Ora sos","Pret econ"};
								}
								else
								{
									header2=new String[] {"Cod cursa","Tip avion","Data plec","Data sos","Ora plec","Ora sos","Pret business"};
								}
								tableModel2.setColumnIdentifiers(header2);
								
								JTable table2 = new JTable();
								table2.setModel(tableModel2);
								scrollPane2.setViewportView(table2);
								
								int nr_locuri = Integer.parseInt(txtAdulti.getText()) + Integer.parseInt(txtCopii.getText());
								
								while(rs.next())
								{
									if(rs.getString(3).equalsIgnoreCase(txtPlec.getText()) && rs.getString(4).equalsIgnoreCase(txtDest.getText()))
									{
										if(rs.getString(5).equalsIgnoreCase(date) && rs.getString(6).equalsIgnoreCase(date2))
										{
											if(radioEconomic.isSelected() && Integer.parseInt(rs.getString(9))>=nr_locuri)
											{
												okDus = 1;
												if(dateNow.equalsIgnoreCase(rs.getString(5)))
												{
													tableModel.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), 
																					rs.getString(7), rs.getString(8), rs.getDouble(11) - rs.getDouble(11)*2/5});
												}
												else if(checkDusIntors.isSelected())
													tableModel.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), 
																					rs.getString(7), rs.getString(8), rs.getDouble(11) - rs.getDouble(11)/10});
												else
													tableModel.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), 
																					rs.getString(7), rs.getString(8), rs.getDouble(11)});
											}
											else if(radioBusiness.isSelected() && Integer.parseInt(rs.getString(10))>=nr_locuri)
											{
												okDus = 1;
												
												if(dateNow.equalsIgnoreCase(rs.getString(5)))
												{
													tableModel.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), 
																					rs.getString(7), rs.getString(8), rs.getDouble(12) - rs.getDouble(12)*2/5});
												}
													
												else if(checkDusIntors.isSelected())
													tableModel.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), 
																					rs.getString(7), rs.getString(8), rs.getDouble(12) - rs.getDouble(12)/10});
												else
													tableModel.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), 
																					rs.getString(7), rs.getString(8), rs.getDouble(12)});
											}
										}
									}
									
									if(checkDusIntors.isSelected())
										if(rs.getString(3).equalsIgnoreCase(txtDest.getText()) && rs.getString(4).equalsIgnoreCase(txtPlec.getText()))
										{
											if(rs.getString(5).equalsIgnoreCase(date3))
											{
												okIntors = 1;
												if(radioEconomic.isSelected() && Integer.parseInt(rs.getString(9))>=nr_locuri)
													tableModel2.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), 
																					rs.getString(7), rs.getString(8), rs.getDouble(11) - rs.getDouble(11)/10});
												else if(radioBusiness.isSelected() && Integer.parseInt(rs.getString(10))>=nr_locuri)
													tableModel2.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), 
																					rs.getString(7), rs.getString(8), rs.getDouble(12) - rs.getDouble(12)/10});
											}
										}
								}
								
								if(checkDusIntors.isSelected() && okIntors == 0)
									JOptionPane.showMessageDialog(null, "Nu s-a gasit in ziua aleasa avion pentru ruta de intoarcere");
								else if(okDus == 1)
								{
									lblPlec.setVisible(false);
									txtPlec.setVisible(false);
									lblDest.setVisible(false);
									txtDest.setVisible(false);
									lblDataPlec.setVisible(false);
									datePicker.setVisible(false);
									lblDataSos.setVisible(false);
									datePickerSos.setVisible(false);
									lblDataRevenire.setVisible(false);
									datePickerRev.setVisible(false);
									lblAdulti.setVisible(false);
									txtAdulti.setVisible(false);
									lblCopii.setVisible(false);
									txtCopii.setVisible(false);
									radioEconomic.setVisible(false);
									radioBusiness.setVisible(false);
									checkDusIntors.setVisible(false);
									btnCautare.setVisible(false);
									
									scrollPane.setVisible(true);
									lblDus.setVisible(true);
									if(okIntors == 1)
									{
										lblIntors.setVisible(true);
										scrollPane2.setVisible(true);
									}
										
									JButton btnBuy=new JButton("Cumpara");
									btnBuy.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											// TODO Auto-generated method stub
											
											boolean checkedIntors = false;
											
											if(table.getSelectedRow() != -1)
											{
												if(checkDusIntors.isSelected() && table2.getSelectedRow() == -1)
													JOptionPane.showMessageDialog(null, "Nu ati selectat un zbor pentru ruta de intoarcere!");
												else
												{
													checkedIntors = true;
													JFrame frameBuy = new JFrame("Introducere date client");
													frameBuy.setBounds(100, 100, 852, 480);
													frameBuy.setResizable(false);
													ImageIcon img=new ImageIcon("plane.jpg");
													JLabel contentPane=new JLabel(img);
													frameBuy.setContentPane(contentPane);
													frameBuy.getContentPane().setLayout(null);
													frameBuy.setVisible(true);
													
													JLabel lblNume=new JLabel("Nume");
													lblNume.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblNume.setForeground(Color.WHITE);
													lblNume.setBounds(20,10,100,30);
													frameBuy.getContentPane().add(lblNume);
													
													JTextField txtNume=new JTextField();
													txtNume.setBounds(20,40,150,30);
													frameBuy.getContentPane().add(txtNume);
													
													JLabel lblPrenume=new JLabel("Prenume");
													lblPrenume.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblPrenume.setForeground(Color.WHITE);
													lblPrenume.setBounds(20,70,100,30);
													frameBuy.getContentPane().add(lblPrenume);
													
													JTextField txtPrenume=new JTextField();
													txtPrenume.setBounds(20,100,150,30);
													frameBuy.getContentPane().add(txtPrenume);
													
													JLabel lblMail=new JLabel("E-mail");
													lblMail.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblMail.setForeground(Color.WHITE);
													lblMail.setBounds(20,130,100,30);
													frameBuy.getContentPane().add(lblMail);
													
													JTextField txtMail=new JTextField();
													txtMail.setBounds(20,160,150,30);
													frameBuy.getContentPane().add(txtMail);
													
													JLabel lblTelefon=new JLabel("Telefon");
													lblTelefon.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblTelefon.setForeground(Color.WHITE);
													lblTelefon.setBounds(20,190,100,30);
													frameBuy.getContentPane().add(lblTelefon);
													
													JTextField txtTelefon=new JTextField();
													txtTelefon.setBounds(20,220,150,30);
													frameBuy.getContentPane().add(txtTelefon);
													
													JLabel lblVarsta=new JLabel("Varsta");
													lblVarsta.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblVarsta.setForeground(Color.WHITE);
													lblVarsta.setBounds(20,250,100,30);
													frameBuy.getContentPane().add(lblVarsta);
													
													JTextField txtVarsta=new JTextField();
													txtVarsta.setBounds(20,280,150,30);
													frameBuy.getContentPane().add(txtVarsta);
													
													JRadioButton radioCardCredit = new JRadioButton("Card Credit");
													radioCardCredit.setBounds(20, 315, 100, 20);
													radioCardCredit.setSelected(true);
													frameBuy.getContentPane().add(radioCardCredit);
													
													JRadioButton radioVirament = new JRadioButton("Virament Bancar");
													radioVirament.setBounds(20, 335, 130, 20);
													frameBuy.getContentPane().add(radioVirament);
													
													JRadioButton radioCash = new JRadioButton("Cash");
													radioCash.setBounds(20, 355, 70, 20);
													radioCash.setVisible(false);
													frameBuy.getContentPane().add(radioCash);
													
													JLabel lblNrCard=new JLabel("Numar Card");
													lblNrCard.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblNrCard.setForeground(Color.WHITE);
													lblNrCard.setBounds(300,30,100,30);
													frameBuy.getContentPane().add(lblNrCard);
													
													JTextField txtNrCard=new JTextField();
													txtNrCard.setBounds(300,60,150,30);
													frameBuy.getContentPane().add(txtNrCard);
													
													JLabel lblNumeDetinator=new JLabel("Nume Detinator");
													lblNumeDetinator.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblNumeDetinator.setForeground(Color.WHITE);
													lblNumeDetinator.setBounds(460,30,150,30);
													frameBuy.getContentPane().add(lblNumeDetinator);
													
													JTextField txtNumeDetinator=new JTextField();
													txtNumeDetinator.setBounds(460,60,200,30);
													frameBuy.getContentPane().add(txtNumeDetinator);
													
													JLabel lblDataExpirare=new JLabel("Expira");
													lblDataExpirare.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblDataExpirare.setForeground(Color.WHITE);
													lblDataExpirare.setBounds(300,90,80,30);
													frameBuy.getContentPane().add(lblDataExpirare);
													
													JTextField txtLunaExpirare=new JTextField();
													txtLunaExpirare.setBounds(300,120,30,30);
													frameBuy.getContentPane().add(txtLunaExpirare);
													
													JTextField txtAnExpirare=new JTextField();
													txtAnExpirare.setBounds(330,120,30,30);
													frameBuy.getContentPane().add(txtAnExpirare);
													
													JLabel lblCVV=new JLabel("CVV");
													lblCVV.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblCVV.setForeground(Color.WHITE);
													lblCVV.setBounds(380,90,50,30);
													frameBuy.getContentPane().add(lblCVV);
													
													JTextField txtCVV=new JTextField();
													txtCVV.setBounds(380,120,30,30);
													frameBuy.getContentPane().add(txtCVV);
													
													JLabel lblCardPret=new JLabel();
													if(!checkDusIntors.isSelected())
														lblCardPret.setText("Total de plata: " + (Double.parseDouble(txtAdulti.getText()) + Double.parseDouble(txtCopii.getText())) * Double.parseDouble(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 6))));
													else
													{
														double pretDus = (Double.parseDouble(txtAdulti.getText()) + Double.parseDouble(txtCopii.getText())) * Double.parseDouble(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 6)));
														double pretIntors = (Double.parseDouble(txtAdulti.getText()) + Double.parseDouble(txtCopii.getText())) * Double.parseDouble(String.valueOf(tableModel2.getValueAt(table2.getSelectedRow(), 6)));
														lblCardPret.setText("Total de plata: " + (pretDus + pretIntors));
													}
													
													
													lblCardPret.setFont(new Font("Tahoma", Font.BOLD, 13));
													lblCardPret.setForeground(Color.WHITE);
													lblCardPret.setBounds(300,170,200,30);
													frameBuy.getContentPane().add(lblCardPret);
													
													// Virament Bancar
													
													JTextArea lblVirament=new JTextArea();
													lblVirament.setFont(new Font("Tahoma", Font.BOLD, 18));
													lblVirament.setForeground(Color.BLACK);
													lblVirament.setBounds(300,30,350,70);
													lblVirament.setVisible(false);
													lblVirament.setEditable(false);
													frameBuy.getContentPane().add(lblVirament);
													
													// Cash
													
													JLabel lblCash=new JLabel();
													lblCash.setFont(new Font("Tahoma", Font.BOLD, 18));
													lblCash.setForeground(Color.WHITE);
													lblCash.setBounds(300,30,300,100);
													lblCash.setVisible(false);
													frameBuy.getContentPane().add(lblCash);
													
													radioCardCredit.addActionListener(new ActionListener() {
	
														@Override
														public void actionPerformed(ActionEvent e) {
															// TODO Auto-generated method stub
															radioVirament.setSelected(false);
															radioCash.setSelected(false);
															
															lblNrCard.setVisible(true);
															txtNrCard.setVisible(true);
															lblNumeDetinator.setVisible(true);
															txtNumeDetinator.setVisible(true);
															lblDataExpirare.setVisible(true);
															txtLunaExpirare.setVisible(true);
															txtAnExpirare.setVisible(true);
															lblCVV.setVisible(true);
															txtCVV.setVisible(true);
															
															
															lblVirament.setVisible(false);
															lblCash.setVisible(false);
														}
														
													});
													
													radioVirament.addActionListener(new ActionListener() {
	
														@Override
														public void actionPerformed(ActionEvent e) {
															// TODO Auto-generated method stub
															radioCardCredit.setSelected(false);
															radioCash.setSelected(false);
															
															lblNrCard.setVisible(false);
															txtNrCard.setVisible(false);
															lblNumeDetinator.setVisible(false);
															txtNumeDetinator.setVisible(false);
															lblDataExpirare.setVisible(false);
															txtLunaExpirare.setVisible(false);
															txtAnExpirare.setVisible(false);
															lblCVV.setVisible(false);
															txtCVV.setVisible(false);
															
															lblVirament.setVisible(true);
															lblCash.setVisible(false);
															
															lblVirament.setText("DETALII PLATA VIRAMENT\nIBAN: RO49AAAA1B31007593840000\nSWIFT: INGRO");
														}
														
													});
													
													radioCash.addActionListener(new ActionListener() {
	
														@Override
														public void actionPerformed(ActionEvent e) {
															// TODO Auto-generated method stub
															radioVirament.setSelected(false);
															radioCardCredit.setSelected(false);
															
															lblNrCard.setVisible(false);
															txtNrCard.setVisible(false);
															lblNumeDetinator.setVisible(false);
															txtNumeDetinator.setVisible(false);
															lblDataExpirare.setVisible(false);
															txtLunaExpirare.setVisible(false);
															txtAnExpirare.setVisible(false);
															lblCVV.setVisible(false);
															txtCVV.setVisible(false);
															
															lblVirament.setVisible(false);
															lblCash.setVisible(true);
															
															lblCash.setText("Ati ales metoda de plata CASH!");
														}
														
													});
													
													JButton btnPlateste=new JButton("Plateste");
													btnPlateste.setBounds(0,392,100,50);
													frameBuy.getContentPane().add(btnPlateste);
													btnPlateste.addActionListener(new ActionListener() {

														@Override
														public void actionPerformed(ActionEvent e) {
															// TODO Auto-generated method stub
															boolean clientInfo = false;
															boolean plataVerified = false;
															
															if(!txtNume.getText().equals("") && !txtPrenume.getText().equals("") && !txtMail.getText().equals("") && !txtTelefon.getText().equals("") && !txtVarsta.getText().equals(""))
																clientInfo = true;
															
															if(radioCardCredit.isSelected())
															{
																if(txtNrCard.getText().length() == 16)
																{
																	if(!txtNumeDetinator.getText().equals(""))
																	{
																		if(Integer.parseInt(txtLunaExpirare.getText()) >= 1 && Integer.parseInt(txtLunaExpirare.getText()) <= 12)
																		{
																			if(Integer.parseInt(txtAnExpirare.getText()) >= 2021 && Integer.parseInt(txtAnExpirare.getText()) <= 2030)
																			{
																				if(txtCVV.getText().length() == 3)
																				{
																					plataVerified = true;
																				}
																				else
																					JOptionPane.showMessageDialog(null, "Introduceti corect numarul de verificare(CVV) pentru card!","Eroare",JOptionPane.ERROR_MESSAGE);
																			}
																			else
																				JOptionPane.showMessageDialog(null, "Introduceti un an valid pentru card!","Eroare",JOptionPane.ERROR_MESSAGE);
																		}
																		else
																			JOptionPane.showMessageDialog(null, "Introduceti o luna valida pentru card!","Eroare",JOptionPane.ERROR_MESSAGE);
																	}
																	else
																		JOptionPane.showMessageDialog(null, "Introduceti numele detinatorului cardului!","Eroare",JOptionPane.ERROR_MESSAGE);
																}
																else
																	JOptionPane.showMessageDialog(null, "Numarul cardului nu este introdus corect!","Eroare",JOptionPane.ERROR_MESSAGE);
															}
															else if(radioVirament.isSelected())
																plataVerified = true;
															else if(radioCash.isSelected())
																plataVerified = true;
															else
																JOptionPane.showMessageDialog(null, "Selectati o metoda de plata!","Eroare",JOptionPane.ERROR_MESSAGE);
															
															
															if(clientInfo)
																if(plataVerified)
																{
																	try {
																		//DE FACUT SCRIEREA IN FISIER!!!!
																		BufferedWriter out = new BufferedWriter(new FileWriter("src/rezervari/"+txtNume.getText() + ".txt"));
																		
																		String url = "jdbc:mysql://localhost:3306/test";
																		Connection con = DriverManager.getConnection (url, "root", "root");
																		//Statement sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
																		//ResultSet rs = sql.executeQuery("select * from zboruri where ");
																		
																		String query = "insert into rezervari values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
																		PreparedStatement preparedStmt = con.prepareStatement(query);
																		
	
																		preparedStmt.setString(1, String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 0)));
																		preparedStmt.setInt(2, Integer.parseInt(txtAdulti.getText()));
																		preparedStmt.setInt(3, Integer.parseInt(txtCopii.getText()));
																		if(radioEconomic.isSelected())
																			preparedStmt.setInt(4, 0);
																		else
																			preparedStmt.setInt(4, 1);
																		preparedStmt.setString(5, txtNume.getText());
																		preparedStmt.setString(6, txtPrenume.getText());
																		preparedStmt.setString(7, txtMail.getText());
																		preparedStmt.setString(8, txtTelefon.getText());
																		preparedStmt.setInt(9, Integer.parseInt(txtVarsta.getText()));
																		preparedStmt.setDouble(10, (Double.parseDouble(txtAdulti.getText()) + Double.parseDouble(txtCopii.getText())) * Double.parseDouble(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 6))));
																		if(radioCardCredit.isSelected())
																			preparedStmt.setString(11, radioCardCredit.getText());
																		else if(radioVirament.isSelected())
																			preparedStmt.setString(11, radioVirament.getText());
																		else
																			preparedStmt.setString(11, radioCash.getText());
																		
																		int random = (int)Math.floor(Math.random()*(999999999-100000000+1)+100000000);
																		preparedStmt.setString(12, String.valueOf(random));
																		preparedStmt.execute();
																		
																		Statement sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
																		ResultSet rs = sql.executeQuery("select * from zboruri");
																		
																		while(rs.next())
																		{
																			if(rs.getString(1).equalsIgnoreCase(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 0))))
																			{
																				out.write("Detalii zbor dus");
																				out.newLine();
																				out.write("Cod Zbor: " + rs.getString(1));
																				out.newLine();
																				out.write("Avion: " + rs.getString(2));
																				out.newLine();
																				out.write("Oras plecare: " + rs.getString(3));
																				out.newLine();
																				out.write("Oras destinatie: " + rs.getString(4));
																				out.newLine();
																				out.write("Data plecare: " + rs.getString(5));
																				out.newLine();
																				out.write("Data sosire: " + rs.getString(6));
																				out.newLine();
																				out.write("Ora plecare: " + rs.getString(7));
																				out.newLine();
																				out.write("Ora sosire: " + rs.getString(8));
																				out.newLine();
																				out.newLine();
																				break;
																			}
																		}
																		
																		if(checkDusIntors.isSelected())
																		{
																			query = "insert into rezervari values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
																			PreparedStatement preparedStmt2 = con.prepareStatement(query);
																			
																			preparedStmt2.setString(1, String.valueOf(tableModel2.getValueAt(table2.getSelectedRow(), 0)));
																			preparedStmt2.setInt(2, Integer.parseInt(txtAdulti.getText()));
																			preparedStmt2.setInt(3, Integer.parseInt(txtCopii.getText()));
																			if(radioEconomic.isSelected())
																				preparedStmt2.setInt(4, 0);
																			else
																				preparedStmt2.setInt(4, 1);
																			preparedStmt2.setString(5, txtNume.getText());
																			preparedStmt2.setString(6, txtPrenume.getText());
																			preparedStmt2.setString(7, txtMail.getText());
																			preparedStmt2.setString(8, txtTelefon.getText());
																			preparedStmt2.setInt(9, Integer.parseInt(txtVarsta.getText()));
																			preparedStmt2.setDouble(10, (Double.parseDouble(txtAdulti.getText()) + Double.parseDouble(txtCopii.getText())) * Double.parseDouble(String.valueOf(tableModel2.getValueAt(table2.getSelectedRow(), 6))));
																			if(radioCardCredit.isSelected())
																				preparedStmt2.setString(11, radioCardCredit.getText());
																			else if(radioVirament.isSelected())
																				preparedStmt2.setString(11, radioVirament.getText());
																			else
																				preparedStmt2.setString(11, radioCash.getText());
																			
																			random = (int)Math.floor(Math.random()*(999999999-100000000+1)+100000000);
																			preparedStmt2.setString(12, String.valueOf(random));
																			preparedStmt2.execute();
																			
																			rs = sql.executeQuery("select * from zboruri");
																			
																			while(rs.next())
																			{
																				if(rs.getString(1).equalsIgnoreCase(String.valueOf(tableModel2.getValueAt(table2.getSelectedRow(), 0))))
																				{
																					out.write("Detalii zbor intors");
																					out.newLine();
																					out.write("Cod Zbor: " + rs.getString(1));
																					out.newLine();
																					out.write("Avion: " + rs.getString(2));
																					out.newLine();
																					out.write("Oras plecare: " + rs.getString(3));
																					out.newLine();
																					out.write("Oras destinatie: " + rs.getString(4));
																					out.newLine();
																					out.write("Data plecare: " + rs.getString(5));
																					out.newLine();
																					out.write("Data sosire: " + rs.getString(6));
																					out.newLine();
																					out.write("Ora plecare: " + rs.getString(7));
																					out.newLine();
																					out.write("Ora sosire: " + rs.getString(8));
																					out.newLine();
																					out.newLine();
																					break;
																				}
																			}
																		}
																		
																		rs = sql.executeQuery("select * from rezervari");
																		while(rs.next())
																		{
																			if(rs.getString(12).equalsIgnoreCase(String.valueOf(random)))
																			{
																				out.write("Detalii Client");
																				out.newLine();
																				out.write("Nume: " + rs.getString(5));
																				out.newLine();
																				out.write("Prenume: " + rs.getString(6));
																				out.newLine();
																				out.write("Locuri adulti:" + rs.getInt(2));
																				out.newLine();
																				out.write("Locuri copii: " + rs.getInt(3));
																				out.newLine();
																				if(rs.getInt(4) == 0)
																					out.write("Clasa: Economic");
																				else
																					out.write("Clasa: Business");
																				out.newLine();
																				out.write("Email: " + rs.getString(7));
																				out.newLine();
																				out.write("Telefon: " + rs.getString(8));
																				out.newLine();
																				out.write("Varsta: " + rs.getString(9));
																				out.newLine();
																				if(!checkDusIntors.isSelected())
                                                                                    out.write("Total Plata: " + ((Double.parseDouble(txtAdulti.getText()) + Double.parseDouble(txtCopii.getText())) * Double.parseDouble(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 6)))));
                                                                                else
                                                                                    out.write("Total Plata: " + ((Double.parseDouble(txtAdulti.getText()) + Double.parseDouble(txtCopii.getText())) * Double.parseDouble(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 6))) +
                                                                                            (Double.parseDouble(txtAdulti.getText()) + Double.parseDouble(txtCopii.getText())) * Double.parseDouble(String.valueOf(tableModel2.getValueAt(table2.getSelectedRow(), 6)))));
																				out.newLine();
																				out.write("Modalitate plata: " + rs.getString(11));
																				out.newLine();
																				out.write("Cod bare rezervare: " + rs.getString(12));
																				out.newLine();
																				break;
																			}
																		}
																		
																		out.close();
																		
																		if(radioEconomic.isSelected())
																			query = "UPDATE zboruri SET nrLocuriEco = nrLocuriEco - ? WHERE codCursa = ?";
																		else
																			query = "UPDATE zboruri SET nrLocuriBuss = nrLocuriBuss - ? WHERE codCursa = ?";
																		PreparedStatement preparedStmt3 = con.prepareStatement(query);
																		preparedStmt3.setInt(1, Integer.parseInt(txtAdulti.getText()) + Integer.parseInt(txtCopii.getText()));
																		preparedStmt3.setString(2, String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 0)));
																		preparedStmt3.execute();
																		if(checkDusIntors.isSelected())
																		{
																			preparedStmt3.setString(2, String.valueOf(tableModel2.getValueAt(table2.getSelectedRow(), 0)));
																			preparedStmt3.execute();
																		}
																		
																		
																		query = "DELETE FROM zboruri WHERE nrLocuriEco = 0 AND nrLocuriBuss = 0";
																		PreparedStatement preparedStmt4 = con.prepareStatement(query);
																		preparedStmt4.execute();
																		
																		JOptionPane.showMessageDialog(null, "Plata a fost efectuata cu succes! Verificati src/rezervari pentru bilet!");
																		frameBuy.setVisible(false);
																		frameClt.setVisible(false);
																		frame.setVisible(true);
																	} catch (IOException e1) {
																		// TODO Auto-generated catch block
																		e1.printStackTrace();
																	} catch (SQLException e1) {
																		// TODO Auto-generated catch block
																		e1.printStackTrace();
																	}
																}
																else
																	JOptionPane.showMessageDialog(null, "Eroare la plata!","Eroare",JOptionPane.ERROR_MESSAGE);
															else
																JOptionPane.showMessageDialog(null, "Introduceti datele clientului pentru plata!","Eroare",JOptionPane.ERROR_MESSAGE);
														}
														
													});
													
													
													JButton btnLoginPersonal=new JButton("Login");
													btnLoginPersonal.addActionListener(new ActionListener() {
	
														@Override
														public void actionPerformed(ActionEvent e) {
															// TODO Auto-generated method stub
															JFrame frameAng=new JFrame("Autentificare");
															frameAng.setBounds(300, 100, 852, 480);
															frameAng.setResizable(false);
															ImageIcon img=new ImageIcon("plane.jpg");
															JLabel contentPane=new JLabel(img);
															frameAng.setContentPane(contentPane);
															frameAng.getContentPane().setLayout(null);
															frameAng.setVisible(true);
															
															JLabel user=new JLabel("Utilizator");
															user.setFont(new Font("Tahoma", Font.BOLD, 13));
															user.setBounds(240,190,80,30);
															user.setForeground(Color.WHITE);
															frameAng.getContentPane().add(user);
	
															JLabel pass=new JLabel("Parola");
															pass.setFont(new Font("Tahoma", Font.BOLD, 13));
															pass.setBounds(240,230,80,30);
															pass.setForeground(Color.WHITE);
															frameAng.getContentPane().add(pass);
	
															JTextField txtUser=new JTextField();
															txtUser.setBounds(330,190,100,30);
															frameAng.getContentPane().add(txtUser);
	
															JPasswordField txtPass=new JPasswordField();
															txtPass.setBounds(330,230,100,30);
															frameAng.getContentPane().add(txtPass);
	
															JCheckBox showPass=new JCheckBox("Arata parola");
															showPass.setFocusable(false);
															showPass.setFont(new Font("Tahoma", Font.BOLD, 13));
															showPass.setBounds(240,270,120,30);
															frameAng.getContentPane().add(showPass);
	
															txtPass.setEchoChar('*');
	
															showPass.addActionListener(new ActionListener() {
	
																@Override
																public void actionPerformed(ActionEvent e) {
																	// TODO Auto-generated method stub
																	JCheckBox check = (JCheckBox) e.getSource();
	
																	if (check.isSelected()) {
																		txtPass.setEchoChar((char)0);
																	} else {
																		txtPass.setEchoChar('*');
																	}
																}
															});
	
															JButton btnLogin=new JButton("Autentificare");
															btnLogin.addActionListener(new ActionListener() {
	
																@Override
																public void actionPerformed(ActionEvent e) {
																	// TODO Auto-generated method stub
																	try {
																		String url = "jdbc:mysql://localhost:3306/test";
																		Connection con = DriverManager.getConnection (url, "root", "root");
																		Statement sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
																		ResultSet rs = sql.executeQuery("select * from admini");
	
																		int ok=0;//presupunem ca userul si parola nu sunt valide
	
																		if(txtUser!=null && txtPass!=null)
																		{
																			while(rs.next())
																			{
																				if(rs.getString(1).equals(txtUser.getText()) && rs.getString(2).equals(String.valueOf(txtPass.getPassword())))
																				{
																					ok=1;
																					JOptionPane.showMessageDialog(null, "Autentificare reusita!");
																					frameAng.setVisible(false);
																					radioCash.setVisible(true);
																					break;
																				}
																			}
																		}
																		
																		if(ok==0)
																		{
																			frameAng.setVisible(false);
																			JOptionPane.showMessageDialog(null, "Autentificare nereusita!","Eroare",JOptionPane.ERROR_MESSAGE);
																		}
	
																	} catch (SQLException e1) {
																		// TODO Auto-generated catch block
																		e1.printStackTrace();
																	}
	
																}
															});
															btnLogin.setFocusable(false);
															btnLogin.setBounds(240,310,190,50);
															frameAng.getContentPane().add(btnLogin);
														}
													});
													btnLoginPersonal.setBounds(737,0,100,50);
													frameBuy.getContentPane().add(btnLoginPersonal);
												}
											}
											else
												JOptionPane.showMessageDialog(null, "Selectati un zbor pentru varianta dus!");
											
										}
									});
									btnBuy.setBounds(737,0,100,50);
									frameClt.getContentPane().add(btnBuy);
								}
								else
									JOptionPane.showMessageDialog(null, "Nu s-a gasit avion disponibil in zilele selectate");
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}
					
				});
			}
		});
		btnClient.setFocusable(false);
		btnClient.setBounds(200,190,100,50);
		frame.getContentPane().add(btnClient);

		JButton btnAngajat=new JButton("Angajat");
		btnAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);

				JFrame frameAng=new JFrame("Autentificare");
				frameAng.setBounds(100, 100, 852, 480);
				frameAng.setResizable(false);
				frameAng.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ImageIcon img=new ImageIcon("plane.jpg");
				JLabel contentPane=new JLabel(img);
				frameAng.setContentPane(contentPane);
				frameAng.getContentPane().setLayout(null);
				frameAng.setVisible(true);

				JButton btnBack=new JButton("Inapoi");
				btnBack.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frameAng.setVisible(false);
						frame.setVisible(true);
					}
				});
				btnBack.setFocusable(false);
				btnBack.setBounds(0,0,100,50);
				frameAng.getContentPane().add(btnBack);

				JLabel user=new JLabel("Utilizator");
				user.setFont(new Font("Tahoma", Font.BOLD, 13));
				user.setBounds(240,190,80,30);
				user.setForeground(Color.WHITE);
				frameAng.getContentPane().add(user);

				JLabel pass=new JLabel("Parola");
				pass.setFont(new Font("Tahoma", Font.BOLD, 13));
				pass.setBounds(240,230,80,30);
				pass.setForeground(Color.WHITE);
				frameAng.getContentPane().add(pass);

				JTextField txtUser=new JTextField();
				txtUser.setBounds(330,190,100,30);
				frameAng.getContentPane().add(txtUser);

				JPasswordField txtPass=new JPasswordField();
				txtPass.setBounds(330,230,100,30);
				frameAng.getContentPane().add(txtPass);

				JCheckBox showPass=new JCheckBox("Arata parola");
				showPass.setFocusable(false);
				showPass.setFont(new Font("Tahoma", Font.BOLD, 13));
				showPass.setBounds(240,270,120,30);
				frameAng.getContentPane().add(showPass);

				txtPass.setEchoChar('*');

				showPass.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JCheckBox check = (JCheckBox) e.getSource();

						if (check.isSelected()) {
							txtPass.setEchoChar((char)0);
						} else {
							txtPass.setEchoChar('*');
						}
					}
				});

				JButton btnLogin=new JButton("Autentificare");
				btnLogin.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							String url = "jdbc:mysql://localhost:3306/test";
							Connection con = DriverManager.getConnection (url, "root", "root");
							Statement sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							ResultSet rs = sql.executeQuery("select * from admini");

							int ok=0;//presupunem ca userul si parola nu sunt valide

							if(txtUser!=null && txtPass!=null)
							{
								while(rs.next())
								{
									if(rs.getString(1).equals(txtUser.getText()) && rs.getString(2).equals(String.valueOf(txtPass.getPassword())))
									{
										ok=1;
										JOptionPane.showMessageDialog(null, "Autentificare reusita!");
										break;
									}
								}
							}
							
							if(ok==0)
							{
								txtUser.setText("");
								txtPass.setText("");
								JOptionPane.showMessageDialog(null, "Autentificare nereusita!","Eroare",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								frameAng.setVisible(false);
								
								JFrame frameAut=new JFrame("Operatiuni");
								frameAut.setBounds(100, 100, 852, 480);
								frameAut.setResizable(false);
								frameAut.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								ImageIcon img=new ImageIcon("plane.jpg");
								JLabel contentPane=new JLabel(img);
								frameAut.setContentPane(contentPane);
								frameAut.getContentPane().setLayout(null);
								frameAut.setVisible(true);
								
								JToolBar toolBar=new JToolBar();
								toolBar.setBounds(0,0,852,48);
								frameAut.getContentPane().add(toolBar);
								
								JPanel p=new JPanel();
								
								JScrollPane scrollPane=new JScrollPane();
								scrollPane.setBounds(8,280,822,150);
								frameAut.getContentPane().add(scrollPane);
								
								DefaultTableModel tableModel=new DefaultTableModel(0,0);
								String[] header=new String[] {"Cod cursa","Tip avion","Oras plec","Oras dest",
										"Data plec","Data sos","Ora plec","Ora sos",
										"Nr loc econ","Nr loc business","Pret econ","Pret business"};
								tableModel.setColumnIdentifiers(header);
								
								JTable table = new JTable();
								table.setModel(tableModel);
								scrollPane.setViewportView(table);
								
								JLabel lblCod=new JLabel("Cod cursa");
								lblCod.setVisible(false);
								lblCod.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblCod.setForeground(Color.WHITE);
								lblCod.setBounds(10,50,80,30);
								frameAut.getContentPane().add(lblCod);
								
								JTextField txtCod=new JTextField();
								txtCod.setVisible(false);
								txtCod.setBounds(10,81,80,30);
								frameAut.getContentPane().add(txtCod);
								
								JLabel lblTip=new JLabel("Tip avion");
								lblTip.setVisible(false);
								lblTip.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblTip.setForeground(Color.WHITE);
								lblTip.setBounds(100,50,80,30);
								frameAut.getContentPane().add(lblTip);
								
								JTextField txtTip=new JTextField();
								txtTip.setVisible(false);
								txtTip.setBounds(100,81,80,30);
								frameAut.getContentPane().add(txtTip);
								
								JLabel lblPlec=new JLabel("Oras plecare");
								lblPlec.setVisible(false);
								lblPlec.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblPlec.setForeground(Color.WHITE);
								lblPlec.setBounds(190,50,100,30);
								frameAut.getContentPane().add(lblPlec);
								
								JTextField txtPlec=new JTextField();
								txtPlec.setVisible(false);
								txtPlec.setBounds(190,81,80,30);
								frameAut.getContentPane().add(txtPlec);
								
								JLabel lblDest=new JLabel("Oras destinatie");
								lblDest.setVisible(false);
								lblDest.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblDest.setForeground(Color.WHITE);
								lblDest.setBounds(280,50,110,30);
								frameAut.getContentPane().add(lblDest);
								
								JTextField txtDest=new JTextField();
								txtDest.setVisible(false);
								txtDest.setBounds(280,81,100,30);
								frameAut.getContentPane().add(txtDest);
								
								JLabel lblDataPlec=new JLabel("Data plecare");
								lblDataPlec.setVisible(false);
								lblDataPlec.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblDataPlec.setForeground(Color.WHITE);
								lblDataPlec.setBounds(390,50,110,30);
								frameAut.getContentPane().add(lblDataPlec);
								
								UtilDateModel model = new UtilDateModel();
								Properties p1 = new Properties();
								p1.put("text.today", "Today");
								p1.put("text.month", "Month");
								p1.put("text.year", "Year");
								JDatePanelImpl datePanel = new JDatePanelImpl(model, p1);
								JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
								datePicker.setVisible(false);
								datePicker.setBounds(390,81,160,30);
								frameAut.getContentPane().add(datePicker);
								
								JLabel lblDataSos=new JLabel("Data sosire");
								lblDataSos.setVisible(false);
								lblDataSos.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblDataSos.setForeground(Color.WHITE);
								lblDataSos.setBounds(560,50,110,30);
								frameAut.getContentPane().add(lblDataSos);
								
								UtilDateModel modelSos = new UtilDateModel();
								Properties p2 = new Properties();
								p2.put("text.today", "Today");
								p2.put("text.month", "Month");
								p2.put("text.year", "Year");
								JDatePanelImpl datePanelSos = new JDatePanelImpl(modelSos, p2);
								JDatePickerImpl datePickerSos = new JDatePickerImpl(datePanelSos, new DateLabelFormatter());
								datePickerSos.setVisible(false);
								datePickerSos.setBounds(560,81,160,30);
								frameAut.getContentPane().add(datePickerSos);
								
								JLabel lblOraPlec=new JLabel("Ora plecare");
								lblOraPlec.setVisible(false);
								lblOraPlec.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblOraPlec.setForeground(Color.WHITE);
								lblOraPlec.setBounds(730,50,110,30);
								frameAut.getContentPane().add(lblOraPlec);
								
								JTextField txtOraPlec=new JTextField();
								txtOraPlec.setVisible(false);
								txtOraPlec.setBounds(730,81,30,30);
								frameAut.getContentPane().add(txtOraPlec);
								
								JLabel lblMinutar=new JLabel(":");
								lblMinutar.setVisible(false);
								lblMinutar.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblMinutar.setForeground(Color.WHITE);
								lblMinutar.setBounds(770,81,30,30);
								frameAut.getContentPane().add(lblMinutar);
								
								JTextField txtMinPlec=new JTextField();
								txtMinPlec.setVisible(false);
								txtMinPlec.setBounds(785,81,30,30);
								frameAut.getContentPane().add(txtMinPlec);
								
								JLabel lblOraSos=new JLabel("Ora sosire");
								lblOraSos.setVisible(false);
								lblOraSos.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblOraSos.setForeground(Color.WHITE);
								lblOraSos.setBounds(10,112,110,30);
								frameAut.getContentPane().add(lblOraSos);
								
								JTextField txtOraSos=new JTextField();
								txtOraSos.setVisible(false);
								txtOraSos.setBounds(10,143,30,30);
								frameAut.getContentPane().add(txtOraSos);
								
								JLabel lblMinutar2=new JLabel(":");
								lblMinutar2.setVisible(false);
								lblMinutar2.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblMinutar2.setForeground(Color.BLACK);
								lblMinutar2.setBounds(50,143,30,30);
								frameAut.getContentPane().add(lblMinutar2);
								
								JTextField txtMinSos=new JTextField();
								txtMinSos.setVisible(false);
								txtMinSos.setBounds(65,143,30,30);
								frameAut.getContentPane().add(txtMinSos);
								
								JLabel lblLocEcon=new JLabel("Nr. locuri economic");
								lblLocEcon.setVisible(false);
								lblLocEcon.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblLocEcon.setForeground(Color.WHITE);
								lblLocEcon.setBounds(100,112,130,30);
								frameAut.getContentPane().add(lblLocEcon);
								
								JTextField txtLocEcon=new JTextField();
								txtLocEcon.setVisible(false);
								txtLocEcon.setBounds(100,143,130,30);
								frameAut.getContentPane().add(txtLocEcon);
								
								JLabel lblLocBus=new JLabel("Nr. locuri business");
								lblLocBus.setVisible(false);
								lblLocBus.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblLocBus.setForeground(Color.WHITE);
								lblLocBus.setBounds(235,112,130,30);
								frameAut.getContentPane().add(lblLocBus);
								
								JTextField txtLocBus=new JTextField();
								txtLocBus.setVisible(false);
								txtLocBus.setBounds(235,143,130,30);
								frameAut.getContentPane().add(txtLocBus);
								
								JLabel lblPretEcon=new JLabel("Pret loc economic");
								lblPretEcon.setVisible(false);
								lblPretEcon.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblPretEcon.setForeground(Color.WHITE);
								lblPretEcon.setBounds(370,112,130,30);
								frameAut.getContentPane().add(lblPretEcon);
								
								JTextField txtPretEcon=new JTextField();
								txtPretEcon.setVisible(false);
								txtPretEcon.setBounds(370,143,130,30);
								frameAut.getContentPane().add(txtPretEcon);
								
								JLabel lblPretBus=new JLabel("Pret loc business");
								lblPretBus.setVisible(false);
								lblPretBus.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblPretBus.setForeground(Color.WHITE);
								lblPretBus.setBounds(505,112,130,30);
								frameAut.getContentPane().add(lblPretBus);
								
								JTextField txtPretBus=new JTextField();
								txtPretBus.setVisible(false);
								txtPretBus.setBounds(505,143,130,30);
								frameAut.getContentPane().add(txtPretBus);
								
								JLabel lblOperatiune=new JLabel();
								lblOperatiune.setVisible(false);
								lblOperatiune.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblOperatiune.setBounds(280,215,572,30);
								frameAut.getContentPane().add(lblOperatiune);
								
								JButton btnOperatiune=new JButton();
								btnOperatiune.setVisible(false);
								btnOperatiune.setFocusable(false);
								btnOperatiune.setBounds(325,245,190,30);
								frameAut.getContentPane().add(btnOperatiune);
								
								String url2 = "jdbc:mysql://localhost:3306/test";
								Connection con2 = DriverManager.getConnection (url2, "root", "root");
								Statement sql2 = (Statement) con2.createStatement();
								ResultSet rs2 = sql2.executeQuery("select * from zboruri");
								
								while(rs2.next())
								{
									tableModel.addRow(new Object[] {rs2.getString(1), rs2.getString(2), rs2.getString(3)
											, rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7)
											, rs2.getString(8) , rs2.getInt(9), rs2.getInt(10), rs2.getDouble(11), rs2.getDouble(12)});
								}
								
								con2.close();
								sql2.close();
								rs2.close();
								
								JButton btnMain = new JButton("");
								btnMain.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										frame.setVisible(true);
										frameAut.setVisible(false);
									}
								});
								btnMain.setIcon(new ImageIcon("undo.png"));
								btnMain.setBorder(BorderFactory.createLineBorder(Color.BLACK));
								p.add(btnMain);
								
								JLabel lblPlus=new JLabel(new ImageIcon("plus.png"));
								lblPlus.addMouseListener(new MouseListener() {
									
									@Override
									public void mouseReleased(MouseEvent e) {}
									
									@Override
									public void mousePressed(MouseEvent e) {}
									
									@Override
									public void mouseExited(MouseEvent e) {}
									
									@Override
									public void mouseEntered(MouseEvent e) {
										// TODO Auto-generated method stub
										lblPlus.setToolTipText("Adaugare zbor");
									}
									
									@Override
									public void mouseClicked(MouseEvent e) {
										// TODO Auto-generated method stub
										txtPretBus.setVisible(true);
										lblPretBus.setVisible(true);
										txtPretEcon.setVisible(true);
										lblPretEcon.setVisible(true);
										txtLocBus.setVisible(true);
										lblLocBus.setVisible(true);
										txtLocEcon.setVisible(true);
										lblLocEcon.setVisible(true);
										txtMinSos.setVisible(true);
										lblMinutar2.setVisible(true);
										txtOraSos.setVisible(true);
										lblOraSos.setVisible(true);
										txtMinPlec.setVisible(true);
										lblMinutar.setVisible(true);
										txtOraPlec.setVisible(true);
										lblOraPlec.setVisible(true);
										datePickerSos.setVisible(true);
										lblDataSos.setVisible(true);
										datePicker.setVisible(true);
										lblDataPlec.setVisible(true);
										txtDest.setVisible(true);
										lblDest.setVisible(true);
										txtPlec.setVisible(true);
										lblPlec.setVisible(true);
										txtTip.setVisible(true);
										lblTip.setVisible(true);
										txtCod.setVisible(true);
										lblCod.setVisible(true);
										btnOperatiune.setVisible(true);
										lblOperatiune.setVisible(true);
										
										txtPretBus.setText("");
										txtPretEcon.setText("");
										txtLocBus.setText("");
										txtLocEcon.setText("");
										txtMinSos.setText("");
										txtOraSos.setText("");
										txtMinPlec.setText("");
										txtOraPlec.setText("");
										txtDest.setText("");
										txtPlec.setText("");
										txtTip.setText("");
										txtCod.setText("");
										
										lblOperatiune.setForeground(Color.GREEN);
										lblOperatiune.setText("Apasati butonul pentru a introduce un nou zbor");
										btnOperatiune.setText("Adaugare zbor");
										btnOperatiune.addActionListener(new ActionListener() {
											
											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												try {
													String url = "jdbc:mysql://localhost:3306/test";
													Connection con = DriverManager.getConnection (url, "root", "root");
													
													//Statement sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
													//ResultSet rs = sql.executeQuery("select * from admini");
													
													String query = "insert into zboruri values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
													PreparedStatement preparedStmt = con.prepareStatement(query);
													
													preparedStmt.setString(1, txtCod.getText());
													preparedStmt.setString(2, txtTip.getText());
													preparedStmt.setString(3, txtPlec.getText());
													preparedStmt.setString(4, txtDest.getText());
													Date selectedValue = (Date) datePicker.getModel().getValue();
													String date = new SimpleDateFormat("dd-MM-yyyy").format(selectedValue);
													preparedStmt.setString(5, date);
													Date selectedValue2 = (Date) datePickerSos.getModel().getValue();
													String date2 = new SimpleDateFormat("dd-MM-yyyy").format(selectedValue2);
													preparedStmt.setString(6, date2);
													preparedStmt.setString(7, txtOraPlec.getText()+":"+txtMinPlec.getText());
													preparedStmt.setString(8, txtOraSos.getText()+":"+txtMinSos.getText());
													preparedStmt.setInt(9, Integer.parseInt(txtLocEcon.getText()));
													preparedStmt.setInt(10, Integer.parseInt(txtLocBus.getText()));
													preparedStmt.setDouble(11, Double.parseDouble(txtPretEcon.getText()));
													preparedStmt.setDouble(12, Double.parseDouble(txtPretBus.getText()));
													
													preparedStmt.execute();
													
													tableModel.addRow(new Object[] {txtCod.getText(), txtTip.getText(), txtPlec.getText()
															, txtDest.getText(), date, date2, txtOraPlec.getText()+":"+txtMinPlec.getText()
															, txtOraSos.getText()+":"+txtMinSos.getText(), txtLocEcon.getText()
															, txtLocBus.getText(), txtPretEcon.getText(), txtPretBus.getText()});					
													
													con.close();
												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}
										});
									}
								});
								lblPlus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
								p.add(lblPlus);
								
								toolBar.add(p);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
				btnLogin.setFocusable(false);
				btnLogin.setBounds(240,310,190,50);
				frameAng.getContentPane().add(btnLogin);
			}
		});
		btnAngajat.setFocusable(false);
		btnAngajat.setBounds(500,190,100,50);
		frame.getContentPane().add(btnAngajat);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Prima pagina");
		frame.setBounds(100, 100, 852, 480);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img=new ImageIcon("plane.jpg");
		JLabel contentPane=new JLabel(img);
		frame.setContentPane(contentPane);
		frame.getContentPane().setLayout(null);
	}

}

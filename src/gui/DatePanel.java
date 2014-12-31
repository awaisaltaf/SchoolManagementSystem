package gui;

import gui.DateLabelFormatter;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.SpringLayout;

public class DatePanel extends JPanel {
	private JDatePickerImpl datePicker;
	public DatePanel dtp;

	/**
	 * Create the panel.
	 */
	public DatePanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.NORTH,
				datePicker.getJFormattedTextField(), 0, SpringLayout.NORTH,
				datePicker);
		add(datePicker);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new DatePanel().setVisible(true);
			}
		});
	}

	public Date getDateObj() {
		Date selectedDate = (Date) datePicker.getModel().getValue();
		return selectedDate;
	}

	public String getTimeAndDate() {
		Date selectedDate = (Date) datePicker.getModel().getValue();
		String formatedDate;
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		formatedDate = dateFormatter.format(selectedDate);
		return formatedDate;
	}
	public String getCompleteDate() {
		Date selectedDate = (Date) datePicker.getModel().getValue();
		String formatedDate;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("d-M-y");
		formatedDate = dateFormatter.format(selectedDate);
		return formatedDate;
	}

	public int getDate() {
		Date selectedDate = (Date) datePicker.getModel().getValue();
		int date = selectedDate.getDate();
		return date;
	}

	public int getMonth() {
		Date selectedDate = (Date) datePicker.getModel().getValue();
		int month = selectedDate.getMonth() + 1;
		return month;
	}

	public int getYear() {
		Date selectedDate = (Date) datePicker.getModel().getValue();
		int year = selectedDate.getYear() + 1900;
		return year;
	}

	public void show() {
		dtp.setVisible(false);
	}

}

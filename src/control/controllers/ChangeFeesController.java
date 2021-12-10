

package control.controllers;

import control.controllers.*;
import presentation.guicomponents.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import business.usermodels.*;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Class ChangeFeesController
 * @since December 4th, 2021
 * @author Azlan Amjad
 * @author Rohan Amjad
 * @author Gibran Akmal
 * @author Ibrahim Asad
 * @version 1.2
 */
public class ChangeFeesController implements Controller {

 private Frontend view;
 private CompanyDatabase model;
 
 
    /**
     * ChangeFeesController constuctor, Initializes the values of Frontend
     * and CompantDatabase, placing Action Listners to the buttons of our 
     * GUI so that if a button is pressed it will cause an action.
     * @param view, parameter is a Frontend type and used to make the GUI
     * @param model, the model parameter is a CompanyDatabase type and is used to connect to the SQL Database
     */
  
public ChangeFeesController(Frontend view, CompanyDatabase model) {
		this.view = view;
		this.model = model;

		view.getChangeFees().getDashboardButton().addActionListener(this);
		view.getChangeFees().getUpdateButton().addActionListener(this);
	}


    /**
     * actionPerformed If one of the two buttons is pressed will make sure the
     * an action is performed correctly regarding Fees. Useres will select the Fee amount and the period and press the 
     * update button and "e" will detect it and if valid it will go and update otherwise it will go back to the 
     * "Logged in as Manager" Page and if user select dashboard button it will also go back to the "Logged in as Manager" page
     * @param e parametr is a ActionEvent type and makes sure to check if a button is pressed and will commit an an action
     */

public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getChangeFees().getDashboardButton()) {
			view.dashboard();
			view.getDashboard().loggedInManager();

		}
		else if (e.getSource() == view.getChangeFees().getUpdateButton()) {
			int feeAmount = 0;
			int feePeriod = 0;

			try {
				String feeAmountString = view.getChangeFees().getFeeAmountComboBox().getSelectedItem().toString();
				String feePeriodString = view.getChangeFees().getFeePeriodComboBox().getSelectedItem().toString();

				feeAmount = Integer.parseInt(feeAmountString);
				feePeriod = Integer.parseInt(feePeriodString);
			} 
			catch (NullPointerException exception) {
				exception.printStackTrace();
			}
			catch (NumberFormatException exception) {
				exception.printStackTrace();
			}

			Fee fee = new Fee(feePeriod, feeAmount);

			try {
				model.setFee(fee);

				view.dashboard();
				view.getDashboard().loggedInManager();
			} 
			catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}

    
}

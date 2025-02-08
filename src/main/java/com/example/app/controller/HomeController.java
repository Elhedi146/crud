package com.example.app.controller;

import com.example.app.dao.UserDAO;
import com.example.app.models.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private TableColumn<User, Integer> colId;
    @FXML
    public TableColumn<User ,String> colPassword;
    @FXML
    private TableColumn<User, String>colCin;

    @FXML
    private TableColumn<User, String> colEmail;


    @FXML
    private TableColumn<User, String> colLastname;

    @FXML
    private TableColumn<User,String> colName;


    @FXML
    private TableView<User> tableView;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private TextField id;

    @FXML
    private TextField lastname;

    @FXML
    private TextField name;

    @FXML
    private TextField password;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));


        tableView.setItems(UserDAO.getAllUsers());
    }
    public void update() throws Exception {
        int userId = Integer.parseInt(id.getText().trim());
            User user = new User(
                    userId,
                    name.getText().trim(),
                    lastname.getText().trim(),
                    email.getText().trim(),
                    cin.getText().trim(),
                    UserDAO.hashPassword(password.getText()) // À implémenter
            );

            UserDAO.updateUser(user,userId);

    }
    @FXML
    private void handleAddUser() {

        String nameText = name.getText().trim();
        String lastnameText = lastname.getText().trim();
        String emailText = email.getText().trim();
        String cinText = cin.getText().trim();
        String passwordText = password.getText().trim();

        if (!nameText.isEmpty() && !lastnameText.isEmpty() && !emailText.isEmpty()
                && !cinText.isEmpty() && !passwordText.isEmpty()) {
            try {


                User newUser = new User(0, nameText, lastnameText, emailText, cinText, passwordText);

                UserDAO.insertUser(newUser);

                tableView.setItems(UserDAO.getAllUsers());

                clearFields();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Please fill all fields!");
        }
    }
    @FXML
    private void handleDeleteUser() {
        String emailToDelete = email.getText().trim();

        if (!emailToDelete.isEmpty()) {
            // Delete user by email from TextField
            UserDAO.deleteUser(emailToDelete);

            // Refresh the table
            tableView.setItems(UserDAO.getAllUsers());

            // Clear the email field
            email.clear();
        } else {
            // Show error message
            System.err.println("Please enter an email to delete!");
            // For better UX, use: new Alert(Alert.AlertType.WARNING, "Enter email!").show();
        }
    }
    private void clearFields() {
        name.clear();
        lastname.clear();
        email.clear();
        cin.clear();
        password.clear();
    }


}

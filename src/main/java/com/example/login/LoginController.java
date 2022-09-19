package com.example.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class LoginController{
 
    @FXML
    private Button CANCELBUTTON;
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField KeyField;

    @FXML
    private Label Message;

    public void cancelButtonOnAction(ActionEvent event){

        Stage stage = (Stage) CANCELBUTTON.getScene().getWindow();
        stage.close();

    }

    public void LoginButtonOnAction(ActionEvent event){

        try{

            File ArchivoXML = new File("C:\\Users\\Andres\\IdeaProjects\\Login\\src\\Usuarios.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder Builder = dbFactory.newDocumentBuilder();
            Document documentoXML = Builder.parse(ArchivoXML);
            documentoXML.getDocumentElement().normalize();
            NodeList Usuarios = documentoXML.getElementsByTagName("Usuario");


            for (int i = 0; i < Usuarios.getLength(); i++) {
                Node Usuario = Usuarios.item(i);
                Element Elemento = (Element) Usuario;
                String User = UsernameField.getText();
                String Key = KeyField.getText();
                if (Objects.equals(Elemento.getElementsByTagName("User").item(0).getTextContent(), User) && Objects.equals(Elemento.getElementsByTagName("Password").item(0).getTextContent(), Key)){
                    System.out.println("Yes");
                    Message.setText("Exito!");
                    return;
                }else{
                    System.out.println(Elemento.getElementsByTagName("User").item(0).getTextContent());
                }

            }
        }catch (IOException | ParserConfigurationException | SAXException e) {
            System.err.println("Error: "+ e.getMessage());
        }

    }


}
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.*;
import java.lang.Runtime;
import java.nio.file.Files;
import java.nio.charset.Charset;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.util.Base64;
import java.util.Scanner;
import java.io.PrintWriter;
import javafx.scene.shape.Rectangle;
import java.awt.Desktop; //is it bad to mix JavaD
import java.awt.Desktop.*;
import javafx.event.*;
import java.awt.Toolkit;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import org.apache.commons.io.FileUtils;

//ezc_new123
//all other versions are invalid

public class Main extends Application {

    BorderPane bPane = new BorderPane();
    GridPane gPane = new GridPane();
    VBox box = new VBox();
    StackPane sPane = new StackPane();
    Pane confirmPane = new Pane();
    HBox buttonBottomForBorder = new HBox();
    Button encryptButton;
    Button decryptButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //this is where the window is built, don't do anything else here
        try {
            buildUI();
            customizeUI();
            registerEvents();

            sPane.getChildren().add(bPane); //putting the BorderPane in a stack pane
            Scene scene = new Scene(sPane); //to do pseudo-pop ups by pushing to top of stackpane
            primaryStage.setScene(scene);
            primaryStage.setTitle("Alan's EZcrypt");
            //primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception sfsdfgsdfg) {
            System.out.println(sfsdfgsdfg.getMessage());
        }
    }

    public void buildUI() {
        encryptButton = new Button("Encrypt");
        decryptButton = new Button("Decrypt");
        //bPane.add(hiding,);
        sPane.setMinWidth(500);
        sPane.setMinHeight(500);
        buttonBottomForBorder.getChildren().add(encryptButton);
        buttonBottomForBorder.getChildren().add(decryptButton);
        bPane.setBottom(buttonBottomForBorder);
        //fileType1 = new Label("Host files: PNG, GIF, or JPG/JPEG only");



        //bPane.setCenter(gPane);


    }

    public void customizeUI() {
        //not very useful, could probably merge with the buildUI
        //gPane.setHgap(10);
        //bPane.setPadding(new Insets(0, 0, 10, 0));


    }

    public void registerEvents() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException {
        //setOnAction lambda expressions
        //this is where all the button functionality goes
        //however, the hiding and retrieving buttons call functions that are defined below
        //System.out.println("This is where the button setOnAction or lambdas go");
        encryptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Encrypting");
                boolean noPaddingError = true;
                boolean noAlgoError = true;
                boolean noKeyError = true;
                boolean nofileByteArrayIOError = true;
                Cipher encryptionCipher = null;
                try {
                    String myKey = "helloworld"; //replace with getting PasswordField.getText();
                    byte[] myKeyByteArray = myKey.getBytes(); //converts encryption key to byte array
                    SecretKeySpec myKeySpec = new SecretKeySpec(myKeyByteArray, "Blowfish");
                    try {
                        encryptionCipher = Cipher.getInstance("Blowfish");
                    } catch (NoSuchPaddingException padEx) {
                        System.out.println("no such padding exception");
                        noPaddingError = false;
                    }
                    if (noPaddingError) {
                        try {
                            encryptionCipher.init(Cipher.ENCRYPT_MODE, myKeySpec);
                        } catch (InvalidKeyException keyEx) {
                            System.out.println("invalid key exception");
                            noKeyError = false;
                        }
                    }
                } catch (NoSuchAlgorithmException algoEx) {
                    System.out.println("no such encryption algo");
                    noAlgoError = false;
                }
                //
                if (noPaddingError == false || noAlgoError == false || noKeyError == false) {
                    System.out.println("something went wrong");
                } else {
                    //actually doing the file encryption
                    String filename = "example.jpg";
                    //try {

                    File fileToEncrypt = new File(filename);

                    //} catch (IOException fileEx) {
                    //    System.out.println("IO error with filename: " + filename);
                    //}
                    byte[] fileByteArray = null;
                    try {
                        fileByteArray = Files.readAllBytes(fileToEncrypt.toPath());



                    } catch (IOException ioEx) {
                        System.out.println("file IO exception with filename: " + filename);
                        nofileByteArrayIOError = false;
                    }

                    if (nofileByteArrayIOError) {
                        try {
                            byte[] encryptedOutput = encryptionCipher.doFinal(fileByteArray);
                            //now the byte array needs to be converted to a file
                            //TO-DO: CONVERT encryptedOutput TO FILE, THEN WRITE TO DISK
                            //THEN TEST WITH BCRYPT FOR TESTING DECRYPTION
                            //IF IT WORKS, THEN MOVE ON TO THE decryptButton.SetOnAction stuff
                            FileUtils.writeByteArrayToFile(new File("output.jpg"), encryptedOutput);
                            System.out.println("wew you got here");
                        } catch (IllegalBlockSizeException blockEx) {
                            System.out.println("illegal block size exception");
                        } catch (BadPaddingException badPadEx) {
                            System.out.println("bad padding exception");
                        } catch (IOException ioExc) {
                            System.out.println("io exception");
                        }

                    } else {
                        System.out.println("there was a file byte array error, not proceeding");

                    }
                }
            }
        });
        decryptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Decrypting");
                String myDecryptionKey = "helloworld";
                byte[] myDecryptionKeyByteArray = myDecryptionKey.getBytes(); //converts encryption key to byte array
                SecretKeySpec myKeySpec = new SecretKeySpec(myDecryptionKeyByteArray, "Blowfish");

                Cipher decryptCipher = null;
                try {
                    decryptCipher = Cipher.getInstance("Blowfish");
                } catch(NoSuchAlgorithmException noSuchAlgoDecEx) {
                    System.out.println("noSuchAlgoDecEx");
                } catch (NoSuchPaddingException noPadDecEx) {
                    System.out.println("no pad decrypt exception");
                }
                if (decryptCipher != null) {
                    try {
                        decryptCipher.init(Cipher.DECRYPT_MODE, myKeySpec);
                    } catch (InvalidKeyException invDecEx) {//InvalidKeyException
                        System.out.println("invalid decryption key");
                    }
                } else {
                    System.out.println("decryptCipher null error");
                }

                byte[] fileDecryptionByteArray = null; //will take the file to be decrypted and turn to byte array
                String decryptionFilename = "output.jpg";
                File fileToDecrypt = new File(decryptionFilename);
                try {
                    fileDecryptionByteArray = Files.readAllBytes(fileToDecrypt.toPath());
                } catch (IOException decryptIOex) {
                    System.out.println("decryption IO error");
                }
                byte[] decrypted = null;
                try {
                    decrypted = decryptCipher.doFinal(fileDecryptionByteArray);
                } catch (IllegalBlockSizeException blockSizeDecryptEx) {
                    System.out.println("illegal block size exception for decrypting");
                } catch (BadPaddingException badPadDecryptEx) {
                    System.out.println("bad padding decryption file exception");
                }
                try {
                    //now need to convert "decrypted" byte array to a File
                    FileUtils.writeByteArrayToFile(new File("exampleDecrypt.jpg"), decrypted);
                } catch (IOException decryptIOex) {
                    System.out.println("decryptIOex");
                }

            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}

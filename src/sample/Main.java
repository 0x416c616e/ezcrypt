package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

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
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.Clipboard;







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
    Pane centerPane = new Pane();
    Button websiteButton;
    Text titleText;
    Button aboutButton;
    RadioButton encryptRadioButton;
    RadioButton decryptRadioButton;
    FileChooser fileChooser;
    Button fileChooserButton;
    Rectangle dragAndDropRectangle;
    Button keyGeneratorButton;
    Button submitButton;
    Label keyLabel;
    TextField keyField;
    Button showHideButton;
    Button copyToClipboardButton;
    Button pasteFromClipboardButton;
    Button clearClipboardButton;
    Button clearKeyFieldButton;
    Text sizeOfGeneratedKeys;
    Text dragAndDropText;
    PasswordField keyPasswordField;
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
            primaryStage.setResizable(false);
            //not sure about the next line
            //fileChooser = new FileChooser();
            //fileChooser.setTitle("Click here to select file");
            //fileChooser.getExtensionFilters().addAll(
            //        new ExtensionFilter("Text Files", "*.txt"),
            //        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
            //        new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
            //        new ExtensionFilter("All Files", "*.*"));
            //File selectedFile = fileChooser.showOpenDialog(primaryStage);

            primaryStage.show();
        } catch (Exception sfsdfgsdfg) {
            System.out.println(sfsdfgsdfg.getMessage());
        }
    }

    public void buildUI() {
        websiteButton = new Button("Website");
        encryptButton = new Button("Encrypt");
        decryptButton = new Button("Decrypt");
        titleText = new Text(200, 40, "EZcrypt");
        titleText.setFont(new Font(24));
        centerPane.getChildren().add(titleText);
        aboutButton = new Button("About");
        aboutButton.relocate(425,40);
        centerPane.getChildren().add(aboutButton);
        ToggleGroup group = new ToggleGroup();
        RadioButton encryptRadioButton = new RadioButton("Encrypt");
        encryptRadioButton.setToggleGroup(group);
        encryptRadioButton.setSelected(true);
        RadioButton decryptRadioButton = new RadioButton("Decrypt");
        decryptRadioButton.setToggleGroup(group);
        encryptRadioButton.relocate(175,100);
        decryptRadioButton.relocate(275, 100);
        centerPane.getChildren().addAll(encryptRadioButton, decryptRadioButton);
        fileChooserButton = new Button("Click here to select file");
        fileChooserButton.relocate(175,125);
        centerPane.getChildren().add(fileChooserButton);
        submitButton = new Button("GO");
        submitButton.setMinWidth(75);
        submitButton.setMinHeight(75);
        //submitButton.relocate(375,125);
        submitButton.relocate(225,400);
        centerPane.getChildren().add(submitButton);
        //File selectedFile = fileChooser.showOpenDialog(primaryStage);
        dragAndDropText = new Text("Or drag and drop file\nto this rectangle");
        dragAndDropText.setFont(new Font(20));
        dragAndDropText.relocate(175,190);
        centerPane.getChildren().add(dragAndDropText);
        dragAndDropRectangle = new Rectangle(100,170,300,100);
        dragAndDropRectangle.setFill(Color.TRANSPARENT);
        dragAndDropRectangle.setStroke(Color.BLACK);
        centerPane.getChildren().add(dragAndDropRectangle);
        keyLabel = new Label("Key:");
        keyLabel.relocate(45,300);
        centerPane.getChildren().add(keyLabel);
        keyField = new TextField("");
        keyField.setMinWidth(300);
        keyField.relocate(100,300);
        centerPane.getChildren().add(keyField);
        showHideButton = new Button("Show/Hide");
        showHideButton.relocate(405,300);
        centerPane.getChildren().add(showHideButton);


        copyToClipboardButton = new Button("Copy key to clipboard");
        copyToClipboardButton.relocate(45, 340);
        centerPane.getChildren().add(copyToClipboardButton);

        pasteFromClipboardButton = new Button("Paste key from clipboard");
        pasteFromClipboardButton.relocate(315,340);
        centerPane.getChildren().add(pasteFromClipboardButton);
        //Text sizeOfGeneratedKeys;
        sizeOfGeneratedKeys = new Text(15, 400,"Size of generated keys:");
        centerPane.getChildren().add(sizeOfGeneratedKeys);

        clearClipboardButton = new Button("Clear clipboard");
        clearClipboardButton.relocate(370, 400);
        centerPane.getChildren().add(clearClipboardButton);

        clearKeyFieldButton = new Button("Clear key field");
        clearKeyFieldButton.relocate(375,435);
        centerPane.getChildren().add(clearKeyFieldButton);
        //Button clearKeyFieldButton;



        //bPane.add(hiding,);
        sPane.setMinWidth(500);
        sPane.setMinHeight(500);


        //encryptButton.relocate(100,100);
        //decryptButton.relocate(200,100);
        //centerPane.getChildren().add(encryptButton);
        //centerPane.getChildren().add(decryptButton);

        websiteButton.relocate(15,40);
        centerPane.getChildren().add(websiteButton);
        //fileType1 = new Label("Host files: PNG, GIF, or JPG/JPEG only");
        Slider slider = new Slider(32, 448, 448);
        slider.setShowTickMarks(false);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(32);
        slider.setBlockIncrement(1);
        slider.relocate(15,425);
        centerPane.getChildren().add(slider);
        keyGeneratorButton = new Button("Generate random key");
        keyGeneratorButton.relocate(15,460);
        centerPane.getChildren().add(keyGeneratorButton);



        //bPane.setCenter(gPane);
        bPane.setCenter(centerPane);

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
        clearClipboardButton.setOnAction( e -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent blankClipboardContent = new ClipboardContent();
            blankClipboardContent.putString("");
            clipboard.setContent(blankClipboardContent);
        });

        copyToClipboardButton.setOnAction(e -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent keyFieldContent = new ClipboardContent();
            keyFieldContent.putString(keyField.getText());
            clipboard.setContent(keyFieldContent);
        });

        clearKeyFieldButton.setOnAction(e ->{
            keyField.setText("");
        });

        pasteFromClipboardButton.setOnAction( e-> {
            final Clipboard clipboard2 = Clipboard.getSystemClipboard();
            keyField.setText(clipboard2.getString());
        });


        websiteButton.setOnAction( e -> {
            getHostServices().showDocument("https://saintlouissoftware.com/");
        });

        aboutButton.setOnAction( e -> {
            //f4f4f4
            Rectangle overlayRectangle = new Rectangle(0,0,500,500);
            overlayRectangle.setFill(Color.web("f4f4f4"));
            centerPane.getChildren().add(overlayRectangle);
            Text aboutText = new Text("This program was made by Alan. It is free\n" +
                    "and open source, released under the GNU\n" +
                    "GPLv3 license. This program is provided AS\n" +
                    "IS, without warranty. It uses the Blowfish\n" +
                    "encryption cipher. For more information,\n" +
                    "click the website button in the upper left.");
            aboutText.setFont(new Font(24));
            aboutText.relocate(5,100);
            Button goBackButton = new Button("Go back");
            goBackButton.relocate(200,400);
            centerPane.getChildren().add(aboutText);
            centerPane.getChildren().add(goBackButton);
            goBackButton.setOnAction(x -> {
                centerPane.getChildren().remove(aboutText);
                centerPane.getChildren().remove(goBackButton);
                centerPane.getChildren().remove(overlayRectangle);

            });

        });



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

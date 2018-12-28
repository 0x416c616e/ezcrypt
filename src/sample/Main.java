package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.Random;
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
import java.security.SecureRandom;








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
    RadioButton encryptRadioButton = new RadioButton("Encrypt");
    RadioButton decryptRadioButton = new RadioButton("Decrypt");
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
    Text keySizeInfo;
    Text typeOrGenerate;
    PasswordField keyPasswordField;
    boolean textFieldVisibility = true;
    Slider slider;
    public String globalFileName = "";
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
        //encryptRadioButton.setSelected(true);
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
        keySizeInfo = new Text("Keys must be 4-56 characters long (which is 32-448 bits)");
        keyLabel.relocate(45,300);
        keySizeInfo.relocate(60, 280);
        centerPane.getChildren().add(keySizeInfo);
        typeOrGenerate = new Text("Type your own text key or randomly generate one.");
        typeOrGenerate.relocate(100, 80);
        centerPane.getChildren().add(typeOrGenerate);
        centerPane.getChildren().add(keyLabel);
        keyField = new TextField("");
        keyPasswordField = new PasswordField();
        keyField.setMinWidth(300);
        keyPasswordField.setMinWidth(300);
        keyField.relocate(100,300);
        keyPasswordField.relocate(100,300);

        centerPane.getChildren().add(keyField);
        keyPasswordField.setVisible(false);
        centerPane.getChildren().add(keyPasswordField);

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
        slider = new Slider(4, 56, 56);
        slider.setShowTickMarks(false);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(13);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.setSnapToTicks(false);
        slider.relocate(15,425);
        centerPane.getChildren().add(slider);
        keyGeneratorButton = new Button("Generate random key");
        keyGeneratorButton.relocate(15,460);
        centerPane.getChildren().add(keyGeneratorButton);



        //bPane.setCenter(gPane);
        bPane.setCenter(centerPane);
        submitButton.setOnAction( e -> {
            if ((globalFileName != "") && (keyField.getText() != "")) {
                System.out.println("hello");
                System.out.println(encryptRadioButton.isSelected());
                //see radio button status
                if (encryptRadioButton.isSelected() && !(decryptRadioButton.isSelected())) {
                    //encryption is selected, now need to encrypt
                    System.out.println("got here");
                    System.out.println("encrypting file: " + globalFileName);
                    encryptFile(globalFileName, keyField.getText());
                } else if (decryptRadioButton.isSelected() && !(encryptRadioButton.isSelected())) {
                    //decryption is selected, now need to decrypt
                    System.out.println("decryption will happen here, finish this later");
                    System.out.println("attempting to decrypt file: " + globalFileName);
                    System.out.println("using key: " + keyField.getText());
                    decryptFile(globalFileName, keyField.getText());
                } else {
                    System.out.println("something went wrong with the radio buttons");
                } //end of picking whether encrypting or decrypting based on radio buttons
            } else {
                System.out.println("filename and/or key is null, can't proceed with encryption/decryption");
            }

        });




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
            if (textFieldVisibility == true) {
                keyFieldContent.putString(keyField.getText());
                clipboard.setContent(keyFieldContent);
            } else { //passwordField
                keyFieldContent.putString(keyPasswordField.getText());
                clipboard.setContent(keyFieldContent);
            }

        });

        clearKeyFieldButton.setOnAction(e ->{
            keyField.setText("");
            keyPasswordField.setText("");
        });

        pasteFromClipboardButton.setOnAction( e-> {
            final Clipboard clipboard2 = Clipboard.getSystemClipboard();
            if (textFieldVisibility == true) {
                keyField.setText(clipboard2.getString());
            } else {
                keyPasswordField.setText(clipboard2.getString());
            }
        });

        showHideButton.setOnAction(e -> {
            //keyPasswordField
            //textFieldVisibility
            //keyField
            if (textFieldVisibility == true) {
                keyField.setVisible(false);
                keyPasswordField.setText(keyField.getText());
                keyPasswordField.setVisible(true);
                textFieldVisibility = false;
                //System.out.println("now password bullets are shown");
            } else if (textFieldVisibility == false) {
                keyField.setVisible(true);
                keyPasswordField.setVisible(false);
                keyField.setText(keyPasswordField.getText());
                textFieldVisibility = true;
            } else {
                System.out.println("???");
            }
        });

        websiteButton.setOnAction( e -> {
            getHostServices().showDocument("https://saintlouissoftware.com/");
        });

        keyGeneratorButton.setOnAction(e -> {
            System.out.println("size of key: " + String.valueOf(Math.round(slider.getValue())));
            int keyLength = (int)Math.round(slider.getValue());
            String base64String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
            Random randomNumber = new SecureRandom();
            String generatedKey = "";
            for (int i = 0; i < keyLength; i++) {
                int randomBase64 = randomNumber.nextInt(64); //0 to 63
                generatedKey += base64String.charAt(randomBase64);
            }


            /*
            System.out.println("Generating SecureRandom key");
            SecureRandom random = new SecureRandom();
            byte bytesArray[] = new byte[8*(int)Math.round(slider.getValue())];
            random.nextBytes(bytesArray);
            System.out.println(String.valueOf(bytesArray));
            String secureRandomString = "";
            try {
                secureRandomString = new String(bytesArray, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                System.out.println("encoding issue");
            }
            System.out.println("secureRandomString is :" + secureRandomString);
            */


            /*//======================================testing
            String doc = "whatever";
            String doc2 = "";
            try {
                byte[] bytes = doc.getBytes("UTF-8");
                doc2 = new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                System.out.println("encoding issue");
            }
            System.out.println("doc2: " + doc2);
            //======================================end of test
            */

            keyField.setText(generatedKey);
            keyPasswordField.setText(generatedKey);
        });
        fileChooserButton.setOnAction( e -> {
            chooseFile();
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

        //button for encryption/decryption




        /*
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


                //blah
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
                //up to here
            }
        });
        */
        //I can probably delete the encryptButton and decryptButton stuff;
        //that was just left over from my initial test program, before I added
        //all the more complicated stuff for user-selected key and file

    }

    public void chooseFile()
    {
        //final Image image;
        String filePath = "";
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac", ".ogg", ".flac"),
                new ExtensionFilter("All Files", "*.*"));

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        filePath = file.getAbsolutePath();

        globalFileName = filePath;
    }


    public void encryptFile(String fileNameToEncrypt, String encryptionKey) {
        System.out.println("Encrypting");
        boolean noPaddingError = true;
        boolean noAlgoError = true;
        boolean noKeyError = true;
        boolean nofileByteArrayIOError = true;
        Cipher encryptionCipher = null;
        try {
            String myKey = encryptionKey; //replace with getting PasswordField.getText();
            byte[] myKeyByteArray = myKey.getBytes(); //converts encryption key to byte array
            System.out.println("myKey toString: " +myKey.toString());
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
            String filename = fileNameToEncrypt; //from argument
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
                    FileUtils.writeByteArrayToFile(new File(globalFileName), encryptedOutput);
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

    public void decryptFile(String fileNameToDecrypt, String decryptionKey) {
        System.out.println("Decrypting! I hope this actually works uwu");
        String myDecryptionKey = decryptionKey;
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
        String decryptionFilename = fileNameToDecrypt;
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
            FileUtils.writeByteArrayToFile(new File(fileNameToDecrypt), decrypted);
        } catch (IOException decryptIOex) {
            System.out.println("decryptIOex");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

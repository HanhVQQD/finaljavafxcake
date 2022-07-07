package com.example.javafxproject;

import com.example.javafxproject.data.DBConnection;
import com.example.javafxproject.data.models.Admin;
import com.example.javafxproject.data.models.Cake;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloApplication extends Application {

    private Scene scene, screenLogin,navigationButton, home;
    TextField name, pass;
    private static final String EMPTY = "";
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    //admin
    void hompage(DBConnection conn, GridPane grid, Stage primaryStage){
        ArrayList<Cake> cakeList = conn.getCake();

        grid.add(new Label("Name:"), 0, 0);
        var tfName = new TextField();
        grid.add(tfName, 0, 1);
        //
        grid.add(new Label("Image:"), 1, 0);
        var tfImage = new TextField();
        grid.add(tfImage, 1, 1);
        //
        grid.add(new Label("Price:"), 2, 0);
        var tfPrice = new TextField();
        grid.add(tfPrice, 2, 1);
        //
        grid.add(new Label("Type Cake:"),3,  0);
        var tfTypecake = new TextField();
        grid.add(tfTypecake, 3, 1);
        //
        grid.add(new Label("Quality:"),4,  0);
        var tfQuality = new TextField();
        grid.add(tfQuality, 4, 1);
        //

        // add
        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            String image = tfImage.getText();
            Float price = Float.valueOf(tfPrice.getText());
            String typecake = tfTypecake.getText();
            Integer quality = Integer.valueOf(tfQuality.getText());
            if (!name.equals(EMPTY) && !image.equals(EMPTY) && !price.equals(EMPTY) && !typecake.equals(EMPTY) && !quality.equals(EMPTY)) {
                conn.insertProduct(new Cake(name,quality, price, typecake,image));
                try {
                    start(primaryStage);
                    window.setScene(scene);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank!");
            alert.showAndWait();
        });
        grid.add(btnAdd, 5, 1);

        //show
        for(int i = 0; i < cakeList.size(); i++){
            Image image = new Image(cakeList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);

            grid.add(new Label (cakeList.get(i).getName()), 0, i+2);
            grid.add(imageView, 1, i+2);
            grid.add(new Label ("$"+(cakeList.get(i).getPrice())), 2, i+2);
            grid.add(new Label (cakeList.get(i).getTypecake()), 3, i+2);
            grid.add(new Label (""+cakeList.get(i).getQuality()), 4, i+2);

            // Update
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int id1 = Integer.parseInt(btnUpdate.getId());
                TextField tfname = (TextField) grid.getChildren().get(1);
                tfname.setText("" + cakeList.get(id1).getName());
                TextField tfimage = (TextField) grid.getChildren().get(3);
                tfimage.setText("" + cakeList.get(id1).getImage());
                TextField tfprice = (TextField) grid.getChildren().get(5);
                tfprice.setText("" + cakeList.get(id1).getPrice());
                TextField tftypecake = (TextField) grid.getChildren().get(7);
                tftypecake.setText("" + cakeList.get(id1).getTypecake());
                TextField tfquality = (TextField) grid.getChildren().get(9);
                tfquality.setText("" + cakeList.get(id1).getQuality());
                var newbtnAdd = new Button("Update");
                newbtnAdd.setPadding(new Insets(6, 20, 6, 20));
                newbtnAdd.setOnAction(newe -> {
                    Integer Newid = cakeList.get(id1).id;
                    String Newname = tfname.getText();
                    String Newimage = tfimage.getText();
                    Float Newprice = Float.valueOf(tfprice.getText());
                    String Newtypecake = tftypecake.getText();
                    Integer Newquality = Integer.valueOf(tfquality.getText());
                    if (!Newname.equals(EMPTY) && !Newimage.equals(EMPTY) && !Newprice.equals(EMPTY) && !Newtypecake.equals(EMPTY) && !Newquality.equals(EMPTY)) {
                        conn.updateProduct(new Cake(Newid, Newname,Newquality, Newprice, Newtypecake, Newimage));
                        try {
                            start(primaryStage);
                            window.setScene(scene);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return;
                    }
                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank!");
                    alert.showAndWait();
                });
                grid.add(newbtnAdd, 5, 1);
            });
            grid.add(btnUpdate, 5, i+2);

            // Delete
            var btnDelete = new Button("Delete");

            btnDelete.setId(String.valueOf(cakeList.get(i).id));
            btnDelete.setOnAction(e -> {
                int id3 = Integer.parseInt(btnDelete.getId());
                conn.deleteProduct(id3);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Do you agree to delete it?");
                alert.showAndWait();
                try {
                    start(primaryStage);
                    window.setScene(scene);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 6, i+2);
        }
        //button back
        Button btnGoBack = new Button("Back");
        btnGoBack.setPadding(new Insets(5, 15, 5, 15));
        btnGoBack.setOnAction(actionEvent -> {
            window.setScene(navigationButton);
            window.centerOnScreen();
        });
        grid.add(btnGoBack,9,0);
    }


    void navigationButton( VBox sceneNavigationButton) {
        GridPane goAdmin = new GridPane();
        GridPane goHome = new GridPane();

        Button btnGoAdmin = new Button("Admin");
        btnGoAdmin.setPadding(new Insets(15, 25, 15, 25));
        btnGoAdmin.setOnAction(actionEvent -> {
            window.setScene(scene);
            window.centerOnScreen();
        });
        Button btnGoHome = new Button("User");
        btnGoHome.setPadding(new Insets(15, 25, 15, 25));
        btnGoHome.setOnAction(actionEvent -> {
            window.setScene(home);
            window.centerOnScreen();
        });
        GridPane gridAdmin = new GridPane();
        gridAdmin.getChildren().addAll(btnGoAdmin);

        GridPane btnHome = new GridPane();
        btnHome.getChildren().add(btnGoHome);

        sceneNavigationButton.getChildren().addAll(goAdmin,goHome,btnGoAdmin,btnHome,btnGoHome);

    }
    // Homepage
    void home( VBox sceneHome) {
        Button btnGoBack = new Button("Back");
        btnGoBack.setPadding(new Insets(5, 15, 5, 15));
        btnGoBack.setOnAction(actionEvent -> {
            window.setScene(navigationButton);
            window.centerOnScreen();
        });
        GridPane home = new GridPane();
        GridPane grid = new GridPane();
        GridPane goBack = new GridPane();
        GridPane btnBack = new GridPane();
        btnBack.getChildren().addAll(btnGoBack);
        goBack.add(btnBack, 2, 0);
        home.add( goBack,2,0);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        DBConnection DB = new DBConnection();
        ArrayList<Cake> fruitList = DB.getCake();
        //show
        for(int i = 0; i < fruitList.size(); i++){

            var btnBuy = new Button("Buy Now");
            Image image = new Image(fruitList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            Text textName = new Text(fruitList.get(i).getName());

            grid.add(imageView, 10, i+1);
            grid.add((textName), 15, i+1);
            grid.add(new Label ("Quality: "+String.valueOf(fruitList.get(i).getQuality())), 20, i+1);
            grid.add(new Label ("Price: $"+String.valueOf(fruitList.get(i).getPrice())), 30, i+1);
            grid.add((btnBuy), 50, i+1);
        }
        home.getChildren().add(grid);
        ScrollPane scrollHome = new ScrollPane(grid);
        scrollHome.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollHome.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollHome.setContent(grid);
        sceneHome.getChildren().addAll(home,scrollHome,btnGoBack,goBack);
    }


    @Override
    public void start(Stage primaryStage) {
        DBConnection conn = new DBConnection();
        VBox loginPage = new VBox();
        VBox sceneNavigationButton = new VBox();
        VBox sceneHome= new VBox();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        this.home(sceneHome);
        this.navigationButton(sceneNavigationButton);
        this.hompage(conn, grid, primaryStage);

        ScrollPane scrollHomepage = new ScrollPane(grid);
        // Setting a horizontal scroll bar is always display
        scrollHomepage.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        // Setting vertical scroll bar is never displayed.
        scrollHomepage.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollHomepage.setContent(grid);

        scene = new Scene(scrollHomepage, 1000, 600);
        home = new Scene(sceneHome, 1000,600);
        navigationButton = new Scene(sceneNavigationButton, 400, 400);
        primaryStage.setTitle("Bạn là?");
        window = primaryStage;
        window.setScene(navigationButton);
        window.show();
    }
}

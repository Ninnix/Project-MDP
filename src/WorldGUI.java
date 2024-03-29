
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Interfaccia grafica per inserire i prametri iniziali della popolazione e far partire la simulazione
 */
public class WorldGUI extends Application{
    /**
     * Invoca il metodo start che Lancia l'applicazione
     * @param args array degli argomenti
     */
    public static void main(String[] args) {
        Platform.runLater(() -> {
            new WorldGUI().start(new Stage());
        });
        //launch(args);  // Lancia l'applicazione, ritorna quando l'applicazione
    }                  // termina. Può essere invocato una sola volta

    /**
     * Metodo che apre la finestra dell'interfaccia e crea le varie componenti che la rendono utilizzabile
     * @param primaryStage finestra principale
     */
    @Override
    public void start(Stage primaryStage) {
        //settore iniziale con intestazioni         a   b   c
        Text t1= new Text("a");
        t1.setFont(new Font("Arial" , 20));
        t1.setFill(Color.rgb(0, 0, 0));
        Text t2= new Text("b");
        t2.setFont(new Font("Arial" , 20));
        t2.setFill(Color.rgb(0, 0, 0));
        Text t3= new Text("c");
        t3.setFont(new Font("Arial" , 20));
        t3.setFill(Color.rgb(0, 0, 0));
        HBox hb1= new HBox(t1,t2,t3);
        hb1.setAlignment(Pos.CENTER);
        hb1.setSpacing(200);

        //settore sottostante con campi testuali
        TextField tf1 = new TextField("");
        tf1.setMaxWidth(60);
        //il seguente listener permette di inserire solo numeri nel campo testuale
        tf1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        TextField tf2 = new TextField("");
        tf2.setMaxWidth(60);
        tf2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        TextField tf3 = new TextField("");
        tf3.setMaxWidth(60);
        tf3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf3.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        HBox hb2=new HBox(tf1,tf2,tf3);
        hb2.setAlignment(Pos.CENTER);
        hb2.setSpacing(150);

        // prima coppia testo-campo
        VBox vb1= new VBox(hb1,hb2);

        //intestazioni      morigerati     avventurieri    prudenti      spregiudicate
        Text t2_1= new Text("   Morigerati");
        t2_1.setFont(new Font("Arial" , 20));
        t2_1.setFill(Color.rgb(232, 108, 62));
        Text t2_2= new Text("Avventurieri");
        t2_2.setFont(new Font("Arial" , 20));
        t2_2.setFill(Color.rgb(237, 164, 46));
        Text t2_3= new Text("  Prudenti");
        t2_3.setFont(new Font("Arial" , 20));
        t2_3.setFill(Color.rgb(83, 169, 83));
        Text t2_4= new Text("Spregiudicate");
        t2_4.setFont(new Font("Arial" , 20));
        t2_4.setFill(Color.rgb(70, 162, 190));
        HBox hb3= new HBox(t2_1,t2_2,t2_3,t2_4);
        hb3.setAlignment(Pos.CENTER);
        hb3.setSpacing(100);

        //immagini    morigerati     avventurieri    prudenti      spregiudicate
        Image oba= new Image(getClass().getResource("Obama.jpg").toString());
        ImageView obaview= new ImageView(oba);
        obaview.setFitHeight(100);
        obaview.setFitWidth(100);

        Image trump= new Image(getClass().getResource("DonaldTrump.jpg").toString());
        ImageView trumpview = new ImageView(trump);
        trumpview.setFitHeight(100);
        trumpview.setFitWidth(110);

        Image clint= new Image(getClass().getResource("Clinton.jpg").toString());
        ImageView clintview = new ImageView(clint);
        clintview.setFitHeight(100);
        clintview.setFitWidth(100);

        Image merkel= new Image(getClass().getResource("Merkel.jpg").toString());
        ImageView merkelview = new ImageView(merkel);
        merkelview.setFitHeight(100);
        merkelview.setFitWidth(100);

        HBox himg= new HBox(obaview,trumpview,clintview,merkelview);
        himg.setAlignment(Pos.CENTER);
        himg.setSpacing(90);


        //settore sottostante con campi testuali
        TextField tf2_1 = new TextField("");
        tf2_1.setMaxWidth(60);
        tf2_1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf2_1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        TextField tf2_2 = new TextField("");
        tf2_2.setMaxWidth(60);
        tf2_2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf2_2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        TextField tf2_3 = new TextField("");
        tf2_3.setMaxWidth(60);
        tf2_3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf2_3.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        TextField tf2_4 = new TextField("");
        tf2_4.setMaxWidth(60);
        tf2_4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf2_4.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        HBox hb4=new HBox(tf2_1,tf2_2,tf2_3,tf2_4);
        hb4.setAlignment(Pos.CENTER);
        hb4.setSpacing(130);

        // seconda coppia testo-campo
        VBox vb2= new VBox(hb3,himg,hb4);

        //bottoni
        Button start = new Button("START");
        start.setFont(new Font("Arial" , 15));
        start.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        start.setOnAction(e -> {
            try {
                if(tf1.getText().equals("")){tf1.setText("0");}
                if(tf2.getText().equals("")){tf2.setText("0");}
                if(tf3.getText().equals("")){tf3.setText("0");}
                if(tf2_1.getText().equals("")){tf2_1.setText("0");}
                if(tf2_2.getText().equals("")){tf2_2.setText("0");}
                if(tf2_3.getText().equals("")){tf2_3.setText("0");}
                if(tf2_4.getText().equals("")){tf2_4.setText("0");}
                Popolazione p1 = new Popolazione(Integer.parseInt(tf1.getText()), Integer.parseInt(tf2.getText()),
                        Integer.parseInt(tf3.getText()), Integer.parseInt(tf2_1.getText()), Integer.parseInt(tf2_2.getText()),
                        Integer.parseInt(tf2_3.getText()), Integer.parseInt(tf2_4.getText()));
                corrente=p1;

                Platform.runLater(() -> {
                    new ChartGUI(p1).start(new Stage());
                });
            }catch (InvalidPopulationException f){}
        });


        Button def= new Button("Default");
        def.setFont(new Font("Arial" , 15));
        def.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        def.setOnAction(event -> {
            tf1.setText("15");
            tf2.setText("20");
            tf3.setText("3");
            tf2_1.setText("50");
            tf2_2.setText("50");
            tf2_3.setText("50");
            tf2_4.setText("50");
        });

        HBox hb5= new HBox(start,def);
        hb5.setAlignment(Pos.CENTER);
        hb5.setSpacing(100);

        //box finale
        VBox fin=new VBox(vb1,vb2,hb5);
        fin.setSpacing(100);
        fin.setBackground(new Background(new BackgroundFill(Color.rgb(244, 244, 244), CornerRadii.EMPTY, Insets.EMPTY))); //sfondo tinta unita
        /**
        String image = WorldGUI.class.getResource("World.jpg").toExternalForm();
        fin.setStyle("-fx-background-image: url('" + image + "'); " + "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
        */

        Parent root= fin;
        Scene scena = new Scene(root, 840, 500);

        primaryStage.setScene(scena);
        primaryStage.setTitle("La Battaglia Dei Sessi");
        primaryStage.show();  // Rende visibile la finestra (o stage) principale
    }

    Popolazione corrente;  //tiene traccia della popolazione corrente(quella che e' stata fatta partire piu' recentemente)
}

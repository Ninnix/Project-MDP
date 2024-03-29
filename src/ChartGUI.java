
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * GUI lanciata da WorldGUI che mostra i grafici della popolazione
 */
public class ChartGUI extends Application {
    //attributo
    Popolazione popolo;

    /**
     * Costuttore ChartGUI
     * @param popolo Popolazione
     */
    ChartGUI(Popolazione popolo){
        super();
        this.popolo=popolo;
    }

    /**
     * Il metodo statico void launch(String... args) di Application, lancia l'applicazione JavaFX
     * @param args array di argomenti
     */
    public static void main(String[] args) {
        launch(args);  // Lancia l'applicazione, ritorna quando l'applicazione
    }                  // termina. Può essere invocato una sola volta

    /**
     * Estende il metodo astratto abstract void start(Stage primaryStage) di Application
     * @param stage la finestra
     */
    @Override
    public void start(Stage stage) {
        Parent root = createChart(stage);
        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("La Battaglia dei Sessi");
        stage.setScene(scene);
        stage.show();
    }

    int type[] = new int[4]; //array di interi usata per l'animazione

    /**
     * Crea i due grafici i barChart1 (instogramma) e PieChart2 (Diagramma a torta)
     * @param stage finestra
     * @return root del grafo(scene graph) che è una struttura ad albero i cui nodi sono le componenti di una GUI
     */
    private Parent createChart(Stage stage) {

        Popolazione p1 = popolo;

        //Creo barChart1 (instogramma)
        VBox vBoxBarChart1 = new VBox();
        final CategoryAxis xAxis1 = new CategoryAxis();
        final NumberAxis yAxis1 = new NumberAxis();
        final BarChart<String, Number> barChart1 = new BarChart<>(xAxis1, yAxis1);
        barChart1.setCategoryGap(0);
        barChart1.setBarGap(0);
        barChart1.setMaxSize(500, 450);

        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("Morigerati", type[0]));
        series1.getData().add(new XYChart.Data("Avventurieri", type[1]));
        series1.getData().add(new XYChart.Data("Prundenti", type[2]));
        series1.getData().add(new XYChart.Data("Spregiudicate", type[3]));

        barChart1.getData().addAll(series1);
        barChart1.setAnimated(false);
        vBoxBarChart1.getChildren().addAll(barChart1);
        //- End of barChart1

        //Creo PieChart2 (Diagramma a torta)
        VBox vBoxPieChart2 = new VBox();

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("Morigerati", type[0]),
                new PieChart.Data("Avventurieri", type[1]),
                new PieChart.Data("Prudenti", type[2]),
                new PieChart.Data("Spregiudicate", type[3])
        );

        final PieChart pieChart2 = new PieChart(pieChartData);
        pieChart2.setTitle("La Battaglia dei Sessi");
        pieChart2.setMaxSize(500, 450);

        pieChart2.setAnimated(false);
        vBoxPieChart2.getChildren().addAll(pieChart2);
        //fine del pieChart2

        //bottone start/stop
        Button startStop = new Button("Stop");
        startStop.setOnAction(e -> {
             p1.stop();
             stage.close();
        });

        //Layout
        HBox hBoxCharts = new HBox();
        hBoxCharts.getChildren().addAll(vBoxBarChart1, vBoxPieChart2);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBoxCharts, startStop);
        vBox.setAlignment(Pos.CENTER);  // Allineamento dei nodi
        vBox.setSpacing(30); // Spazio tra i nodi

        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        //fine Layout

        //Tread che fa partire il mondo
        Thread startWorld = new Thread(()->{p1.start();});
        startWorld.start();

        //Applica l'animazione ai dati dei grafici(Charts)
        //Source: http://docs.oracle.com/javafx/2/charts/bar-chart.htm
        //Sezione "Animating Data in Charts"
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(300), (ActionEvent actionEvent) -> {
                    //aggiorno i grafici dei morigerati
                    series1.getData().set(0, new XYChart.Data("Morigerati", p1.morigerati.size()));
                    pieChartData.set(0, new PieChart.Data("Morigerati", p1.morigerati.size()));
                    //aggiorno i grafici degli avventurieri
                    series1.getData().set(1, new XYChart.Data("Avventurieri", p1.avventurieri.size()));
                    pieChartData.set(1, new PieChart.Data("Avventurieri",  p1.avventurieri.size()));
                    //aggiorno i grafici delle prudenti
                    series1.getData().set(2, new XYChart.Data("Prudenti", p1.prudenti.size()));
                    pieChartData.set(2, new PieChart.Data("Prudenti", p1.prudenti.size()));
                    //aggiorno i grafici delle spregiudicate
                    series1.getData().set(3, new XYChart.Data("Spregiudicate", p1.spregiudicate.size()));
                    pieChartData.set(3, new PieChart.Data("Spregiudicate", p1.spregiudicate.size()));
                }));

        timeline.setCycleCount(1000);
        timeline.setAutoReverse(true);  //!?
        timeline.play();
        return root;
    }
}

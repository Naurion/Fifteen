import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;

public class Start extends Application {
    private String sMaxSize;

    public void start(Stage startStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Text text = new Text("Enter max size of board:");
        TextField textField = new TextField();
        Button startButton = new Button();
        startButton.setText("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMaxSize = textField.getText();
                startStage.close();
                getMax();
            }
        });

        gridPane.add(text, 0, 0);
        gridPane.add(textField,0,1);
        gridPane.add(startButton, 0, 2);
        startStage.setScene(new Scene(gridPane));
        startStage.show();
    }

    public void getMax() {
        int maxSize = Integer.parseInt(sMaxSize);
        Visual.Launch(maxSize);
    }

    static void Launch() {
        launch();
    }
}

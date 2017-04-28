import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.HashMap;

public class Visual  {
    private static int mMax;
    private static HashMap<Integer,Button> buttons = new HashMap<Integer, Button>();
    private static Stage primaryStage = new Stage();


    private static void start() throws Exception {
        int counter = mMax*mMax-1;
        GridPane root = getGridPane();

        primaryStage.setTitle("Fefteen");
        primaryStage.setScene(new Scene(root));

        for (int i = 0; i < mMax*mMax; i++) {createButton(i);}

        for (int i = 0; i < mMax; i++) {
            for (int j = 0; j < mMax; j++) {
                root.add(buttons.get(counter--), j, i);
            }
        }

        if (mMax % 2 == 0) {
            changeButons();
        }

        primaryStage.show();
    }

    private static GridPane getGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        return gridPane;
    }

    private static void createButton(int i) {
        buttons.put(i, new Button());
        buttons.get(i).setText("" + i);
        buttons.get(i).setId("" + i);
        buttons.get(i).setPrefSize(40, 40);
        if (i == 0) {
            buttons.get(0).setVisible(false);
        }
        String mButtonId = buttons.get(i).getId();
        buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(mButtonId);
            }
        });
    }

    private static boolean isWon() {
        int counter = 0;
        boolean bln = true;
        for (int i = 0; i < mMax; i++) {
            for (int j = 0; j < mMax; j++) {
                counter++;
                if (counter < mMax * mMax) {
                    if (GridPane.getRowIndex(buttons.get(counter)) == i
                            && GridPane.getColumnIndex(buttons.get(counter)) == j) {
                    } else bln = false;
                }
            }
        }
        if (bln && GridPane.getRowIndex(buttons.get(0)) == mMax - 1
                && GridPane.getColumnIndex(buttons.get(0)) == mMax - 1) {
            bln = true;
        }
        return bln;
    }

    private static void Won() {
        Stage winStage = new Stage();
        GridPane mWonPane = new GridPane();
        mWonPane.setAlignment(Pos.CENTER);
        Button wonBtn = new Button();
        winStage.setTitle("You WIN!!!");
        wonBtn.setText("Click!");
        wonBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                winStage.close();
                primaryStage.close();
            }
        });
        mWonPane.add(wonBtn, 1, 1);
        winStage.setScene(new Scene(mWonPane));
        winStage.show();
    }

    private static void makeMove(String mButtonId) {
        int mRow0 = GridPane.getRowIndex(buttons.get(0));
        int mColumn0 = GridPane.getColumnIndex(buttons.get(0));
        int mRow = GridPane.getRowIndex(buttons.get(Integer.parseInt(mButtonId)));
        int mColumn = GridPane.getColumnIndex(buttons.get(Integer.parseInt(mButtonId)));
        if(mRow == mRow0 && (mColumn == mColumn0+1 || mColumn == mColumn0-1) || mColumn == mColumn0 && (mRow == mRow0+1 || mRow == mRow0-1)){
            GridPane.setConstraints(buttons.get(0), mColumn, mRow);
            GridPane.setConstraints(buttons.get(Integer.parseInt(mButtonId)),mColumn0, mRow0);
        }
        if (isWon()) {
            Won();
        }
    }

    private static void changeButons() {
        int mRow1 = GridPane.getRowIndex(buttons.get(1));
        int mColumn1 = GridPane.getColumnIndex(buttons.get(1));
        int mRow2 = GridPane.getRowIndex(buttons.get(2));
        int mColumn2 = GridPane.getColumnIndex(buttons.get(2));
        GridPane.setConstraints(buttons.get(1), mColumn2, mRow2);
        GridPane.setConstraints(buttons.get(2),mColumn1, mRow1);
    }

    static void Launch(int maxSize){
        mMax = maxSize;
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

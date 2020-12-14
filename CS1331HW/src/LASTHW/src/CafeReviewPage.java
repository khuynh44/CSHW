import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Program that allows users to enter blogs and post reviews about the cafe.
 * @author Kevin Huynh
 * @version 1.0
 */
public class CafeReviewPage extends Application {
    /** The x coordinate of the post. */
    private int x = 10;

    /** The y coordinate of the post. */
    private int y = 10;

    /** The name of the person posting. */
    private TextField name = new TextField();

    /** The feedback from the poster. */
    private TextField feedback = new TextField();

    /** The color of the text. */
    private TextField color = new TextField();

    /** The first pane that holds the background. */
    private StackPane pane0 = new StackPane();

    /** The second pane formats the nodes. */
    private BorderPane pane = new BorderPane();

    /** Pane that places the posts on the top of the page. */
    private Pane pane1 = new Pane();

    /** Pane that holds the text, color, name, and post button. */
    private FlowPane pane2 = new FlowPane();

    /**
     * Starts the program by creating a window showing the blog.
     * @param primary The window which it creates the window.
     */
    public void start(Stage primary) {
        Scene scene = new Scene(pane0, 800, 500);
        Image pic = new Image("file:Yelp.jpg");
        ImageView image = new ImageView(pic);
        image.setFitHeight(500);
        image.setFitWidth(800);
        name.setPromptText("Name");
        feedback.setPromptText("Feedback");
        color.setPromptText("Color");
        Button btn = new Button("Post");
        btn.setOnAction(new buttonHandler());
        name.setOnAction(new buttonHandler());
        feedback.setOnAction(new buttonHandler());
        color.setOnAction(new buttonHandler());
        pane0.getChildren().add(image);
        pane0.getChildren().add(pane);
        pane2.getChildren().addAll(name, feedback, color, btn);
        pane.setBottom(pane2);
        pane.setTop(pane1);
        primary.setScene(scene);
        primary.setTitle("Cafe1331 Reviews");
        primary.show();
    }

    /**
     * Class that handles events triggered from the stage window.
     */
    class buttonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            String nam = name.getText();
            String fb = feedback.getText();
            Text txt;
            Color c;
            if (!fb.equals("")) {
                if (nam.equals("")) {
                    nam = "Anonymous";
                }
                try {
                    String col = color.getText().toUpperCase();
                    c = Color.valueOf(col);
                } catch (Exception e) {
                    c = Color.BLACK;
                }
                y += 20;
                txt = new Text(x, y, nam + ": " + fb);
                txt.setFill(c);
                pane1.getChildren().add(txt);
            }
        }
    }
}

package com.main;

import java.util.HashMap;

import com.main.config.Config;
import com.main.game.data.GameSettingDataMap;
import com.main.model.GameScreenType;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Angie
 */
public class ScreensController extends StackPane {
    // Holds the screens to be displayed

    private HashMap<GameScreenType, Node> screens = new HashMap<>();

    public ScreensController() {
        super();
    }

    // Add the screen to the collection
    public void addScreen(GameScreenType gameScreenType, Node screen) {
        screens.put(gameScreenType, screen);
    }

    // Returns the Node with the appropriate name
    public Node getScreen(GameScreenType gameScreenType) {
        return screens.get(gameScreenType);
    }

    // finally injects the screenPane to the controller.
    public boolean loadScreen(GameScreenType gameScreenType, String resource) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            Scene scene = new Scene(loadScreen, Config.STAGE_WIDTH, Config.STAGE_HEIGHT);
            primaryStage.setScene(scene);
            ControlledScreen myScreenControler = ((ControlledScreen) myLoader.getController());
            myScreenControler.setScreenParent(this);
            addScreen(gameScreenType, loadScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // This method tries to displayed the screen with a predefined name.
    // First it makes sure the screen has been already loaded. Then if there is
    // more than
    // one screen the new screen is been added second, and then the current
    // screen is removed.
    // If there isn't any screen being displayed, the new screen is just added
    // to the root.
    public boolean setScreen(final GameScreenType gameScreenType) {
        // force the method to load the screen
        if (screens.get(gameScreenType) == null) {
            loadScreen(gameScreenType, GameSettingDataMap.getFileName(gameScreenType));
        }

        if (screens.get(gameScreenType) != null) { // screen loaded
            final DoubleProperty opacity = opacityProperty();
            if (!getChildren().isEmpty()) { // if there is more than one screen
<<<<<<< HEAD
                Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
=======
                Timeline fade =
                        new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
>>>>>>> 38e31749abd80f5cbea4c212fd4d6f58bfe05a4b
                        new KeyFrame(new Duration(80), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                getChildren().remove(0); // remove the displayed
                                getChildren().add(0, screens.get(gameScreenType));
<<<<<<< HEAD
                                Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
=======
                                Timeline fadeIn =
                                        new Timeline(new KeyFrame(Duration.ZERO,
                                                new KeyValue(opacity, 0.0)),
>>>>>>> 38e31749abd80f5cbea4c212fd4d6f58bfe05a4b
                                        new KeyFrame(new Duration(80), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(gameScreenType)); // no one else been
<<<<<<< HEAD
                Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
=======
                Timeline fadeIn =
                        new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
>>>>>>> 38e31749abd80f5cbea4c212fd4d6f58bfe05a4b
                        new KeyFrame(new Duration(80), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }

        /*
         * Node screenToRemove; if(screens.get(name) != null){ //screen loaded
         * if(!getChildren().isEmpty()){ //if there is more than one screen
         * getChildren().add(0, screens.get(name)); //add the screen
         * screenToRemove = getChildren().get(1); getChildren().remove(1);
         * //remove the displayed screen }else{
         * getChildren().add(screens.get(name)); //no one else been displayed,
         * then just show } return true; }else { System.out.println(
         * "screen hasn't been loaded!!! \n"); return false; }
         */
    }

    // This method will remove the screen with the given name from the
    // collection of screens
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
}
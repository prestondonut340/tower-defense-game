package com.test;

import com.main.MainApplication;
import com.main.game.DataController;
import com.main.game.data.GameSettingDataMap;
import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class ConfigurationScreenTest extends ApplicationTest {
    private MainApplication app;
    private DataController dataController;
    private Pane mainNode;
    private Stage mainstage;

    @Override
    public void start(Stage stage) throws Exception {
        mainNode = (Pane) FXMLLoader.load(
                MainApplication.class.getResource(
                        GameSettingDataMap.getFileName(GameScreenType.CONFIG_SCREEN)
                )
        );
        mainstage = stage;
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp() throws Exception {
        dataController = MainApplication.getDataController();
    }

    @Test
    public void testPlayerNameValidityWithBlank() {
        dataController.setPlayerName(" "); //test invalid player name input
        assertFalse(dataController.isPlayerNameValid(dataController.getPlayerName()));
        assertFalse(dataController.isAbleToGoGameScreen());
    }

    @Test
    public void testPlayerNameValidityWithValidName() {
        dataController.setGameLevel(GameLevelType.EASY);
        dataController.setPlayerName("James Bond"); //test invalid player name input
        assertTrue(dataController.isPlayerNameValid(dataController.getPlayerName()));
        assertTrue(dataController.isAbleToGoGameScreen());
    }

    @Test
    public void testHardModeSetting() {
        System.out.println(mainNode);
        GameLevelType level = GameLevelType.HARD;
        dataController.setGameLevel(level);
        dataController.setPlayerName("King Doma");
        assertEquals(
                dataController.getGameMoney(),
                GameSettingDataMap.getStartingMoney(level)
        );
        assertEquals(
                dataController.getEnemyMonumentHealth(),
                GameSettingDataMap.getStartingMonumentHealth(level)
        );
    }

    @Test
    public void testNormalModeSetting() {
        System.out.println(mainNode);
        GameLevelType level = GameLevelType.NORMAL;
        dataController.setGameLevel(level);
        dataController.setPlayerName("King Doma");
        assertEquals(
                dataController.getGameMoney(),
                GameSettingDataMap.getStartingMoney(level)
        );
        assertEquals(
                dataController.getEnemyMonumentHealth(),
                GameSettingDataMap.getStartingMonumentHealth(level)
        );
    }

    @Test
    public void testEasyModeSetting() {
        System.out.println(mainNode);
        GameLevelType level = GameLevelType.EASY;
        dataController.setGameLevel(level);
        dataController.setPlayerName("King Doma");
        assertEquals(
                dataController.getGameMoney(),
                GameSettingDataMap.getStartingMoney(level)
        );
        assertEquals(
                dataController.getEnemyMonumentHealth(),
                GameSettingDataMap.getStartingMonumentHealth(level)
        );
    }
}

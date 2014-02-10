package zombiecrushsaga.ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import zombiecrushsaga.data.ZombieCrushSagaDataModel;

import mini_game.MiniGame;


import static zombiecrushsaga.ZombieCrushSagaConstants.*;
import mini_game.Sprite;
import mini_game.SpriteType;
import properties_manager.PropertiesManager;
import zombiecrushsaga.ZombieCrushSaga.ZombieCrushSagaPropertyType;
import zombiecrushsaga.file.ZombieCrushSagaFileManager;
import zombiecrushsaga.ui.ZombieCrushSagaErrorHandler;
import zombiecrushsaga.ui.ZombieCrushSagaPanel;
import zombiecrushsaga.data.ZombieCrushSagaRecord;
import zombiecrushsaga.events.ClickExitGameHandler;
import zombiecrushsaga.events.ClickXGameHandler;
import zombiecrushsaga.events.ExitHandler;

import zombiecrushsaga.events.ChangeCursorHandler;
import zombiecrushsaga.events.NewGameHandler;
import zombiecrushsaga.events.SelectLevelHandler;
import zombiecrushsaga.events.StatsHandler;
import zombiecrushsaga.events.UndoHandler;
import zombiecrushsaga.events.BackHandler;
import zombiecrushsaga.events.PlayHandler;
import zombiecrushsaga.events.UpHandler;
import zombiecrushsaga.events.DownHandler;
import zombiecrushsaga.events.GoIntoGameHandler;
import zombiecrushsaga.events.ZombieCrushSagaKeyHandler;


public class ZombieCrushSagaMiniGame extends MiniGame {
	
	
	
	private ZombieCrushSagaRecord record;

	// HANDLES ERROR CONDITIONS
	private ZombieCrushSagaErrorHandler errorHandler;

	// MANAGES LOADING OF LEVELS AND THE PLAYER RECORDS FILES
	private ZombieCrushSagaFileManager fileManager;

	// THE SCREEN CURRENTLY BEING PLAYED
	private String currentScreenState;

	// ACCESSOR METHODS
	// - getPlayerRecord
	// - getErrorHandler
	// - getFileManager
	// - isCurrentScreenState

	/**
	 * Accessor method for getting the player record object, which summarizes
	 * the player's record on all levels.
	 * 
	 * @return The player's complete record.
	 */
	public ZombieCrushSagaRecord getPlayerRecord() {
		return record;
	}

	/**
	 * Accessor method for getting the application's error handler.
	 * 
	 * @return The error handler.
	 */
	public ZombieCrushSagaErrorHandler getErrorHandler() {
		return errorHandler;
	}

	/**
	 * Accessor method for getting the app's file manager.
	 * 
	 * @return The file manager.
	 */
	public ZombieCrushSagaFileManager getFileManager() {
		return fileManager;
	}

	/**
	 * Used for testing to see if the current screen state matches the
	 * testScreenState argument. If it mates, true is returned, else false.
	 * 
	 * @param testScreenState
	 *            Screen state to test against the current state.
	 * 
	 * @return true if the current state is testScreenState, false otherwise.
	 */
	public boolean isCurrentScreenState(String testScreenState) {
		return testScreenState.equals(currentScreenState);
	}

	// SERVICE METHODS
	// - displayStats
	// - savePlayerRecord
	// - switchToGameScreen
	// - switchToSagaScreen
	// - updateBoundaries

	/**
	 * This method displays makes the stats dialog display visible, which
	 * includes the text inside.
	 */

	
	
	
	public String getState(){
		return currentScreenState;
	}
	
	


	/**
	 * This method forces the file manager to save the current player record.
	 */
	public void savePlayerRecord() {
		// THIS CURRENTLY DOES NOTHING, INSTEAD, IT MUST SAVE ALL THE
		// PLAYER RECORDS IN THE SAME FORMAT IT IS BEING LOADED
		// YIDI KE 10/10/2013
		try {
			PropertiesManager props = PropertiesManager.getPropertiesManager();
			String dataPath = props
					.getProperty(ZombieCrushSagaPropertyType.DATA_PATH);
			String recordPath = dataPath
					+ props.getProperty(ZombieCrushSagaPropertyType.RECORD_FILE_NAME);
			// File fileToOpen = new File(recordPath);
			byte[] bytes = record.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			FileOutputStream fis = new FileOutputStream(recordPath);
			BufferedOutputStream bis = new BufferedOutputStream(fis);
			DataOutputStream out = new DataOutputStream(bis);
			out.write(bytes, 0, bytes.length);
			out.close();
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {

		}

	}
	
	public void switchToSplashScreen() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();

		// CHANGE THE BACKGROUND
		guiDecor.get(BACKGROUND_TYPE).setState(SPLASH_SCREEN_STATE);

		// ACTIVATE THE TOOLBAR AND ITS CONTROLS
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(BACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TYPE).setEnabled(false);
		guiDecor.get(TIME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(UNDO_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UNDO_TYPE).setEnabled(false);
		guiButtons.get(A_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(A_TYPE).setEnabled(false);
		
		guiButtons.get(MALLET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(MALLET_TYPE).setEnabled(false);
		guiButtons.get(MALLET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(MALLET_TYPE).setEnabled(false);
		
		guiDecor.get(TILE_COUNT_TYPE).setState(INVISIBLE_STATE);
		guiDecor.get(TILE_COUNT_TYPE).setEnabled(false);
		guiDecor.get(TILE_STACK_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(PLAY_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(PLAY_TYPE).setEnabled(true);
		guiButtons.get(RESET_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(RESET_TYPE).setEnabled(true);
		guiButtons.get(QUIT_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(QUIT_TYPE).setEnabled(true);
		guiDecor.get(PROGRESS_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(X_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(X_TYPE).setEnabled(false);
		guiButtons.get(UP_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UP_TYPE).setEnabled(false);
		guiButtons.get(DOWN_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(DOWN_TYPE).setEnabled(false);
		// DEACTIVATE THE LEVEL SELECT BUTTONS

		guiButtons.get(FAIL_TYPE1).setState(INVISIBLE_STATE);
		guiButtons.get(FAIL_TYPE1).setEnabled(false);
		guiButtons.get(FAIL_TYPE2).setState(INVISIBLE_STATE);
		guiButtons.get(FAIL_TYPE2).setEnabled(false);
		// MOVE THE TILES TO THE STACK AND MAKE THEM VISIBLE
		//((ZombieCrushSagaDataModel) data).enableTiles(true);
		//data.reset(this);

		// AND CHANGE THE SCREEN STATE
		currentScreenState = SPLASH_SCREEN_STATE;

		// PLAY THE GAMEPLAY SCREEN SONG
		audio.stop(ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE
				.toString());
		audio.play(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE.toString(),
				true);
	}
	
	public void switchToYouWinScreen() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();

		// CHANGE THE BACKGROUND
		guiDecor.get(BACKGROUND_TYPE).setState(YOUWIN_STATE);

		// ACTIVATE THE TOOLBAR AND ITS CONTROLS
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(BACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TYPE).setEnabled(false);
		guiDecor.get(TIME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(UNDO_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UNDO_TYPE).setEnabled(false);
		guiButtons.get(A_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(A_TYPE).setEnabled(true);
		
		guiButtons.get(MALLET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(MALLET_TYPE).setEnabled(false);
		
		
		guiDecor.get(TILE_COUNT_TYPE).setState(INVISIBLE_STATE);
		guiDecor.get(TILE_COUNT_TYPE).setEnabled(false);
		guiDecor.get(TILE_STACK_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(PLAY_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(PLAY_TYPE).setEnabled(false);
		guiButtons.get(RESET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(RESET_TYPE).setEnabled(false);
		guiButtons.get(QUIT_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(QUIT_TYPE).setEnabled(false);
		guiDecor.get(PROGRESS_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(X_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(X_TYPE).setEnabled(false);
		guiButtons.get(UP_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UP_TYPE).setEnabled(false);
		guiButtons.get(DOWN_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(DOWN_TYPE).setEnabled(false);
		// DEACTIVATE THE LEVEL SELECT BUTTONS
		
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setEnabled(false);


		// MOVE THE TILES TO THE STACK AND MAKE THEM VISIBLE
		((ZombieCrushSagaDataModel) data).enableTiles(false);
		//data.reset(this);

		// AND CHANGE THE SCREEN STATE
		currentScreenState = YOUWIN_STATE;

		// PLAY THE GAMEPLAY SCREEN SONG
		audio.stop(ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE
				.toString());
		audio.play(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE.toString(),
				true);
	}
	
	public void switchToYouFailScreen() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();

		// CHANGE THE BACKGROUND
		guiDecor.get(BACKGROUND_TYPE).setState(YOUFAIL_STATE);

		// ACTIVATE THE TOOLBAR AND ITS CONTROLS
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(BACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TYPE).setEnabled(false);
		guiDecor.get(TIME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(UNDO_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UNDO_TYPE).setEnabled(false);
		
		guiButtons.get(A_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(A_TYPE).setEnabled(false);
		guiButtons.get(MALLET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(MALLET_TYPE).setEnabled(false);
		
		
		guiDecor.get(TILE_COUNT_TYPE).setState(INVISIBLE_STATE);
		guiDecor.get(TILE_COUNT_TYPE).setEnabled(false);
		guiDecor.get(TILE_STACK_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(PLAY_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(PLAY_TYPE).setEnabled(false);
		guiButtons.get(RESET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(RESET_TYPE).setEnabled(false);
		guiButtons.get(QUIT_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(QUIT_TYPE).setEnabled(false);
		guiDecor.get(PROGRESS_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(X_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(X_TYPE).setEnabled(false);
		guiButtons.get(UP_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UP_TYPE).setEnabled(false);
		guiButtons.get(DOWN_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(DOWN_TYPE).setEnabled(false);
		// DEACTIVATE THE LEVEL SELECT BUTTONS
		
		guiButtons.get(FAIL_TYPE1).setState(VISIBLE_STATE);
		guiButtons.get(FAIL_TYPE1).setEnabled(true);
		guiButtons.get(FAIL_TYPE2).setState(VISIBLE_STATE);
		guiButtons.get(FAIL_TYPE2).setEnabled(true);
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setEnabled(false);

		((ZombieCrushSagaDataModel) data).enableTiles(false);
		// MOVE THE TILES TO THE STACK AND MAKE THEM VISIBLE
		//((ZombieCrushSagaDataModel) data).enableTiles(true);
		//data.reset(this);

		// AND CHANGE THE SCREEN STATE
		currentScreenState = YOUFAIL_STATE;

		// PLAY THE GAMEPLAY SCREEN SONG
		audio.stop(ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE
				.toString());
		audio.play(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE.toString(),
				true);
	}
	
	
	
	
	public void switchToStatScreen() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();

		// CHANGE THE BACKGROUND
		guiDecor.get(BACKGROUND_TYPE).setState(STATS_SCREEN_STATE);

		// ACTIVATE THE TOOLBAR AND ITS CONTROLS
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(BACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TYPE).setEnabled(false);
		guiDecor.get(TIME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(UNDO_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UNDO_TYPE).setEnabled(false);
		guiDecor.get(TILE_COUNT_TYPE).setState(INVISIBLE_STATE);
		guiDecor.get(TILE_COUNT_TYPE).setEnabled(false);
		guiDecor.get(TILE_STACK_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(PLAY_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(PLAY_TYPE).setEnabled(false);
		guiButtons.get(RESET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(RESET_TYPE).setEnabled(false);
		guiButtons.get(QUIT_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(QUIT_TYPE).setEnabled(false);
		
		
		guiButtons.get(X_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(X_TYPE).setEnabled(false);
		guiButtons.get(UP_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UP_TYPE).setEnabled(false);
		guiButtons.get(DOWN_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(DOWN_TYPE).setEnabled(false);
		
		guiButtons.get(MALLET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(MALLET_TYPE).setEnabled(false);
		guiButtons.get(A_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(A_TYPE).setEnabled(false);
		
		guiButtons.get(GO_INTO_GAME_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(GO_INTO_GAME_TYPE).setEnabled(true);
		guiButtons.get(BACK_TO_SAGA_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(BACK_TO_SAGA_TYPE).setEnabled(true);

		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setEnabled(false);
		
		guiButtons.get(FAIL_TYPE1).setState(INVISIBLE_STATE);
		guiButtons.get(FAIL_TYPE1).setEnabled(false);
		guiButtons.get(FAIL_TYPE2).setState(INVISIBLE_STATE);
		guiButtons.get(FAIL_TYPE2).setEnabled(false);
		guiDecor.get(PROGRESS_TYPE).setState(INVISIBLE_STATE);
		

		// DEACTIVATE THE LEVEL SELECT BUTTONS
		
/*		ArrayList<String> levels = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);*/
		for (String level : LEVEL_NAME) {
			guiButtons.get(level).setState(INVISIBLE_STATE);
			guiButtons.get(level).setEnabled(false);
		}

		// MOVE THE TILES TO THE STACK AND MAKE THEM VISIBLE
		((ZombieCrushSagaDataModel) data).enableTiles(false);
		//data.reset(this);

		// AND CHANGE THE SCREEN STATE
		currentScreenState = STATS_SCREEN_STATE;

		// PLAY THE GAMEPLAY SCREEN SONG
		audio.stop(ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE
				.toString());
		audio.play(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE.toString(),
				true);
	}
	
	
	
	
	
	
	public void switchToFinishScreen() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();

		// CHANGE THE BACKGROUND
		guiDecor.get(BACKGROUND_TYPE).setState(FINISH_SCREEN_STATE);

		// ACTIVATE THE TOOLBAR AND ITS CONTROLS
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(BACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TYPE).setEnabled(false);
		guiDecor.get(TIME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(UNDO_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UNDO_TYPE).setEnabled(false);
		guiDecor.get(TILE_COUNT_TYPE).setState(INVISIBLE_STATE);
		guiDecor.get(TILE_COUNT_TYPE).setEnabled(false);
		guiDecor.get(TILE_STACK_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(PLAY_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(PLAY_TYPE).setEnabled(false);
		guiButtons.get(RESET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(RESET_TYPE).setEnabled(false);
		guiButtons.get(QUIT_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(QUIT_TYPE).setEnabled(false);
		
		
		guiButtons.get(X_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(X_TYPE).setEnabled(false);
		guiButtons.get(UP_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UP_TYPE).setEnabled(false);
		guiButtons.get(DOWN_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(DOWN_TYPE).setEnabled(false);
		
		
		
		guiButtons.get(GO_INTO_GAME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(GO_INTO_GAME_TYPE).setEnabled(false);
		guiButtons.get(BACK_TO_SAGA_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(BACK_TO_SAGA_TYPE).setEnabled(true);

		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setEnabled(false);
		
		
		guiDecor.get(PROGRESS_TYPE).setState(INVISIBLE_STATE);
		

		// DEACTIVATE THE LEVEL SELECT BUTTONS
		
/*		ArrayList<String> levels = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);*/
		for (String level : LEVEL_NAME) {
			guiButtons.get(level).setState(INVISIBLE_STATE);
			guiButtons.get(level).setEnabled(false);
		}

		// MOVE THE TILES TO THE STACK AND MAKE THEM VISIBLE
		//((ZombieCrushSagaDataModel) data).enableTiles(true);
		//data.reset(this);

		// AND CHANGE THE SCREEN STATE
		currentScreenState = FINISH_SCREEN_STATE;

		// PLAY THE GAMEPLAY SCREEN SONG
		audio.stop(ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE
				.toString());
		audio.play(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE.toString(),
				true);
	}
	
	
	
	
	
	
	
	
	
	
	public void switchToWrongLevelScreen() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();

		// CHANGE THE BACKGROUND
		guiDecor.get(BACKGROUND_TYPE).setState(FINISH_SCREEN_STATE);

		// ACTIVATE THE TOOLBAR AND ITS CONTROLS
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(BACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TYPE).setEnabled(false);
		guiDecor.get(TIME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(UNDO_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UNDO_TYPE).setEnabled(false);
		guiDecor.get(TILE_COUNT_TYPE).setState(INVISIBLE_STATE);
		guiDecor.get(TILE_COUNT_TYPE).setEnabled(false);
		guiDecor.get(TILE_STACK_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(PLAY_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(PLAY_TYPE).setEnabled(false);
		guiButtons.get(RESET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(RESET_TYPE).setEnabled(false);
		guiButtons.get(QUIT_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(QUIT_TYPE).setEnabled(false);
		
		
		guiButtons.get(X_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(X_TYPE).setEnabled(false);
		guiButtons.get(UP_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UP_TYPE).setEnabled(false);
		guiButtons.get(DOWN_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(DOWN_TYPE).setEnabled(false);
		guiButtons.get(MALLET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(MALLET_TYPE).setEnabled(false);
		
		
		guiButtons.get(GO_INTO_GAME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(GO_INTO_GAME_TYPE).setEnabled(false);
		guiButtons.get(BACK_TO_SAGA_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(BACK_TO_SAGA_TYPE).setEnabled(true);

		//guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setState(INVISIBLE_STATE);
		//guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setEnabled(false);
		
		
		guiDecor.get(PROGRESS_TYPE).setState(INVISIBLE_STATE);
		

		// DEACTIVATE THE LEVEL SELECT BUTTONS
		
/*		ArrayList<String> levels = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);*/
		for (String level : LEVEL_NAME) {
			guiButtons.get(level).setState(INVISIBLE_STATE);
			guiButtons.get(level).setEnabled(false);
		}

		// MOVE THE TILES TO THE STACK AND MAKE THEM VISIBLE
		//((ZombieCrushSagaDataModel) data).enableTiles(true);
	//	data.reset(this);

		// AND CHANGE THE SCREEN STATE
		currentScreenState = FINISH_SCREEN_STATE;

		// PLAY THE GAMEPLAY SCREEN SONG
		audio.stop(ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE
				.toString());
		audio.play(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE.toString(),
				true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * This method switches the application to the game screen, making all the
	 * appropriate UI controls visible & invisible.
	 */
	public void switchToGameScreen() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();

		// CHANGE THE BACKGROUND
		guiDecor.get(BACKGROUND_TYPE).setState(GAME_SCREEN_STATE);

		// ACTIVATE THE TOOLBAR AND ITS CONTROLS
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(BACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TYPE).setEnabled(false);
		guiDecor.get(TIME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(UNDO_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(UNDO_TYPE).setEnabled(false);
		guiDecor.get(TILE_COUNT_TYPE).setState(VISIBLE_STATE);
		guiDecor.get(TILE_COUNT_TYPE).setEnabled(false);
		guiDecor.get(TILE_STACK_TYPE).setState(VISIBLE_STATE);
		guiDecor.get(PROGRESS_TYPE).setState(VISIBLE_STATE);
		
		guiButtons.get(A_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(A_TYPE).setEnabled(false);
		
		guiButtons.get(GO_INTO_GAME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(GO_INTO_GAME_TYPE).setEnabled(false);
		guiButtons.get(BACK_TO_SAGA_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TO_SAGA_TYPE).setEnabled(false);
		


		
		guiButtons.get(MALLET_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(MALLET_TYPE).setEnabled(true);
		
		guiButtons.get(FAIL_TYPE1).setState(INVISIBLE_STATE);
		guiButtons.get(FAIL_TYPE1).setEnabled(false);
		guiButtons.get(FAIL_TYPE2).setState(INVISIBLE_STATE);
		guiButtons.get(FAIL_TYPE2).setEnabled(false);
		
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setEnabled(true);
		

		// DEACTIVATE THE LEVEL SELECT BUTTONS
		/*ArrayList<String> levels = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);*/
		for (String level : LEVEL_NAME) {
			guiButtons.get(level).setState(INVISIBLE_STATE);
			guiButtons.get(level).setEnabled(false);
		}

		// MOVE THE TILES TO THE STACK AND MAKE THEM VISIBLE
		((ZombieCrushSagaDataModel) data).enableTiles(true);
		data.reset(this);

		// AND CHANGE THE SCREEN STATE
		currentScreenState = GAME_SCREEN_STATE;

		// PLAY THE GAMEPLAY SCREEN SONG
		audio.stop(ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE
				.toString());
		audio.play(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE.toString(),
				true);
	}

	/**
	 * This method switches the application to the splash screen, making all the
	 * appropriate UI controls visible & invisible.
	 */
	public void switchToSagaScreen() {
		// CHANGE THE BACKGROUND

		guiDecor.get(BACKGROUND_TYPE).setState(SAGA_SCREEN_STATE);

		// DEACTIVATE THE TOOLBAR CONTROLS
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(BACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TYPE).setEnabled(false);
		guiDecor.get(TIME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(UNDO_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UNDO_TYPE).setEnabled(false);
		guiDecor.get(TILE_COUNT_TYPE).setState(INVISIBLE_STATE);
		guiDecor.get(TILE_COUNT_TYPE).setEnabled(false);
		guiDecor.get(TILE_STACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(MALLET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(MALLET_TYPE).setEnabled(false);
		guiButtons.get(A_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(A_TYPE).setEnabled(false);
		
		guiDecor.get(PROGRESS_TYPE).setState(INVISIBLE_STATE);
		
		guiButtons.get(PLAY_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(PLAY_TYPE).setEnabled(false);
		guiButtons.get(RESET_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(RESET_TYPE).setEnabled(false);
		guiButtons.get(QUIT_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(QUIT_TYPE).setEnabled(false);
		
		
		
		guiButtons.get(X_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(X_TYPE).setEnabled(true);
		guiButtons.get(UP_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(UP_TYPE).setEnabled(true);
		guiButtons.get(DOWN_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(DOWN_TYPE).setEnabled(true);
		
		guiButtons.get(GO_INTO_GAME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(GO_INTO_GAME_TYPE).setEnabled(false);
		guiButtons.get(BACK_TO_SAGA_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TO_SAGA_TYPE).setEnabled(false);
		
		
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setEnabled(false);

		// ACTIVATE THE LEVEL SELECT BUTTONS
		// DEACTIVATE THE LEVEL SELECT BUTTONS
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		/*ArrayList<String> levels = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);*/
		/*for (String level : LEVEL_NAME) {
			guiButtons.get(level).setState(VISIBLE_STATE);
			guiButtons.get(level).setEnabled(true);
		}*/
		//
		guiButtons.get(LEVEL_NAME[0]).setState(VISIBLE_STATE);
        guiButtons.get(LEVEL_NAME[0]).setEnabled(true);
		
		//
		for(int i=1;i<LEVEL_NAME.length;i++){
	        if(LEVEL_LOCK[i]){
	                guiButtons.get(LEVEL_NAME[i]).setState(VISIBLE_STATE);
	                guiButtons.get(LEVEL_NAME[i]).setEnabled(true);
	            }
	        else{guiButtons.get(LEVEL_NAME[i]).setState(INCOMPLETE_STATE);
            guiButtons.get(LEVEL_NAME[i]).setEnabled(false);}
	        }
		
//
		// DEACTIVATE ALL DIALOGS
		guiDialogs.get(WIN_DIALOG_TYPE).setState(INVISIBLE_STATE);
		guiDialogs.get(LOSE_DIALOG_TYPE).setState(INVISIBLE_STATE);
		guiDialogs.get(STATS_DIALOG_TYPE).setState(INVISIBLE_STATE);

		// HIDE THE TILES
		((ZombieCrushSagaDataModel) data).enableTiles(false);

		// MAKE THE CURRENT SCREEN THE SPLASH SCREEN
		currentScreenState = SAGA_SCREEN_STATE;

		// PLAY THE WELCOME SCREEN SONG
		//audio.play(
	//			ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE.toString(),
	//			true);
	//	audio.stop(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE.toString());
	}
	
	
	
	/*public void switchToSagaScreen() {
		// CHANGE THE BACKGROUND
		guiDecor.get(BACKGROUND_TYPE).setState(SPLASH_SCREEN_STATE);

		// DEACTIVATE THE TOOLBAR CONTROLS
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(NEW_GAME_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(BACK_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(BACK_TYPE).setEnabled(false);
		guiDecor.get(TIME_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(STATS_BUTTON_TYPE).setEnabled(false);
		guiButtons.get(UNDO_TYPE).setState(INVISIBLE_STATE);
		guiButtons.get(UNDO_TYPE).setEnabled(false);
		guiDecor.get(TILE_COUNT_TYPE).setState(INVISIBLE_STATE);
		guiDecor.get(TILE_COUNT_TYPE).setEnabled(false);
		guiDecor.get(TILE_STACK_TYPE).setState(INVISIBLE_STATE);

		// ACTIVATE THE LEVEL SELECT BUTTONS
		// DEACTIVATE THE LEVEL SELECT BUTTONS
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		ArrayList<String> levels = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
		for (String level : levels) {
			guiButtons.get(level).setState(VISIBLE_STATE);
			guiButtons.get(level).setEnabled(true);
		}

		// DEACTIVATE ALL DIALOGS
		guiDialogs.get(WIN_DIALOG_TYPE).setState(INVISIBLE_STATE);
		guiDialogs.get(LOSE_DIALOG_TYPE).setState(INVISIBLE_STATE);
		guiDialogs.get(STATS_DIALOG_TYPE).setState(INVISIBLE_STATE);

		// HIDE THE TILES
		((ZombieCrushSagaDataModel) data).enableTiles(false);

		// MAKE THE CURRENT SCREEN THE SPLASH SCREEN
		currentScreenState = SPLASH_SCREEN_STATE;

		// PLAY THE WELCOME SCREEN SONG
		audio.play(
				ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE.toString(),
				true);
		audio.stop(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE.toString());
	}*/

	/**
	 * This method updates the game grid boundaries, which will depend on the
	 * level loaded.
	 */
	public void updateBoundaries() {
		// NOTE THAT THE ONLY ONES WE CARE ABOUT ARE THE LEFT & TOP BOUNDARIES
		float totalWidth = ((ZombieCrushSagaDataModel) data).getGridColumns()
				* TILE_IMAGE_WIDTH;
		float halfTotalWidth = totalWidth / 2.0f;
		float halfViewportWidth = data.getGameWidth() / 2.0f;
		boundaryLeft = halfViewportWidth - halfTotalWidth;

		// THE LEFT & TOP BOUNDARIES ARE WHERE WE START RENDERING TILES IN THE
		// GRID
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		float topOffset = Integer.parseInt(props
				.getProperty(ZombieCrushSagaPropertyType.GAME_TOP_OFFSET
						.toString()));
		float totalHeight = ((ZombieCrushSagaDataModel) data).getGridRows()
				* TILE_IMAGE_HEIGHT;
		float halfTotalHeight = totalHeight / 2.0f;
		float halfViewportHeight = (data.getGameHeight() - topOffset) / 2.0f;
		boundaryTop = topOffset + halfViewportHeight - halfTotalHeight;
	}

	// METHODS OVERRIDDEN FROM MiniGame
	// - initAudioContent
	// - initData
	// - initGUIControls
	// - initGUIHandlers
	// - reset
	// - updateGUI

	@Override
	/**
	 * Initializes the sound and music to be used by the application.
	 */
	public void initAudioContent() {
		try {
			PropertiesManager props = PropertiesManager.getPropertiesManager();
			String audioPath = props
					.getProperty(ZombieCrushSagaPropertyType.AUDIO_PATH);

			// LOAD ALL THE AUDIO
			loadAudioCue(ZombieCrushSagaPropertyType.SELECT_AUDIO_CUE);
			loadAudioCue(ZombieCrushSagaPropertyType.MATCH_AUDIO_CUE);
			loadAudioCue(ZombieCrushSagaPropertyType.NO_MATCH_AUDIO_CUE);
			loadAudioCue(ZombieCrushSagaPropertyType.BLOCKED_TILE_AUDIO_CUE);
			loadAudioCue(ZombieCrushSagaPropertyType.UNDO_AUDIO_CUE);
			loadAudioCue(ZombieCrushSagaPropertyType.WIN_AUDIO_CUE);
			loadAudioCue(ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE);
			loadAudioCue(ZombieCrushSagaPropertyType.GAMEPLAY_SONG_CUE);
			loadAudioCue(ZombieCrushSagaPropertyType.LOSS_AUDIO_CUE);

			// PLAY THE WELCOME SCREEN SONG
			audio.play(ZombieCrushSagaPropertyType.SPLASH_SCREEN_SONG_CUE
					.toString(), true);
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException | InvalidMidiDataException
				| MidiUnavailableException e) {
			errorHandler
					.processError(ZombieCrushSagaPropertyType.AUDIO_FILE_ERROR);
		}
	}

	/**
	 * This helper method loads the audio file associated with audioCueType,
	 * which should have been specified via an XML properties file.
	 */
	private void loadAudioCue(ZombieCrushSagaPropertyType audioCueType)
			throws UnsupportedAudioFileException, IOException,
			LineUnavailableException, InvalidMidiDataException,
			MidiUnavailableException {
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		String audioPath = props
				.getProperty(ZombieCrushSagaPropertyType.AUDIO_PATH);
		String cue = props.getProperty(audioCueType.toString());
		audio.loadAudio(audioCueType.toString(), audioPath + cue);
	}

	/**
	 * Initializes the game data used by the application. Note that it is this
	 * method's obligation to construct and set this Game's custom GameDataModel
	 * object as well as any other needed game objects.
	 */
	@Override
	public void initData() {
		// INIT OUR ERROR HANDLER
		errorHandler = new ZombieCrushSagaErrorHandler(window);

		// INIT OUR FILE MANAGER
		fileManager = new ZombieCrushSagaFileManager(this);

		// LOAD THE PLAYER'S RECORD FROM A FILE
		record = fileManager.loadRecord();

		// INIT OUR DATA MANAGER
		data = new ZombieCrushSagaDataModel(this);

		// LOAD THE GAME DIMENSIONS
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		int gameWidth = Integer
				.parseInt(props
						.getProperty(ZombieCrushSagaPropertyType.GAME_WIDTH
								.toString()));
		int gameHeight = Integer.parseInt(props
				.getProperty(ZombieCrushSagaPropertyType.GAME_HEIGHT
						.toString()));
		data.setGameDimensions(gameWidth, gameHeight);

		// THIS WILL CHANGE WHEN WE LOAD A LEVEL
		boundaryLeft = Integer.parseInt(props
				.getProperty(ZombieCrushSagaPropertyType.GAME_LEFT_OFFSET
						.toString()));
		boundaryTop = Integer.parseInt(props
				.getProperty(ZombieCrushSagaPropertyType.GAME_TOP_OFFSET
						.toString()));
		boundaryRight = gameWidth - boundaryLeft;
		boundaryBottom = gameHeight;
	}

	/**
	 * Initializes the game controls, like buttons, used by the game
	 * application. Note that this includes the tiles, which serve as buttons of
	 * sorts.
	 */
	@Override
	public void initGUIControls() {
		// WE'LL USE AND REUSE THESE FOR LOADING STUFF
		BufferedImage img;
		float x, y;
		SpriteType sT;
		Sprite s;

		// FIRST PUT THE ICON IN THE WINDOW
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		String imgPath = props
				.getProperty(ZombieCrushSagaPropertyType.IMG_PATH);
		String windowIconFile = props
				.getProperty(ZombieCrushSagaPropertyType.WINDOW_ICON);
		img = loadImage(imgPath + windowIconFile);
		window.setIconImage(img);

		// CONSTRUCT THE PANEL WHERE WE'LL DRAW EVERYTHING
		canvas = new ZombieCrushSagaPanel(this,
				(ZombieCrushSagaDataModel) data);

		// LOAD THE BACKGROUNDS, WHICH ARE GUI DECOR
		currentScreenState = SPLASH_SCREEN_STATE;
		img = loadImage(imgPath
				+ props.getProperty(ZombieCrushSagaPropertyType.SPLASH_SCREEN_IMAGE_NAME));
		sT = new SpriteType(BACKGROUND_TYPE);
		sT.addState(SPLASH_SCREEN_STATE, img);
		img = loadImage(imgPath
				+ props.getProperty(ZombieCrushSagaPropertyType.GAME_BACKGROUND_IMAGE_NAME));
		sT.addState(GAME_SCREEN_STATE, img);
		img = loadImage(imgPath
				+ props.getProperty(ZombieCrushSagaPropertyType.SAGA_SCREEN_IMAGE_NAME));
		sT.addState(SAGA_SCREEN_STATE, img);
		
		img = loadImage(imgPath
				+ props.getProperty(ZombieCrushSagaPropertyType.STATS_SCREEN_IMAGE_NAME));
		sT.addState(STATS_SCREEN_STATE, img);
		
		img = loadImage(imgPath
				+ props.getProperty(ZombieCrushSagaPropertyType.STATS_SCREEN_IMAGE_NAME));
		sT.addState(FINISH_SCREEN_STATE, img);
		
		
		img = loadImage(imgPath
				+ props.getProperty(ZombieCrushSagaPropertyType.FAIL_SCREEN_IMAGE_NAME));
		sT.addState(FAIL_SCREEN_STATE, img);
		
		

		img = loadImage(imgPath
				+ props.getProperty(ZombieCrushSagaPropertyType.YOUWIN_NAME));
		sT.addState(YOUWIN_STATE, img);

		img = loadImage(imgPath
				+ props.getProperty(ZombieCrushSagaPropertyType.YOUFAIL_NAME));
		sT.addState(YOUFAIL_STATE, img);
		s = new Sprite(sT, 0, 0, 0, 0, SPLASH_SCREEN_STATE);
		
		
		
		
		guiDecor.put(BACKGROUND_TYPE, s);
		
/*		
		sT = new SpriteType(BIG_BACKGROUND_TYPE);
		img = loadImage(imgPath
				+ props.getProperty(ZombieCrushSagaPropertyType.SAGA_SCREEN_IMAGE_NAME));
		sT.addState(SAGA_SCREEN_STATE, img);
		s = new Sprite(sT, 0, 0, 0, 0, SAGA_SCREEN_STATE);
		guiDecor.put(BIG_BACKGROUND_TYPE, s);
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		// ADD A BUTTON FOR EACH LEVEL AVAILABLE
		ArrayList<String> levels = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
		ArrayList<String> levelImageNames = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_IMAGE_OPTIONS);
		ArrayList<String> levelMouseOverImageNames = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_MOUSE_OVER_IMAGE_OPTIONS);
		ArrayList<String> noImageNames = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_NO_IMAGE_OPTIONS);
		//float totalWidth = levels.size()
		//		* (LEVEL_BUTTON_WIDTH + LEVEL_BUTTON_MARGIN)
		//		- LEVEL_BUTTON_MARGIN;
		//float gameWidth = Integer.parseInt(props
		//		.getProperty(ZombieCrushSagaPropertyType.GAME_WIDTH));
		//x = (gameWidth - totalWidth) / 2.0f;
		//y = LEVEL_BUTTON_Y;
		//for (int i = 0; i < levels.size(); i++) {
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(0),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(0), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(0),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 65, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[0], s);
			
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(1),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(1), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(1),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 114, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[1], s);
			
			
			
			
			
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(2),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(2), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(2),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 161, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[2], s);
			
			
			
			
			
			
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(3),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(3), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(3),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 210, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[3], s);
			
			
			
			
			
			
			
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(4),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(4), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(4),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 260, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[4], s);
			
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(5),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(5), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(5),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 310, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[5], s);
			
			
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(6),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(6), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(6),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 380, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[6], s);
			
			
			
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(7),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(7), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(7),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 450, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[7], s);
			
			
			
			
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(8),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(8), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(8),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 510, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[8], s);
			
			
			
			sT = new SpriteType(LEVEL_SELECT_BUTTON_TYPE);
			img = loadImageWithColorKey(imgPath + levelImageNames.get(9),
					COLOR_KEY);
			sT.addState(VISIBLE_STATE, img);
			img = loadImageWithColorKey(
					imgPath + levelMouseOverImageNames.get(9), COLOR_KEY);
			sT.addState(MOUSE_OVER_STATE, img);
			img = loadImageWithColorKey(imgPath + noImageNames.get(9),
					COLOR_KEY);
			sT.addState(INCOMPLETE_STATE, img);
			s = new Sprite(sT, 570, 55, 0, 0, INVISIBLE_STATE);
			guiButtons.put(LEVEL_NAME[9], s);
			
			
			
			
			
			
			
			
			//x += LEVEL_BUTTON_WIDTH + LEVEL_BUTTON_MARGIN;
		//}

		// ADD THE CONTROLS ALONG THE NORTH OF THE GAME SCREEN

		// THEN THE NEW BUTTON
		
		String playButton = props
				.getProperty(ZombieCrushSagaPropertyType.PLAY_NAME);
		sT = new SpriteType(PLAY_TYPE);
		img = loadImage(imgPath + playButton);
		sT.addState(VISIBLE_STATE, img);
		String playOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.PLAY_MOUSE_OVER_IMAGE_NAME);
		img = loadImage(imgPath + playOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, PLAY_X, PLAY_Y, 0, 0, VISIBLE_STATE);
		guiButtons.put(PLAY_TYPE, s);
		
		String resetButton = props
				.getProperty(ZombieCrushSagaPropertyType.RESET_NAME);
		sT = new SpriteType(RESET_TYPE);
		img = loadImage(imgPath + resetButton);
		sT.addState(VISIBLE_STATE, img);
		String resetOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.RESET_MOUSE_OVER_IMAGE_NAME);
		img = loadImage(imgPath + resetOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, RESET_X, RESET_Y, 0, 0, VISIBLE_STATE);
		guiButtons.put(RESET_TYPE, s);
		
		String quitButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_NAME);
		sT = new SpriteType(QUIT_TYPE);
		img = loadImage(imgPath + quitButton);
		sT.addState(VISIBLE_STATE, img);
		String quitOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_MOUSE_OVER_IMAGE_NAME);
		img = loadImage(imgPath + quitOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, QUIT_X, QUIT_Y, 0, 0, VISIBLE_STATE);
		guiButtons.put(QUIT_TYPE, s);
		
		
		
		
		
		
		
		
		String newButton = props
				.getProperty(ZombieCrushSagaPropertyType.NEW_BUTTON_IMAGE_NAME);
		sT = new SpriteType(NEW_GAME_BUTTON_TYPE);
		img = loadImage(imgPath + newButton);
		sT.addState(INVISIBLE_STATE, img);
		String newMouseOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.NEW_BUTTON_MOUSE_OVER_IMAGE_NAME);
		img = loadImage(imgPath + newMouseOverButton);
		//sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, NEW_BUTTON_X, NEW_BUTTON_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(NEW_GAME_BUTTON_TYPE, s);

		String backButton = props
				.getProperty(ZombieCrushSagaPropertyType.BACK_BUTTON_IMAGE_NAME);
		sT = new SpriteType(BACK_TYPE);
		img = loadImage(imgPath + backButton);
		sT.addState(INVISIBLE_STATE, img);
		String backMouseOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.BACK_BUTTON_MOUSE_OVER_IMAGE_NAME);
		img = loadImage(imgPath + backMouseOverButton);
		//sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, BACK_BUTTON_X, BACK_BUTTON_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(BACK_TYPE, s);
		
		
		
		
		String aButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_NAME);
		sT = new SpriteType(A_TYPE);
		img = loadImage(imgPath + aButton);
		sT.addState(VISIBLE_STATE, img);
		String aMouseOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_MOUSE_OVER_IMAGE_NAME);
		img = loadImage(imgPath + aMouseOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, 300, 483, 0, 0, INVISIBLE_STATE);
		guiButtons.put(A_TYPE, s);

		String undoButton = props
				.getProperty(ZombieCrushSagaPropertyType.UNDO_BUTTON_IMAGE_NAME);
		sT = new SpriteType(UNDO_TYPE);
		img = loadImage(imgPath + undoButton);
		sT.addState(INVISIBLE_STATE, img);
		String undoMouseOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.UNDO_BUTTON_MOUSE_OVER_IMAGE_NAME);
		img = loadImage(imgPath + undoMouseOverButton);
		//sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, UNDO_BUTTON_X, UNDO_BUTTON_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(UNDO_TYPE, s);

		String tileCountButton = props
				.getProperty(ZombieCrushSagaPropertyType.TILE_COUNT_IMAGE_NAME);
		sT = new SpriteType(TILE_COUNT_TYPE);
		img = loadImage(imgPath + tileCountButton);
		sT.addState(INVISIBLE_STATE, img);
		s = new Sprite(sT, TILE_COUNT_BUTTON_X, TILE_COUNT_BUTTON_Y, 0, 0,
				INVISIBLE_STATE);
		guiDecor.put(TILE_COUNT_TYPE, s);

		// AND THE TIME DISPLAY
		String timeContainer = props
				.getProperty(ZombieCrushSagaPropertyType.TIME_IMAGE_NAME);
		sT = new SpriteType(TIME_TYPE);
		img = loadImage(imgPath + timeContainer);
		sT.addState(INVISIBLE_STATE, img);
		s = new Sprite(sT, TIME_X, TIME_Y, 0, 0, INVISIBLE_STATE);
		guiDecor.put(TIME_TYPE, s);
		
		
		
		String progressBar = props
				.getProperty(ZombieCrushSagaPropertyType.PROGRESS_NAME);
		sT = new SpriteType(PROGRESS_TYPE);
		img = loadImage(imgPath + progressBar);
		sT.addState(VISIBLE_STATE, img);
		s = new Sprite(sT, PROGRESS_X, PROGRESS_Y, 0, 0, INVISIBLE_STATE);
		guiDecor.put(PROGRESS_TYPE, s);

		
		
		
		
		
		
		

		// AND THE STATS BUTTON
		String statsButton = props
				.getProperty(ZombieCrushSagaPropertyType.STATS_BUTTON_IMAGE_NAME);
		sT = new SpriteType(STATS_BUTTON_TYPE);
		img = loadImage(imgPath + statsButton);
		sT.addState(INVISIBLE_STATE, img);
		String statsMouseOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.STATS_BUTTON_MOUSE_OVER_IMAGE_NAME);
		img = loadImage(imgPath + statsMouseOverButton);
	//	sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, STATS_X, STATS_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(STATS_BUTTON_TYPE, s);

		// AND THE TILE STACK
		String tileStack = props
				.getProperty(ZombieCrushSagaPropertyType.TILE_STACK_IMAGE_NAME);
		sT = new SpriteType(TILE_STACK_TYPE);
		img = loadImageWithColorKey(imgPath + tileStack, COLOR_KEY);
		sT.addState(VISIBLE_STATE, img);
		s = new Sprite(sT, TILE_STACK_X, TILE_STACK_Y, 0, 0, INVISIBLE_STATE);
		guiDecor.put(TILE_STACK_TYPE, s);
		
		
		

		String xButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_X_NAME);
		sT = new SpriteType(X_TYPE);
		img = loadImage(imgPath + xButton);
		sT.addState(VISIBLE_STATE, img);
		String xOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_X_MOUSE_OVER_NAME);
		img = loadImage(imgPath + xOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, QUIT_X_X, QUIT_X_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(X_TYPE, s);
		
		
		String failgameButton = props
				.getProperty(ZombieCrushSagaPropertyType.GO_INTO_GAME_NAME);
		sT = new SpriteType(FAIL_TYPE1);
		img = loadImage(imgPath + failgameButton);
		sT.addState(VISIBLE_STATE, img);
		String failgameOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.GO_INTO_GAME_MOUSE_OVER_NAME);
		img = loadImage(imgPath + failgameOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, 160, 560, 0, 0, INVISIBLE_STATE);
		guiButtons.put(FAIL_TYPE1, s);
		
		
		
		String failgamesagaButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_NAME);
		sT = new SpriteType(FAIL_TYPE2);
		img = loadImage(imgPath + failgamesagaButton);
		sT.addState(VISIBLE_STATE, img);
		String failgamesagaOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_MOUSE_OVER_IMAGE_NAME);
		img = loadImage(imgPath + failgamesagaOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, 420, 560, 0, 0, INVISIBLE_STATE);
		guiButtons.put(FAIL_TYPE2, s);
			
		
		
		
		
		
		
		
		
		
		String backToSagaButton = props
				.getProperty(ZombieCrushSagaPropertyType.BACK_TO_SAGA_NAME);
		sT = new SpriteType(BACK_TO_SAGA_TYPE);
		img = loadImage(imgPath + backToSagaButton);
		sT.addState(VISIBLE_STATE, img);
		String backToSagaOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.BACK_TO_SAGA_MOUSE_OVER_NAME);
		img = loadImage(imgPath + backToSagaOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, BACK_TO_SAGA_X, BACK_TO_SAGA_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(BACK_TO_SAGA_TYPE, s);
		
		
		String backToInstructionButton = props
				.getProperty(ZombieCrushSagaPropertyType.BACK_TO_INSTRUCTION_NAME);
		sT = new SpriteType(BACK_TO_INSTRUCTION_TYPE);
		img = loadImage(imgPath + backToInstructionButton);
		sT.addState(VISIBLE_STATE, img);
		String backToInstructionOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.BACK_TO_INSTRUCTION_MOUSE_OVER_NAME);
		img = loadImage(imgPath + backToInstructionOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, BACK_TO_INSTRUCTION_X, BACK_TO_INSTRUCTION_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(BACK_TO_INSTRUCTION_TYPE, s);
		
		
		
		
		String goIntoGameButton = props
				.getProperty(ZombieCrushSagaPropertyType.GO_INTO_GAME_NAME);
		sT = new SpriteType(GO_INTO_GAME_TYPE);
		img = loadImage(imgPath + goIntoGameButton);
		sT.addState(VISIBLE_STATE, img);
		String goIntoGameOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.GO_INTO_GAME_MOUSE_OVER_NAME);
		img = loadImage(imgPath + goIntoGameOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, GO_INTO_GAME_X, GO_INTO_GAME_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(GO_INTO_GAME_TYPE, s);
		
		
		
		
		
		
		
		
		String malletButton = props
				.getProperty(ZombieCrushSagaPropertyType.MALLET_NAME);
		sT = new SpriteType(MALLET_TYPE);
		img = loadImage(imgPath + malletButton);
		sT.addState(VISIBLE_STATE, img);
		String malletOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.MALLETMOUSE_NAME);
		img = loadImage(imgPath + malletOverButton);
		sT.addState(MOUSE_OVER_STATE, img);

		s = new Sprite(sT, 655, 80, 0, 0, INVISIBLE_STATE);
		guiButtons.put(MALLET_TYPE, s);
		
		
		
		
		
		
		
		
		
		String upButton = props
				.getProperty(ZombieCrushSagaPropertyType.UP_NAME);
		sT = new SpriteType(UP_TYPE);
		img = loadImage(imgPath + upButton);
		sT.addState(VISIBLE_STATE, img);
		String upOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.UP_OVER_NAME);
		img = loadImage(imgPath + upOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, UP_TYPE_X, UP_TYPE_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(UP_TYPE, s);
		
		String downButton = props
				.getProperty(ZombieCrushSagaPropertyType.DOWN_NAME);
		sT = new SpriteType(DOWN_TYPE);
		img = loadImage(imgPath + downButton);
		sT.addState(VISIBLE_STATE, img);
		String downOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.DOWN_OVER_NAME);
		img = loadImage(imgPath + downOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, DOWN_TYPE_X, DOWN_TYPE_Y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(DOWN_TYPE, s);

		// NOW ADD THE DIALOGS

		// AND THE STATS DISPLAY
		String statsDialog = props
				.getProperty(ZombieCrushSagaPropertyType.STATS_DIALOG_IMAGE_NAME);
		sT = new SpriteType(STATS_DIALOG_TYPE);
		img = loadImageWithColorKey(imgPath + statsDialog, COLOR_KEY);
		sT.addState(VISIBLE_STATE, img);
		x = (data.getGameWidth() / 2) - (img.getWidth(null) / 2);
		y = (data.getGameHeight() / 2) - (img.getHeight(null) / 2);
		s = new Sprite(sT, x, y, 0, 0, INVISIBLE_STATE);
		guiDialogs.put(STATS_DIALOG_TYPE, s);

		// AND THE WIN CONDITION DISPLAY
		String winDisplay = props
				.getProperty(ZombieCrushSagaPropertyType.WIN_DIALOG_IMAGE_NAME);
		sT = new SpriteType(WIN_DIALOG_TYPE);
		img = loadImageWithColorKey(imgPath + winDisplay, COLOR_KEY);
		sT.addState(VISIBLE_STATE, img);
		x = (data.getGameWidth() / 2) - (img.getWidth(null) / 2);
		y = (data.getGameHeight() / 2) - (img.getHeight(null) / 2);
		s = new Sprite(sT, x, y, 0, 0, INVISIBLE_STATE);
		guiDialogs.put(WIN_DIALOG_TYPE, s);

		String loseDisplay = props
				.getProperty(ZombieCrushSagaPropertyType.LOSE_DIALOG_IMAGE_NAME);
		sT = new SpriteType(LOSE_DIALOG_TYPE);
		img = loadImageWithColorKey(imgPath + loseDisplay, COLOR_KEY);
		sT.addState(VISIBLE_STATE, img);
		x = (data.getGameWidth() / 2) - (img.getWidth(null) / 2);
		y = (data.getGameHeight() / 2) - (img.getHeight(null) / 2);
		s = new Sprite(sT, x, y, 0, 0, INVISIBLE_STATE);
		guiDialogs.put(LOSE_DIALOG_TYPE, s);

		// THEN THE TILES STACKED TO THE TOP LEFT
		((ZombieCrushSagaDataModel) data).initTiles();
	}
	
	
	
	
	
	
	

	
public void resetDownButtons()
	
	
	
	{
		
		BufferedImage img;
		float x, y;
		SpriteType sT;
		Sprite s;
		
		 Rectangle lc=canvas.getBounds();
		 Point lo=lc.getLocation();

		// FIRST PUT THE ICON IN THE WINDOW
		
		guiButtons.remove(UP_TYPE);
		guiButtons.remove(DOWN_TYPE);
		guiButtons.remove(X_TYPE);
		
		
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		
		
		String imgPath = props
				.getProperty(ZombieCrushSagaPropertyType.IMG_PATH);
		
		String upButton = props
				.getProperty(ZombieCrushSagaPropertyType.UP_NAME);
		sT = new SpriteType(UP_TYPE);
		img = loadImage(imgPath + upButton);
		sT.addState(VISIBLE_STATE, img);
		String upOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.UP_OVER_NAME);
		img = loadImage(imgPath + upOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, UP_TYPE_X, UP_TYPE_Y-lo.y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(UP_TYPE, s);
		
		
		
		
		String downButton = props
				.getProperty(ZombieCrushSagaPropertyType.DOWN_NAME);
		sT = new SpriteType(DOWN_TYPE);
		img = loadImage(imgPath + downButton);
		sT.addState(VISIBLE_STATE, img);
		String downOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.DOWN_OVER_NAME);
		img = loadImage(imgPath + downOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, DOWN_TYPE_X, DOWN_TYPE_Y-lo.y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(DOWN_TYPE, s);
		
		
		

		
		
		
		
		
		
		
		
		

		String xButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_X_NAME);
		sT = new SpriteType(X_TYPE);
		img = loadImage(imgPath + xButton);
		sT.addState(VISIBLE_STATE, img);
		String xOverButton = props
				.getProperty(ZombieCrushSagaPropertyType.QUIT_X_MOUSE_OVER_NAME);
		img = loadImage(imgPath + xOverButton);
		sT.addState(MOUSE_OVER_STATE, img);
		s = new Sprite(sT, QUIT_X_X, QUIT_X_Y-lo.y, 0, 0, INVISIBLE_STATE);
		guiButtons.put(X_TYPE, s);
		
		
		
		guiButtons.get(DOWN_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(UP_TYPE).setState(VISIBLE_STATE);
		guiButtons.get(X_TYPE).setState(VISIBLE_STATE);
		
		UpHandler ih=new UpHandler(this);
		guiButtons.get(UP_TYPE).setActionListener(ih);
		
		DownHandler dh=new DownHandler(this);
		guiButtons.get(DOWN_TYPE).setActionListener(dh);
		ClickXGameHandler xx = new ClickXGameHandler(this);
		guiButtons.get(X_TYPE).setActionListener(xx);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Initializes the game event handlers for things like game gui buttons.
	 */
	@Override
	public void initGUIHandlers() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		String dataPath = props
				.getProperty(ZombieCrushSagaPropertyType.DATA_PATH);

		// WE'LL HAVE A CUSTOM RESPONSE FOR WHEN THE USER CLOSES THE WINDOW
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ExitHandler eh = new ExitHandler(this);
		window.addWindowListener(eh);

		// LEVEL BUTTON EVENT HANDLERS
		ArrayList<String> levels = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
		for (String levelFile : LEVEL_NAME) {
			SelectLevelHandler slh = new SelectLevelHandler(this, 
					 levelFile);
			guiButtons.get(levelFile).setActionListener(slh);
		}
		
		/////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		// NEW GAME EVENT HANDLER
		NewGameHandler ngh = new NewGameHandler(this);
		//guiButtons.get(NEW_GAME_BUTTON_TYPE).setActionListener(ngh);

		// KEY LISTENER - LET'S US PROVIDE CUSTOM RESPONSES
		ZombieCrushSagaKeyHandler mkh = new ZombieCrushSagaKeyHandler(this);
		this.setKeyListener(mkh);

		// STATS BUTTON EVENT HANDLER
		StatsHandler sh = new StatsHandler(this);
		//guiButtons.get(STATS_BUTTON_TYPE).setActionListener(sh);

		UndoHandler uh = new UndoHandler(this);
		//guiButtons.get(UNDO_TYPE).setActionListener(uh);

		BackHandler bh = new BackHandler(this);
		guiButtons.get(BACK_TO_INSTRUCTION_TYPE).setActionListener(bh);
		guiButtons.get(BACK_TO_SAGA_TYPE).setActionListener(bh);
		
		guiButtons.get(FAIL_TYPE2).setActionListener(bh);
		
		guiButtons.get(A_TYPE).setActionListener(bh);
		
		PlayHandler ph = new PlayHandler(this);
		guiButtons.get(PLAY_TYPE).setActionListener(ph);
		
		
		UpHandler ih=new UpHandler(this);
		guiButtons.get(UP_TYPE).setActionListener(ih);
		
		DownHandler dh=new DownHandler(this);
		guiButtons.get(DOWN_TYPE).setActionListener(dh);
		
		ClickExitGameHandler e2h = new ClickExitGameHandler(this);
		guiButtons.get(QUIT_TYPE).setActionListener(e2h);
		
		ClickXGameHandler x = new ClickXGameHandler(this);
		guiButtons.get(X_TYPE).setActionListener(x);
		
		GoIntoGameHandler gh =new GoIntoGameHandler(this);
		guiButtons.get(GO_INTO_GAME_TYPE).setActionListener(gh);
		guiButtons.get(FAIL_TYPE1).setActionListener(gh);
		
		ChangeCursorHandler cch =new ChangeCursorHandler(this);
		guiButtons.get(MALLET_TYPE).setActionListener(cch);
		
		
		
		
		
		
		
		
		//guiButtons.get(QUIT_TYPE).setActionListener(e2h);

	}

	/**
	 * Invoked when a new game is started, it resets all relevant game data and
	 * gui control states.
	 */
	@Override
	public void reset() {
		data.reset(this);
	}
	

	/**
	 * Updates the state of all gui controls according to the current game
	 * conditions.
	 */
	@Override
	public void updateGUI() {
		// GO THROUGH THE VISIBLE BUTTONS TO TRIGGER MOUSE OVERS
		Iterator<Sprite> buttonsIt = guiButtons.values().iterator();
		while (buttonsIt.hasNext()) {
			Sprite button = buttonsIt.next();

			// ARE WE ENTERING A BUTTON?
			if (button.getState().equals(VISIBLE_STATE)) {
				if (button.containsPoint(data.getLastMouseX(),
						data.getLastMouseY())) {
					button.setState(MOUSE_OVER_STATE);
				}
			}
			// ARE WE EXITING A BUTTON?
			else if (button.getState().equals(MOUSE_OVER_STATE)) {
				if (!button.containsPoint(data.getLastMouseX(),
						data.getLastMouseY())) {
					button.setState(VISIBLE_STATE);
				}
			}
		}
	}
}

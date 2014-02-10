package zombiecrushsaga;

import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import zombiecrushsaga.ui.ZombieCrushSagaErrorHandler;
import xml_utilities.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;



import zombiecrushsaga.ZombieCrushSaga.ZombieCrushSagaPropertyType;
import zombiecrushsaga.ui.ZombieCrushSagaErrorHandler;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import zombiecrushsaga.ui.*;
import zombiecrushsaga.data.*;
import zombiecrushsaga.file.*;
import xml_utilities.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;


public class ZombieCrushSaga {
	
static ZombieCrushSagaMiniGame miniGame = new ZombieCrushSagaMiniGame();
    
    // WE'LL LOAD ALL THE UI AND ART PROPERTIES FROM FILES,
    // BUT WE'LL NEED THESE VALUES TO START THE PROCESS
    static String PROPERTY_TYPES_LIST = "property_types.txt";
    static String UI_PROPERTIES_FILE_NAME = "properties.xml";
    static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";    
    static String DATA_PATH = "./data/";

    /**
     * This is where the Mahjong Solitaire game application starts execution. We'll
     * load the application properties and then use them to build our
     * user interface and start the window in event handling mode. Once
     * in that mode, all code execution will happen in response to a 
     * user request.
     */
    public static void main(String[] args)
    {
        try
        {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(ZombieCrushSagaPropertyType.UI_PROPERTIES_FILE_NAME, UI_PROPERTIES_FILE_NAME);
            props.addProperty(ZombieCrushSagaPropertyType.PROPERTIES_SCHEMA_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            props.addProperty(ZombieCrushSagaPropertyType.DATA_PATH.toString(), DATA_PATH);
            props.loadProperties(UI_PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            
            // THEN WE'LL LOAD THE MAHJONG FLAVOR AS SPECIFIED BY THE PROPERTIES FILE
            String gameFlavorFile = props.getProperty(ZombieCrushSagaPropertyType.GAME_FLAVOR_FILE_NAME);
            props.loadProperties(gameFlavorFile, PROPERTIES_SCHEMA_FILE_NAME);
            
            String level =props.getProperty(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
          //  props.loadProperties(level, xmlSchemaFile)
            
            
            
                               
            // NOW WE CAN LOAD THE UI, WHICH WILL USE ALL THE FLAVORED CONTENT
            String appTitle = props.getProperty(ZombieCrushSagaPropertyType.GAME_TITLE_TEXT);
            int fps = Integer.parseInt(props.getProperty(ZombieCrushSagaPropertyType.FPS));
            miniGame.initMiniGame(appTitle, fps);
            miniGame.startGame();
        }
        // THERE WAS A PROBLEM LOADING THE PROPERTIES FILE
        catch(InvalidXMLFileFormatException ixmlffe)
        {
            // LET THE ERROR HANDLER PROVIDE THE RESPONSE
            ZombieCrushSagaErrorHandler errorHandler = miniGame.getErrorHandler();
            errorHandler.processError(ZombieCrushSagaPropertyType.INVALID_XML_FILE_ERROR_TEXT);
        }
    }
    
	public enum ZombieCrushSagaPropertyType
    {
        /* SETUP FILE NAMES */
        UI_PROPERTIES_FILE_NAME,
        PROPERTIES_SCHEMA_FILE_NAME,
        GAME_FLAVOR_FILE_NAME,
        RECORD_FILE_NAME,
        BACKGROUND_IMAGE_NAME,
        JELLY_IMAGE_NAME,
        

        /* DIRECTORIES FOR FILE LOADING */
        AUDIO_PATH,
        DATA_PATH,
        IMG_PATH,
        
        /* WINDOW DIMENSIONS & FRAME RATE */
        WINDOW_WIDTH,
        WINDOW_HEIGHT,
        FPS,
        GAME_WIDTH,
        GAME_HEIGHT,
        GAME_LEFT_OFFSET,
        GAME_TOP_OFFSET,
        
        /* GAME TEXT */
        GAME_TITLE_TEXT,
        EXIT_REQUEST_TEXT,
        INVALID_XML_FILE_ERROR_TEXT,
        ERROR_DIALOG_TITLE_TEXT,
        
        /* ERROR TYPES */
        AUDIO_FILE_ERROR,
        LOAD_LEVEL_ERROR,
        RECORD_SAVE_ERROR,

        /* IMAGE FILE NAMES */
        WINDOW_ICON,
        SPLASH_SCREEN_IMAGE_NAME,
        SAGA_SCREEN_IMAGE_NAME,
        STATS_SCREEN_IMAGE_NAME,
        GAME_BACKGROUND_IMAGE_NAME,
        
        FAIL_SCREEN_IMAGE_NAME,
        YOUFAIL_NAME,
        YOUWIN_NAME,
        
        
        
        
        
        
        
        
        BLANK_TILE_IMAGE_NAME,
        BLANK_TILE_SELECTED_IMAGE_NAME,
        INCORRECT_BLANK_TILE_SELECTED_IMAGE_NAME,
        NEW_BUTTON_IMAGE_NAME,
        NEW_BUTTON_MOUSE_OVER_IMAGE_NAME,
        TILE_COUNT_IMAGE_NAME,
        TIME_IMAGE_NAME,
        STATS_BUTTON_IMAGE_NAME,
        STATS_BUTTON_MOUSE_OVER_IMAGE_NAME,
        TILE_STACK_IMAGE_NAME,
        BACK_BUTTON_IMAGE_NAME,
        BACK_BUTTON_MOUSE_OVER_IMAGE_NAME,
        UNDO_BUTTON_IMAGE_NAME,
        UNDO_BUTTON_MOUSE_OVER_IMAGE_NAME,
        
        PLAY_NAME,
        PLAY_MOUSE_OVER_IMAGE_NAME,
        RESET_NAME,
        RESET_MOUSE_OVER_IMAGE_NAME,
        QUIT_NAME,
        QUIT_MOUSE_OVER_IMAGE_NAME,
        
        QUIT_X_NAME,
        QUIT_X_MOUSE_OVER_NAME,
        MALLET_NAME,
        MALLETMOUSE_NAME,

        
        
        DOWN_NAME,
		UP_NAME,   
		DOWN_OVER_NAME,
		UP_OVER_NAME,
        
        
		
		GO_INTO_GAME_NAME,
		GO_INTO_GAME_MOUSE_OVER_NAME,
		BACK_TO_SAGA_NAME,
		BACK_TO_SAGA_MOUSE_OVER_NAME,
		BACK_TO_INSTRUCTION_NAME,
		BACK_TO_INSTRUCTION_MOUSE_OVER_NAME,
		
		PROGRESS_NAME,
		
		
		
		
		//LEVEL_NO_IMAGE_OPTIONS,
		
		
		
        // AND THE DIALOGS
        STATS_DIALOG_IMAGE_NAME,
        WIN_DIALOG_IMAGE_NAME,
        LOSE_DIALOG_IMAGE_NAME,
        
        /* TILE LOADING STUFF */
        LEVEL_OPTIONS,
        LEVEL_IMAGE_OPTIONS,
        LEVEL_MOUSE_OVER_IMAGE_OPTIONS,
        LEVEL_NO_IMAGE_OPTIONS,
        TYPE_A_TILES,
        TYPE_B_TILES,
        TYPE_C_TILES,
        TYPE_D_TILES,
        TYPE_E_TILES,
        TYPE_F_TILES,
        TYPE_S_TILES,
        TYPE_W_TILES,
        TYPE_J_TILES,
        TYPE_COLOR_TILES,
        
        
        
        
        STAR1_SCORE,
        STAR2_SCORE,
        STAR3_SCORE,
        MOVES,
        
        /* AUDIO CUES */
        SELECT_AUDIO_CUE,
        MATCH_AUDIO_CUE,
        NO_MATCH_AUDIO_CUE,
        BLOCKED_TILE_AUDIO_CUE,
        UNDO_AUDIO_CUE,
        WIN_AUDIO_CUE,
        LOSS_AUDIO_CUE,
        SPLASH_SCREEN_SONG_CUE,
        GAMEPLAY_SONG_CUE
    }
	
	

}

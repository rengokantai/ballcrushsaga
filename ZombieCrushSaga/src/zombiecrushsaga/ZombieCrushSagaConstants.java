package zombiecrushsaga;

import java.awt.Color;
import java.awt.Font;

public class ZombieCrushSagaConstants {
	public static final String TILE_A_TYPE = "TILE_A_TYPE";
    public static final String TILE_B_TYPE = "TILE_B_TYPE";
    public static final String TILE_C_TYPE = "TILE_C_TYPE";
    public static final String TILE_D_TYPE = "TILE_D_TYPE";
    public static final String TILE_E_TYPE = "TILE_E_TYPE";
    public static final String TILE_F_TYPE = "TILE_F_TYPE";
    public static final String TILE_S_TYPE = "TILE_S_TYPE";
    public static final String TILE_W_TYPE = "TILE_W_TYPE";
    public static final String TILE_J_TYPE = "TILE_J_TYPE";
    public static final String TILE_COLOR_TYPE = "TILE_COLOR_TYPE";
    public static final String TILE_SPRITE_TYPE_PREFIX = "TILE_";
    
    // EACH SCREEN HAS ITS OWN BACKGROUND TYPE
    public static final String BACKGROUND_TYPE = "BACKGROUND_TYPE";
    public static final String DATA_PREFIX ="./data/";
    
    
    
    
    // SAGA SCREEN IS BIGGER
    public static final String BIG_BACKGROUND_TYPE = "BIG_BACKGROUND_TYPE";
    
    
    
    // THIS REPRESENTS THE BUTTONS ON THE SPLASH SCREEN FOR LEVEL SELECTION
    public static final String LEVEL_SELECT_BUTTON_TYPE = "LEVEL_SELECT_BUTTON_TYPE";

    // IN-GAME UI CONTROL TYPES
    public static final String NEW_GAME_BUTTON_TYPE = "NEW_GAME_BUTTON_TYPE";
    public static final String TIME_TYPE = "TIME_TYPE"; 
    public static final String BACK_TYPE = "BACK_TYPE";
    public static final String UNDO_TYPE = "UNDO_TYPE";
    public static final String TILE_COUNT_TYPE = "TILE_COUNT_TYPE";
    public static final String STATS_BUTTON_TYPE = "STATS_BUTTON_TYPE";
    public static final String TILE_STACK_TYPE = "TILE_STACK_TYPE";
    public static final String DOWN_TYPE = "DOWN_TYPE";
    public static final String UP_TYPE = "UP_TYPE";
    public static final String MALLET_TYPE = "MALLET_TYPE";
    public static final String MALLET1_TYPE = "MALLET1_TYPE";
    
    public static final String PROGRESS_TYPE = "PROGRESS_TYPE";
    
    
    public static final String A_TYPE = "A_TYPE";
    
    public static final String LEVEL1 ="LEVEL_1";
    
    
    
    public static final String PLAY_TYPE = "PLAY_TYPE";
    public static final String RESET_TYPE = "RESET_TYPE";
    public static final String QUIT_TYPE = "QUIT_TYPE";
    public static final String X_TYPE = "X_TYPE";
    
    
    
    
    
    
    public static final String GO_INTO_GAME_TYPE = "GO_INTO_GAME_TYPE";
    public static final String BACK_TO_SAGA_TYPE = "BACK_TO_SAGA_TYPE";
    public static final String BACK_TO_INSTRUCTION_TYPE = "BACK_TO_INSTRUCTION_TYPE";
    public static final int[]TILEN={40,100,100,100,100,100,100,100,100,100};
    public static final String BACK_GROUND_TYPE="BACK_GROUND_TYPE";
    public static final String JELLY_TYPE="JELLY_TYPE";
    //public static int TOTAL_TILE[]={40,73,45,69,44,45,80,73,39,78};
    public static final int NUMBER_OF_MOVES[]={6,15,18,15,20,25,50,20,25,40};
    public static final int FIRST_STAR_SCORE[]={300,1900,4000,4500,5000,9000,60000,20000,22000,40000};
    public static final int SECOND_STAR_SCORE[]={400,2100,6000,6000,8000,11000,75000,30000,44000,70000};    
    public static final int THIRD_STAR_SCORE[]={500,2400,8000,9000,12000,13000,85000,45000,66000,100000};
    //public static final String LEVEL_NAME[]={"Level1","Level2","Level3","Level4","Level5","Level6","Level7","Level8","Level9","Level10"};
   // public static final int pos_x[]={181,230,289,337,388,449,511,586,648,714}; 
    
    
    public static final int GO_INTO_GAME_X =160;
    public static final int GO_INTO_GAME_Y =560;
    public static final int BACK_TO_SAGA_X =420;
    public static final int BACK_TO_SAGA_Y =560;
    public static final int BACK_TO_INSTRUCTION_X =0;
    public static final int BACK_TO_INSTRUCTION_Y =600;

    // DIALOG TYPES
    public static final String STATS_DIALOG_TYPE = "STATS_DIALOG_TYPE";
    public static final String WIN_DIALOG_TYPE = "WIN_DIALOG_TYPE";
    public static final String LOSE_DIALOG_TYPE = "LOSE_DIALOG_TYPE";
    public static final String FAIL_TYPE1 = "FAIL_TYPE1";
    public static final String FAIL_TYPE2 = "FAIL_TYPE2";
   
    
    // WE'LL USE THESE STATES TO CONTROL SWITCHING BETWEEN THE TWO
    public static final String SPLASH_SCREEN_STATE = "SPLASH_SCREEN_STATE";
    public static final String GAME_SCREEN_STATE = "GAME_SCREEN_STATE";    
    public static final String SAGA_SCREEN_STATE = "SAGA_SCREEN_STATE";    
    public static final String STATS_SCREEN_STATE = "STATS_SCREEN_STATE";    
    public static final String FAIL_SCREEN_STATE = "FAIL_SCREEN_STATE"; 
    public static final String YOUWIN_STATE = "YOUWIN_STATE"; 
    public static final String YOUFAIL_STATE = "YOUFAIL_STATE"; 
    
    //FAIL_SCREEN_IMAGE_NAME
    public static final String FINISH_SCREEN_STATE = "FINISH_SCREEN_STATE";    

    // THE TILES MAY HAVE 4 STATES:
        // - INVISIBLE_STATE: USED WHEN ON THE SPLASH SCREEN, MEANS A TILE
            // IS NOT DRAWN AND CANNOT BE CLICKED
        // - VISIBLE_STATE: USED WHEN ON THE GAME SCREEN, MEANS A TILE
            // IS VISIBLE AND CAN BE CLICKED (TO SELECT IT), BUT IS NOT CURRENTLY SELECTED
        // - SELECTED_STATE: USED WHEN ON THE GAME SCREEN, MEANS A TILE
            // IS VISIBLE AND CAN BE CLICKED (TO UNSELECT IT), AND IS CURRENTLY SELECTED     
        // - NOT_AVAILABLE_STATE: USED FOR A TILE THE USER HAS CLICKED ON THAT
            // IS NOT FREE. THIS LET'S US GIVE THE USER SOME FEEDBACK
    public static final String INVISIBLE_STATE = "INVISIBLE_STATE";
    public static final String VISIBLE_STATE = "VISIBLE_STATE";
    public static final String INCOMPLETE_STATE = "INCOMPLETE_STATE";
    public static final String SELECTED_STATE = "SELECTED_STATE";
    public static final String INCORRECTLY_SELECTED_STATE = "NOT_AVAILABLE_STATE";
    public static final String MOUSE_OVER_STATE = "MOUSE_OVER_STATE";

    // THE BUTTONS MAY HAVE 2 STATES:
        // - INVISIBLE_STATE: MEANS A BUTTON IS NOT DRAWN AND CAN'T BE CLICKED
        // - VISIBLE_STATE: MEANS A BUTTON IS DRAWN AND CAN BE CLICKED
        // - MOUSE_OVER_STATE: MEANS A BUTTON IS DRAWN WITH SOME HIGHLIGHTING
            // BECAUSE THE MOUSE IS HOVERING OVER THE BUTTON

    // UI CONTROL SIZE AND POSITION SETTINGS
    
    // OR POSITIONING THE LEVEL SELECT BUTTONS
    //public static final int LEVEL_BUTTON_WIDTH = 22;
   // public static final int LEVEL_BUTTON_MARGIN = 5;
    public static final int LEVEL_BUTTON_Y = 570;

    // FOR STACKING TILES ON THE GRID
    //public static final int NUM_TILES = 144;
    public static final int TILE_IMAGE_OFFSET = 1;
    public static final int TILE_IMAGE_WIDTH = 55;
    public static final int TILE_IMAGE_HEIGHT = 55;
    public static final int Z_TILE_OFFSET = 5;

    // FOR MOVING TILES AROUND
    public static final int MAX_TILE_VELOCITY = 70;
    
    // UI CONTROLS POSITIONS IN THE GAME SCREEN
    public static final int CONTROLS_MARGIN = 0;
    public static final int NEW_BUTTON_X = 0;
    public static final int NEW_BUTTON_Y = 0;
    public static final int TILE_TEXT_OFFSET = 60;
    public static final int BACK_BUTTON_X = 130;
    public static final int BACK_BUTTON_Y = 0;
    //public static final int TIME_X = TILE_COUNT_BUTTON_X + 260 + CONTROLS_MARGIN;
    //public static final int TIME_Y = 0;
    public static final int TIME_OFFSET = 130;
    public static final int TIME_TEXT_OFFSET = 55;

    public static final int TILE_COUNT_BUTTON_X = BACK_BUTTON_X + 130 + CONTROLS_MARGIN;
    public static final int TILE_COUNT_BUTTON_Y = 0;
    
    public static final int TIME_X = TILE_COUNT_BUTTON_X + 230 + CONTROLS_MARGIN;
    public static final int TIME_Y = 0;
    public static final int STATS_X = TIME_X + 310 + CONTROLS_MARGIN;
    public static final int STATS_Y = 0;
    public static final int UNDO_BUTTON_X = STATS_X + 160 + CONTROLS_MARGIN;
    public static final int UNDO_BUTTON_Y = 0;
    public static final int TILE_STACK_X = STATS_X + 290 + CONTROLS_MARGIN;
    public static final int TILE_STACK_Y = 0;
    public static final int TILE_STACK_OFFSET_X = 30;
    public static final int TILE_STACK_OFFSET_Y = 12;
    public static final int TILE_STACK_2_OFFSET_X = 105;
    public static final int FULLSTAT_X = 284;
    public static final int FULLSTAT_Y =200;
    public static final int TARGET_X = 274;
    public static final int TARGET_Y =250;
    public static final int PROGRESS_X =550;
    public static final int PROGRESS_Y =40;
    
    
    
    
    
    
    
    
    
    
    
    public static final int PLAY_X = 290;
    public static final int PLAY_Y = 340;
    public static final int RESET_X = 290;
    public static final int RESET_Y = 410;
    public static final int QUIT_X = 290;
    public static final int QUIT_Y = 480;
    
    public static final int DOWN_TYPE_X =700;
    public static final int DOWN_TYPE_Y =450;
    public static final int UP_TYPE_X =700;
    public static final int UP_TYPE_Y =350;
    public static final int QUIT_X_X=700;
    public static final int QUIT_X_Y=400;
    
    
    
       
    // THESE ARE USED FOR FORMATTING THE TIME OF GAME
    public static final long MILLIS_IN_A_SECOND = 1000;
    public static final long MILLIS_IN_A_MINUTE = 1000 * 60;
    public static final long MILLIS_IN_AN_HOUR  = 1000 * 60 * 60;

    // USED FOR DOING OUR VICTORY ANIMATION
    public static final int WIN_PATH_NODES = 180;
    public static final int WIN_PATH_TOLERANCE = 100;
    public static final int WIN_PATH_COORD = 100;
    
    
    public static boolean LEVEL_LOCK[]=new boolean[10];
    public static final String LEVEL_NAME[]={"Level1","Level2","Level3","Level4","Level5","Level6","Level7","Level8","Level9","Level10"};

    // COLORS USED FOR RENDERING VARIOUS THINGS, INCLUDING THE
    // COLOR KEY, WHICH REFERS TO THE COLOR TO IGNORE WHEN
    // LOADING ART.
    public static final Color COLOR_KEY = new Color(255, 174, 201);
    public static final Color DEBUG_TEXT_COLOR = Color.BLACK;
    public static final Color TEXT_DISPLAY_COLOR = new Color (10, 160, 10);
    public static final Color SELECTED_TILE_COLOR = new Color(255,255,0,100);
    public static final Color INCORRECTLY_SELECTED_TILE_COLOR = new Color(255, 50, 50, 100);
    public static final Color STATS_COLOR = new Color(123, 60, 0);
    public static final Color CHESS_COLOR_KEY = new Color(255, 255, 255);

    // FONTS USED DURING FOR TEXTUAL GAME DISPLAYS
    public static final Font TEXT_DISPLAY_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 48);
    public static final Font DEBUG_TEXT_FONT = new Font(Font.MONOSPACED, Font.BOLD, 14);
    public static final Font STATS_FONT = new Font(Font.SERIF, Font.BOLD, 36);
    
    // AND AUDIO STUFF
    public static final String SUCCESS_AUDIO_TYPE = "SUCCESS_AUDIO_TYPE";
    public static final String FAILURE_AUDIO_TYPE = "FAILURE_AUDIO_TYPE";
    public static final String THEME_SONG_TYPE = "THEME_SONG_TYPE";
}

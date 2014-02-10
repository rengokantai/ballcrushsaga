package zombiecrushsaga.ui;

import static zombiecrushsaga.ZombieCrushSagaConstants.*;
import static zombiecrushsaga.ZombieCrushSagaConstants.DEBUG_TEXT_COLOR;
import static zombiecrushsaga.ZombieCrushSagaConstants.DEBUG_TEXT_FONT;
import static zombiecrushsaga.ZombieCrushSagaConstants.FULLSTAT_X;
import static zombiecrushsaga.ZombieCrushSagaConstants.FULLSTAT_Y;
import static zombiecrushsaga.ZombieCrushSagaConstants.GAME_SCREEN_STATE;
import static zombiecrushsaga.ZombieCrushSagaConstants.INCORRECTLY_SELECTED_STATE;
import static zombiecrushsaga.ZombieCrushSagaConstants.INCORRECTLY_SELECTED_TILE_COLOR;
import static zombiecrushsaga.ZombieCrushSagaConstants.INVISIBLE_STATE;
//import static zombiecrushsaga.ZombieCrushSagaConstants.NUM_TILES;
import static zombiecrushsaga.ZombieCrushSagaConstants.SELECTED_STATE;
import static zombiecrushsaga.ZombieCrushSagaConstants.SELECTED_TILE_COLOR;
import static zombiecrushsaga.ZombieCrushSagaConstants.STATS_DIALOG_TYPE;
import static zombiecrushsaga.ZombieCrushSagaConstants.STATS_FONT;
import static zombiecrushsaga.ZombieCrushSagaConstants.TEXT_DISPLAY_FONT;
import static zombiecrushsaga.ZombieCrushSagaConstants.TILE_COUNT_BUTTON_X;
import static zombiecrushsaga.ZombieCrushSagaConstants.TILE_COUNT_BUTTON_Y;
import static zombiecrushsaga.ZombieCrushSagaConstants.TILE_IMAGE_HEIGHT;
import static zombiecrushsaga.ZombieCrushSagaConstants.TILE_IMAGE_OFFSET;
import static zombiecrushsaga.ZombieCrushSagaConstants.TILE_IMAGE_WIDTH;
import static zombiecrushsaga.ZombieCrushSagaConstants.TIME_OFFSET;
import static zombiecrushsaga.ZombieCrushSagaConstants.TIME_TEXT_OFFSET;
import static zombiecrushsaga.ZombieCrushSagaConstants.TIME_X;
import static zombiecrushsaga.ZombieCrushSagaConstants.TIME_Y;
import static zombiecrushsaga.ZombieCrushSagaConstants.VISIBLE_STATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import properties_manager.PropertiesManager;

import zombiecrushsaga.ZombieCrushSaga;
import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import zombiecrushsaga.data.ZombieCrushSagaRecord;

import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import zombiecrushsaga.ui.ZombieCrushSagaTile;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;

public class ZombieCrushSagaPanel extends JPanel {
	private MiniGame game;
   // private int i=0;
	// AND HERE IS ALL THE GAME DATA THAT WE NEED TO RENDER
	private ZombieCrushSagaDataModel data;

	// WE'LL USE THIS TO FORMAT SOME TEXT FOR DISPLAY PURPOSES
	private NumberFormat numberFormatter;

	// WE'LL USE THIS AS THE BASE IMAGE FOR RENDERING UNSELECTED TILES
	private BufferedImage blankTileImage;
	
	private JScrollPane jp;

	// WE'LL USE THIS AS THE BASE IMAGE FOR RENDERING SELECTED TILES
	private BufferedImage blankTileSelectedImage;
	
	// WE'LL USE THIS AS THE BASE IMAGE FOR RENDERING BLOCKED TILES
		private BufferedImage incorrectTileSelectedImage;
		
		private boolean fill;
		
		
		
		 private boolean needFill;
		
		//private BufferedImage 

	private ZombieCrushSagaRecord record;

	/**
	 * This constructor stores the game and data references, which we'll need
	 * for rendering.
	 * 
	 * @param initGame
	 *            the Mahjong Solitaire game that is using this panel for
	 *            rendering.
	 * 
	 * @param initData
	 *            the Mahjong Solitaire game data.
	 */
	public ZombieCrushSagaPanel(MiniGame initGame,
			ZombieCrushSagaDataModel initData) {
		game = initGame;
		data = initData;
		numberFormatter = NumberFormat.getNumberInstance();
		numberFormatter.setMinimumFractionDigits(3);
		numberFormatter.setMaximumFractionDigits(3);
	}

	// MUTATOR METHODS
	// -setBlankTileImage
	// -setBlankTileSelectedImage

	/**
	 * This mutator method sets the base image to use for rendering tiles.
	 * 
	 * @param initBlankTileImage
	 *            The image to use as the base for rendering tiles.
	 */
	public void setBlankTileImage(BufferedImage initBlankTileImage) {
		blankTileImage = initBlankTileImage;
	}

	
	
	/**
	 * This mutator method sets the base image to use for rendering selected
	 * tiles.
	 * 
	 * @param initBlankTileSelectedImage
	 *            The image to use as the base for rendering selected tiles.
	 */
	public void setBlankTileSelectedImage(
			BufferedImage initBlankTileSelectedImage) {
		blankTileSelectedImage = initBlankTileSelectedImage;
	}
	


	/**
	 * This is where rendering starts. This method is called each frame, and the
	 * entire game application is rendered here with the help of a number of
	 * helper methods.
	 * 
	 * @param g
	 *            The Graphics context for this panel.
	 */
	@Override
	public void paintComponent(Graphics g) {
		try {
			// MAKE SURE WE HAVE EXCLUSIVE ACCESS TO THE GAME DATA
			
			game.beginUsingData();
			renderClearScore(g);
			// CLEAR THE PANEL
			super.paintComponent(g);

			// RENDER THE BACKGROUND, WHICHEVER SCREEN WE'RE ON
			renderBackground(g);
	         renderFillEmpty(g);
	            renderCheckToMoves(g);
	            
			// AND THE BUTTONS AND DECOR
			renderGUIControls(g);           //E
			renderBag(g);
			// AND THE TILES
			renderTiles(g);           //E
			renderActualStar(g);
			renderActualStar2(g);
			
	           renderScoreBar(g);
	           renderMoves(g);
	            renderNumLevel(g);
	       //     renderHighestScore(g);
	            
	            renderSwap(g);
	            renderClear(g);
	  
	            rendersmall(g);
	            
	            
	            
	            renderDistinguish(g);
	            

			// AND THE DIALOGS, IF THERE ARE ANY
			renderDialogs(g);

			// AND THE TIME AND TILES STATS
			renderStats(g);
			//renderTileNumbers(g);
			renderFullStats(g);
			renderCurrentScore(g);
			renderCurrentScore2(g);
			// RENDERING THE GRID WHERE ALL THE TILES GO CAN BE HELPFUL
			// DURING DEBUGGIN TO BETTER UNDERSTAND HOW THEY RE LAID OUT
			//renderGrid(g);

			// AND FINALLY, TEXT FOR DEBUGGING
			renderDebuggingText(g);
		} finally {
			// RELEASE THE LOCK
			game.endUsingData();
            if (((ZombieCrushSagaMiniGame)game).isCurrentScreenState(GAME_SCREEN_STATE)){
                ZombieCrushSagaDataModel dm=(ZombieCrushSagaDataModel)game.getDataModel();}
		}
	}

	// RENDERING HELPER METHODS
	// - renderBackground
	// - renderGUIControls
	// - renderTiles
	// - renderDialogs
	// - renderGrid
	// - renderDebuggingText
	
	
	
	 public void renderWin(Graphics g){
	       ZombieCrushSagaDataModel dm=(ZombieCrushSagaDataModel)game.getDataModel();
	        if(dm.beginToUpdateBoard&&!dm.isProcessMoving()&&dm.finishMoves()&&dm.inProgress()){
	            if(dm.FinishAllMove())
	               dm.processWin();
	        }
	    }
	
    public void renderSwap(Graphics g){
        if (((ZombieCrushSagaMiniGame)game).isCurrentScreenState(GAME_SCREEN_STATE)){
             ZombieCrushSagaDataModel dm=(ZombieCrushSagaDataModel)game.getDataModel();
             if(dm.beginToUpdateBoard&&dm.finishExchange){

               dm.setSpeed(3);
                 dm.swap(dm.selectTile, dm.selectedTile);
                 dm.updateMoving();
                 dm.beginToUpdateBoard=false;
                 dm.finishExchange=false;
                 
             }
        }
    
    }
	
	
	
	
	
	
    public void renderClear(Graphics g){
        if (((ZombieCrushSagaMiniGame)game).isCurrentScreenState(GAME_SCREEN_STATE)){
                ZombieCrushSagaDataModel dm=(ZombieCrushSagaDataModel)game.getDataModel();           
                if(dm.beginToUpdateBoard&&!dm.isProcessMoving()&&dm.startClear){
                    dm.finishExchange=false;   
                    dm.clear();

                    dm.startClear=false;
                    dm.startFillEmptyCol=true;
                    dm.selectTile=null;
                    dm.selectedTile=null;

                }
        }
       }
	
    
    public void renderBag(Graphics g){
        if (((ZombieCrushSagaMiniGame)game).isCurrentScreenState(GAME_SCREEN_STATE)){
            ZombieCrushSagaDataModel dm=(ZombieCrushSagaDataModel)game.getDataModel();
            if(dm.beginToUpdateBoard&&!dm.isProcessMoving()&&dm.processBag){
                dm.sleep(100);
                dm.setSpeed(2);
                dm.addScoreTimes();
                dm.clearThreethree();
                dm.processBag=false;
            }
        }
   }

    
    
    public void renderFillEmpty(Graphics g){
        if (((ZombieCrushSagaMiniGame)game).isCurrentScreenState(GAME_SCREEN_STATE)){
            ZombieCrushSagaDataModel dm=(ZombieCrushSagaDataModel)game.getDataModel();
            if(dm.beginToUpdateBoard&&dm.startFillEmptyCol&&!dm.isProcessMoving()){
                for (int i = 0; i <dm.getTileGrid().length; i++) {
                       if(dm.filetycln(i))                   
                       dm.getEC().add(i);
                   }
                dm.startFillEmptyCol=false;
                
            }
       }
       
   
    }
	
	
	
	public void renderNumLevel(Graphics g) {
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(GAME_SCREEN_STATE)){
	
	ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
			.getDataModel();
	ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
	ZombieCrushSagaRecord record = msmg.getPlayerRecord();
	String levelPath = data.getCurrentLevel();
	

	
	g.setFont(STATS_FONT);
	g.setColor(Color.BLACK);
	g.drawString(levelPath, 100, 40);
	
	
		}}
	
	public void renderScoreBar(Graphics g) {
		if (((ZombieCrushSagaMiniGame)game).isCurrentScreenState(GAME_SCREEN_STATE)){
			ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
					.getDataModel();
			ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
			String[] levelPath = data.getCurrentLevel().split("/");
			
			String levelPlayed = levelPath[levelPath.length - 1].substring(
					0, levelPath[levelPath.length - 1].length() - 4);
			PropertiesManager props = PropertiesManager.getPropertiesManager();
			int SCORE_BAR_LENGTH=455;
            int leftMost= Integer.parseInt(props.getProperty(ZombieCrushSaga.ZombieCrushSagaPropertyType.GAME_WIDTH))-SCORE_BAR_LENGTH+46;
            int length=143;
            
            ZombieCrushSagaRecord record = msmg.getPlayerRecord();
            int level=Integer.parseInt(data.getCurrentLevel().substring(5));
            int ThirdStar=THIRD_STAR_SCORE[level-1];  
			
            int scorebar=0;
            if(data.Score>=ThirdStar)
                scorebar=length;
            else
                scorebar=(int) (data.Score*length/ ThirdStar);
            //if(scorebar>0){
           // g.setColor(Color.GREEN);
           // g.drawRect(622,52,scorebar ,9);
            g.setColor(Color.GREEN);
            g.fillRect(557,47,scorebar,8);
           // }
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void renderMoves(Graphics g) {
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(GAME_SCREEN_STATE)){
	
	ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
			.getDataModel();
	ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
	ZombieCrushSagaRecord record = msmg.getPlayerRecord();

	int level=Integer.parseInt(data.getCurrentLevel().substring(5));

	String movesTime = "Moves: "
			+ (NUMBER_OF_MOVES[level-1]-data.getCurrentMoves())+"";
	
	g.setFont(STATS_FONT);
	g.setColor(Color.BLACK);
	g.drawString(movesTime, 340, 40);
	
	
		}}
	
	
	public void renderCurrentScore(Graphics g) {
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(GAME_SCREEN_STATE)){
	
	ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
			.getDataModel();
	ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
	ZombieCrushSagaRecord record = msmg.getPlayerRecord();

	String movesTime = "Score: "
			+ data.Score;
	
	g.setFont(STATS_FONT);
	g.setColor(Color.BLACK);
	g.drawString(movesTime, 100, 70);
	
	
		}}
	
	
	public void renderActualStar(Graphics g) {
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(GAME_SCREEN_STATE)){
	
	ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
			.getDataModel();
	ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
	ZombieCrushSagaRecord record = msmg.getPlayerRecord();

	
    int level=Integer.parseInt(data.getCurrentLevel().substring(5));
    int OneStar=FIRST_STAR_SCORE[level-1];  
    int SecondStar=SECOND_STAR_SCORE[level-1];  
    int ThirdStar=THIRD_STAR_SCORE[level-1];  
	long a=data.Score;
	int star=0;
	
	if (a<OneStar)
		star=0;
	if (a<SecondStar&&a>=OneStar)
		star=1;
	if (a<ThirdStar&&a>=SecondStar)
		star=2;
	if (a>=ThirdStar)
		star=3;
	String movesTime="Stars: "+star;
	g.setFont(STATS_FONT);
	g.setColor(Color.BLACK);
	g.drawString(movesTime, 340, 70);
	
	
		}}
	
	
	public void renderActualScore(Graphics g) {
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(FINISH_SCREEN_STATE)){
	
	ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
			.getDataModel();
	ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
	ZombieCrushSagaRecord record = msmg.getPlayerRecord();

	String movesTime = "Score :   "
			+ data.getScore();
	
	g.setFont(STATS_FONT);
	g.setColor(Color.BLACK);
	g.drawString(movesTime, 260, 80);
	
	
		}}
	
	
	
	
	
	
	
	
	public void renderCurrentScore2(Graphics g) {
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(STATS_SCREEN_STATE)){
	
	ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
			.getDataModel();
	ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
	ZombieCrushSagaRecord record = msmg.getPlayerRecord();

	String movesTime = "Score :   "
			+ data.Score;
	
	g.setFont(STATS_FONT);
	g.setColor(Color.BLACK);
	g.drawString(movesTime, 150, 150);
	
	
		}}
	
	
	
	
	public void renderActualStar2(Graphics g) {
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(STATS_SCREEN_STATE)){
	
	ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
			.getDataModel();
	ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
	ZombieCrushSagaRecord record = msmg.getPlayerRecord();

	long a=data.Score;
	int star=0;
    int level=Integer.parseInt(data.getCurrentLevel().substring(5));
    int OneStar=FIRST_STAR_SCORE[level-1];  
    int SecondStar=SECOND_STAR_SCORE[level-1];  
    int ThirdStar=THIRD_STAR_SCORE[level-1]; 
	if (a<OneStar)
		star=0;
	if (a<SecondStar&&a>=OneStar)
		star=1;
	if (a<ThirdStar&&a>=SecondStar)
		star=2;
	if (a>=ThirdStar)
		star=3;
	String movesTime="Actual Stars "+star;
	g.setFont(STATS_FONT);
	g.setColor(Color.BLACK);
	g.drawString(movesTime, 370, 150);
	
	
		}}
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Renders the background image, which is different depending on the screen.
	 * 
	 * @param g
	 *            the Graphics context of this panel.
	 */
	public void renderBackground(Graphics g) {
		// THERE IS ONLY ONE CURRENTLY SET
		Sprite bg = game.getGUIDecor().get(BACKGROUND_TYPE);
		renderSprite(g, bg);
	}

	/**
	 * Renders all the GUI decor and buttons.
	 * 
	 * @param g
	 *            this panel's rendering context.
	 */
	public void renderGUIControls(Graphics g) {
		// GET EACH DECOR IMAGE ONE AT A TIME
		Collection<Sprite> decorSprites = game.getGUIDecor().values();
		for (Sprite s : decorSprites) {
			renderSprite(g, s);
		}

		// AND NOW RENDER THE BUTTONS
		Collection<Sprite> buttonSprites = game.getGUIButtons().values();
		for (Sprite s : buttonSprites) {
			renderSprite(g, s);
		}
	}
	
	
	
	
	
	
	
	
	
	public void rendersmall(Graphics g){
		
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(YOUWIN_STATE)){
			
			ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
			.getDataModel();
	ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
	ZombieCrushSagaRecord record = msmg.getPlayerRecord();



	
	
    int level=Integer.parseInt(data.getCurrentLevel().substring(5));
    int OneStar=FIRST_STAR_SCORE[level-1];  
    int SecondStar=SECOND_STAR_SCORE[level-1];  
    int ThirdStar=THIRD_STAR_SCORE[level-1];  
	long a=data.Score;
	int star=0;
	
	if (a<OneStar)
		star=0;
	if (a<SecondStar&&a>=OneStar)
		star=1;
	if (a<ThirdStar&&a>=SecondStar)
		star=2;
	if (a>=ThirdStar)
		star=3;
	String movesTime="Stars: "+star;
	g.setFont(STATS_FONT);
	g.setColor(Color.BLACK);
	g.drawString(movesTime, 300, 420);
	
	
	
	
	String move1sTime = "Score :   "
			+ data.Score;
	
	g.setFont(STATS_FONT);
	g.setColor(Color.BLACK);
	g.drawString(move1sTime, 300, 350);
	
	
	
	
	
	
	
	
	
		}
		
		
		
	}

	public void renderFullStats(Graphics g) {
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(STATS_SCREEN_STATE)
				//&& data.inProgress()
				//|| data.isPaused()||data.lost()||data.won()
				) 
		{
			//if (game.getGUIDialogs().get(STATS_DIALOG_TYPE).getState() == INVISIBLE_STATE) 
			
			
			
			{
				ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel) game
						.getDataModel();
				ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) game;
				ZombieCrushSagaRecord record = msmg.getPlayerRecord();
			

				int xLevel = FULLSTAT_X;
				int yLevel = FULLSTAT_Y;
				g.setFont(TEXT_DISPLAY_FONT);
				g.setColor(Color.PINK);
				g.drawString(data.getCurrentLevel(), xLevel, yLevel);
				int level=Integer.parseInt(data.getCurrentLevel().substring(5));
	            int OneStar=FIRST_STAR_SCORE[level-1];  
	            int SecondStar=SECOND_STAR_SCORE[level-1];  
				int ThirdStar = THIRD_STAR_SCORE[level-1];  
				int movetime =NUMBER_OF_MOVES[level-1];
				
				
				int xTarget = TARGET_X;
				int yTarget = TARGET_Y;
				
				
				g.setFont(STATS_FONT);
				g.setColor(Color.BLACK);
				g.drawString("Star Target:", xTarget, yTarget);
				
				
				
				
				String games = "1Star  :   "
						+ OneStar;
				int xGame = FULLSTAT_X-10;
				int yGame = FULLSTAT_Y + 90;
				g.setFont(STATS_FONT);
				g.setColor(Color.BLACK);
				g.drawString(games, xGame, yGame);
				String wins = "2Stars :   "
						+ SecondStar;
				int xWins = FULLSTAT_X-10;
				int yWins = FULLSTAT_Y + 130;
				g.setFont(STATS_FONT);
				g.setColor(Color.BLACK);
				g.drawString(wins, xWins, yWins);

				String losses = "3Stars :   "
						+ ThirdStar;
				int xLosses = FULLSTAT_X-10;
				int yLosses = FULLSTAT_Y + 170;
				g.setFont(STATS_FONT);
				g.setColor(Color.BLACK);
				g.drawString(losses, xLosses, yLosses);
				String movesTime = "Moves :   "
						+ movetime;
				int xMoves = FULLSTAT_X-10;
				int yMoves = FULLSTAT_Y + 210;
				g.setFont(STATS_FONT);
				g.setColor(Color.BLACK);
				g.drawString(movesTime, xMoves, yMoves);

				String winP = "High Score :   "
						+ (int)((ZombieCrushSagaMiniGame)game).getPlayerRecord().getHighestScore(data.getCurrentLevel()); 
				/*if (winP.length() > 13)
					winP = winP.substring(0, 14);*/
				int xWP = FULLSTAT_X-20;
				int yWP = FULLSTAT_Y + 250;
				g.setFont(STATS_FONT);
				g.setColor(Color.BLACK);
				g.drawString(winP, xWP, yWP);

				String fastestWin = "High Star: "  +((ZombieCrushSagaMiniGame)game).getPlayerRecord().getStarEarned(data.getCurrentLevel()) ;
				//if (record.getWins(data.getCurrentLevel()) != 0)
				//	fastestWin += data.timeToText(record.getFastestTime(data
				//			.getCurrentLevel()));
				//int xFW = FULLSTAT_X;
				//int yFW = FULLSTAT_Y + 170;
				//if (record.getWins(data.getCurrentLevel()) != 0)
				//	fastestWin += data.timeToText(record.getFastestTime(data
				//			.getCurrentLevel()));
				//int xFW = FULLSTAT_X;
				//int yFW = FULLSTAT_Y + 170;
				g.setFont(STATS_FONT);
				g.setColor(Color.BLACK);
				g.drawString(fastestWin, xWP, yWP+40);
			}
		}
	}
	  public void renderClearScore(Graphics g){
	        if (((ZombieCrushSagaMiniGame)game).isCurrentScreenState(GAME_SCREEN_STATE)){
	            ZombieCrushSagaDataModel dm=(ZombieCrushSagaDataModel)game.getDataModel();
	            if(!dm.isProcessMoving()){
	                dm.clearScore();
	            }
	        }
	   }
	    public void renderDistinguish(Graphics g){
	        if (((ZombieCrushSagaMiniGame)game).isCurrentScreenState(GAME_SCREEN_STATE)){
	            ZombieCrushSagaDataModel dm=(ZombieCrushSagaDataModel)game.getDataModel();
	             if(dm.beginToUpdateBoard&&fill){
	                 
	                  dm.sleep(100);
	                 for (int k = 0; k <  dm.getTileGrid().length; k++) 
	                                   if (dm.filetycln(k)) 
	                                       dm.getEC().add(k);
	                                   
	                        fill=false;     
	                    }
	             
	        }
	   }

	/**
	 * This method renders the on-screen stats that change as the game
	 * progresses. This means things like the game time and the number of tiles
	 * remaining.
	 * 
	 * @param g
	 *            the Graphics context for this panel
	 */
	public void renderStats(Graphics g) {
		// RENDER THE GAME TIME
		if (((ZombieCrushSagaMiniGame) game)
				.isCurrentScreenState(GAME_SCREEN_STATE)
				&& data.inProgress()
				|| data.isPaused()) {
			// RENDER THE TIME
			//String time = data.gameTimeToText();
			int x = TIME_X + TIME_OFFSET;
			int y = TIME_Y + TIME_TEXT_OFFSET;
			g.setFont(TEXT_DISPLAY_FONT);
			//g.drawString(time, x, y);
		}
	}

	/**
	 * Renders all the game tiles, doing so carefully such that they are
	 * rendered in the proper order.
	 * 
	 * @param g
	 *            the Graphics context of this panel.
	 */


	/**
	 * Renders all the game tiles, doing so carefully such that they are
	 * rendered in the proper order.
	 * 
	 * @param g
	 *            the Graphics context of this panel.
	 */
	public void renderTiles(Graphics g)
    {
        // DRAW THE TOP TILES ON THE STACK
       if (!data.won())
        {
            // WE DRAW ONLY THE TOP 4 (OR 2 IF THERE ARE ONLY 2). THE REASON
            // WE DRAW 4 IS THAT WHILE WE MOVE MATCHES TO THE STACK WE WANT
            // TO SEE THE STACK
            ArrayList<ZombieCrushSagaTile> stackTiles = data.getStackTiles();
            if (stackTiles.size() > 3)
            {
                renderTile(g, stackTiles.get(stackTiles.size()-3));
                renderTile(g, stackTiles.get(stackTiles.size()-4));
            }
            if (stackTiles.size() > 1)
            {
                renderTile(g, stackTiles.get(stackTiles.size()-1));
                renderTile(g, stackTiles.get(stackTiles.size()-2));
            }
        }
        
        // THEN DRAW THE GRID TILES BOTTOM TO TOP USING
        // THE TILE'S Z TO STAGGER THEM AND GIVE THE ILLUSION
        // OF DEPTH
        ArrayList<ZombieCrushSagaTile>[][] tileGrid = data.getTileGrid();
        boolean noneOnLevel = false;
        int zIndex = 0;
        while (!noneOnLevel)
        {
            int levelCounter = 0;
            for (int i = 0; i < data.getGridColumns(); i++)
            {
                for (int j = 0; j < data.getGridRows(); j++)
                {
                    if (tileGrid[i][j].size() > zIndex)
                    {
                        ZombieCrushSagaTile tile = tileGrid[i][j].get(zIndex);
                        renderTile(g, tile);
                        levelCounter++;
                    }
                }
            }
            if (levelCounter == 0)
                noneOnLevel = true;
            zIndex++;
        }
        
        // THEN DRAW ALL THE MOVING TILES
        Iterator<ZombieCrushSagaTile> movingTiles = data.getMovingTiles();
        while (movingTiles.hasNext())
        {
            ZombieCrushSagaTile tile = movingTiles.next();
            renderTile(g, tile);
        }
        ArrayList<ZombieCrushSagaTile> needTiles = data.getSixTiles();
        for (int i=0;i<needTiles.size();i++)
        {
            ZombieCrushSagaTile tile = needTiles.get(i);
            renderTile(g, tile);
        }
    }
    public void renderTile(Graphics g, ZombieCrushSagaTile tileToRender)
    {
        // ONLY RENDER VISIBLE TILES
        if (!tileToRender.getState().equals(INVISIBLE_STATE))
        {
            // FIRST DRAW THE BLANK TILE IMAGE
            if (tileToRender.getState().equals(SELECTED_STATE))
                g.drawImage(blankTileSelectedImage, (int)tileToRender.getX(), (int)tileToRender.getY(), null);
            else if (tileToRender.getState().equals(VISIBLE_STATE))
                g.drawImage(blankTileImage, (int)tileToRender.getX(), (int)tileToRender.getY(), null);
            
            // THEN THE TILE IMAGE
            SpriteType bgST = tileToRender.getSpriteType();
            Image img = bgST.getStateImage(tileToRender.getState());
            g.drawImage(img, (int)tileToRender.getX()+TILE_IMAGE_OFFSET, (int)tileToRender.getY()+TILE_IMAGE_OFFSET, bgST.getWidth(), bgST.getHeight(), null); 
            
            // IF THE TILE IS SELECTED, HIGHLIGHT IT
            if (tileToRender.getState().equals(SELECTED_STATE))
            {
                g.setColor(SELECTED_TILE_COLOR);
                g.fillRoundRect((int)tileToRender.getX(), (int)tileToRender.getY(), bgST.getWidth(), bgST.getHeight(),5,5);
            }
            else {
                if (tileToRender.getState().equals(INCORRECTLY_SELECTED_STATE))
            {
                g.setColor(INCORRECTLY_SELECTED_TILE_COLOR);
                g.fillRoundRect((int)tileToRender.getX(), (int)tileToRender.getY(), bgST.getWidth(), bgST.getHeight(),5,5);
            }              
                        
            }
        }        
    }
    public void renderCheckToMoves(Graphics g){
        if (((ZombieCrushSagaMiniGame)game).isCurrentScreenState(GAME_SCREEN_STATE)){
            ZombieCrushSagaDataModel dm=(ZombieCrushSagaDataModel)game.getDataModel();
            if(!dm.getEC().isEmpty()&&dm.beginToUpdateBoard&&!dm.isProcessMoving()){
                dm.sleep(100);
                dm.setSpeed(2);
                 if(!dm.clearthreethree)
                   dm.addScoreTimes();
                 else{
                     dm.timeOfElimination=0;
                     dm.clearthreethree=false;
                 }
           

                 ArrayList <Integer>emptyCol=new ArrayList<Integer>();
                 while(dm.getEC().size()!=0){
                     emptyCol.add(dm.getEC().remove(0));
                 }
                 while(!emptyCol.isEmpty()){
                 int ecol = emptyCol.remove(0);
                 for (int j = dm.getTileGrid()[0].length - 1; j >= 0;j--) {
                          if( dm.getTileGrid()[ecol][j].size()>0)
                           if (dm.checkFinal(dm.getTileGrid()[ecol][j].get(dm.getTileGrid()[ecol][j].size()-1))) {
                               dm.moveTiles( dm.getTileGrid()[ecol][j].get(dm.getTileGrid()[ecol][j].size()-1));
                            
                              
                           }}
                 }
                 
                 dm.clear();
                   fill=true;

                 
            }
                
           
        }
   
   }
	
	/**
	 * Helper method for rendering the tiles that are currently moving.
	 * 
	 * @param g
	 *            Rendering context for this panel.
	 * 

	/**
	 * Renders the game dialog boxes.
	 * 
	 * @param g
	 *            This panel's graphics context.
	 */
	public void renderDialogs(Graphics g) {
		// GET EACH DECOR IMAGE ONE AT A TIME
		Collection<Sprite> dialogSprites = game.getGUIDialogs().values();
		for (Sprite s : dialogSprites) {
			// RENDER THE DIALOG, NOTE IT WILL ONLY DO IT IF IT'S VISIBLE
			renderSprite(g, s);
		}
	}

	/**
	 * Renders the s Sprite into the Graphics context g. Note that each Sprite
	 * knows its own x,y coordinate location.
	 * 
	 * @param g
	 *            the Graphics context of this panel
	 * 
	 * @param s
	 *            the Sprite to be rendered
	 */
	public void renderSprite(Graphics g, Sprite s) {
		// ONLY RENDER THE VISIBLE ONES
		if (!s.getState().equals(INVISIBLE_STATE)) {
			SpriteType bgST = s.getSpriteType();
			Image img = bgST.getStateImage(s.getState());

			g.drawImage(img, (int) s.getX(), (int) s.getY(), bgST.getWidth(),
					bgST.getHeight(), null);
		}
	}

	/**
	 * This method renders grid lines in the game tile grid to help during
	 * debugging.
	 * 
	 * @param g
	 *            Graphics context for this panel.
	 */
	public void renderGrid(Graphics g) {
		// ONLY RENDER THE GRID IF WE'RE DEBUGGING
		if (data.isDebugTextRenderingActive()) {
			for (int i = 0; i < data.getGridColumns(); i++) {
				for (int j = 0; j < data.getGridRows(); j++) {
					int x = data.calculateTileXInGrid(i, 0);
					int y = data.calculateTileYInGrid(j, 0);
					g.drawRect(x, y, TILE_IMAGE_WIDTH, TILE_IMAGE_HEIGHT);
				}
			}
		}
	}

	/**
	 * Renders the debugging text to the panel. Note that the rendering will
	 * only actually be done if data has activated debug text rendering.
	 * 
	 * @param g
	 *            the Graphics context for this panel
	 */
	public void renderDebuggingText(Graphics g) {
		// IF IT'S ACTIVATED
		if (data.isDebugTextRenderingActive()) {
			// ENABLE PROPER RENDER SETTINGS
			g.setFont(DEBUG_TEXT_FONT);
			g.setColor(DEBUG_TEXT_COLOR);

			// GO THROUGH ALL THE DEBUG TEXT
			Iterator<String> it = data.getDebugText().iterator();
			int x = data.getDebugTextX();
			int y = data.getDebugTextY();
			while (it.hasNext()) {
				// RENDER THE TEXT
				String text = it.next();
				g.drawString(text, x, y);
				y += 20;
			}
		}
	}
	
	

}

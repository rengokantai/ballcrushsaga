package zombiecrushsaga.data;

import zombiecrushsaga.file.ZombieCrushSagaFileManager;
import zombiecrushsaga.ui.ZombieCrushSagaTile;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import zombiecrushsaga.ZombieCrushSaga.ZombieCrushSagaPropertyType;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.SpriteType;
import properties_manager.PropertiesManager;
import static zombiecrushsaga.ZombieCrushSagaConstants.*;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import zombiecrushsaga.ui.ZombieCrushSagaPanel;

/**
 * This class manages the game data for Mahjong Solitaire.
 * 
 * @author Richard McKenna
 */
public class ZombieCrushSagaDataModel extends MiniGameDataModel {
	// THIS CLASS HAS A REFERERENCE TO THE MINI GAME SO THAT IT
	// CAN NOTIFY IT TO UPDATE THE DISPLAY WHEN THE DATA MODEL CHANGES
	private HashMap<String, ZombieLevelRecord> levelRecords;

	private double speed;
	private MiniGame miniGame;
	// THE LEVEL GRID REFERS TO THE LAYOUT FOR A GIVEN LEVEL, MEANING
	// HOW MANY TILES FIT INTO EACH CELL WHEN FIRST STARTING A LEVEL
	private int[][] levelGrid;
	private int addOtherTile;
	// LEVEL GRID DIMENSIONS
	private int gridColumns;
	private int gridRows;
	private int howmanystar;

	private boolean startPlaying;
	private ArrayList<int[]> needToRemove;
	private ArrayList<int[]> currentNeed;
	// THIS STORES THE TILES ON THE GRID DURING THE GAME
	private ArrayList<ZombieCrushSagaTile>[][] tileGrid;

	private ArrayList<ZombieCrushSagaTile>[][] currentGrid;
	// THESE ARE THE TILES THE PLAYER HAS MATCHED
	private ArrayList<ZombieCrushSagaTile> stackTiles;
	// supplement
	private ArrayList<ZombieCrushSagaTile> supplementTiles;
	// L T shape
	private ArrayList<ZombieCrushSagaTile>[] wrappedTiles;
	// 4 in a row
	private ArrayList<ZombieCrushSagaTile>[] stripedTiles;
	private ArrayList<ZombieCrushSagaTile>[] glassTiles;
	// 5 in a row
	private ArrayList<ZombieCrushSagaTile> colorTiles;

	private ArrayList<ZombieCrushSagaTile>[] testTiles;
	// THESE ARE THE TILES THAT ARE MOVING AROUND, AND SO WE HAVE TO UPDATE
	private ArrayList<ZombieCrushSagaTile> movingTiles;
	// THIS IS A SELECTED TILE, MEANING THE FIRST OF A PAIR THE PLAYER
	// IS TRYING TO MATCH. THERE CAN ONLY BE ONE OF THESE AT ANY TIME
	private int levelTimes;
	public int timeOfElimination;
	private boolean stop;
	private int counter;
	public boolean finishExchange;
	public boolean startClear;
	public boolean startFillEmptyCol;
	public boolean beginToUpdateBoard;
	public ZombieCrushSagaTile selectedTile;
	public ZombieCrushSagaTile selectTile;
	// THE INITIAL LOCATION OF TILES BEFORE BEING PLACED IN THE GRID
	private int unassignedTilesX;
	private int unassignedTilesY;
	// THESE ARE USED FOR TIMING THE GAME
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;
	private GregorianCalendar time;
	public long Score;
	private int currentMoves;
	// THE REFERENCE TO THE FILE BEING PLAYED
	private String currentLevel;
	private ArrayList<Integer> ec;

	public boolean freeMove;
	public boolean replaceSpecial;
	public ZombieCrushSagaTile replacingSpecial;
	public int numberOfTiles;
	private ZombieCrushSagaTile[][] backGrid;
	public boolean isBom;
	public int[] Bom;
	private ArrayList<ZombieCrushSagaTile> jellyTiles;
	private ArrayList<ZombieCrushSagaTile> backTiles;
	private int[][] score;

	public boolean processBag;
	public boolean clearthreethree;

	/**
	 * Constructor for initializing this data model, it will create the data
	 * structures for storing tiles, but not the tile grid itself, that is
	 * dependent of file loading, and so should be subsequently initialized.
	 * 
	 * @param initMiniGame
	 *            The Mahjong game UI.
	 */
	public ZombieCrushSagaDataModel(MiniGame initMiniGame) {
		// KEEP THE GAME FOR LATER
		miniGame = initMiniGame;
		Score = 0;
		this.currentMoves = 0;
		needToRemove = new ArrayList<int[]>();
		// INIT THESE FOR HOLDING MATCHED AND MOVING TILES
		stackTiles = new ArrayList();
		supplementTiles = new ArrayList();
		movingTiles = new ArrayList();
		ec = new ArrayList<Integer>();
		Bom = new int[2];
		wrappedTiles = new ArrayList[6];
		for (int i = 0; i < 6; i++)
			wrappedTiles[i] = new ArrayList();
		stripedTiles = new ArrayList[6];
		for (int i = 0; i < 6; i++)
			stripedTiles[i] = new ArrayList();
		glassTiles = new ArrayList[6];
		for (int i = 0; i < 6; i++)
			glassTiles[i] = new ArrayList();

		colorTiles = new ArrayList<ZombieCrushSagaTile>();

		jellyTiles = new ArrayList<ZombieCrushSagaTile>();
		backTiles = new ArrayList<ZombieCrushSagaTile>();
		testTiles = new ArrayList[6];
		for (int i = 0; i < 6; i++)
			testTiles[i] = new ArrayList();
		addOtherTile = 0;
		startPlaying = false;
		speed = 70;
		stop = false;
		timeOfElimination = 1;
	}

	public void setData(int score, int moves) {
		this.currentMoves = 0;
		this.Score = 0;
	}



	public double getSpeed() {
		return this.speed;
	}

	/*
	 * public int getStar(){
	 * 
	 * 
	 * int le=Integer.parseInt(currentLevel.substring(5));
	 * if(Score>=FIRST_STAR_SCORE[le-1]&&Score<Level_2_Star[le-1]){
	 * howmanystar=1;} else if
	 * (Score>=Level_2_Star[le-1]&&Score<Level_3_Star[le-1]){ howmanystar=2; }
	 * else howmanystar=3; return howmanystar;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	public ArrayList<Integer> getEC() {
		return this.ec;
	}

	// INIT METHODS - AFTER CONSTRUCTION, THESE METHODS SETUP A GAME FOR USE
	// - initTiles
	// - initTile
	// - initLevelGrid
	// - initSpriteType
	/**
	 * This method loads the tiles, creating an individual sprite for each. Note
	 * that tiles may be of various types, which is important during the tile
	 * matching tests.
	 */
	public void initTiles() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		String imgPath = props
				.getProperty(ZombieCrushSagaPropertyType.IMG_PATH);
		int spriteTypeID = 0;
		SpriteType sT;

		/*
		 * // WE'LL RENDER ALL THE TILES ON TOP OF THE BLANK TILE String
		 * blankTileFileName =
		 * props.getProperty(ZombieCrushSagaPropertyType.BLANK_TILE_IMAGE_NAME);
		 * BufferedImage blankTileImage = miniGame.loadImageWithColorKey(imgPath
		 * + blankTileFileName, COLOR_KEY); ((ZombieCrushSagaPanel)
		 * (miniGame.getCanvas())).setBlankTileImage(blankTileImage);
		 */

		// THIS IS A HIGHLIGHTED BLANK TILE FOR WHEN THE PLAYER SELECTS ONE
		String blankTileSelectedFileName = props
				.getProperty(ZombieCrushSagaPropertyType.BLANK_TILE_SELECTED_IMAGE_NAME);
		BufferedImage blankTileSelectedImage = miniGame.loadImageWithColorKey(
				imgPath + blankTileSelectedFileName, COLOR_KEY);
		((ZombieCrushSagaPanel) (miniGame.getCanvas()))
				.setBlankTileSelectedImage(blankTileSelectedImage);

		// AND THEN TYPE C, FOR WHICH THERE ARE 4 OF EACH
		// THIS IS ANALOGOUS TO THE CHARACTER AND NUMBER TILES IN FLAVORLESS
		// MAHJONG
		ZombieCrushSagaMiniGame msmg = (ZombieCrushSagaMiniGame) miniGame;
		ZombieCrushSagaRecord record = msmg.getPlayerRecord();
		ZombieCrushSagaFileManager zcsm = new ZombieCrushSagaFileManager(msmg);

		// int chess=0;

		ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
		int chess = 100;
		// int
		// chess=TILEN[Integer.parseInt(scoress.get(0).charAt(currentLevel.length()-1)+"")-1];
		/*
		 * int
		 * level=Integer.parseInt(this.getCurrentLevel().charAt(this.getCurrentLevel
		 * ().length()-5)+""); if(level==1) chess=80; if(level==3) chess=80;
		 */
		/*
		 * if(zcsm.getState()!=SPLASH_SCREEN_STATE)
		 * if(Integer.parseInt(this.getCurrentLevel
		 * ().charAt(currentLevel.length()-1)+"")==1) chess=100;
		 */

		// chess=zcsm.numOfTiles;

		/*
		 * ArrayList<String> typeCTiles = props
		 * .getPropertyOptionsList(ZombieCrushSagaPropertyType.TYPE_C_TILES);
		 */
		// for (int i = 0; i < typeCTiles.size(); i++) {
		ArrayList<String> typeCTiles = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.TYPE_C_TILES);
		int aa = (int) (Math.random() * (int) ((chess / 6) + 2));
		int bb = (int) (Math.random() * (int) ((chess / 6) + 2));
		int cc = (int) (Math.random() * (int) ((chess / 6) + 2));
		int dd = (int) (Math.random() * (int) ((chess / 6) + 2));
		int ee = (int) (Math.random() * (int) ((chess / 6) + 2));
		int ff = chess - aa - bb - cc - dd - ee;

		String imgFile = imgPath + typeCTiles.get(0);
		sT = initTileSpriteType(imgFile, TILE_SPRITE_TYPE_PREFIX + spriteTypeID);

		for (int j = 0; j < aa; j++) {
			initTile(sT, TILE_C_TYPE);
		}
		spriteTypeID++;

		String imgFile1 = imgPath + typeCTiles.get(1);
		sT = initTileSpriteType(imgFile1, TILE_SPRITE_TYPE_PREFIX
				+ spriteTypeID);

		for (int j = 0; j < bb; j++) {
			initTile(sT, TILE_C_TYPE);
		}
		spriteTypeID++;
		String imgFile2 = imgPath + typeCTiles.get(2);
		sT = initTileSpriteType(imgFile2, TILE_SPRITE_TYPE_PREFIX
				+ spriteTypeID);

		for (int j = 0; j < cc; j++) {
			initTile(sT, TILE_C_TYPE);
		}
		spriteTypeID++;
		String imgFile3 = imgPath + typeCTiles.get(3);
		sT = initTileSpriteType(imgFile3, TILE_SPRITE_TYPE_PREFIX
				+ spriteTypeID);

		for (int j = 0; j < dd; j++) {
			initTile(sT, TILE_C_TYPE);
		}
		spriteTypeID++;
		String imgFile4 = imgPath + typeCTiles.get(4);
		sT = initTileSpriteType(imgFile4, TILE_SPRITE_TYPE_PREFIX
				+ spriteTypeID);

		for (int j = 0; j < ee; j++) {
			initTile(sT, TILE_C_TYPE);
		}
		spriteTypeID++;

		String imgFile5 = imgPath + typeCTiles.get(5);
		sT = initTileSpriteType(imgFile5, TILE_SPRITE_TYPE_PREFIX
				+ spriteTypeID);

		for (int j = 0; j < ff; j++) {
			initTile(sT, TILE_C_TYPE);
		}
		spriteTypeID++;
		this.addOtherTile = 1;
		spriteTypeID = 0;
		for (int i = 0; i < 200; i++) // using to fill empty
		{
			int pos = (int) (Math.random() * 6);

			String imgFile15 = imgPath + typeCTiles.get(pos);
			sT = initTileSpriteType(imgFile15, TILE_SPRITE_TYPE_PREFIX + pos);
			initTile(sT, TILE_C_TYPE);
		}
		ArrayList<String> typeSTiles = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.TYPE_S_TILES);
		this.addOtherTile = 2;
		spriteTypeID = 0;
		for (int i = 0; i < 6; i++) // using to striped
		{
			counter = i;
			for (int j = 0; j < 50; j++) {

				String imgFile6 = imgPath + typeSTiles.get(i);
				sT = initTileSpriteType(imgFile6, TILE_SPRITE_TYPE_PREFIX + i);
				initTile(sT, TILE_S_TYPE);
			}
		}
		ArrayList<String> typeWTiles = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.TYPE_W_TILES);
		this.addOtherTile = 3;
		spriteTypeID = 0;
		for (int i = 0; i < 6; i++) // using to wrapped
		{
			counter = i;
			for (int j = 0; j < 50; j++) {

				String imgFile7 = imgPath + typeWTiles.get(i);
				sT = initTileSpriteType(imgFile7, TILE_SPRITE_TYPE_PREFIX + i);
				initTile(sT, TILE_W_TYPE);
			}
		}
		ArrayList<String> typeColorTiles = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.TYPE_COLOR_TILES);
		this.addOtherTile = 4;
		spriteTypeID = 0;
		for (int i = 0; i < 30; i++) // color tile
		{

			String imgFile8 = imgPath + typeColorTiles.get(0);
			sT = initTileSpriteType(imgFile8, TILE_SPRITE_TYPE_PREFIX + 7);
			initTile(sT, TILE_COLOR_TYPE);

		}
		/*
		 * ArrayList<String> typeGTiles =
		 * props.getPropertyOptionsList(ZombieCrushSagaPropertyType
		 * .TYPE_G_TILES); this.addOtherTile = 5; spriteTypeID = 0; for (int i =
		 * 0; i < 6; i++) // using to glass { counter = i; for (int j = 0; j <
		 * 10; j++) {
		 * 
		 * String imgFile = imgPath + typeGTiles.get(i); sT =
		 * initTileSpriteType(imgFile, TILE_SPRITE_TYPE_PREFIX + i);
		 * initTile(sT, TILE_COLOR_TYPE); } }
		 */

		this.addOtherTile = 6;
		spriteTypeID = 0;
		for (int i = 0; i < typeCTiles.size(); i++) // 100
		{

			String imgFile9 = imgPath + typeCTiles.get(i);
			counter = i;
			sT = initTileSpriteType(imgFile9, TILE_SPRITE_TYPE_PREFIX + i);

			for (int j = 0; j < 200; j++) {
				initTile(sT, TILE_C_TYPE);
			}

		}

		String jellyTiles = BACK_GROUND_TYPE;
		this.addOtherTile = 7;
		spriteTypeID = 0;
		for (int i = 0; i < 200; i++) // 100
		{

			String imgFile10 = imgPath + "./zomjong/background.png";
			counter = i;
			sT = initTileSpriteType(imgFile10, TILE_SPRITE_TYPE_PREFIX + 10);

			initTile(sT, BACK_GROUND_TYPE);

		}

		String backTiles = JELLY_TYPE;
		this.addOtherTile = 8;
		spriteTypeID = 0;
		for (int i = 0; i < 200; i++) // 100
		{

			String imgFile11 = imgPath + "./zomjong/jelly.png";
			counter = i;
			sT = initTileSpriteType(imgFile11, TILE_SPRITE_TYPE_PREFIX + 11);

			initTile(sT, JELLY_TYPE);

		}

	}

	public void addTestTiles() {
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		String imgPath = props
				.getProperty(ZombieCrushSagaPropertyType.IMG_PATH);
		String blankTileSelectedFileName = props
				.getProperty(ZombieCrushSagaPropertyType.BLANK_TILE_SELECTED_IMAGE_NAME);
		BufferedImage blankTileSelectedImage = miniGame.loadImageWithColorKey(
				imgPath + blankTileSelectedFileName, COLOR_KEY);
		((ZombieCrushSagaPanel) (miniGame.getCanvas()))
				.setBlankTileSelectedImage(blankTileSelectedImage);
		SpriteType sT;

		// AND THEN TYPE C, FOR WHICH THERE ARE 4 OF EACH
		// THIS IS ANALOGOUS TO THE CHARACTER AND NUMBER TILES IN FLAVORLESS
		// MAHJONG
		ArrayList<String> typeCTiles = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.TYPE_C_TILES);

		for (int i = 0; i < testTiles.length; i++)
			if (testTiles[i].size() < 150) {
				this.addOtherTile = 6;

				String imgFile = imgPath + typeCTiles.get(i);
				counter = i;
				sT = initTileSpriteType(imgFile, TILE_SPRITE_TYPE_PREFIX + i);

				for (int j = 0; j < 160; j++) {
					initTile(sT, TILE_C_TYPE);
				}

			}

	}

	public void addBackTiles() {
		if (jellyTiles.size() < 200 || backTiles.size() < 200) {
			PropertiesManager props = PropertiesManager.getPropertiesManager();
			String imgPath = props
					.getProperty(ZombieCrushSagaPropertyType.IMG_PATH);
			String blankTileSelectedFileName = props
					.getProperty(ZombieCrushSagaPropertyType.BLANK_TILE_SELECTED_IMAGE_NAME);
			BufferedImage blankTileSelectedImage = miniGame
					.loadImageWithColorKey(imgPath + blankTileSelectedFileName,
							COLOR_KEY);
			((ZombieCrushSagaPanel) (miniGame.getCanvas()))
					.setBlankTileSelectedImage(blankTileSelectedImage);
			SpriteType sT;

			// AND THEN TYPE C, FOR WHICH THERE ARE 4 OF EACH
			// THIS IS ANALOGOUS TO THE CHARACTER AND NUMBER TILES IN FLAVORLESS
			// MAHJONG

			this.addOtherTile = 7;

			for (int i = 0; i < (201 - jellyTiles.size()); i++) // using to fill
																// empty
			{
				String imgFile = imgPath + "./zomjong/background.png";
				counter = i;
				sT = initTileSpriteType(imgFile, TILE_SPRITE_TYPE_PREFIX + 10);

				initTile(sT, BACK_GROUND_TYPE);
			}

			this.addOtherTile = 8;

			for (int i = 0; i < (210 - backTiles.size()); i++) // using to fill
																// empty
			{
				String imgFile = imgPath + "./zomjong/jelly.png";
				counter = i;
				sT = initTileSpriteType(imgFile, TILE_SPRITE_TYPE_PREFIX + 11);

				initTile(sT, JELLY_TYPE);
			}
		}
	}



	public void addTiles() {
		if (supplementTiles.size() < 300) {
			PropertiesManager props = PropertiesManager.getPropertiesManager();
			String imgPath = props
					.getProperty(ZombieCrushSagaPropertyType.IMG_PATH);
			String blankTileSelectedFileName = props
					.getProperty(ZombieCrushSagaPropertyType.BLANK_TILE_SELECTED_IMAGE_NAME);
			BufferedImage blankTileSelectedImage = miniGame
					.loadImageWithColorKey(imgPath + blankTileSelectedFileName,
							COLOR_KEY);
			((ZombieCrushSagaPanel) (miniGame.getCanvas()))
					.setBlankTileSelectedImage(blankTileSelectedImage);
			SpriteType sT;

			// AND THEN TYPE C, FOR WHICH THERE ARE 4 OF EACH
			// THIS IS ANALOGOUS TO THE CHARACTER AND NUMBER TILES IN FLAVORLESS
			// MAHJONG
			ArrayList<String> typeCTiles = props
					.getPropertyOptionsList(ZombieCrushSagaPropertyType.TYPE_C_TILES);
			this.addOtherTile = 1;

			for (int i = 0; i < (310 - supplementTiles.size()); i++) // using to
																		// fill
																		// empty
			{
				int pos = (int) (Math.random() * 6);

				String imgFile = imgPath + typeCTiles.get(pos);
				sT = initTileSpriteType(imgFile, TILE_SPRITE_TYPE_PREFIX + pos);
				initTile(sT, TILE_C_TYPE);
			}
		}
	}

	/**
	 * Helper method for loading the tiles, it constructs the prescribed tile
	 * type using the provided sprite type.
	 * 
	 * @param sT
	 *            The sprite type to use to represent this tile during
	 *            rendering.
	 * 
	 * @param tileType
	 *            The type of tile. Note that there are 3 broad categories.
	 */
	private void initTile(SpriteType sT, String tileType) {
		// CONSTRUCT THE TILE
		ZombieCrushSagaTile newTile = new ZombieCrushSagaTile(sT,
				unassignedTilesX, unassignedTilesY, 0, 0, INVISIBLE_STATE,
				tileType);
		// AND ADD IT TO THE STACK
		if (addOtherTile == 0) {
			stackTiles.add(newTile);
		} else if (addOtherTile == 1) {
			supplementTiles.add(newTile);
		} else if (addOtherTile == 2) {
			stripedTiles[counter].add(newTile);
		} else if (addOtherTile == 3) {
			wrappedTiles[counter].add(newTile);
		} else if (addOtherTile == 4) {
			colorTiles.add(newTile);
		} else if (addOtherTile == 5) {
			glassTiles[counter].add(newTile);
		} else if (addOtherTile == 6)
			testTiles[counter].add(newTile);
		else if (addOtherTile == 7) {
			jellyTiles.add(newTile);
		} else
			backTiles.add(newTile);

	}

	public boolean finishMoves() {
		int i = Integer.parseInt(this.getCurrentLevel().substring(5));
		int moves = NUMBER_OF_MOVES[i - 1];
		return (this.currentMoves == moves);
	}

	public void processWin() {
		int i = Integer.parseInt(this.getCurrentLevel().substring(5));

		int score = FIRST_STAR_SCORE[i - 1];
		int moves = NUMBER_OF_MOVES[i - 1];
		boolean loss = false;

		if (this.currentMoves == moves) {
			// endGameAsWin();/////////////////////////////
			this.sleep(5);
			for (int k = 0; k < tileGrid.length; k++)
				for (int j = 0; j < tileGrid[0].length; j++) {

					if (tileGrid[k][j].size() > 0) {
						ZombieCrushSagaTile tile = tileGrid[k][j].remove(0);
						if (tile.getTileType().equals(BACK_GROUND_TYPE)) {
							this.endGameAsLoss();

							loss = true;
						}
					}
				}
			if (loss)
				return;
			if (this.Score < score) {

				this.endGameAsLoss();
			} else {

				this.endGameAsWin();
			}
		}
	}

	public void winCheat() {
		int i = Integer.parseInt(this.getCurrentLevel().substring(5));
		for (int k = 0; k < tileGrid.length; k++)
			for (int j = 0; j < tileGrid[0].length; j++) {

				if (tileGrid[k][j].size() > 0) {
					tileGrid[k][j].remove(0);

				}
			}
		int score = THIRD_STAR_SCORE[i - 1];
		int moves = NUMBER_OF_MOVES[i - 1];
		this.currentMoves = moves;
		if (this.Score < score)
			this.Score = score;

		this.endGameAsWin();

	}

	public void loseCheat() {
		int i = Integer.parseInt(this.getCurrentLevel().substring(5));
		for (int k = 0; k < tileGrid.length; k++)
			for (int j = 0; j < tileGrid[0].length; j++) {

				if (tileGrid[k][j].size() > 0) {
					tileGrid[k][j].remove(0);

				}
			}
		int score = FIRST_STAR_SCORE[i - 1];
		int moves = NUMBER_OF_MOVES[i - 1];

		this.currentMoves = moves - 2;

		if (this.Score >= score)
			this.Score = score - 1;
		if (this.Score == 0)
			this.Score = 100;

		this.endGameAsLoss();

		// ((ZombieCrushSagaMiniGame)
		// miniGame).getPlayerRecord().setVisible("Level"+(i+2));
		// }
		// long gameTime = endTime.getTimeInMillis() -
		// startTime.getTimeInMillis();
		/*
		 * int astar=0;
		 * if(Score>=FIRST_STAR_SCORE[i-1]&&Score<Level_2_Star[i-1]){ astar=1;}
		 * if(Score>=Level_2_Star[i-1]&&Score<Level_3_Star[i-1]){ astar=2;}
		 * if(Score>=Level_3_Star[i-1]){ astar=3;}
		 */

	}

	/**
	 * Called after a level has been selected, it initializes the grid so that
	 * it is the proper dimensions.
	 * 
	 * @param initGrid
	 *            The grid distribution of tiles, where each cell specifies the
	 *            number of tiles to be stacked in that cell.
	 * 
	 * @param initGridColumns
	 *            The columns in the grid for the level selected.
	 * 
	 * @param initGridRows
	 *            The rows in the grid for the level selected.
	 */
	public void initLevelGrid(int[][] initGrid, int initGridColumns,
			int initGridRows) {
		// KEEP ALL THE GRID INFO
		levelGrid = initGrid;
		gridColumns = initGridColumns;
		gridRows = initGridRows;

		// AND BUILD THE TILE GRID FOR STORING THE TILES
		// SINCE WE NOW KNOW ITS DIMENSIONS
		tileGrid = new ArrayList[gridColumns][gridRows];
		currentGrid = new ArrayList[gridColumns][gridRows];
		score = new int[gridColumns][gridRows];
		backGrid = new ZombieCrushSagaTile[gridColumns][gridRows];
		for (int i = 0; i < gridColumns; i++) {
			for (int j = 0; j < gridRows; j++) {
				// EACH CELL HAS A STACK OF TILES, WE'LL USE
				// AN ARRAY LIST FOR THE STACK
				tileGrid[i][j] = new ArrayList();
				currentGrid[i][j] = new ArrayList();

			}
		}
		// MAKE ALL THE TILES VISIBLE
		enableTiles(true);
	}

	public boolean isProcessMoving() {
		for (int i = 0; i < tileGrid.length; i++) {
			for (int j = 0; j < tileGrid[0].length; j++) {
				if (tileGrid[i][j].size() > 0 && tileGrid[i][j].size() < 3) {
					ZombieCrushSagaTile tile = tileGrid[i][j]
							.get(tileGrid[i][j].size() - 1);
					if (tile.isMovingToTarget())
						return true;
				}

			}
		}
		return false;
	}

	public void saveCurrentGame() {
		for (int i = 0; i < tileGrid.length; i++) {
			for (int j = 0; j < tileGrid[0].length; j++) {

				if (tileGrid[i][j].size() > 0 && tileGrid[i][j].size() < 3) {
					for (int h = tileGrid[i][j].size() - 1; h >= 0; h--) {

						ZombieCrushSagaTile tile = tileGrid[i][j].get(h);

						tile.setState(INVISIBLE_STATE);
						// PUT IT IN THE GRID
						// tile.setGridCell(i, j);
						currentGrid[i][j].add(tile);
					}

				}

			}
		}

	}

	public void reloadCurrentGame() {
		for (int i = 0; i < tileGrid.length; i++) {
			for (int j = 0; j < tileGrid[0].length; j++) {

				if (currentGrid[i][j].size() > 0) {
					for (int h = currentGrid[i][j].size() - 1; h >= 0; h--) {
						ZombieCrushSagaTile tile = currentGrid[i][j].remove(h);
						tile.setState(VISIBLE_STATE);
						// PUT IT IN THE GRID
						tile.setGridCell(i, j);
						tileGrid[i][j].add(tile);
						// WE'LL ANIMATE IT GOING TO THE GRID, SO FIGURE
						// OUT WHERE IT'S GOING AND GET IT MOVING
						float x = calculateTileXInGrid(i, 0);
						float y = calculateTileYInGrid(j, 0);
						tile.setTarget(x, y);
						tile.startMovingToTarget(speed);
						movingTiles.add(tile);
					}

				}

			}
		}

	}

	/**
	 * This helper method initializes a sprite type for a tile or set of similar
	 * tiles to be created.
	 */
	private SpriteType initTileSpriteType(String imgFile, String spriteTypeID) {
		// WE'LL MAKE A NEW SPRITE TYPE FOR EACH GROUP OF SIMILAR LOOKING TILES
		SpriteType sT = new SpriteType(spriteTypeID);
		addSpriteType(sT);

		// LOAD THE ART
		BufferedImage img = miniGame.loadImageWithColorKey(imgFile, COLOR_KEY);
		Image tempImage = img.getScaledInstance(TILE_IMAGE_WIDTH,
				TILE_IMAGE_HEIGHT, BufferedImage.SCALE_SMOOTH);
		img = new BufferedImage(TILE_IMAGE_WIDTH, TILE_IMAGE_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		img.getGraphics().drawImage(tempImage, 0, 0, null);

		// WE'LL USE THE SAME IMAGE FOR ALL STATES
		sT.addState(INVISIBLE_STATE, img);
		sT.addState(VISIBLE_STATE, img);
		sT.addState(SELECTED_STATE, img);
		sT.addState(INCORRECTLY_SELECTED_STATE, img);
		return sT;
	}

	// ACCESSOR METHODS
	/**
	 * Accessor method for getting the level currently being played.
	 * 
	 * @return The level name used currently for the game screen.
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}

	public long getCurrentScore() {
		return Score;
	}

	public long getCurrentMoves() {
		return currentMoves;
	}

	/**
	 * Accessor method for getting the number of tile columns in the game grid.
	 * 
	 * @return The number of columns (left to right) in the grid for the level
	 *         currently loaded.
	 */
	public int getGridColumns() {
		return gridColumns;
	}

	public void setStart(boolean t) {
		this.startPlaying = t;
	}

	public void setLevelMultiplier(int lm) {
		this.levelTimes = lm;
	}

	/**
	 * Accessor method for getting the number of tile rows in the game grid.
	 * 
	 * @return The number of rows (top to bottom) in the grid for the level
	 *         currently loaded.
	 */
	public int getGridRows() {
		return gridRows;
	}

	/**
	 * Accessor method for getting the tile grid, which has all the tiles the
	 * user may select from.
	 * 
	 * @return The main 2D grid of tiles the user selects tiles from.
	 */

	/**
	 * Used to calculate the x-axis pixel location in the game grid for a tile
	 * placed at column with stack position z.
	 * 
	 * @param column
	 *            The column in the grid the tile is located.
	 * 
	 * @param z
	 *            The level of the tile in the stack at the given grid location.
	 * 
	 * @return The x-axis pixel location of the tile
	 */
	public int calculateTileXInGrid(int column, int z) {
		int cellWidth = TILE_IMAGE_WIDTH;
		float leftEdge = miniGame.getBoundaryLeft();
		return (int) (leftEdge + (cellWidth * column) - (Z_TILE_OFFSET * z));
	}

	/**
	 * Used to calculate the y-axis pixel location in the game grid for a tile
	 * placed at row with stack position z.
	 * 
	 * @param row
	 *            The row in the grid the tile is located.
	 * 
	 * @param z
	 *            The level of the tile in the stack at the given grid location.
	 * 
	 * @return The y-axis pixel location of the tile
	 */
	public int calculateTileYInGrid(int row, int z) {
		int cellHeight = TILE_IMAGE_HEIGHT;
		float topEdge = miniGame.getBoundaryTop();
		return (int) (topEdge + (cellHeight * row) - (Z_TILE_OFFSET * z));
	}

	/**
	 * Used to calculate the grid column for the x-axis pixel location.
	 * 
	 * @param x
	 *            The x-axis pixel location for the request.
	 * 
	 * @return The column that corresponds to the x-axis location x.
	 */
	public int calculateGridCellColumn(int x) {
		float leftEdge = miniGame.getBoundaryLeft();
		x = (int) (x - leftEdge);
		return x / TILE_IMAGE_WIDTH;
	}

	/**
	 * Used to calculate the grid row for the y-axis pixel location.
	 * 
	 * @param y
	 *            The y-axis pixel location for the request.
	 * 
	 * @return The row that corresponds to the y-axis location y.
	 */
	public int calculateGridCellRow(int y) {
		float topEdge = miniGame.getBoundaryTop();
		y = (int) (y - topEdge);
		return y / TILE_IMAGE_HEIGHT;
	}

	// TIME TEXT METHODS
	// - timeToText
	// - gameTimeToText
	/**
	 * This method creates and returns a textual description of the timeInMillis
	 * argument as a time duration in the format of (H:MM:SS).
	 * 
	 * @param timeInMillis
	 *            The time to be represented textually.
	 * 
	 * @return A textual representation of timeInMillis.
	 */
	public String timeToText(long timeInMillis) {
		// FIRST CALCULATE THE NUMBER OF HOURS,
		// SECONDS, AND MINUTES
		long hours = timeInMillis / MILLIS_IN_AN_HOUR;
		timeInMillis -= hours * MILLIS_IN_AN_HOUR;
		long minutes = timeInMillis / MILLIS_IN_A_MINUTE;
		timeInMillis -= minutes * MILLIS_IN_A_MINUTE;
		long seconds = timeInMillis / MILLIS_IN_A_SECOND;

		// THEN ADD THE TIME OF GAME SUMMARIZED IN PARENTHESES
		String minutesText = "" + minutes;
		if (minutes < 10) {
			minutesText = "0" + minutesText;
		}
		String secondsText = "" + seconds;
		if (seconds < 10) {
			secondsText = "0" + secondsText;
		}
		return hours + ":" + minutesText + ":" + secondsText;
	}

	/**
	 * This method builds and returns a textual representation of the game time.
	 * Note that the game may still be in progress.
	 * 
	 * @return The duration of the current game represented textually.
	 */
	public String gameTimeToText() {
		// CALCULATE GAME TIME USING HOURS : MINUTES : SECONDS
		if ((startTime == null) || (endTime == null)) {
			return "";
		}
		long timeInMillis = endTime.getTimeInMillis()
				- startTime.getTimeInMillis();
		return timeToText(timeInMillis);
	}



	public ArrayList<ZombieCrushSagaTile>[][] getTileGrid() {
		return tileGrid;
	}

	public ArrayList<ZombieCrushSagaTile> getSixTiles() {
		return this.supplementTiles;
	}

	/**
	 * Accessor method for getting the stack tiles.
	 * 
	 * @return The stack tiles, which are the tiles the matched tiles are placed
	 *         in.
	 */
	public ArrayList<ZombieCrushSagaTile> getStackTiles() {
		return stackTiles;
	}

	/**
	 * Accessor method for getting the moving tiles.
	 * 
	 * @return The moving tiles, which are the tiles currently being animated as
	 *         they move around the game.
	 */
	public Iterator<ZombieCrushSagaTile> getMovingTiles() {
		return movingTiles.iterator();
	}

	/**
	 * Mutator method for setting the currently loaded level.
	 * 
	 * @param initCurrentLevel
	 *            The level name currently being used to play the game.
	 */
	public void setCurrentLevel(String initCurrentLevel) {
		currentLevel = initCurrentLevel;
	}

	// GAME DATA SERVICE METHODS
	// -enableTiles
	// -findMove
	// -moveAllTilesToStack
	// -moveTiles
	// -playWinAnimation
	// -processMove
	// -selectTile
	// -undoLastMove

	/**
	 * This method can be used to make all of the tiles either visible (true) or
	 * invisible (false). This should be used when switching between the splash
	 * and game screens.
	 * 
	 * @param enable
	 *            Specifies whether the tiles should be made visible or not.
	 */
	public void enableTiles(boolean enable) {
		// PUT ALL THE TILES IN ONE PLACE WHERE WE CAN PROCESS THEM TOGETHER

		moveAllTilesToStack();

		// GO THROUGH ALL OF THEM
		for (ZombieCrushSagaTile tile : stackTiles) {
			// AND SET THEM PROPERLY
			if (enable) {
				tile.setState(VISIBLE_STATE);
			} else {
				tile.setState(INVISIBLE_STATE);
			}
		}
	}

	/**
	 * This method moves all the tiles not currently in the stack to the stack.
	 */
	public void moveAllTilesToStack() {
		for (int i = 0; i < gridColumns; i++) {
			for (int j = 0; j < gridRows; j++) {
				ArrayList<ZombieCrushSagaTile> cellStack = tileGrid[i][j];
				moveTiles(cellStack, stackTiles);
			}
		}
	}

	public boolean checkH(String left, String right, ZombieCrushSagaTile a,
			ZombieCrushSagaTile b) {
		int leftUp = upTraverse(left, a);
		int leftDown = downTraverse(left, a);
		int leftLeft = leftTraverse(left, a);
		int rightUp = upTraverse(right, b);
		int rightDown = downTraverse(right, b);
		int rightRight = rightTraverse(right, b);
		boolean get = false;
		if (leftUp + leftDown >= 2) {

			get = true;
		}
		if (leftLeft >= 2) {

			get = true;
		}
		if (rightUp + rightDown >= 2) {

			get = true;
		}

		if (rightRight >= 2) {

			get = true;
		}

		return get;

	}

	public boolean checkV(String up, String down, ZombieCrushSagaTile a,
			ZombieCrushSagaTile b) {
		boolean get = false;
		int upUp = upTraverse(up, a);
		int upLeft = leftTraverse(up, a);
		int upRight = rightTraverse(up, a);
		int downDown = downTraverse(down, b);
		int downLeft = leftTraverse(down, b);
		int downRight = rightTraverse(down, b);
		if (upUp >= 2) {
			// int pos[] = {col_U, row_U};
			// if (!this.isContain(pos)) {
			// this.currentNeedToMove.add(pos);
			// }
			// this.moveUp(up, col_U, row_U);
			get = true;
		}
		if (upLeft + upRight >= 2) {
			// int pos[] = {col_U, row_U};
			// if (!this.isContain(pos)) {
			// this.currentNeedToMove.add(pos);
			// }
			// this.moveLeft(up, col_U, row_U);
			// this.moveRight(up, col_U, row_U);
			get = true;
		}
		if (downDown >= 2) {
			// int pos[] = {col_D, row_D};
			// if (!this.isContain(pos)) {
			// this.currentNeedToMove.add(pos);
			// }
			// this.moveDown(down, col_D, row_D);
			get = true;
		}
		if (downLeft + downRight >= 2) {
			// int pos[] = {col_D, row_D};
			// if (!this.isContain(pos)) {
			// this.currentNeedToMove.add(pos);
			// }
			// this.moveLeft(down, col_D, row_D);
			// this.moveRight(down, col_D, row_D);
			get = true;
		}
		return get;

	}

	private boolean isLegalMove(ZombieCrushSagaTile selectTile) {
		int col = selectTile.getGridColumn();
		int row = selectTile.getGridRow();
		int col1 = selectedTile.getGridColumn();
		int row1 = selectedTile.getGridRow();

		
		if (row == row1) {
			if (col + 1 == col1) { 
				return true;
			}
			if (col == col1 + 1) {

				return true;
			}

		}
		if (col == col1) {
			if (row + 1 == row1) {
				return true;

			}
			if (row == row1 + 1) {
				return true;
			}

		}
		return false;

	}

	public boolean FinishAllMove() {
		for (int i = 0; i < tileGrid.length; i++) {
			for (int j = 0; j < tileGrid[0].length; j++) {
				if (tileGrid[i][j].size() == 2) {
					if (checkFinal(tileGrid[i][j]
							.get(tileGrid[i][j].size() - 1))) {
						return false;
						// int pos[] = {col_D, row_D};
						// if (!this.isContain(pos)) {
						// this.currentNeedToMove.add(pos);
						// }
						// this.moveDown(down, col_D, row_D);
					}

				}
			}
		}
		return true;
	}

	// checkFinal each tile and move
	public boolean checkFinal(ZombieCrushSagaTile tile) {

		String select = tile.getSpriteType().getSpriteTypeID();
		int left = this.leftTraverse(select, tile);
		int right = this.rightTraverse(select, tile);
		int up = this.upTraverse(select, tile);
		int down = this.downTraverse(select, tile);
		if (left + right >= 2) {
			if (this.startPlaying) {
				if (!tile.getTileType().equals(TILE_J_TYPE)
						&& !tile.getTileType().equals(TILE_C_TYPE)) {
					this.replacingSpecial = tile;
				}
			}
			return true;
		}
		if (up + down >= 2) {
			if (this.startPlaying) {
				if (!tile.getTileType().equals(TILE_J_TYPE)
						&& !tile.getTileType().equals(TILE_C_TYPE)) {
					this.replacingSpecial = tile;
				}
			}
			return true;
		}

		return false;

	}

	public void moveAllSameType(ZombieCrushSagaTile tile) {
		for (int i = 0; i < tileGrid.length; i++) {
			for (int j = 0; j < tileGrid[0].length; j++) {
				if (tileGrid[i][j].size() == 2)
					if (tileGrid[i][j].get(tileGrid[i][j].size() - 1)
							.getSpriteType().getSpriteTypeID()
							.equals(tile.getSpriteType().getSpriteTypeID())) {

						this.removeAnimation(i, j, 1);
						score[i][j] = (this.levelTimes * 10 + 20) * 3;

					}
			}
		}
	}

	public int moveTiles(ZombieCrushSagaTile tile) { // moving the match tiles
														// and add score
		String select = tile.getSpriteType().getSpriteTypeID();
		int col = tile.getGridColumn();
		int row = tile.getGridRow();
		int left = this.leftTraverse(select, tile);
		int right = this.rightTraverse(select, tile);
		int up = this.upTraverse(select, tile);
		int down = this.downTraverse(select, tile);


		if (up + down == 3) {
			this.moveDown(select, tile);
			this.moveUp(select, tile);

			this.replcaingTiles(
					stripedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(4);
			this.scoreDown(select, tile, this.calculateScore(4) / 4);
			this.scoreUp(select, tile, this.calculateScore(4) / 4);
			score[col][row] = this.calculateScore(4) / 4;

			return 4;
		}
		if (left + right == 3) {
			this.moveLeft(select,tile);
			this.moveRight(select, tile);

			this.replcaingTiles(
					stripedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(4);
			this.scoreLeft(select, tile, this.calculateScore(4) / 4);
			this.scoreRight(select, tile,this.calculateScore(4) / 4);
			score[col][row] = this.calculateScore(4) / 4;

			return 4;
		}
		if (left + right == 4) {
			this.moveLeft(select, tile);
			this.moveRight(select, tile);

			this.replcaingTiles(colorTiles.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreLeft(select, tile, this.calculateScore(5) / 5);
			this.scoreRight(select, tile, this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;

		}
		if (up + down == 4) {
			this.moveDown(select, tile);
			this.moveUp(select, tile);

			this.replcaingTiles(colorTiles.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreDown(select, tile, this.calculateScore(5) / 5);
			this.scoreUp(select, tile, this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;
		}

		if (left == 2 && up == 2) {
			this.moveLeft(select, tile);
			this.moveUp(select, tile);

			this.replcaingTiles(
					wrappedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreLeft(select, tile,this.calculateScore(5) / 5);
			this.scoreUp(select,tile, this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;

		}
		if (left == 2 && down == 2) {
			this.moveLeft(select,tile);
			this.moveDown(select, tile);

			this.replcaingTiles(
					wrappedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreLeft(select, tile,this.calculateScore(5) / 5);
			this.scoreDown(select, tile,this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;
		}
		if (right == 2 && up == 2) {
			this.moveRight(select, tile);
			this.moveUp(select,tile);

			this.replcaingTiles(
					wrappedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreUp(select, tile,this.calculateScore(5) / 5);
			this.scoreRight(select, tile,this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;
		}
		if (right == 2 && down == 2) {
			this.moveRight(select, tile);
			this.moveDown(select, tile);

			this.replcaingTiles(
					wrappedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreDown(select, tile, this.calculateScore(5) / 5);
			this.scoreRight(select, tile, this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;

		}
		if (left == 1 && right == 1 && up == 2) {
			this.moveLeft(select, tile);
			this.moveRight(select,tile);
			this.moveUp(select, tile);

			this.replcaingTiles(
					wrappedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreLeft(select, tile,this.calculateScore(5) / 5);
			this.scoreRight(select, tile, this.calculateScore(5) / 5);
			this.scoreUp(select,tile, this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;

		}

		if (left == 1 && right == 1 && down == 2) {
			this.moveLeft(select, tile);
			this.moveRight(select, tile);
			this.moveDown(select, tile);

			this.replcaingTiles(
					wrappedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreLeft(select, tile, this.calculateScore(5) / 5);
			this.scoreRight(select, tile, this.calculateScore(5) / 5);
			this.scoreDown(select, tile, this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;

		}

		if (up == 1 && down == 1 && left == 2) {
			this.moveUp(select,tile);
			this.moveDown(select, tile);
			this.moveLeft(select, tile);

			this.replcaingTiles(
					wrappedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreLeft(select, tile, this.calculateScore(5) / 5);
			this.scoreDown(select, tile, this.calculateScore(5) / 5);
			this.scoreUp(select, tile, this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;

		}
		if (up == 1 && down == 1 && right == 2) {
			this.moveUp(select, tile);
			this.moveDown(select, tile);
			this.moveRight(select, tile);

			this.replcaingTiles(
					wrappedTiles[select.charAt(select.length() - 1) - '0']
							.remove(0), col, row);
			this.clearJelly(col, row);
			this.Score += this.calculateScore(5);
			this.scoreDown(select, tile, this.calculateScore(5) / 5);
			this.scoreRight(select, tile, this.calculateScore(5) / 5);
			this.scoreUp(select, tile, this.calculateScore(5) / 5);
			score[col][row] = this.calculateScore(5) / 5;

			return 5;

		}
		if (left + right == 2 || left == 2 || right == 2) {
			this.moveLeft(select, tile);
			this.moveRight(select, tile);
			int pos[] = { col, row };
			if (!this.isContain(pos)) {
				this.needToRemove.add(pos);
			}

			this.scoreLeft(select, tile, this.calculateScore(3) / 3);
			this.scoreRight(select, tile, this.calculateScore(3) / 3);

			score[col][row] = this.calculateScore(3) / 3;
			this.Score += this.calculateScore(3);

			return 3;
		}

		if (up + down == 2 || up == 2 || down == 2) {
			this.moveUp(select, tile);
			this.moveDown(select, tile);
			int pos[] = { col, row };
			if (!this.isContain(pos)) {
				this.needToRemove.add(pos);
			}

			this.scoreUp(select, tile, this.calculateScore(3) / 3);
			this.scoreDown(select, tile, this.calculateScore(3) / 3);

			score[col][row] = this.calculateScore(3) / 3;
			this.Score += this.calculateScore(3);

			return 3;
		}
		return 0;

	}

	public int calculateScore(int length) {
		int score = 0;
		if (length == 3) {
			score = (this.levelTimes * 10 + 20) * 3 * this.timeOfElimination;
		}
		if (length == 4) {
			score = (this.levelTimes * 10 + 30) * 4 * this.timeOfElimination;
		}
		if (length == 5) {
			score = (this.levelTimes * 10 + 40) * 5 * this.timeOfElimination;
		}

		return score;
	}

	/**
	 * This method removes all the tiles in from argument and moves them to
	 * argument.
	 * 
	 * @param from
	 *            The source data structure of tiles.
	 * 
	 * @param to
	 *            The destination data structure of tiles.
	 */
	private void moveTiles(ArrayList<ZombieCrushSagaTile> from,
			ArrayList<ZombieCrushSagaTile> to) {
		// GO THROUGH ALL THE TILES, TOP TO BOTTOM
		if (from.size() > 0 && from.size() < 3) {

			ZombieCrushSagaTile tile = from.remove(from.size() - 1);

			// ONLY ADD IT IF IT'S NOT THERE ALREADY
			if (!to.contains(tile)) {
				to.add(tile);
			}
		}
	}


	/**
	 * This method updates all the necessary state information to process the
	 * move argument.
	 * 
	 * @param move
	 *            The move to make. Note that a move specifies the cell
	 *            locations for a match.
	 */
	public void processMove(ZombieCrushSagaMove move) {
		// REMOVE THE MOVE TILES FROM THE GRID

		try {
			miniGame.beginUsingData();
			ArrayList<ZombieCrushSagaTile> stack1 = tileGrid[move.col1][move.row1];
			ArrayList<ZombieCrushSagaTile> stack2 = tileGrid[move.col2][move.row2];
			ZombieCrushSagaTile tile1 = stack1.remove(stack1.size() - 1);
			ZombieCrushSagaTile tile2 = stack2.remove(stack2.size() - 1);
			int targetX1 = this.calculateTileXInGrid(move.col2, 0);
			int targetY1 = this.calculateTileYInGrid(move.row2, 0);
			int targetX2 = this.calculateTileXInGrid(move.col1, 0);
			int targetY2 = this.calculateTileYInGrid(move.row1, 0);
			// MAKE SURE BOTH ARE UNSELECTED
			tile1.setState(VISIBLE_STATE);
			tile2.setState(VISIBLE_STATE);

			// SEND THEM TO THE STACK
			tile1.setGridCell(move.col2, move.row2);
			tile2.setGridCell(move.col1, move.row1);

			tile1.setTarget(targetX1, targetY1);
			tile2.setTarget(targetX2, targetY2);
			// MAKE SURE THEY MOVE
			movingTiles.add(tile1);
			movingTiles.add(tile2);

			tile1.startMovingToTarget(speed);
			tile2.startMovingToTarget(speed);

			stack1.add(tile2);
			stack2.add(tile1);

			// AND MAKE SURE NEW TILES CAN BE SELECTED
	
		} finally {
			miniGame.endUsingData();
		}

	}

	public int getStar1(String levelName) {
		ZombieLevelRecord rec = levelRecords.get(levelName);
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
		if (levelName.equals(DATA_PREFIX + scoress.get(0)))
			rec.star1 = 300;
		if (levelName.equals(DATA_PREFIX + scoress.get(1)))
			rec.star1 = 1900;
		if (levelName.equals(DATA_PREFIX + scoress.get(2)))
			rec.star1 = 4000;
		if (levelName.equals(DATA_PREFIX + scoress.get(3)))
			rec.star1 = 4500;
		if (levelName.equals(DATA_PREFIX + scoress.get(4)))
			rec.star1 = 5000;
		if (levelName.equals(DATA_PREFIX + scoress.get(5)))
			rec.star1 = 9000;
		if (levelName.equals(DATA_PREFIX + scoress.get(6)))
			rec.star1 = 60000;
		if (levelName.equals(DATA_PREFIX + scoress.get(7)))
			rec.star1 = 20000;
		if (levelName.equals(DATA_PREFIX + scoress.get(8)))
			rec.star1 = 22000;
		if (levelName.equals(DATA_PREFIX + scoress.get(9)))
			rec.star1 = 40000;
		// rec.star1=332;

		return rec.star1;
	}

	public int getStar2(String levelName) {
		ZombieLevelRecord rec = levelRecords.get(levelName);
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
		if (levelName.equals(DATA_PREFIX + scoress.get(0)))
			rec.star2 = 400;
		if (levelName.equals(DATA_PREFIX + scoress.get(1)))
			rec.star2 = 2100;
		if (levelName.equals(DATA_PREFIX + scoress.get(2)))
			rec.star2 = 6000;
		if (levelName.equals(DATA_PREFIX + scoress.get(3)))
			rec.star2 = 6000;
		if (levelName.equals(DATA_PREFIX + scoress.get(4)))
			rec.star2 = 8000;

		if (levelName.equals(DATA_PREFIX + scoress.get(5)))
			rec.star2 = 11000;
		if (levelName.equals(DATA_PREFIX + scoress.get(6)))
			rec.star2 = 75000;
		if (levelName.equals(DATA_PREFIX + scoress.get(7)))
			rec.star2 = 30000;
		if (levelName.equals(DATA_PREFIX + scoress.get(8)))
			rec.star2 = 44000;
		if (levelName.equals(DATA_PREFIX + scoress.get(9)))
			rec.star2 = 70000;

		return rec.star2;
	}

	public int getStar3(String levelName) {
		ZombieLevelRecord rec = levelRecords.get(levelName);
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
		if (levelName.equals(DATA_PREFIX + scoress.get(0)))
			rec.star3 = 500;
		if (levelName.equals(DATA_PREFIX + scoress.get(1)))
			rec.star3 = 2400;
		if (levelName.equals(DATA_PREFIX + scoress.get(2)))
			rec.star3 = 8000;
		if (levelName.equals(DATA_PREFIX + scoress.get(3)))
			rec.star3 = 9000;
		if (levelName.equals(DATA_PREFIX + scoress.get(4)))
			rec.star3 = 12000;

		if (levelName.equals(DATA_PREFIX + scoress.get(5)))
			rec.star3 = 13000;
		if (levelName.equals(DATA_PREFIX + scoress.get(6)))
			rec.star3 = 85000;
		if (levelName.equals(DATA_PREFIX + scoress.get(7)))
			rec.star3 = 45000;
		if (levelName.equals(DATA_PREFIX + scoress.get(8)))
			rec.star3 = 66000;
		if (levelName.equals(DATA_PREFIX + scoress.get(9)))
			rec.star3 = 100000;

		return rec.star3;
	}

	public int getMove(String levelName) {
		ZombieLevelRecord rec = levelRecords.get(levelName);
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
		if (levelName.equals(DATA_PREFIX + scoress.get(0)))
			rec.moves = 6;
		if (levelName.equals(DATA_PREFIX + scoress.get(1)))
			rec.moves = 15;
		if (levelName.equals(DATA_PREFIX + scoress.get(2)))
			rec.moves = 18;
		if (levelName.equals(DATA_PREFIX + scoress.get(3)))
			rec.moves = 15;
		if (levelName.equals(DATA_PREFIX + scoress.get(4)))
			rec.moves = 20;

		if (levelName.equals(DATA_PREFIX + scoress.get(5)))
			rec.moves = 25;
		if (levelName.equals(DATA_PREFIX + scoress.get(6)))
			rec.moves = 50;
		if (levelName.equals(DATA_PREFIX + scoress.get(7)))
			rec.moves = 20;
		if (levelName.equals(DATA_PREFIX + scoress.get(8)))
			rec.moves = 25;
		if (levelName.equals(DATA_PREFIX + scoress.get(9)))
			rec.moves = 40;

		return rec.moves;
	}

	public int getNumOfTiles(String levelName) {
		ZombieLevelRecord rec = levelRecords.get(levelName);
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
		if (levelName.equals(DATA_PREFIX + scoress.get(0)))
			rec.numOfTiles = 100;
		if (levelName.equals(DATA_PREFIX + scoress.get(1)))
			rec.numOfTiles = 100;
		if (levelName.equals(DATA_PREFIX + scoress.get(2)))
			rec.numOfTiles = 100;
		if (levelName.equals(DATA_PREFIX + scoress.get(3)))
			rec.numOfTiles = 100;
		if (levelName.equals(DATA_PREFIX + scoress.get(4)))
			rec.numOfTiles = 100;

		if (levelName.equals(DATA_PREFIX + scoress.get(5)))
			rec.numOfTiles = 100;
		if (levelName.equals(DATA_PREFIX + scoress.get(6)))
			rec.numOfTiles = 100;
		if (levelName.equals(DATA_PREFIX + scoress.get(7)))
			rec.numOfTiles = 100;
		if (levelName.equals(DATA_PREFIX + scoress.get(8)))
			rec.numOfTiles = 100;
		if (levelName.equals(DATA_PREFIX + scoress.get(9)))
			rec.numOfTiles = 100;

		return rec.numOfTiles;
	}

	public int leftTraverse(String tileType, ZombieCrushSagaTile zcst) {

		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();

		int count = 0;
		int x1 = col - 1;
		while (x1 >= 0) {
			if (tileGrid[x1][row].size() != 2) {
				break;
			}
			if (tileGrid[x1][row].get(tileGrid[x1][row].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				count++;
			} else {
				break;
			}
			x1--;
		}
		return count;
	}

	public int rightTraverse(String tileType, ZombieCrushSagaTile zcst) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();

		int count = 0;
		int x1 = col + 1;
		while (x1 < tileGrid.length) {
			if (tileGrid[x1][row].size() != 2) {
				break;
			}
			if (tileGrid[x1][row].get(tileGrid[x1][row].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				count++;
			} else {
				break;
			}
			x1++;
		}
		return count;
	}

	public int upTraverse(String tileType, ZombieCrushSagaTile zcst) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int y1 = row - 1;
		while (y1 >= 0) {
			if (tileGrid[col][y1].size() != 2) {
				break;
			}
			if (tileGrid[col][y1].get(tileGrid[col][y1].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				count++;
			} else {
				break;
			}
			y1--;
		}
		return count;
	}

	public int downTraverse(String tileType, ZombieCrushSagaTile zcst) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int y1 = row + 1;
		while (y1 < tileGrid[0].length) {
			if (tileGrid[col][y1].size() != 2) {
				break;
			}
			if (tileGrid[col][y1].get(tileGrid[col][y1].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				count++;
			} else {
				break;
			}
			y1++;
		}
		return count;
	}

	public int scoreLeft(String tileType,ZombieCrushSagaTile zcst, int score1) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int x1 = col - 1;
		while (x1 >= 0) {
			if (tileGrid[x1][row].size() != 2) {
				break;
			}
			if (tileGrid[x1][row].get(tileGrid[x1][row].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				score[x1][row] = Math.max(score[x1][row], score1);
			} else {
				break;
			}
			x1--;
		}
		return count;
	}

	

	public int scoreRight(String tileType, ZombieCrushSagaTile zcst, int score1) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int x1 = col + 1;
		while (x1 < tileGrid.length) {
			if (tileGrid[x1][row].size() != 2) {
				break;
			}
			if (tileGrid[x1][row].get(tileGrid[x1][row].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				score[x1][row] = Math.max(score[x1][row], score1);
			} else {
				break;
			}
			x1++;
		}
		return count;
	}

	public int scoreUp(String tileType,ZombieCrushSagaTile zcst, int score1) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int y1 = row - 1;
		while (y1 >= 0) {
			if (tileGrid[col][y1].size() != 2) {
				break;
			}
			if (tileGrid[col][y1].get(tileGrid[col][y1].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				score[col][y1] = Math.max(score[col][y1], score1);
			} else {
				break;
			}
			y1--;
		}
		return count;
	}

	public int scoreDown(String tileType,ZombieCrushSagaTile zcst, int score1) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int y1 = row + 1;
		while (y1 < tileGrid[0].length) {
			if (tileGrid[col][y1].size() != 2) {
				break;
			}
			if (tileGrid[col][y1].get(tileGrid[col][y1].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				score[col][y1] = Math.max(score[col][y1], score1);
			} else {
				break;
			}
			y1++;
		}
		return count;
	}

	public void moveLeft(String tileType, ZombieCrushSagaTile zcst) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int x1 = col - 1;
		while (x1 >= 0) {
			if (tileGrid[x1][row].size() != 2) {
				break;
			}
			if (tileGrid[x1][row].get(tileGrid[x1][row].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				int pos[] = { x1, row };
				if (!isContain(pos)) {
					needToRemove.add(pos);
				}

			} else {
				break;
			}
			x1--;
		}

	}

	public void moveRight(String tileType, ZombieCrushSagaTile zcst) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int x1 = col + 1;
		while (x1 < tileGrid.length) {
			if (tileGrid[x1][row].size() != 2) {
				break;
			}
			if (tileGrid[x1][row].get(tileGrid[x1][row].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				int pos[] = { x1, row };
				if (!isContain(pos)) {
					needToRemove.add(pos);
				}
			} else {
				break;
			}
			x1++;
		}

	}

	public void moveUp(String tileType, ZombieCrushSagaTile zcst) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int y1 = row - 1;
		while (y1 >= 0) {

			if (tileGrid[col][y1].size() != 2) {
				break;
			}
			if (tileGrid[col][y1].get(tileGrid[col][y1].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				int pos[] = { col, y1 };
				if (!isContain(pos)) {
					needToRemove.add(pos);
				}
			} else {
				break;
			}

			y1--;
		}

	}

	public void moveDown(String tileType,ZombieCrushSagaTile zcst) {
		int col = zcst.getGridColumn();
		int row = zcst.getGridRow();
		int count = 0;
		int y1 = row + 1;
		while (y1 < tileGrid[0].length) {
			if (tileGrid[col][y1].size() != 2) {
				break;
			}
			if (tileGrid[col][y1].get(tileGrid[col][y1].size() - 1)
					.getSpriteType().getSpriteTypeID().equals(tileType)) {
				int pos[] = { col, y1 };
				if (!isContain(pos)) {
					needToRemove.add(pos);
				}
			} else {
				break;
			}
			y1++;
		}

	}

	/**
	 * This method attempts to select the selectTile argument. Note that this
	 * may be the first or second selected tile. If a tile is already selected,
	 * it will attempt to process a match/move.
	 * 
	 * @param selectTile
	 *            The tile to select.
	 */
	public void addMoves() {
		this.currentMoves++;
	}

	public void setScoreTimes() {
		this.timeOfElimination = 1;
	}

	public void addScoreTimes() {
		this.timeOfElimination++;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void resetEmptyCol() {
		this.ec = new ArrayList<Integer>();
	}

	public ArrayList<int[]> getCurrentNeedToMove() {
		return this.needToRemove;
	}

	public void selectTile(ZombieCrushSagaTile selectTile) {
		this.setScoreTimes();

		// IF IT'S ALREADY THE SELECTED TILE, DESELECT IT

		if (selectTile == selectedTile) {
			selectedTile = null;
			selectTile.setState(VISIBLE_STATE);
			return;
		}
		if (selectedTile == null) {
			selectedTile = selectTile;
			selectedTile.setState(SELECTED_STATE);
			return;
		} else {

			if ((!selectedTile.match(selectTile) && isLegalMove(selectTile))) {
				this.selectTile = selectTile;
				this.beginToUpdateBoard = true;
				speed = 3;
				swap(selectedTile, selectTile);
				this.finishExchange = false;
				if ((this.checkFinal(selectTile)
						|| this.checkFinal(selectedTile)
						|| selectedTile.getTileType().equals(TILE_COLOR_TYPE) || selectTile
						.getTileType().equals(TILE_COLOR_TYPE))) {
					this.freeMove = true;
					this.currentMoves++;
					this.timeOfElimination = 1;
					this.needToRemove = new ArrayList<int[]>();
					this.ec = new ArrayList<Integer>();
					if (selectedTile.getTileType().equals(TILE_COLOR_TYPE)) {
						this.moveAllSameType(selectTile);
						int[] p = { selectedTile.getGridColumn(),
								selectedTile.getGridRow() };
						this.needToRemove.add(p);

					} else {
						if (selectTile.getTileType().equals(TILE_COLOR_TYPE)) {
							this.moveAllSameType(selectedTile);
							int[] p = { selectTile.getGridColumn(),
									selectTile.getGridRow() };
							this.needToRemove.add(p);

						} else {
							if (checkFinal(selectedTile)) {

								this.moveTiles(selectedTile);
							}
							if (checkFinal(selectTile))

								this.moveTiles(selectTile);
						}
					}

				} else {
					this.freeMove = false;
					this.finishExchange = false;
					selectedTile.beginExchange = true;
				}

				speed = 1;

			} else {
				updateMoving();

			}

		}



	}


	public void updateMoving() {
		if (selectedTile != null) {

			selectedTile.setState(VISIBLE_STATE);
			selectedTile = null;
		}
		if (selectTile != null) {
			selectTile.setState(VISIBLE_STATE);
			selectTile = null;
		}

	}

	public void clearJelly(int col, int row) {
		this.addBackTiles();
		if (!this.startPlaying)
			return;
		ArrayList<ZombieCrushSagaTile> stack = tileGrid[col][row];
		if (stack.size() == 1)
			if (stack.get(0).getTileType().equals(BACK_GROUND_TYPE)) {
				this.replcaingTiles(backTiles.remove(0), col, row);
			}
		if (stack.size() == 2) {
			ArrayList<ZombieCrushSagaTile> stack2 = tileGrid[col][row];
			ZombieCrushSagaTile tile1 = backTiles.remove(0);
			this.removeAnimation(col, row, 0);
			double i = speed;
			speed = 200;
			int z = 0;
			float targetX = this.calculateTileXInGrid(col, 0);
			float targetY = this.calculateTileYInGrid(row, 0);
			tile1.setX(targetX);
			tile1.setY(targetY);

			tile1.setState(VISIBLE_STATE);
			tile1.setGridCell(col, row);
			tile1.setTarget(targetX, targetY);
			movingTiles.add(tile1);
			tile1.startMovingToTarget(speed);
			tileGrid[col][row].add(0, tile1);
			speed = i;
		}
	}

	public void clear() {
		boolean getBom = false;

		for (int i = 0; i < this.needToRemove.size(); i++) {
			ArrayList<ZombieCrushSagaTile> stack = tileGrid[needToRemove.get(i)[0]][needToRemove
					.get(i)[1]];

			if (stack.size() == 2) {
				ZombieCrushSagaTile s = stack.get(stack.size() - 1);
				if (s.getTileType().equals(TILE_W_TYPE) && !this.isBom) {
					this.clearFirstThreethree(s.getGridColumn(), s.getGridRow());
					this.isBom = false;
					getBom = true;
				}
				clearJelly(s.getGridColumn(), s.getGridRow());
				if (selectTile != null && selectedTile != null) {
					if (s.getTileType().equals(TILE_S_TYPE)) {
						if (selectTile.getGridColumn() == selectedTile
								.getGridColumn())
							this.clearCol(s.getGridColumn());
						if (selectTile.getGridRow() == selectedTile
								.getGridRow())
							this.clearRow(s.getGridRow());
					}

				}

				if (!getBom) {
					if (stack.size() == 2)
						this.removeAnimation(s.getGridColumn(), s.getGridRow(),
								1);
				}

				addTiles();
				getBom = false;
			}
		}
		while (this.needToRemove.size() != 0)
			this.needToRemove.remove(0);

	}

	public void clearCol(int col) {
		for (int i = 0; i < tileGrid[col].length; i++) {
			;
			if (tileGrid[col][i].size() != 0)
				score[col][i] = (this.levelTimes * 10 + 20) * 3;
		}
		for (int i = 0; i < tileGrid[col].length; i++) {
			ArrayList<ZombieCrushSagaTile> stack = tileGrid[col][i];

			if (stack.size() == 2) {


				this.removeAnimation(col, i, 1);

				this.Score += (this.levelTimes * 10 + 20) * 3;

			}
		}

	}

	public void clearRow(int row) {
		for (int i = 0; i < tileGrid.length; i++)
			if (tileGrid[i][row].size() != 0)
				score[i][row] = (this.levelTimes * 10 + 20) * 3;
		for (int i = 0; i < tileGrid.length; i++) {
			ArrayList<ZombieCrushSagaTile> stack = tileGrid[i][row];
			if (stack.size() != 0)
				score[i][row] = (this.levelTimes * 10 + 20) * 3;
			if (stack.size() == 2) {

				this.removeAnimation(i, row, 1);

				this.Score += (this.levelTimes * 10 + 20) * 3;

			}
		}

	}

	public void delectNeedToMove(int col, int row) {
		for (int i = 0; i < this.needToRemove.size(); i++) {
			if (needToRemove.get(i)[0] == col && needToRemove.get(i)[0] == row) {
				needToRemove.remove(i);
				return;
			}

		}
	}

	public void MovingAnimation() {
		if (inProgress() && stackTiles.size() > 1) {
			// TAKE THE TOP 2 TILES
			ZombieCrushSagaTile topTile = stackTiles
					.remove(stackTiles.size() - 1);
			ZombieCrushSagaTile nextToTopTile = stackTiles.remove(stackTiles
					.size() - 1);

			// SET THEIR DESTINATIONS
			float boundaryLeft = miniGame.getBoundaryLeft();
			float boundaryTop = miniGame.getBoundaryTop();

			// FIRST TILE 1
			int col = topTile.getGridColumn();
			int row = topTile.getGridRow();

			int col1 = nextToTopTile.getGridColumn();
			int row1 = nextToTopTile.getGridRow();

			int z = tileGrid[col][row].size();
			float targetX = this.calculateTileXInGrid(col1, z);
			float targetY = this.calculateTileYInGrid(row1, z);

			int z1 = tileGrid[col][row].size();
			int targetX1 = this.calculateTileXInGrid(col, z);
			int targetY1 = this.calculateTileYInGrid(row, z);
			topTile.setGridCell(col1, row1);
			topTile.setTarget(targetX, targetY);

			topTile.setState(VISIBLE_STATE);
			movingTiles.add(topTile);
			topTile.startMovingToTarget(1);

			tileGrid[col1][row1].add(topTile);

			// AND THEN TILE 2

			nextToTopTile.setGridCell(col, row);
			nextToTopTile.setState(VISIBLE_STATE);
			nextToTopTile.setTarget(targetX1, targetY1);
			movingTiles.add(nextToTopTile);
			nextToTopTile.startMovingToTarget(speed);

			tileGrid[col][row].add(nextToTopTile);

		}
	}

	public void clearFirstThreethree(int col, int row) {

		for (int i = col - 1; i >= 0 && i <= col + 1 && i < tileGrid.length; i++)
			for (int j = row - 1; j >= 0 && j <= row + 1
					&& j < tileGrid[0].length; j++) {
				if (tileGrid[i][j].size() == 2 && (col != i || j != row)) {

					score[i][j] = (this.levelTimes * 10 + 20) * 3;
					this.Score += (this.levelTimes * 10 + 20) * 3;

					this.removeAnimation(i, j, 1);

				}

			}
		delectNeedToMove(col, row);

		this.isBom = true;
		this.processBag = true;
		Bom[0] = col;
		Bom[1] = row + 1;

	}

	public void clearThreethree() {

		int col = Bom[0];
		int row = Bom[1];
		if (row >= tileGrid[0].length)
			row = tileGrid[0].length - 1;
		if (tileGrid[col][row].size() == 0)
			row++;
		if (row >= tileGrid[0].length)
			row = tileGrid[0].length - 1;
		if (tileGrid[col][row].size() == 0)
			row++;
		if (row >= tileGrid[0].length)
			row = tileGrid[0].length - 1;

		for (int i = col - 1; i >= 0 && i <= col + 1 && i < tileGrid.length; i++)
			for (int j = row - 1; j >= 0 && j <= row + 1
					&& j < tileGrid[0].length; j++) {
				if (tileGrid[i][j].size() == 2) {

					score[i][j] = (this.levelTimes * 10 + 20) * 3;
					this.Score += (this.levelTimes * 10 + 20) * 3;

					this.removeAnimation(i, j, 1);

				}

			}

		for (int i = 0; i < tileGrid.length; i++)
			this.ec.add(i);

	}

	/**
	 * left right swap
	 */
	public void swap(ZombieCrushSagaTile selectTile,
			ZombieCrushSagaTile selectedTile) {
		// before change set seted
		if (selectTile == null || selectedTile == null)
			return;
		if (!this.finishExchange)
			if (selectTile.getState().equals(VISIBLE_STATE)
					&& selectedTile.getState().equals(VISIBLE_STATE))
				return;
		ZombieCrushSagaMove move = new ZombieCrushSagaMove();
		move.col1 = selectTile.getGridColumn();
		move.col2 = selectedTile.getGridColumn();
		move.row1 = selectTile.getGridRow();
		move.row2 = selectedTile.getGridRow();
		processMove(move);

		// MovingAnimotion();
	}

	public boolean moveDownOneStep(ZombieCrushSagaTile tile) {

		int row2 = tile.getGridRow() + 1;
		if (row2 == tileGrid[0].length)
			return false; // last row

		if (tileGrid[tile.getGridColumn()][row2].size() == 2)
			return false;

		while (row2 < tileGrid[0].length) {
			if (levelGrid[tile.getGridColumn()][row2] == 3)
				row2++;
			else if (tileGrid[tile.getGridColumn()][row2].size() == 2)
				return false;
			else
				break;
		}
		if (row2 > tileGrid[0].length - 1) {
			return false;
		}
		if (levelGrid[tile.getGridColumn()][row2] == 3)
			return false;

		if (tileGrid[tile.getGridColumn()][row2].size() == 2)
			return false;

		if ((tileGrid[tile.getGridColumn()][row2].size() == 1)) {

			// REMOVE THE MOVE TILES FROM THE GRID
			ArrayList<ZombieCrushSagaTile> stack1 = tileGrid[tile
					.getGridColumn()][tile.getGridRow()];
			ArrayList<ZombieCrushSagaTile> stack2 = tileGrid[tile
					.getGridColumn()][row2];
			ZombieCrushSagaTile tile1 = stack1.remove(stack1.size() - 1);

			// if(tileGrid[i][j].get(tileGrid[i][j].size()-1).getTileType().equals(TILE_W_TYPE))
			// tileGrid[i][j].remove(1);
			// SEND THEM TO THE STACK
			tile1.setTarget(TILE_STACK_X + TILE_STACK_OFFSET_X, TILE_STACK_Y
					+ TILE_STACK_OFFSET_Y);
			tile1.startMovingToTarget(speed);
			stackTiles.add(tile1);
			// MAKE SURE THEY MOVE
			movingTiles.add(tile1);
			ZombieCrushSagaTile topTile = stackTiles
					.remove(stackTiles.size() - 1);

			// FIRST TILE 1
			int col = topTile.getGridColumn();
			int row = topTile.getGridRow();
			int col1 = topTile.getGridColumn();
			int row1 = row2;

			int z = tileGrid[col][row].size();
			float targetX = this.calculateTileXInGrid(col1, z);
			float targetY = this.calculateTileYInGrid(row1, z);

			topTile.setGridCell(col1, row1);
			topTile.setTarget(targetX, targetY);
			topTile.setState(VISIBLE_STATE);
			movingTiles.add(topTile);
			topTile.startMovingToTarget(speed);
			tileGrid[col1][row1].add(topTile);
			return true;
		}
		return false;
	}

	// / fill empty row

	public boolean filetycln(int col) {

		boolean fill = true;
		boolean get = false;
		this.speed = 1.5;
		while (fill) {
			// /////////////////////////
			// ////////////////////////
			// /////////////////////////
			fill = false;
			for (int i = tileGrid[0].length - 1; i >= 0; i--) {

				if (tileGrid[col][i].size() == 2 && i != tileGrid[0].length - 1
						&& levelGrid[col][i] != 3) {
					ZombieCrushSagaTile currentTile = tileGrid[col][i]
							.get(tileGrid[col][i].size() - 1);
					if (tileGrid[col][i].size() == 2)
						if (moveDownOneStep(currentTile)) {

							fill = true;
							get = true;
						}
				}

			}

			if (supply(col)) {
				fill = true;
				get = true;
			}

		}

		return get;

	}

	public boolean supply(int col) {

		if (tileGrid[col][0].size() == 1 && levelGrid[col][0] != 3) {

			ArrayList<ZombieCrushSagaTile> stack2 = tileGrid[col][0];
			ZombieCrushSagaTile tile1 = supplementTiles.remove(0);
			int z = stack2.size();
			float targetX = this.calculateTileXInGrid(col, z);
			float targetY = this.calculateTileYInGrid(0, z);
			tile1.setX(targetX);
			tile1.setY(targetY - 55);

			tile1.setState(VISIBLE_STATE);
			tile1.setGridCell(col, 0);
			tile1.setTarget(targetX, targetY);
			movingTiles.add(tile1);
			tile1.startMovingToTarget(speed);

			tileGrid[col][0].add(tile1);

			return true;
		}
		return false;
	}

	public void replcaingTiles(ZombieCrushSagaTile tile1, int col, int row) {
		ArrayList<ZombieCrushSagaTile> stack2 = tileGrid[col][row];
		stack2.remove(tileGrid[col][row].size() - 1);

		double i = speed;
		speed = 200;
		int z = stack2.size();
		float targetX = this.calculateTileXInGrid(col, z);
		float targetY = this.calculateTileYInGrid(row, z);
		tile1.setX(targetX);
		tile1.setY(targetY);

		tile1.setState(VISIBLE_STATE);
		tile1.setGridCell(col, row);
		tile1.setTarget(targetX, targetY);
		movingTiles.add(tile1);
		tile1.startMovingToTarget(speed);
		tileGrid[col][row].add(tile1);
		speed = i;
	}

	public boolean isContain(int[] pos) {
		for (int i = 0; i < this.needToRemove.size(); i++) {
			if (needToRemove.get(i)[0] == pos[0]
					&& needToRemove.get(i)[1] == pos[1]) {
				return true;
			}
		}
		return false;

	}

	public void removeAnimation(int col, int row, int h) {
		

		ArrayList<ZombieCrushSagaTile> stack2 = tileGrid[col][row];

		ZombieCrushSagaTile tile1 = stack2.remove(h);
		this.clearJelly(col, row);
		double i = speed;
		speed = 10;
		int z = stack2.size();
		float targetX;
		float targetY;
		if (tile1.getTileType().equals(backTiles.get(0).getTileType()))
			return;
		if (tile1.getTileType().equals(jellyTiles.get(0).getTileType())) {
			targetX = 1220;
			targetY = 900;
		} else {
			if (this.calculateTileXInGrid(col, 1) < 620)
				targetX = 1100;
			else
				targetX = 950;

			targetY = 980;
		}
		tile1.setState(VISIBLE_STATE);
		tile1.setTarget(targetX, targetY);
		movingTiles.add(tile1);
		tile1.startMovingToTarget(speed);
		speed = i;

	}

	// OVERRIDDEN METHODS
	// - checkMousePressOnSprites
	// - endGameAsWin
	// - endGameAsLoss
	// - reset
	// - updateAll
	// - updateDebugText

	/**
	 * This method provides a custom game response for handling mouse clicks on
	 * the game screen. We'll use this to close game dialogs as well as to
	 * listen for mouse clicks on grid cells.
	 * 
	 * @param game
	 *            The Mahjong game.
	 * 
	 * @param x
	 *            The x-axis pixel location of the mouse click.
	 * 
	 * @param y
	 *            The y-axis pixel location of the mouse click.
	 */
	@Override
	public void checkMousePressOnSprites(MiniGame game, int x, int y) {
		this.clearthreethree = false;
		if (this.isProcessMoving())// if continue moving can not select any tile
			return;

		int col = calculateGridCellColumn(x);
		int row = calculateGridCellRow(y);

		// CHECK THE TOP OF THE STACK AT col, row
		if (tileGrid.length <= col || tileGrid[0].length <= row || col < 0
				|| row < 0)
			return;
		ArrayList<ZombieCrushSagaTile> tileStack = tileGrid[col][row];
		if (tileStack.size() == 2) {
			// GET AND TRY TO SELECT THE TOP TILE IN THAT CELL, IF THERE IS ONE
			ZombieCrushSagaTile testTile = tileStack.get(tileStack.size() - 1);

			if (testTile.containsPoint(x, y)) {
				if (!stop) {
					if (NUMBER_OF_MOVES[Integer.parseInt(this.getCurrentLevel()
							.substring(5)) - 1] == this.currentMoves) {
						processWin();
					} else {

						if (miniGame.getCanvas().getCursor() != Cursor
								.getDefaultCursor()) {


							this.clearthreethree = true;
							this.removeAnimation(col, row, 1);
							this.ec.add(col);
							if (tileGrid[col][row].get(0).getTileType()
									.equals(BACK_GROUND_TYPE))
								this.replcaingTiles(backTiles.remove(0), col,
										row);
							this.beginToUpdateBoard = true;
							miniGame.getCanvas().setCursor(
									Cursor.getDefaultCursor());

						} else
							selectTile(testTile);
					}
				}

			}
		}
	}

	/**
	 * Called when the game is won, it will record the ending game time, update
	 * the player record, display the win dialog, and play the win animation.
	 */
	@Override
	public void endGameAsWin() {
		// UPDATE THE GAME STATE USING THE INHERITED FUNCTIONALITY
		int i = Integer.parseInt(this.currentLevel.substring(5)) - 1;
		if (i < 9) {
			LEVEL_LOCK[i + 1] = true; // open next level

			((ZombieCrushSagaMiniGame) miniGame).getPlayerRecord()
					.openNewLevelRecord(LEVEL_NAME[i + 1]);

			// ((ZombieCrushSagaMiniGame)
			// miniGame).getPlayerRecord().setVisible("Level"+(i+2));
		}
		// long gameTime = endTime.getTimeInMillis() -
		// startTime.getTimeInMillis();
		/*
		 * int astar=0;
		 * if(Score>=FIRST_STAR_SCORE[i-1]&&Score<Level_2_Star[i-1]){ astar=1;}
		 * if(Score>=Level_2_Star[i-1]&&Score<Level_3_Star[i-1]){ astar=2;}
		 * if(Score>=Level_3_Star[i-1]){ astar=3;}
		 */

		((ZombieCrushSagaMiniGame) miniGame).getPlayerRecord().addWin(
				currentLevel, Score);
		((ZombieCrushSagaMiniGame) miniGame).savePlayerRecord();

		super.endGameAsWin();

		this.sleep(600);
		((ZombieCrushSagaMiniGame) miniGame).switchToYouWinScreen();
		// System.exit(0);
		// miniGame.get
		// miniGame.getGUIDialogs().get(WIN_DIALOG_TYPE).setState(VISIBLE_STATE);

	}

	/**
	 * 
	 * Called when the game is loss, it will record the ending game time, update
	 * the player record, display the loss dialog.
	 * 
	 */
	@Override
	public void endGameAsLoss() {

		// long gameTime = endTime.getTimeInMillis() -
		// startTime.getTimeInMillis();
		((ZombieCrushSagaMiniGame) miniGame).getPlayerRecord().addLoss(
				currentLevel);

		((ZombieCrushSagaMiniGame) miniGame).savePlayerRecord();

		super.endGameAsLoss();
		// miniGame.getGUIDialogs().get(LOSS_DIALOG_TYPE).setState(VISIBLE_STATE);
		this.sleep(300);
		((ZombieCrushSagaMiniGame) miniGame).switchToYouFailScreen();

	}

	/**
	 * Called when a game is started, the game grid is reset.
	 * 
	 * @param game
	 */
	@Override
	public void reset(MiniGame game) {
		this.addBackTiles();

		this.clearScore();
		// this.smarsh.setX(this.calculateGridCellColumn(15));
		// this.smarsh.setY(this.calculateGridCellRow(15));
		// this.smarsh.setState(VISIBLE_STATE);

		// ANpD START ALL UPDATES
		if (this.inProgress()) {
			this.reloadCurrentGame();
			return;
		}

		this.startPlaying = false;
		this.speed = 20;

		moveAllTilesToStack();
		for (ZombieCrushSagaTile tile : stackTiles) {
			tile.setX(-200);
			tile.setY(-200);
			tile.setState(VISIBLE_STATE);

		}
		for (ZombieCrushSagaTile tile : jellyTiles) {
			tile.setX(-50);
			tile.setY(-50);
			tile.setState(VISIBLE_STATE);

		}
		for (ZombieCrushSagaTile tile : backTiles) {
			tile.setX(-100);
			tile.setY(-100);
			tile.setState(VISIBLE_STATE);

		}

		// RANDOMLY ORDER THEM
		// if(!this.inProgress())
		Collections.shuffle(stackTiles);

		// START THE CLOCK

		startTime = new GregorianCalendar();

		// NOW LET'S REMOVE THEM FROM THE STACK
		// AND PUT THE TILES IN THE GRID
		for (int i = 0; i < gridColumns; i++) {
			for (int j = 0; j < gridRows; j++) {
				if (levelGrid[i][j] == 2) {
					ZombieCrushSagaTile tile1 = jellyTiles.remove(jellyTiles
							.size() - 1); // set jelly
					tileGrid[i][j].add(tile1);
					tile1.setGridCell(i, j);

					// WE'LL ANIMATE IT GOING TO THE GRID, SO FIGURE
					// OUT WHERE IT'S GOING AND GET IT MOVING
					float x1 = calculateTileXInGrid(i, 0);
					float y1 = calculateTileYInGrid(j, 0);
					tile1.setTarget(x1, y1);
					tile1.startMovingToTarget(speed);
					movingTiles.add(tile1);
					// TAKE THE TILE OUT OF THE STACK
					ZombieCrushSagaTile tile = stackTiles.remove(stackTiles
							.size() - 1);

					// PUT IT IN THE GRID
					tileGrid[i][j].add(tile);
					tile.setGridCell(i, j);

					// WE'LL ANIMATE IT GOING TO THE GRID, SO FIGURE
					// OUT WHERE IT'S GOING AND GET IT MOVING
					float x = calculateTileXInGrid(i, 1);
					float y = calculateTileYInGrid(j, 1);
					tile.setTarget(x, y);
					tile.startMovingToTarget(speed);
					movingTiles.add(tile);

				} else if (levelGrid[i][j] == 1) { // set backgroud
					ZombieCrushSagaTile tile1 = backTiles.remove(backTiles
							.size() - 1); // set jelly
					tileGrid[i][j].add(tile1);
					tile1.setGridCell(i, j);

					// WE'LL ANIMATE IT GOING TO THE GRID, SO FIGURE
					// OUT WHERE IT'S GOING AND GET IT MOVING
					float x1 = calculateTileXInGrid(i, 0);
					float y1 = calculateTileYInGrid(j, 0);
					tile1.setTarget(x1, y1);
					tile1.startMovingToTarget(speed);
					movingTiles.add(tile1);
					// TAKE THE TILE OUT OF THE STACK
					ZombieCrushSagaTile tile = stackTiles.remove(stackTiles
							.size() - 1);

					// PUT IT IN THE GRID
					tileGrid[i][j].add(tile);
					tile.setGridCell(i, j);

					// WE'LL ANIMATE IT GOING TO THE GRID, SO FIGURE
					// OUT WHERE IT'S GOING AND GET IT MOVING
					float x = calculateTileXInGrid(i, 1);
					float y = calculateTileYInGrid(j, 1);
					tile.setTarget(x, y);
					tile.startMovingToTarget(speed);
					movingTiles.add(tile);
				}

			}
		}
		for (int i = 0; i < tileGrid.length; i++) {
			for (int j = tileGrid[0].length - 1; j >= 0; j--) {
				if (tileGrid[i][j].size() == 1 || tileGrid[i][j].size() == 2) {
					ZombieCrushSagaTile tile = tileGrid[i][j]
							.get(tileGrid[i][j].size() - 1);
					if (tile.getTileType().equals(TILE_COLOR_TYPE)
							|| tile.getTileType().equals(TILE_W_TYPE)
							|| tile.getTileType().equals(TILE_S_TYPE)
							|| tile.getTileType().equals(BACK_GROUND_TYPE)
							|| tile.getTileType().equals(JELLY_TYPE))
						this.replcaingTiles(this.supplementTiles.remove(0), i,
								j);

				}

			}
		}

		for (int i = 0; i < tileGrid.length; i++) {
			for (int j = tileGrid[0].length - 1; j >= 0;) {
				if (tileGrid[i][j].size() == 1 || tileGrid[i][j].size() == 2) {
					ZombieCrushSagaTile tile = tileGrid[i][j]
							.get(tileGrid[i][j].size() - 1);
					if (this.checkFinal(tile)) {
						this.replcaingTiles(this.supplementTiles.remove(0), i,
								j);

					} else
						j--;

				} else
					j--;

			}
		}

		beginGame();
		this.speed = 3;
		this.startPlaying = true;
		// CLEAR ANY WIN OR LOSS DISPLAY
		miniGame.getGUIDialogs().get(WIN_DIALOG_TYPE).setState(INVISIBLE_STATE);
		miniGame.getGUIDialogs().get(LOSE_DIALOG_TYPE)
				.setState(INVISIBLE_STATE);
		// miniGame.getGUIDialogs().get(STATS_DIALOG_TYPE).setState(INVISIBLE_STATE);

		// miniGame.getGUIDialogs().get(STATS_DIALOG_TYPE).setState(INVISIBLE_STATE);
	}

	public void resetGame() {
		this.addBackTiles();
		this.clearScore();

		this.startPlaying = false;
		this.speed = 35;

		moveAllTilesToStack();
		for (ZombieCrushSagaTile tile : stackTiles) {
			tile.setX(-100);
			tile.setY(-100);
			tile.setState(VISIBLE_STATE);

		}

		// RANDOMLY ORDER THEM
		// if(!this.inProgress())
		Collections.shuffle(stackTiles);

		// START THE CLOCK

		startTime = new GregorianCalendar();

		// NOW LET'S REMOVE THEM FROM THE STACK
		// AND PUT THE TILES IN THE GRID
		for (int i = 0; i < gridColumns; i++) {
			for (int j = 0; j < gridRows; j++) {

				if (levelGrid[i][j] == 2 || levelGrid[i][j] == 1) {

					ZombieCrushSagaTile tile = stackTiles.remove(stackTiles
							.size() - 1);

					// PUT IT IN THE GRID
					tileGrid[i][j].add(tile);
					tile.setGridCell(i, j);
					/*
					 * if(levelGrid[i][j]==2){ // TAKE THE TILE OUT OF THE STACK
					 * // ZombieCrushSagaTile tile = jellyTiles.remove(0);
					 * 
					 * // PUT IT IN THE GRID tileGrid[i][j].add(tile);
					 * tile.setGridCell(i, j);
					 * 
					 * // WE'LL ANIMATE IT GOING TO THE GRID, SO FIGURE // OUT
					 * WHERE IT'S GOING AND GET IT MOVING float x =
					 * calculateTileXInGrid(i, 0); float y =
					 * calculateTileYInGrid(j, 0); tile.setTarget(x, y);
					 * tile.startMovingToTarget(speed); movingTiles.add(tile); }
					 */
					// WE'LL ANIMATE IT GOING TO THE GRID, SO FIGURE
					// OUT WHERE IT'S GOING AND GET IT MOVING
					float x = calculateTileXInGrid(i, 1);
					float y = calculateTileYInGrid(j, 1);
					tile.setTarget(x, y);
					tile.startMovingToTarget(speed);
					movingTiles.add(tile);

				}

			}
		}

		for (int i = 0; i < tileGrid.length; i++) {
			for (int j = tileGrid[0].length - 1; j >= 0;) {
				if (tileGrid[i][j].size() == 1 || tileGrid[i][j].size() == 2) {
					ZombieCrushSagaTile tile = tileGrid[i][j]
							.get(tileGrid[i][j].size() - 1);
					if (this.checkFinal(tile)
							&& tile.getTileType().equals(TILE_C_TYPE)) {
						this.replcaingTiles(this.supplementTiles.remove(0), i,
								j);

					} else
						j--;

				} else
					j--;

			}
		}

		beginGame();
		this.speed = 3;
		this.startPlaying = true;
		// CLEAR ANY WIN OR LOSS DISPLAY
		miniGame.getGUIDialogs().get(WIN_DIALOG_TYPE).setState(INVISIBLE_STATE);
		miniGame.getGUIDialogs().get(LOSE_DIALOG_TYPE)
				.setState(INVISIBLE_STATE);

	}

	/**
	 * Called each frame, this method updates all the game objects.
	 * 
	 * @param game
	 *            The Mahjong game to be updated.
	 */
	@Override
	public void updateAll(MiniGame game) {
		// MAKE SURE THIS THREAD HAS EXCLUSIVE ACCESS TO THE DATA
		try {
			game.beginUsingData();

			// WE ONLY NEED TO UPDATE AND MOVE THE MOVING TILES
			for (int i = 0; i < movingTiles.size(); i++) {
				// GET THE NEXT TILE
				ZombieCrushSagaTile tile = movingTiles.get(i);

				// THIS WILL UPDATE IT'S POSITION USING ITS VELOCITY
				tile.update(game);

				// IF IT'S REACHED ITS DESTINATION, REMOVE IT
				// FROM THE LIST OF MOVING TILES
				if (!tile.isMovingToTarget()) {
					movingTiles.remove(tile);
				}
			}

			// IF THE GAME IS STILL ON, THE TIMER SHOULD CONTINUE
			if (inProgress()) {
				// KEEP THE GAME TIMER GOING IF THE GAME STILL IS
				endTime = new GregorianCalendar();
			}
		} finally {
			// MAKE SURE WE RELEASE THE LOCK WHETHER THERE IS
			// AN EXCEPTION THROWN OR NOT
			game.endUsingData();
		}
	}

	/**
	 * This method is for updating any debug text to present to the screen. In a
	 * graphical application like this it's sometimes useful to display data in
	 * the GUI.
	 * 
	 * @param game
	 *            The Mahjong game about which to display info.
	 */
	@Override
	public void updateDebugText(MiniGame game) {
	}

	public void resetTestLevel(int[][] test) {

		// ANpD START ALL UPDATES

		// this.startPlaying = false;
		this.speed = 80;
		addTestTiles();
		for (int i = 0; i < gridColumns; i++)
			for (int j = 0; j < gridRows; j++)
				if (tileGrid[i][j].size() == 2)

					tileGrid[i][j].remove(1);
		// RANDOMLY ORDER THEM
		// if(!this.inProgress())
		// Collections.shuffle(stackTiles);

		// START THE CLOCK

		startTime = new GregorianCalendar();

		// NOW LET'S REMOVE THEM FROM THE STACK
		// AND PUT THE TILES IN THE GRID
		for (int i = 0; i < gridColumns; i++) {
			for (int j = 0; j < gridRows; j++) {
				if (testTiles[test[i][j]].size() >= 1 && levelGrid[i][j] != 3) {
					// TAKE THE TILE OUT OF THE STACK
					ZombieCrushSagaTile tile = testTiles[test[i][j]]
							.remove(testTiles[test[i][j]].size() - 1);
					tile.setState(VISIBLE_STATE);
					// PUT IT IN THE GRID
					tileGrid[i][j].add(tile);
					tile.setGridCell(i, j);

					// WE'LL ANIMATE IT GOING TO THE GRID, SO FIGURE
					// OUT WHERE IT'S GOING AND GET IT MOVING
					float x = calculateTileXInGrid(i, 1);
					float y = calculateTileYInGrid(j, 1);
					tile.setTarget(x, y);
					tile.startMovingToTarget(speed);
					movingTiles.add(tile);
				}

			}
		}

		// AND START ALL UPDATES
		this.speed = 3;
		this.startPlaying = true;

	}
	public int[][] getScore() {
		return score;
	}
	public void sleep(int i) {
		stop = true;
		long time = System.currentTimeMillis() + i;
		while (System.currentTimeMillis() < time) {

		}

		stop = false;
	}


	public void clearScore() {
		for (int i = 0; i < score.length; i++)
			for (int j = 0; j < score[0].length; j++)
				score[i][j] = 0;

	}
	
}
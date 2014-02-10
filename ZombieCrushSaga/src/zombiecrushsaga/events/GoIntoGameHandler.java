package zombiecrushsaga.events;

import static zombiecrushsaga.ZombieCrushSagaConstants.STATS_SCREEN_STATE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import zombiecrushsaga.file.ZombieCrushSagaFileManager;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;

public class GoIntoGameHandler implements ActionListener {
	
	
	
	
	
	
	
private ZombieCrushSagaMiniGame game;
    
    // HERE'S THE LEVEL TO LOAD
    private String levelFile;
    
    /**
     * This constructor just stores the game and the level to
     * load for later.
     *     
     * @param initGame The game to update.
     * 
     * @param initLevelFile The level to load when the user requests it. 
     */
    public GoIntoGameHandler(  ZombieCrushSagaMiniGame initGame)
                               // String initLevelFile)
    {
        game = initGame;
        //levelFile = initLevelFile;
    }
	
	
	
	
	public void actionPerformed(ActionEvent ae)
    {
        // WE ONLY LET THIS HAPPEN IF THE SPLASH SCREEN IS VISIBLE
       
            // GET THE GAME'S DATA MODEL, WHICH IS ALREADY LOCKED FOR US

            // GO TO THE GAME
        	if(!game.getDataModel().inProgress()){
                ZombieCrushSagaDataModel data=(ZombieCrushSagaDataModel)game.getDataModel();
                String level=data.getCurrentLevel();
                data.setCurrentLevel(level);
                data.setData(0, 0);
               // UPDATE THE DATA
                ZombieCrushSagaFileManager fileManager = game.getFileManager();
                fileManager.loadLevel(level);
            }
            game.switchToGameScreen();
        }
    }



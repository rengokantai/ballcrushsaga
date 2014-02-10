package zombiecrushsaga.events;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import zombiecrushsaga.file.ZombieCrushSagaFileManager;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import static zombiecrushsaga.ZombieCrushSagaConstants.*;

/**
 * This event handler responds to when the user selects
 * a Mahjong level to play on the splash screen.
 * 
 * @author Richard McKenna
 */
public class SelectLevelHandler implements ActionListener
{
    // HERE'S THE GAME WE'LL UPDATE
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
    public SelectLevelHandler(  ZombieCrushSagaMiniGame initGame,
                                String initLevelFile)
    {
        game = initGame;
        levelFile = initLevelFile;
    }
    
    /**
     * Here is the event response. This code is executed when
     * the user clicks on a button for selecting a level
     * which is how the user starts a game. Note that the game 
     * data is already locked for this thread before it is called, 
     * and that it will be unlocked after it returns.
     * 
     * @param ae the event object for the button press
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // WE ONLY LET THIS HAPPEN IF THE SPLASH SCREEN IS VISIBLE
        if (game.isCurrentScreenState(SAGA_SCREEN_STATE))
        {
        	
        	
        	
        	 JPanel canvas=game.getCanvas();
    		 Rectangle lc=canvas.getBounds();
    		 Point lo=lc.getLocation();
    		lo.y=0;
    		 //int xx=(int)canvas.getY();
    		// xx+=30;
    		 lc.setLocation(lo);
    		 canvas.setBounds(lc);
            // GET THE GAME'S DATA MODEL, WHICH IS ALREADY LOCKED FOR US
            ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel)game.getDataModel();
            
            data.setCurrentLevel(levelFile);
            data.setData(0, 0);
            data.setLevelMultiplier(Integer.parseInt(levelFile.substring(5))-1);
            
        
            // UPDATE THE DATA
            ZombieCrushSagaFileManager fileManager = game.getFileManager();
            fileManager.loadLevel(levelFile);
           // game.displayStats();
           // data.getStackTiles().clear();
           // data.initTiles();

            // GO TO THE GAME
            game.switchToStatScreen();
        }
    }
}
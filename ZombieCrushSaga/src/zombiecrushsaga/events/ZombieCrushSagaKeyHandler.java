package zombiecrushsaga.events;
import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import zombiecrushsaga.file.ZombieCrushSagaFileManager;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ZombieCrushSagaKeyHandler extends KeyAdapter{
	
	
	
	 private ZombieCrushSagaMiniGame game;

	    /**
	     * This constructor simply inits the object by 
	     * keeping the game for later.
	     * 
	     * @param initGame The Mahjong game that contains
	     * the back button.
	     */    
	    public ZombieCrushSagaKeyHandler(ZombieCrushSagaMiniGame initGame)
	    {
	        game = initGame;
	    }
	    
	    /**
	     * This method provides a custom game response to when the user
	     * presses a keyboard key.
	     * 
	     * @param ke Event object containing information about the event,
	     * like which key was pressed.
	     */
	    @Override
	    public void keyPressed(KeyEvent ke)
	    {
	        // CHEAT BY ONE MOVE. NOTE THAT IF WE HOLD THE C
	        // KEY DOWN IT WILL CONTINUALLY CHEAT
	        ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel)game.getDataModel();
	        ZombieCrushSagaFileManager fileManager = game.getFileManager();
	        if (ke.getKeyCode()==KeyEvent.VK_1)
	        {         
	            int[][]testGrid=fileManager.loadCheat(1);
	            data.resetTestLevel(testGrid);
	        }
	        if (ke.getKeyCode()==KeyEvent.VK_2)
	        {         
	            int[][]testGrid=fileManager.loadCheat(2);
	            data.resetTestLevel(testGrid);
	        }
	        if (ke.getKeyCode()==KeyEvent.VK_3)
	        {         
	            int[][]testGrid=fileManager.loadCheat(3);
	            data.resetTestLevel(testGrid);
	        }
	        if (ke.getKeyCode()==KeyEvent.VK_4)
	        {         
	            int[][]testGrid=fileManager.loadCheat(4);
	            data.resetTestLevel(testGrid);
	        }
	        if (ke.getKeyCode()==KeyEvent.VK_5)
	        {         
	            int[][]testGrid=fileManager.loadCheat(5);
	            data.resetTestLevel(testGrid);
	        }
	        if(ke.getKeyCode()==KeyEvent.VK_W){
	            data.winCheat();
	        }
	        if(ke.getKeyCode()==KeyEvent.VK_L){
	            data.loseCheat();
	        }
	        
	        
	        
	    }

}

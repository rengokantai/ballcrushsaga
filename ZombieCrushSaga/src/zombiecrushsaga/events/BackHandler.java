package zombiecrushsaga.events;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import static zombiecrushsaga.ZombieCrushSagaConstants.*;



public class BackHandler implements ActionListener{
	
private ZombieCrushSagaMiniGame game;

	
	public BackHandler(ZombieCrushSagaMiniGame initGame)
    {
        game = initGame;
    }
	
	public void actionPerformed(ActionEvent ae)
    {
        // IF THERE IS A GAME UNDERWAY, COUNT IT AS A LOSS
		if(game.isCurrentScreenState(STATS_SCREEN_STATE)||game.isCurrentScreenState(YOUFAIL_STATE)||game.isCurrentScreenState(YOUWIN_STATE)){
			game.switchToSagaScreen();
		}
		
		if(game.isCurrentScreenState(GAME_SCREEN_STATE))
		{
		
    
            //game.getDataModel().endGameAsLoss();
        	ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel)game.getDataModel();
        	data.processWin();
            data.enableTiles(false);
            
        
        // RESET THE GAME AND ITS DATA
        //game.reset();
        game.switchToStatScreen();
        
		}
        
        
        
        
        
        
        
    }

}

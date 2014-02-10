package zombiecrushsaga.events;

import static zombiecrushsaga.ZombieCrushSagaConstants.SAGA_SCREEN_STATE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;

public class ClickXGameHandler implements ActionListener{
	
	
    private ZombieCrushSagaMiniGame game;
	private ZombieCrushSagaDataModel data;
	
	
	
	
	public ClickXGameHandler(ZombieCrushSagaMiniGame initMiniGame)
    {
        game = initMiniGame;
        
    }
    
    
    public void actionPerformed(ActionEvent ae)
    
    
    {
    	if (game.isCurrentScreenState(SAGA_SCREEN_STATE))
        {
    	
    	 if (game.getDataModel().inProgress())
         {
             game.getDataModel().endGameAsLoss();
         }
         // AND CLOSE THE ALL
         System.exit(0);

        }
    }
	
	
	
	
	
	
	
	
	

}

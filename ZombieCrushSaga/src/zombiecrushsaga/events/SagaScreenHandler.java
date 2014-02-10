package zombiecrushsaga.events;

import java.awt.event.ActionEvent;

import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;

public class SagaScreenHandler {
private ZombieCrushSagaMiniGame game;

	
	public SagaScreenHandler(ZombieCrushSagaMiniGame initGame)
    {
        game = initGame;
    }
	
	public void actionPerformed(ActionEvent ae)
    {
        // IF THERE IS A GAME UNDERWAY, COUNT IT AS A LOSS
        if (game.getDataModel().inProgress())
        {
            //game.getDataModel().endGameAsLoss();
        	ZombieCrushSagaDataModel data = (ZombieCrushSagaDataModel)game.getDataModel();
        	data.endGameAsLoss();
            //game.switchToSplashScreen();
            
        }
        // RESET THE GAME AND ITS DATA
        //game.reset();
        game.switchToSagaScreen();
    }

}

package zombiecrushsaga.events;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import zombiecrushsaga.data.ZombieCrushSagaDataModel;
/**
 * 
 * This handler addresses undo button.
 * @author Yidi Ke
 *
 */
public class UndoHandler implements ActionListener{
	
	private ZombieCrushSagaMiniGame game;

	
	public UndoHandler(ZombieCrushSagaMiniGame initGame)
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
        //    data.undoLastMove();
        }
        // RESET THE GAME AND ITS DATA
        //game.reset();
    }
	

}

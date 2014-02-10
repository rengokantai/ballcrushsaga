package mahjong_solitaire.events;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mahjong_solitaire.ui.MahjongSolitaireMiniGame;
import mahjong_solitaire.data.MahjongSolitaireDataModel;
/**
 * 
 * This handler addresses undo button.
 * @author Yidi Ke
 *
 */
public class UndoHandler implements ActionListener{
	
	private MahjongSolitaireMiniGame game;

	
	public UndoHandler(MahjongSolitaireMiniGame initGame)
    {
        game = initGame;
    }
	
	public void actionPerformed(ActionEvent ae)
    {
        // IF THERE IS A GAME UNDERWAY, COUNT IT AS A LOSS
        if (game.getDataModel().inProgress())
        {
            //game.getDataModel().endGameAsLoss();
        	MahjongSolitaireDataModel data = (MahjongSolitaireDataModel)game.getDataModel();
            data.undoLastMove();
        }
        // RESET THE GAME AND ITS DATA
        //game.reset();
    }
	

}

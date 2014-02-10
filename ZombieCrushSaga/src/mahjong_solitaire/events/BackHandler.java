package mahjong_solitaire.events;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mahjong_solitaire.ui.MahjongSolitaireMiniGame;
import mahjong_solitaire.data.MahjongSolitaireDataModel;
public class BackHandler implements ActionListener{
	
private MahjongSolitaireMiniGame game;

	
	public BackHandler(MahjongSolitaireMiniGame initGame)
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
        	data.endGameAsLoss();
            //game.switchToSplashScreen();
            
        }
        // RESET THE GAME AND ITS DATA
        //game.reset();
        game.switchToSplashScreen();
    }

}

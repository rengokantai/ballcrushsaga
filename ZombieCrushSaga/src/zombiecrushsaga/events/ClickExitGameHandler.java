package zombiecrushsaga.events;

import static zombiecrushsaga.ZombieCrushSagaConstants.SAGA_SCREEN_STATE;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import mini_game.Sprite;
import mini_game.SpriteType;
import properties_manager.PropertiesManager;

import zombiecrushsaga.ZombieCrushSaga.ZombieCrushSagaPropertyType;
import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import zombiecrushsaga.ui.ZombieCrushSagaPanel;

public class ClickExitGameHandler implements ActionListener{
	
	
	
	private ZombieCrushSagaMiniGame miniGame;
	private ZombieCrushSagaDataModel data;
	
	
	
	
	public ClickExitGameHandler(ZombieCrushSagaMiniGame initMiniGame)
    {
        miniGame = initMiniGame;
        
    }
    
    
    public void actionPerformed(ActionEvent ae)
    
    
    {
    	
    	 if (miniGame.getDataModel().inProgress())
         {
             miniGame.getDataModel().endGameAsLoss();
         }
         // AND CLOSE THE ALL
         System.exit(0);

         
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package zombiecrushsaga.events;

import static zombiecrushsaga.ZombieCrushSagaConstants.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import zombiecrushsaga.ZombieCrushSaga.ZombieCrushSagaPropertyType;
import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;
import zombiecrushsaga.ui.ZombieCrushSagaPanel;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import properties_manager.PropertiesManager;

public class DownHandler implements ActionListener{

	
	private ZombieCrushSagaMiniGame miniGame;
	private ZombieCrushSagaDataModel data;
	private MiniGame mgm;
    //private Sprite sT;
    //private 
    /**
     * This constructor simply inits the object by 
     * keeping the game for later.
     * 
     * @param initGame The Mahjong game that contains
     * the back button.
     */
    public DownHandler(ZombieCrushSagaMiniGame initMiniGame)
    {
        miniGame = initMiniGame;
        
    }
    
    
    public void actionPerformed(ActionEvent ae)
    
    
    {
    	
		BufferedImage img;
		float x, y;
		SpriteType sT;
		Sprite s;
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		String imgPath = props
				.getProperty(ZombieCrushSagaPropertyType.IMG_PATH);


    	 if (miniGame.isCurrentScreenState(SAGA_SCREEN_STATE))
         {
             // GET THE GAME'S DATA MODEL, WHICH IS ALREADY LOCKED FOR US
    		 
    		 ZombieCrushSagaPanel so =new  ZombieCrushSagaPanel(miniGame,data);

    		 JPanel canvas=miniGame.getCanvas();
    		 Rectangle lc=canvas.getBounds();
    		 Point lo=lc.getLocation();
    		 if(lo.y==-400)
    			 return;
    		 else{
    		 lo.x+=0;
    		 lo.y-=10;
    		 //int xx=(int)canvas.getY();
    		// xx+=30;
    		 lc.setLocation(lo);
    		 canvas.setBounds(lc);
    		 
    		 miniGame.resetDownButtons();


    		 
    		 
    		 
    		 }}

         
    }
	
	
	
	
	
	

}
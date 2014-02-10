package zombiecrushsaga.events;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static zombiecrushsaga.ZombieCrushSagaConstants.*;
import zombiecrushsaga.data.ZombieCrushSagaDataModel;
import zombiecrushsaga.ui.ZombieCrushSagaMiniGame;


public class ChangeCursorHandler implements ActionListener{
	
	private ZombieCrushSagaMiniGame miniGame;
	private ZombieCrushSagaDataModel data;
	
	
	public ChangeCursorHandler(ZombieCrushSagaMiniGame initMiniGame)
    {
        miniGame = initMiniGame;
        
    }
	
    
    public void actionPerformed(ActionEvent ae)
    
    
    {

    		 Toolkit tk=Toolkit.getDefaultToolkit();
    		 Image curimage =tk.getImage("./img/zomjong/malletc.png");
    		 Cursor cs=tk.createCustomCursor(curimage, new Point(4,4), "");
             miniGame.getCanvas().setCursor(cs);

    }
	
	
	
	
	
	
	
	
	
	

}

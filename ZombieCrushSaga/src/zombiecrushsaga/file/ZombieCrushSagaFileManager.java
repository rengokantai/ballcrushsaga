package zombiecrushsaga.file;



import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


import zombiecrushsaga.ZombieCrushSaga;
import zombiecrushsaga.ZombieCrushSaga.ZombieCrushSagaPropertyType;
import zombiecrushsaga.ZombieCrushSagaConstants;

import zombiecrushsaga.ui.*;
import zombiecrushsaga.data.*;
import static zombiecrushsaga.ZombieCrushSagaConstants.*;
import properties_manager.PropertiesManager;





public class ZombieCrushSagaFileManager {
private ZombieCrushSagaMiniGame miniGame;
public int numOfTiles=0;
public ArrayList <int[]>emycell;
    
    /**
     * Constructor for initializing this file manager, it simply keeps
     * the game for later.
     * 
     * @param initMiniGame The game for which this class loads data.
     */
    public ZombieCrushSagaFileManager(ZombieCrushSagaMiniGame initMiniGame)
    {
        // KEEP IT FOR LATER
        miniGame = initMiniGame;

    }
    
    
    public int getN(){
    	return numOfTiles;
    }
    
    
    
    
    public ArrayList<int[]> getEmptyCell(){
    	return emycell;
    }

    /**
     * This method loads the contents of the levelFile argument so that
     * the player may then play that level. 
     * 
     * @param levelFile Level to load.
     */

    public void loadLevel(String levelFile)
    {
        // LOAD THE RAW DATA SO WE CAN USE IT
        // OUR LEVEL FILES WILL HAVE THE DIMENSIONS FIRST,
        // FOLLOWED BY THE GRID VALUES
        try
        {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String dataPath = props.getProperty(ZombieCrushSaga.ZombieCrushSagaPropertyType.DATA_PATH);
            File fileToOpen = new File(dataPath +"./zomjong/"+levelFile+".zom");

            // LET'S USE A FAST LOADING TECHNIQUE. WE'LL LOAD ALL OF THE
            // BYTES AT ONCE INTO A BYTE ARRAY, AND THEN PICK THAT APART.
            // THIS IS FAST BECAUSE IT ONLY HAS TO DO FILE READING ONCE
            byte[] bytes = new byte[Long.valueOf(fileToOpen.length()).intValue()];
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            FileInputStream fis = new FileInputStream(fileToOpen);
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            // HERE IT IS, THE ONLY READY REQUEST WE NEED
            bis.read(bytes);
            bis.close();
            
            // NOW WE NEED TO LOAD THE DATA FROM THE BYTE ARRAY
            DataInputStream dis = new DataInputStream(bais);
            
            // NOTE THAT WE NEED TO LOAD THE DATA IN THE SAME
            // ORDER AND FORMAT AS WE SAVED IT
            
            // FIRST READ THE GRID DIMENSIONS
            int initGridColumns = dis.readInt();
            int initGridRows = dis.readInt();
            int[][] newGrid = new int[initGridColumns][initGridRows];
            // AND NOW ALL THE CELL VALUES
            for (int i = 0; i < initGridColumns; i++)
            {                        
                for (int j = 0; j < initGridRows; j++)
                {
                    newGrid[i][j] = dis.readInt();
                 
                }
              
            }
            
            // EVERYTHING WENT AS PLANNED SO LET'S MAKE IT PERMANENT
            ZombieCrushSagaDataModel dataModel = (ZombieCrushSagaDataModel)miniGame.getDataModel();
            dataModel.initLevelGrid(newGrid, initGridColumns, initGridRows);
            dataModel.setCurrentLevel(levelFile);
            
            miniGame.updateBoundaries();
        }
        catch(Exception e)
        {
            // LEVEL LOADING ERROR
            miniGame.getErrorHandler().processError(ZombieCrushSagaPropertyType.LOAD_LEVEL_ERROR);
        }
    }    
    
    /**
     * This method loads the player record from the records file
     * so that the user may view stats.
     * 
     * @return The fully loaded record from the player record file.
     */
    public ZombieCrushSagaRecord loadRecord()
    {
        ZombieCrushSagaRecord recordToLoad = new ZombieCrushSagaRecord();
        
        // LOAD THE RAW DATA SO WE CAN USE IT
        // OUR LEVEL FILES WILL HAVE THE DIMENSIONS FIRST,
        // FOLLOWED BY THE GRID VALUES
        try
        {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String dataPath = props.getProperty(ZombieCrushSagaPropertyType.DATA_PATH);
            String recordPath = dataPath + props.getProperty(ZombieCrushSagaPropertyType.RECORD_FILE_NAME);
            File fileToOpen = new File(recordPath);
            // LET'S USE A FAST LOADING TECHNIQUE. WE'LL LOAD ALL OF THE
            // BYTES AT ONCE INTO A BYTE ARRAY, AND THEN PICK THAT APART.
            // THIS IS FAST BECAUSE IT ONLY HAS TO DO FILE READING ONCE
            byte[] bytes = new byte[Long.valueOf(fileToOpen.length()).intValue()];
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            FileInputStream fis = new FileInputStream(fileToOpen);
            BufferedInputStream bis = new BufferedInputStream(fis);   
            // HERE IT IS, THE ONLY READY REQUEST WE NEED
            bis.read(bytes);
            bis.close();    
            // NOW WE NEED TO LOAD THE DATA FROM THE BYTE ARRAY
            DataInputStream dis = new DataInputStream(bais);        
            // NOTE THAT WE NEED TO LOAD THE DATA IN THE SAME
            // ORDER AND FORMAT AS WE SAVED IT
            // FIRST READ THE NUMBER OF LEVELS
            int numLevels = dis.readInt(); 
            for (int i = 0; i < numLevels; i++)
            {
                String levelName = dis.readUTF();
                ZombieLevelRecord rec = new ZombieLevelRecord();
                rec.gamesPlayed = dis.readInt();
                rec.wins = dis.readInt();
                rec.losses = dis.readInt();
                rec.highestScore = dis.readLong();
                rec.visible=dis.readBoolean();
                rec.star=dis.readInt();
                recordToLoad.addZombieLevelRecord(levelName, rec);
                if(i>0){
                    if(rec.highestScore>=FIRST_STAR_SCORE[i]||rec.visible){
                       LEVEL_LOCK[i]=true;
                    }
                }
        //  System.out.println("gamesPlayed "+rec.gamesPlayed+" fast "+rec.fastestTime+" win "+rec.wins+"  loss "+rec.losses);     
            }
        }
        catch(Exception e)
        {
            // THERE WAS NO RECORD TO LOAD, SO WE'LL JUST RETURN AN
            // EMPTY ONE AND SQUELCH THIS EXCEPTION
        }        
        return recordToLoad;
    }
    
    public int[][] loadCheat(int cheat)
    {    int[][] newGrid = null;

        try
        {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
        //String dataPath = props.getProperty(zombiecrushsaga.ZombieCrushSagaPropertyType.DATA_PATH);
            File fileToOpen = new File(DATA_PREFIX +"./zomjong/"+"cheat"+cheat+".zom");

            // LET'S USE A FAST LOADING TECHNIQUE. WE'LL LOAD ALL OF THE
            // BYTES AT ONCE INTO A BYTE ARRAY, AND THEN PICK THAT APART.
            // THIS IS FAST BECAUSE IT ONLY HAS TO DO FILE READING ONCE
            byte[] bytes = new byte[Long.valueOf(fileToOpen.length()).intValue()];
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            FileInputStream fis = new FileInputStream(fileToOpen);
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            // HERE IT IS, THE ONLY READY REQUEST WE NEED
            bis.read(bytes);
            bis.close();
            
            // NOW WE NEED TO LOAD THE DATA FROM THE BYTE ARRAY
            DataInputStream dis = new DataInputStream(bais);
            
            // NOTE THAT WE NEED TO LOAD THE DATA IN THE SAME
            // ORDER AND FORMAT AS WE SAVED IT
            
            // FIRST READ THE GRID DIMENSIONS
            int initGridColumns = dis.readInt();
            int initGridRows = dis.readInt();
            newGrid = new int[initGridColumns][initGridRows];
            // AND NOW ALL THE CELL VALUES
            for (int i = 0; i < initGridColumns; i++)
            {                        
                for (int j = 0; j < initGridRows; j++)
                {
                    newGrid[i][j] = dis.readInt();
                 
                }
              
            }
    
        }
        catch(Exception e)
        {
            // LEVEL LOADING ERROR
            miniGame.getErrorHandler().processError(ZombieCrushSagaPropertyType.LOAD_LEVEL_ERROR);
        }
        return newGrid;
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
	
	

}

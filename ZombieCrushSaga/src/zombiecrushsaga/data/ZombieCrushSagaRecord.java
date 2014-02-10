package zombiecrushsaga.data;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import mini_game.MiniGame;

import properties_manager.PropertiesManager;
import static zombiecrushsaga.ZombieCrushSagaConstants.*;

import zombiecrushsaga.ZombieCrushSaga.ZombieCrushSagaPropertyType;
import zombiecrushsaga.data.ZombieLevelRecord;


public class ZombieCrushSagaRecord {
	private HashMap<String, ZombieLevelRecord> levelRecords;
	private MiniGame mg;

    /**
     * Default constructor, it simply creates the hash table for
     * storing all the records stored by level.
     */
    public ZombieCrushSagaRecord()
    {
        levelRecords = new HashMap();
    }

    // GET METHODS
        // - getGamesPlayed
        // - getWins
        // - getLosses
        // - getFastestTime
    
    /**
     * This method gets the games played for a given level.
     * 
     * @param levelName Level for the request.
     * 
     * @return The number of games played for the levelName level.
     */
    public int getGamesPlayed(String levelName) 
    {
        ZombieLevelRecord rec = levelRecords.get(levelName);

        // IF levelName ISN'T IN THE RECORD OBJECT
        // THEN SIMPLY RETURN 0
        
        
        
        
        
        if (rec == null)
            return 0;
        // OTHERWISE RETURN THE GAMES PLAYED
        else
            return rec.gamesPlayed; 
    }
    
    public void setVisible(String level){
        ZombieLevelRecord rec = levelRecords.get(level);
        rec.visible=true;
    }
    

    public int getStar1(String levelName){
    	ZombieLevelRecord rec = levelRecords.get(levelName);
    	PropertiesManager props = PropertiesManager.getPropertiesManager();
    	ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
    	if(levelName.equals(DATA_PREFIX+scoress.get(0)))
    	rec.star1=300;
    	if(levelName.equals(DATA_PREFIX+scoress.get(1)))
    		rec.star1=1900;
    		if(levelName.equals(DATA_PREFIX+scoress.get(2)))
    			rec.star1=4000;
    			if(levelName.equals(DATA_PREFIX+scoress.get(3)))
    				rec.star1=4500;
    				if(levelName.equals(DATA_PREFIX+scoress.get(4)))
    					rec.star1=5000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(5)))
    					rec.star1=9000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(6)))
    					rec.star1=60000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(7)))
    					rec.star1=20000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(8)))
    					rec.star1=22000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(9)))
    					rec.star1=40000;
    	//rec.star1=332;
    	
    	return rec.star1;
    }
    
    public int getStar2(String levelName){
    	ZombieLevelRecord rec = levelRecords.get(levelName);
    	PropertiesManager props = PropertiesManager.getPropertiesManager();
    	ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
    	if(levelName.equals(DATA_PREFIX+scoress.get(0)))
    	rec.star2=400;
    	if(levelName.equals(DATA_PREFIX+scoress.get(1)))
    		rec.star2=2100;
    		if(levelName.equals(DATA_PREFIX+scoress.get(2)))
    			rec.star2=6000;
    			if(levelName.equals(DATA_PREFIX+scoress.get(3)))
    				rec.star2=6000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(4)))
    					rec.star2=8000;
    				
    				if(levelName.equals(DATA_PREFIX+scoress.get(5)))
    					rec.star2=11000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(6)))
    					rec.star2=75000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(7)))
    					rec.star2=30000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(8)))
    					rec.star2=44000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(9)))
    					rec.star2=70000;
    	
    	
    	return rec.star2;
    }
    
    
    public int getStar3(String levelName){
    	ZombieLevelRecord rec = levelRecords.get(levelName);
    	PropertiesManager props = PropertiesManager.getPropertiesManager();
    	ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
    	if(levelName.equals(DATA_PREFIX+scoress.get(0)))
    	rec.star3=500;
    	if(levelName.equals(DATA_PREFIX+scoress.get(1)))
    		rec.star3=2400;
    		if(levelName.equals(DATA_PREFIX+scoress.get(2)))
    			rec.star3=8000;
    			if(levelName.equals(DATA_PREFIX+scoress.get(3)))
    				rec.star3=9000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(4)))
    					rec.star3=12000;
    				
    				
    				if(levelName.equals(DATA_PREFIX+scoress.get(5)))
    					rec.star3=13000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(6)))
    					rec.star3=85000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(7)))
    					rec.star3=45000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(8)))
    					rec.star3=66000;
    				if(levelName.equals(DATA_PREFIX+scoress.get(9)))
    					rec.star3=100000;
    	
    	
    	return rec.star3;
    }
    
    public int getMove(String levelName){
    	ZombieLevelRecord rec = levelRecords.get(levelName);
    	PropertiesManager props = PropertiesManager.getPropertiesManager();
    	ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
    	if(levelName.equals(DATA_PREFIX+scoress.get(0)))
    	rec.moves=6;
    	if(levelName.equals(DATA_PREFIX+scoress.get(1)))
    		rec.moves=15;
    		if(levelName.equals(DATA_PREFIX+scoress.get(2)))
    			rec.moves=18;
    			if(levelName.equals(DATA_PREFIX+scoress.get(3)))
    				rec.moves=15;
    				if(levelName.equals(DATA_PREFIX+scoress.get(4)))
    					rec.moves=20;
    				
    				if(levelName.equals(DATA_PREFIX+scoress.get(5)))
    					rec.moves=25;
    				if(levelName.equals(DATA_PREFIX+scoress.get(6)))
    					rec.moves=50;
    				if(levelName.equals(DATA_PREFIX+scoress.get(7)))
    					rec.moves=20;
    				if(levelName.equals(DATA_PREFIX+scoress.get(8)))
    					rec.moves=25;
    				if(levelName.equals(DATA_PREFIX+scoress.get(9)))
    					rec.moves=40;
    	
    	
    	return rec.moves;
    }
    
    public int getNumOfTiles(String levelName){
    	ZombieLevelRecord rec = levelRecords.get(levelName);
    	PropertiesManager props = PropertiesManager.getPropertiesManager();
    	ArrayList<String> scoress = props
				.getPropertyOptionsList(ZombieCrushSagaPropertyType.LEVEL_OPTIONS);
    	if(levelName.equals(DATA_PREFIX+scoress.get(0)))
    	rec.numOfTiles=100;
    	if(levelName.equals(DATA_PREFIX+scoress.get(1)))
    		rec.numOfTiles=100;
    		if(levelName.equals(DATA_PREFIX+scoress.get(2)))
    			rec.numOfTiles=100;
    			if(levelName.equals(DATA_PREFIX+scoress.get(3)))
    				rec.numOfTiles=100;
    				if(levelName.equals(DATA_PREFIX+scoress.get(4)))
    					rec.numOfTiles=100;
    				
    				if(levelName.equals(DATA_PREFIX+scoress.get(5)))
    					rec.numOfTiles=100;
    				if(levelName.equals(DATA_PREFIX+scoress.get(6)))
    					rec.numOfTiles=100;
    				if(levelName.equals(DATA_PREFIX+scoress.get(7)))
    					rec.numOfTiles=100;
    				if(levelName.equals(DATA_PREFIX+scoress.get(8)))
    					rec.numOfTiles=100;
    				if(levelName.equals(DATA_PREFIX+scoress.get(9)))
    					rec.numOfTiles=100;
    	
    	
    	return rec.numOfTiles;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * This method gets the wins for a given level.
     * 
     * @param levelName Level for the request.
     * 
     * @return The wins the player has earned for the levelName level.
     */    
    public int getWins(String levelName)
    {
        ZombieLevelRecord rec = levelRecords.get(levelName);
        
        // IF levelName ISN'T IN THE RECORD OBJECT
        // THEN SIMPLY RETURN 0        
        if (rec == null)
            return 0;
        // OTHERWISE RETURN THE WINS
        else
            return rec.wins; 
    }
    
    
    
    public long getHighestScore(String levelName)
    {
        ZombieLevelRecord rec = levelRecords.get(levelName);
        if(rec==null)
            return 0;
        else
       
         return rec.highestScore; 
    }
    
    
    
    
    public long getStarEarned(String levelName)
    {
        ZombieLevelRecord rec = levelRecords.get(levelName);
        if(rec==null)
            return 0;
        else
       
         return rec.star; 
    }
    
    
    
    
    /**
     * This method gets the losses for a given level.
     * 
     * @param levelName Level for the request.
     * 
     * @return The losses the player has earned for the levelName level.
     */      
    public int getLosses(String levelName)
    {
        ZombieLevelRecord rec = levelRecords.get(levelName);

        // IF levelName ISN'T IN THE RECORD OBJECT
        // THEN SIMPLY RETURN 0
        
        if (rec == null)
            return 0;
        // OTHERWISE RETURN THE LOSSES
        else
            return rec.losses; 
    }
    
    /**
     * This method gets the fastest time for a given level.
     * 
     * @param levelName Level for the request.
     * 
     * @return The fastest time the player has earned for the levelName level.
     */       
    public long getFastestTime(String levelName)
    {
        ZombieLevelRecord rec = levelRecords.get(levelName);
        
        // IF THE PLAYER HAS NEVER PLAYED THAT LEVEL, RETURN
        // THE MAX AS A  FLAG
        if (rec == null)
            return Long.MAX_VALUE;
        // OTHERWISE RETURN THE FASTEST TIME
        else
            return rec.fastestTime; 
    }

    // ADD METHODS
        // -addZombieLevelRecord
        // -addWin
        // -addLoss
    
    /**
     * Adds the record for a level
     * 
     * @param levelName
     * 
     * @param rec 
     */
    public void addZombieLevelRecord(String levelName, ZombieLevelRecord rec)
    {
        levelRecords.put(levelName, rec);
    }
    
    /**
     * This method adds a win to the current player's record according
     * to the level being played.
     * 
     * @param levelName The level being played that the player won.
     * 
     * @param winTime The time it took to win the game.
     */
    public void addWin(String levelName, long currentScore)
    {
        // GET THE RECORD FOR levelName
        ZombieLevelRecord rec = levelRecords.get(levelName);
        int le=Integer.parseInt(levelName.substring(5));
        ZombieCrushSagaDataModel zd=new ZombieCrushSagaDataModel(mg);
        
        // IF THE PLAYER HAS NEVER PLAYED A GAME ON levelName
        if (rec == null)
        {
            // MAKE A NEW RECORD FOR THIS LEVEL, SINCE THIS IS
            // THE FIRST TIME WE'VE PLAYED IT
            rec = new ZombieLevelRecord();
            rec.gamesPlayed = 1;
            rec.wins = 1;
            rec.losses = 0;
            rec.highestScore = currentScore;
            rec.visible=true;
            if(currentScore>=FIRST_STAR_SCORE[le-1]&&currentScore<SECOND_STAR_SCORE[le-1]){
            rec.star=1;}
            else if
            (currentScore>=SECOND_STAR_SCORE[le-1]&&currentScore<THIRD_STAR_SCORE[le-1]){
            	rec.star=2;
            }
            else 
            	rec.star=3;
           // rec.star=0;
           // rec.star=zd.getStar();
            levelRecords.put(levelName, rec);
        }
        else
        {
            // WE'VE PLAYED THIS LEVEL BEFORE, SO SIMPLY
            // UPDATE THE STATS
            rec.gamesPlayed++;
            rec.wins++;
           if (currentScore >rec.highestScore)
                rec.highestScore=currentScore;
         /*   if(currentScore>rec.highestScore&&currentScore>FIRST_STAR_SCORE[le-1])
            	rec.star=1;
            if(currentScore>rec.highestScore&&currentScore>SECOND_STAR_SCORE[le-1])
            	rec.star=2;
            if(currentScore>rec.highestScore&&currentScore>THIRD_STAR_SCORE[le-1])
            	rec.star=3;*/
          //rec.star=0;
          // if(zd.getStar()>rec.star)
        	//   rec.star=zd.getStar();
           
           
         if(currentScore >rec.highestScore){
           if(currentScore>=FIRST_STAR_SCORE[le-1]&&currentScore<SECOND_STAR_SCORE[le-1]){
               rec.star=1;}
               else if
               (currentScore>=SECOND_STAR_SCORE[le-1]&&currentScore<THIRD_STAR_SCORE[le-1]){
               	rec.star=2;
               }
               else 
               	rec.star=3;}
           
        }
    }
    
    /**
     * This method adds a loss to the current player's record according
     * to the level being played.
     * 
     * @param levelName The level being played that the player lost.
     */
    public void addLoss(String levelName)
    {
        // GET THE RECORD FOR levelName
        ZombieLevelRecord rec = levelRecords.get(levelName);

        // IF THE PLAYER HAS NEVER PLAYED A GAME ON levelName
        if (rec == null)
        {
            // MAKE A NEW RECORD FOR THIS LEVEL, SINCE THIS IS
            // THE FIRST TIME WE'VE PLAYED IT
            rec = new ZombieLevelRecord();
            rec.gamesPlayed = 1;
            rec.wins = 0;
            rec.losses = 1;

            rec.visible=true;
            rec.star=0;
            levelRecords.put(levelName, rec);
        }
        else
        {
            // WE'VE PLAYED THIS LEVEL BEFORE, SO SIMPLY
            // UPDATE THE STATS
            rec.gamesPlayed++;
            rec.losses++;
        }
    }
    
    // ADDITIONAL SERVICE METHODS
        // -calculateWinPercentage
        // -toByteArray

    /**
     * This method calculates and returns the player's win
     * percentage for levelName.
     * 
     * @param levelName The level for which to retreive 
     * the win percentage.
     * 
     * @return The win percentage for levelName.
     */
    public double calculateWinPercentage(String levelName)
    {
        // GET THE RECORD FOR leveName
        ZombieLevelRecord rec = levelRecords.get(levelName);
        
        // IF NO GAMES HAVE BEEN PLAYED RETURN 0
        if (rec == null)
            return 0.0;
        // OTHERWISE CALCULATE AND RETURN THE WIN PERCENTAGE
        else
            return (double)rec.wins/(double)rec.gamesPlayed;
    }
    
   
/*public int star1(String levelName){
	if(levelName=="Level1"){
		return 300;}
}
    */
    
    public void openNewLevelRecord(String levelName){
        // GET THE RECORD FOR levelName
       ZombieLevelRecord rec = levelRecords.get(levelName);
       
       // IF THE PLAYER HAS NEVER PLAYED A GAME ON levelName
       if (rec == null)
       {
           // MAKE A NEW RECORD FOR THIS LEVEL, SINCE THIS IS
           // THE FIRST TIME WE'VE PLAYED IT
           rec = new ZombieLevelRecord();
           rec.gamesPlayed = 0;
           rec.wins = 0;
           rec.losses = 0;
           rec.highestScore =0;
           rec.visible=true;
           rec.star=0;
           levelRecords.put(levelName, rec);
       }
       else
       {
           rec.visible=true;
       }
   
   }
    
    
 
    /**
     * This method constructs and fills in a byte array with all the
     * necessary data stored by this object. We do this because writing
     * a byte array all at once to a file is fast. Certainly much faster
     * than writing to a file across many write operations.
     * 
     * @return A byte array filled in with all the data stored in this
     * object, which means all the player records in all the levels.
     * 
     * @throws IOException Note that this method uses a stream that
     * writes to an internal byte array, not a file. So this exception
     * should never happen.
     */
    public byte[] toByteArray() throws IOException
    {
        Iterator<String> keysIt = levelRecords.keySet().iterator();
        int numLevels = levelRecords.keySet().size();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(numLevels);
        while(keysIt.hasNext())
        {
            String key = keysIt.next();
            dos.writeUTF(key);
            ZombieLevelRecord rec = levelRecords.get(key);
            dos.writeInt(rec.gamesPlayed);
            dos.writeInt(rec.wins);
            dos.writeInt(rec.losses);
            dos.writeLong(rec.highestScore);
            dos.writeBoolean(rec.visible);
            dos.writeInt(rec.star);
        }
        // AND THEN RETURN IT
        return baos.toByteArray();
    }

}

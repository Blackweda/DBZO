
float greenEnergyRegenRate;           // how fast Green Energy regenerates
float yellowEnergyRegenRate;          // how fast Yellow Energy regenerates
//float darkYellowEnergyRegenRate;      // possibly secondary rating of Yellow Energy that has been exhausted fast
//float redEnergyRegenRate;             // Energy that is used to the maximum and worthless almost

float restTime;
bool restMode;

float combatTime;
bool combatMode;

float trainTime;
bool trainMode;

float greenEnergyPercent;
float yellowEnergyPercent;
//float redEnergyPercent;

if(yellowEnergyPercent){                  // IF PLAYER HAS BEEN TRAINING OR FIGHTING (NOT 100% GREEN ENERGY)
      
      yellowEnergyRegenRate = 2.5f;
      greenEnergyRegenRate = 20.0f;

      if(combatMode){
            
            restTime = 0.0f;
            trainTime = 0.0f;
            
            combatTime += 1.0f * Time.deltaTime;
            if(combatTime >= 60){
             
                  yellowEnergyPercent += yellowEnergyRegenRate;
                  combatTime = 0.0f;
            }        
      
      } // end of combatMode
      
      else if(trainMode){
      
          restTime = 0.0f;
          combatTime = 0.0f;
      
          trainTime += 1.0f * Time.deltaTime;
            if(trainTime >= 60){
             
                  yellowEnergyPercent += yellowEnergyRegenRate;
                  trainTime = 0.0f;
            }
      
      
      } // end of trainMode
      
      else if(restMode){
      
            float yellowTime;
            combatTime = trainTime = 0.0f;
            
            restTime += 1 * Time.deltaTime;
            yellowTime += 1 * Time.deltaTime;
            
            if(yellowTime >= 60){
                  
                  yellowEnergyPercent += yellowEnergyRegenRate;
                  yellowTime = 0.0f;
            }
              
            if(restTime >= 7200){            
                  greenEnergyPercent += greenEnergyRegenRate;
                  restTime = 0.0f;
            }
            
      
      } // end of restMode
      
   if(yellowEnergyPercent >= 100)
         yellowEnergyPercent = 100;
      

} // end of yellowEnergyPercent

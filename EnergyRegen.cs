
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
float totalEnergy;
//float redEnergyPercent;

if(yellowEnergyPercent){                  // IF PLAYER HAS BEEN TRAINING OR FIGHTING (NOT 100% GREEN ENERGY)
      
      yellowEnergyRegenRate = 2.5f;
      greenEnergyRegenRate = 20.0f;

      if(combatMode){
            
            restTime = 0.0f;
            trainTime = 0.0f;
            
            combatTime += 1.0f * Time.deltaTime;
            if(combatTime >= 60 && totalEnergy < 100){
             
                  yellowEnergyPercent += yellowEnergyRegenRate;
                  combatTime = 0.0f;
            }        
      
      } // end of combatMode
      /*
            While in combat mode, training mode and rest mode are disabled. If there has been energy used, there will be space
            to regenerate more. If not, then no new energy should be added to a complete energy bar. Every minute there will be 
            a regeneration based on the Rate.
      */
      
      else if(trainMode){
      
          restTime = 0.0f;
          combatTime = 0.0f;
      
          trainTime += 1.0f * Time.deltaTime;
            if(trainTime >= 60 && totalEnergy < 100){
             
                  yellowEnergyPercent += yellowEnergyRegenRate;
                  trainTime = 0.0f;
            }
      
      
      } // end of trainMode
      
      else if(restMode){
      
            float yellowTime;
            combatTime = trainTime = 0.0f;
            
            restTime += 1 * Time.deltaTime;
            yellowTime += 1 * Time.deltaTime;
            
            if(yellowTime >= 60 && totalEnergy < 100){
                  
                  yellowEnergyPercent += yellowEnergyRegenRate;
                  yellowTime = 0.0f;
            }
              
            if(restTime >= 7200){            
                  greenEnergyPercent += greenEnergyRegenRate;
                  yellowEnergyPercent -= greenEnergyRegenRate;
                  restTime = 0.0f;
            }
            
      
      } // end of restMode
      
   if(yellowEnergyPercent >= 100)
         yellowEnergyPercent = 100;
      
   if(totalEnergy >= 100)
         totalEnergy = 100;
      

} // end of yellowEnergyPercent


float greenEnergyRegenRate;           // how fast Green Energy regenerates
float yellowEnergyRegenRate;          // how fast Yellow Energy regenerates
float darkYellowEnergyRegenRate;      // possibly secondary rating of Yellow Energy that has been exhausted fast
float redEnergyRegenRate;             // Energy that is used to the maximum and worthless almost




float restingTime;
bool restMode;

float combatTime;
bool combatMode;

float trainingTime;
bool trainMode;

float greenEnergyPercent;
float yellowEnergyPercent;
float redEnergyPercent;

if(yellowEnergyPercent){

      if(combatMode){
      
        restingTime = 0.0f;
      
        if(combatTime <= 1.0f)
              combatTime += 1 * Time.deltaTime;
      
      
      }
      else if(trainMode){
      
          restingTime = 0.0f;
      
            if(trainingTime <= 1.0f)
              trainingTime += 1 * Time.deltaTime;
      
      
      }
      else if(restMode){
      
            if(restingTime <= 1.0f)
              restingTime += 1 * Time.deltaTime;
              
            if(restingTime >= 7200)            
                  greenEnergyPercent += 20;
            
      
      }
      

}

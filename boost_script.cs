using UnityEngine;
using System.Collections;

public class boost_script : MonoBehaviour {

	// THIS SCRIPT CONTROLS THE POSITIVE/NEGATIVE EFFECTS OF INTERACTING WITH AVATAR TEAM MEMBERS VS. ENEMY MEMBERS
	
	attributes_script Attributes_Script_VACC;
	public bool inTriggerPhase;
	public int touchCount;
	
	void Start () {
	
		Attributes_Script_VACC = GetComponent<attributes_script>();
		inTriggerPhase = false;
		touchCount = 0;
		
	
	}
	
	
	void Update () {
	
	}
	
	void OnTriggerEnter(Collider other) {
	
		inTriggerPhase = true;
		
		float boostDifference;
		float otherBoostLevel;
		
		
		touchCount++;
		
		// ACCESS THE GAMEOBJECT OF THE OTHER COLLIDER AND ITS VARIABLES
		
							GameObject otherAvatar;
							otherAvatar = other.gameObject;
							attributes_script Other_Attributes_Script_VACC = otherAvatar.GetComponent<attributes_script>();
							otherBoostLevel = Other_Attributes_Script_VACC.boostLevel;
							
							boostDifference = otherBoostLevel - Attributes_Script_VACC.boostLevel;
								
							if(boostDifference >= 0)
								transform.position -= transform.forward * boostDifference * (Time.deltaTime * 10);
								
		
	
	}
	
	
}

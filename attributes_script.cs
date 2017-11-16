using UnityEngine;
using System.Collections;

public class attributes_script : MonoBehaviour {

	// THIS SCRIPT CONTROLS THE BASE ATTRIBUTES OF THE AVATAR AND WILL BE PUBLICLY ACCESSED FOR DETERMINING CONTROL VARIABLES IN BOOSTING AND LEVELING
	// AS WELL AS HOLD THE MEMORIES OF THE AVATAR
	
	boost_script Boost_Script_VACC;
	
	public bool touchable; 		// the first collision with another avatar sets this bool to false so that a group of avatar collisions don't screw up each
								// other terribly.
	public float boostLevel;
	public float teamNumber;
	public float idNumber;
	public Bounds avatarBounds;
	public Collider avatarCollider;
	
	// ANDREAS JOHNSON - GLORIOUS
	// MC SNIPER - GLOOMY SUNDAY

	
	void Start () {
	
		Boost_Script_VACC = GetComponent<boost_script>();
		
		touchable = true;
		avatarCollider = GetComponent<Collider>();
		avatarBounds = GetComponent<Collider>().bounds;
		
		
		//Debug.Log("bounds.x is: " + bounds.extents.x + ", bounds.y is: " + bounds.extents.y + ", bounds.z is: " + bounds.extents.z);
	}
	
	
	void Update () {
	
		if(Boost_Script_VACC.inTriggerPhase)
		{
			touchable = false;
		}
	}
}

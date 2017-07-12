using UnityEngine;
using System.Collections;

public class move_script : MonoBehaviour {

	// THIS SCRIPT CONTROLS THE GENERAL MOVEMENT BEHAVIOUR OF THE AVATAR IN ITS ENVIRONMENT

	//http://docs.unity3d.com/412/Documentation/ScriptReference/index.Accessing_Other_Game_Objects.html

	look_script Look_Script_VACC;
	
	public Vector3 newVectorDown;
	public float newTurnDirection;
	public float turnTimer;
	public bool onCountDown;
	public float speed;


	
	void Start () {
	
		Look_Script_VACC = GetComponent<look_script>();		// VACC is "variable access"
		
		newVectorDown = Look_Script_VACC.newVectorDown;
		newTurnDirection = 0.0f;
		turnTimer = 2.0f;
		onCountDown = false;
		speed = 2.7f;

	
	}
	

	void Update () {
	
				// SEQUENCE #1.1: CONTROL WALKING UNTIL DECIDING TO TURN DIRECTION BASED ON 2 SECOND TIMER DECISION-MAKING~~~
				
				if(Look_Script_VACC.onPlatform == true)
					turnTimer = 0.0f;
				
				if(Look_Script_VACC.onPlatform == false && onCountDown == false)
				{
					turnTimer = 2.0f;
					onCountDown = true;
				}
				
				if(turnTimer <= 2.0f && onCountDown == true)
					turnTimer -= 1.0f * Time.deltaTime;
					
				if(turnTimer <= 1.0f && Look_Script_VACC.onPlatform == false && onCountDown == true)
				{
						// CHOOSE RANDOM DIRECTION (LEFT-RIGHT-BACKWARDS)
						newTurnDirection = Random.Range(1.0f, 1.3f);
						
															/* SLERP FUNCTION TO BE USED POSSIBLY
															public class ExampleClass : MonoBehaviour {
															
																public Transform from;
																public Transform to;
																public float speed = 0.1F;
																void Update() {
																	transform.rotation = Quaternion.Slerp(from.rotation, to.rotation, Time.time * speed);
																}
															}
															public static Quaternion Slerp(Quaternion a, Quaternion b, float t);
															Spherically interpolates between a and b by t. The parameter t is clamped to the range [0, 1].
															*/
						
						if(newTurnDirection >= 1.0 && newTurnDirection < 1.1)
						{
							
							
							transform.Rotate(0, 90, 0, Space.Self);
						}
						if(newTurnDirection >= 1.1 && newTurnDirection < 1.2)
						{
							
														
							transform.Rotate(0, 180, 0, Space.Self);
						}
						if(newTurnDirection >= 1.2 && newTurnDirection <= 1.3)
						{
							
							
							transform.Rotate(0, 270, 0, Space.Self);
						}
					
					// REINITIALIZE THE FORWARD AND DOWNWARD ANGLE LOOKING MECHANISMS
					Look_Script_VACC.straightForward = transform.forward * 10;
					Look_Script_VACC.localDownVector = Look_Script_VACC.spreadAngleDown * Vector3.forward * 10;
					Look_Script_VACC.newVectorDown = transform.TransformDirection(Look_Script_VACC.localDownVector);
					
				}
				
				if(Look_Script_VACC.onPlatform == true && turnTimer <= 0 && onCountDown == true)
					onCountDown = false;
				
				if(Look_Script_VACC.onPlatform == true && turnTimer <= 0 && onCountDown == false)
					transform.position += transform.forward * Time.deltaTime * speed;
				
				// END OF SEQUENCE #1.1 ~~~

	} // end of Update()
	
	void LateUpdate() {
					
	}
	
	
} // end of move_script

/*
	
	if (Input.GetKeyDown(KeyCode.Space)){
			transform.Rotate(0, 90, 0, Space.Self);
			
				// reinitializing straightForward is NECESSARY to keep looking at Local Forward!!!
				Look_Script_VACC.straightForward = transform.forward * 10;
					//Look_Script_VACC.straightForward = transform.TransformDirection(Vector3.forward) * 10; // does the same thing apparently
					
				Look_Script_VACC.localDownVector = Look_Script_VACC.spreadAngleDown * Vector3.forward * 10;
				Look_Script_VACC.newVectorDown = transform.TransformDirection(Look_Script_VACC.localDownVector);
				
				/*		MODERATOR HELP ONLINE:
						Vector3 straightForward = transform.forward; //forward direction vector in global axis.
						Quaternion spreadAngleDown = Quaternion.AngleAxis(15, new Vector3(1,0,0)); 
						Vector3 localDownVector = Vector3.forward * spreadAngleDown;
						Vector3 downVector = transform.TransformDirection(localDownVector); // down direction vector in global axis
 
						if (Physics.Raycast(transform.position, downVector, 10)) 
						{ 
							Debug.DrawRay(transform.position, downVector, Color.green); 
							Debug.DrawRay(transform.position, straightForward, Color.cyan); 
							//Debug.Log("Looking at something :)"); 
							onPlatform = true; 
						}
				
				
				
		}
		
*/

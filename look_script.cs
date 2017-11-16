using UnityEngine;
using System.Collections;

public class look_script : MonoBehaviour {

	// THIS SCRIPT CONTROLS THE 'VISION' OF THE AVATAR AND HOW IT OBSERVES ITS ENVIRONMENT

	move_script Move_Script_VACC;
	
	public bool onPlatform;
	public Vector3 straightForward;
	public Vector3 noAngle;
	public Quaternion spreadAngleDown;
	public Vector3 localDownVector;
	public Vector3 newVectorDown;
	

	void Start () {
	
				
				onPlatform = false;
				Move_Script_VACC = GetComponent<move_script>();			// VACC is "variable access"
				
	
				straightForward = transform.TransformDirection(Vector3.forward) * 10;
				noAngle = straightForward;
				
				spreadAngleDown = Quaternion.AngleAxis(15, new Vector3(1,0,0));
				localDownVector = spreadAngleDown * Vector3.forward * 10;
				
				newVectorDown = transform.TransformDirection(localDownVector);
				//newVectorDown = spreadAngleDown * noAngle;
				
				
				/* http://answers.unity3d.com/questions/525952/how-i-can-converting-a-quaternion-to-a-direction-v.html
				******* You have to do Quaternion * Vector3
				******* Vector3 * Quaternion gives an error
				*/
	}
	

	void Update () {
	
			if (Physics.Raycast(transform.position, newVectorDown, 10))
			{
				Debug.DrawRay(transform.position, newVectorDown, Color.green); 
				Debug.DrawRay(transform.position, straightForward, Color.cyan);
				//Debug.Log("Looking at something :)");
				onPlatform = true;
			}
			if (!Physics.Raycast(transform.position, newVectorDown, 10))
			{		
				Debug.DrawRay(transform.position, newVectorDown, Color.red); 
				Debug.DrawRay(transform.position, straightForward, Color.cyan);
				//Debug.Log("Looking at nothing :(");
				onPlatform = false;
			}
				
		
	
	} // end of Update()
	
	
	void LateUpdate() {
					
	}
	
	
	/* Complete Script for Viewing Angles	
	
		// http://answers.unity3d.com/questions/174344/quaternion-rotation-around-local-axis.html
		
		Vector3 straightForward = transform.TransformDirection(Vector3.forward) * 10;
		Debug.DrawRay(transform.position, straightForward, Color.green);
		
		Vector3 noAngle = straightForward;
		
		// Quaternion off of Y-AXIS looks Left and Right
		Quaternion spreadAngleLeft = Quaternion.AngleAxis(-15, new Vector3(0,1,0));
		Quaternion spreadAngleRight = Quaternion.AngleAxis(15, new Vector3(0,1,0));
		
		// Quaternion off of X-AXIS looks Up and Down
		Quaternion spreadAngleDown = Quaternion.AngleAxis(15, new Vector3(1,0,0));
		Quaternion spreadAngleUp = Quaternion.AngleAxis(-15, new Vector3(1,0,0));
		
		// Create new Vector with direction * rotation factor
		Vector3 newVectorLeft = spreadAngleLeft * noAngle;
		Vector3 newVectorRight = spreadAngleRight * noAngle;
		Vector3 newVectorDown = spreadAngleDown * noAngle;
		Vector3 newVectorUp = spreadAngleUp * noAngle;
		
		Debug.DrawRay(transform.position, newVectorLeft, Color.red);
		Debug.DrawRay(transform.position, newVectorRight, Color.blue);
		Debug.DrawRay(transform.position, newVectorDown, Color.yellow);
		Debug.DrawRay(transform.position, newVectorUp, Color.cyan);

	*/

} // end of look_script

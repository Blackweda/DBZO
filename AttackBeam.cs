using UnityEngine;
using System.Collections;
// http://forum.unity3d.com/threads/transform-lookat-or-quaternion-lookrotation-on-1-axis-only.36377/
/*
You want the collision-detection set to Continuous Dynamic for fast moving objects.  Continuous is used for static- or slow-moving objects that Continuous Dynamic objects collide with. See here.

(Keep in mind that continuous collision detection is expensive, so use it sparingly)
*/

public class AttackBeam : MonoBehaviour {

	public Transform target;
	public float force;
	public float speed = 0;
	
		
	// Use this for initialization
	void Start () {
		
		
	}
	
	void FixedUpdate() {
		
		//transform.rotation = Quaternion.Inverse(target.rotation);
		transform.LookAt(target);
		Debug.DrawLine(target.position, transform.position, Color.yellow);
		transform.position = Vector3.MoveTowards(transform.position, target.position, speed * Time.deltaTime);
		if(Input.GetButtonDown("3")){
			speed += 1;
			// rigidbody.AddForce(Vector3.forward * force);		// AddForce will apply force in WORLD SPACE, NOT LOCAL!!
			//rigidbody.AddRelativeForce(Vector3.forward * force);
			//rigidbody.AddRelativeForce(-transform.forward * force);
		}
			
    }
	
	// Update is called once per frame
	void Update () {
				
		//transform.rotation = Quaternion.Inverse(target.rotation);
		transform.LookAt(target);
		Debug.DrawLine(target.position, transform.position, Color.yellow);
		transform.position = Vector3.MoveTowards(transform.position, target.position, speed * Time.deltaTime);
		if(Input.GetButtonDown("3")){
			speed += 1;
			// rigidbody.AddForce(Vector3.forward * force);		// AddForce will apply force in WORLD SPACE, NOT LOCAL!!
			//rigidbody.AddRelativeForce(Vector3.forward * force);
		}
			
	}
}

using UnityEngine;
using System.Collections;

/*
The Attack Ball must be a rigidbody, yet UseGravity = false, and isKinematic = false
We do not want the ball being pulled down by gravity nor do we want the ball bouncing off objects, it should explode on impact of any
thing that is not another energy ball or beam.
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
		if(Input.GetButtonDown("1")){
			speed += 1;
			// rigidbody.AddForce(Vector3.forward * force);		// AddForce will apply force in WORLD SPACE, NOT LOCAL!!
			//rigidbody.AddRelativeForce(Vector3.forward * force);
			//rigidbody.AddRelativeForce(-transform.forward * force);
		}
		if(Input.GetButtonDown("2")){
			speed += 0.25f;
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
		if(Input.GetButtonDown("1")){
			speed += 1;
			// rigidbody.AddForce(Vector3.forward * force);		// AddForce will apply force in WORLD SPACE, NOT LOCAL!!
			//rigidbody.AddRelativeForce(Vector3.forward * force);
		}
		if(Input.GetButtonDown("2")){
			speed += 0.25f;
			// rigidbody.AddForce(Vector3.forward * force);		// AddForce will apply force in WORLD SPACE, NOT LOCAL!!
			//rigidbody.AddRelativeForce(Vector3.forward * force);
			//rigidbody.AddRelativeForce(-transform.forward * force);
		}
			
	}
}


/*

Currently this script works pretty well. What needs to be fixed is when the power of a ball is increased, the size of the ball
should change and the distance from the other ball be adjusted so that they don't start to occupy the same space.

*/

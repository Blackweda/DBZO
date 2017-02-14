using UnityEngine;
using System.Collections;

public class PulsatingForce : MonoBehaviour {
	// http://youtu.be/ncJZpjG3Ck8
	// http://answers.unity3d.com/questions/185338/make-objects-push-away-from-each-other.html
	public string buttonName = "1";
	public int Ki = 20000;
	public string buttonName2 = "Fire2";
	public float radius = 5.0f;
    public float force;
	public float upwardsModifier = 0.0f;		// the force that knocks objects upwards into air from force
	public ForceMode forceMode;	
	
	// Vector3 variables remmeber xyz positions
	// Quaternion variables remember xyz rotations
	// Time.deltaTime refers to an action of value 1 happening in one second. Time.deltaTime * 10 means multiply by 10 in one second.
		
	//public AudioClip aura;
	
	// Input.GetButtonDown <-- will look for the button down once
	// Input.GetButton <-- will look for the button down every frame that it finds it down
		
	/*
	void FixedUpdate()
 {
     rb.AddForce(-Physics.gravity);
 }
	*/	
	void Start () {
	
		radius = 20.0f;
		force = 0.0f;
	}
	
	
	void FixedUpdate () {
	
		if(Input.GetKeyDown(KeyCode.F)){		// this button will increase power slightly
			//PlayClipAtPoint(aura, transform.transform, 1.0F);	
		
			force += 1f;
			//upwardsModifier += 5.0f;
		}	
			foreach(Collider col in Physics.OverlapSphere(transform.position, radius)){ // Returns an array with all colliders touching or inside the sphere.
			
				if(col.rigidbody != null){
					if(col.rigidbody.tag != "Player")
						col.rigidbody.AddExplosionForce(force, transform.position, radius, upwardsModifier, forceMode);
				}
			}
		
		//if(Input.GetButtonDown(buttonName)){
		
		//	rigidbody.AddForce(0, 200, 0);
		//}
		/*if(Input.GetButtonDown(buttonName)){
		
			rigidbody.AddForce(0, 100, 0);
			Ki -= 100;
			Debug.Log(Physics.gravity);
		}
		*/
		//rigidbody.AddForce(0, 106, 0);
		//rigidbody.AddForce(0, -100, 0);
		
		// CODE TO GET AN OBJECT/CHARACTER TO FLOAT IN PLACE AGAINST GRAVITY
		
		
		
		
	}
	
	
}

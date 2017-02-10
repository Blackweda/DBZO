using UnityEngine;
using System.Collections;

public class kiSense : MonoBehaviour {

	/*
	This collider should have no rigidbody, and be 'is trigger' = true;
	
	
	*/

	public SphereCollider kiSenseTriggerCollider;
	public float powerLevelKi;			// data from the parent gameObject will update the power level ki being used to change the kiCollider radius
	public uint i;

	// Use this for initialization
	void Start () {
	
		kiSenseTriggerCollider = gameObject.GetComponent<SphereCollider>();
		kiSenseTriggerCollider.radius = 5;
		powerLevelKi = 15;
		i = 0;
	}
	
	// Update is called once per frame
	void Update () {
	
		if(kiSense == true){
		
			if(powerLevelKi > 0){
				i = (uint)powerLevelKi / 2;
				kiSenseTriggerCollider.radius = i;
			}
		
		
		
		}
	
	}
	
	/*
	void OnCollisionStay(Collision collision) {
        foreach (ContactPoint contact in collision.contacts) {
			Debug.Log(contact.thisCollider.name + " hit " + contact.otherCollider.name);
            print(contact.thisCollider.name + " hit " + contact.otherCollider.name);
            Debug.DrawRay(contact.point, contact.normal, Color.white);
        }
    }
	*/
	void OnCollisionEnter(Collision collision) {				// function used to initiate a zap animation when sensing a NEW ki force
        foreach (ContactPoint contact in collision.contacts) {
            //Debug.DrawRay(contact.point, contact.normal, Color.red);
			Debug.Log("Collided with : " + contact.otherCollider.name);
        }
        if (collision.relativeVelocity.magnitude > 2){
            //audio.Play();
		}
        
    }
	/*
	void OnTriggerStay(Collider other) {
        if (other.attachedRigidbody)
            other.attachedRigidbody.AddForce(Vector3.up * 10);
        
    }
	*/
}

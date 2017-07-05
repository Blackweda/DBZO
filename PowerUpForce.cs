using UnityEngine;
using System.Collections;

public class PowerUpForce : MonoBehaviour {
	
	public float radius = 5.0f;
    public float force;
	public float upwardsModifier = 0.0f;		// the force that knocks objects upwards into air from force
	public ForceMode forceMode;	
	public float movieTimer;
	public AudioSource ss3Theme;
	public string forceValue = "Force:";
	public string stringToEdit;
	public Camera cam1;
	public Camera cam2;
	public Camera cam3;

	// Use this for initialization
	void Start () {
		
		radius = 0.0f;
		force = 0.0f;
		movieTimer = 0;
		ss3Theme = GetComponent<AudioSource>();
		cam1 = GameObject.Find("Main Camera").GetComponent<Camera>();
		cam2 = GameObject.Find("Main Camera2").GetComponent<Camera>();
		cam3 = GameObject.Find("Main Camera3").GetComponent<Camera>();
		
		cam1.enabled = true;
		cam2.enabled = false;
		cam3.enabled = false;
	
	}
	
	// Update is called once per frame
	void Update () {
	
		//movieTimer += Time.deltaTime * 1;		// Timer goes up 1 per second
		radius = force / 100.0f;
	}
	
	void FixedUpdate () {
	
	movieTimer += Time.deltaTime * 1;		// Timer goes up 1 per second
		// START MUSIC
		if(movieTimer == 5){
			
			ss3Theme.Play();
		}
		
		if(movieTimer >= 6 && movieTimer <= 12){		
			force = 500f;
			upwardsModifier = 5.0f;
		}
		if(movieTimer >= 13 && movieTimer <= 64){		
			force = 1500f;
			upwardsModifier = 5.0f;
		}
		
		// START OF POWER UP INTO SS3
		//if(movieTimer >= 60 && movieTimer <= 70){		
			//force += 50f;
		//	upwardsModifier = 5.0f;
		//}
		// A SPIKE IS FELT BY THE Z WARRIORS
		if(movieTimer >= 60 && movieTimer <= 92){			
			force += 75f;
			upwardsModifier = 50.0f;
		}
		// SPIRALS ARE FORMING IN THE OCEAN
		if(movieTimer >= 92 && movieTimer <= 105){		
			force += 150f;
			upwardsModifier = 50.0f;
		}
		// CLOUDS ARE MOVING TOWARDS GOKU
		if(movieTimer >= 105 && movieTimer <= 112){		
			force += 200f;
			upwardsModifier = 50.0f;
		}
		// THE GROUND IS SHAKING EVERYWHERE
		if(movieTimer >= 112 && movieTimer <= 155){
			force += 500f;
			upwardsModifier = 50.0f;
			cam1.enabled = false;
			cam2.enabled = true;
			cam3.enabled = false;
		}
		// SERIOUS TIDAL WAVES AND SHAKING WORLDWIDE
		if(movieTimer >= 155 && movieTimer <= 192){		
			force += 800f;
			upwardsModifier = 50.0f;
		}
		// BUILDINGS ARE FALLING APART
		if(movieTimer >= 192 && movieTimer <= 260){		
			force += 1500f;
			upwardsModifier = 50.0f;
			cam1.enabled = false;
			cam2.enabled = false;
			cam3.enabled = true;
		}
		// LAST FINAL BURST OF POWER TO COMPLETE TRANSFORMATION
		if(movieTimer >= 260 && movieTimer <= 270){		
			force += 3000f;
			upwardsModifier = 50.0f;
		}
		if(movieTimer > 270){		
			force = 5000.0f;
			upwardsModifier = 5.0f;
		}
		
		
		foreach(Collider col in Physics.OverlapSphere(transform.position, radius)){ // Returns an array with all colliders touching or inside the sphere.
			
			if(col.GetComponent<Rigidbody>() != null){
				if(col.GetComponent<Rigidbody>().tag != "Player")
					col.GetComponent<Rigidbody>().AddExplosionForce(force, transform.position, radius, upwardsModifier, forceMode);
			}
		}
	}
	
	void OnGUI(){
			forceValue = force.ToString();
			stringToEdit = GUI.TextField(new Rect(10, 10, 100, 20), forceValue, 25);
	}
	
}

using UnityEngine;
using System.Collections;
using UnityEngine.UI; // Required when Using UI elements.

public class CreatePlayerData : MonoBehaviour {

	public string fname;
	public string lname;
	public int age;
	
	// Use this for initialization
	void Start () {
	
	
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	void OnGUI(){
	

	
	}
	
	string firstNameRecord(string firstName){
	
	fname = firstName;
	return firstName;
	}
}

/* Canvas Details

Canvas Render Mode: World Space
Canvas Position: (0,0,0)
Width: 700
Height: 350

Main Camera
-----------

Position: (0, 0, -350) <--- same as Canvas Height

SO FAR LOOKS GREAT

*/

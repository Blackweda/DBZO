using UnityEngine;
using System.Collections;
using System;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;

public class MultiplyBehaviour : MonoBehaviour {

	public static MultiplyBehaviour mb;

	public int timeAlive;			// a counter for how long the object has been alive (while application is running)
	public double secondsCounter;	// a tracker for seconds per minute
	public float scale;
	
	
	void Start () {
	//	if(scale < 1.0f)
	//		scale = 1.0f;

	//	transform.localScale = new Vector3(scale, scale, scale);
	}
	
	void Update () {
	
	// Counter Controls
				if(secondsCounter >= 60){
					timeAlive++;
					secondsCounter = 0.0f;
				}
				secondsCounter += 1.0f * Time.deltaTime;
				
				if(secondsCounter >= 58){
					transform.localScale += new Vector3(1.0f, 1.0f, 1.0f) * Time.deltaTime;
					scale += 0.01f;
				}
	}
	
	public void Save()
	{
		BinaryFormatter bf = new BinaryFormatter();
		FileStream file = File.Create(Application.persistentDataPath + "/first_test.dat");
		
		HoldMyData hmd = new HoldMyData();
		hmd.count = timeAlive;
		hmd.seconds = secondsCounter;
		hmd.scale = scale;
		
		bf.Serialize(file, hmd);
		file.Close();
	}
	
	void OnGUI()
	{
		if(GUI.Button(new Rect(10, 120,100,30), "SAVE"))
		{
			Save();
			Application.Quit();
		}
		GUI.Label(new Rect(10, 40, 200, 30), "SecondCount: " + secondsCounter);
		GUI.Label(new Rect(10, 80, 200, 30), "Minutes: " + timeAlive);
	}
	
	
}

[Serializable]
class HoldMyData {

public int count;
public double seconds;
public float scale;
}

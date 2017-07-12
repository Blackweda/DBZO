using UnityEngine;
using System.Collections;
using System;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;

public class TitleScreen : MonoBehaviour {

	MultiplyBehaviour mb;

	void Start () 
	{
			mb = GetComponent<MultiplyBehaviour>();
	}
		
	public void Load()
	{
		if(File.Exists(Application.persistentDataPath + "/first_test.dat"))
		{
			BinaryFormatter bf = new BinaryFormatter();
			FileStream file = File.Open(Application.persistentDataPath + "/first_test.dat", FileMode.Open);
			HoldMyData hmd = (HoldMyData)bf.Deserialize(file);
			file.Close();
			
			mb.timeAlive = hmd.count;
			mb.secondsCounter = hmd.seconds;
		}
		//else
		//{
		//Application.LoadLevel ("SceneMain")
		//}
	}
	
	void OnGUI()
	{
		if(GUI.Button(new Rect(10, 100,100,30), "LOAD"))
		{			
			
			Load();
			//Application.LoadLevel ("SceneMain");
		}
	}
}

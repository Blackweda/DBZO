using UnityEngine;
using System.Collections;

public class EngineBoost : MonoBehaviour {
	
	public Rigidbody rb;

	// Use this for initialization
	void Start () {
	
		rb = GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	void Update () {
	
		rb.AddForce(transform.up * 1);
	}
}

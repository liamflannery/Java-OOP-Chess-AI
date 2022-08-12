using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AgentController : MonoBehaviour
{
    public int DayLength = 5000;
    List<GameObject> agents = new List<GameObject>();
    public GameObject baseAgent;

    // Start is called before the first frame update
    void Start()
    {
        Instantiate(baseAgent);
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}

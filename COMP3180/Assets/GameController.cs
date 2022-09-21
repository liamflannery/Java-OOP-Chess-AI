using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameController : MonoBehaviour
{
    // Start is called before the first frame update
    public GameObject food;
    public GameObject plane;
    public GameObject agent;
    public int startingAgents;
    public float foodSpawnRate;
    public int initialFood;
    public float timeScale = 0f;
    private float fixedDeltaTime;
    public int CurrentAgentCount;
    private ClickHandler agentUI;
    private Camera camera;
    
    Vector3 randomPosition = new Vector3();
    void Start()
    {
        camera = Camera.main;
        this.fixedDeltaTime = Time.fixedDeltaTime;
        for (int i = 0; i < startingAgents; i++)
        {
            randomPosition = GetARandomPos(plane);
            Instantiate(agent, randomPosition, Quaternion.identity);
        }
        for (int j = 0; j < initialFood; j++)
        {
            randomPosition = GetARandomPos(plane);
            Instantiate(food, randomPosition, Quaternion.identity);
        }
        agentUI = GameObject.Find("AgentUI").GetComponent<ClickHandler>();
    }

    // Update is called once per frame
    float timeElapsed = 0f;

    void Update()
    {
        
        Time.timeScale = timeScale;
        Time.fixedDeltaTime = this.fixedDeltaTime * Time.timeScale;
        timeElapsed += Time.deltaTime * foodSpawnRate;

        CurrentAgentCount = GameObject.FindGameObjectsWithTag("Agent").Length;
    //    if (CurrentAgentCount == 1)
    //    {
    //        timeScale = 0;
    //    }
        if (timeElapsed >= 1)
        {
            randomPosition = GetARandomPos(plane);
            Instantiate(food, randomPosition, Quaternion.identity);
            timeElapsed = timeElapsed % 1f;
        }
    }

    GameObject currentAgent;
    void OnGUI()
    {
        Vector3 point = new Vector3();
        Event   currentEvent = Event.current;
        Vector2 mousePos = new Vector2();
        
     
       
        mousePos.x = currentEvent.mousePosition.x;
        mousePos.y = camera.pixelHeight - currentEvent.mousePosition.y;

        point = camera.ScreenToWorldPoint(new Vector3(mousePos.x, mousePos.y, camera.nearClipPlane + 3));

        if(agentUI.getClicked()){
            currentAgent = Instantiate(agent);
            agentUI.resetClicked();
        // agent.transform.position;
        }
        if(currentAgent){
            currentAgent.transform.position = point;
        }
    }

    public Vector3 GetARandomPos(GameObject plane)
    {

        Mesh planeMesh = plane.GetComponent<MeshFilter>().mesh;
        Bounds bounds = planeMesh.bounds;

        float minX = plane.transform.position.x - plane.transform.localScale.x * bounds.size.x * 0.4f;
        float minZ = plane.transform.position.z - plane.transform.localScale.z * bounds.size.z * 0.3f;

        Vector3 newVec = new Vector3(Random.Range(minX, -minX),
                                     plane.transform.position.y + 2,
                                     Random.Range(minZ, -minZ));
        return newVec;
    }
}
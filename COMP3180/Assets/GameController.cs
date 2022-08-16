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
    Vector3 randomPosition = new Vector3();
    void Start()
    {
        for (int i = 0; i < startingAgents; i++){
            randomPosition = GetARandomPos(plane);
            Instantiate(agent, randomPosition, Quaternion.identity);
        }
        for(int j = 0; j < initialFood; j++)
        {
            randomPosition = GetARandomPos(plane);
            Instantiate(food, randomPosition, Quaternion.identity);
        }
    }

    // Update is called once per frame
    float timeElapsed = 0f;
    
    void Update()
    {
        timeElapsed += Time.deltaTime;
        if (timeElapsed >= foodSpawnRate)
        {
            randomPosition = GetARandomPos(plane);
            Instantiate(food, randomPosition, Quaternion.identity);
            timeElapsed = timeElapsed % 1f;
        }
    }

    public Vector3 GetARandomPos(GameObject plane)
    {

        Mesh planeMesh = plane.GetComponent<MeshFilter>().mesh;
        Bounds bounds = planeMesh.bounds;

        float minX = plane.transform.position.x - plane.transform.localScale.x * bounds.size.x * 0.9f;
        float minZ = plane.transform.position.z - plane.transform.localScale.z * bounds.size.z * 0.9f;

        Vector3 newVec = new Vector3(Random.Range(minX, -minX),
                                     plane.transform.position.y + 4,
                                     Random.Range(minZ, -minZ));
        return newVec;
    }
}

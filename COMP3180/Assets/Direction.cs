using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Direction
{
    public Vector3 direction;
    public int weight;
    public int index;
   
    private float visionLength;
    private AgentBehaviour agentBehaviour;
    public Direction(Vector3 dir, int index, float inVisionLength, AgentBehaviour inAgentBehaviour)
    {
        this.direction = dir;
        this.index = index;
        this.weight = 1;
        this.visionLength = inVisionLength;
        this.agentBehaviour = inAgentBehaviour;
    }

    public int getIndex()
    {
        return index;
    }

    public Vector3 getDir()
    {
        return direction;
    }
    public int getWeight()
    {
        return weight;
    }

    public void updateWeight(int newWeight)
    {
        weight = newWeight;
        agentBehaviour.updateWeight(index, weight);
    }
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    public void CalledUpDate(Vector3 playerPos)
    {
        RaycastHit hit;
        if (Physics.Raycast(playerPos, direction, out hit, visionLength))
        {
            if(hit.transform.tag == "Wall")
            {
                updateWeight(0);
            }

            if (hit.transform.tag == "Food")
            {
                updateWeight(10);
            }

        }
        else
        {
            updateWeight(1);
        }
            Debug.DrawRay(playerPos, direction * visionLength, Color.yellow);
    }
}

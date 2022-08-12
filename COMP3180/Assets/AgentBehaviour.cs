using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AgentBehaviour : MonoBehaviour
{
     
    public static int initial_decisiveness = 500;
    // ATTRIBUTES
    public int visionLength = 10;
    public int decisiveness = initial_decisiveness;



    
    
    private Direction dir1, dir2, dir3, dir4, dir5, dir6, dir7, dir8;
    Direction currentDir;
    List<Direction> directions = new List<Direction>();
    List<int> weights = new List<int>();


    // Start is called before the first frame update
    void Start()
    {
        dir1 = new Direction(new Vector3(1, 0, 0), 0, visionLength, this);
        dir2 = new Direction(new Vector3(1, 0, 1), 1, visionLength, this);
        dir3 = new Direction(new Vector3(1, 0, -1), 2, visionLength, this);
        dir4 = new Direction(new Vector3(-1, 0, 0), 3, visionLength, this);
        dir5 = new Direction(new Vector3(-1, 0, 1), 4, visionLength, this);
        dir6 = new Direction(new Vector3(-1, 0, -1), 5, visionLength, this);
        dir7 = new Direction(new Vector3(0, 0, 1), 6, visionLength, this);
        dir8 = new Direction(new Vector3(0, 0, -1), 7, visionLength, this);
      

        directions.Add(dir1);
        directions.Add(dir2);
        directions.Add(dir3);
        directions.Add(dir4);
        directions.Add(dir5);
        directions.Add(dir6);
        //directions.Add(dir7);
        //directions.Add(dir8);

        foreach (Direction d in directions)
        {
            weights.Insert(d.getIndex(), d.getWeight());
        }

        currentDir = directions[Random.Range(0, directions.Count)];
    }

    // Update is called once per frame
    void Update()
    {
        foreach(Direction d in directions)
        {
            d.CalledUpDate(transform.position);
        }
        if(decisiveness == 0)
        {
            currentDir = pickDirection();
            decisiveness = initial_decisiveness;
        }
        decisiveness -= 1;
        
        Debug.DrawRay(transform.position, transform.TransformDirection(currentDir.getDir()) * visionLength, Color.red);
        transform.Translate(currentDir.getDir() * Time.deltaTime);



    }

    public void updateWeight(int index, int newWeight)
    {
        weights.Insert(index, newWeight);
    }

    public Direction pickDirection()
    {
        List<int> distWeights = new List<int>();
        int currentWeight = 0;
        foreach(Direction d in directions)
        {
            currentWeight = d.getWeight();
            for(int i = 0; i <= currentWeight; i++)
            {
                distWeights.Add(d.getIndex());
            }
        }
        Debug.Log(distWeights.Count);
        int selectIndex = distWeights[Random.Range(0, distWeights.Count - 1)];
        
        return directions[selectIndex];
    }
}

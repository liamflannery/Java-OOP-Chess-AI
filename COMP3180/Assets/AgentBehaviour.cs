using System.Collections;
using System.Collections.Generic;
using UnityEngine;
[System.Serializable]
public class AgentBehaviour : MonoBehaviour
{
     
    public float initial_decisiveness = 5;
   
    // ATTRIBUTES
    public float visionLength = 10;
    public float decisiveness;
    public float energy;
    public int dirCount;
    public float speed = 5;


    Direction currentDir;
    List<Direction> directions = new List<Direction>();
    List<int> weights = new List<int>();

  
  
    // Start is called before the first frame update
    void Start()
    {
        speed = Mathf.Abs(speed + Random.Range(-1.0f, 1.0f));
        initial_decisiveness = Mathf.Abs(initial_decisiveness + Random.Range(-1.0f, 1.0f));
        visionLength = Mathf.Abs(visionLength + Random.Range(-1.0f, 1.0f));
        decisiveness = initial_decisiveness;
        dirCount = dirCount + Random.Range(-2, 2);
        if(dirCount <= 1)
        {
            dirCount = 1;
        }
        float scale = Random.Range(0.9f, 1.1f);
        gameObject.transform.localScale = gameObject.transform.localScale * scale;
        gameObject.GetComponent<Rigidbody>().mass *= scale;

        Vector3 directionValue = new Vector3(1, 0, 0);
        int rotateDegrees = 360 / dirCount;
        
        for (int i = 0; i < dirCount; i++)
        {
            directions.Add(new Direction(directionValue, i, visionLength, this));
            directionValue = Quaternion.Euler(0, rotateDegrees, 0) * directionValue;
        }
     
        

        foreach (Direction d in directions)
        {
            weights.Insert(d.getIndex(), d.getWeight());
        }

        currentDir = directions[Random.Range(0, directions.Count)];
    }

    // Update is called once per frame
    float timeElapsed = 0f;
    void Update()
    {
        foreach(Direction d in directions)
        {
            d.CalledUpDate(transform.position);
        }

        if(energy <= 0)
        {
            Destroy(gameObject);
        }
        timeElapsed += Time.deltaTime;
        if(timeElapsed >= 1f)
        {
            energy -= 1 * gameObject.transform.localScale.x; ;
            timeElapsed = timeElapsed % 1f;
            decisiveness -= 1;
        }
       
        if(decisiveness <= 0)
        {
            currentDir = pickDirection();
            decisiveness = initial_decisiveness;
        }
        

        
        
        Debug.DrawRay(transform.position, transform.TransformDirection(currentDir.getDir()) * visionLength, Color.red);
        transform.Translate(currentDir.getDir() * Time.deltaTime * speed * 0.5f);



    }

    void OnCollisionEnter(Collision collision)
    {
        if(collision.gameObject.tag == "Food")
        {
            energy += 10;
            Destroy(collision.gameObject);
            Instantiate(gameObject);
        } 
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

        int selectIndex = distWeights[Random.Range(0, distWeights.Count - 1)];
        
        return directions[selectIndex];
    }
}

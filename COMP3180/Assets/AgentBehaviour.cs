using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;
[System.Serializable]
public class AgentBehaviour : MonoBehaviour
{


    // ATTRIBUTES
    [SerializeField]
    private float visionLength = 10;
    [SerializeField]
    public float energy = 20;
    [SerializeField]
    public float speed = 5;
    bool foundTarget = false;
    [SerializeField]
    bool prey;
    NavMeshAgent navMeshAgent;
  
  
    // Start is called before the first frame update
    void Start()
    {

        energy = 20;

        speed = Mathf.Abs(speed + Random.Range(-1.0f, 1.0f));
        visionLength = Mathf.Abs(visionLength + Random.Range(-1.0f, 1.0f));
    
        float scale = Random.Range(0.9f, 1.1f);
        gameObject.transform.localScale = gameObject.transform.localScale * scale;
        gameObject.GetComponent<Rigidbody>().mass *= scale;
        navMeshAgent = GetComponent<NavMeshAgent>();
        if (gameObject.transform.localScale.x > 3)
        {
            prey = false;
        }
        if (prey)
        {
            gameObject.GetComponent<Renderer>().material.color = Color.blue ;
        }
        else
        {
            gameObject.GetComponent<Renderer>().material.color = Color.red;
        }
    }

    // Update is called once per frame
    float timeElapsed = 0f;
    Collider[] hitColliders;
    public Vector3 target;
    Vector3 currentTarget;
    void Update()
    {
        

        hitColliders = Physics.OverlapSphere(transform.position, visionLength);

        if(foundTarget == false)
        {
            Looking();
        }
        if(Vector3.Distance(transform.position, target) < 1)
        {
            foundTarget = false;
        }
        
        if(currentTarget != target)
        {
            navMeshAgent.SetDestination(target);
            currentTarget = target;
        }
        var step = speed * Time.deltaTime;

        //transform.position = Vector3.MoveTowards(transform.position, target, step);
        
        
        


        if(energy <= 0)
        {
            Destroy(gameObject);
        }
        timeElapsed += Time.deltaTime;
        if(timeElapsed >= 1f)
        {
            energy -= 1 * gameObject.transform.localScale.x; ;
            timeElapsed = timeElapsed % 1f;
        }

        if(energy > 25)
        {
            energy -= 5;
            Instantiate(gameObject, GetARandomPos(), transform.rotation);
        }
       
     




    }

    void OnCollisionEnter(Collision collision)
    {
        if(collision.gameObject.tag == "Food")
        {
            energy += 10;
            foundTarget = false;
            Destroy(collision.gameObject);
            
        } 
        if(collision.gameObject.tag == "Wall")
        {
            target = GetARandomPos();
        }
    
    }

    void OnCollisionStay(Collision collision)
    {
        if ((collision.gameObject.tag == "Agent") && (collision.gameObject != this.gameObject) && (collision.gameObject.transform.localScale.x < transform.localScale.x) && (prey = false))
        {
            Debug.Log("ATTACK");
            energy += 20;
            Destroy(collision.gameObject);

        }
        
    }

    

    
    void Looking()
    {
        Collider currentNearest = null;
        float closestDistance = 100;
        float currentDistance;

        foreach (Collider collider in hitColliders)
        {
            if(collider.gameObject != this.gameObject)
            {
                if (collider.gameObject.tag == "Food" || (prey == false && collider.gameObject.tag == "Agent" && collider.gameObject.transform.localScale.x <= 2))
                {
                    currentDistance = Vector3.Distance(transform.position, collider.gameObject.transform.position);
                    if (currentDistance < closestDistance)
                    {
                        currentNearest = collider;
                        closestDistance = currentDistance;
                    }
                }
            }
           
        }
        if (currentNearest)
        {
            foundTarget = true;
            target = currentNearest.gameObject.transform.position;
        }
        else if(transform.position == target || target == Vector3.zero)
        {
            target = GetARandomPos();
        }
       
    }


    public Vector3 GetARandomPos()
    {
        GameObject plane = GameObject.Find("Plane");
        Mesh planeMesh = plane.GetComponent<MeshFilter>().mesh;
        Bounds bounds = planeMesh.bounds;

        float minX = plane.transform.position.x - plane.transform.localScale.x * bounds.size.x * 0.5f;
        float minZ = plane.transform.position.z - plane.transform.localScale.z * bounds.size.z * 0.5f;

        Vector3 newVec = new Vector3(Random.Range(minX, -minX),
                                     plane.transform.position.y + 2,
                                     Random.Range(minZ, -minZ));
        return newVec;
    }






}

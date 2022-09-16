using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;
[System.Serializable]
public class AgentBehaviour : MonoBehaviour
{


    // ATTRIBUTES
    [SerializeField]
    private float visionLength = 5;
    [SerializeField]
    public float energy = 10;
    [SerializeField]
    public float speed = 5;
    bool foundTarget = false;
    [SerializeField]
    bool prey;
    NavMeshAgent navMeshAgent;
    LayerMask mask;


    // Start is called before the first frame update
    void Start()
    {
        RandomiseGenes();
        
        mask = LayerMask.GetMask("Food");
    }

    // Update is called once per frame
    float timeElapsed = 0f;
    Collider[] hitColliders;
    /*navMeshAgent.SetDestination(target);
            currentTarget = target;*/
    
  
    void Update()
    {


        HandleEnergy();

        HandleMovement();
        //Patrol();
        //hitColliders = Physics.OverlapSphere(transform.position, visionLength);


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
            //target = GetARandomPos();
        }
    
    }

    void OnCollisionStay(Collision collision)
    {
        /*if ((collision.gameObject.tag == "Agent") && (collision.gameObject != this.gameObject) && (collision.gameObject.transform.localScale.x < transform.localScale.x) && (prey = false))
        {
            Debug.Log("ATTACK");
            energy += 20;
            Destroy(collision.gameObject);

        }*/
        
    }

    

    
    /*void Looking()
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
       
    }*/

    void RandomiseGenes()
    {
        energy = 20;

        speed = Mathf.Abs(speed + Random.Range(-1.0f, 1.0f));
        visionLength = Mathf.Abs(visionLength + Random.Range(-1.0f, 1.0f));

        float scale = Random.Range(0.9f, 1.1f);
        gameObject.transform.localScale = gameObject.transform.localScale * scale;
        gameObject.GetComponent<Rigidbody>().mass *= scale;
        navMeshAgent = this.gameObject.GetComponent<NavMeshAgent>();
        
        navMeshAgent.speed = speed;
        
        
    }
    void HandleEnergy()
    {
        if (energy <= 0)
        {
            Destroy(gameObject);
        }
        timeElapsed += Time.deltaTime;
        if (timeElapsed >= 1f)
        {
            energy -= 1.2f * gameObject.transform.localScale.x; ;
            timeElapsed = timeElapsed % 1f;
        }

        if (energy > 25)
        {
            energy -= 5;
            Instantiate(gameObject, GetARandomPos(), transform.rotation);
        }
    }

    GameObject actualTarget;
    Vector3 randomPos;
    GameObject target;
    void HandleMovement()
    {
        if (target != null)
        {
            navMeshAgent.SetDestination(target.transform.position);
        }
        else
        {
            if (GetComponent<Rigidbody>().velocity.magnitude == 0)
            {
                navMeshAgent.SetDestination(GetARandomPos());
                
            }
            Patrol();
        }
        
        
        
    }
    void Patrol()
    {
        hitColliders = Physics.OverlapSphere(transform.position, visionLength);
        float closestDistance = Mathf.Infinity;
        GameObject closestObject = null;
        float currentDistance;
        
        foreach (Collider collider in hitColliders)
        {
            if (collider.gameObject.tag == "Food")
            {
                currentDistance = Vector3.Distance(collider.gameObject.transform.position, transform.position);
                if (currentDistance < closestDistance)
                {
                    closestDistance = currentDistance;
                    closestObject = collider.gameObject;
                }
            }
        }
        Debug.Log(target);
        target = closestObject;
        

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

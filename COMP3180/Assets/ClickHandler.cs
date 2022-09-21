using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class ClickHandler : MonoBehaviour, IPointerClickHandler
{
    public bool clicked; 
    public void OnPointerClick(PointerEventData pointerEventData){
        clicked = true; 
    }
    public bool getClicked(){
        return clicked;
    }
    public void resetClicked(){
        clicked = false;
    }
   
}

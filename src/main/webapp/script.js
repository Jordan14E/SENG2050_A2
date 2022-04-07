function roundValidation(round) {

    //if up to round 2 and input 1 and 2 are the same alert the user
     if(round === 2 && document.getElementById("first").value === document.getElementById("second").value){
         alert("Invalid input:\nPlease do not enter duplicate numbers");
         document.getElementById("first").focus();
         return false;
     }
     //if up to round 3 and any duplicates, alert the user
     else if(round ===3 && (document.getElementById("first").value === document.getElementById("second").value || document.getElementById("third").value === document.getElementById("second").value || document.getElementById("first").value === document.getElementById("third").value)){
         alert("Invalid input:\nPlease do not enter duplicate numbers");
         return false;
     }
     //if up to round 4 and there are duplicates, alert the user
     else if(round === 4 && (document.getElementById("first").value === document.getElementById("second").value || document.getElementById("first").value === document.getElementById("third").value || document.getElementById("first").value === document.getElementById("fourth").value || document.getElementById("second").value === document.getElementById("third").value || document.getElementById("second").value === document.getElementById("fourth").value || document.getElementById("third").value === document.getElementById("fourth").value)){
         alert("Invalid input:\nPlease do not enter duplicate numbers");
         return false;
     }
     //if there are any empty field in any submission, alert the user
     else if((round === 1 && document.getElementById("first").value === "") || (round === 2 && (document.getElementById("first").value === "" || document.getElementById("second").value === "")) || (round  === 3 && (document.getElementById("first").value === "" || document.getElementById("second").value === "" || document.getElementById("third").value === "")) || (round === 4 && (document.getElementById("first").value === "" || document.getElementById("second").value === "" || document.getElementById("third").value === "" || document.getElementById("fourth").value === ""))){
        alert("Invalid input:\nPlease enter a number in all fields");
        return false;
     }
     else{
         return true;
     }
}
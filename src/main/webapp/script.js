function roundValidation(round) {

 if(round === 2 && document.getElementById("first").value === document.getElementById("second").value){
     alert("Invalid input:\nPlease do not enter duplicate numbers");
     document.getElementById("first").focus();
     return false;
 }
 else if(round ===3 && (document.getElementById("first").value === document.getElementById("second").value || document.getElementById("third").value === document.getElementById("second").value || document.getElementById("first").value === document.getElementById("third").value)){
     alert("Invalid input:\nPlease do not enter duplicate numbers");
     return false;
 }
 else if(round === 4 && (document.getElementById("first").value === document.getElementById("second").value || document.getElementById("first").value === document.getElementById("third").value || document.getElementById("first").value === document.getElementById("fourth").value || document.getElementById("second").value === document.getElementById("third").value || document.getElementById("second").value === document.getElementById("fourth").value || document.getElementById("third").value === document.getElementById("fourth").value)){
     alert("Invalid input:\nPlease do not enter duplicate numbers");
     return false;
 }
 else if((round === 1 && document.getElementById("first").value === "") || (round === 2 && (document.getElementById("first").value === "" || document.getElementById("second").value === "")) || (round  === 3 && (document.getElementById("first").value === "" || document.getElementById("second").value === "" || document.getElementById("third").value === "")) || (round === 4 && (document.getElementById("first").value === "" || document.getElementById("second").value === "" || document.getElementById("third").value === "" || document.getElementById("fourth").value === ""))){
    alert("Invalid input:\nPlease enter a number in all fields");
    return false;
 }
 else{
     return true;
 }
}
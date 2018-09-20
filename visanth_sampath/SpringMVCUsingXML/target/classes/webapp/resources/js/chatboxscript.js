var chatHeader = document.getElementById("chat-header");
chatHeader.onclick = function () {
    if(document.getElementById("status").value == "none"){
        document.getElementsByClassName("chat-container")[0].style.height = "40vh";
        document.getElementsByClassName("chat-footer")[0].style.display = "block";
        document.getElementsByClassName("chat-body")[0].style.display = "block" ;
        document.getElementById("status").value = "block";
    }
    else if(document.getElementById("status").value == "block"){
        document.getElementsByClassName("chat-container")[0].style.height = "5vh";
        document.getElementsByClassName("chat-footer")[0].style.display = "none";
        document.getElementsByClassName("chat-body")[0].style.display = "none" ;
        document.getElementById("status").value = "none";
    }
}

function updateMessage() {
	var datetime =new Date();
    var time = (datetime.getHours()<10)?("0"+datetime.getHours().toString()):(datetime.getHours().toString());
    time += ":";
    time += (datetime.getMinutes()<10)?"0"+(datetime.getMinutes().toString()):(datetime.getMinutes().toString()); 
	var message = document.getElementsByClassName("user-message")[0].value;
	var currentMessage = "<div class='right-user'>" +
	"<div class='name'>"+
	"me"
	+ "</div>"
	+ "<div class='message'><p>"
	+ message
	+ "</p><br/><div class='time'>"
	+ time +
	"</div></div>" +
	"</div>";
	node += currentMessage;
	document.getElementsByClassName("messages_container")[0].innerHTML = node;
	document.getElementsByClassName("user-message")[0].value = "";
	
	
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     console.log("sucess");
	    }
	  };
	  xhttp.open("POST", "http://127.0.0.1:8080/FantasyLeague/UpdateMessageServlet", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("message="+message);
	
}

var node;
var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
    document.getElementsByClassName("messages_container")[0].innerHTML = this.responseText;
    node = this.responseText;
  }
};
var url ="http://127.0.0.1:8080/FantasyLeague/GetChatServlet";
xhttp.open("GET", url, true);
xhttp.send();
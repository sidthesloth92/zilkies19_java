function updateTopPicks(){
	var matchId = this["document"]["activeElement"]["attributes"][1]["nodeValue"];
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementsByClassName("records-block")[0].innerHTML = this.responseText;
	    }
	  };
	  var url ="http://127.0.0.1:8080/FantasyLeague/TopPicksServlet?match-id="+matchId;
	  xhttp.open("GET", url, true);
	  xhttp.send();
	console.log(matchId);
}
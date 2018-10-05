function displaySchedule() {
			document.getElementById('scorecard-1').style.display = 'none';
			document.getElementById('schedule-1').style.display = 'block';
			document.getElementById('schedule-1').style.opacity = '1';
		}

		function updateScorecard() {
			document.getElementById('schedule-1').style.opacity = '0.3';
			document.getElementById('scorecard-1').style.display = 'flex';
			document.getElementById('scorecard-1').style.left = '0px';
			document.getElementById('scorecard-1').style.top = '200px';
			document.getElementById('scorecard-1').style.zIndex = '1';
			document.getElementById('scorecard-1').style.position = 'fixed';
			document.getElementById('scorecard-1').style.width = '100%';
		}
		function btnmanipulation(clicked_id) {
			console.log('inside btnmanipulation');
			console.log(clicked_id);
			var match = document.getElementById(clicked_id).textContent;
			var arr = match.split('v');
			/*var match=document.querySelector("h3[id=" \\+ clicked_id + "]").value;*/
			for (i = 0; i < arr.length; i++) {
				arr[i] = arr[i].replace(/^\s\s*/, '').replace(/\s\s*$/, '');
			}
			console.log(arr);
			var list = document.getElementById("dropdown11");
			while (list.hasChildNodes()) {
				list.removeChild(list.firstChild);
			}
			var contents = '';
			for (var i = 0; i < arr.length; i++) {
				contents += '<option value='+arr[i]+'>' + arr[i] + '</option>';
			}
			document.getElementById("dropdown11").innerHTML += contents;
		}

		function fetchScorecard() {
			var teamName = document.getElementById("dropdown11").value;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var myArr = JSON.parse(this.responseText);
					for (var i = 0; i < myArr.length; i += 3) {
						document.querySelector("input[name=runs]").value = myArr[i];
						document.querySelector("input[name=overs]").value = myArr[i + 1];
						document.querySelector("input[name=wkts]").value = myArr[i + 2];
					}
				}
			};
			xhttp.open("GET",
					"http://localhost:8080/CricketTour/UpdateScore?teamname="
							+ teamName + "", true);
			xhttp.send();
		}
		
		function showloader(){
			document.getElementById("schedule-1").style.opacity='0.5';
			document.getElementById("stump-container").style.display='block';
			document.getElementById("stump-container").style.marginTop='-1000px';
			document.getElementById("stump-container").style.marginLeft='300px';
		}
		
		function closeloader(){
			document.getElementById("stump-container").style.display='none';
			document.getElementById("schedule-1").style.opacity='1';
		}

		function filterTournament() {
			showloader();
			console.log('inside dropdown');
			var tournamentName = document.getElementById("dropdown1").value;
			var xhttp = false;
		    if (window.XMLHttpRequest) {
		        xhttp = new XMLHttpRequest();
		    }
		    else {
		        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		    }
		    if (xhttp) {
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					closeloader();
					document.getElementById("dynamicadder").innerHTML="";
					if(this.responseText!="nothing"){
					var myArr = JSON.parse(this.responseText);
					console.log(myArr);
					var i;
					if (myArr.length != 0)
					var message = "";
					message+='<div class="team-container"><div class="team"><h3><span> MatchNo </span></h3></div><div class="view"><h3><span>Match</span></h3></div></div>'
					for (i = 0; i < myArr.length; i += 5) {
						message += '<div class="team-container"><div class="team"><h3>'
								+ myArr[i]
								+ '</h3></div><div class="view"><h3 id='+i+'>'
								+ myArr[i + 3]
								+ '</h3></div></div>';
					}
					}
					else {
						var msg = 'Not Enough Team/Team Limit is not Reached to schedule';
						message = '<div class="team-container"><div class="team"><h3>'
								+ msg
								+ '</h3></div></div>';
					}
					document.getElementById("dynamicadder").innerHTML += message;
				}
			};
			xhttp.open("POST",
					"http://localhost:8080/CricketTour/CreateSchedule?tournamentname="
							+ tournamentName + "", true);
			xhttp.send();
		    }
		}

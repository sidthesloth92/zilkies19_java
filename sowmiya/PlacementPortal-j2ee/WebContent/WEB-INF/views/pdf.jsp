<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <em><span style="font-size:small;"><script type="text/javascript" src="jquery/jquery-1.7.1.min.js"></script> </span><
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.debug.js" integrity="sha384-THVO/sM0mFD9h7dfSndI6TS0PgAGavwKvB5hAxRRvc0o9cPLohB0wb/PTA7LdUHs" crossorigin="anonymous"></script>
<script>
function demoFromHTML() {
var doc = new jsPDF('p', 'in', 'letter');
var source = document.getElementById("testcase");
var specialElementHandlers = {
'#bypassme': function(element, renderer) {
return true;
}
};

doc.fromHTML(
source, // HTML string or DOM elem ref.
0.5, // x coord
0.5, // y coord
{
'width': 7.5, // max width of content on PDF
'elementHandlers': specialElementHandlers
});

doc.output('dataurl');
}
</script>
<a href="javascript:demoFromHTML()" class="button">

<div id="testcase">

<h1>
We support special element handlers. Register them with jQuery-style.
</h1>

</div>
/**
 * 
 */

function myPdfGenerator () {
        	console.log("yes");
            var detail1 = document.getElementById("details--container").innerHTML;
            var detail2= document.getElementById("academic").innerHTML;
            var detail3=document.getElementById("project").innerHTML;
            var detail4=document.getElementById("certification").innerHTML;
            var detail5=document.getElementById("achievement").innerHTML;
            var printWindow = window.open('', '', 'height=400,width=800');
            printWindow.document.write('<html><head><title>RESUME</title>');
            printWindow.document.write('</head><body >');
            printWindow.document.write(detail1);
            printWindow.document.write('<h2 style="blue">ACADEMICS</h2>');
            printWindow.document.write(detail2);
            printWindow.document.write('<h2 style="blue">PROJECT</h2>');
            printWindow.document.write(detail3);
            printWindow.document.write('<h2 style="blue">CERTIFICATIONS</h2>');
            printWindow.document.write(detail4);
            printWindow.document.write('<h2 style="blue">ACHIEVEMENTS</h2>');
            printWindow.document.write(detail5);






            printWindow.document.write('</body></html>');
            printWindow.document.close();
            printWindow.print();
        }
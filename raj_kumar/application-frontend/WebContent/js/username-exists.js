$(document).ready(function() {
	$('#username').on('keyup', function() {
		var dataToBeSent = {
			uName : $("#username").val()
		};

		$.ajax({
			url : '/TenderApplication/GetDataServlet',
			data : dataToBeSent,
			type : 'POST',
			dataType : 'html',
			success : function(response) {
				$('#outputDiv').html(response);
			},
			error : function(request, textStatus, errorThrown) {
				// alert(errorThrown);
			}
		});
	});
});
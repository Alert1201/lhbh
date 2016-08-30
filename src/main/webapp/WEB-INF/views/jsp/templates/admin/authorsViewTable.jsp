<script type="text/javascript" class="init">

	$(document).ready(function() {
		$('#example').DataTable( {
			"scrollX": true,
			"columns": [
			    {"orderable":true, "width":"20%"},  // Fname
			    {"orderable":true, "width":"20%"},  // Lname
			    {"orderable":true, "width":"5%"},  // DOB M
			    {"orderable":true, "width":"5%"},  // DOB Y
			    {"orderable":true, "width":"5%"},  // DOB C
			    {"orderable":true, "width":"5%"},  // DOD M
			    {"orderable":true, "width":"5%"},  // DOD Y
			    {"orderable":true, "width":"5%"},  // DOD C
			    {"orderable":true, "width":"20%"},  // Nat
			    {"orderable":false,"width":"5%"},
			    {"orderable":false,"width":"5%"}
			    		  ]
			} );
		
	});
	
</script>

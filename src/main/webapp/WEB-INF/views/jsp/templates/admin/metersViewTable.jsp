<script type="text/javascript" class="init">

	$(document).ready(function() {
		$('#example').DataTable( {
			  "columns": [
			    {"orderable":true, "width":"45%"},
			    {"orderable":true, "width":"45%"},
			    {"orderable":false,"width":"5%"},
			    {"orderable":false,"width":"5%"}
			    		  ]
			} );
		
		$('a#delRow').on("click", function(event){
			  var retVal = confirm("Are you sure you want to delete?");
			  if(!retVal){
				  event.preventDefault(); // This is for preventing the default behavior of links 
			  }
			  
			});
	});
	
</script>
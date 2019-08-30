$( function() {

	$('#addiv').hide();
	$('#admin').click(function(){
		$('#bookpage').hide();
		$('#regpage').hide();
		$('#addiv').show();
		$('#adtab').hide();
		$('#admin').hide();
	
	});
	$('#adsub').click(function(){
		if($('#aduser').val()!='admin'||$('#adpass').val()!='admin')
			alert("Wrong User Name or Password!!!");
		else
			{
			$('#adinp').hide();
			$('#adtab').show();
			$.ajax({
	    		
		   		 url: "RunQuery",
		   			type: "Post",
		   			data : {
		   				x : "select * from book_table where fname is not null order by fname,lname"
		   			},
		   			dataType :'json',
		   			complete : 
		   				function(result){
		   				
		   				var x='<tr><th>Date</th><th>Time</th><th>Table Number</th><th>First Name</th><th>Last Name</th><th>e-mail</th><th>Contact</th></tr>'; Date
		   				$.each(result.responseJSON,function(i){
		   					x+='<tr><td>'+result.responseJSON[i].a[0].substr(0,10)+'</td>';
		   					if(result.responseJSON[i].a[1]<12)
		   						x+='<td>'+result.responseJSON[i].a[1]+' am</td>';
		   					else
		   						x+='<td>'+result.responseJSON[i].a[1]-12+' pm</td>';
		   					for(j=2;j<7;j++)
		   						x+='<td>'+result.responseJSON[i].a[j]+'</td>';
		   					
		   					x+='</tr>';
		   				});
		   				$('#adtab').empty();
		   				$('#adtab').append(x);
		   			
		   			}
				});
			
			}
	});
});
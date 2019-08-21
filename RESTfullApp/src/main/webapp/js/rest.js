$(document).ready(function () {

$("#updateForm").on("submit", function(){
	
	$.ajax({
	    url: 'rest/userInfo',
	    type: 'PUT',
	    dataType: "xml",
	    data:$("#updateForm").serialize(),
	    success: function(xml) {
	    	console.log(xml);
	    	var user="";
	    	$(xml).find('User').each(function(){
                $(this).find("id").each(function(){
                    var id = $(this).text();
                    console.log(id);
                    user=user+"ID: "+id;
                });
                $(this).find("name").each(function(){
                    var name = $(this).text();
                    console.log(name);
                    user=user+" Name: "+name;
                });
                $(this).find("age").each(function(){
                    var age = $(this).text();
                    console.log(age);
                    user=user+" Age: "+age;
                });
            });
	    	alert(user);
	    }
	});
   return true;
 })
 
 $("#deleteForm").on("submit", function(){
	$.ajax({
	    url: 'rest/userInfo',
	    type: 'DELETE',
	    dataType: "xml",
	    data:$("#deleteForm").serialize(),
	    success: function(xml) {
	    	console.log(xml);
	    	$(xml).find('User').each(function(){
                $(this).find("id").each(function(){
                    var id = $(this).text();
                    console.log(id);
                    alert("Deleted the user with id "+id);
                });
            });
	    	
	    }
	});
   return true;
 })
});
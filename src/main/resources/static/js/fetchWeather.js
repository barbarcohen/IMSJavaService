function fetchJSON(){ 
    $.ajax({ 
         type: "GET",
         dataType: "json",
         url: "/control/status",
         success: function(data){
             console.log(data);
             alert(data.status);
         }
     })
};
        
window.onload = fetchJSON;
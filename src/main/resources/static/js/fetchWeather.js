function fetchJSON(){ 
    $.ajax({ 
         type: "GET",
         dataType: "json",
         url: "/control/status",
         success: function(data){
             console.log(data);

         }
     })
};
        
window.onload = fetchJSON;
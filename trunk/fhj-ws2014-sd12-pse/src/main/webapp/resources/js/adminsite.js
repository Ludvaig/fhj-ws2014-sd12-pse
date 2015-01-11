$( document ).ready(function() {
	var locationPath = window.location.pathname;
	
	// set list items active
	if ( locationPath.indexOf("community_list.jsf") > 0 ) {
		console.log("in communities");
		$("#list_communities").addClass("active");
		$("#list_documents").addClass("active");
		$("#list_news").removeClass("active");
	}
	else if ( locationPath.indexOf("news_list.jsf") > 0 ) {
		console.log("in news");
		$("#list_communities").removeClass("active");
		$("#list_documents").addClass("active");
		$("#list_news").addClass("active");
	}
	else if ( locationPath.indexOf("documents_list.jsf") > 0 ) {
		console.log("in documents");
		$("#list_documents").removeClass("active");
		$("#list_communities").addClass("active");
		$("#list_news").addClass("active");
	}
	else {
		console.log("on main");
		$("#list_communities").removeClass("active");
		$("#list_news").removeClass("active");
		$("#list_documents").removeClass("active");
	}
	
	// toggle elements for welcome message and login forward
	if ($(".ui-messages-warn-summary").text().length > 0 ) {
		$("#welcomeText").hide();
		$("#list_communities").hide();
		$("#list_news").hide();
		$("#list_documents").hide();
		$("#li_logout").hide();
		$("#li_login").show();
	}
	else {
		$("#welcomeText").show();
		$("#list_communities").show();
		$("#list_news").show();
		$("#list_documents").show();
		$("#li_logout").show();
		$("#li_login").hide();
	}
	
});

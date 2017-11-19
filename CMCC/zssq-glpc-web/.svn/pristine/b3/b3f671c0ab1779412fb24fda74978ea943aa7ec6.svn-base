var baseUrl = "sta_files/";
var fileServerUrl = "172.16.0.110";
function formatTime(timeStamp) {
		var year = new Date(timeStamp).getFullYear();
		var month = new Date(timeStamp).getMonth() + 1;
		month = (month < 10 ? '0' + month : month);
		var day = new Date(timeStamp).getDate();
		day = (day < 10 ? '0' + day : day);
		var hour = new Date(timeStamp).getHours();
		hour = (hour < 10 ? '0' + hour : hour);
		var min = new Date(timeStamp).getMinutes();
		min = (min < 10 ? '0' + min : min);
		return year + '-' + month + '-' + day + ' ' + hour + ':' + min;
} ;

function formatContent(content){
	var $divWrap = $("<div></div>");
	var $imgs = $divWrap.html(content).find('img');
	$imgs.each(function(index,elem){

		var imgUrl = $(elem).attr("src") || $(elem).attr("currentSrc");
		if(imgUrl.indexOf(fileServerUrl) > -1){
			var imgName = imgUrl.substring(imgUrl.lastIndexOf("/")+1);
			$(elem).attr("src",formatUrl(imgName));		
		}
	});
	return $divWrap.html();
}

function formatUrl(filename){
	return baseUrl+filename;
}
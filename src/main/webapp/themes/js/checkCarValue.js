/*验证问题名是否重复*/
function vProblemName(oldvale,ppname,errmes)
{
	var $box = navTab.getCurrentPanel();
	var NAME = $("#"+ppname,$box).val();
	//alertMsg.error(oldvale+";"+ppname+";"+errmes+";"+NAME);
	if(''!=NAME)
	{
		var url = "xhCarLoanUser/chkValue?oldValue="+encodeURI(encodeURI(oldvale))+"&propertyName="+ppname+"&errmes="+encodeURI(encodeURI(errmes))+"&"+ppname+"="+encodeURI(encodeURI(NAME));
		var urlOld = convertURL(url);
		$.get(urlOld,null,function(data,text)
		{
			if(data=='')
			{
				if(ppname == 'cardNumber' && $("#pocertificates",$box).val()=='身份证'){
					NAME = $("#"+ppname,$box).val();
					var sr = "";
					var xb = 0;
					var xbv = "";
					if(NAME.length == 15){
						sr = NAME.substring(6,12);
						xbv = NAME.substring(14,15);
						xb = Number(xbv)%2;
						//alert("sr === >" + sr + ";;xbv === >" + xbv + ";;xb === >" + xb );
						$("#birthday",$box).val("19" + sr.substring(0, 2) + "-" + sr.substring(2, 4) + "-" + sr.substring(4, 6));
						var gender = xb == 0 ? '女' : '男';
						$("input[value='" + gender + "']", $box).click();
						/*var $genderSelect = $("#genders",$box);
						if(xb == 0){			    
							$genderSelect.val('女');
							$genderSelect.prev('a').html('女');
						}else{
							$genderSelect.val('男');
							$genderSelect.prev('a').html('男');
						}*/
					}
					if(NAME.length == 18){
						sr = NAME.substring(6,14);
						xbv = NAME.substring(16,17);
						xb = Number(xbv)%2;
						$("#birthday",$box).val(sr.substring(0, 4) + "-" + sr.substring(4, 6) + "-" + sr.substring(6, 8));
						var gender = xb == 0 ? '女' : '男';
						$("input[value='" + gender + "']", $box).click();
						/*var $genderSelect = $("#genders",$box);
						if(xb == 0){			    
							$genderSelect.val('女');
							$genderSelect.prev('a').html('女');
						}else{
							$genderSelect.val('男');
							$genderSelect.prev('a').html('男');
						}*/
					}
				}
			}else{
				//alert(data);
				alertMsg.error(data);
				$("#"+ppname,$box).val("");
			};
		});
	}
}
//给url地址增加时间戳，不读取缓存
function convertURL(url) {
    var timstamp = (new Date()).valueOf();
    //将时间戳信息拼接到url上
    if (url.indexOf("?") >= 0) {
        url = url + "&t=" + timstamp;
    } else {
        url = url + "?t=" + timstamp;
    }
    return url;
}

$(function(){
	   var $box = navTab.getCurrentPanel();
	   $("#pocertificates",$box).change(function(){
		    if($(this).val() == '身份证'){
				$("#cardNumber",$box).addClass("isIdCardNo");
			}else{
				$("#cardNumber",$box).removeClass("isIdCardNo");
			}
		});
	});
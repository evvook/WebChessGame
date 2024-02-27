<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
</script>
<head>
<meta charset="UTF-8">
<title>체스게임</title>
</head>
<body onload="javascirpt:ajaxRequest()">
<div onclick="javascirpt:location.href=location.origin+'/chesslike/battle.jsp?play=makeGame'"><h1>새 게임 만들기</h1></div>
<div id="battleListContainer">

</div>
</body>
<script type="text/javascript">
	function ajaxRequest(){
		
		var reqJson = new Object();
		/* 통신에 사용 될 XMLHttpRequest 객체 정의 */
		httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = () => {
	    	/* readyState가 Done이고 응답 값이 200일 때, 받아온 response로 처리*/
		    if (httpRequest.readyState === XMLHttpRequest.DONE) {
			      if (httpRequest.status === 200) {
			    	var result = httpRequest.response;
			    	doAfterwork(result);
			      } else {
			        alert('request에 뭔가 문제가 있어요.');
			      }
			}
	    };
	    
	    /* Post 방식으로 요청 */
	    httpRequest.open('POST', '/chesslike/MatchingListController', true);//경로 잡아줌
	    /* Response Type을 Json으로 사전 정의 */
	    httpRequest.responseType = "json";
	    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
	    httpRequest.setRequestHeader('Content-Type', 'application/json');
	    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
	    httpRequest.send(JSON.stringify(reqJson));
	}
	
	function doAfterwork(result){
		let listContainer = document.querySelector("#battleListContainer");
		if(result.readyToBattle.length == 0){
			//리무브 선행
	    	if(listContainer.childElementCount > 0){
				listContainer.replaceChildren()
	    	}
			//새로 만듬
			let notice = document.createElement('div');
			let h1 = document.createElement('h1');
			h1.innerText = "게임이 없어요. 새로고침 하시겠습니까?";
			notice.append(h1);
			notice.addEventListener('click',ajaxRequest)
			
			listContainer.append(notice);
		}else{
			let battle = null;
			for(let idx in result.readyToBattle){
				battle = document.createElement('div');
				let h1 = document.createElement('h1');
				h1.innerText = result.readyToBattle[idx];
				battle.append(h1);
				battle.addEventListener('click',function(){
					location.href=location.origin+'/chesslike/battle.jsp?play=joinGame&key='+battle.innerText;
				})
				listContainer.append(battle);
			}
		}
	}
</script>
</html>
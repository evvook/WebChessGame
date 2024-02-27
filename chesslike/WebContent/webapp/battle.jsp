<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	let gameState='start';
	function getGameState(){
		return  gameState;
	}
	function setGameState(state){
		gameState = state
		console.log(gameState)
	}
	//♔♕♖♗♘♙♚♛♜♝♞♟
	//서버에서 가져와서 쓰지는 않음
	let white = [{notation:"K",text:"♔"},{notation:"Q",text:"♕"},{notation:"R",text:"♖"},{notation:"B",text:"♗"},{notation:"N",text:"♘"},{notation:"P",text:"♙"}]
	let black = [{notation:"K",text:"♚"},{notation:"Q",text:"♛"},{notation:"R",text:"♜"},{notation:"B",text:"♝"},{notation:"N",text:"♞"},{notation:"P",text:"♟"}]
	let campsText = {white:white,black:black};
	
    function drawGame(boardAxis,positions,movesPositions){
    	let board = makeBoard(boardAxis.RANK,boardAxis.FILE);
    	let table = makeBoardTalbe(board);
    	let backgroud = document.getElementById("board");
    	if(backgroud.childElementCount > 0){
    		backgroud.removeChild(backgroud.firstChild);
    	}
    	backgroud.append(table);
    	setGameInfo(positions,movesPositions)
    }
    
    function makeBoard(axisX,axisY,positions){
        let board = [];
        let rank = []
        let idx = 0;
   		let checker = null;
   		let color = '';
       	for(let y in axisY){
	        for(let x in axisX){
        		let id = axisX[x]+axisY[y];
        		if(idx % 8 == 0){
        			checker = switchChecker(checker);
        		}
        		if(checker(idx++)){
        			color = '#DEB887';
        		}else{
        			color = '#FAF0E6';
        		}
        		let notation = axisX[x]+axisY[y];
        		rank[x] = {id:notation,color:color};
        	}
        	board[y] = rank;
        	rank = [];
        }
        return board.reverse();
    }
    
    function makeReverseBoard(axisX,axisY,positions){
        let board = [];
        let rank = []
        let idx = 0;
   		let checker = null;
   		let color = '';
       	for(let y in axisY){
	        for(let x in axisX){
        		let id = axisX[x]+axisY[y];
        		if(idx % 8 == 0){
        			checker = switchChecker(checker);
        		}
        		if(checker(idx++)){
        			color = '#DEB887';
        		}else{
        			color = '#FAF0E6';
        		}
        		let notation = axisX[x]+axisY[y];
        		rank[x] = {id:notation,color:color};
        	}
        	board[y] = rank.reverse();
        	rank = [];
        }
        return board;
    }
    
    function makeBoardTalbe(board){
    	
    	let table = null;
   	    let tr = null;
   	 	let td = null;
   	 	let token = null;
   	 	
    	table = document.createElement('table');
    	table.style.borderCollapse = 'collapse';
    	
    	for(let bIdx in board){
    		let rank = board[bIdx];
    		for(let rIdx in rank){
    			if(token == null){
    				token = rank[rIdx].id.charAt(0);
    			}
    			
	    	    let position = rank[rIdx];
	    	    if(position.id.charAt(0) == token){
	    	        tr = document.createElement('tr');
	    	        table.append(tr);
	    	    }
	    	    let pieceElement = document.createElement('div');
	    	    let pieceP = document.createElement('p');
	    	    pieceElement.className = "piece";
	    	    pieceElement.style.width = "80px";
	    	    pieceElement.style.height = "80px";
	    	    pieceElement.style.margin = "auto";
	    	    pieceElement.style.fontSize = "45pt";
	    	    pieceElement.style.textAlign = "center";
	    	    pieceElement.style.verticalAlign = "middle";
	    	    
	    	    let positionElement = document.createElement('div');
	    	    positionElement.id = position.id
	    	    positionElement.activeStatus = "";
	    	    positionElement.style.width = "80px";
	    	    positionElement.style.height = "80px";
	    	    positionElement.style.display = "flex";
	    	    positionElement.style.backgroundColor = position.color;
	    	    positionElement.addEventListener("click",click);
	    	    positionElement.append(pieceElement);
	    	    
	    	    td = document.createElement('td');
	    	    td.style.border = "solid 1px";
	    	    td.append(positionElement);
	    	    tr.append(td);
    		}
    	}
    	return table;
    }
    
    function setGameInfo(positions,movesPositions){
    	for(let idx in positions){
    		let position = positions[idx];
    		let positionElement = document.querySelector("#"+position.notation);
    		
    		let piece = position.onPiece;
    		if(piece.camp != null || piece.camp != undefined){
    			let camp = piece.camp;
	    		positionElement.activeStatus = camp.activeStatus;
	    		let pieceElement = document.querySelector("#"+position.notation+" .piece");
	    		pieceElement.innerText = piece.specialChar;
	    		/*
	    		//전역변수로 선언한 체스기물 특수기호 맵핑(서버에서 가져와서 쓰지 않음)
	    		let campText = campsText[camp.name];
	    		for(let idx in campText){
	    			let pieceText = campText[idx];
	    			if(pieceText.notation == piece.notation){
	    				pieceElement.innerText = pieceText.text;
	    			}
	    		}
	    		*/
    		}else{
    			positionElement.activeStatus = "INACTIVE";
    		}
    	}
    	for(let idx in positions){
    		let position = positions[idx];
    		let positionElement = document.querySelector("#"+position.notation);
   			for(let idx in movesPositions){
   				let movesPosition = movesPositions[idx];
   				if(position.notation == movesPosition.notation){
	    			positionElement.activeStatus = "ACTIVE";
	    			if(position.onPiece != ''){
		    			positionElement.style.backgroundColor = '#F4A460';
	    			}else{
		    			positionElement.style.backgroundColor = '#FFE4B5';
	    			}
   				}
   			}
    	}
    }
    
    function isOdd(number){
    	return number % 2 != 0;
    }
    function isEven(number){
    	return number % 2 == 0;
    }
    
    function switchChecker(checker){
    	if(checker == null || checker == isOdd){
    		return isEven;
    	}else{
    		return isOdd;
    	}
    }
    function click(){
    	let gameState = getGameState();
    	if(gameState != undefined && gameState == "Checkmate" && gameState == "Stalemate"){
    		alert('게임이 끝났습니다.')
    		return;
    	}
    	if(this.activeStatus == 'INACTIVE'){
    	}
    	let position = this;
    	let pieceSelector = "#"+this.id+" .piece";
    	let piece = document.querySelector(pieceSelector);
    	ajaxRequest(setGameState(), position.id);
    }
    
    function ajaxRequest(command,positionLetter){
    	
    	var reqJson = new Object();
		reqJson.command = command;
		reqJson.positionLetter = positionLetter;
		reqJson.play = 
	<%=
		"\""+request.getParameter("play")+"\";"
	%>
		reqJson.oppositeKey = 
	<%=
		"\""+request.getParameter("key")+"\";"
	%>
		//reqJson.ip = "127.0.0.1";
		reqJson.gameState = (getGameState()==null||getGameState()==undefined)?"onGoing":getGameState();
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
	    httpRequest.open('POST', '/chesslike/WebController', true);//경로 잡아줌
	    /* Response Type을 Json으로 사전 정의 */
	    httpRequest.responseType = "json";
	    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
	    httpRequest.setRequestHeader('Content-Type', 'application/json');
	    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
	    httpRequest.send(JSON.stringify(reqJson));
    }
    
    var intervalID;
    
    function doAfterwork(result){
    	let state = result.gameState;
    	setGameState(state);
        if(gameState != undefined && gameState == "Checkmate"){
        	alert(result.winner+" is Winner!");
        	
        	//clearInterval(intervalID)
        	
        }else if(gameState != undefined && gameState == "Stalemate"){
        	alert("비겼습니다!");
        	
        	//clearInterval(intervalID)
        }else{
	        drawGame(result.boardAxis,result.positions,result.movesPositions)
	        if(result.message != null && result.message != undefined){
	        	alert(result.message);
	        }
	        if(!intervalID){
		        intervalID = setInterval(intervalInner, 3000);
	        }
        }
        
    }
    
    function intervalInner(){
		ajaxRequest('load');
	}
</script>
<head>
<meta charset="UTF-8">
<title>대전</title>
</head>
<body onload="javascirpt:ajaxRequest('start','');setGameState(null);">
<div id="board"></div>
</body>
</html>
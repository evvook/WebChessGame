/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.44
 * Generated at: 2022-07-07 02:12:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class battle_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tlet gameState='start';\r\n");
      out.write("\tfunction getGameState(){\r\n");
      out.write("\t\treturn  gameState;\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction setGameState(state){\r\n");
      out.write("\t\tgameState = state\r\n");
      out.write("\t\tconsole.log(gameState)\r\n");
      out.write("\t}\r\n");
      out.write("\t//♔♕♖♗♘♙♚♛♜♝♞♟\r\n");
      out.write("\t//서버에서 가져와서 쓰지는 않음\r\n");
      out.write("\tlet white = [{notation:\"K\",text:\"♔\"},{notation:\"Q\",text:\"♕\"},{notation:\"R\",text:\"♖\"},{notation:\"B\",text:\"♗\"},{notation:\"N\",text:\"♘\"},{notation:\"P\",text:\"♙\"}]\r\n");
      out.write("\tlet black = [{notation:\"K\",text:\"♚\"},{notation:\"Q\",text:\"♛\"},{notation:\"R\",text:\"♜\"},{notation:\"B\",text:\"♝\"},{notation:\"N\",text:\"♞\"},{notation:\"P\",text:\"♟\"}]\r\n");
      out.write("\tlet campsText = {white:white,black:black};\r\n");
      out.write("\t\r\n");
      out.write("    function drawGame(boardAxis,positions,movesPositions){\r\n");
      out.write("    \tlet board = makeBoard(boardAxis.RANK,boardAxis.FILE);\r\n");
      out.write("    \tlet table = makeBoardTalbe(board);\r\n");
      out.write("    \tlet backgroud = document.getElementById(\"board\");\r\n");
      out.write("    \tif(backgroud.childElementCount > 0){\r\n");
      out.write("    \t\tbackgroud.removeChild(backgroud.firstChild);\r\n");
      out.write("    \t}\r\n");
      out.write("    \tbackgroud.append(table);\r\n");
      out.write("    \tsetGameInfo(positions,movesPositions)\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function makeBoard(axisX,axisY,positions){\r\n");
      out.write("        let board = [];\r\n");
      out.write("        let rank = []\r\n");
      out.write("        let idx = 0;\r\n");
      out.write("   \t\tlet checker = null;\r\n");
      out.write("   \t\tlet color = '';\r\n");
      out.write("       \tfor(let y in axisY){\r\n");
      out.write("\t        for(let x in axisX){\r\n");
      out.write("        \t\tlet id = axisX[x]+axisY[y];\r\n");
      out.write("        \t\tif(idx % 8 == 0){\r\n");
      out.write("        \t\t\tchecker = switchChecker(checker);\r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t\tif(checker(idx++)){\r\n");
      out.write("        \t\t\tcolor = '#DEB887';\r\n");
      out.write("        \t\t}else{\r\n");
      out.write("        \t\t\tcolor = '#FAF0E6';\r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t\tlet notation = axisX[x]+axisY[y];\r\n");
      out.write("        \t\trank[x] = {id:notation,color:color};\r\n");
      out.write("        \t}\r\n");
      out.write("        \tboard[y] = rank;\r\n");
      out.write("        \trank = [];\r\n");
      out.write("        }\r\n");
      out.write("        return board.reverse();\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function makeReverseBoard(axisX,axisY,positions){\r\n");
      out.write("        let board = [];\r\n");
      out.write("        let rank = []\r\n");
      out.write("        let idx = 0;\r\n");
      out.write("   \t\tlet checker = null;\r\n");
      out.write("   \t\tlet color = '';\r\n");
      out.write("       \tfor(let y in axisY){\r\n");
      out.write("\t        for(let x in axisX){\r\n");
      out.write("        \t\tlet id = axisX[x]+axisY[y];\r\n");
      out.write("        \t\tif(idx % 8 == 0){\r\n");
      out.write("        \t\t\tchecker = switchChecker(checker);\r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t\tif(checker(idx++)){\r\n");
      out.write("        \t\t\tcolor = '#DEB887';\r\n");
      out.write("        \t\t}else{\r\n");
      out.write("        \t\t\tcolor = '#FAF0E6';\r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t\tlet notation = axisX[x]+axisY[y];\r\n");
      out.write("        \t\trank[x] = {id:notation,color:color};\r\n");
      out.write("        \t}\r\n");
      out.write("        \tboard[y] = rank.reverse();\r\n");
      out.write("        \trank = [];\r\n");
      out.write("        }\r\n");
      out.write("        return board;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function makeBoardTalbe(board){\r\n");
      out.write("    \t\r\n");
      out.write("    \tlet table = null;\r\n");
      out.write("   \t    let tr = null;\r\n");
      out.write("   \t \tlet td = null;\r\n");
      out.write("   \t \tlet token = null;\r\n");
      out.write("   \t \t\r\n");
      out.write("    \ttable = document.createElement('table');\r\n");
      out.write("    \ttable.style.borderCollapse = 'collapse';\r\n");
      out.write("    \t\r\n");
      out.write("    \tfor(let bIdx in board){\r\n");
      out.write("    \t\tlet rank = board[bIdx];\r\n");
      out.write("    \t\tfor(let rIdx in rank){\r\n");
      out.write("    \t\t\tif(token == null){\r\n");
      out.write("    \t\t\t\ttoken = rank[rIdx].id.charAt(0);\r\n");
      out.write("    \t\t\t}\r\n");
      out.write("    \t\t\t\r\n");
      out.write("\t    \t    let position = rank[rIdx];\r\n");
      out.write("\t    \t    if(position.id.charAt(0) == token){\r\n");
      out.write("\t    \t        tr = document.createElement('tr');\r\n");
      out.write("\t    \t        table.append(tr);\r\n");
      out.write("\t    \t    }\r\n");
      out.write("\t    \t    let pieceElement = document.createElement('div');\r\n");
      out.write("\t    \t    let pieceP = document.createElement('p');\r\n");
      out.write("\t    \t    pieceElement.className = \"piece\";\r\n");
      out.write("\t    \t    pieceElement.style.width = \"80px\";\r\n");
      out.write("\t    \t    pieceElement.style.height = \"80px\";\r\n");
      out.write("\t    \t    pieceElement.style.margin = \"auto\";\r\n");
      out.write("\t    \t    pieceElement.style.fontSize = \"45pt\";\r\n");
      out.write("\t    \t    pieceElement.style.textAlign = \"center\";\r\n");
      out.write("\t    \t    pieceElement.style.verticalAlign = \"middle\";\r\n");
      out.write("\t    \t    \r\n");
      out.write("\t    \t    let positionElement = document.createElement('div');\r\n");
      out.write("\t    \t    positionElement.id = position.id\r\n");
      out.write("\t    \t    positionElement.activeStatus = \"\";\r\n");
      out.write("\t    \t    positionElement.style.width = \"80px\";\r\n");
      out.write("\t    \t    positionElement.style.height = \"80px\";\r\n");
      out.write("\t    \t    positionElement.style.display = \"flex\";\r\n");
      out.write("\t    \t    positionElement.style.backgroundColor = position.color;\r\n");
      out.write("\t    \t    positionElement.addEventListener(\"click\",click);\r\n");
      out.write("\t    \t    positionElement.append(pieceElement);\r\n");
      out.write("\t    \t    \r\n");
      out.write("\t    \t    td = document.createElement('td');\r\n");
      out.write("\t    \t    td.style.border = \"solid 1px\";\r\n");
      out.write("\t    \t    td.append(positionElement);\r\n");
      out.write("\t    \t    tr.append(td);\r\n");
      out.write("    \t\t}\r\n");
      out.write("    \t}\r\n");
      out.write("    \treturn table;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function setGameInfo(positions,movesPositions){\r\n");
      out.write("    \tfor(let idx in positions){\r\n");
      out.write("    \t\tlet position = positions[idx];\r\n");
      out.write("    \t\tlet positionElement = document.querySelector(\"#\"+position.notation);\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\tlet piece = position.onPiece;\r\n");
      out.write("    \t\tif(piece.camp != null || piece.camp != undefined){\r\n");
      out.write("    \t\t\tlet camp = piece.camp;\r\n");
      out.write("\t    \t\tpositionElement.activeStatus = camp.activeStatus;\r\n");
      out.write("\t    \t\tlet pieceElement = document.querySelector(\"#\"+position.notation+\" .piece\");\r\n");
      out.write("\t    \t\tpieceElement.innerText = piece.specialChar;\r\n");
      out.write("\t    \t\t/*\r\n");
      out.write("\t    \t\t//전역변수로 선언한 체스기물 특수기호 맵핑(서버에서 가져와서 쓰지 않음)\r\n");
      out.write("\t    \t\tlet campText = campsText[camp.name];\r\n");
      out.write("\t    \t\tfor(let idx in campText){\r\n");
      out.write("\t    \t\t\tlet pieceText = campText[idx];\r\n");
      out.write("\t    \t\t\tif(pieceText.notation == piece.notation){\r\n");
      out.write("\t    \t\t\t\tpieceElement.innerText = pieceText.text;\r\n");
      out.write("\t    \t\t\t}\r\n");
      out.write("\t    \t\t}\r\n");
      out.write("\t    \t\t*/\r\n");
      out.write("    \t\t}else{\r\n");
      out.write("    \t\t\tpositionElement.activeStatus = \"INACTIVE\";\r\n");
      out.write("    \t\t}\r\n");
      out.write("    \t}\r\n");
      out.write("    \tfor(let idx in positions){\r\n");
      out.write("    \t\tlet position = positions[idx];\r\n");
      out.write("    \t\tlet positionElement = document.querySelector(\"#\"+position.notation);\r\n");
      out.write("   \t\t\tfor(let idx in movesPositions){\r\n");
      out.write("   \t\t\t\tlet movesPosition = movesPositions[idx];\r\n");
      out.write("   \t\t\t\tif(position.notation == movesPosition.notation){\r\n");
      out.write("\t    \t\t\tpositionElement.activeStatus = \"ACTIVE\";\r\n");
      out.write("\t    \t\t\tif(position.onPiece != ''){\r\n");
      out.write("\t\t    \t\t\tpositionElement.style.backgroundColor = '#F4A460';\r\n");
      out.write("\t    \t\t\t}else{\r\n");
      out.write("\t\t    \t\t\tpositionElement.style.backgroundColor = '#FFE4B5';\r\n");
      out.write("\t    \t\t\t}\r\n");
      out.write("   \t\t\t\t}\r\n");
      out.write("   \t\t\t}\r\n");
      out.write("    \t}\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function isOdd(number){\r\n");
      out.write("    \treturn number % 2 != 0;\r\n");
      out.write("    }\r\n");
      out.write("    function isEven(number){\r\n");
      out.write("    \treturn number % 2 == 0;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function switchChecker(checker){\r\n");
      out.write("    \tif(checker == null || checker == isOdd){\r\n");
      out.write("    \t\treturn isEven;\r\n");
      out.write("    \t}else{\r\n");
      out.write("    \t\treturn isOdd;\r\n");
      out.write("    \t}\r\n");
      out.write("    }\r\n");
      out.write("    function click(){\r\n");
      out.write("    \tlet gameState = getGameState();\r\n");
      out.write("    \tif(gameState != undefined && gameState == \"Checkmate\" && gameState == \"Stalemate\"){\r\n");
      out.write("    \t\talert('게임이 끝났습니다.')\r\n");
      out.write("    \t\treturn;\r\n");
      out.write("    \t}\r\n");
      out.write("    \tif(this.activeStatus == 'INACTIVE'){\r\n");
      out.write("    \t}\r\n");
      out.write("    \tlet position = this;\r\n");
      out.write("    \tlet pieceSelector = \"#\"+this.id+\" .piece\";\r\n");
      out.write("    \tlet piece = document.querySelector(pieceSelector);\r\n");
      out.write("    \tajaxRequest(setGameState(), position.id);\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function ajaxRequest(command,positionLetter){\r\n");
      out.write("    \t\r\n");
      out.write("    \tvar reqJson = new Object();\r\n");
      out.write("\t\treqJson.command = command;\r\n");
      out.write("\t\treqJson.positionLetter = positionLetter;\r\n");
      out.write("\t\treqJson.play = \r\n");
      out.write("\t");
      out.print(
		"\""+request.getParameter("play")+"\";"
	);
      out.write("\r\n");
      out.write("\t\treqJson.oppositeKey = \r\n");
      out.write("\t");
      out.print(
		"\""+request.getParameter("key")+"\";"
	);
      out.write("\r\n");
      out.write("\t\t//reqJson.ip = \"127.0.0.1\";\r\n");
      out.write("\t\treqJson.gameState = (getGameState()==null||getGameState()==undefined)?\"onGoing\":getGameState();\r\n");
      out.write("\t\t/* 통신에 사용 될 XMLHttpRequest 객체 정의 */\r\n");
      out.write("\t\thttpRequest = new XMLHttpRequest();\r\n");
      out.write("\t\thttpRequest.onreadystatechange = () => {\r\n");
      out.write("\t    \t/* readyState가 Done이고 응답 값이 200일 때, 받아온 response로 처리*/\r\n");
      out.write("\t\t    if (httpRequest.readyState === XMLHttpRequest.DONE) {\r\n");
      out.write("\t\t\t      if (httpRequest.status === 200) {\r\n");
      out.write("\t\t\t    \tvar result = httpRequest.response;\r\n");
      out.write("\t\t\t    \tdoAfterwork(result);\r\n");
      out.write("\t\t\t      } else {\r\n");
      out.write("\t\t\t        alert('request에 뭔가 문제가 있어요.');\r\n");
      out.write("\t\t\t      }\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t    };\r\n");
      out.write("\t    \r\n");
      out.write("\t    /* Post 방식으로 요청 */\r\n");
      out.write("\t    httpRequest.open('POST', '/chesslike/WebController', true);//경로 잡아줌\r\n");
      out.write("\t    /* Response Type을 Json으로 사전 정의 */\r\n");
      out.write("\t    httpRequest.responseType = \"json\";\r\n");
      out.write("\t    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */\r\n");
      out.write("\t    httpRequest.setRequestHeader('Content-Type', 'application/json');\r\n");
      out.write("\t    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */\r\n");
      out.write("\t    httpRequest.send(JSON.stringify(reqJson));\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    var intervalID;\r\n");
      out.write("    \r\n");
      out.write("    function doAfterwork(result){\r\n");
      out.write("    \tlet state = result.gameState;\r\n");
      out.write("    \tsetGameState(state);\r\n");
      out.write("        if(gameState != undefined && gameState == \"Checkmate\"){\r\n");
      out.write("        \talert(result.winner+\" is Winner!\");\r\n");
      out.write("        \t\r\n");
      out.write("        \t//clearInterval(intervalID)\r\n");
      out.write("        \t\r\n");
      out.write("        }else if(gameState != undefined && gameState == \"Stalemate\"){\r\n");
      out.write("        \talert(\"비겼습니다!\");\r\n");
      out.write("        \t\r\n");
      out.write("        \t//clearInterval(intervalID)\r\n");
      out.write("        }else{\r\n");
      out.write("\t        drawGame(result.boardAxis,result.positions,result.movesPositions)\r\n");
      out.write("\t        if(result.message != null && result.message != undefined){\r\n");
      out.write("\t        \talert(result.message);\r\n");
      out.write("\t        }\r\n");
      out.write("\t        if(!intervalID){\r\n");
      out.write("\t\t        intervalID = setInterval(intervalInner, 3000);\r\n");
      out.write("\t        }\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function intervalInner(){\r\n");
      out.write("\t\tajaxRequest('load');\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>대전</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body onload=\"javascirpt:ajaxRequest('start','');setGameState(null);\">\r\n");
      out.write("<div id=\"board\"></div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

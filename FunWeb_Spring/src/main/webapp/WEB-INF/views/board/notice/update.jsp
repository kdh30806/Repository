<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='<c:url value="/resources/css/default.css" />' rel="stylesheet" type="text/css">
<link href='<c:url value="/resources/css/subpage.css" />' rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../../inc/top.jsp"  />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<jsp:include page="../../inc/leftMenu.jsp"  />
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->
<article>
<h1>Notice</h1>

<form action="update" method="post">
<input type="hidden" name="num" value="${bb.num }">
<table border="1" id="notice">
<tr><th></th><th></th></tr>
<tr><td>글쓴이</td><td><input type= "text"  name="name" value="${bb.name }" readonly="readonly"></td></tr>
<tr><td>제목</td><td><input type= "text"  name="subject" value="${bb.subject }"></td></tr>
<tr><td>내용</td><td><textarea rows="10" cols="20" name="content">${bb.content }</textarea></td></tr>
</table>
<div id="table_search">
<input type="submit" value="글수정" class="btn"><input type="button" value="취소" class="btn" onclick='<c:url value="/board/notice/noticeMain"/>'>
</div>
</form>

</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../../inc/bottom.jsp"  />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>
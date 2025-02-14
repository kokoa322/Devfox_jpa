<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>

    <style>
    /* 기본 레이아웃 */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f7f7f7;
    }

    /* 헤더 스타일 */
    h2 {
        text-align: center;
        margin-top: 20px;
        color: #333;
    }

    /* 테이블 스타일 */
    table {
        width: 90%;
        margin: 30px auto;
        border-collapse: collapse;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    table th, table td {
        padding: 10px;
        text-align: center;
        border: 1px solid #ddd;
    }

    table th {
        background-color: #4CAF50;
        color: white;
    }

    table tbody tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    table tbody tr:hover {
        background-color: #f1f1f1;
    }

    /* 페이지네이션 스타일 */
    .pagination {
        display: flex;
        justify-content: center;
        align-items: center;
        list-style-type: none;
        padding: 0;
        margin-top: 30px;
    }

    .pagination li {
        margin: 0 5px;
    }

    .pagination a {
        display: block;
        padding: 8px 15px;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        font-size: 14px;
        transition: background-color 0.3s ease;
    }

    .pagination a:hover {
        background-color: #45a049;
    }

    .pagination li.active a {
        background-color: #333;
    }

    .pagination li a:focus {
        outline: none;
    }

    .pagination li a:hover {
        background-color: #45a049;
    }

    .pagination li:first-child a {
        border-radius: 5px 0 0 5px;
    }

    .pagination li:last-child a {
        border-radius: 0 5px 5px 0;
    }

    .pagination li.disabled a {
        background-color: #ddd;
        color: #aaa;
        pointer-events: none;
    }

    /* 반응형 디자인 */
    @media (max-width: 768px) {
        table {
            width: 100%;
            font-size: 14px;
        }

        .pagination a {
            padding: 6px 12px;
        }
    }
    </style>
</head>
<body>

<h2>게시판 목록</h2>

<table border="1">
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="board" items="${boards}" varStatus="status">
            <tr>
                <td>${board.id}</td>
                <td><a href="/boards/${board.id}">${board.title}</a></td>
                <td>${board.author}</td>
                <td>

                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- 페이지네이션 -->
<!-- 페이지네이션 -->
<div>
    <ul class="pagination">
        <c:if test="${currentPage > 0}">
            <li><a href="?page=0"><<</a></li> <!-- 첫 페이지 -->
            <li><a href="?page=${currentPage - 1}"><</a></li> <!-- 이전 페이지 -->
        </c:if>

        <c:set var="maxPagesToShow" value="5" />
        <c:set var="halfPagesToShow" value="${maxPagesToShow / 2}" />

        <c:set var="startPage" value="${currentPage - halfPagesToShow}" />
        <c:set var="endPage" value="${currentPage + halfPagesToShow}" />

        <!-- 시작 페이지 조정 (0보다 작으면 0으로) -->
        <c:if test="${startPage < 0}">
            <c:set var="endPage" value="${endPage - startPage}" />
            <c:set var="startPage" value="0" />
        </c:if>

        <!-- 끝 페이지 조정 (totalPages 넘어가면 조정) -->
        <c:if test="${endPage >= totalPages}">
            <c:set var="startPage" value="${startPage - (endPage - totalPages + 1)}" />
            <c:set var="endPage" value="${totalPages - 1}" />
        </c:if>

        <!-- 최소 5개 표시 보장 -->
        <c:if test="${endPage - startPage + 1 < maxPagesToShow}">
            <c:set var="endPage" value="${startPage + maxPagesToShow - 1}" />
            <c:if test="${endPage >= totalPages}">
                <c:set var="endPage" value="${totalPages - 1}" />
            </c:if>
        </c:if>

        <c:forEach var="i" begin="${startPage}" end="${endPage}">
            <li class="${i == currentPage ? 'active' : ''}">
                <a href="?page=${i}">${i + 1}</a>
            </li>
        </c:forEach>

        <c:if test="${currentPage < totalPages - 1}">
            <li><a href="?page=${currentPage + 1}">></a></li> <!-- 다음 페이지 -->
            <li><a href="?page=${totalPages - 1}">>></a></li> <!-- 마지막 페이지 -->
        </c:if>
    </ul>
</div>



</body>
</html>

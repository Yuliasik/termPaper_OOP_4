<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Students getter</title>
    <style>
        html,
        body {
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
        }

        .all-page {
            height: inherit;
            display: flex;
            justify-content: space-around;
            align-items: stretch;
            flex-direction: row;
        }

        .buttons {
            margin-left: 80px;
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            width: 300px;
            background: rgb(30, 32, 61);
        }

        .button {
            margin-left: 20px;
        }

        .button button {
            width: 260px;
            height: 80px;
            background: linear-gradient(to bottom, #4eb5e5 0%, #389ed5 100%);
            border: none;
            border-radius: 10px;
            border-bottom: 4px solid #2b8bc6;
            border-left: 4px solid #2b8bc6;
            color: #fbfbfb;
            font-weight: 600;
            font-family: 'Open Sans', sans-serif;
            text-shadow: 1px 1px 1px rgba(0, 0, 0, .4);
            font-size: 15px;
            text-indent: 5px;
            box-shadow: 0 3px 0 0 rgba(0, 0, 0, .2)
        }

        .content {
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: stretch;
            border: 8px double rgb(30, 32, 61);
            width: 700px;
            margin: 30px;
            padding: 30px;
        }

        .content_title p {
            margin-bottom: 30px;
            padding: 5px 30px 5px 30px;
            background: linear-gradient(to bottom, #4eb5e5 0%, #389ed5 100%);
            border: none;
            border-radius: 20px;
            border-bottom: 4px solid #2b8bc6;
            border-left: 4px solid #2b8bc6;
            color: #fbfbfb;
            text-align: center;
            font-weight: 600;
            font-family: 'Open Sans', sans-serif;
            text-shadow: 1px 1px 1px rgba(0, 0, 0, .4);
            font-size: 18px;
            text-indent: 5px;
            box-shadow: 0 3px 0 0 rgba(0, 0, 0, .2)
        }

        .rezult {
            height: 78%;
            padding: 30px;
            background: #f1f7fa;
            overflow-x: auto;
        }

        .input_t {
            margin-left: 150px;
            width: 230px;
        }

        .input_b {
            width: 100px;
        }

        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            border-collapse: collapse;
            text-align: center;
            border-top: 1px solid rgb(30, 32, 61);
            border-left: 1px solid rgb(30, 32, 61);
            margin: auto;
        }

        .first {
            background: #AFCDE7;
            color: rgb(30, 32, 61);
            padding: 10px 20px;
        }

        th,
        td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: rgb(30, 32, 61);
        }

        td {
            background: #D8E6F3;
        }

        th {
            padding: 4px;
            text-align: center;
        }

        td {
            padding: 4px;
            text-align: left;
        }

        .before_inp {
            font-size: 15px;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            text-align: center;
        }
    </style>
    <script>
        function redirectPage(pageName) {
            window.location.href = pageName;
        }
    </script>
</head>
<body>

<div class="all-page">
    <div class="buttons">
        <%
            List<String> redirects = ((List<String>) (session.getAttribute("redirectings")));
            List<String> titles = ((List<String>) (session.getAttribute("titles")));
            for (int i = 0; i < 6; i++) {
                String title = titles.get(i);
                String redirect = redirects.get(i);
        %>
        <div class="button">
            <button onclick="redirectPage('<%= redirect %>')"><%= title %>
            </button>
        </div>
        <% } %>
    </div>
    <div class="content">
        <div class="content_title">
            <p><%= session.getAttribute("title") %></p>
        </div>
        <div class="rezult">
            <div>
                <%= session.getAttribute("content") %>
            </div>
        </div>

    </div>
</div>

</body>
</html>
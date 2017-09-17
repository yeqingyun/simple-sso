<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
<table style="margin: 0 auto;">
    <form method="post" action="/login">
        <caption>login</caption>
        <tbody>
        <tr align="center">
            <td>accont:</td>
            <td><input name="account" type="text"></td>
        </tr>
        <tr align="center">
            <td>password:</td>
            <td><input name="password" type="password"></td>
        </tr>
        <tr align="center">
            <td colspan="2"><input value="login" type="submit"></td>
        </tr>
        <tr align="center">
            <td style="color: red" colspan="2">${requestScope.message}</td>
        </tr>
        </tbody>
        <input name="redirect" type="hidden" value="${param.redirect}" >
    </form>
</table>
</body>
</html>
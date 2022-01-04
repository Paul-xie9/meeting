<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件上传页面</title>
</head>
<body>
<div>
    <div>
        ${error!''}
    </div>
    <h4>请选择需要上传的文件</h4>
    <form name="uploadFile" action="/uploadFile" method="get" enctype="multipart/form-data">
        <table>
            <tr>
                <td>上传者</td>
                <td><input type="text" name="name" required="required" size="50"></td>
            </tr>
            <tr>
                <td>上传文件</td>
                <td><input type="file" name="fileName" required="required" size="50"></td>
            </tr>
            <tr>
            <td><input type="submit" value="开始上传"></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
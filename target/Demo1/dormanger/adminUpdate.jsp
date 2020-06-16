<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>欢迎您来到信息修改界面！</h1>
<form action="uploadAdminServlet?adminId=${Admin.adminId}&image=${Admin.image}" method="post" enctype="multipart/form-data">
    <div>
        <div class="layui-upload-list" align="center">
            <img class="layui-upload-img" src="/imagepath/${Admin.image}"  width="300px;" height="300px;">
            <br>
            <label>头像上传</label><input class="layui-btn" type="file" name="fileUpload">
        </div>

        <div class="layui-progress">
            <div class="layui-progress-bar layui-bg-red" lay-percent="100%"></div>
        </div>
        <br>
        <div  style="width: 650px;height: 500px;margin-left: 350px;">
            <div class="layui-form-item">
                <label class="layui-form-label">用户：</label>
                <div class="layui-input-block">
                    <input type="text" name="userName" typeof="password"  class="layui-input" value="${Admin.userName}" >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名：</label>
                <div class="layui-input-block">
                    <input type="text" name="name" typeof="password"  class="layui-input" value="${Admin.name}" >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码：</label>
                <div class="layui-input-block">
                    <input type="text" name="password"  class="layui-input" value="${Admin.passWord}" >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别：</label>
                <div class="layui-input-block">
                    <input type="text" name="sex"  class="layui-input" value="${Admin.sex}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话：</label>
                <div class="layui-input-block">
                    <input type="text" name="tel"  class="layui-input" value="${Admin.tel}">
                </div>
            </div>
            <div align="center">
                <button class="layui-btn layui-btn-danger" type="submit">提交</button>
                ${tsxx}${tsxx=null}
            </div>
        </div>
    </div>
</form>
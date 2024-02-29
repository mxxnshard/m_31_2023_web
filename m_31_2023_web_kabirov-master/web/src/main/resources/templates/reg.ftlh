<#import "/spring.ftl" as spring>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">

        </div>
        <div class="col">
            <form action="/web/register" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <@spring.formInput "reg_form.email" "class=\"form-control\" id=\"email\"" "email"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">name</label>
                    <@spring.formInput "reg_form.name" "class=\"form-control\" id=\"name\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="mb-3">
                    <label for="secondName" class="form-label">Second name</label>
                    <@spring.formInput "reg_form.secondName" "class=\"form-control\" id=\"secondName\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <@spring.formInput "reg_form.password" "class=\"form-control\" id=\"password\"" "password"/>
                    <@spring.showErrors "<br>"/>
                </div>

                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>
        <div class="col">

        </div>
    </div>
</div>


<script src="/js/bootstrap.min.js"></script>
</body>
</html>
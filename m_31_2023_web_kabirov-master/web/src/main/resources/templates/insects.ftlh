<#import "/spring.ftl" as spring>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Login</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <#list insects>
        <#items as hat>
            <div class="row">
                <div class="col">
                    ${hat.hatName}
                </div>
                <div class="col">
                    ${hat.size}
                </div>
            </div>
        </#items>
    </#list>
</div>


<script src="/js/bootstrap.min.js"></script>
</body>
</html>
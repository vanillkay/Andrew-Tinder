<#include "partials/head.ftl">
<body class="text-center">
<form class="form-signin" method="post">
    <img class="mb-4" src="https://i.pinimg.com/564x/be/b3/67/beb36735e74caaeb6e18ac00dadc3794.jpg" alt="" width="72"
         height="72">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" name="email" value="${email}" id="inputEmail"
           class="form-control ${isEmailError?then('is-invalid','')}" placeholder="Email address" required>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" name="password" id="inputPassword" value="${password}"
           class="form-control ${isPasswordError?then('is-invalid','')}" placeholder="Password" required>
    <#if isPasswordError || isEmailError>
        <div class="invalid-feedback">
            ${errorText}
        </div>
    </#if>
    <button class="btn btn-lg btn-primary  mt-3 btn-block" type="submit">Sign in</button>
    <a href="/register" class="btn btn-lg btn-primary btn-block">Sign up</a>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2021</p>
</form>
</body>
</html>

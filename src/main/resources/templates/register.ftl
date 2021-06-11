<#include "partials/head.ftl">
<body class="text-center">
<form class="form-signin" method="post">
    <img class="mb-4" src="https://i.pinimg.com/564x/be/b3/67/beb36735e74caaeb6e18ac00dadc3794.jpg" alt="" width="72"
         height="72">
    <h1 class="h3 mb-3 font-weight-normal">Registration</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" name="email" value="${email}" id="inputEmail"
           class="form-control ${isEmailError?then('is-invalid','')}" placeholder="Email address" required>
    <label for="inputUri" class="sr-only">Avatar uri</label>
    <input type="text" name="avatar_uri" value="${avatar_uri}" id="inputUri" class="form-control"
           placeholder="Avatar URI" required>
    <label for="inputName" class="sr-only">Your name</label>
    <input type="text" name="name" value="${name}" id="inputName" class="form-control" placeholder="Your name" required>
    <label for="inputName" class="sr-only">Your job</label>
    <input type="text" name="job" value="${job}" id="inputName" class="form-control" placeholder="Your job" required>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" name="password" value="${password}" id="inputPassword"
           class="form-control ${isPasswordError?then('is-invalid','')}" placeholder="Password" required>

    <#if isPasswordError || isEmailError>
        <div class="invalid-feedback">
            ${errorText}
        </div>
    </#if>
    <button class="btn btn-lg mt-3 btn-primary btn-block" type="submit">Sign up</button>
    <a href="/login" class="btn btn-lg btn-primary btn-block">Sign in</a>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2021</p>
</form>
</body>
</html>

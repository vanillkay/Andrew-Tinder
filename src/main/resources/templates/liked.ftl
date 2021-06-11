<#include "partials/head.ftl">
<body>
<#include "partials/nav.ftl">
<div class="container">
    <div class="row">
        <div class="col-8 offset-2">

            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">Liked users</h3>
                    <#if users?size gt 0>
                        <h3 class="panel-title">Click on user's name to start a chat</h3>
                    </#if>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as user>
                                <tr class="d-flex  flex-column  flex-lg-row justify-content-around align-items-center border-bottom">
                                    <td class="border-top-0">
                                        <a href="/messages/?id=${user.ID}">
                                            <div class="avatar-img">
                                                <img class="img-circle"
                                                     src="${user.AVATAR_URI}" alt="avatar">
                                            </div>
                                        </a>
                                    </td>
                                    <td class="align-middle border-top-0">
                                        <a style="color: inherit; text-decoration: none;" class=".stretched-link"
                                           href="/messages/?id=${user.ID}">
                                            ${user.NAME}
                                        </a>
                                    </td>
                                    <td class="align-middle border-top-0">
                                        ${user.JOB}
                                    </td>
                                    <td class="align-middle border-top-0">
                                        Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small>
                                    </td>
                                </tr>
                            <#else>
                                <p class="no-liked-users">No liked users</p>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


<#include "partials/head.ftl">
<body>
<#include "partials/nav.ftl">
<div class="page-content page-container container" id="page-content">
    <div class="container d-flex justify-content-center align-self-center">
        <div class="card card-bordered">
            <div class="card-header">
                <h4 class="card-title"><strong>Chat with ${name}</strong><img class="avatar avatar-xs"
                                                                              src="${avatar_uri}"
                                                                              alt="..."></h4>
            </div>
            <div class="ps-container ps-theme-default ps-active-y" id="chat-content"
                 style="overflow-y: scroll !important; height:60vh !important;">
                <#list messages as message>
                    <div class="media media-chat ${message.ISOWN?then("media-chat-reverse", "")}">
                        <#if !message.ISOWN>
                            <img class="avatar" src="${avatar_uri}"
                                 alt="...">
                        </#if>
                        <div class="media-body">
                            <p>${message.TEXT}</p>
                            <#--                                                <p class="meta"><time datetime="2018">23:58</time></p>-->
                        </div>
                    </div>
                <#--                                        <div class="media media-meta-day">Today</div>-->
                <#else>
                    <div class="no-messages">No messages yet</div>
                </#list>
            </div>
            <form class="publisher bt-1 border-light" method="post"><input type="hidden" name="to_user"
                                                                           value="${to_user}"><img
                        class="avatar avatar-xs"
                        src="${own_avatar_uri}"
                        alt="..."><input
                        required
                        autocomplete="off"
                        class="publisher-input" type="text"
                        name="message"
                        placeholder="Write something">
                <button type="submit" style="outline: none"
                        class="publisher-btn text-info" data-abc="true"><i
                            class="fa fa-paper-plane"></i></button>
            </form>
        </div>

    </div>
</div>
</body>
</html>

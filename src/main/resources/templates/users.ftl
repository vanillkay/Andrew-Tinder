<#include "partials/head.ftl">
<body style="background-color: #f5f5f5;">
<#include "partials/nav.ftl">
<div class="container">
    <div class="col">
        <div class="col-sm-3 col-md-6 col-lg-5 m-auto">
            <#if user_name?? && user_avatar?? && user_id??>
                <form method="post">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12 col-lg-12 col-md-12 text-center">
                                    <img src=${user_avatar} alt=""
                                         class="mx-auto rounded-circle img-fluid">
                                    <h3 class="mb-0 text-truncated">${user_name}</h3>
                                    <br>
                                </div>
                                <div class="col-12 col-lg-6">
                                    <button type="submit" value="false" name="like"
                                            class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span>
                                        Dislike
                                    </button>
                                </div>
                                <input type="hidden" value="${user_id}" name="user_id">
                                <div class="col-12 col-lg-6">
                                    <button value="true" name="like" type="submit"
                                            class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span>
                                        Like
                                    </button>
                                </div>
                                <!--/col-->
                            </div>
                            <!--/row-->
                        </div>
                        <!--/card-block-->
                    </div>
                </form>
            <#else>
                <div class="no-new-users">There are no new users for you</div>
            </#if>
        </div>
    </div>
</div>
</body>
</html>

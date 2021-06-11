<nav class="nav nav-pills flex-column flex-sm-row mb-5">
    <#if userTab??>
        <a class="flex-sm-fill text-sm-center nav-link active" aria-current="page"
           href="/users">Users</a>
    <#else>
        <a class="flex-sm-fill text-sm-center nav-link" aria-current="page"
           href="/users">Users</a>
    </#if>
    <#if likedTab??>
        <a class="flex-sm-fill text-sm-center nav-link active"
           href="/liked">Liked</a>
    <#else>
        <a class="flex-sm-fill text-sm-center nav-link"
           href="/liked">Liked</a>
    </#if>
    <#if messageTab??>
        <a class="flex-sm-fill text-sm-center nav-link active"
           href="#">Messages</a>
    <#else>
        <a class="flex-sm-fill text-sm-center nav-link"
           href="">Messages</a>
    </#if>
    <a class="flex-sm-fill text-sm-center nav-link" href="/logout">Logout</a>
</nav>
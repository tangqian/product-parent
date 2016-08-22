<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- 左 -->
<div class="fl">
    <!-- 左菜单栏 -->
    <div class="menu" id="menu">
        <ul class="menu-parent">
            <!-- 样式：menu-cur 是控制显示二级 -->
            <li class="one-level menu-cur">
                <p class="ol-title">
                    <img src="${webSite}/static/web/modules/speaker/img/menu_1.png" />
                    <strong>直播管理</strong>
                </p>
                <!-- 二级 -->
                <ul class="menu-son">
                    <li class="two-level"><a id="subMenu_liveManager" href="javascript:">管理直播</a></li>
                </ul>
            </li>
            <li class="one-level menu-cur">
                <p class="ol-title">
                   <img src="${webSite}/static/web/modules/speaker/img/menu_2.png" />
                    <strong>资料管理</strong> 
                </p>
                <!-- 二级 -->
                <ul class="menu-son">
                    <!-- 选中的二级菜单项添加样式：menu-son-cur -->
                    <li class="two-level" id="sm_speaker_data"><a href="${ctx}/speaker/data">下载管理</a></li>
                    <li class="two-level"><a href="javascript:">视频管理</a></li>
                    <li class="two-level"><a href="javascript:">PPT管理</a></li>
                    <li class="two-level"><a href="javascript:">资料回收站</a></li>
                </ul>
            </li>
            <li class="one-level menu-cur">
                <p class="ol-title">
                    <img src="${webSite}/static/web/modules/speaker/img/menu_3.png" />
                    <strong>数据报告</strong>
                </p>
                <!-- 二级 -->
                <ul class="menu-son">
                    <li class="two-level"><a href="javascript:">观众数据</a></li>
                </ul>
            </li>
            <!-- 没有子级需要添加样式：no-clild 不显示箭头图标 -->
            <li class="one-level no-child">
                <p class="ol-title">
                    <img src="${webSite}/static/web/modules/speaker/img/menu_4.png" />
                    <strong>聊天记录</strong>
                </p>
            </li>
            <li class="one-level menu-cur">
                <p class="ol-title">
                    <img src="${webSite}/static/web/modules/speaker/img/menu_5.png" />
                    <strong>帐号管理</strong>
                </p>
                <!-- 二级 -->
                <ul class="menu-son">
                    <li class="two-level"><a href="javascript:">设置客服</a></li>
                    <li class="two-level"><a href="javascript:">账号安全</a></li>
                    <li class="two-level"><a href="javascript:">联系方式</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
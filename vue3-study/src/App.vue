
<template>
  <div class="app-shell">
    <header class="page-title">
      <h1>Vue路由测试</h1>
    </header>

    <nav class="route-nav" aria-label="页面导航">
      <span class="nav-dot" aria-hidden="true"></span>
      <!--  -->
      <RouterLink class="nav-button" active-class="is-active" to="/">首页</RouterLink>
      <RouterLink class="nav-button" active-class="is-active" to="/news">新闻</RouterLink>

      <!-- 
         to的几种写法：字符串写法和对象写法。
         对象中可以指定path或者name，都能跳转到指定页面。
      -->
      <!-- <RouterLink class="nav-button" active-class="is-active" to="/about">关于</RouterLink> -->
      <!-- <RouterLink class="nav-button" active-class="is-active" :to="{path:'/about'}">关于</RouterLink> -->
      <RouterLink class="nav-button" active-class="is-active" :to="{name:'/myabout'}">关于</RouterLink>
    </nav>

    <main class="route-content">
      <RouterView />
    </main>
  </div>
</template>


<!-- 
    1、路由组件放在views或者pages文件夹，一般组件放在components文件夹。（开发标准）
    2、通过路由，跳转到一个新页面，消失的原页面是会被销毁。
-->


<script lang="ts" setup name="App">
import { RouterLink, RouterView } from 'vue-router'
</script>

<style>
:root {
  font-family: "Microsoft YaHei", "PingFang SC", Arial, sans-serif;
  color: #202020;
  background: #ffffff;
  font-synthesis: none;
  text-rendering: optimizeLegibility;
}

* {
  box-sizing: border-box;
}

html,
body,
#app {
  min-width: 320px;
  min-height: 100%;
  margin: 0;
}

body {
  background: #ffffff;
}

button,
a {
  font: inherit;
}

.app-shell {
  min-height: 100vh;
  padding: 50px 0 64px;
  border-top: 24px solid #294d60;
  background: #ffffff;
}

.page-title {
  display: flex;
  align-items: center;
  justify-content: center;
  width: calc(100% - 8px);
  height: 128px;
  margin-left: 4px;
  border-radius: 16px;
  background: linear-gradient(105deg, #d8d8d8 0%, #eeeeee 52%, #f8f8f8 100%);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.24);
}

.page-title h1 {
  margin: 0;
  color: #050505;
  font-size: clamp(34px, 4vw, 58px);
  font-weight: 700;
  letter-spacing: 0.02em;
}

.route-nav {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: clamp(36px, 12vw, 170px);
  padding: 50px 5% 56px;
}

.nav-dot {
  position: absolute;
  top: 27px;
  left: 4.2%;
  width: 13px;
  height: 13px;
  border-radius: 50%;
  background: #d62020;
  box-shadow: 0 0 0 2px rgba(214, 32, 32, 0.08);
}

.nav-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: clamp(128px, 12vw, 166px);
  height: 72px;
  border-radius: 17px;
  color: #ffffff;
  background: #969696;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.14), 0 2px 4px rgba(0, 0, 0, 0.14);
  font-size: clamp(24px, 2.2vw, 32px);
  text-decoration: none;
  transition: transform 160ms ease, background-color 160ms ease, box-shadow 160ms ease;
}

.nav-button:hover,
.nav-button.is-active {
  background: #858585;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.12), 0 4px 8px rgba(0, 0, 0, 0.2);
  transform: translateY(-2px);
}

.nav-button:focus-visible {
  outline: 4px solid rgba(41, 77, 96, 0.35);
  outline-offset: 4px;
}

.route-content {
  min-height: clamp(500px, 62vh, 725px);
  margin: 0 5.2%;
  overflow: hidden;
  border: 2px solid #858585;
  border-radius: 17px;
  background: #ffffff;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
}

@media (max-width: 680px) {
  .app-shell {
    padding-top: 30px;
  }

  .page-title {
    height: 96px;
  }

  .route-nav {
    gap: 12px;
    padding-right: 4%;
    padding-left: 4%;
  }

  .nav-dot {
    left: 2%;
  }

  .nav-button {
    width: 31%;
    height: 58px;
  }

  .route-content {
    margin-right: 4%;
    margin-left: 4%;
  }
}
</style>

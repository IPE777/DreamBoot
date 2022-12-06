// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'


Vue.config.productionTip = false
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios";
Vue.use(ElementUI);
/* eslint-disable no-new */
axios.defaults.withCredentials = true



// 挂载路由导航守卫
// to 将要访问的路径
// from 代表从哪个路径跳转而来
// next 是个函数，表示放行 next() 放行  next('/login') 强制跳转
router.beforeEach((to, from, next) => {
  if (to.path.startsWith('/front/login')){
    window.localStorage.removeItem('user')

    next()
  } else {
    let user = window.localStorage.getItem('user')

    if (!user && to.path.startsWith('/front') ) {
      next({
        path: '/front/login'
      })

    }else {
      next()
    }
  }

  if (to.path.startsWith('/end/login')){
    window.localStorage.removeItem('user2')

    next()
  } else {
    let user2 = window.localStorage.getItem('user2')

    if (!user2 && to.path.startsWith('/end') ) {
      next({
        path: '/end/login'
      })

    } else {
      next()
    }
  }

});


new Vue({
  el: '#app',
  render: h => h(App),
  router,
  // 注意这里
  store,
  components: { App },
  template: '<App/>'
})


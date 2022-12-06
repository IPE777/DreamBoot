<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-avatar :src="image" ></el-avatar>  <span class="title">{{name}}</span>
        <i class="el-icon-switch-button" v-on:click="logout()" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>

      </el-header>

    <el-container style="height: 900px; border: 1px solid #eee">

      <el-aside width="200px"  style="background-color: rgb(238, 241, 246)">

        <el-menu router :default-openeds="['1','2','3']">
          <el-submenu v-for="(item,index) in $router.options.routes" :key="index" :index="index+''" v-if="item.show">
            <template slot="title"><i class ="el-icon-setting">{{item.name}}</i></template>
            <el-menu-item v-for="(item2,index2) in item.children" :key="index2" :index="item2.path"
                          :class="$route.path===item2.path?'is-active':''">{{item2.name}}</el-menu-item>
          </el-submenu>

        </el-menu>

      </el-aside>


      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    </el-container>

  </div>
</template>

<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 100px;
}

.el-aside {
  color: #333;
}
.el-icon-switch-button {
  cursor: pointer;
  outline:0;
}
</style>

<script>
import axios from "axios";

export default {
  name: "Index",
  data() {
    return {
      name: '',
      image: ''
    }


  },
  methods: {
    logout () {
      var _this = this
      axios.get('http://localhost:7777/end/api/logout').then(resp => {
        console.log(resp.data.code)
        if (resp.data.code === '200') {
          // 前后端状态保持一致
          _this.$store.commit('logout2')
          _this.$router.replace('/end/login')
        }
      })
    }
  },
  created() {
    console.log( window.localStorage.getItem('user2'))
    var _this = this
    axios.get('http://localhost:7777/end/api/getUser?username=' + window.localStorage.getItem('user2')).then(resp =>{
      if(resp.data.code === '200'){
        _this.image = resp.data.result.image
        _this.name = resp.data.result.name
        console.log(resp.data.result.image)
      }
    })
  }

}
</script>

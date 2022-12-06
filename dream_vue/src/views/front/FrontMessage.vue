<template>
  <div>
    <el-container>
      <el-header style="background: #e3e3e5">
        <el-avatar :src="image" style="float: left"></el-avatar>  <span class="title" style="float: left">{{name}}</span>
        <i class="el-icon-switch-button" v-on:click="logout" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
        <i class="el-icon-user-solid" v-on:click="toPerson" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
        <i class="el-icon-message-solid" v-on:click="toMessage" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
        <i class="el-icon-s-shop" v-on:click="toShop" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
        <i class="el-icon-s-promotion" v-on:click="toIndex" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>

      </el-header>

      <!--    <div class="demo-image__placeholder">-->
      <!--      <div class="block">-->
      <!--        <span class="demonstration">默认</span>-->
      <!--        <el-image :src="image"></el-image>-->
      <!--      </div>-->
<!--      :show-header="false"-->
      <!--    </div>-->
      <el-main style="background: #b8b8d3;height: 890px;text-align: center;" >
        <el-table
          :data="tableData"


          style="width: 100%;">

<!--          <el-table-column-->

<!--            prop="id"-->
<!--            label="编号"-->
<!--            min-width="10%">-->
<!--          </el-table-column>-->
<!--          <el-table-column-->

<!--            prop="customer_id"-->
<!--            label="客户编号"-->
<!--            min-width="12%">-->
<!--          </el-table-column>-->


          <el-table-column

            prop="customer_name"
            label="名称"
            min-width="3%">
          </el-table-column>
<!--          <el-table-column-->

<!--            prop="content_id"-->
<!--            label="文章id"-->
<!--            min-width="12%">-->
<!--          </el-table-column>-->

<!--          <el-table-column-->

<!--            prop="content_name"-->
<!--            label="文章内容"-->
<!--            min-width="12%">-->
<!--          </el-table-column>-->

          <el-table-column label="文章图片"  min-width="8%" prop="license">

            <template width="40%" slot-scope="scope">

              <img style="width:150px;height:150px;border:none;" :src="scope.row.content_photos">

            </template>

          </el-table-column>

          <el-table-column

            prop="message"
            label="消息内容"
            min-width="20%">
          </el-table-column>
          <el-table-column

            prop="create_time"
            label="时间"
            min-width="12%">
          </el-table-column>
          <el-table-column

            prop="type"
            label="消息类型"
            min-width="12%">
            <template slot-scope="scope">{{options[scope.row.type]['label']}}</template>
          </el-table-column>




          <!--          <el-table-column-->
<!--            fixed="right"-->
<!--            label="操作"-->
<!--            width="100">-->
<!--            <template slot-scope="scope">-->
<!--              <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>-->
<!--              <el-button @click="deleteMessage(scope.row)" type="text" size="small">删除</el-button>-->
<!--            </template>-->
<!--          </el-table-column>-->
        </el-table>

        <el-pagination
          background
          layout="prev, pager, next"
          page-size="4"
          :total=total
          @current-change="page">
        </el-pagination>

      </el-main>
    </el-container>
  </div>
</template>
<style>
.el-icon-switch-button {
  cursor: pointer;
  outline:0;
}

.el-icon-user-solid{
  cursor: pointer;
  outline:0;
}
.el-icon-message-solid{
  cursor: pointer;
  outline:0;
}

.el-icon-s-promotion{
  cursor: pointer;
  outline:0;
}
.el-icon-s-shop{
  cursor: pointer;
  outline:0;
}

.el-header{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
</style>
<script>
import axios from "axios";

export default {
  name: "FrontMessage",
  data(){
    return{
      id: '',
      name: '',
      image: '',
      tableData: null,
      total: null,
      options: [
        {
          value: 0,
          label: "用户"
        },
        {
          value: 1,
          label: "后台"
        }
      ],
    }
  },
  methods: {
    logout () {
      var _this = this
      this.$confirm('确定退出吗?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/front/api/logout').then(resp => {
          console.log(resp.data.code)
          if (resp.data.code === '200') {
            // 前后端状态保持一致
            _this.$store.commit('logout')
            _this.$router.replace('/front/login')
          }
        })
      })
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/message/getMyMessage/'+currentPage+'/4/'+ _this.id).then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total

      })
    },
    handleChange(val) {
      console.log(val);
    },
    toPerson(){
      var _this = this
      _this.$router.push('/front/publish')
    },
    toIndex(){
      var _this = this
      _this.$router.push('/front/index')
    },
    toMessage(){
      var _this = this
      _this.$router.push('/front/message')
    },
    toShop(){
    var _this = this
    _this.$router.push('/front/shop')
    }
  },
  created() {
    console.log( window.localStorage.getItem('user'))
    var _this = this
    axios.get('http://localhost:7777/front/api/getUser?username=' + window.localStorage.getItem('user')).then(resp =>{
      if(resp.data.code === '200'){
        _this.image = resp.data.result.image
        _this.name = resp.data.result.name
        _this.id = resp.data.result.id

        console.log(resp.data.result.image)

        axios.get('http://localhost:7777/message/getMyMessage/1/4/'+ _this.id).then(function (resp){
          _this.tableData = resp.data.result.records
          console.log(_this.tableData)

          _this.total = resp.data.result.total
          console.log("_this.total="+_this.total)

        })
      }
    })


  }
}
</script>



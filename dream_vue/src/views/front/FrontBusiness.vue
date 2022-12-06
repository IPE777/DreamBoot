<template>
  <div>
    <el-container style="background: #b8b8d3">
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

      <!--    </div>-->

      <div class="line"></div>
      <el-menu
        router
        default-active="/front/business"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="text-align: center;width: 100%">
        <el-menu-item index="/front/person" style="width: 25%">个人信息</el-menu-item>
        <el-menu-item index="/front/publish" style="width: 25%">我的发布</el-menu-item>
        <el-menu-item index="/front/like" style="width: 25%">我的喜欢</el-menu-item>
        <el-menu-item index="/front/business" style="width: 25%">我的商城</el-menu-item>

      </el-menu>


      <el-main style="background: #b8b8d3;height: 890px;text-align: center;" >

        <el-table
          :data="tableData"
          border
          style="width: 100%">

<!--          <el-table-column-->

<!--            prop="id"-->
<!--            label="编号"-->
<!--            min-width="10%">-->
<!--          </el-table-column>-->
<!--          <el-table-column-->

<!--            prop="code"-->
<!--            label="编码"-->
<!--            min-width="10%">-->
<!--          </el-table-column>-->


          <el-table-column

            prop="customer_name"
            label="客户名称"
            min-width="10%">
          </el-table-column>
          <el-table-column

            prop="goods_name"
            label="商品名称"
            min-width="10%">
          </el-table-column>
          <el-table-column

            prop="count"
            label="数量"
            min-width="10%">
          </el-table-column>
          <el-table-column

            prop="price"
            label="单价"
            min-width="10%">
          </el-table-column>

          <el-table-column
            prop="sum"
            label="总额"
            min-width="10%">
          </el-table-column>
          <el-table-column
            prop="status"
            label="状态"
            min-width="12%">
            <template slot-scope="scope">{{options[scope.row.status]['label']}}</template>
          </el-table-column>
<!--          <el-table-column-->
<!--            prop="goods_code"-->
<!--            label="商品编号"-->
<!--            min-width="10%">-->
<!--          </el-table-column>-->
          <el-table-column
            prop="create_time"
            label="创建时间"
            min-width="10%">
          </el-table-column>



<!--          <el-table-column-->
<!--            fixed="right"-->
<!--            label="操作"-->
<!--            width="100">-->
<!--            <template slot-scope="scope">-->
<!--              <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>-->
<!--              <el-button @click="deleteUser(scope.row)" type="text" size="small">删除</el-button>-->
<!--            </template>-->
<!--          </el-table-column>-->
        </el-table>

        <el-pagination
          background
          layout="prev, pager, next"
          page-size="5"
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
  name: "FrontBusiness",
  data(){
    return{
      id: '',
      name: '',
      image: '',
      total: null,
      tableData: null,
      bizGoodsOrderForm: {
        id: '',
        code: '',
        customer_id: '',
        customer_name: '',
        customer_address: '',
        goods_id: '',
        goods_name: '',
        goods_code: '',
        count: '',
        price: '',
        sum: '',
        status: '',

      },
      options: [
        {
          value: 0,
          label: "未处理"
        },
        {
          value: 1,
          label: "处理中"
        },
        {
          value: 2,
          label: "已处理"
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
      axios.get('http://localhost:7777/goodsOrder/getMyGoodsOrder/'+currentPage+'/5/' + _this.id).then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total


      })
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
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

        axios.get('http://localhost:7777/goodsOrder/getMyGoodsOrder/1/5/'+ _this.id).then(function (resp){
          _this.tableData = resp.data.result.records

          _this.total = resp.data.result.total

        })
      }
    })


  }
}
</script>



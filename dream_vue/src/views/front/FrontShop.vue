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

      <!--    </div>-->
      <el-dialog title="购买" :visible.sync="dialogFormVisible">
        <el-form :model="orderGoodsForm" :rules="rules" ref="orderGoodsForm" label-width="100px" class="demo-ruleForm">

          <el-form-item label="商品名称:" prop="goods_name">
            <span style="width: 250px">{{orderGoodsForm.goods_name}}</span>
          </el-form-item>

          <el-form-item label="图片:">
              <img v-if="bizGoodsForm.photos" :src="bizGoodsForm.photos" class="avatar">
          </el-form-item>

          <el-form-item label="商品类别:" prop="status">
            <span style="width: 250px">{{options[bizGoodsForm.type]['label']}}</span>
          </el-form-item>

          <el-form-item label="姓名:" prop="customer_name">
            <span style="width: 250px">{{orderGoodsForm.customer_name}}</span>
          </el-form-item>

          <el-form-item label="电话:" prop="customer_phone">
            <span style="width: 250px">{{orderGoodsForm.customer_phone}}</span>
          </el-form-item>

          <el-form-item label="送货地址:" prop="customer_address">
            <span style="width: 250px">{{orderGoodsForm.customer_address}}</span>
          </el-form-item>

          <el-form-item label="数量:" prop="sum">
            <span style="width: 250px">{{orderGoodsForm.count}}</span>
          </el-form-item>

          <el-form-item label="总额:" prop="sum">
            <span style="width: 250px">{{orderGoodsForm.sum}}</span>
          </el-form-item>


        </el-form>

        <div slot="footer" class="dialog-footer">

          <el-button type="primary" @click="submitForm('orderGoodsForm')">立即购买</el-button>

        </div>
      </el-dialog>


      <el-drawer
        title="商品详情"

        :visible.sync="drawer">
        <el-form :model="bizGoodsForm" :rules="rules" ref="bizGoodsForm" label-width="100px" class="demo-ruleForm">


          <el-form-item label="图片:">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:7777/common/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="bizGoodsForm.photos" :src="bizGoodsForm.photos" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>

          <el-form-item label="名称:" prop="name">
            <span>{{bizGoodsForm.name}}</span>
          </el-form-item>


          <el-form-item label="价格:" prop="price">
            <span>{{bizGoodsForm.price}}元</span>

          </el-form-item>

          <el-form-item label="库存:" prop="stock">
            <span>{{bizGoodsForm.stock}}件</span>
          </el-form-item>

          <el-form-item label="类型:" prop="type">
            <span>{{options[bizGoodsForm.type]['label']}}</span>
          </el-form-item>

          <el-form-item label="数量:">
            <el-input-number v-model="num" controls-position="right" @change="handleChange" :min="1" :max="10"></el-input-number>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="buy('orderGoodsForm')">立即购买</el-button>
          </el-form-item>

        </el-form>
      </el-drawer>

      <el-main style="background: #b8b8d3;height: 890px;text-align: center;" >
        <el-table
          :data="tableData"
          :show-header="false"
          style="width: 700px;margin-left: 550px;">
          <el-table-column
            prop="date"
            label="头像"
            width="700">

            <template slot-scope="scope">

              <el-image
                style="width: 100%;height: 100%;"
                :src="scope.row.photos"
                @click="selectGoods(scope.row)"
                >

              </el-image>

            </template>
          </el-table-column>

        </el-table>

      </el-main>
    </el-container>
  </div>
</template>
<style>

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
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
  name: "FrontShop",
  data(){
    return{
      id: '',
      name: '',
      image: '',
      total: null,
      tableData: null,
      drawer: false,
      num: 1,
      rowid: '',
      dialogFormVisible: false,
      bizGoodsForm: {
        id: '',
        name: '',
        photos: '',
        price: '',
        stock: '',
        type: 0,
      },
      orderGoodsForm:{
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
      },
      options: [
        {
          value: 0,
          label: "未知"
        },
        {
          value: 1,
          label: "水泥"
        },
        {
          value: 2,
          label: "玻璃"
        },
        {
          value: 3,
          label: "石材"
        },
        {
          value: 4,
          label: "涂鸦"
        },
        {
          value: 5,
          label: "塑料"
        },
        {
          value: 6,
          label: "地板"
        },
        {
          value: 7,
          label: "大理石"
        },
        {
          value: 8,
          label: "玻璃钢"
        },
        {
          value: 9,
          label: "装饰板"
        }
      ],

      rules: {
        // name: [
        //   { required: true, message: '请输入名称', trigger: 'change' }
        // ]

      }
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
    selectGoods(row){
      const _this = this
      _this.drawer = true
      _this.rowid = row.id
      axios.get('http://localhost:7777/goods/findById/'+ _this.rowid).then(function (resp){
        _this.bizGoodsForm = resp.data.result
        _this.orderGoodsForm.goods_code = resp.data.result.code
        _this.orderGoodsForm.goods_id = resp.data.result.id
        _this.orderGoodsForm.goods_name = resp.data.result.name


      })



      // this.$router.push({
      //   path: '/front/goods',
      //   query:{
      //     id: row.id
      //   }
      // })

    },
    buy(formName){
      const _this = this
      _this.orderGoodsForm.count = _this.num
      _this.orderGoodsForm.price = _this.bizGoodsForm.price
      _this.orderGoodsForm.sum = _this.num * _this.bizGoodsForm.price

      if(_this.num > _this.bizGoodsForm.stock){
        _this.$alert('库存不足', '消息', {
          confirmButtonText: '确定',
          callback: action => {

          }
        })
      }else{
        _this.dialogFormVisible = true
        console.log( _this.orderGoodsForm)
      }
    },
    submitForm(formName){
      const _this = this
      this.$confirm('确定购买吗?','提示',{
        type:'warning'
      }).then(()=>{
        axios.post('http://localhost:7777/goodsOrder/add',_this.orderGoodsForm).then(function (resp){
          if (resp.data.code === "200") {
            alert("购买成功")
            _this.dialogFormVisible = false
            _this.drawer = false
          } else {
            alert("购买失败,库存不足")
            _this.dialogFormVisible = false
            _this.drawer = false
          }
        }).catch((error) => {
          console.log("购买失败")
        })
      })
    },
    handleChange(value) {
      console.log(value);
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleAvatarSuccess(response, file) {
      console.log(file.name)
      console.log("图片路径=" + response.returnFileUrl)
      this.bizGoodsForm.image = response.returnFileUrl

    },

    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }

      return isJPG && isLt2M;
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
        _this.orderGoodsForm.customer_id = resp.data.result.id
        _this.orderGoodsForm.customer_name = resp.data.result.name
        _this.orderGoodsForm.customer_phone = resp.data.result.phone
        _this.orderGoodsForm.customer_address = resp.data.result.address
      }
    })

    axios.get('http://localhost:7777/goods/getGoods/1/5').then(function (resp){
      _this.tableData = resp.data.result.records
      _this.total = resp.data.result.total

      axios.get('http://localhost:7777/goods/getGoods/1/'+_this.total).then(function (resp){
        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total
    })


    })

  }
}
</script>



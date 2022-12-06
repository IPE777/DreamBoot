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
          default-active="/front/person"
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


      <el-main style="height: 832px;">
        <el-form :model="customerForm" :rules="rules" ref="customerForm" label-width="100px" class="demo-ruleForm"
        style="padding-left: 620px">

          <el-form-item label="姓名" prop="name">
            <el-input v-model="customerForm.name"  style="width: 250px"></el-input>
          </el-form-item>

          <el-form-item label="照片">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:7777/common/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="customerForm.image" :src="customerForm.image" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>


          <el-form-item label="账号" prop="username" >
            <el-input v-model="customerForm.username" style="width: 250px" :disabled="true"></el-input>
          </el-form-item>

<!--          <el-form-item label="密码" prop="password">-->
<!--            <el-input v-model="customerForm.password" style="width: 250px"></el-input>-->
<!--          </el-form-item>-->

          <el-form-item label="手机" prop="phone">
            <el-input v-model="customerForm.phone" style="width: 250px"></el-input>
          </el-form-item>

          <el-form-item label="邮箱" prop="email">
            <el-input v-model="customerForm.email" style="width: 250px"></el-input>
          </el-form-item>

          <el-form-item label="地址" prop="address">
            <el-input v-model="customerForm.address" style="width: 250px"></el-input>
          </el-form-item>


          <el-form-item label="性别">
            <el-select v-model="customerForm.sex" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>




          <el-form-item>
            <el-button type="primary" @click="submitForm('customerForm')">立即修改</el-button>

          </el-form-item>

        </el-form>
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
  name: "FrontPerson",
  data(){
    return{
      name: '',
      image: '',
      customerForm: {
        id: '',
        name: '',
        image: '',
        username: '',
        password: '',
        phone: '',
        email: '',
        address: '',
        sex: ''

      },

      options: [{
        value: 0,
        label: '未知'
      },{
        value: 1,
        label: '男'
      },{
        value: "2",
        label: "女"
      }],

      rules: {

        name: [
          { required: true, message: '请输入姓名', trigger: 'change' }
        ]
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
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.put('http://localhost:7777/customer/updateById',this.customerForm).then(function (resp){
            if(resp.data.result === 'success'){
              console.log(resp)
              _this.$alert('修改成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.dialogFormVisible = false
                  window.location.reload()
                }
              })
            }


          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleAvatarSuccess(response, file) {
      console.log(file.name)
      console.log("图片路径=" + response.returnFileUrl)
      this.customerForm.image = response.returnFileUrl

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

        _this.customerForm = resp.data.result

        console.log(resp.data.result.image)
      }
    })


  }
}
</script>



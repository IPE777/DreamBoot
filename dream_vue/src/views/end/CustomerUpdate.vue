<template>
  <el-form :model="customerForm" :rules="rules" ref="customerForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="name" prop="name">
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


    <el-form-item label="账号" prop="username">
      <el-input v-model="customerForm.username" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="password" prop="password">
      <el-input v-model="customerForm.password" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="phone" prop="phone">
      <el-input v-model="customerForm.phone" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="email" prop="email">
      <el-input v-model="customerForm.email" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="address" prop="email">
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
      <el-button @click="resetForm('customerForm')">重置</el-button>

    </el-form-item>

  </el-form>
</template>



<script>
import axios from "axios";

import moment from 'moment'


export default {
  name: "CustomerUpdate",
  data() {
    return {
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
        label: '女'
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
    };
  },
  methods: {
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {

          axios.put('http://localhost:7777/customer/updateById',this.customerForm).then(function (resp){

            if(resp.data.result === 'success'){

              _this.$alert('修改成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/customer')
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
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:7777/customer/findById/'+this.$route.query.id).then(function (resp){
      _this.customerForm = resp.data.result

    })
  }
}
</script>

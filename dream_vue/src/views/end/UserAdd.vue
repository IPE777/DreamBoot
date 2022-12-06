<template>
  <el-form :model="userForm" :rules="rules" ref="userForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="name" prop="name">
      <el-input v-model="userForm.name"  style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="照片">
      <el-upload
        class="avatar-uploader"
        action="http://localhost:7777/common/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="userForm.image" :src="userForm.image" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>


    <el-form-item label="账号" prop="username">
      <el-input v-model="userForm.username" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="password" prop="password">
      <el-input v-model="userForm.password" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="phone" prop="phone">
      <el-input v-model="userForm.phone" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="email" prop="email">
      <el-input v-model="userForm.email" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="出生年月">
      <div class="block">

        <el-date-picker
          v-model="userForm.birthday"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择日期">
        </el-date-picker>
      </div>
    </el-form-item>



    <el-form-item label="性别">
      <el-select v-model="userForm.sex" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>




    <el-form-item>
      <el-button type="primary" @click="submitForm('userForm')">立即创建</el-button>
      <el-button @click="resetForm('userForm')">重置</el-button>

    </el-form-item>

  </el-form>
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
</style>
<script>
import axios from "axios";

export default {
  name: "UserAdd",
  data() {
    return {
      userForm: {
        id: '',
        name: '',
        image: '',
        username: '',
        password: '',
        phone: '',
        email: '',
        birthday: '',
        sex: ''

      },

      options: [{
        value: 0,
        label: '未知'
      },{
        value: 1,
        label: '男'
      },{
        value: 2,
        label: '女'
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
          axios.post('http://localhost:7777/user/add',this.userForm).then(function (resp){
            if(resp.data.result === 'success'){
              console.log(resp)
              _this.$alert('添加成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/user')
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
      this.userForm.image = response.returnFileUrl

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
  }
}
</script>



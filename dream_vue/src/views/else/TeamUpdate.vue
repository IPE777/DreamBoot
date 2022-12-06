<template>
  <el-form :model="teamForm" :rules="rules" ref="teamForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="团队名" prop="name">
      <el-input v-model="teamForm.name"  style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="联系人" prop="username">
      <el-input v-model="teamForm.contact" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="电话" prop="phone">
      <el-input v-model="teamForm.phone" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="地址" prop="address">
      <el-input v-model="teamForm.address" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="执照">
      <el-upload
        class="avatar-uploader"
        action="http://localhost:7777/common/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="teamForm.license" :src="teamForm.license" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>

    <el-form-item label="满意度">
      <el-select v-model="teamForm.satisfied" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="订单数" prop="order_sum">
      <el-input v-model="teamForm.order_sum" style="width: 250px"></el-input>
    </el-form-item>


    <el-form-item>
      <el-button type="primary" @click="submitForm('teamForm')">立即创建</el-button>
      <el-button @click="resetForm('teamForm')">重置</el-button>

    </el-form-item>

  </el-form>
</template>



<script>
import axios from "axios";

import moment from 'moment'


export default {
  name: "teamUpdate",
  data() {
    return {
      teamForm: {
        id: '',
        code: '',
        name: '',
        contact: '',
        phone: '',
        address: '',
        license: '',
        satisfied: '',
        order_sum: ''

      },

      options: [{
        value: 1,
        label: '优'
      },{
        value: 2,
        label: '良'
      },{
        value: "2",
        label: "差"
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

          axios.put('http://localhost:7777/team/updateById',this.teamForm).then(function (resp){

            if(resp.data.result === 'success'){

              _this.$alert('修改成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/team')
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
      this.teamForm.license = response.returnFileUrl

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
    axios.get('http://localhost:7777/team/findById/'+this.$route.query.id).then(function (resp){
      _this.teamForm = resp.data.result

    })
  }
}
</script>

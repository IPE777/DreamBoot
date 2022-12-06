<template>
  <el-form :model="bizSchemeForm" :rules="rules" ref="bizSchemeForm" label-width="100px" class="demo-ruleForm">



    <el-form-item label="客户名称" prop="customer_name">
      <el-input v-model="bizSchemeForm.customer_name"  style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="员工名称" prop="user_name">
      <el-input v-model="bizSchemeForm.user_name" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="方案" prop="user_name">
      <el-input type="textarea" v-model="bizSchemeForm.scheme" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="预算" prop="user_name">
      <el-input v-model="bizSchemeForm.budget" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="状态">
      <el-select v-model="bizSchemeForm.status" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>


    <el-form-item>
      <el-button type="primary" @click="submitForm('bizSchemeForm')">立即修改</el-button>
      <el-button @click="resetForm('bizSchemeForm')">重置</el-button>

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
  name: "BizOrderAdd",
  data() {
    return {
      bizSchemeForm: {
        id: '',
        customer_name: '',
        user_name: '',
        scheme: '',
        budget: '',
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

      rules: {

        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ]
      }
    };
  },
  methods: {

    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/scheme/add',this.bizSchemeForm).then(function (resp){
            if(resp.data.result === 'success'){
              console.log(resp)
              _this.$alert('添加成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/bizScheme')
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
    }
  }
}
</script>



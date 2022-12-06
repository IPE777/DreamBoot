<template>
  <el-form :model="commentForm" :rules="rules" ref="commentForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="用户名称" prop="customer_name">
      <el-input v-model="commentForm.customer_name"  style="width: 250px"></el-input>
    </el-form-item>


    <el-form-item label="评论" prop="comment">
      <el-input v-model="commentForm.comment" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="点赞数" prop="likes">
      <el-input v-model="commentForm.likes" style="width: 250px"></el-input>
    </el-form-item>


    <el-form-item>
      <el-button type="primary" @click="submitForm('commentForm')">立即创建</el-button>
      <el-button @click="resetForm('commentForm')">重置</el-button>

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
  name: "CommentAdd",
  data() {
    return {
      commentForm: {
        id: '',
        customer_name: '',
        comment: '',
        likes: ''

      },

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
          axios.post('http://localhost:7777/comment/add',this.commentForm).then(function (resp){
            if(resp.data.result === 'success'){
              console.log(resp)
              _this.$alert('添加成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/comment')
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



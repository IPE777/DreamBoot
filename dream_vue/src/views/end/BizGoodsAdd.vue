<template>
  <el-form :model="bizGoodsForm" :rules="rules" ref="bizGoodsForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="name" prop="name">
      <el-input v-model="bizGoodsForm.name"  style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="图片">
      <el-upload
        class="avatar-uploader"
        action="http://localhost:7777/common/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="bizGoodsForm.image" :src="bizGoodsForm.image" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>


    <el-form-item label="价格" prop="price">
      <el-input v-model="bizGoodsForm.price" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="库存" prop="stock">
      <el-input v-model="bizGoodsForm.stock" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="类型">
      <el-select v-model="bizGoodsForm.type" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>


    <el-form-item>
      <el-button type="primary" @click="submitForm('bizGoodsForm')">立即创建</el-button>
      <el-button @click="resetForm('bizGoodsForm')">重置</el-button>

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
  name: "BizGoodsAdd",
  data() {
    return {
      bizGoodsForm: {
        id: '',
        name: '',
        image: '',
        price: '',
        stock: '',
        type: '',

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
          axios.post('http://localhost:7777/goods/add',this.bizGoodsForm).then(function (resp){
            if(resp.data.result === 'success'){
              console.log(resp)
              _this.$alert('添加成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/bizGoods')
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
    }
  }
}
</script>


